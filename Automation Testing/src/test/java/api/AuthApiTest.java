package api;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.response.Response;
import org.example.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

/**
 * API-level tests for OrangeHRM's login flow itself
 * (POST /web/index.php/auth/login).
 * <p>
 * This is the same form-based login the browser performs; OrangeHRM does not
 * expose a JSON login endpoint on the public demo, so these tests operate at
 * the HTTP layer the way a security/API tester would: valid credentials,
 * invalid credentials, missing CSRF token, and session cookie behavior.
 */
@Epic("API Testing")
@Feature("Authentication API")
public class AuthApiTest {

    private static final String BASE_URL = ConfigReader.baseUrl();
    private static final String LOGIN_PAGE_PATH = "/web/index.php/auth/login";
    private static final Pattern CSRF_TOKEN_PATTERN =
            Pattern.compile("name=\"_token\"\\s+value=\"([^\"]+)\"");

    private String fetchCsrfToken(CookieFilter cookieFilter) {
        Response loginPage = given()
                .baseUri(BASE_URL)
                .filter(cookieFilter)
                .get(LOGIN_PAGE_PATH);

        Matcher matcher = CSRF_TOKEN_PATTERN.matcher(loginPage.getBody().asString());
        Assert.assertTrue(matcher.find(), "CSRF token field not found on login page.");
        return matcher.group(1);
    }

    @Test(description = "TC_API_022 - Verify GET login page returns 200 and includes a CSRF token field")
    @Story("TC_API_022 - Login page loads with CSRF token")
    public void verifyLoginPageReturnsCsrfToken() {
        CookieFilter cookieFilter = new CookieFilter();
        String token = fetchCsrfToken(cookieFilter);

        Assert.assertNotNull(token, "CSRF token should not be null.");
        Assert.assertFalse(token.isEmpty(), "CSRF token should not be empty.");
    }

    @Test(description = "TC_API_023 - Verify login with valid credentials and valid CSRF token redirects to dashboard")
    @Story("TC_API_023 - Successful login")
    public void verifyLoginWithValidCredentials() {
        CookieFilter cookieFilter = new CookieFilter();
        String csrfToken = fetchCsrfToken(cookieFilter);

        Response response = given()
                .baseUri(BASE_URL)
                .filter(cookieFilter)
                .redirects().follow(false)
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", ConfigReader.adminUsername())
                .formParam("password", ConfigReader.adminPassword())
                .formParam("_token", csrfToken)
                .when()
                .post(LOGIN_PAGE_PATH);

        // Successful login issues a 302 redirect toward /dashboard
        Assert.assertEquals(response.getStatusCode(), 302,
                "Successful login should respond with a 302 redirect.");
        String location = response.getHeader("Location");
        Assert.assertTrue(location != null && location.contains("dashboard"),
                "Redirect Location header should point to the dashboard, was: " + location);
    }

    @Test(description = "TC_API_024 - Verify login with invalid password is rejected and does not redirect to dashboard")
    @Story("TC_API_024 - Invalid credentials rejected")
    public void verifyLoginWithInvalidPassword() {
        CookieFilter cookieFilter = new CookieFilter();
        String csrfToken = fetchCsrfToken(cookieFilter);

        Response response = given()
                .baseUri(BASE_URL)
                .filter(cookieFilter)
                .redirects().follow(false)
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", ConfigReader.adminUsername())
                .formParam("password", "WrongPassword123!")
                .formParam("_token", csrfToken)
                .when()
                .post(LOGIN_PAGE_PATH);

        if (response.getStatusCode() == 302) {
            String location = response.getHeader("Location");
            Assert.assertFalse(location != null && location.contains("dashboard"),
                    "Login with an invalid password must NOT redirect to the dashboard.");
        } else {
            // Some versions return 200 and re-render the login form with an error
            Assert.assertEquals(response.getStatusCode(), 200,
                    "Invalid login should either redirect away from dashboard or return 200 with an error page.");
        }
    }

    @Test(description = "TC_API_025 - Verify login with non-existent username is rejected")
    @Story("TC_API_025 - Non-existent user rejected")
    public void verifyLoginWithNonExistentUsername() {
        CookieFilter cookieFilter = new CookieFilter();
        String csrfToken = fetchCsrfToken(cookieFilter);

        Response response = given()
                .baseUri(BASE_URL)
                .filter(cookieFilter)
                .redirects().follow(false)
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", "NonExistentUser_" + System.currentTimeMillis())
                .formParam("password", "SomePassword123!")
                .formParam("_token", csrfToken)
                .when()
                .post(LOGIN_PAGE_PATH);

        if (response.getStatusCode() == 302) {
            String location = response.getHeader("Location");
            Assert.assertFalse(location != null && location.contains("dashboard"),
                    "Login with a non-existent username must NOT redirect to the dashboard.");
        } else {
            Assert.assertEquals(response.getStatusCode(), 200);
        }
    }

    @Test(description = "TC_API_026 - Verify login submission without a CSRF token is rejected")
    @Story("TC_API_026 - Missing CSRF token rejected")
    public void verifyLoginWithoutCsrfTokenRejected() {
        CookieFilter cookieFilter = new CookieFilter();
        // Establish a session cookie but deliberately omit the _token field
        given()
                .baseUri(BASE_URL)
                .filter(cookieFilter)
                .get(LOGIN_PAGE_PATH);

        Response response = given()
                .baseUri(BASE_URL)
                .filter(cookieFilter)
                .redirects().follow(false)
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", ConfigReader.adminUsername())
                .formParam("password", ConfigReader.adminPassword())
                .when()
                .post(LOGIN_PAGE_PATH);

        Assert.assertNotEquals(response.getStatusCode(), 302,
                "Login without a CSRF token should be rejected, not redirected to dashboard.");
    }

    @Test(description = "TC_API_027 - Verify login submission with an empty username and password is rejected")
    @Story("TC_API_027 - Empty credentials rejected")
    public void verifyLoginWithEmptyCredentialsRejected() {
        CookieFilter cookieFilter = new CookieFilter();
        String csrfToken = fetchCsrfToken(cookieFilter);

        Response response = given()
                .baseUri(BASE_URL)
                .filter(cookieFilter)
                .redirects().follow(false)
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", "")
                .formParam("password", "")
                .formParam("_token", csrfToken)
                .when()
                .post(LOGIN_PAGE_PATH);

        Assert.assertNotEquals(response.getStatusCode(), 302,
                "Login with empty username/password should not succeed.");
    }

    @Test(description = "TC_API_028 - Verify SQL-injection-style input in the username field is safely rejected, not a 500 error")
    @Story("TC_API_028 - Injection safety")
    public void verifyLoginSqlInjectionAttemptHandledSafely() {
        CookieFilter cookieFilter = new CookieFilter();
        String csrfToken = fetchCsrfToken(cookieFilter);

        Response response = given()
                .baseUri(BASE_URL)
                .filter(cookieFilter)
                .redirects().follow(false)
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", "' OR '1'='1")
                .formParam("password", "' OR '1'='1")
                .formParam("_token", csrfToken)
                .when()
                .post(LOGIN_PAGE_PATH);

        Assert.assertTrue(response.getStatusCode() < 500,
                "Server should handle injection-style input gracefully (4xx/redirect), not crash with a 5xx error.");
        if (response.getStatusCode() == 302) {
            String location = response.getHeader("Location");
            Assert.assertFalse(location != null && location.contains("dashboard"),
                    "SQL-injection-style credentials must NOT succeed in logging in.");
        }
    }
}