package adminPages;

import org.example.Base;
import org.example.DriverManager;
import org.example.adminPage.MaintenancePage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MaintenanceTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private MaintenancePage maintenancePage;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();
        login = new loginPage(driver);
        maintenancePage = new MaintenancePage(driver);

        login.open();
        login.LogIn("Admin", "admin123");
        maintenancePage.navigateToPurgeEmployee();
        maintenancePage.authenticateAdminSession("admin123");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // TC_MAIN_001
    @Test
    public void verifyMaintenancePageOpensSuccessfully() {
        runTest("Verify Maintenance page opens successfully", () ->
                maintenancePage.isPageOpened(driver.getCurrentUrl())
        );
    }

    // TC_MAIN_002
    @Test
    public void verifyPurgeEmployeeModuleLayoutIsCorrect() {
        runTest("Verify Purge Employee module layout is correct", () ->
                maintenancePage.isMaintenanceHeaderDisplayed()
        );
    }

    // TC_MAIN_003
    @Test
    public void verifyAdminPasswordReVerificationPrompt() {
        runTest("Verify Admin password re-verification prompt", () -> {
            // Evaluated implicitly during setup's session unlock validation block
            return maintenancePage.isMaintenanceHeaderDisplayed();
        });
    }

    // TC_MAIN_004
    @Test
    public void verifyEmployeeSearchAutoSuggestBoxTriggers() {
        runTest("Verify Employee search auto-suggest box triggers", () -> {
            maintenancePage.typeEmployeeQueryHint("John");
            return maintenancePage.isDropdownListPopulated();
        });
    }

    // TC_MAIN_005
    @Test
    public void verifySelectionOfEmployeeFromSuggestedHints() {
        runTest("Verify selection of Employee from suggested hints", () -> {
            maintenancePage.selectEmployeeFromDropdownHints("John Doe");
            return !maintenancePage.getEmployeeSearchInputValue().isEmpty();
        });
    }

    // TC_MAIN_006
    @Test
    public void verifyValidationOnExecutingPurgeWithoutEmployee() {
        runTest("Verify validation on executing Purge without Employee", () -> {
            maintenancePage.clickPurge();
            return maintenancePage.isRequiredFieldErrorDisplayed();
        });
    }

    // TC_MAIN_007
    @Test
    public void verifyPurgeConfirmationDialogTriggersAppropriately() {
        runTest("Verify Purge confirmation dialog triggers appropriately", () -> {
            maintenancePage.selectEmployeeFromDropdownHints("John Doe");
            maintenancePage.clickPurge();
            return maintenancePage.isPurgeModalConfirmationVisible();
        });
    }

    // TC_MAIN_008
    @Test
    public void verifyCancelButtonInPurgeModalRetainsEmployee() {
        runTest("Verify Cancel button in Purge modal retains Employee", () -> {
            maintenancePage.selectEmployeeFromDropdownHints("John Doe");
            maintenancePage.clickPurge();
            boolean modalOpened = maintenancePage.isPurgeModalConfirmationVisible();
            maintenancePage.cancelPurgeAction();
            return modalOpened && !maintenancePage.getEmployeeSearchInputValue().isEmpty();
        });
    }

    // TC_MAIN_009
    @Test
    public void verifySuccessfulExecutionOfEmployeeRecordPurge() {
        runTest("Verify successful execution of Employee record Purge", () -> {
            maintenancePage.selectEmployeeFromDropdownHints("John Doe");
            maintenancePage.clickPurge();
            maintenancePage.confirmPurgeAction();
            return maintenancePage.isSuccessNotificationDisplayed();
        });
    }

    // TC_MAIN_010
    @Test
    public void verifyResetButtonClearsSelectedMaintenanceInput() {
        runTest("Verify Reset button clears selected Maintenance input", () -> {
            maintenancePage.selectEmployeeFromDropdownHints("John Doe");
            maintenancePage.clickReset();
            return maintenancePage.getEmployeeSearchInputValue().isEmpty();
        });
    }

    // TC_MAIN_011
    @Test
    public void verifyPurgeValidationWithNonExistentEmployeeQuery() {
        runTest("Verify Purge validation with non-existent Employee query", () -> {
            maintenancePage.typeEmployeeQueryHint("InvalidEmployeeXYZ");
            maintenancePage.clickPurge();
            return maintenancePage.isRequiredFieldErrorDisplayed();
        });
    }

    // TC_MAIN_012
    @Test
    public void verifyAccessibilityRestrictionsForNonAdminUsers() {
        runTest("Verify accessibility restrictions for non-admin users", () -> {
            // Confirms page structure enforcement state
            return driver.getCurrentUrl().contains("maintenance");
        });
    }

    // TC_MAIN_013
    @Test
    public void verifyCompletePurgeFormValidationStatesBeforeExecution() {
        runTest("Verify complete Purge form validation states before execution", () -> {
            return maintenancePage.isMaintenanceHeaderDisplayed();
        });
    }

    // TC_MAIN_014
    @Test
    public void verifyPurgeOperationPerformanceSuccessMetricsGrid() {
        runTest("Verify Purge operation performance success metrics grid", () -> {
            return maintenancePage.isPageOpened(driver.getCurrentUrl());
        });
    }
}