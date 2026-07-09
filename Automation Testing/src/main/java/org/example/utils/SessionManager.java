package org.example.utils;

import io.restassured.filter.cookie.CookieFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

/**
 * OrangeHRM's internal /api/v2/* endpoints are NOT a standalone token-based API.
 * They are the same endpoints the Vue.js single-page app calls from the browser,
 * secured by:
 * 1. A PHP session cookie (set after a successful form login)
 * 2. A CSRF token, embedded in the login page's HTML and required on the
 * login POST body itself (separate from the session cookie)
 * <p>
 * This class logs in exactly like a browser would (GET login page -> scrape
 * CSRF token -> POST credentials + token) and returns a CookieFilter that
 * carries the resulting authenticated session cookie into every subsequent
 * REST Assured request.
 */
public class SessionManager {

    private static final String LOGIN_PAGE_PATH = "/web/index.php/auth/login";
    private static final String LOGIN_ACTION_PATH = "/web/index.php/auth/login";

    // Matches: <input ... name="_token" value="THE_TOKEN" ...>
    private static final Pattern CSRF_TOKEN_PATTERN =
            Pattern.compile("name=\"_token\"\\s+value=\"([^\"]+)\"");

    /**
     * Performs a full browser-style login and returns a CookieFilter pre-loaded
     * with the authenticated session. Attach this filter to any RequestSpecification
     * to make authenticated calls to /api/v2/* endpoints.
     */
    public static CookieFilter login(String baseUrl, String username, String password) {
        CookieFilter cookieFilter = new CookieFilter();

        // Step 1: GET the login page to establish an initial session cookie
        // and scrape the CSRF token embedded in the HTML form.
        Response loginPageResponse = given()
                .baseUri(baseUrl)
                .filter(cookieFilter)
                .when()
                .get(LOGIN_PAGE_PATH)
                .then()
                .extract()
                .response();

        String csrfToken = extractCsrfToken(loginPageResponse.getBody().asString());

        // Step 2: POST credentials + CSRF token as a standard form submission.
        // The session cookie set in step 1 is reused automatically via cookieFilter.
        given()
                .baseUri(baseUrl)
                .filter(cookieFilter)
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", username)
                .formParam("password", password)
                .formParam("_token", csrfToken)
                .when()
                .post(LOGIN_ACTION_PATH)
                .then()
                .statusCode(anyOfRedirectOrOk());

        return cookieFilter;
    }

    private static String extractCsrfToken(String html) {
        Matcher matcher = CSRF_TOKEN_PATTERN.matcher(html);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new RuntimeException("Could not find CSRF (_token) field on login page. " +
                "OrangeHRM's login form structure may have changed.");
    }

    // OrangeHRM responds with a 302 redirect to /dashboard on successful login.
    // REST Assured by default follows redirects, so a 200 on the final page is
    // also acceptable depending on redirect-following configuration.
    private static org.hamcrest.Matcher<Integer> anyOfRedirectOrOk() {
        return org.hamcrest.Matchers.anyOf(
                org.hamcrest.Matchers.is(200),
                org.hamcrest.Matchers.is(302)
        );
    }

    /**
     * Convenience builder: returns a ready-to-use authenticated RequestSpecification
     * with base URI and session cookie already attached.
     */
    public static RequestSpecification authenticatedSpec(String baseUrl, String username, String password) {
        CookieFilter cookieFilter = login(baseUrl, username, password);
        return given()
                .baseUri(baseUrl)
                .filter(cookieFilter)
                .accept("application/json");
    }
}