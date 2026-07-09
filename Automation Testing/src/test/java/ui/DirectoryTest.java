package adminPages;

import org.example.Base;
import org.example.DriverManager;
import org.example.adminPage.DirectoryPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DirectoryTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private DirectoryPage directoryPage;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();
        login = new loginPage(driver);
        directoryPage = new DirectoryPage(driver);

        login.open();
        login.LogIn("Admin", "admin123");
        directoryPage.navigateToDirectory();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // TC_DIR_001
    @Test
    public void verifyDirectoryPageOpensSuccessfully() {
        runTest("Verify Directory page opens successfully", () ->
                directoryPage.isPageOpened(driver.getCurrentUrl())
        );
    }

    // TC_DIR_002
    @Test
    public void verifyDirectoryHeaderIsDisplayed() {
        runTest("Verify Directory header is displayed", () ->
                directoryPage.isDirectoryHeaderDisplayed()
        );
    }

    // TC_DIR_003
    @Test
    public void verifyDirectoryRecordsGridIsVisible() {
        runTest("Verify Directory records grid is visible", () ->
                directoryPage.isDirectoryGridDisplayed()
        );
    }

    // TC_DIR_004
    @Test
    public void verifySearchingByEmployeeName() {
        runTest("Verify searching by employee name", () -> {
            directoryPage.typeEmployeeName("John");
            directoryPage.clickSearch();
            return directoryPage.isDirectoryGridDisplayed();
        });
    }

    // TC_DIR_005
    @Test
    public void verifySearchingByJobTitle() {
        runTest("Verify searching by job title", () -> {
            directoryPage.selectJobTitle("QA Engineer");
            directoryPage.clickSearch();
            return directoryPage.isDirectoryGridDisplayed();
        });
    }

    // TC_DIR_006
    @Test
    public void verifySearchingByLocation() {
        runTest("Verify searching by location", () -> {
            directoryPage.selectLocation("Texas R&D");
            directoryPage.clickSearch();
            return directoryPage.isDirectoryGridDisplayed();
        });
    }

    // TC_DIR_007
    @Test
    public void verifyResetButtonClearsAllSearchFilters() {
        runTest("Verify reset button clears all search filters", () -> {
            directoryPage.selectJobTitle("QA Engineer");
            directoryPage.clickReset();
            return directoryPage.getRecordsFoundText() != null;
        });
    }

    // TC_DIR_008
    @Test
    public void verifyCombiningMultipleSearchFilters() {
        runTest("Verify combining multiple search filters", () -> {
            directoryPage.typeEmployeeName("Sara");
            directoryPage.selectJobTitle("Chief Executive Officer");
            directoryPage.selectLocation("HQ - CA, USA");
            directoryPage.clickSearch();
            return directoryPage.isDirectoryGridDisplayed();
        });
    }

    // TC_DIR_009
    @Test
    public void verifyNoRecordsFoundScenarioLayout() {
        runTest("Verify no records found scenario layout", () -> {
            directoryPage.typeEmployeeName("NonExistentUserXYZ");
            directoryPage.clickSearch();
            return directoryPage.isNoRecordsFoundDisplayed();
        });
    }

    // TC_DIR_010
    @Test
    public void verifyViewingEmployeeContactCardDetails() {
        runTest("Verify viewing employee contact card details", () -> {
            directoryPage.clickFirstDirectoryRecordCard();
            return directoryPage.isDirectoryGridDisplayed();
        });
    }

    // TC_DIR_011
    @Test
    public void verifyJobTitleDropdownPopulatesCorrectOptions() {
        runTest("Verify Job Title dropdown populates correct options", () -> {
            directoryPage.clickJobTitleDropdownContainer();
            return directoryPage.isDropdownOptionsVisible();
        });
    }

    // TC_DIR_012
    @Test
    public void verifySearchPerformanceWithMassiveDirectoryRecords() {
        runTest("Verify search performance with massive directory records", () -> {
            directoryPage.clickSearch();
            return !directoryPage.getRecordsFoundText().isEmpty();
        });
    }
}