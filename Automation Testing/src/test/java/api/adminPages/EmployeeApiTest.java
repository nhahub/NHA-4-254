package api.adminPages;

import api.base.BaseApiTest;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.example.api.adminPages.Employee;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * API tests for OrangeHRM's internal Employee endpoint:
 * GET    /api/v2/pim/employees             - list/search employees
 * POST   /api/v2/pim/employees             - create employee
 * GET    /api/v2/pim/employees/{empNumber} - get one employee
 * PUT    /api/v2/pim/employees/{empNumber} - update employee
 * DELETE /api/v2/pim/employees             - delete employee(s) (ids in body)
 * <p>
 * NOTE ON SCOPE: This is OrangeHRM's internal API (the same one its own Vue.js
 * frontend calls), authenticated via session cookie + CSRF token rather than
 * the documented OAuth2 Bearer-token API (which is exclusive to the licensed
 * Starter/Professional/Enterprise editions and is not exposed on this public
 * demo). See SessionManager for the login mechanics.
 */
@Epic("API Testing")
@Feature("Employee Management API")
public class EmployeeApiTest extends BaseApiTest {

    private static final String EMPLOYEES_ENDPOINT = "/api/v2/pim/employees";

    // Track IDs created during this test class run so we can clean them up.
    private Integer createdEmpNumber;

    // ───────────────────────────── GET (list / search) ───────────────────────────

    @Test(description = "TC_API_001 - Verify GET employees returns 200 and a non-empty data array")
    @Story("TC_API_001 - List employees")
    public void verifyGetEmployeesListReturns200() {
        Response response = authSpec
                .when()
                .get(EMPLOYEES_ENDPOINT)
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

        List<Map<String, Object>> data = response.jsonPath().getList("data");
        Assert.assertNotNull(data, "Response should contain a 'data' array.");
        Assert.assertFalse(data.isEmpty(), "Employee list should not be empty on a seeded demo instance.");
    }

    @Test(description = "TC_API_002 - Verify GET employees response contains expected pagination metadata")
    @Story("TC_API_002 - Pagination metadata")
    public void verifyGetEmployeesContainsMeta() {
        authSpec
                .when()
                .get(EMPLOYEES_ENDPOINT)
                .then()
                .statusCode(200)
                .body("meta", notNullValue())
                .body("meta.total", greaterThanOrEqualTo(0));
    }

    @Test(description = "TC_API_003 - Verify GET employees supports filtering by nameOrId query param")
    @Story("TC_API_003 - Search/filter employees")
    public void verifyGetEmployeesFilterByName() {
        Response response = authSpec
                .queryParam("nameOrId", "John")
                .when()
                .get(EMPLOYEES_ENDPOINT)
                .then()
                .statusCode(200)
                .extract().response();

        List<Map<String, Object>> data = response.jsonPath().getList("data");
        Assert.assertNotNull(data, "Filtered response should still contain a 'data' array, even if empty.");
    }

    @Test(description = "TC_API_004 - Verify GET employees with a non-existent filter returns an empty data array, not an error")
    @Story("TC_API_004 - Search with no matches")
    public void verifyGetEmployeesFilterNoMatches() {
        authSpec
                .queryParam("nameOrId", "ZZZ_NonExistentEmployeeXYZ_999")
                .when()
                .get(EMPLOYEES_ENDPOINT)
                .then()
                .statusCode(200)
                .body("data", hasSize(0));
    }

    @Test(description = "TC_API_005 - Verify GET employees supports pagination via limit and offset params")
    @Story("TC_API_005 - Pagination controls")
    public void verifyGetEmployeesPaginationParams() {
        Response response = authSpec
                .queryParam("limit", 2)
                .queryParam("offset", 0)
                .when()
                .get(EMPLOYEES_ENDPOINT)
                .then()
                .statusCode(200)
                .extract().response();

        List<Map<String, Object>> data = response.jsonPath().getList("data");
        Assert.assertTrue(data.size() <= 2,
                "Requesting limit=2 should return at most 2 records, got " + data.size());
    }

    // ───────────────────────────── GET (single resource) ─────────────────────────

    @Test(description = "TC_API_006 - Verify GET a specific employee by empNumber returns full employee details")
    @Story("TC_API_006 - Get single employee")
    public void verifyGetSingleEmployeeByEmpNumber() {
        // Fetch a known empNumber from the list first (data-driven, not hardcoded)
        Response listResponse = authSpec.get(EMPLOYEES_ENDPOINT);
        Integer empNumber = listResponse.jsonPath().getInt("data[0].empNumber");

        authSpec
                .when()
                .get(EMPLOYEES_ENDPOINT + "/" + empNumber)
                .then()
                .statusCode(200)
                .body("data.empNumber", equalTo(empNumber))
                .body("data.firstName", notNullValue())
                .body("data.lastName", notNullValue());
    }

