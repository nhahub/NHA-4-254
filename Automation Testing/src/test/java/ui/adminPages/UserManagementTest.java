package ui.adminPages;

import org.example.DriverManager;
import org.example.pages.adminPage.UserManagementPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UserManagementTest {


    private static WebDriver driver;

    @BeforeTest
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
//        driver = new ChromeDriver(options);
        DriverManager.getInstance().getDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        System.out.println("end of setup");
    }

    @AfterTest
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }


    // Tests
    //TC_001
    @Test
    public void searchAdminUser() {
        UserManagementPage page = new UserManagementPage(driver);
        loginPage loginPage = new loginPage(driver);

        loginPage.LogIn("Admin", "admin123")
                .isLoogedIn("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
        page.searchByUsername("FMLName")
//                .selectUserRole("Admin")
//                .selectStatus("Enabled")
                .clickSearch();
        try {
            Assert.assertTrue(
                    page.isUserFound("FMLName"),
                    "User was not found in search results"
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    //TC_002
//    @Test
//    public void invalidLoginTest() {
//        // Implement login test steps here
//        super.showTC(driver, "TC_002 ");
//        loginPage loginPage = new loginPage(driver);
//        loginPage.validLogIn("Admin", "wrongpassword");
//                .isLoogedIn("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
//        ScreenshotUtil.capture(driver, "TC_002 ");
//    }
}
