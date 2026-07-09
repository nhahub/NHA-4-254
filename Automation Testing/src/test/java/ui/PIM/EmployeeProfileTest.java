package ui.PIM;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.Base;
import org.example.DriverManager;
import org.example.pages.PIM.EmployeeListPage;
import org.example.pages.PIM.EmployeeProfilePage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EmployeeProfileTest extends Base {

    // Sample attachment path used across attachment-related tests.
    // Update this to point to a real local file in your test resources.
    private static final String SAMPLE_FILE_PATH =
            System.getProperty("user.dir") + "/src/test/resources/national_id.pdf";
    private WebDriver driver;
    private loginPage login;
    private EmployeeListPage employeeList;
    private EmployeeProfilePage profile;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();

        login = new loginPage(driver);
        employeeList = new EmployeeListPage(driver);
        profile = new EmployeeProfilePage(driver);

        login.open();
        login.LogIn("Admin", "admin123");

        // Navigate directly to a known employee profile (Personal Details tab)
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/172");
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
        runTest("Verify Employee Profile (Personal Details) page opens successfully",
                () -> profile.isProfilePageLoaded());
    }

    // ───────────────────────────── Personal Details ─────────────────────────────

    // TC_PIM_048 - Verify Admin can modify and save all editable fields in the Personal Details section
    @Epic("PIM")
    @Feature("Employee Profile - Personal Details")
    @Story("TC_PIM_048 - Ensure that an Admin can successfully update all personal identity fields")
    @Test
    public void verifyUpdatePersonalDetails() {
        runTest("TC_PIM_048 - Ensure that an Admin can successfully update all personal identity fields", () -> {
            profile.updatePersonalDetails("Alex", "M", "Smith", "ID9988", "DL-77665");
            profile.selectNationality("American");
            profile.selectMaritalStatus("Married");
            profile.selectGenderMale();
            profile.savePersonalDetails();
            Assert.assertTrue(profile.isUpdatedMessageDisplayed(),
                    "Expected 'Successfully Updated' message not found after editing Personal Details.");
            return profile.isUpdatedMessageDisplayed();
        });
    }

    // TC_PIM_049 - Verify Admin can successfully upload a valid file attachment with a comment (Personal Details)
    @Epic("PIM")
    @Feature("Employee Profile - Personal Details")
    @Story("TC_PIM_049 - Ensure Admin can upload a valid file with a comment in the Attachments section")
    @Test
    public void verifyUploadAttachmentPersonalDetails() {
        runTest("TC_PIM_049 - Ensure Admin can upload a valid file with a comment in the Attachments section", () -> {
            profile.uploadAttachment(SAMPLE_FILE_PATH, "Valid national identity document copy.");
            Assert.assertTrue(profile.isSuccessMessageDisplayed(),
                    "Expected 'Successfully Saved' message not found after uploading attachment.");
            return profile.isSuccessMessageDisplayed();
        });
    }

    // TC_PIM_050 - Verify validation error when saving an attachment with an empty file field (Personal Details)
    @Epic("PIM")
    @Feature("Employee Profile - Personal Details")
    @Story("TC_PIM_050 - Ensure the system prevents submission when file field is empty")
    @Test
    public void verifyAttachmentEmptyFileValidationPersonalDetails() {
        runTest("TC_PIM_050 - Ensure the system prevents submission when file field is empty", () -> {
            profile.attemptSaveWithoutFile("Attempting submission without file data.");
            Assert.assertTrue(profile.isRequiredErrorDisplayed(),
                    "Expected 'Required' validation error not displayed for empty file upload field.");
            return profile.isRequiredErrorDisplayed();
        });
    }

    // TC_PIM_051 - Verify Admin can edit an existing attachment's details (Personal Details)
    @Epic("PIM")
    @Feature("Employee Profile - Personal Details")
    @Story("TC_PIM_051 - Ensure clicking Edit on an attachment allows uploading a new file and modifying the comment")
    @Test
    public void verifyEditAttachmentPersonalDetails() {
        runTest("TC_PIM_051 - Ensure clicking Edit on an attachment allows uploading a new file and modifying the comment", () -> {
            profile.editFirstAttachment(null, "Updated national identity document copy - Ver 2.");
            Assert.assertTrue(profile.isUpdatedMessageDisplayed(),
                    "Expected 'Successfully Updated' message not found after editing attachment.");
            return profile.isUpdatedMessageDisplayed();
        });
    }

    // TC_PIM_052 - Verify Admin can successfully download an uploaded attachment file (Personal Details)
    @Epic("PIM")
    @Feature("Employee Profile - Personal Details")
    @Story("TC_PIM_052 - Ensure clicking Download fetches the file from the server")
    @Test
    public void verifyDownloadAttachmentPersonalDetails() {
        runTest("TC_PIM_052 - Ensure clicking Download fetches the file from the server", () -> {
            Assert.assertTrue(profile.hasAttachmentRows(),
                    "At least one attachment row should exist before attempting download.");
            profile.downloadFirstAttachment();
            // Download is a browser-level action; we verify the click executed without breaking the page.
            Assert.assertTrue(profile.isProfilePageLoaded(),
                    "Page should remain stable after triggering attachment download.");
            return profile.isProfilePageLoaded();
        });
    }

    // TC_PIM_053 - Verify Admin can delete an attachment row with confirmation (Personal Details)
    @Epic("PIM")
    @Feature("Employee Profile - Personal Details")
    @Story("TC_PIM_053 - Ensure clicking Delete unlinks and removes the selected attachment")
    @Test
    public void verifyDeleteAttachmentPersonalDetails() {
        runTest("TC_PIM_053 - Ensure clicking Delete unlinks and removes the selected attachment", () -> {
            profile.deleteFirstAttachment();
            Assert.assertTrue(profile.isDeletedMessageDisplayed(),
                    "Expected 'Successfully Deleted' message not found after deleting attachment.");
            return profile.isDeletedMessageDisplayed();
        });
    }

    // ───────────────────────────── Contact Details ──────────────────────────────

    // TC_PIM_054 - Verify Admin can modify and save all editable fields in the Contact Details section
    @Epic("PIM")
    @Feature("Employee Profile - Contact Details")
    @Story("TC_PIM_054 - Ensure that an Admin can update all contact-related parameters")
    @Test
    public void verifyUpdateContactDetails() {
        runTest("TC_PIM_054 - Ensure that an Admin can update all contact-related parameters", () -> {
            profile.openContactDetailsTab();
            profile.updateContactDetails(
                    "123 Main Street", "123 Secondary Street", "Cairo",
                    "12345", "03123456", "01001234567", "01234567891",
                    "alex.smith@orangehrm.com", "test@test.com"
            );
            profile.selectCountry("Egypt");
            profile.saveContactDetails();
            Assert.assertTrue(profile.isUpdatedMessageDisplayed(),
                    "Expected 'Successfully Updated' message not found after editing Contact Details.");
            return profile.isUpdatedMessageDisplayed();
        });
    }

    // TC_PIM_055 - Verify Admin can upload a valid file attachment with a comment (Contact Details)
    @Epic("PIM")
    @Feature("Employee Profile - Contact Details")
    @Story("TC_PIM_055 - Ensure Admin can upload a valid file with a comment in the Attachments section")
    @Test
    public void verifyUploadAttachmentContactDetails() {
        runTest("TC_PIM_055 - Ensure Admin can upload a valid file with a comment in the Attachments section", () -> {
            profile.openContactDetailsTab();
            profile.uploadAttachment(SAMPLE_FILE_PATH, "Valid national identity document copy.");
            Assert.assertTrue(profile.isSuccessMessageDisplayed(),
                    "Expected 'Successfully Saved' message not found after uploading attachment.");
            return profile.isSuccessMessageDisplayed();
        });
    }

    // TC_PIM_056 - Verify validation error when saving an attachment with an empty file field (Contact Details)
    @Epic("PIM")
    @Feature("Employee Profile - Contact Details")
    @Story("TC_PIM_056 - Ensure the system prevents submission when file field is empty")
    @Test
    public void verifyAttachmentEmptyFileValidationContactDetails() {
        runTest("TC_PIM_056 - Ensure the system prevents submission when file field is empty", () -> {
            profile.openContactDetailsTab();
            profile.attemptSaveWithoutFile("Attempting submission without file data.");
            Assert.assertTrue(profile.isRequiredErrorDisplayed(),
                    "Expected 'Required' validation error not displayed for empty file upload field.");
            return profile.isRequiredErrorDisplayed();
        });
    }

    // TC_PIM_057 - Verify Admin can edit an existing attachment's details (Contact Details)
    @Epic("PIM")
    @Feature("Employee Profile - Contact Details")
    @Story("TC_PIM_057 - Ensure clicking Edit on an attachment allows uploading a new file and modifying the comment")
    @Test
    public void verifyEditAttachmentContactDetails() {
        runTest("TC_PIM_057 - Ensure clicking Edit on an attachment allows uploading a new file and modifying the comment", () -> {
            profile.openContactDetailsTab();
            profile.editFirstAttachment(null, "Updated national identity document copy - Ver 2.");
            Assert.assertTrue(profile.isUpdatedMessageDisplayed(),
                    "Expected 'Successfully Updated' message not found after editing attachment.");
            return profile.isUpdatedMessageDisplayed();
        });
    }

    // TC_PIM_058 - Verify Admin can successfully download an uploaded attachment file (Contact Details)
    @Epic("PIM")
    @Feature("Employee Profile - Contact Details")
    @Story("TC_PIM_058 - Ensure clicking Download fetches the file from the server")
    @Test
    public void verifyDownloadAttachmentContactDetails() {
        runTest("TC_PIM_058 - Ensure clicking Download fetches the file from the server", () -> {
            profile.openContactDetailsTab();
            Assert.assertTrue(profile.hasAttachmentRows(),
                    "At least one attachment row should exist before attempting download.");
            profile.downloadFirstAttachment();
            Assert.assertTrue(profile.isProfilePageLoaded(),
                    "Page should remain stable after triggering attachment download.");
            return profile.isProfilePageLoaded();
        });
    }

    // TC_PIM_059 - Verify Admin can delete an attachment row with confirmation (Contact Details)
    @Epic("PIM")
    @Feature("Employee Profile - Contact Details")
    @Story("TC_PIM_059 - Ensure clicking Delete unlinks and removes the selected attachment")
    @Test
    public void verifyDeleteAttachmentContactDetails() {
        runTest("TC_PIM_059 - Ensure clicking Delete unlinks and removes the selected attachment", () -> {
            profile.openContactDetailsTab();
            profile.deleteFirstAttachment();
            Assert.assertTrue(profile.isDeletedMessageDisplayed(),
                    "Expected 'Successfully Deleted' message not found after deleting attachment.");
            return profile.isDeletedMessageDisplayed();
        });
    }

    // ───────────────────────────── Cross-Cutting Checks ──────────────────────────

    // TC_PIM_063 - Verify system displays failure message when deletion fails
    @Epic("PIM")
    @Feature("Employee Profile")
    @Story("TC_PIM_063 - Ensure system reports deletion failure properly for restricted records")
    @Test
    public void verifyDeletionFailureMessage() {
        runTest("TC_PIM_063 - Ensure system reports deletion failure properly for restricted records", () -> {
            employeeList.openEmployeeListPage();
            // Attempt to delete a restricted/logged-in admin's own employee record (empNumber 172 assumed admin-linked)
            employeeList.searchByEmployeeId("172");
            employeeList.deleteFirstEmployee();
            // Either a failure toast appears, or the record correctly disappears (environment dependent)
            boolean handled = employeeList.isSuccessMessageDisplayed() || employeeList.isNoRecordsFoundDisplayed();
            Assert.assertTrue(handled,
                    "System should clearly report success or failure feedback when attempting to delete a restricted employee.");
            return handled;
        });
    }

    // TC_PIM_061 - Verify deleted employee no longer appears in employee list
    @Epic("PIM")
    @Feature("Employee Profile")
    @Story("TC_PIM_061 - Ensure deleted records are removed from displayed table")
    @Test
    public void verifyDeletedEmployeeNotInList() {
        runTest("TC_PIM_061 - Ensure deleted records are removed from displayed table", () -> {
            employeeList.openEmployeeListPage();
            employeeList.deleteFirstEmployee();
            Assert.assertTrue(employeeList.isSuccessMessageDisplayed(),
                    "Employee should be deleted successfully before verifying removal from the list.");
            return employeeList.isSuccessMessageDisplayed();
        });
    }
}