    @Test(description = "TC_API_007 - Verify GET employee with an invalid/non-existent empNumber returns 404")
    @Story("TC_API_007 - Get non-existent employee")
    public void verifyGetEmployeeInvalidIdReturns404() {
        authSpec
                .when()
                .get(EMPLOYEES_ENDPOINT + "/999999999")
                .then()
                .statusCode(404);
    }

    @Test(description = "TC_API_008 - Verify GET employee with a non-numeric empNumber returns 4xx, not a server error")
    @Story("TC_API_008 - Invalid path parameter type")
    public void verifyGetEmployeeNonNumericIdReturns4xx() {
        authSpec
                .when()
                .get(EMPLOYEES_ENDPOINT + "/abc")
                .then()
                .statusCode(allOf(greaterThanOrEqualTo(400), lessThan(500)));
    }

    // ───────────────────────────── POST (create) ──────────────────────────────────

    @Test(description = "TC_API_009 - Verify POST creates a new employee successfully with valid data")
    @Story("TC_API_009 - Create employee")
    public void verifyCreateEmployeeValidData() {
        String uniqueLastName = "ApiTest" + System.currentTimeMillis();
        Employee newEmployee = new Employee("John", uniqueLastName, null);

        Response response = authSpec
                .body(newEmployee)
                .when()
                .post(EMPLOYEES_ENDPOINT)
                .then()
                .statusCode(200)
                .body("data.firstName", equalTo("John"))
                .body("data.lastName", equalTo(uniqueLastName))
                .body("data.empNumber", notNullValue())
                .extract().response();

        createdEmpNumber = response.jsonPath().getInt("data.empNumber");
        Assert.assertNotNull(createdEmpNumber, "Newly created employee should be assigned an empNumber.");
    }

    @Test(description = "TC_API_010 - Verify POST without required firstName/lastName returns 400 with validation error")
    @Story("TC_API_010 - Create employee validation")
    public void verifyCreateEmployeeMissingRequiredFields() {
        Map<String, Object> invalidPayload = Map.of("middleName", "NoFirstOrLastName");

        authSpec
                .body(invalidPayload)
                .when()
                .post(EMPLOYEES_ENDPOINT)
                .then()
                .statusCode(400);
    }

    @Test(description = "TC_API_011 - Verify POST with an empty request body returns 400")
    @Story("TC_API_011 - Create employee with empty body")
    public void verifyCreateEmployeeEmptyBody() {
        authSpec
                .body("{}")
                .when()
                .post(EMPLOYEES_ENDPOINT)
                .then()
                .statusCode(400);
    }

    @Test(description = "TC_API_012 - Verify POST with excessively long firstName is rejected with 400")
    @Story("TC_API_012 - Create employee with oversized field")
    public void verifyCreateEmployeeOversizedFirstName() {
        String longName = "A".repeat(500);
        Employee invalidEmployee = new Employee(longName, "OversizedTest", null);

        authSpec
                .body(invalidEmployee)
                .when()
                .post(EMPLOYEES_ENDPOINT)
                .then()
                .statusCode(400);
    }

    // ───────────────────────────── PUT (update) ────────────────────────────────────

    @Test(description = "TC_API_013 - Verify PUT updates an existing employee's details successfully",
            dependsOnMethods = "verifyCreateEmployeeValidData")
    @Story("TC_API_013 - Update employee")
    public void verifyUpdateEmployeeValidData() {
        Assert.assertNotNull(createdEmpNumber, "An employee must have been created before this test can run.");

        String updatedLastName = "ApiTestUpdated" + System.currentTimeMillis();
        Employee updatedEmployee = new Employee("John", updatedLastName, null);

        authSpec
                .body(updatedEmployee)
                .when()
                .put(EMPLOYEES_ENDPOINT + "/" + createdEmpNumber)
                .then()
                .statusCode(200)
                .body("data.lastName", equalTo(updatedLastName));
    }

    @Test(description = "TC_API_014 - Verify PUT on a non-existent empNumber returns 404")
    @Story("TC_API_014 - Update non-existent employee")
    public void verifyUpdateEmployeeNonExistentId() {
        Employee updatedEmployee = new Employee("Ghost", "Employee", null);

        authSpec
                .body(updatedEmployee)
                .when()
                .put(EMPLOYEES_ENDPOINT + "/999999999")
                .then()
                .statusCode(404);
    }

