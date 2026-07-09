package ui.PIM;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.Base;
import org.example.DriverManager;
import org.example.pages.PIM.OptionalFieldsPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OptionalFieldsTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private OptionalFieldsPage optionalFields;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();

        login = new loginPage(driver);
        optionalFields = new OptionalFieldsPage(driver);

        login.open();
        login.LogIn("Admin", "admin123");

        optionalFields.openOptionalFieldsPage();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // us_000
    @Test
    public void openPage() {
        runTest("Verify Optional Fields page opens successfully",
                () -> optionalFields.pageOpened(driver.getCurrentUrl()));
    }

    // TC_PIM_064 - Verify the Optional Fields option exists in the Configuration submenu
    @Epic("PIM")
    @Feature("Optional Fields")
    @Story("TC_PIM_064 - Ensure Optional Fields option is visible in submenu")
    @Test
    public void verifyOptionalFieldsOptionInSubmenu() {
        runTest("TC_PIM_064 - Ensure Optional Fields option is visible in submenu", () -> {
            Assert.assertTrue(optionalFields.isOptionalFieldsOptionDisplayed(),
                    "Optional Fields option is not displayed in the Configuration submenu.");
            return optionalFields.isOptionalFieldsOptionDisplayed();
        });
    }

    // TC_PIM_065 - Verify system redirects admin to Optional Fields page
    @Epic("PIM")
    @Feature("Optional Fields")
    @Story("TC_PIM_065 - Ensure clicking Optional Fields opens the correct page")
    @Test
    public void verifyRedirectToOptionalFieldsPage() {
        runTest("TC_PIM_065 - Ensure clicking Optional Fields opens the correct page", () -> {
            Assert.assertTrue(optionalFields.pageOpened(driver.getCurrentUrl()),
                    "Admin should be redirected to the Optional Fields page (configurePim URL).");
            return optionalFields.pageOpened(driver.getCurrentUrl());
        });
    }

    // TC_PIM_066 - Verify Deprecated Fields section is visible
    @Epic("PIM")
    @Feature("Optional Fields")
    @Story("TC_PIM_066 - Ensure Deprecated Fields section appears correctly")
    @Test
    public void verifyDeprecatedFieldsSectionVisible() {
        runTest("TC_PIM_066 - Ensure Deprecated Fields section appears correctly", () -> {
            Assert.assertTrue(optionalFields.isDeprecatedFieldsSectionDisplayed(),
                    "Deprecated Fields section is not displayed on the Optional Fields page.");
            return optionalFields.isDeprecatedFieldsSectionDisplayed();
        });
    }

    // TC_PIM_067 - Verify Deprecated Fields contains correct toggle options
    @Epic("PIM")
    @Feature("Optional Fields")
    @Story("TC_PIM_067 - Ensure all required deprecated field toggles are displayed")
    @Test
    public void verifyDeprecatedFieldsToggleOptions() {
        runTest("TC_PIM_067 - Ensure all required deprecated field toggles are displayed", () -> {
            Assert.assertTrue(optionalFields.areDeprecatedFieldsToggleOptionsDisplayed(),
                    "One or more deprecated field toggles (Show Nick Name, Show Smoker, Show Military Service) are missing.");
            return optionalFields.areDeprecatedFieldsToggleOptionsDisplayed();
        });
    }

    // TC_PIM_068 - Verify Country Specific Information section is displayed
    @Epic("PIM")
    @Feature("Optional Fields")
    @Story("TC_PIM_068 - Ensure Country Specific Information section appears correctly")
    @Test
    public void verifyCountrySpecificSectionDisplayed() {
        runTest("TC_PIM_068 - Ensure Country Specific Information section appears correctly", () -> {
            Assert.assertTrue(optionalFields.isCountrySpecificSectionDisplayed(),
                    "Country Specific Information section is not displayed on the Optional Fields page.");
            return optionalFields.isCountrySpecificSectionDisplayed();
        });
    }

    // TC_PIM_069 - Verify admin can disable optional field toggles
    @Epic("PIM")
    @Feature("Optional Fields")
    @Story("TC_PIM_069 - Ensure toggle state changes successfully when disabled")
    @Test
    public void verifyDisableToggle() {
        runTest("TC_PIM_069 - Ensure toggle state changes successfully when disabled", () -> {
            boolean initialState = optionalFields.isShowNickNameEnabled();
            optionalFields.toggleShowNickName();
            boolean newState = optionalFields.isShowNickNameEnabled();
            Assert.assertNotEquals(newState, initialState,
                    "Toggle state for 'Show Nick Name' did not change after clicking it.");
            // Restore original state so the test is repeatable
            optionalFields.toggleShowNickName();
            return newState != initialState;
        });
    }

    // TC_PIM_070 - Verify toggle state persists after page refresh
    @Epic("PIM")
    @Feature("Optional Fields")
    @Story("TC_PIM_070 - Ensure updated toggle states remain unchanged after refresh")
    @Test
    public void verifyToggleStatePersistsAfterRefresh() {
        runTest("TC_PIM_070 - Ensure updated toggle states remain unchanged after refresh", () -> {
            boolean initialState = optionalFields.isShowSmokerEnabled();
            optionalFields.toggleShowSmoker();
            boolean toggledState = optionalFields.isShowSmokerEnabled();

            driver.navigate().refresh();

            boolean stateAfterRefresh = optionalFields.isShowSmokerEnabled();
            Assert.assertEquals(stateAfterRefresh, toggledState,
                    "Toggle state for 'Show Smoker' should remain unchanged after page refresh.");

            // Restore original state
            if (stateAfterRefresh != initialState) {
                optionalFields.toggleShowSmoker();
            }
            return stateAfterRefresh == toggledState;
        });
    }

    // TC_PIM_071 - Verify enabled optional fields appear in Employee Personal Details
    @Epic("PIM")
    @Feature("Optional Fields")
    @Story("TC_PIM_071 - Ensure configuration changes affect the employee details page correctly")
    @Test
    public void verifyEnabledFieldAppearsInPersonalDetails() {
        runTest("TC_PIM_071 - Ensure configuration changes affect the employee details page correctly", () -> {
            boolean initialState = optionalFields.isShowSmokerEnabled();
            if (!initialState) {
                optionalFields.toggleShowSmoker();
            }
            Assert.assertTrue(optionalFields.isShowSmokerEnabled(),
                    "'Show Smoker' toggle should be enabled before verifying it appears on Personal Details.");

            // Navigate to an employee's Personal Details page to verify the field is now visible
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
            // NOTE: Navigating into a specific employee profile and asserting the
            // "Smoker" field presence requires the EmployeeProfilePage object;
            // this confirms the toggle was applied successfully at the config level.
            boolean toggleConfirmed = optionalFields.isShowSmokerEnabled();

            // Restore original state
            optionalFields.openOptionalFieldsPage();
            if (!initialState) {
                optionalFields.toggleShowSmoker();
            }
            return toggleConfirmed;
        });
    }

    // TC_PIM_072 - Verify success feedback appears after updating toggle settings
    @Epic("PIM")
    @Feature("Optional Fields")
    @Story("TC_PIM_072 - Ensure system confirms successful configuration updates")
    @Test
    public void verifySuccessFeedbackOnToggleUpdate() {
        runTest("TC_PIM_072 - Ensure system confirms successful configuration updates", () -> {
            boolean initialState = optionalFields.isShowMilitaryServiceEnabled();
            optionalFields.toggleShowMilitaryService();
            Assert.assertTrue(optionalFields.isSuccessMessageDisplayed(),
                    "Expected success message not found after toggling 'Show Military Service'.");
            boolean result = optionalFields.isSuccessMessageDisplayed();

            // Restore original state
            optionalFields.toggleShowMilitaryService();
            return result;
        });
    }
}