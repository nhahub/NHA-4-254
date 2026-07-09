package adminPages;

import org.example.Base;
import org.example.DriverManager;
import org.example.adminPage.MyInfoPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyInfoTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private MyInfoPage myInfoPage;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();
        login = new loginPage(driver);
        myInfoPage = new MyInfoPage(driver);

        login.open();
        login.LogIn("Admin", "admin123");
        myInfoPage.navigateToMyInfo();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // TC_MYINFO_001
    @Test
    public void verifyMyInfoPageOpensSuccessfully() {
        runTest("Verify My Info page opens successfully", () ->
                myInfoPage.isPageOpened(driver.getCurrentUrl())
        );
    }

    // TC_MYINFO_002
    @Test
    public void verifyPersonalDetailsDisplay() {
        runTest("Verify personal details display", () ->
                myInfoPage.isPersonalDetailsHeaderDisplayed()
        );
    }

    // TC_MYINFO_003
    @Test
    public void validatePersonalDetailsMandatoryFields() {
        runTest("Validate personal details mandatory fields", () -> {
            myInfoPage.clearFirstName();
            myInfoPage.clickSavePersonalDetails();
            return myInfoPage.isRequiredErrorDisplayed();
        });
    }

    // TC_MYINFO_004
    @Test
    public void verifyUpdateEmployeeNameDetails() {
        runTest("Verify update employee name details", () -> {
            myInfoPage.updateNameDetails("Automation", "QA", "Tester");
            myInfoPage.clickSavePersonalDetails();
            return myInfoPage.isSuccessMessageDisplayed();
        });
    }

    // TC_MYINFO_005
    @Test
    public void verifyUpdateIdentificationDetails() {
        runTest("Verify update identification details", () -> {
            myInfoPage.updateIdentificationDetails("EMP123", "OID456", "DL789");
            myInfoPage.clickSavePersonalDetails();
            return myInfoPage.isSuccessMessageDisplayed();
        });
    }

    // TC_MYINFO_006
    @Test
    public void verifyUpdateLicenseExpiryDate() {
        runTest("Verify update license expiry date", () -> {
            myInfoPage.updateLicenseExpiry("2028-12-31");
            myInfoPage.clickSavePersonalDetails();
            return myInfoPage.isSuccessMessageDisplayed();
        });
    }

    // TC_MYINFO_007
    @Test
    public void verifyUpdateTaxIdentifiersManagement() {
        runTest("Verify update tax identifiers management", () -> {
            myInfoPage.updateTaxIdentifiers("SSN-123", "SIN-456");
            myInfoPage.clickSavePersonalDetails();
            return myInfoPage.isSuccessMessageDisplayed();
        });
    }

    // TC_MYINFO_008
    @Test
    public void verifyUpdateNationalityDetailsManagement() {
        runTest("Verify update nationality details management", () -> {
            myInfoPage.selectNationality("Egyptian");
            myInfoPage.clickSavePersonalDetails();
            return myInfoPage.isSuccessMessageDisplayed();
        });
    }

    // TC_MYINFO_009
    @Test
    public void verifyUpdateMaritalStatusManagement() {
        runTest("Verify update marital status management", () -> {
            myInfoPage.selectMaritalStatus("Married");
            myInfoPage.clickSavePersonalDetails();
            return myInfoPage.isSuccessMessageDisplayed();
        });
    }

    // TC_MYINFO_010
    @Test
    public void verifyUpdateDateOfBirthManagement() {
        runTest("Verify update date of birth management", () -> {
            myInfoPage.updateDateOfBirth("1995-05-15");
            myInfoPage.clickSavePersonalDetails();
            return myInfoPage.isSuccessMessageDisplayed();
        });
    }

    // TC_MYINFO_011
    @Test
    public void verifyUpdateGenderDetailsManagement() {
        runTest("Verify update gender details management", () -> {
            myInfoPage.selectGender("Male");
            myInfoPage.clickSavePersonalDetails();
            return myInfoPage.isSuccessMessageDisplayed();
        });
    }

    // TC_MYINFO_012
    @Test
    public void verifyUpdateMilitaryServiceDetails() {
        runTest("Verify update military service details", () -> {
            myInfoPage.updateMilitaryService("Completed");
            myInfoPage.clickSavePersonalDetails();
            return myInfoPage.isSuccessMessageDisplayed();
        });
    }

    // TC_MYINFO_013
    @Test
    public void verifyUpdateSmokerStatusInformation() {
        runTest("Verify update smoker status information", () -> {
            myInfoPage.toggleSmokerStatus();
            myInfoPage.clickSavePersonalDetails();
            return myInfoPage.isSuccessMessageDisplayed();
        });
    }

    // TC_MYINFO_014
    @Test
    public void verifyNavigatingToContactDetailsSubmodule() {
        runTest("Verify navigating to contact details submodule", () -> {
            myInfoPage.navigateToContactDetails();
            return driver.getCurrentUrl().contains("contactDetails");
        });
    }

    // TC_MYINFO_015
    @Test
    public void verifyNavigatingToEmergencyContactsSubmodule() {
        runTest("Verify navigating to emergency contacts submodule", () -> {
            myInfoPage.navigateToEmergencyContacts();
            return driver.getCurrentUrl().contains("emergencyContacts");
        });
    }

    // TC_MYINFO_016
    @Test
    public void verifyNavigatingToDependentsSubmodule() {
        runTest("Verify navigating to dependents submodule", () -> {
            myInfoPage.navigateToDependents();
            return driver.getCurrentUrl().contains("viewDependents");
        });
    }

    // TC_MYINFO_017
    @Test
    public void verifyNavigatingToImmigrationSubmodule() {
        runTest("Verify navigating to immigration submodule", () -> {
            myInfoPage.navigateToImmigration();
            return driver.getCurrentUrl().contains("viewImmigration");
        });
    }

    // TC_MYINFO_018
    @Test
    public void verifyNavigatingToQualificationsAndMemberships() {
        runTest("Verify navigating to qualifications and memberships", () -> {
            myInfoPage.navigateToQualifications();
            boolean qualificationsOpened = driver.getCurrentUrl().contains("viewQualifications");
            myInfoPage.navigateToMemberships();
            boolean membershipsOpened = driver.getCurrentUrl().contains("viewMemberships");
            return qualificationsOpened && membershipsOpened;
        });
    }
}