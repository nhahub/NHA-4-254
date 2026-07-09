package api.base;

//import api.utils.SessionManager;
//import io.qameta.allure.restassured.AllureRestAssured;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.specification.RequestSpecification;
import org.example.utils.ConfigReader;
import org.example.utils.SessionManager;
import org.testng.annotations.BeforeClass;

/**
 * Base class for all API test classes.
 * <p>
 * Logs in once before the test class runs (browser-style session login) and
 * exposes a pre-authenticated RequestSpecification (`authSpec`) for tests to
 * use. This avoids re-authenticating before every single test method, which
 * would be slow and would also defeat dedicated "auth/session" test cases
 * that intentionally test login behavior in isolation.
 */
public class BaseApiTest {

    protected static final String BASE_URL = ConfigReader.baseUrl();
    protected CookieFilter sessionCookieFilter;
    protected RequestSpecification authSpec;

    @BeforeClass(alwaysRun = true)
    public void setUpSession() {
        sessionCookieFilter = SessionManager.login(
                BASE_URL,
                ConfigReader.adminUsername(),
                ConfigReader.adminPassword()
        );

        authSpec = RestAssured.given()
                .baseUri(BASE_URL)
                .filter(sessionCookieFilter)
                .filter(new AllureRestAssured())
                .accept("application/json")
                .contentType("application/json");
    }

    /**
     * Returns a fresh, unauthenticated request spec — used by tests that
     * specifically verify unauthorized/unauthenticated access is rejected.
     */
    protected RequestSpecification unauthenticatedSpec() {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .filter(new AllureRestAssured())
                .accept("application/json");
    }
}