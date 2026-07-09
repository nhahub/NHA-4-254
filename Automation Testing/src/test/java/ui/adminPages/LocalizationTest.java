package ui.adminPages;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.Base;
import org.example.DriverManager;
import org.example.pages.adminPage.LocalizationPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LocalizationTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private LocalizationPage localization;

    @BeforeMethod
    public void setup() {

        driver = DriverManager.getInstance().getDriver();

        login = new loginPage(driver);
        localization = new LocalizationPage(driver);

        login.open();
        login.LogIn("Admin", "admin123");

        localization.openLocalizationPage();
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

    @Epic("Admin Page")
    @Feature("Localization")
    @Story("TC_ADMIN_626 Verify Localization page opens successfully")
    @Test
    public void verifyLocalizationPageOpens() {

        runTest("Verify Localization page opens successfully", () -> {

            Assert.assertTrue(
                    localization.pageOpened(driver.getCurrentUrl()),
                    "Localization page did not open");

            return localization.pageOpened(driver.getCurrentUrl());
        });
    }

    @Epic("Admin Page")
    @Feature("Localization")
    @Story("TC_ADMIN_LOCAL_002 Verify Language dropdown displayed")
    @Test
    public void verifyLanguageDropdownDisplayed() {

        runTest("Verify Language dropdown displayed", () -> {

            Assert.assertTrue(
                    localization.isLanguageDropdownDisplayed(),
                    "Language dropdown not displayed");

            return localization.isLanguageDropdownDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Localization")
    @Story("TC_ADMIN_LOCAL_003 Verify Date Format dropdown displayed")
    @Test
    public void verifyDateFormatDropdownDisplayed() {

        runTest("Verify Date Format dropdown displayed", () -> {

            Assert.assertTrue(
                    localization.isDateFormatDropdownDisplayed(),
                    "Date Format dropdown not displayed");

            return localization.isDateFormatDropdownDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Localization")
    @Story("TC_ADMIN_LOCAL_004 Verify Save button functionality")
    @Test
    public void verifySaveButton() {

        runTest("Verify Save button functionality", () -> {

            localization.clickSave();

            Assert.assertTrue(
                    localization.isSuccessMessageDisplayed(),
                    "Success message not displayed");

            return localization.isSuccessMessageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Localization")
    @Story("TC_ADMIN_LOCAL_005 Verify Success message after save")
    @Test
    public void verifySuccessMessageAfterSave() {

        runTest("Verify Success message after save", () -> {

            localization.clickSave();

            Assert.assertTrue(
                    localization.getSuccessMessage().contains("Successfully"),
                    "Wrong success message");

            return localization.getSuccessMessage()
                    .contains("Successfully");
        });
    }

    @Epic("Admin Page")
    @Feature("Localization")
    @Story("TC_ADMIN_LOCAL_006 Verify Reset button functionality")
    @Test
    public void verifyResetButton() {

        runTest("Verify Reset button functionality", () -> {

            localization.clickReset();

            Assert.assertTrue(
                    localization.isLocalizationPageDisplayed(),
                    "Localization page not displayed after reset");

            return localization.isLocalizationPageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Localization")
    @Story("TC_ADMIN_LOCAL_007 Verify Localization page remains after refresh")
    @Test
    public void verifyPageAfterRefresh() {

        runTest("Verify Localization page remains after refresh", () -> {

            driver.navigate().refresh();

            Assert.assertTrue(
                    localization.isLocalizationPageDisplayed(),
                    "Localization page disappeared after refresh");

            return localization.isLocalizationPageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Localization")
    @Story("TC_ADMIN_LOCAL_008 Verify Language dropdown clickable")
    @Test
    public void verifyLanguageDropdownClickable() {

        runTest("Verify Language dropdown clickable", () -> {

            localization.openLanguageDropdown();

            Assert.assertTrue(
                    localization.isLanguageDropdownDisplayed(),
                    "Language dropdown not clickable");

            return localization.isLanguageDropdownDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Localization")
    @Story("TC_ADMIN_LOCAL_009 Verify Date Format dropdown clickable")
    @Test
    public void verifyDateFormatDropdownClickable() {

        runTest("Verify Date Format dropdown clickable", () -> {

            localization.openDateFormatDropdown();

            Assert.assertTrue(
                    localization.isDateFormatDropdownDisplayed(),
                    "Date format dropdown not clickable");

            return localization.isDateFormatDropdownDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Localization")
    @Story("TC_ADMIN_LOCAL_010 Verify Localization URL")
    @Test
    public void verifyLocalizationUrl() {

        runTest("Verify Localization URL", () -> {

            Assert.assertTrue(
                    driver.getCurrentUrl().contains("localization"),
                    "Wrong URL");

            return driver.getCurrentUrl().contains("localization");
        });
    }
}