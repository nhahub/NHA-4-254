/// /package org.example.api;
/// /
/// /import io.restassured.RestAssured;
/// /import io.restassured.response.Response;
/// /
/// /public class AuthApi {
/// /
/// /    public Response login(String username,
/// /                          String password,
/// /                          String token) {
/// /
/// /        return RestAssured.given()
/// /                .contentType("application/x-www-form-urlencoded")
/// /                .formParam("_token", token)
/// /                .formParam("username", username)
/// /                .formParam("password", password)
/// /                .redirects()
/// /                .follow(false)
/// /                .post("/web/index.php/auth/validate");
/// /    }
/// /}
//
//package api;
//
//import io.restassured.response.Response;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import static io.restassured.RestAssured.given;
//
//public class AuthApi {
//
//    private String csrfToken;
//    private String sessionCookie;
//
//    public void loadLoginPage() {
//        Response response = given().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
//        sessionCookie = response.getCookie("orangehrm");
//        String html = response.getBody().asString();
//        Pattern pattern = Pattern.compile(":token=\"&quot;(.*?)&quot;\"");
//        Matcher matcher = pattern.matcher(html);
//        if (matcher.find()) {
//            csrfToken = matcher.group(1);
//        }
//        System.out.println("Cookie = " + sessionCookie);
//        System.out.println("Token = " + csrfToken);
//    }
//
//    public Response login(String username, String password) {
//
//        return given()
//                .cookie("orangehrm", sessionCookie)
//                .contentType("application/x-www-form-urlencoded")
//                .formParam("_token", csrfToken)
//                .formParam("username", username)
//                .formParam("password", password)
//                .redirects()
//                .follow(false)
//                .post("https://opensource-demo.orangehrmlive.com/web/index.php/auth/validate");
//    }
//}


// package api;

// import io.restassured.response.Response;

// import java.util.regex.Matcher;
// import java.util.regex.Pattern;

// import static io.restassured.RestAssured.given;

// public class AuthApi {

//     private String csrfToken;
//     private String sessionCookie;

//     public void login() {

//         Response loginPage = given()
//                 .get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

//         sessionCookie = loginPage.getCookie("orangehrm");

//         String html = loginPage.getBody().asString();

//         Pattern pattern =
//                 Pattern.compile(":token=\"&quot;(.*?)&quot;\"");

//         Matcher matcher = pattern.matcher(html);

//         if (matcher.find()) {
//             csrfToken = matcher.group(1);
//         }

//         given()
//                 .cookie("orangehrm", sessionCookie)
//                 .contentType("application/x-www-form-urlencoded")
//                 .formParam("_token", csrfToken)
//                 .formParam("username", "Admin")
//                 .formParam("password", "admin123")
//                 .redirects()
//                 .follow(false)
//                 .post("https://opensource-demo.orangehrmlive.com/web/index.php/auth/validate");
//     }

//     public String getCookie() {
//         return sessionCookie;
//     }

//     public String getToken() {
//         return csrfToken;
//     }
// }




package api;

import io.restassured.response.Response;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class AuthApi {

    private String csrfToken;
    private String sessionCookie;

    public void login() {

        Response loginPage =
                given()
                        .get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        sessionCookie = loginPage.getCookie("orangehrm");

        String html = loginPage.asString();

        Pattern pattern =
                Pattern.compile(":token=\"&quot;(.*?)&quot;\"");

        Matcher matcher = pattern.matcher(html);

        if (matcher.find()) {
            csrfToken = matcher.group(1);
        }

        Response loginResponse =
                given()
                        .cookie("orangehrm", sessionCookie)
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("_token", csrfToken)
                        .formParam("username", "Admin")
                        .formParam("password", "admin123")
                        .redirects()
                        .follow(false)
                        .post("https://opensource-demo.orangehrmlive.com/web/index.php/auth/validate");

        String authenticatedCookie =
                loginResponse.getCookie("orangehrm");

        if (authenticatedCookie != null) {
            sessionCookie = authenticatedCookie;
        }
    }

    public String getCookie() {
        return sessionCookie;
    }

    public String getToken() {
        return csrfToken;
    }
}