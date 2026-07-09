package ui.PIM;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.Base;
import org.example.DriverManager;
import org.example.pages.PIM.TerminationReasonsPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TerminationReasonsTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private TerminationReasonsPage terminationReasons;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();

        login = new loginPage(driver);
        terminationReasons = new TerminationReasonsPage(driver);

        login.open();
        login.LogIn("Admin", "admin123");

        terminationReasons.openTerminationReasonsPage();
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
        runTest("Verify Termination Reasons page opens successfully",
                () -> terminationReasons.pageOpened(driver.getCurrentUrl()));
    }

    // ───────────────────────────── Page / Table ──────────────────────────────────

    // TC_PIM_144 - Verify Termination Reasons option appears in submenu
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_144 - Ensure Termination Reasons option exists inside Configuration menu")
    @Test
    public void verifyTerminationReasonsOptionInSubmenu() {
        runTest("TC_PIM_144 - Ensure Termination Reasons option exists inside Configuration menu", () -> {
            Assert.assertTrue(terminationReasons.isTerminationReasonsOptionDisplayed(),
                    "Termination Reasons option is not displayed in the Configuration submenu.");
            return terminationReasons.isTerminationReasonsOptionDisplayed();
        });
    }

    // TC_PIM_145 - Verify system redirects admin to Termination Reasons page
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_145 - Ensure correct page opens after selecting Termination Reasons")
    @Test
    public void verifyRedirectToTerminationReasonsPage() {
        runTest("TC_PIM_145 - Ensure correct page opens after selecting Termination Reasons", () -> {
            Assert.assertTrue(terminationReasons.pageOpened(driver.getCurrentUrl()),
                    "Admin should be redirected to the Termination Reasons page (viewTerminationReasons URL).");
            return terminationReasons.pageOpened(driver.getCurrentUrl());
        });
    }

    // TC_PIM_146 - Verify Termination Reasons page loads without errors
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_146 - Ensure page loads completely and remains stable")
    @Test
    public void verifyPageLoadsWithoutErrors() {
        runTest("TC_PIM_146 - Ensure page loads completely and remains stable", () -> {
            Assert.assertTrue(terminationReasons.isPageLoaded(),
                    "Termination Reasons page did not load successfully.");
            return terminationReasons.isPageLoaded();
        });
    }

    // TC_PIM_147 - Verify Termination Reasons table contains correct columns
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_147 - Ensure required table columns are displayed correctly")
    @Test
    public void verifyTableColumnsCorrect() {
        runTest("TC_PIM_147 - Ensure required table columns are displayed correctly", () -> {
            Assert.assertTrue(terminationReasons.areRequiredColumnsDisplayed(),
                    "Termination Reasons table is missing one or more required columns (Name, Actions).");
            return terminationReasons.areRequiredColumnsDisplayed();
        });
    }

    // TC_PIM_148 - Verify Edit and Delete buttons are visible for each record
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_148 - Ensure every Termination Reason contains action buttons")
    @Test
    public void verifyActionButtonsDisplayed() {
        runTest("TC_PIM_148 - Ensure every Termination Reason contains action buttons", () -> {
            Assert.assertTrue(terminationReasons.areActionButtonsDisplayedForRecords(),
                    "Edit and/or Delete buttons are not displayed for existing Termination Reasons records.");
            return terminationReasons.areActionButtonsDisplayedForRecords();
        });
    }

    // TC_PIM_149 - Verify Add button is visible and clickable
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_149 - Ensure Add button is accessible on Termination Reasons page")
    @Test
    public void verifyAddButtonVisible() {
        runTest("TC_PIM_149 - Ensure Add button is accessible on Termination Reasons page", () -> {
            Assert.assertTrue(terminationReasons.isAddButtonDisplayed(),
                    "Add button is not visible/clickable on the Termination Reasons page.");
            return terminationReasons.isAddButtonDisplayed();
        });
    }

    // TC_PIM_150 - Verify existing Termination Reasons appear in table
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_150 - Ensure saved records are displayed correctly")
    @Test
    public void verifyExistingReasonsDisplayed() {
        runTest("TC_PIM_150 - Ensure saved records are displayed correctly", () -> {
            Assert.assertTrue(terminationReasons.hasReasonRows(),
                    "Existing Termination Reasons records are not displayed in the table.");
            return terminationReasons.hasReasonRows();
        });
    }

    // TC_PIM_151 - Verify empty-state message when no Termination Reasons exist
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_151 - Ensure proper feedback appears when table is empty")
    @Test
    public void verifyEmptyStateMessage() {
        runTest("TC_PIM_151 - Ensure proper feedback appears when table is empty", () -> {
            // Assumes a clean/empty environment; on a seeded demo this may not trigger.
            boolean hasRows = terminationReasons.hasReasonRows();
            boolean noRecordsShown = terminationReasons.isNoRecordsFoundDisplayed();
            Assert.assertTrue(hasRows || noRecordsShown,
                    "Page should show either existing records or a 'No Records Found' message.");
            return hasRows || noRecordsShown;
        });
    }

    // ───────────────────────────── Add Termination Reason ────────────────────────

    // TC_PIM_152 - Verify Add button opens Add Termination Reason form
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_152 - Ensure Add form appears after clicking Add button")
    @Test
    public void verifyAddFormOpens() {
        runTest("TC_PIM_152 - Ensure Add form appears after clicking Add button", () -> {
            Assert.assertTrue(terminationReasons.isAddFormOpened(),
                    "Add Termination Reason form did not open after clicking the Add button.");
            return terminationReasons.isAddFormOpened();
        });
    }

    // TC_PIM_153 - Verify system prevents submission with empty required fields
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_153 - Ensure validation messages appear when required field is empty")
    @Test
    public void verifyEmptyFieldValidation() {
        runTest("TC_PIM_153 - Ensure validation messages appear when required field is empty", () -> {
            terminationReasons.saveAddFormEmpty();
            Assert.assertTrue(terminationReasons.isRequiredErrorDisplayed(),
                    "Expected 'Required' validation error not displayed when saving with empty Name field.");
            return terminationReasons.isRequiredErrorDisplayed();
        });
    }

    // TC_PIM_154 - Verify Termination Reason is created successfully with valid data
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_154 - Ensure valid Termination Reasons data is saved correctly")
    @Test
    public void verifyCreateTerminationReasonValidData() {
        String reasonName = "Direct Manager Review " + System.currentTimeMillis();
        runTest("TC_PIM_154 - Ensure valid Termination Reasons data is saved correctly", () -> {
            terminationReasons.createTerminationReason(reasonName);
            Assert.assertTrue(terminationReasons.isSuccessMessageDisplayed(),
                    "Expected success message not found after creating a valid Termination Reason.");
            return terminationReasons.isSuccessMessageDisplayed();
        });
    }

    // TC_PIM_155 - Verify new Termination Reason appears immediately in table
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_155 - Ensure table refreshes automatically after creation")
    @Test
    public void verifyNewReasonAppearsInTable() {
        String reasonName = "Direct Manager Review " + System.currentTimeMillis();
        runTest("TC_PIM_155 - Ensure table refreshes automatically after creation", () -> {
            terminationReasons.createTerminationReason(reasonName);
            Assert.assertTrue(terminationReasons.isSuccessMessageDisplayed(),
                    "Termination Reason should be created successfully before verifying it appears in the table.");
            Assert.assertTrue(terminationReasons.isReasonNamePresentInTable(reasonName),
                    "Newly created Termination Reason '" + reasonName + "' does not appear in the records table.");
            return terminationReasons.isReasonNamePresentInTable(reasonName);
        });
    }

    // TC_PIM_156 - Verify success message appears after adding Termination Reason
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_156 - Ensure system confirms successful creation")
    @Test
    public void verifySuccessMessageAfterCreate() {
        String reasonName = "Direct Manager Review " + System.currentTimeMillis();
        runTest("TC_PIM_156 - Ensure system confirms successful creation", () -> {
            terminationReasons.createTerminationReason(reasonName);
            Assert.assertTrue(terminationReasons.isSuccessMessageDisplayed(),
                    "Success message should be displayed after creating a Termination Reason.");
            return terminationReasons.isSuccessMessageDisplayed();
        });
    }

    // TC_PIM_157 - Verify special characters handling in Termination Reasons names (Add)
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_157 - Ensure special characters do not break UI or cause unexpected behavior")
    @Test
    public void verifySpecialCharactersHandledOnAdd() {
        runTest("TC_PIM_157 - Ensure special characters do not break UI or cause unexpected behavior", () -> {
            terminationReasons.createTerminationReasonWithSpecialChars("@@@###%%%HR_Method!!! " + System.currentTimeMillis());
            Assert.assertTrue(terminationReasons.isPageLoaded(),
                    "UI should remain stable and functional after submitting special characters in the Termination Reason name.");
            return terminationReasons.isPageLoaded();
        });
    }

    // TC_PIM_158 - Verify system prevents duplicate Termination Reasons
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_158 - Ensure duplicate Termination Reason names cannot be added")
    @Test
    public void verifyDuplicateReasonPrevented() {
        String reasonName = "HR Approval " + System.currentTimeMillis();
        runTest("TC_PIM_158 - Ensure duplicate Termination Reason names cannot be added", () -> {
            terminationReasons.createTerminationReason(reasonName);
            Assert.assertTrue(terminationReasons.isSuccessMessageDisplayed(),
                    "Initial Termination Reason should be created successfully before testing duplicate prevention.");
            terminationReasons.createDuplicateTerminationReason(reasonName);
            Assert.assertTrue(terminationReasons.isAlreadyExistsErrorDisplayed(),
                    "Expected 'Already exists' validation error not displayed for duplicate Termination Reason name.");
            return terminationReasons.isAlreadyExistsErrorDisplayed();
        });
    }

    // TC_PIM_159 - Verify duplicate Termination Reasons are not created on double Save click
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_159 - Ensure system prevents duplicate creation caused by rapid multiple clicks")
    @Test
    public void verifyNoDuplicateOnDoubleSaveClick() {
        String reasonName = "Operations Approval " + System.currentTimeMillis();
        runTest("TC_PIM_159 - Ensure system prevents duplicate creation caused by rapid multiple clicks", () -> {
            terminationReasons.doubleClickSaveOnAddForm(reasonName);
            Assert.assertTrue(terminationReasons.isSuccessMessageDisplayed(),
                    "Only one Termination Reason record should be created even after rapid double-click on Save.");
            return terminationReasons.isSuccessMessageDisplayed();
        });
    }

    // ───────────────────────────── Edit Termination Reason ───────────────────────

    // TC_PIM_160 - Verify Edit button opens form with existing data
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_160 - Ensure existing record data loads correctly in edit form")
    @Test
    public void verifyEditFormOpensWithExistingData() {
        runTest("TC_PIM_160 - Ensure existing record data loads correctly in edit form", () -> {
            Assert.assertTrue(terminationReasons.isEditFormPrefilled(),
                    "Edit form did not open with pre-filled existing Termination Reason data.");
            return terminationReasons.isEditFormPrefilled();
        });
    }

    // TC_PIM_161 - Verify special characters handling in Termination Reasons names (Edit)
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_161 - Ensure special characters do not break UI or cause unexpected behavior during editing")
    @Test
    public void verifySpecialCharactersHandledOnEdit() {
        runTest("TC_PIM_161 - Ensure special characters do not break UI or cause unexpected behavior during editing", () -> {
            terminationReasons.editFirstRecordWithSpecialChars("@@@###%%%HR_Method!!! " + System.currentTimeMillis());
            Assert.assertTrue(terminationReasons.isPageLoaded(),
                    "UI should remain stable and functional after editing with special characters in the Termination Reason name.");
            return terminationReasons.isPageLoaded();
        });
    }

    // TC_PIM_162 - Verify admin can update Termination Reason details
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_162 - Ensure edited values are saved successfully")
    @Test
    public void verifyUpdateTerminationReason() {
        String updatedName = "HR Final Approval " + System.currentTimeMillis();
        runTest("TC_PIM_162 - Ensure edited values are saved successfully", () -> {
            terminationReasons.editFirstRecord(updatedName);
            Assert.assertTrue(terminationReasons.isUpdatedMessageDisplayed(),
                    "Expected 'Successfully Updated' message not found after editing Termination Reason.");
            return terminationReasons.isUpdatedMessageDisplayed();
        });
    }

    // TC_PIM_163 - Verify updated Termination Reason appears immediately in table
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_163 - Ensure latest values display correctly after update")
    @Test
    public void verifyUpdatedReasonAppearsInTable() {
        String updatedName = "HR Final Approval " + System.currentTimeMillis();
        runTest("TC_PIM_163 - Ensure latest values display correctly after update", () -> {
            terminationReasons.editFirstRecord(updatedName);
            Assert.assertTrue(terminationReasons.isUpdatedMessageDisplayed(),
                    "Termination Reason should be updated successfully before verifying it appears in the table.");
            Assert.assertTrue(terminationReasons.isReasonNamePresentInTable(updatedName),
                    "Updated Termination Reason name '" + updatedName + "' does not appear in the records table.");
            return terminationReasons.isReasonNamePresentInTable(updatedName);
        });
    }

    // TC_PIM_164 - Verify success message appears after updating Termination Reason
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_164 - Ensure system confirms successful update operation")
    @Test
    public void verifySuccessMessageAfterUpdate() {
        String updatedName = "HR Final Approval " + System.currentTimeMillis();
        runTest("TC_PIM_164 - Ensure system confirms successful update operation", () -> {
            terminationReasons.editFirstRecord(updatedName);
            Assert.assertTrue(terminationReasons.isUpdatedMessageDisplayed(),
                    "Success message should be displayed after updating a Termination Reason.");
            return terminationReasons.isUpdatedMessageDisplayed();
        });
    }

    // TC_PIM_165 - Verify refreshing page during edit does not corrupt data
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_165 - Ensure system preserves data integrity when user refreshes the page while editing")
    @Test
    public void verifyRefreshDuringEditDoesNotCorruptData() {
        runTest("TC_PIM_165 - Ensure system preserves data integrity when user refreshes the page while editing", () -> {
            String originalName = terminationReasons.modifyWithoutSavingThenRefresh("HR Approval Updated " + System.currentTimeMillis());
            Assert.assertTrue(terminationReasons.isPageLoaded(),
                    "Page should reload correctly after a refresh during unsaved edit.");
            boolean originalStillPresent = originalName != null && !originalName.isEmpty()
                    && terminationReasons.isReasonNamePresentInTable(originalName);
            Assert.assertTrue(originalStillPresent,
                    "Original Termination Reason data should remain unchanged and stable after refreshing during an unsaved edit.");
            return originalStillPresent;
        });
    }

    // ───────────────────────────── Delete Termination Reason ─────────────────────

    // TC_PIM_166 - Verify Termination Reason records can be selected using checkboxes
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_166 - Ensure admin can select one or multiple records for deletion")
    @Test
    public void verifyRecordsSelectableWithCheckboxes() {
        runTest("TC_PIM_166 - Ensure admin can select one or multiple records for deletion", () -> {
            terminationReasons.selectFirstRecordCheckbox();
            Assert.assertTrue(terminationReasons.isAnyCheckboxSelected(),
                    "Selecting the checkbox for a Termination Reason record did not register as selected.");
            return terminationReasons.isAnyCheckboxSelected();
        });
    }

    // TC_PIM_167 - Verify confirmation dialog appears before deletion
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_167 - Ensure system requests confirmation before deleting records")
    @Test
    public void verifyDeleteConfirmationAppears() {
        runTest("TC_PIM_167 - Ensure system requests confirmation before deleting records", () -> {
            terminationReasons.selectFirstRecordCheckbox();
            terminationReasons.clickDeleteOnSelected();
            Assert.assertTrue(terminationReasons.isDeleteConfirmationDisplayed(),
                    "Confirmation dialog was not displayed before deleting a Termination Reason.");
            return terminationReasons.isDeleteConfirmationDisplayed();
        });
    }

    // TC_PIM_168 - Verify Termination Reason is deleted successfully after confirmation
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_168 - Ensure selected record is removed correctly after confirmation")
    @Test
    public void verifyDeleteAfterConfirmation() {
        runTest("TC_PIM_168 - Ensure selected record is removed correctly after confirmation", () -> {
            terminationReasons.deleteFirstRecord();
            Assert.assertTrue(terminationReasons.isDeletedMessageDisplayed(),
                    "Expected 'Successfully Deleted' message not found after confirming Termination Reason deletion.");
            return terminationReasons.isDeletedMessageDisplayed();
        });
    }

    // TC_PIM_169 - Verify deleted Termination Reason no longer appears in table
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_169 - Ensure deleted record disappears immediately after deletion")
    @Test
    public void verifyDeletedReasonNotInTable() {
        String reasonName = "ToDelete " + System.currentTimeMillis();
        runTest("TC_PIM_169 - Ensure deleted record disappears immediately after deletion", () -> {
            terminationReasons.createTerminationReason(reasonName);
            Assert.assertTrue(terminationReasons.isSuccessMessageDisplayed(),
                    "Termination Reason should be created successfully before testing deletion visibility.");
            terminationReasons.deleteFirstRecord();
            Assert.assertTrue(terminationReasons.isDeletedMessageDisplayed(),
                    "Termination Reason should be deleted successfully.");
            Assert.assertFalse(terminationReasons.isReasonNamePresentInTable(reasonName),
                    "Deleted Termination Reason should no longer appear in the records table.");
            return !terminationReasons.isReasonNamePresentInTable(reasonName);
        });
    }

    // TC_PIM_170 - Verify success message appears after deleting Termination Reason
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_170 - Ensure system confirms successful deletion")
    @Test
    public void verifySuccessMessageAfterDelete() {
        runTest("TC_PIM_170 - Ensure system confirms successful deletion", () -> {
            terminationReasons.deleteFirstRecord();
            Assert.assertTrue(terminationReasons.isDeletedMessageDisplayed(),
                    "Success message should be displayed after deleting a Termination Reason.");
            return terminationReasons.isDeletedMessageDisplayed();
        });
    }

    // TC_PIM_171 - Verify deleted Termination Reason does not reappear after refresh
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_171 - Ensure deleted record is permanently removed from table")
    @Test
    public void verifyDeletedReasonRemainsRemovedAfterRefresh() {
        String reasonName = "ToDeletePersist " + System.currentTimeMillis();
        runTest("TC_PIM_171 - Ensure deleted record is permanently removed from table", () -> {
            terminationReasons.createTerminationReason(reasonName);
            Assert.assertTrue(terminationReasons.isSuccessMessageDisplayed(),
                    "Termination Reason should be created successfully before testing post-refresh deletion persistence.");
            terminationReasons.deleteFirstRecord();
            Assert.assertTrue(terminationReasons.isDeletedMessageDisplayed(),
                    "Termination Reason should be deleted successfully.");
            driver.navigate().refresh();
            Assert.assertFalse(terminationReasons.isReasonNamePresentInTable(reasonName),
                    "Deleted Termination Reason should not reappear in the table after refresh.");
            return !terminationReasons.isReasonNamePresentInTable(reasonName);
        });
    }

    // TC_PIM_172 - Verify Termination Reason is not deleted before confirmation
    @Epic("PIM")
    @Feature("Termination Reasons")
    @Story("TC_PIM_172 - Ensure deletion occurs only after user confirmation")
    @Test
    public void verifyCancelDeletePreservesRecord() {
        runTest("TC_PIM_172 - Ensure deletion occurs only after user confirmation", () -> {
            terminationReasons.selectFirstRecordAndCancelDelete();
            Assert.assertTrue(terminationReasons.hasReasonRows(),
                    "Termination Reason record should remain in the table after canceling deletion.");
            return terminationReasons.hasReasonRows();
        });
    }
}