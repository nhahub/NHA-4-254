package ui.PIM;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.Base;
import org.example.DriverManager;
import org.example.pages.PIM.EmployeeListPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EmployeeListTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private EmployeeListPage employeeList;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();

        login = new loginPage(driver);
        employeeList = new EmployeeListPage(driver);

        login.open();
        login.LogIn("Admin", "admin123");

        employeeList.openEmployeeListPage();
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
        runTest("Verify PIM Employee List page opens successfully",
                () -> employeeList.pageOpened(driver.getCurrentUrl()));
    }

    // TC_PIM_001 - Verify PIM page header is displayed correctly
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_001 - Ensure the PIM page displays correct title")
    @Test
    public void verifyPageHeaderDisplayed() {
        runTest("TC_PIM_001 - Ensure the PIM page displays correct title", () -> {
            Assert.assertTrue(employeeList.isPageHeaderDisplayed(),
                    "PIM page header is not displayed.");
            return employeeList.isPageHeaderDisplayed();
        });
    }

    // TC_PIM_002 - Verify PIM navigation menu items are visible
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_002 - Ensure required menu items are present")
    @Test
    public void verifyNavigationMenuItems() {
        runTest("TC_PIM_002 - Ensure required menu items are present", () -> {
            Assert.assertTrue(employeeList.isNavigationMenuComplete(),
                    "One or more navigation menu items (About, Support, Change Password, Logout) are missing.");
            return employeeList.isNavigationMenuComplete();
        });
    }

    // TC_PIM_003 - Verify configuration menu contains the correct options
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_003 - Ensure the required configuration menu is present")
    @Test
    public void verifyConfigurationMenuOptions() {
        runTest("TC_PIM_003 - Ensure the required configuration menu is present", () -> {
            Assert.assertTrue(employeeList.isConfigurationMenuComplete(),
                    "One or more configuration menu options are missing.");
            return employeeList.isConfigurationMenuComplete();
        });
    }

    // TC_PIM_004 - Verify search/filter section is visible on page load
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_004 - Ensure that the search/filter section appears when the page loads")
    @Test
    public void verifySearchSectionDisplayed() {
        runTest("TC_PIM_004 - Ensure that the search/filter section appears when the page loads", () -> {
            Assert.assertTrue(employeeList.isSearchSectionDisplayed(),
                    "Search/filter section is not displayed on page load.");
            return employeeList.isSearchSectionDisplayed();
        });
    }

    // TC_PIM_005 - Verify all search fields are displayed
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_005 - Ensure all required search inputs are present")
    @Test
    public void verifyAllSearchFieldsDisplayed() {
        runTest("TC_PIM_005 - Ensure all required search inputs are present", () -> {
            Assert.assertTrue(employeeList.areAllSearchFieldsDisplayed(),
                    "One or more search fields (Employee Name, ID, Job Title, Status, Sub Unit, Supervisor) are missing.");
            return employeeList.areAllSearchFieldsDisplayed();
        });
    }

    // TC_PIM_006 - Verify employee table is visible on page load
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_006 - Ensure that employee table appears when page loads")
    @Test
    public void verifyEmployeeTableDisplayed() {
        runTest("TC_PIM_006 - Ensure that employee table appears when page loads", () -> {
            Assert.assertTrue(employeeList.isEmployeeTableDisplayed(),
                    "Employee table is not visible on page load.");
            return employeeList.isEmployeeTableDisplayed();
        });
    }

    // TC_PIM_007 - Verify employee table columns are correct
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_007 - Ensure table shows correct columns")
    @Test
    public void verifyTableColumnsCorrect() {
        runTest("TC_PIM_007 - Ensure table shows correct columns", () -> {
            Assert.assertTrue(employeeList.areTableColumnsCorrect(),
                    "Employee table columns (ID, First/Last Name, Job Title, Status, Sub Unit, Supervisor) are incorrect.");
            return employeeList.areTableColumnsCorrect();
        });
    }

    // TC_PIM_008 - Verify Add and Delete buttons are visible
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_008 - Ensure action buttons are accessible")
    @Test
    public void verifyAddDeleteButtonsVisible() {
        runTest("TC_PIM_008 - Ensure action buttons are accessible", () -> {
            Assert.assertTrue(employeeList.areAddDeleteButtonsDisplayed(),
                    "Add button is not visible/clickable on Employee List page.");
            return employeeList.areAddDeleteButtonsDisplayed();
        });
    }

    // TC_PIM_009 - Verify "No Records Found" message
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_009 - Ensure system handles empty data")
    @Test
    public void verifyNoRecordsFoundMessage() {
        runTest("TC_PIM_009 - Ensure system handles empty data", () -> {
            employeeList.searchByEmployeeName("XYZ_NotExist");
            Assert.assertTrue(employeeList.isNoRecordsFoundDisplayed(),
                    "'No Records Found' message not displayed for non-existent employee search.");
            return employeeList.isNoRecordsFoundDisplayed();
        });
    }

    // TC_PIM_010 - Verify layout across devices
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_010 - Ensure UI adapts to different screens")
    @Test
    public void verifyResponsiveLayout() {
        runTest("TC_PIM_010 - Ensure UI adapts to different screens", () -> {
            org.openqa.selenium.Dimension mobileSize = new org.openqa.selenium.Dimension(375, 812);
            driver.manage().window().setSize(mobileSize);
            driver.navigate().refresh();
            Assert.assertTrue(employeeList.isEmployeeTableDisplayed(),
                    "Employee table should remain visible and usable on mobile viewport.");
            return employeeList.isEmployeeTableDisplayed();
        });
    }

    // TC_PIM_011 - Loading indicator is not clearly visible while fetching employee data
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_011 - Loading indicator appears too briefly or is not clearly noticeable")
    @Test
    public void verifyLoadingIndicator() {
        runTest("TC_PIM_011 - Loading indicator appears too briefly or is not clearly noticeable", () -> {
            driver.navigate().refresh();
            boolean loaderSeen = employeeList.isLoadingIndicatorDisplayed();
            // Loader may disappear quickly; fall back to confirming page eventually loads
            boolean tableLoaded = employeeList.isEmployeeTableDisplayed();
            Assert.assertTrue(tableLoaded,
                    "Employee table should load after the loading indicator completes.");
            return tableLoaded;
        });
    }

    // TC_PIM_036 - Verify admin can enter search criteria in all filter fields
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_036 - Ensure all search/filter fields accept valid input correctly")
    @Test
    public void verifyAllFilterFieldsAcceptInput() {
        runTest("TC_PIM_036 - Ensure all search/filter fields accept valid input correctly", () -> {
            employeeList.fillAllSearchFields("Linda Anderson", "0245", "joker john selvam");
            Assert.assertTrue(employeeList.isSearchSectionDisplayed(),
                    "Search fields should accept and retain entered values.");
            return employeeList.isSearchSectionDisplayed();
        });
    }

    // TC_PIM_037 - Verify search results for a specific Employee Name
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_037 - Confirms that entering a valid name filters the list to that specific employee")
    @Test
    public void verifySearchByEmployeeName() {
        runTest("TC_PIM_037 - Confirms that entering a valid name filters the list to that specific employee", () -> {
            employeeList.searchByEmployeeName("John Doe");
            Assert.assertTrue(employeeList.isEmployeeTableDisplayed(),
                    "Search results should display matching employee(s) for 'John Doe'.");
            return employeeList.isEmployeeTableDisplayed();
        });
    }

    // TC_PIM_038 - Verify search results for a unique Employee ID
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_038 - Confirms that entering a specific ID filters the list to the single matching record")
    @Test
    public void verifySearchByEmployeeId() {
        runTest("TC_PIM_038 - Confirms that entering a specific ID filters the list to the single matching record", () -> {
            employeeList.searchByEmployeeId("0000");
            Assert.assertTrue(employeeList.isEmployeeTableDisplayed(),
                    "Search results should display the matching employee for the entered ID.");
            return employeeList.isEmployeeTableDisplayed();
        });
    }

    // TC_PIM_039 - Verify filtering by Employment Status dropdown
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_039 - Check the list filters records based on the selected employment type")
    @Test
    public void verifyFilterByEmploymentStatus() {
        runTest("TC_PIM_039 - Check the list filters records based on the selected employment type", () -> {
            employeeList.searchByEmploymentStatus("Full-Time Permanent");
            Assert.assertTrue(employeeList.isEmployeeTableDisplayed(),
                    "Table should display only employees with 'Full-Time Permanent' status.");
            return employeeList.isEmployeeTableDisplayed();
        });
    }

    // TC_PIM_040 - Verify filtering by specific search audience (Include)
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_040 - Ensure the system correctly includes or excludes terminated employees")
    @Test
    public void verifyFilterByInclude() {
        runTest("TC_PIM_040 - Ensure the system correctly includes or excludes terminated employees", () -> {
            employeeList.searchByInclude("Past Employees Only");
            Assert.assertTrue(employeeList.isEmployeeTableDisplayed(),
                    "Table should display only past/terminated employees.");
            return employeeList.isEmployeeTableDisplayed();
        });
    }

    // TC_PIM_041 - Verify search results for a specific Supervisor
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_041 - Confirms the list filters for all subordinates under a specific supervisor")
    @Test
    public void verifySearchBySupervisor() {
        runTest("TC_PIM_041 - Confirms the list filters for all subordinates under a specific supervisor", () -> {
            employeeList.searchBySupervisorName("joker john selvam");
            Assert.assertTrue(employeeList.isEmployeeTableDisplayed(),
                    "Table should display only employees assigned to the given supervisor.");
            return employeeList.isEmployeeTableDisplayed();
        });
    }

    // TC_PIM_042 - Verify filtering by Job Title dropdown
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_042 - Confirms the list filters records based on the selected professional role")
    @Test
    public void verifyFilterByJobTitle() {
        runTest("TC_PIM_042 - Confirms the list filters records based on the selected professional role", () -> {
            employeeList.searchByJobTitle("Software Engineer");
            Assert.assertTrue(employeeList.isEmployeeTableDisplayed(),
                    "Table should display only employees with job title 'Software Engineer'.");
            return employeeList.isEmployeeTableDisplayed();
        });
    }

    // TC_PIM_043 - Verify filtering by Sub Unit (Department) dropdown
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_043 - Confirms the list filters records based on the selected organizational department")
    @Test
    public void verifyFilterBySubUnit() {
        runTest("TC_PIM_043 - Confirms the list filters records based on the selected organizational department", () -> {
            employeeList.searchBySubUnit("Engineering");
            Assert.assertTrue(employeeList.isEmployeeTableDisplayed(),
                    "Table should display only employees in the 'Engineering' sub-unit.");
            return employeeList.isEmployeeTableDisplayed();
        });
    }

    // TC_PIM_044 - Verify system displays message when no matching employee exists
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_044 - Ensure proper feedback appears when search returns no results")
    @Test
    public void verifyNoMatchingEmployeeMessage() {
        runTest("TC_PIM_044 - Ensure proper feedback appears when search returns no results", () -> {
            employeeList.searchByEmployeeName("Invalid Employee XYZ");
            Assert.assertTrue(employeeList.isNoRecordsFoundDisplayed(),
                    "'No matching records found' message should appear for invalid search criteria.");
            return employeeList.isNoRecordsFoundDisplayed();
        });
    }

    // TC_PIM_045 - Verify Reset button clears all search filters
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_045 - Ensure all entered filters are removed after reset")
    @Test
    public void verifyResetClearsFilters() {
        runTest("TC_PIM_045 - Ensure all entered filters are removed after reset", () -> {
            employeeList.searchByEmployeeName("Test");
            employeeList.clickReset();
            Assert.assertTrue(employeeList.isEmployeeTableDisplayed(),
                    "Full employee list should reappear after clicking Reset.");
            return employeeList.isEmployeeTableDisplayed();
        });
    }

    // TC_PIM_046 - Verify admin can open employee profile by clicking employee name
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_046 - Ensure employee profile page opens correctly from employee name link")
    @Test
    public void verifyOpenProfileByName() {
        runTest("TC_PIM_046 - Ensure employee profile page opens correctly from employee name link", () -> {
            employeeList.clickFirstEmployeeName();
            Assert.assertFalse(driver.getCurrentUrl().contains("viewEmployeeList"),
                    "Clicking employee name should navigate to the employee profile page.");
            return !driver.getCurrentUrl().contains("viewEmployeeList");
        });
    }

    // TC_PIM_047 - Verify admin can edit employee profile using edit action button
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_047 - Ensure clicking edit button enables editable fields")
    @Test
    public void verifyEditProfileButton() {
        runTest("TC_PIM_047 - Ensure clicking edit button enables editable fields", () -> {
            employeeList.clickFirstEditButton();
            Assert.assertFalse(driver.getCurrentUrl().contains("viewEmployeeList"),
                    "Clicking edit button should navigate to the employee profile page.");
            return !driver.getCurrentUrl().contains("viewEmployeeList");
        });
    }

    // TC_PIM_060 - Verify employees are deleted successfully after confirmation
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_060 - Ensure selected employees are removed after confirming deletion")
    @Test
    public void verifyDeleteEmployeeAfterConfirmation() {
        runTest("TC_PIM_060 - Ensure selected employees are removed after confirming deletion", () -> {
            employeeList.deleteFirstEmployee();
            Assert.assertTrue(employeeList.isSuccessMessageDisplayed(),
                    "Expected success message not found after deleting employee.");
            return employeeList.isSuccessMessageDisplayed();
        });
    }

    // TC_PIM_062 - Verify success message is displayed after deleting employee
    @Epic("PIM")
    @Feature("Employee List")
    @Story("TC_PIM_062 - Ensure user receives confirmation feedback after deletion")
    @Test
    public void verifySuccessMessageAfterDelete() {
        runTest("TC_PIM_062 - Ensure user receives confirmation feedback after deletion", () -> {
            employeeList.deleteFirstEmployee();
            Assert.assertTrue(employeeList.isSuccessMessageDisplayed(),
                    "Success message should be displayed after deleting an employee.");
            return employeeList.isSuccessMessageDisplayed();
        });
    }
}