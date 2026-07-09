package ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.Base;
import org.example.DriverManager;
import org.example.pages.ResetPasswordPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


@Feature("Add Employee")
@Story("TC_PIM_035 - Check that the Create Login Details functionality works end-to-end")
public class loginTest extends Base {

    //    WebDriver driver;
    private static WebDriver driver;
    loginPage loginPage;
    ResetPasswordPage resetPage;


    @BeforeTest
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = DriverManager.getInstance().getDriver();
        //            driver = DriverManager.getInstance().getDriver();
        loginPage = new loginPage(driver);
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    // Tests
    //TC_001 ,Verify successful login with valid Username and password

    @Test
    public void TC_001() {
        super.runTest("TC_LOGIN_001 ,Verify successful login with valid Username and password", () -> {
            loginPage.LogIn("Admin", "admin123")
                    .isLoogedIn("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
            boolean isLoggedIn = loginPage.isLoogedIn("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");


            return isLoggedIn;
        });
    }

    //TC_005 ,Verify system requests identity verification after incorrect password attempts using username
    @Test
    public void TC_005() {
        super.runTest("TC_LOGIN_005 ,Verify system requests identity verification after incorrect password \n" +
                "attempts using username", () -> {
            loginPage.LogIn("Admin", "wrong123").getAlertMessage().contains("Invalid");
            return loginPage.getAlertMessage().contains("Invalid");
        });
    }

    //TC_006 ,Verify account lockout after 5 failed login attempts
    @Test
    public void TC_006() {
        super.runTest("TC_LOGIN_006 ,Verify account lockout after 5 failed login attempts", () -> {
            for (int i = 0; i < 6; i++) {
                loginPage.LogIn("Admin", "wrong123");
            }
            String msg = loginPage.getAlertMessage();
            return msg.toLowerCase().contains("locked") || msg.toLowerCase().contains("disabled");
        });
    }

    //TC_007 ,Verify "Forgot Password" link redirects correctly
    @Test
    public void TC_007() {
        super.runTest("TC_LOGIN_007 ,Verify \"Forgot Password\" link redirects correctly", () -> {
            loginPage.open().clickForgotPassword();
            return resetPage.isOnPage();
        });
    }
}