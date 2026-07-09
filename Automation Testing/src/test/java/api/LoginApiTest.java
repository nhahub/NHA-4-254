//package api;
//
//import io.qameta.allure.Epic;
//import io.qameta.allure.Feature;
//import io.qameta.allure.Story;
//import io.restassured.response.Response;
//import org.example.api.AuthApi;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//public class LoginApiTest {
//
//    @Epic("API Tests")
//    @Feature("Login API")
//    @Story("Valid login")
//    @Test
//    public void verifyLogin() {
//        AuthApi auth = new AuthApi();
//        auth.login();
//        Response response = auth.login("Admin", "admin123");
//        System.out.println("Status Code = " + response.statusCode());
//        System.out.println("Location = " + response.getHeader("Location"));
//        Assert.assertEquals(response.statusCode(), 302);
//        Assert.assertTrue(response.getHeader("Location").contains("/dashboard/index"));
//    }
//
//
//    @Epic("API Tests")
//    @Feature("Login API")
//    @Story("inValid login")
//    @Test
//    public void verifyInvalidLogin() {
//        AuthApi auth = new AuthApi();
//        auth.loadLoginPage();
//        Response response = auth.login("Admin", "wrong123");
//        Assert.assertTrue(response.getHeader("Location").contains("/auth/login"));
//    }
//}