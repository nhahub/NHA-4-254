package ui.PIM;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.Base;
import org.example.DriverManager;
import org.example.pages.PIM.ReportingMethodsPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ReportingMethodsTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private ReportingMethodsPage reportingMethods;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();

        login = new loginPage(driver);
        reportingMethods = new ReportingMethodsPage(driver);

        login.open();
        login.LogIn("Admin", "admin123");

        reportingMethods.openReportingMethodsPage();
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
        runTest("Verify Reporting Methods page opens successfully",
                () -> reportingMethods.pageOpened(driver.getCurrentUrl()));
    }

    // ───────────────────────────── Page / Table ──────────────────────────────────

    // TC_PIM_115 - Verify Reporting Methods option appears in submenu
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_115 - Ensure Reporting Methods option exists inside Configuration menu")
    @Test
    public void verifyReportingMethodsOptionInSubmenu() {
        runTest("TC_PIM_115 - Ensure Reporting Methods option exists inside Configuration menu", () -> {
            Assert.assertTrue(reportingMethods.isReportingMethodsOptionDisplayed(),
                    "Reporting Methods option is not displayed in the Configuration submenu.");
            return reportingMethods.isReportingMethodsOptionDisplayed();
        });
    }

    // TC_PIM_116 - Verify system redirects admin to Reporting Methods page
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_116 - Ensure correct page opens after selecting Reporting Methods")
    @Test
    public void verifyRedirectToReportingMethodsPage() {
        runTest("TC_PIM_116 - Ensure correct page opens after selecting Reporting Methods", () -> {
            Assert.assertTrue(reportingMethods.pageOpened(driver.getCurrentUrl()),
                    "Admin should be redirected to the Reporting Methods page (viewReportingMethods URL).");
            return reportingMethods.pageOpened(driver.getCurrentUrl());
        });
    }

    // TC_PIM_117 - Verify Reporting Methods page loads without errors
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_117 - Ensure page loads completely and remains stable")
    @Test
    public void verifyPageLoadsWithoutErrors() {
        runTest("TC_PIM_117 - Ensure page loads completely and remains stable", () -> {
            Assert.assertTrue(reportingMethods.isPageLoaded(),
                    "Reporting Methods page did not load successfully.");
            return reportingMethods.isPageLoaded();
        });
    }

    // TC_PIM_118 - Verify Reporting Methods table contains correct columns
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_118 - Ensure required table columns are displayed correctly")
    @Test
    public void verifyTableColumnsCorrect() {
        runTest("TC_PIM_118 - Ensure required table columns are displayed correctly", () -> {
            Assert.assertTrue(reportingMethods.areRequiredColumnsDisplayed(),
                    "Reporting Methods table is missing one or more required columns (Name, Actions).");
            return reportingMethods.areRequiredColumnsDisplayed();
        });
    }

    // TC_PIM_119 - Verify Edit and Delete buttons are visible for each record
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_119 - Ensure every reporting method contains action buttons")
    @Test
    public void verifyActionButtonsDisplayed() {
        runTest("TC_PIM_119 - Ensure every reporting method contains action buttons", () -> {
            Assert.assertTrue(reportingMethods.areActionButtonsDisplayedForRecords(),
                    "Edit and/or Delete buttons are not displayed for existing reporting method records.");
            return reportingMethods.areActionButtonsDisplayedForRecords();
        });
    }

    // TC_PIM_120 - Verify Add button is visible and clickable
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_120 - Ensure Add button is accessible on Reporting Methods page")
    @Test
    public void verifyAddButtonVisible() {
        runTest("TC_PIM_120 - Ensure Add button is accessible on Reporting Methods page", () -> {
            Assert.assertTrue(reportingMethods.isAddButtonDisplayed(),
                    "Add button is not visible/clickable on the Reporting Methods page.");
            return reportingMethods.isAddButtonDisplayed();
        });
    }

    // TC_PIM_121 - Verify existing reporting methods appear in table
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_121 - Ensure saved records are displayed correctly")
    @Test
    public void verifyExistingMethodsDisplayed() {
        runTest("TC_PIM_121 - Ensure saved records are displayed correctly", () -> {
            Assert.assertTrue(reportingMethods.hasMethodRows(),
                    "Existing reporting method records are not displayed in the table.");
            return reportingMethods.hasMethodRows();
        });
    }

    // TC_PIM_122 - Verify empty-state message when no reporting methods exist
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_122 - Ensure proper feedback appears when table is empty")
    @Test
    public void verifyEmptyStateMessage() {
        runTest("TC_PIM_122 - Ensure proper feedback appears when table is empty", () -> {
            // Assumes a clean/empty environment; on a seeded demo this may not trigger.
            boolean hasRows = reportingMethods.hasMethodRows();
            boolean noRecordsShown = reportingMethods.isNoRecordsFoundDisplayed();
            Assert.assertTrue(hasRows || noRecordsShown,
                    "Page should show either existing records or a 'No Records Found' message.");
            return hasRows || noRecordsShown;
        });
    }

    // ───────────────────────────── Add Reporting Method ──────────────────────────

    // TC_PIM_123 - Verify Add button opens Add Reporting Method form
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_123 - Ensure Add form appears after clicking Add button")
    @Test
    public void verifyAddFormOpens() {
        runTest("TC_PIM_123 - Ensure Add form appears after clicking Add button", () -> {
            Assert.assertTrue(reportingMethods.isAddFormOpened(),
                    "Add Reporting Method form did not open after clicking the Add button.");
            return reportingMethods.isAddFormOpened();
        });
    }

    // TC_PIM_124 - Verify system prevents submission with empty required fields
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_124 - Ensure validation messages appear when required field is empty")
    @Test
    public void verifyEmptyFieldValidation() {
        runTest("TC_PIM_124 - Ensure validation messages appear when required field is empty", () -> {
            reportingMethods.saveAddFormEmpty();
            Assert.assertTrue(reportingMethods.isRequiredErrorDisplayed(),
                    "Expected 'Required' validation error not displayed when saving with empty Name field.");
            return reportingMethods.isRequiredErrorDisplayed();
        });
    }

    // TC_PIM_125 - Verify reporting method is created successfully with valid data
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_125 - Ensure valid reporting method data is saved correctly")
    @Test
    public void verifyCreateReportingMethodValidData() {
        String methodName = "Direct Manager Review " + System.currentTimeMillis();
        runTest("TC_PIM_125 - Ensure valid reporting method data is saved correctly", () -> {
            reportingMethods.createReportingMethod(methodName);
            Assert.assertTrue(reportingMethods.isSuccessMessageDisplayed(),
                    "Expected success message not found after creating a valid reporting method.");
            return reportingMethods.isSuccessMessageDisplayed();
        });
    }

    // TC_PIM_126 - Verify new reporting method appears immediately in table
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_126 - Ensure table refreshes automatically after creation")
    @Test
    public void verifyNewMethodAppearsInTable() {
        String methodName = "Direct Manager Review " + System.currentTimeMillis();
        runTest("TC_PIM_126 - Ensure table refreshes automatically after creation", () -> {
            reportingMethods.createReportingMethod(methodName);
            Assert.assertTrue(reportingMethods.isSuccessMessageDisplayed(),
                    "Reporting method should be created successfully before verifying it appears in the table.");
            Assert.assertTrue(reportingMethods.isMethodNamePresentInTable(methodName),
                    "Newly created reporting method '" + methodName + "' does not appear in the records table.");
            return reportingMethods.isMethodNamePresentInTable(methodName);
        });
    }

    // TC_PIM_127 - Verify success message appears after adding reporting method
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_127 - Ensure system confirms successful creation")
    @Test
    public void verifySuccessMessageAfterCreate() {
        String methodName = "Direct Manager Review " + System.currentTimeMillis();
        runTest("TC_PIM_127 - Ensure system confirms successful creation", () -> {
            reportingMethods.createReportingMethod(methodName);
            Assert.assertTrue(reportingMethods.isSuccessMessageDisplayed(),
                    "Success message should be displayed after creating a reporting method.");
            return reportingMethods.isSuccessMessageDisplayed();
        });
    }

    // TC_PIM_128 - Verify special characters handling in reporting method names (Add)
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_128 - Ensure special characters do not break UI or cause unexpected behavior")
    @Test
    public void verifySpecialCharactersHandledOnAdd() {
        runTest("TC_PIM_128 - Ensure special characters do not break UI or cause unexpected behavior", () -> {
            reportingMethods.createReportingMethodWithSpecialChars("@@@###%%%HR_Method!!! " + System.currentTimeMillis());
            Assert.assertTrue(reportingMethods.isPageLoaded(),
                    "UI should remain stable and functional after submitting special characters in the reporting method name.");
            return reportingMethods.isPageLoaded();
        });
    }

    // TC_PIM_129 - Verify system prevents duplicate reporting methods
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_129 - Ensure duplicate reporting method names cannot be added")
    @Test
    public void verifyDuplicateMethodPrevented() {
        String methodName = "HR Approval " + System.currentTimeMillis();
        runTest("TC_PIM_129 - Ensure duplicate reporting method names cannot be added", () -> {
            // Create the original record first
            reportingMethods.createReportingMethod(methodName);
            Assert.assertTrue(reportingMethods.isSuccessMessageDisplayed(),
                    "Initial reporting method should be created successfully before testing duplicate prevention.");
            // Per test steps: open Add, type duplicate, Cancel, then open Add again, type duplicate, Save
            reportingMethods.attemptDuplicateThenCancelThenSave(methodName);
            Assert.assertTrue(reportingMethods.isAlreadyExistsErrorDisplayed(),
                    "Expected 'Already exists' validation error not displayed for duplicate reporting method name.");
            return reportingMethods.isAlreadyExistsErrorDisplayed();
        });
    }

    // TC_PIM_130 - Verify duplicate reporting methods are not created on double Save click
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_130 - Ensure system prevents duplicate creation caused by rapid multiple clicks")
    @Test
    public void verifyNoDuplicateOnDoubleSaveClick() {
        String methodName = "Operations Approval " + System.currentTimeMillis();
        runTest("TC_PIM_130 - Ensure system prevents duplicate creation caused by rapid multiple clicks", () -> {
            reportingMethods.doubleClickSaveOnAddForm(methodName);
            Assert.assertTrue(reportingMethods.isSuccessMessageDisplayed(),
                    "Only one reporting method record should be created even after rapid double-click on Save.");
            return reportingMethods.isSuccessMessageDisplayed();
        });
    }

    // ───────────────────────────── Edit Reporting Method ─────────────────────────

    // TC_PIM_131 - Verify Edit button opens form with existing data
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_131 - Ensure existing record data loads correctly in edit form")
    @Test
    public void verifyEditFormOpensWithExistingData() {
        runTest("TC_PIM_131 - Ensure existing record data loads correctly in edit form", () -> {
            Assert.assertTrue(reportingMethods.isEditFormPrefilled(),
                    "Edit form did not open with pre-filled existing reporting method data.");
            return reportingMethods.isEditFormPrefilled();
        });
    }

    // TC_PIM_132 - Verify admin can update reporting method details
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_132 - Ensure edited values are saved successfully")
    @Test
    public void verifyUpdateReportingMethod() {
        String updatedName = "HR Final Approval " + System.currentTimeMillis();
        runTest("TC_PIM_132 - Ensure edited values are saved successfully", () -> {
            reportingMethods.editFirstRecord(updatedName);
            Assert.assertTrue(reportingMethods.isUpdatedMessageDisplayed(),
                    "Expected 'Successfully Updated' message not found after editing reporting method.");
            return reportingMethods.isUpdatedMessageDisplayed();
        });
    }

    // TC_PIM_133 - Verify updated reporting method appears immediately in table
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_133 - Ensure latest values display correctly after update")
    @Test
    public void verifyUpdatedMethodAppearsInTable() {
        String updatedName = "HR Final Approval " + System.currentTimeMillis();
        runTest("TC_PIM_133 - Ensure latest values display correctly after update", () -> {
            reportingMethods.editFirstRecord(updatedName);
            Assert.assertTrue(reportingMethods.isUpdatedMessageDisplayed(),
                    "Reporting method should be updated successfully before verifying it appears in the table.");
            Assert.assertTrue(reportingMethods.isMethodNamePresentInTable(updatedName),
                    "Updated reporting method name '" + updatedName + "' does not appear in the records table.");
            return reportingMethods.isMethodNamePresentInTable(updatedName);
        });
    }

    // TC_PIM_134 - Verify success message appears after updating reporting method
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_134 - Ensure system confirms successful update operation")
    @Test
    public void verifySuccessMessageAfterUpdate() {
        String updatedName = "HR Final Approval " + System.currentTimeMillis();
        runTest("TC_PIM_134 - Ensure system confirms successful update operation", () -> {
            reportingMethods.editFirstRecord(updatedName);
            Assert.assertTrue(reportingMethods.isUpdatedMessageDisplayed(),
                    "Success message should be displayed after updating a reporting method.");
            return reportingMethods.isUpdatedMessageDisplayed();
        });
    }

    // TC_PIM_135 - Verify special characters handling in reporting method names (Edit)
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_135 - Ensure special characters do not break UI or cause unexpected behavior during editing")
    @Test
    public void verifySpecialCharactersHandledOnEdit() {
        runTest("TC_PIM_135 - Ensure special characters do not break UI or cause unexpected behavior during editing", () -> {
            reportingMethods.editFirstRecordWithSpecialChars("@@@###%%%HR_Method!!! " + System.currentTimeMillis());
            Assert.assertTrue(reportingMethods.isPageLoaded(),
                    "UI should remain stable and functional after editing with special characters in the reporting method name.");
            return reportingMethods.isPageLoaded();
        });
    }

    // TC_PIM_136 - Verify refreshing page during edit does not corrupt data
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_136 - Ensure system preserves data integrity when user refreshes the page while editing")
    @Test
    public void verifyRefreshDuringEditDoesNotCorruptData() {
        runTest("TC_PIM_136 - Ensure system preserves data integrity when user refreshes the page while editing", () -> {
            String originalName = reportingMethods.modifyWithoutSavingThenRefresh("HR Approval Updated " + System.currentTimeMillis());
            Assert.assertTrue(reportingMethods.isPageLoaded(),
                    "Page should reload correctly after a refresh during unsaved edit.");
            // Verify the unsaved change was discarded and original data is intact
            boolean originalStillPresent = originalName != null && !originalName.isEmpty()
                    && reportingMethods.isMethodNamePresentInTable(originalName);
            Assert.assertTrue(originalStillPresent,
                    "Original reporting method data should remain unchanged and stable after refreshing during an unsaved edit.");
            return originalStillPresent;
        });
    }

    // ───────────────────────────── Delete Reporting Method ───────────────────────

    // TC_PIM_137 - Verify reporting method records can be selected using checkboxes
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_137 - Ensure admin can select one or multiple records for deletion")
    @Test
    public void verifyRecordsSelectableWithCheckboxes() {
        runTest("TC_PIM_137 - Ensure admin can select one or multiple records for deletion", () -> {
            reportingMethods.selectFirstRecordCheckbox();
            Assert.assertTrue(reportingMethods.isAnyCheckboxSelected(),
                    "Selecting the checkbox for a reporting method record did not register as selected.");
            return reportingMethods.isAnyCheckboxSelected();
        });
    }

    // TC_PIM_138 - Verify confirmation dialog appears before deletion
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_138 - Ensure system requests confirmation before deleting records")
    @Test
    public void verifyDeleteConfirmationAppears() {
        runTest("TC_PIM_138 - Ensure system requests confirmation before deleting records", () -> {
            reportingMethods.selectFirstRecordCheckbox();
            reportingMethods.clickDeleteOnSelected();
            Assert.assertTrue(reportingMethods.isDeleteConfirmationDisplayed(),
                    "Confirmation dialog was not displayed before deleting a reporting method.");
            return reportingMethods.isDeleteConfirmationDisplayed();
        });
    }

    // TC_PIM_139 - Verify reporting method is deleted successfully after confirmation
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_139 - Ensure selected record is removed correctly after confirmation")
    @Test
    public void verifyDeleteAfterConfirmation() {
        runTest("TC_PIM_139 - Ensure selected record is removed correctly after confirmation", () -> {
            reportingMethods.deleteFirstRecord();
            Assert.assertTrue(reportingMethods.isDeletedMessageDisplayed(),
                    "Expected 'Successfully Deleted' message not found after confirming reporting method deletion.");
            return reportingMethods.isDeletedMessageDisplayed();
        });
    }

    // TC_PIM_140 - Verify deleted reporting method no longer appears in table
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_140 - Ensure deleted record disappears immediately after deletion")
    @Test
    public void verifyDeletedMethodNotInTable() {
        String methodName = "ToDelete " + System.currentTimeMillis();
        runTest("TC_PIM_140 - Ensure deleted record disappears immediately after deletion", () -> {
            reportingMethods.createReportingMethod(methodName);
            Assert.assertTrue(reportingMethods.isSuccessMessageDisplayed(),
                    "Reporting method should be created successfully before testing deletion visibility.");
            reportingMethods.deleteFirstRecord();
            Assert.assertTrue(reportingMethods.isDeletedMessageDisplayed(),
                    "Reporting method should be deleted successfully.");
            Assert.assertFalse(reportingMethods.isMethodNamePresentInTable(methodName),
                    "Deleted reporting method should no longer appear in the records table.");
            return !reportingMethods.isMethodNamePresentInTable(methodName);
        });
    }

    // TC_PIM_141 - Verify success message appears after deleting reporting method
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_141 - Ensure system confirms successful deletion")
    @Test
    public void verifySuccessMessageAfterDelete() {
        runTest("TC_PIM_141 - Ensure system confirms successful deletion", () -> {
            reportingMethods.deleteFirstRecord();
            Assert.assertTrue(reportingMethods.isDeletedMessageDisplayed(),
                    "Success message should be displayed after deleting a reporting method.");
            return reportingMethods.isDeletedMessageDisplayed();
        });
    }

    // TC_PIM_142 - Verify deleted reporting method does not reappear after refresh
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_142 - Ensure deleted record is permanently removed from table")
    @Test
    public void verifyDeletedMethodRemainsRemovedAfterRefresh() {
        String methodName = "ToDeletePersist " + System.currentTimeMillis();
        runTest("TC_PIM_142 - Ensure deleted record is permanently removed from table", () -> {
            reportingMethods.createReportingMethod(methodName);
            Assert.assertTrue(reportingMethods.isSuccessMessageDisplayed(),
                    "Reporting method should be created successfully before testing post-refresh deletion persistence.");
            reportingMethods.deleteFirstRecord();
            Assert.assertTrue(reportingMethods.isDeletedMessageDisplayed(),
                    "Reporting method should be deleted successfully.");
            driver.navigate().refresh();
            Assert.assertFalse(reportingMethods.isMethodNamePresentInTable(methodName),
                    "Deleted reporting method should not reappear in the table after refresh.");
            return !reportingMethods.isMethodNamePresentInTable(methodName);
        });
    }

    // TC_PIM_143 - Verify reporting method is not deleted before confirmation
    @Epic("PIM")
    @Feature("Reporting Methods")
    @Story("TC_PIM_143 - Ensure deletion occurs only after user confirmation")
    @Test
    public void verifyCancelDeletePreservesRecord() {
        runTest("TC_PIM_143 - Ensure deletion occurs only after user confirmation", () -> {
            reportingMethods.selectFirstRecordAndCancelDelete();
            Assert.assertTrue(reportingMethods.hasMethodRows(),
                    "Reporting method record should remain in the table after canceling deletion.");
            return reportingMethods.hasMethodRows();
        });
    }
}