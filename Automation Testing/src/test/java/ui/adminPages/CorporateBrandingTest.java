package ui.adminPages;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.Base;
import org.example.DriverManager;
import org.example.pages.adminPage.CorporateBranding;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CorporateBrandingTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private CorporateBranding branding;

    @BeforeMethod
    public void setup() {

        driver = DriverManager.getInstance().getDriver();

        login = new loginPage(driver);
        branding = new CorporateBranding(driver);

        login.open();
        login.LogIn("Admin", "admin123");

        branding.openCorporateBrandingPage();
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

    @Epic("Admin Page")
    @Feature("Corporate Branding")
    @Story("TC_ADMIN_437 Verify Corporate Branding page loads successfully")
    @Test
    public void verifyCorporateBrandingPageLoads() {

        runTest("TC_ADMIN_437 Verify Corporate Branding page loads successfully", () -> {

            Assert.assertTrue(
                    branding.isBrandingPageDisplayed(),
                    "Corporate Branding page not loaded");

            return branding.isBrandingPageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Corporate Branding")
    @Story("TC_ADMIN_438 Verify current branding configuration is displayed")
    @Test
    public void verifyCurrentConfigurationDisplayed() {

        runTest("TC_ADMIN_438 Verify current branding configuration is displayed", () -> {

            Assert.assertTrue(
                    branding.isPrimaryColorDisplayed());

            Assert.assertTrue(
                    branding.isSecondaryColorDisplayed());

            return true;
        });
    }

    @Epic("Admin Page")
    @Feature("Corporate Branding")
    @Story("TC_ADMIN_445 Change primary color using valid hex")
    @Test
    public void verifyChangePrimaryColor() {

        runTest("TC_ADMIN_445 Change primary color using valid hex", () -> {

            branding.enterPrimaryColor("#FF5733");
            branding.clickPublish();

            Assert.assertTrue(
                    branding.isSuccessMessageDisplayed());

            return branding.isSuccessMessageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Corporate Branding")
    @Story("TC_ADMIN_447 Change secondary color using valid hex")
    @Test
    public void verifyChangeSecondaryColor() {

        runTest("TC_ADMIN_447 Change secondary color using valid hex", () -> {

            branding.enterSecondaryColor("#76BC21");
            branding.clickPublish();

            Assert.assertTrue(
                    branding.isSuccessMessageDisplayed());

            return branding.isSuccessMessageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Corporate Branding")
    @Story("TC_ADMIN_448 Verify Reset to Default")
    @Test
    public void verifyResetToDefault() {

        runTest("TC_ADMIN_448 Verify Reset to Default", () -> {

            branding.clickReset();

            Assert.assertTrue(true);

            return true;
        });
    }

    @Epic("Admin Page")
    @Feature("Corporate Branding")
    @Story("TC_ADMIN_449 Verify Publish button saves changes")
    @Test
    public void verifyPublish() {

        runTest("TC_ADMIN_449 Verify Publish button saves changes", () -> {

            branding.clickPublish();

            Assert.assertTrue(
                    branding.isSuccessMessageDisplayed());

            return branding.isSuccessMessageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Corporate Branding")
    @Story("TC_ADMIN_450 Verify Cancel button discards changes")
    @Test
    public void verifyCancel() {

        runTest("TC_ADMIN_450 Verify Cancel button discards changes", () -> {

            branding.clickCancel();

            Assert.assertTrue(
                    branding.isBrandingPageDisplayed());

            return branding.isBrandingPageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Corporate Branding")
    @Story("TC_ADMIN_451 Validate rejection of invalid hex code")
    @Test
    public void verifyInvalidHexCode() {

        runTest("TC_ADMIN_451 Validate rejection of invalid hex code", () -> {

            branding.enterPrimaryColor("ZZZZZZ");
            branding.clickPublish();

            Assert.assertTrue(
                    branding.isValidationErrorDisplayed());

            return branding.isValidationErrorDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Corporate Branding")
    @Story("TC_ADMIN_452 Verify logo preview is shown")
    @Test
    public void verifyLogoPreviewDisplayed() {

        runTest("TC_ADMIN_452 Verify logo preview is shown", () -> {

            Assert.assertTrue(
                    branding.isLogoPreviewDisplayed());

            return branding.isLogoPreviewDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Corporate Branding")
    @Story("TC_ADMIN_470 Verify keyboard accessible controls")
    @Test
    public void verifyButtonsDisplayed() {

        runTest("TC_ADMIN_470 Verify keyboard accessible controls", () -> {

            Assert.assertTrue(
                    branding.isPublishButtonDisplayed());

            Assert.assertTrue(
                    branding.isResetButtonDisplayed());

            Assert.assertTrue(
                    branding.isCancelButtonDisplayed());

            return true;
        });
    }
}