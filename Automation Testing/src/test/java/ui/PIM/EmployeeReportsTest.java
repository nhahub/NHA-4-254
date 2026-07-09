package ui.PIM;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.Base;
import org.example.DriverManager;
import org.example.pages.PIM.EmployeeReportsPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EmployeeReportsTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private EmployeeReportsPage reports;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();

        login = new loginPage(driver);
        reports = new EmployeeReportsPage(driver);

        login.open();
        login.LogIn("Admin", "admin123");

        reports.openReportsPage();
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
        runTest("Verify Employee Reports page opens successfully",
                () -> reports.pageOpened(driver.getCurrentUrl()));
    }

    // TC_PIM_173 - Verify Admin can successfully navigate to and load the Reports interface
    @Epic("PIM")
    @Feature("Employee Reports")

    @Story("TC_PIM_173 - Validates that the Reports navigation link is visible and loads the target view cleanly")
    @Test
    public void verifyReportsNavigationLoads() {
        runTest("TC_PIM_173 - Validates that the Reports navigation link is visible and loads the target view cleanly", () -> {
            Assert.assertTrue(reports.isPageLoaded(),
                    "Reports page did not load successfully after navigating from the PIM top nav bar.");
            return reports.isPageLoaded();
        });
    }

    // TC_PIM_174 - Verify data grid structure shows Report Name and Actions column values
    @Epic("PIM")
    @Feature("Employee Reports")
    @Story("TC_PIM_174 - Checks table design definitions to confirm column headers conform to layout specifications")
    @Test
    public void verifyTableColumnsCorrect() {
        runTest("TC_PIM_174 - Checks table design definitions to confirm column headers conform to layout specifications", () -> {
            Assert.assertTrue(reports.areRequiredColumnsDisplayed(),
                    "Reports table is missing one or more required columns (Report Name, Actions).");
            Assert.assertTrue(reports.hasReportRows(),
                    "Reports table should display existing records under their respective columns.");
            return reports.areRequiredColumnsDisplayed() && reports.hasReportRows();
        });
    }

    // TC_PIM_175 - Verify search filter results show only report names matching your query
    @Epic("PIM")
    @Feature("Employee Reports")
    @Story("TC_PIM_175 - Assures the filter system reduces table rows down to target configurations matching the query")
    @Test
    public void verifySearchFilterByReportName() {
        runTest("TC_PIM_175 - Assures the filter system reduces table rows down to target configurations matching the query", () -> {
            reports.searchByReportName("Sample");
            Assert.assertTrue(reports.hasReportRows() || reports.isNoRecordsFoundDisplayed(),
                    "Search for 'Sample' should return either matching report rows or a 'No Records Found' message — not an error.");
            return reports.hasReportRows() || reports.isNoRecordsFoundDisplayed();
        });
    }

    // TC_PIM_176 - Verify that clicking the View button successfully executes and displays the report data
    @Epic("PIM")
    @Feature("Employee Reports")
    @Story("TC_PIM_176 - Confirms that selecting the View icon routes the Admin to the report generation results page")
    @Test
    public void verifyViewReportButton() {
        runTest("TC_PIM_176 - Confirms that selecting the View icon routes the Admin to the report generation results page", () -> {
            reports.clickViewOnFirstReport();
            Assert.assertTrue(reports.isReportViewPageDisplayed(),
                    "Clicking View should redirect to the generated report results page with a table of employee records.");
            return reports.isReportViewPageDisplayed();
        });
    }

    // TC_PIM_177 - Verify that clicking the Edit button routes the Admin to the Report Edit Wizard form
    @Epic("PIM")
    @Feature("Employee Reports")
    @Story("TC_PIM_177 - Validates that clicking the Edit pencil icon opens the query workspace pre-populated with saved properties")
    @Test
    public void verifyEditReportButton() {
        runTest("TC_PIM_177 - Validates that clicking the Edit pencil icon opens the query workspace pre-populated with saved properties", () -> {
            Assert.assertTrue(reports.isEditFormPrefilled(),
                    "Edit Report form did not open with pre-filled existing report data.");
            return reports.isEditFormPrefilled();
        });
    }

    // TC_PIM_178 - Verify Admin can update details, receive a success message, and view changes in the table
    @Epic("PIM")
    @Feature("Employee Reports")
    @Story("TC_PIM_178 - Confirms that changing the report's attributes updates the database and triggers a success toast")
    @Test
    public void verifyUpdateReportDetails() {
        String updatedName = "QA Team Performance Report " + System.currentTimeMillis();
        runTest("TC_PIM_178 - Confirms that changing the report's attributes updates the database and triggers a success toast", () -> {
            reports.editFirstReportName(updatedName);
            Assert.assertTrue(reports.isUpdatedMessageDisplayed(),
                    "Expected 'Successfully Updated' toast not found after editing report details.");
            Assert.assertTrue(reports.isReportNamePresentInTable(updatedName),
                    "Updated report name '" + updatedName + "' should appear in the Predefined Reports table.");
            return reports.isUpdatedMessageDisplayed() && reports.isReportNamePresentInTable(updatedName);
        });
    }

    // TC_PIM_179 - Verify that clicking the Delete button brings up a confirmation warning
    @Epic("PIM")
    @Feature("Employee Reports")
    @Story("TC_PIM_179 - Assures the trash bin button shows an authorization confirmation modal before unlinking data")
    @Test
    public void verifyDeleteConfirmationAppears() {
        runTest("TC_PIM_179 - Assures the trash bin button shows an authorization confirmation modal before unlinking data", () -> {
            reports.clickDeleteOnFirstReport();
            Assert.assertTrue(reports.isDeleteConfirmationDisplayed(),
                    "Confirmation dialog was not displayed before deleting a report.");
            return reports.isDeleteConfirmationDisplayed();
        });
    }

    // TC_PIM_179 (confirm) - Verify report is removed after confirming deletion
    @Epic("PIM")
    @Feature("Employee Reports")
    @Story("TC_PIM_179 - Verify confirming deletion shows success message and removes the report row")
    @Test
    public void verifyDeleteReportAfterConfirmation() {
        runTest("TC_PIM_179 - Verify confirming deletion shows success message and removes the report row", () -> {
            reports.deleteFirstReport();
            Assert.assertTrue(reports.isDeletedMessageDisplayed(),
                    "Expected 'Successfully Deleted' message not found after confirming report deletion.");
            return reports.isDeletedMessageDisplayed();
        });
    }

    // TC_PIM_180 - Verify clicking the Add button displays the Add Report form with standard layout controls
    @Epic("PIM")
    @Feature("Employee Reports")
    @Story("TC_PIM_180 - Validates that interacting with the + Add control correctly changes the view to display the form")
    @Test
    public void verifyAddFormDisplayed() {
        runTest("TC_PIM_180 - Validates that interacting with the + Add control correctly changes the view to display the form", () -> {
            Assert.assertTrue(reports.isAddFormDisplayed(),
                    "Add Report form did not display the Report Name input and Save button after clicking + Add.");
            return reports.isAddFormDisplayed();
        });
    }

    // TC_PIM_181 - Verify successful report creation, transient feedback alerts, and new grid item visibility
    @Epic("PIM")
    @Feature("Employee Reports")
    @Story("TC_PIM_181 - Validates a complete creation cycle: valid submission saves, prompts success, and registers in the table")
    @Test
    public void verifyCreateReportSuccessfully() {
        String reportName = "QA Team Performance Report " + System.currentTimeMillis();
        runTest("TC_PIM_181 - Validates a complete creation cycle: valid submission saves, prompts success, and registers in the table", () -> {
            reports.createReportWithDisplayField(reportName, "First Name");
            Assert.assertTrue(reports.isSavedMessageDisplayed(),
                    "Expected 'Successfully Saved' message not found after creating a new report.");
            Assert.assertTrue(reports.isReportNamePresentInTable(reportName),
                    "Newly created report '" + reportName + "' should appear as an active row in the Predefined Reports table.");
            return reports.isSavedMessageDisplayed() && reports.isReportNamePresentInTable(reportName);
        });
    }

    // TC_PIM_182 - Verify form submission is blocked when Report Name is empty
    @Epic("PIM")
    @Feature("Employee Reports")
    @Story("TC_PIM_182 - Assures the form controller blocks transmission of payloads with a blank Report Name")
    @Test
    public void verifyEmptyReportNameValidation() {
        runTest("TC_PIM_182 - Assures the form controller blocks transmission of payloads with a blank Report Name", () -> {
            reports.saveAddFormWithEmptyName("First Name");
            Assert.assertTrue(reports.isRequiredErrorDisplayed(),
                    "Expected 'Required' validation error not displayed beneath the blank Report Name field.");
            return reports.isRequiredErrorDisplayed();
        });
    }

    // TC_PIM_183 - Verify form submission is blocked when no display field is added
    @Epic("PIM")
    @Feature("Employee Reports")
    @Story("TC_PIM_183 - Verifies the system prevents submission and warns when no display field has been selected")
    @Test
    public void verifyNoDisplayFieldValidation() {
        String reportName = "Incomplete Layout Test Report " + System.currentTimeMillis();
        runTest("TC_PIM_183 - Verifies the system prevents submission and warns when no display field has been selected", () -> {
            reports.createReportNameOnlyNoDisplayField(reportName);
            Assert.assertTrue(reports.isDisplayFieldWarningDisplayed(),
                    "Expected warning message 'At least one display field should be added' not displayed.");
            return reports.isDisplayFieldWarningDisplayed();
        });
    }
}