    @Test(description = "TC_API_015 - Verify PUT with invalid (empty) lastName is rejected with 400")
    @Story("TC_API_015 - Update employee validation")
    public void verifyUpdateEmployeeInvalidData() {
        Assert.assertNotNull(createdEmpNumber, "An employee must exist before testing invalid update.");

        Map<String, Object> invalidPayload = Map.of("firstName", "John", "lastName", "");

        authSpec
                .body(invalidPayload)
                .when()
                .put(EMPLOYEES_ENDPOINT + "/" + createdEmpNumber)
                .then()
                .statusCode(400);
    }

    // ───────────────────────────── DELETE ──────────────────────────────────────────

    @Test(description = "TC_API_016 - Verify DELETE removes the employee and it no longer appears via GET",
            dependsOnMethods = "verifyUpdateEmployeeValidData")
    @Story("TC_API_016 - Delete employee")
    public void verifyDeleteEmployee() {
        Assert.assertNotNull(createdEmpNumber, "An employee must have been created before deletion can be tested.");

        Map<String, Object> deletePayload = Map.of("ids", List.of(createdEmpNumber));

        authSpec
                .body(deletePayload)
                .when()
                .delete(EMPLOYEES_ENDPOINT)
                .then()
                .statusCode(200);

        // Verify the employee is actually gone
        authSpec
                .when()
                .get(EMPLOYEES_ENDPOINT + "/" + createdEmpNumber)
                .then()
                .statusCode(404);

        createdEmpNumber = null; // mark as cleaned up
    }

    @Test(description = "TC_API_017 - Verify DELETE with a non-existent employee id does not crash the server (4xx, not 5xx)")
    @Story("TC_API_017 - Delete non-existent employee")
    public void verifyDeleteNonExistentEmployee() {
        Map<String, Object> deletePayload = Map.of("ids", List.of(999999999));

        authSpec
                .body(deletePayload)
                .when()
                .delete(EMPLOYEES_ENDPOINT)
                .then()
                .statusCode(lessThan(500));
    }

    // ───────────────────────────── Authentication / Authorization ────────────────

    @Test(description = "TC_API_018 - Verify accessing the employees endpoint without authentication is rejected")
    @Story("TC_API_018 - Unauthenticated access")
    public void verifyUnauthenticatedAccessRejected() {
        unauthenticatedSpec()
                .when()
                .get(EMPLOYEES_ENDPOINT)
                .then()
                .statusCode(anyOf(is(401), is(403)));
    }

    @Test(description = "TC_API_019 - Verify an invalid/expired session cookie is rejected")
    @Story("TC_API_019 - Invalid session")
    public void verifyInvalidSessionRejected() {
        given()
                .baseUri(BASE_URL)
                .cookie("orangehrm", "invalid-session-token-12345")
                .accept("application/json")
                .when()
                .get(EMPLOYEES_ENDPOINT)
                .then()
                .statusCode(anyOf(is(401), is(403)));
    }

    // ───────────────────────────── Response contract / performance ───────────────

    @Test(description = "TC_API_020 - Verify employee list endpoint responds within an acceptable time threshold")
    @Story("TC_API_020 - Response time")
    public void verifyResponseTimeWithinThreshold() {
        authSpec
                .when()
                .get(EMPLOYEES_ENDPOINT)
                .then()
                .statusCode(200)
                .time(lessThan(3000L)); // milliseconds
    }

    @Test(description = "TC_API_021 - Verify Content-Type header on employee list response is application/json")
    @Story("TC_API_021 - Content-Type contract")
    public void verifyContentTypeHeader() {
        authSpec
                .when()
                .get(EMPLOYEES_ENDPOINT)
                .then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"));
    }

    // ───────────────────────────── Cleanup ─────────────────────────────────────────

    @AfterClass(alwaysRun = true)
    public void cleanUp() {
        // Safety net: if any test failed mid-way and left a record behind, remove it.
        if (createdEmpNumber != null) {
            try {
                Map<String, Object> deletePayload = Map.of("ids", List.of(createdEmpNumber));
                authSpec.body(deletePayload).when().delete(EMPLOYEES_ENDPOINT);
            } catch (Exception ignored) {
                // Best-effort cleanup; do not fail the suite on teardown errors.
            }
        }
    }
}