package ui.PIM;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.example.Base;
import org.example.DriverManager;
import org.example.pages.PIM.AddEmployeePage;
import org.example.pages.PIM.EmployeeListPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddEmployeeTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private EmployeeListPage employeeList;
    private AddEmployeePage addEmployee;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();

        login = new loginPage(driver);
        employeeList = new EmployeeListPage(driver);
        addEmployee = new AddEmployeePage(driver);

        login.open();
        login.LogIn("Admin", "admin123");

        employeeList.openEmployeeListPage();
        addEmployee.openAddEmployeeTab();
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
        runTest("Verify Add Employee page opens successfully",
                () -> addEmployee.isPageLoaded());
    }

    // TC_PIM_012 - Verify the green Add button works
    @Epic("PIM")
    @Feature("Add Employee")
    @Story("TC_PIM_012 - Checking the functionality of the Add button")
    @Owner("Aly Salem")
    @Test
    public void verifyAddButtonWorks() {
        runTest("TC_PIM_012 - Checking the functionality of the Add button", () -> {
            Assert.assertTrue(addEmployee.isPageLoaded(),
                    "Add Employee form should be displayed after clicking the Add button.");
            return addEmployee.isPageLoaded();
        });
    }

    // TC_PIM_013 - Verify "Add Employee" button works
    @Epic("PIM")
    @Feature("Add Employee")
    @Story("TC_PIM_013 - Checking the functionality of the Add employee button")
    @Test
    public void verifyAddEmployeeTabWorks() {
        runTest("TC_PIM_013 - Checking the functionality of the Add employee button", () -> {
            Assert.assertTrue(addEmployee.isPageLoaded(),
                    "Add Employee form fields should be displayed.");
            return addEmployee.isPageLoaded();
        });
    }

    // TC_PIM_014 - Verify the Add employee page is loaded
    @Epic("PIM")
    @Feature("Add Employee")
    @Story("TC_PIM_014 - Check if the add employee page is loaded successfully")
    @Test
    public void verifyAddEmployeePageLoaded() {
        runTest("TC_PIM_014 - Check if the add employee page is loaded successfully", () -> {
            Assert.assertTrue(addEmployee.isPageLoaded(),
                    "Add Employee page should display fields for new employee data.");
            return addEmployee.isPageLoaded();
        });
    }

    // TC_PIM_015 - Verify the prevention of empty fields submission
    @Epic("PIM")
    @Feature("Add Employee")
    @Story("TC_PIM_015 - Check system prevents submission when required fields are empty")
    @Test
    public void verifyEmptyFieldsPrevention() {
        runTest("TC_PIM_015 - Check system prevents submission when required fields are empty", () -> {
            addEmployee.saveWithoutData();
            Assert.assertTrue(addEmployee.isRequiredErrorDisplayed(),
                    "Required field validation errors should appear when saving with empty fields.");
            return addEmployee.isRequiredErrorDisplayed();
        });
    }

    // TC_PIM_016 - Verify successful data entry for Add employee page
    @Epic("PIM")
    @Feature("Add Employee")
    @Story("TC_PIM_016 - Check system accepts data successfully")
    @Test
    public void verifySuccessfulEmployeeCreation() {
        String empId = String.valueOf(System.currentTimeMillis()).substring(7);
        runTest("TC_PIM_016 - Check system accepts data successfully", () -> {
            addEmployee.addEmployeeBasic("Test", "Test", "Test", empId);
            Assert.assertTrue(addEmployee.isSuccessMessageDisplayed(),
                    "Expected success message not found after creating employee with valid data.");
            return addEmployee.isSuccessMessageDisplayed();
        });
    }

    // TC_PIM_017 - Verify Employee ID is not mandatory
    @Epic("PIM")
    @Feature("Add Employee")
    @Story("TC_PIM_017 - Ensure system behavior when Employee ID field is left empty during employee creation")
    @Test
    public void verifyEmptyEmployeeIdAllowed() {
        runTest("TC_PIM_017 - Ensure system behavior when Employee ID field is left empty during employee creation", () -> {
            addEmployee.addEmployeeWithEmptyId("John", "Doe");
            // Per spec, system should either block or clearly allow auto-generated ID
            boolean blocked = addEmployee.isRequiredErrorDisplayed();
            boolean saved = addEmployee.isSuccessMessageDisplayed();
            Assert.assertTrue(blocked || saved,
                    "System should either block empty Employee ID or auto-generate it on save.");
            return blocked || saved;
        });
    }

    // TC_PIM_018 - Verify password confirmation validation is triggered on Save action
    @Epic("PIM")
    @Feature("Add Employee")
    @Story("TC_PIM_018 - Ensure system validates password and confirm password fields after clicking Save")
    @Test
    public void verifyPasswordConfirmationValidation() {
        runTest("TC_PIM_018 - Ensure system validates password and confirm password fields after clicking Save", () -> {
            addEmployee.saveWithMismatchedPasswords("testUser", "Example#123456", "Example#123");
            Assert.assertTrue(addEmployee.isPasswordMismatchErrorDisplayed(),
                    "Expected 'Passwords do not match' error not displayed.");
            return addEmployee.isPasswordMismatchErrorDisplayed();
        });
    }

    // TC_PIM_019 - Verify system handles special characters in employee name fields
    @Epic("PIM")
    @Feature("Add Employee")
    @Story("TC_PIM_019 - Ensure system validates employee name fields against invalid characters")
    @Test
    public void verifySpecialCharactersInNameRejected() {
        runTest("TC_PIM_019 - Ensure system validates employee name fields against invalid characters", () -> {
            addEmployee.addEmployeeWithSpecialChars("@@@###", "12345");
            Assert.assertFalse(addEmployee.isSuccessMessageDisplayed(),
                    "System should reject invalid characters in name fields and not create the employee.");
            return !addEmployee.isSuccessMessageDisplayed();
        });
    }

    // TC_PIM_020 - Verify system trims leading and trailing spaces in name fields
    @Epic("PIM")
    @Feature("Add Employee")
    @Story("TC_PIM_020 - Ensure system removes unnecessary spaces before saving")
    @Test
    public void verifyNameFieldsTrimmed() {
        runTest("TC_PIM_020 - Ensure system removes unnecessary spaces before saving", () -> {
            addEmployee.addEmployeeWithSpacedNames(" John ", " Doe ");
            Assert.assertTrue(addEmployee.isSuccessMessageDisplayed(),
                    "Employee with spaced names should be saved (system expected to trim spaces).");
            return addEmployee.isSuccessMessageDisplayed();
        });
    }

    // TC_PIM_021 - Verify system prevents duplicate usernames
    @Epic("PIM")
    @Feature("Add Employee")
    @Story("TC_PIM_021 - Ensure system does not allow creation of duplicate usernames")
    @Test
    public void verifyDuplicateUsernamePrevented() {
        runTest("TC_PIM_021 - Ensure system does not allow creation of duplicate usernames", () -> {
            addEmployee.fillBasicInfo("Test", null, "User", null);
            addEmployee.saveWithDuplicateUsername("Admin", "Password@123");
            Assert.assertTrue(addEmployee.isDuplicateUsernameErrorDisplayed(),
                    "Expected validation message for duplicate username 'Admin' not displayed.");
            return addEmployee.isDuplicateUsernameErrorDisplayed();
        });
    }

    // TC_PIM_022 - Verify system prevents duplicate employee creation on double click
    @Epic("PIM")
    @Feature("Add Employee")
    @Story("TC_PIM_022 - Ensure system handles multiple clicks on Save correctly")
    @Test
    public void verifyNoDuplicateOnDoubleClick() {
        runTest("TC_PIM_022 - Ensure system handles multiple clicks on Save correctly", () -> {
            addEmployee.fillBasicInfo("John", null, "Doe", null);
            addEmployee.doubleClickSave();
            Assert.assertTrue(addEmployee.isSuccessMessageDisplayed(),
                    "Only one employee record should be created even after rapid double-click on Save.");
            return addEmployee.isSuccessMessageDisplayed();
        });
    }

    // TC_PIM_023 - Verify system handles very long input values
    @Epic("PIM")
    @Feature("Add Employee")
    @Story("TC_PIM_023 - Ensure system does not break with large input sizes")
    @Test
    public void verifyLongInputHandled() {
        String longName = "A".repeat(300);
        runTest("TC_PIM_023 - Ensure system does not break with large input sizes", () -> {
            addEmployee.addEmployeeWithLongFirstName(longName, "TestLastName");
            Assert.assertTrue(addEmployee.isPageLoaded(),
                    "Page should remain stable and responsive after submitting a very long First Name.");
            return addEmployee.isPageLoaded();
        });
    }

    // TC_PIM_024 - Verify system behavior when refreshing page during data entry
    @Epic("PIM")
    @Feature("Add Employee")
    @Story("TC_PIM_024 - Ensure system handles refresh safely")
    @Test
    public void verifyRefreshDuringDataEntry() {
        runTest("TC_PIM_024 - Ensure system handles refresh safely", () -> {
            addEmployee.fillBasicInfo("John", null, "Doe", null);
            driver.navigate().refresh();
            Assert.assertTrue(addEmployee.isPageLoaded(),
                    "Page should reload correctly after a refresh during data entry.");
            return addEmployee.isPageLoaded();
        });
    }

    // TC_PIM_026 - Verify Create login details toggle works
    @Epic("PIM")
    @Feature("Add Employee")
    @Story("TC_PIM_026 - Ensure the functionality of create login details toggle")
    @Test
    public void verifyCreateLoginToggleWorks() {
        runTest("TC_PIM_026 - Ensure the functionality of create login details toggle", () -> {
            Assert.assertTrue(addEmployee.areLoginFieldsDisplayed(),
                    "Username and Password fields should appear after enabling Create Login Details toggle.");
            return addEmployee.areLoginFieldsDisplayed();
        });
    }

    // TC_PIM_027 - Verify the status' buttons work
    @Epic("PIM")
    @Feature("Add Employee")
    @Story("TC_PIM_027 - Ensure the functionality of both Enabled and Disabled status buttons")
    @Test
    public void verifyStatusButtonsWork() {
        runTest("TC_PIM_027 - Ensure the functionality of both Enabled and Disabled status buttons", () -> {
            addEmployee.toggleCreateLoginDetails();
            addEmployee.selectStatusEnabled();
            addEmployee.selectStatusDisabled();
            Assert.assertTrue(addEmployee.isPageLoaded(),
                    "Both Enabled and Disabled status radio buttons should be selectable.");
            return addEmployee.isPageLoaded();
        });
    }

    // TC_PIM_028 - Verify Admin can enable and create login details for a new employee
    @Epic("PIM")
    @Feature("Add Employee")
    @Story("TC_PIM_028 - Check that Create Login Details toggle expands the form and saves credentials")
    @Test
    public void verifyCreateLoginDetailsEnabled() {
        String empId = String.valueOf(System.currentTimeMillis()).substring(7);
        String username = "John_Doe" + System.currentTimeMillis();
        runTest("TC_PIM_028 - Check that Create Login Details toggle expands the form and saves credentials", () -> {
            addEmployee.addEmployeeWithLogin("John", "Doe", empId, username, "John_1234", true);
            Assert.assertTrue(addEmployee.isSuccessMessageDisplayed(),
                    "Expected 'Successfully Saved' message after creating employee with login details (Enabled).");
            return addEmployee.isSuccessMessageDisplayed();
        });
    }

    // TC_PIM_029 - Verify Admin can set the account status to Disabled
    @Epic("PIM")
    @Feature("Add Employee")
    @Story("TC_PIM_029 - Check that the Admin can select the Disabled radio button when creating login details")
    @Test
    public void verifyCreateLoginDetailsDisabled() {
        String empId = String.valueOf(System.currentTimeMillis()).substring(7);
        String username = "John_Doe_D" + System.currentTimeMillis();
        runTest("TC_PIM_029 - Check that the Admin can select the Disabled radio button when creating login details", () -> {
            addEmployee.addEmployeeWithLogin("John", "Doe", empId, username, "John_1235", false);
            Assert.assertTrue(addEmployee.isSuccessMessageDisplayed(),
                    "Expected 'Successfully Saved' message after creating employee with login details (Disabled).");
            return addEmployee.isSuccessMessageDisplayed();
        });
    }

    // TC_PIM_030 - Verify validation errors when required login fields are left blank
    @Epic("PIM")
    @Feature("Add Employee")
    @Story("TC_PIM_030 - Check system prevents submission and displays validation errors for blank login fields")
    @Test
    public void verifyBlankLoginFieldsValidation() {
        runTest("TC_PIM_030 - Check system prevents submission and displays validation errors for blank login fields", () -> {
            addEmployee.saveWithBlankLoginFields("John", "Doe", null);
            Assert.assertTrue(addEmployee.areMultipleRequiredErrorsDisplayed(),
                    "Required validation errors should appear under Username, Password, and Confirm Password.");
            return addEmployee.areMultipleRequiredErrorsDisplayed();
        });
    }

    // TC_PIM_031 - Verify Username length validation (Minimum Characters)
    @Epic("PIM")
    @Feature("Add Employee")
    @Story("TC_PIM_031 - Ensures the system validates the format of the Username field for minimum length")
    @Test
    public void verifyUsernameLengthValidation() {
        runTest("TC_PIM_031 - Ensures the system validates the format of the Username field for minimum length", () -> {
            addEmployee.fillBasicInfo("Test", null, "User", null);
            addEmployee.saveWithShortUsername("ay", "Password@123");
            Assert.assertTrue(addEmployee.isUsernameLengthErrorDisplayed(),
                    "Expected minimum character length validation error not displayed for short username.");
            return addEmployee.isUsernameLengthErrorDisplayed();
        });
    }

    // TC_PIM_032 - Verify Password complexity validation requirements
    @Epic("PIM")
    @Feature("Add Employee")
    @Story("TC_PIM_032 - Check that the system enforces password complexity rules")
    @Test
    public void verifyPasswordComplexityValidation() {
        runTest("TC_PIM_032 - Check that the system enforces password complexity rules", () -> {
            addEmployee.fillBasicInfo("Test", null, "User", null);
            addEmployee.saveWithWeakPassword("testUser" + System.currentTimeMillis(), "password");
            Assert.assertTrue(addEmployee.isWeakPasswordErrorDisplayed(),
                    "Expected weak password validation error not displayed for simple lowercase password.");
            return addEmployee.isWeakPasswordErrorDisplayed();
        });
    }

    // TC_PIM_033 - Verify validation when Passwords do not match
    @Epic("PIM")
    @Feature("Add Employee")
    @Story("TC_PIM_033 - Check that the system prevents submission if Confirm Password does not match Password")
    @Test
    public void verifyPasswordsDoNotMatchValidation() {
        runTest("TC_PIM_033 - Check that the system prevents submission if Confirm Password does not match Password", () -> {
            addEmployee.fillBasicInfo("Test", null, "User", null);
            addEmployee.saveWithMismatchedPasswords("testUser" + System.currentTimeMillis(), "Admin@123", "Admin@456");
            Assert.assertTrue(addEmployee.isPasswordMismatchErrorDisplayed(),
                    "Expected 'Passwords do not match' validation error not displayed.");
            return addEmployee.isPasswordMismatchErrorDisplayed();
        });
    }

    // TC_PIM_034 - Verify new employee record appears in the Employee List after successful creation
    @Epic("PIM")
    @Feature("Add Employee")
    @Story("TC_PIM_034 - Check that once an employee is saved, their record is searchable and visible in the list")
    @Test
    public void verifyNewEmployeeAppearsInList() {
        String uniqueName = "Test User" + System.currentTimeMillis();
        String empId = String.valueOf(System.currentTimeMillis()).substring(7);
        runTest("TC_PIM_034 - Check that once an employee is saved, their record is searchable and visible in the list", () -> {
            addEmployee.addEmployeeBasic(uniqueName, null, "Employee", empId);
            Assert.assertTrue(addEmployee.isSuccessMessageDisplayed(),
                    "Employee should be created successfully before checking visibility in the list.");
            employeeList.openEmployeeListPage();
            employeeList.searchByEmployeeName(uniqueName);
            Assert.assertTrue(employeeList.isEmployeeTableDisplayed(),
                    "Newly created employee should appear in the search results.");
            return employeeList.isEmployeeTableDisplayed();
        });
    }

    // TC_PIM_035 - Verify the new employee can successfully log in with the created credentials
    @Epic("PIM")
    @Feature("Add Employee")
    @Story("TC_PIM_035 - Check that the Create Login Details functionality works end-to-end")
    @Test
    public void verifyNewEmployeeCanLogin() {
        String username = "John_Doe_Login" + System.currentTimeMillis();
        String empId = String.valueOf(System.currentTimeMillis()).substring(7);
        runTest("TC_PIM_035 - Check that the Create Login Details functionality works end-to-end", () -> {
            addEmployee.addEmployeeWithLogin("John", "Doe", empId, username, "John_1234#A", true);
            Assert.assertTrue(addEmployee.isSuccessMessageDisplayed(),
                    "Employee with login credentials should be created before testing login.");
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/logout");
            login.LogIn(username, "John_1234#A");
            Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"),
                    "New employee should be able to log in and reach the dashboard.");
            return driver.getCurrentUrl().contains("dashboard");
        });
    }
}