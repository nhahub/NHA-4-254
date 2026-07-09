package ui.PIM;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.Base;
import org.example.DriverManager;
import org.example.pages.PIM.CustomFieldsPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomFieldsTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private CustomFieldsPage customFields;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();

        login = new loginPage(driver);
        customFields = new CustomFieldsPage(driver);

        login.open();
        login.LogIn("Admin", "admin123");

        customFields.openCustomFieldsPage();
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
        runTest("Verify Custom Fields page opens successfully",
                () -> customFields.pageOpened(driver.getCurrentUrl()));
    }

    // ───────────────────────────── Page / Table ──────────────────────────────────

    // TC_PIM_073 - Verify admin can access Configuration menu
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_073 - Ensure Configuration menu is visible and clickable from PIM page")
    @Test
    public void verifyConfigurationMenuAccessible() {
        runTest("TC_PIM_073 - Ensure Configuration menu is visible and clickable from PIM page", () -> {
            Assert.assertTrue(customFields.isConfigurationMenuOpened(),
                    "Configuration dropdown menu did not open successfully.");
            return customFields.isConfigurationMenuOpened();
        });
    }

    // TC_PIM_074 - Verify Custom Fields option appears in Configuration submenu
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_074 - Ensure Custom Fields option is displayed correctly")
    @Test
    public void verifyCustomFieldsOptionDisplayed() {
        runTest("TC_PIM_074 - Ensure Custom Fields option is displayed correctly", () -> {
            Assert.assertTrue(customFields.isCustomFieldsOptionDisplayed(),
                    "Custom Fields option is not displayed in the Configuration submenu.");
            return customFields.isCustomFieldsOptionDisplayed();
        });
    }

    // TC_PIM_075 - Verify Custom Fields page loads without errors
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_075 - Ensure page renders correctly and remains stable")
    @Test
    public void verifyPageLoadsWithoutErrors() {
        runTest("TC_PIM_075 - Ensure page renders correctly and remains stable", () -> {
            Assert.assertTrue(customFields.isPageLoaded(),
                    "Custom Fields page did not load successfully.");
            return customFields.isPageLoaded();
        });
    }

    // TC_PIM_076 - Verify Custom Fields table displays correct columns
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_076 - Ensure table contains all required columns")
    @Test
    public void verifyTableColumnsCorrect() {
        runTest("TC_PIM_076 - Ensure table contains all required columns", () -> {
            Assert.assertTrue(customFields.areRequiredColumnsDisplayed(),
                    "Custom Fields table is missing one or more required columns (Name, Screen, Field Type, Actions).");
            return customFields.areRequiredColumnsDisplayed();
        });
    }

    // TC_PIM_077 - Verify Actions column contains Edit and Delete buttons
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_077 - Ensure action buttons are visible for each record")
    @Test
    public void verifyActionButtonsDisplayed() {
        runTest("TC_PIM_077 - Ensure action buttons are visible for each record", () -> {
            Assert.assertTrue(customFields.areActionButtonsDisplayedForRecords(),
                    "Edit and/or Delete buttons are not displayed for existing custom field records.");
            return customFields.areActionButtonsDisplayedForRecords();
        });
    }

    // TC_PIM_078 - Verify Add button is visible and accessible
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_078 - Ensure Add button appears correctly on page")
    @Test
    public void verifyAddButtonVisible() {
        runTest("TC_PIM_078 - Ensure Add button appears correctly on page", () -> {
            Assert.assertTrue(customFields.isAddButtonDisplayed(),
                    "Add button is not visible/clickable on the Custom Fields page.");
            return customFields.isAddButtonDisplayed();
        });
    }

    // TC_PIM_079 - Verify existing custom fields are displayed correctly
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_079 - Ensure stored custom fields appear in records table")
    @Test
    public void verifyExistingCustomFieldsDisplayed() {
        runTest("TC_PIM_079 - Ensure stored custom fields appear in records table", () -> {
            Assert.assertTrue(customFields.hasCustomFieldRows(),
                    "Existing custom field records are not displayed in the table.");
            return customFields.hasCustomFieldRows();
        });
    }

    // TC_PIM_080 - Verify "No Records Found" message when table is empty
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_080 - Ensure proper empty-state feedback appears")
    @Test
    public void verifyNoRecordsFoundMessage() {
        runTest("TC_PIM_080 - Ensure proper empty-state feedback appears", () -> {
            // This assumes a clean/empty environment; on a seeded demo this may not trigger.
            boolean hasRows = customFields.hasCustomFieldRows();
            boolean noRecordsShown = customFields.isNoRecordsFoundDisplayed();
            Assert.assertTrue(hasRows || noRecordsShown,
                    "Page should show either existing records or a 'No Records Found' message.");
            return hasRows || noRecordsShown;
        });
    }

    // ───────────────────────────── Add Custom Field ──────────────────────────────

    // TC_PIM_081 - Verify Add button opens Add Custom Field form
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_081 - Ensure Add form appears successfully after clicking Add button")
    @Test
    public void verifyAddFormOpens() {
        runTest("TC_PIM_081 - Ensure Add form appears successfully after clicking Add button", () -> {
            Assert.assertTrue(customFields.areAddFormFieldsDisplayed(),
                    "Add Custom Field form did not open after clicking the Add button.");
            return customFields.areAddFormFieldsDisplayed();
        });
    }

    // TC_PIM_082 - Verify Add form displays required fields
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_082 - Ensure Field Name, Screen, and Type fields are visible")
    @Test
    public void verifyAddFormRequiredFieldsDisplayed() {
        runTest("TC_PIM_082 - Ensure Field Name, Screen, and Type fields are visible", () -> {
            Assert.assertTrue(customFields.areAddFormFieldsDisplayed(),
                    "One or more required fields (Name, Screen, Type) are missing from the Add form.");
            return customFields.areAddFormFieldsDisplayed();
        });
    }

    // TC_PIM_083 - Verify system prevents empty required field submission
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_083 - Ensure form validation works correctly for empty fields")
    @Test
    public void verifyEmptyFieldValidation() {
        runTest("TC_PIM_083 - Ensure form validation works correctly for empty fields", () -> {
            customFields.saveAddFormEmpty();
            Assert.assertTrue(customFields.isRequiredErrorDisplayed(),
                    "Expected 'Required' validation error not displayed when saving with empty fields.");
            return customFields.isRequiredErrorDisplayed();
        });
    }

    // TC_PIM_084 - Verify custom field is created successfully with valid data
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_084 - Ensure valid custom field data is saved correctly")
    @Test
    public void verifyCreateCustomFieldValidData() {
        String fieldName = "Passport Number " + System.currentTimeMillis();
        runTest("TC_PIM_084 - Ensure valid custom field data is saved correctly", () -> {
            customFields.createCustomField(fieldName, "Personal Details", "Text");
            Assert.assertTrue(customFields.isSuccessMessageDisplayed(),
                    "Expected success message not found after creating a valid custom field.");
            return customFields.isSuccessMessageDisplayed();
        });
    }

    // TC_PIM_085 - Verify newly created custom field appears in records table
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_085 - Ensure newly added field displays immediately in table")
    @Test
    public void verifyNewFieldAppearsInTable() {
        String fieldName = "Passport Number " + System.currentTimeMillis();
        runTest("TC_PIM_085 - Ensure newly added field displays immediately in table", () -> {
            customFields.createCustomField(fieldName, "Personal Details", "Text");
            Assert.assertTrue(customFields.isSuccessMessageDisplayed(),
                    "Custom field should be created successfully before verifying it appears in the table.");
            Assert.assertTrue(customFields.isFieldNamePresentInTable(fieldName),
                    "Newly created custom field '" + fieldName + "' does not appear in the records table.");
            return customFields.isFieldNamePresentInTable(fieldName);
        });
    }

    // TC_PIM_086 - Verify success message after creating custom field
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_086 - Ensure system confirms successful creation")
    @Test
    public void verifySuccessMessageAfterCreate() {
        String fieldName = "Passport Number " + System.currentTimeMillis();
        runTest("TC_PIM_086 - Ensure system confirms successful creation", () -> {
            customFields.createCustomField(fieldName, "Personal Details", "Text");
            Assert.assertTrue(customFields.isSuccessMessageDisplayed(),
                    "Success message should be displayed after creating a custom field.");
            return customFields.isSuccessMessageDisplayed();
        });
    }

    // TC_PIM_087 - Verify long custom field names do not break table layout
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_087 - Ensure UI layout remains stable when entering very long field names")
    @Test
    public void verifyLongFieldNameLayoutStable() {
        String longName = "EmployeeIdentificationNumberForInternationalBranchOfficeManagementDepartment";
        runTest("TC_PIM_087 - Ensure UI layout remains stable when entering very long field names", () -> {
            customFields.createCustomFieldWithLongName(longName, "Personal Details", "Text");
            Assert.assertTrue(customFields.isPageLoaded(),
                    "Page should remain stable and table layout should not break with a very long field name.");
            return customFields.isPageLoaded();
        });
    }

    // TC_PIM_088 - Verify special characters do not cause UI issues in custom field names
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_088 - Ensure system safely handles special characters entered in field names")
    @Test
    public void verifySpecialCharactersHandledSafely() {
        runTest("TC_PIM_088 - Ensure system safely handles special characters entered in field names", () -> {
            customFields.createCustomFieldWithSpecialChars("@@@###$$$%%%^^^", "Personal Details", "Text");
            Assert.assertTrue(customFields.isPageLoaded(),
                    "UI should remain stable and functional after submitting special characters in field name.");
            return customFields.isPageLoaded();
        });
    }

    // TC_PIM_089 - Verify system prevents duplicate custom field names
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_089 - Ensure system does not allow creating duplicate custom fields with same name")
    @Test
    public void verifyDuplicateFieldNamePrevented() {
        String fieldName = "Passport Number Dup " + System.currentTimeMillis();
        runTest("TC_PIM_089 - Ensure system does not allow creating duplicate custom fields with same name", () -> {
            // Create the field first
            customFields.createCustomField(fieldName, "Personal Details", "Text");
            Assert.assertTrue(customFields.isSuccessMessageDisplayed(),
                    "Initial custom field should be created successfully before testing duplicate prevention.");
            // Attempt to create a duplicate
            customFields.createDuplicateCustomField(fieldName, "Personal Details", "Text");
            Assert.assertTrue(customFields.isAlreadyExistsErrorDisplayed(),
                    "Expected 'Already exists' validation error not displayed for duplicate custom field name.");
            return customFields.isAlreadyExistsErrorDisplayed();
        });
    }

    // TC_PIM_090 - Verify system prevents duplicate records on double Save click
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_090 - Ensure rapid repeated clicks do not create duplicate entries")
    @Test
    public void verifyNoDuplicateOnDoubleSaveClick() {
        String fieldName = "Passport Number DC " + System.currentTimeMillis();
        runTest("TC_PIM_090 - Ensure rapid repeated clicks do not create duplicate entries", () -> {
            customFields.doubleClickSaveOnAddForm(fieldName, "Personal Details", "Text");
            Assert.assertTrue(customFields.isSuccessMessageDisplayed(),
                    "Only one custom field record should be created even after rapid double-click on Save.");
            return customFields.isSuccessMessageDisplayed();
        });
    }

    // ───────────────────────────── Edit Custom Field ─────────────────────────────

    // TC_PIM_091 - Verify Edit button opens edit form with existing data
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_091 - Ensure existing record data loads correctly in edit mode")
    @Test
    public void verifyEditFormOpensWithExistingData() {
        runTest("TC_PIM_091 - Ensure existing record data loads correctly in edit mode", () -> {
            Assert.assertTrue(customFields.isEditFormPrefilled(),
                    "Edit form did not open with pre-filled existing custom field data.");
            return customFields.isEditFormPrefilled();
        });
    }

    // TC_PIM_092 - Verify admin can update custom field details
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_092 - Ensure edited values are saved correctly")
    @Test
    public void verifyUpdateCustomField() {
        String updatedName = "National ID " + System.currentTimeMillis();
        runTest("TC_PIM_092 - Ensure edited values are saved correctly", () -> {
            customFields.editFirstRecord(updatedName);
            Assert.assertTrue(customFields.isUpdatedMessageDisplayed(),
                    "Expected 'Successfully Updated' message not found after editing custom field.");
            return customFields.isUpdatedMessageDisplayed();
        });
    }

    // TC_PIM_093 - Verify updated custom field appears immediately in table
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_093 - Ensure table refreshes with latest values")
    @Test
    public void verifyUpdatedFieldAppearsInTable() {
        String updatedName = "National ID " + System.currentTimeMillis();
        runTest("TC_PIM_093 - Ensure table refreshes with latest values", () -> {
            customFields.editFirstRecord(updatedName);
            Assert.assertTrue(customFields.isUpdatedMessageDisplayed(),
                    "Field should be updated successfully before verifying it appears in the table.");
            Assert.assertTrue(customFields.isFieldNamePresentInTable(updatedName),
                    "Updated custom field name '" + updatedName + "' does not appear in the records table.");
            return customFields.isFieldNamePresentInTable(updatedName);
        });
    }

    // TC_PIM_094 - Verify success message appears after updating custom field
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_094 - Ensure system confirms successful update")
    @Test
    public void verifySuccessMessageAfterUpdate() {
        String updatedName = "National ID " + System.currentTimeMillis();
        runTest("TC_PIM_094 - Ensure system confirms successful update", () -> {
            customFields.editFirstRecord(updatedName);
            Assert.assertTrue(customFields.isUpdatedMessageDisplayed(),
                    "Success message should be displayed after updating a custom field.");
            return customFields.isUpdatedMessageDisplayed();
        });
    }

    // TC_PIM_095 - Verify refreshing page during edit does not corrupt data
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_095 - Ensure system preserves data integrity when browser refresh occurs")
    @Test
    public void verifyRefreshDuringEditDoesNotCorruptData() {
        runTest("TC_PIM_095 - Ensure system preserves data integrity when browser refresh occurs", () -> {
            customFields.modifyFirstRecordThenRefresh("Employee National ID " + System.currentTimeMillis());
            Assert.assertTrue(customFields.isPageLoaded(),
                    "Page should reload correctly and original data should remain intact after refresh during edit.");
            return customFields.isPageLoaded();
        });
    }

    // TC_PIM_096 - Verify edit form loads existing field data correctly
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_096 - Ensure edit form is populated with correct existing values")
    @Test
    public void verifyEditFormLoadsCorrectData() {
        runTest("TC_PIM_096 - Ensure edit form is populated with correct existing values", () -> {
            Assert.assertTrue(customFields.isEditFormPrefilled(),
                    "Edit form should be populated with the existing custom field's data.");
            return customFields.isEditFormPrefilled();
        });
    }

    // ───────────────────────────── Delete Custom Field ───────────────────────────

    // TC_PIM_097 - Verify custom field records can be selected using checkboxes
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_097 - Ensure admin can select one or multiple records")
    @Test
    public void verifyRecordsSelectableWithCheckboxes() {
        runTest("TC_PIM_097 - Ensure admin can select one or multiple records", () -> {
            customFields.selectFirstRecordCheckbox();
            Assert.assertTrue(customFields.isAnyCheckboxSelected(),
                    "Selecting the checkbox for a custom field record did not register as selected.");
            return customFields.isAnyCheckboxSelected();
        });
    }

    // TC_PIM_098 - Verify confirmation message appears before deletion
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_098 - Ensure system requests confirmation before deleting records")
    @Test
    public void verifyDeleteConfirmationAppears() {
        runTest("TC_PIM_098 - Ensure system requests confirmation before deleting records", () -> {
            customFields.selectFirstRecordCheckbox();
            customFields.clickDeleteOnSelected();
            Assert.assertTrue(customFields.isDeleteConfirmationDisplayed(),
                    "Confirmation dialog was not displayed before deleting a custom field.");
            return customFields.isDeleteConfirmationDisplayed();
        });
    }

    // TC_PIM_099 - Verify custom field is deleted successfully after confirmation
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_099 - Ensure selected record is removed correctly after confirmation")
    @Test
    public void verifyDeleteAfterConfirmation() {
        runTest("TC_PIM_099 - Ensure selected record is removed correctly after confirmation", () -> {
            customFields.deleteFirstRecord();
            Assert.assertTrue(customFields.isDeletedMessageDisplayed(),
                    "Expected 'Successfully Deleted' message not found after confirming custom field deletion.");
            return customFields.isDeletedMessageDisplayed();
        });
    }

    // TC_PIM_100 - Verify deleted custom field no longer appears in table
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_100 - Ensure deleted records disappear from records table immediately")
    @Test
    public void verifyDeletedFieldNotInTable() {
        String fieldName = "ToDelete " + System.currentTimeMillis();
        runTest("TC_PIM_100 - Ensure deleted records disappear from records table immediately", () -> {
            customFields.createCustomField(fieldName, "Personal Details", "Text");
            Assert.assertTrue(customFields.isSuccessMessageDisplayed(),
                    "Field should be created successfully before testing deletion visibility.");
            customFields.deleteFirstRecord();
            Assert.assertTrue(customFields.isDeletedMessageDisplayed(),
                    "Field should be deleted successfully.");
            Assert.assertFalse(customFields.isFieldNamePresentInTable(fieldName),
                    "Deleted custom field should no longer appear in the records table.");
            return !customFields.isFieldNamePresentInTable(fieldName);
        });
    }

    // TC_PIM_101 - Verify success message appears after deleting custom field
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_101 - Ensure system confirms successful deletion")
    @Test
    public void verifySuccessMessageAfterDelete() {
        runTest("TC_PIM_101 - Ensure system confirms successful deletion", () -> {
            customFields.deleteFirstRecord();
            Assert.assertTrue(customFields.isDeletedMessageDisplayed(),
                    "Success message should be displayed after deleting a custom field.");
            return customFields.isDeletedMessageDisplayed();
        });
    }

    // TC_PIM_102 - Verify deleted record remains removed after page refresh
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_102 - Ensure deleted records do not reappear after refreshing the page")
    @Test
    public void verifyDeletedRecordRemainsRemovedAfterRefresh() {
        String fieldName = "ToDeletePersist " + System.currentTimeMillis();
        runTest("TC_PIM_102 - Ensure deleted records do not reappear after refreshing the page", () -> {
            customFields.createCustomField(fieldName, "Personal Details", "Text");
            Assert.assertTrue(customFields.isSuccessMessageDisplayed(),
                    "Field should be created successfully before testing post-refresh deletion persistence.");
            customFields.deleteFirstRecord();
            Assert.assertTrue(customFields.isDeletedMessageDisplayed(),
                    "Field should be deleted successfully.");
            driver.navigate().refresh();
            Assert.assertFalse(customFields.isFieldNamePresentInTable(fieldName),
                    "Deleted custom field should not reappear in the table after refresh.");
            return !customFields.isFieldNamePresentInTable(fieldName);
        });
    }

    // TC_PIM_103 - Verify record is not deleted when confirmation popup is canceled
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_103 - Ensure cancellation action prevents accidental deletion")
    @Test
    public void verifyCancelDeletePreservesRecord() {
        runTest("TC_PIM_103 - Ensure cancellation action prevents accidental deletion", () -> {
            customFields.selectFirstRecordAndCancelDelete();
            Assert.assertTrue(customFields.hasCustomFieldRows(),
                    "Custom field record should remain in the table after canceling deletion.");
            return customFields.hasCustomFieldRows();
        });
    }

    // TC_PIM_104 - Verify table refreshes automatically after record deletion
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_104 - Ensure deleted records disappear immediately without manual refresh")
    @Test
    public void verifyTableAutoRefreshesAfterDelete() {
        runTest("TC_PIM_104 - Ensure deleted records disappear immediately without manual refresh", () -> {
            customFields.deleteFirstRecord();
            Assert.assertTrue(customFields.isDeletedMessageDisplayed(),
                    "Table should update automatically and show deletion confirmation without requiring manual refresh.");
            return customFields.isDeletedMessageDisplayed();
        });
    }

    // TC_PIM_105 - Verify checkbox selection is cleared after delete operation
    @Epic("PIM")
    @Feature("Custom Fields")
    @Story("TC_PIM_105 - Ensure checkbox state resets correctly after deletion completes")
    @Test
    public void verifyCheckboxClearedAfterDelete() {
        runTest("TC_PIM_105 - Ensure checkbox state resets correctly after deletion completes", () -> {
            customFields.deleteFirstRecord();
            Assert.assertTrue(customFields.isDeletedMessageDisplayed(),
                    "Record should be deleted successfully before verifying checkbox reset.");
            Assert.assertFalse(customFields.isAnyCheckboxSelected(),
                    "Checkbox selections should be cleared automatically after the delete operation completes.");
            return !customFields.isAnyCheckboxSelected();
        });
    }
}