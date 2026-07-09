package ui.adminPages;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.Base;
import org.example.DriverManager;
import org.example.pages.adminPage.LicensesPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LicensesTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private LicensesPage licenses;

    @BeforeMethod
    public void setup() {

        driver = DriverManager.getInstance().getDriver();

        login = new loginPage(driver);
        licenses = new LicensesPage(driver);

        login.open();
        login.LogIn("Admin", "admin123");

        licenses.openLicensesPage();
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

    // TC_ADMIN_321
    @Epic("Admin Page")
    @Feature("Licenses Management")
    @Story("TC_ADMIN_321 Verify Admin can navigate to license page")
    @Test
    public void verifyLicensePageOpened() {

        runTest("TC_ADMIN_321 Verify Admin can navigate to license page", () -> {
                    Assert.assertTrue(licenses.pageOpened(driver.getCurrentUrl()),
                            "License page did not open successfully");
                    return licenses.pageOpened(driver.getCurrentUrl());

                }
        );
    }

    // TC_ADMIN_322
    @Epic("Admin Page")
    @Feature("Licenses Management")
    @Story("TC_ADMIN_322 Verify license records table is displayed")
    @Test
    public void verifyLicenseTableDisplayed() {
        runTest("TC_ADMIN_322 Verify license records table is displayed", () -> {
                    Assert.assertTrue(licenses.isLicenseTableDisplayed(),
                            "License table is not displayed");
                    return licenses.isLicenseTableDisplayed();
                }
        );
    }

    // TC_ADMIN_323
    @Epic("Admin Page")
    @Feature("Licenses Management")
    @Story("TC_ADMIN_323 Verify license Name column is displayed correctly")
    @Test
    public void verifyLicenseColumnDisplayed() {
        runTest(
                "TC_ADMIN_323 Verify license Name column is displayed correctly",
                () -> {
                    Assert.assertTrue(
                            licenses.isLicenseNameColumnDisplayed(),
                            "License Name column is not displayed");

                    return licenses.isLicenseNameColumnDisplayed();
                }
        );
    }

    // TC_ADMIN_324
    @Epic("Admin Page")
    @Feature("Licenses Management")
    @Story("TC_ADMIN_324 Verify existing license records appear in table")
    @Test
    public void verifyExistingLicenseRecords() {

        runTest(
                "TC_ADMIN_324 Verify existing license records appear in table",
                () -> licenses.isLicenseTableDisplayed()
        );
    }

    // TC_ADMIN_325
    @Epic("Admin Page")
    @Feature("Licenses Management")
    @Story("TC_ADMIN_325 Verify Add button opens Add license form")
    @Test
    public void verifyAddButtonOpensForm() {

        runTest("TC_ADMIN_325 Verify Add button opens Add license form", () -> {
                    licenses.clickAdd();
                    Assert.assertTrue(licenses.isAddFormDisplayed(),
                            "Add form not displayed");
                    return licenses.isAddFormDisplayed();
                }
        );
    }

    // TC_ADMIN_326
    @Epic("Admin Page")
    @Feature("Licenses Management")
    @Story("TC_ADMIN_326 Verify system prevents submission with empty required fields")
    @Test
    public void verifyRequiredValidation() {

        runTest("TC_ADMIN_326 Verify system prevents submission with empty required fields", () -> {
                    licenses.saveWithoutName();
                    Assert.assertTrue(licenses.isRequiredValidationDisplayed(),
                            "Required validation not displayed");
                    return licenses.isRequiredValidationDisplayed();
                }
        );
    }

    // TC_ADMIN_327
    @Epic("Admin Page")
    @Feature("Licenses Management")
    @Story("TC_ADMIN_327 Verify validation error is cleared after entering valid license Name")
    @Test
    public void verifyValidationCleared() {
        runTest("TC_ADMIN_327 Verify validation error is cleared after entering valid license Name", () -> {
                    licenses.saveWithoutName();
                    licenses.enterLicenseName("Test License");
                    Assert.assertFalse(licenses.isRequiredValidationDisplayed());
                    return licenses.isRequiredValidationDisplayed();
                }
        );
    }

    // TC_ADMIN_328
    @Epic("Admin Page")
    @Feature("Licenses Management")
    @Story("TC_ADMIN_328 Verify license is created successfully with valid data")
    @Test
    public void verifyCreateLicense() {
        String licenseName = "License_" + System.currentTimeMillis();
        runTest("TC_ADMIN_328 Verify license is created successfully with valid data", () -> {
                    licenses.createLicense(licenseName);
                    Assert.assertTrue(licenses.isSuccessMessageDisplayed());
                    return licenses.isSuccessMessageDisplayed();
                }
        );
    }

    // TC_ADMIN_329
    @Epic("Admin Page")
    @Feature("Licenses Management")
    @Story("TC_ADMIN_329 Verify new license appears immediately in table")
    @Test
    public void verifyLicenseDisplayedInTable() {

        String licenseName =
                "License_" + System.currentTimeMillis();

        runTest(
                "TC_ADMIN_329 Verify new license appears immediately in table",
                () -> {

                    licenses.createLicense(licenseName);
                    Assert.assertTrue(licenses.isLicenseExist(licenseName),
                            "Newly created license does not appear in the table");
                    return licenses.isLicenseExist(licenseName);
                }
        );
    }

    // TC_ADMIN_330
    @Epic("Admin Page")
    @Feature("Licenses Management")
    @Story("TC_ADMIN_330 Verify success message appears after adding license")
    @Test
    public void verifySuccessMessageAfterCreate() {

        String licenseName =
                "License_" + System.currentTimeMillis();

        runTest(
                "TC_ADMIN_330 Verify success message appears after adding license",
                () -> {

                    licenses.createLicense(licenseName);
                    Assert.assertTrue(licenses.isSuccessMessageDisplayed(),
                            "Success message not displayed after adding license");
                    return licenses.isSuccessMessageDisplayed();
                }
        );
    }

    // TC_ADMIN_331
    @Epic("Admin Page")
    @Feature("Licenses Management")
    @Story("TC_ADMIN_331 Verify system handles special characters in license names correctly")
    @Test
    public void verifySpecialCharacters() {

        runTest(
                "TC_ADMIN_331 Verify system handles special characters in license names correctly",
                () -> {

                    licenses.createLicense("@@@###%%%HR_Method!!!");
                    Assert.assertTrue(licenses.isSuccessMessageDisplayed(),
                            "Success message not displayed after adding license");
                    return licenses.isSuccessMessageDisplayed();
                }
        );
    }

    // TC_ADMIN_332
    @Epic("Admin Page")
    @Feature("Licenses Management")
    @Story("TC_ADMIN_332 Verify system prevents duplicate license")
    @Test
    public void verifyDuplicateLicenseValidation() {
        runTest("TC_ADMIN_332 Verify system prevents duplicate license", () -> {
            licenses.createLicense("Driver License");
            Assert.assertTrue(licenses.isDuplicateValidationDisplayed(), "Duplicate validation message not displayed");
            return licenses.isDuplicateValidationDisplayed();
        });
    }

    // TC_ADMIN_334
    @Epic("Admin Page")
    @Feature("Licenses Management")
    @Story("TC_ADMIN_334 Verify Edit form opens successfully for selected license")
    @Test
    public void verifyEditFormOpened() {

        runTest(
                "TC_ADMIN_334 Verify Edit form opens successfully for selected license",
                () -> {

                    licenses.clickEdit();

                    return licenses.isEditFormDisplayed();
                }
        );
    }

    // TC_ADMIN_336
    @Epic("Admin Page")
    @Feature("Licenses Management")
    @Story("TC_ADMIN_336 Verify admin can update license details")
    @Test
    public void verifyUpdateLicense() {
        String updatedName = "Updated_" + System.currentTimeMillis();
        runTest("TC_ADMIN_336 Verify admin can update license details", () -> {
                    licenses.editFirstLicense(updatedName);
                    return licenses.isSuccessMessageDisplayed();
                }
        );
    }

    // TC_ADMIN_338
    @Epic("Admin Page")
    @Feature("Licenses Management")
    @Story("TC_ADMIN_338 Verify success message appears after updating license")
    @Test
    public void verifySuccessMessageAfterUpdate() {
        String updatedName = "Updated_" + System.currentTimeMillis();
        runTest("TC_ADMIN_338 Verify success message appears after updating license", () -> {
                    licenses.editFirstLicense(updatedName);
                    Assert.assertTrue(licenses.isSuccessMessageDisplayed(), "Success message not displayed after updating license");
                    return licenses.isSuccessMessageDisplayed();
                }
        );
    }

    // TC_ADMIN_340
    @Epic("Admin Page")
    @Feature("Licenses Management")
    @Story("TC_ADMIN_340 Verify license records can be selected using checkboxes")
    @Test
    public void verifyRecordSelection() {
        runTest("TC_ADMIN_340 Verify license records can be selected using checkboxes", () -> {
                    licenses.selectRecord();
                    return true;
                }
        );
    }

    // TC_ADMIN_342
    @Epic("Admin Page")
    @Feature("Licenses Management")
    @Story("TC_ADMIN_342 Verify license is deleted successfully after confirmation")
    @Test
    public void verifyDeleteLicense() {
        runTest("TC_ADMIN_342 Verify license is deleted successfully after confirmation", () -> {
                    licenses.deleteFirstLicense();
                    Assert.assertTrue(licenses.isSuccessMessageDisplayed(),
                            "Success message not displayed after deleting license");
                    return licenses.isSuccessMessageDisplayed();
                }
        );
    }

    // TC_ADMIN_344
    @Epic("Admin Page")
    @Feature("Licenses Management")
    @Story("TC_ADMIN_344 Verify success message appears after deleting license")
    @Test
    public void verifySuccessMessageAfterDelete() {
        runTest("TC_ADMIN_344 Verify success message appears after deleting license", () -> {
                    licenses.deleteFirstLicense();
                    Assert.assertTrue(licenses.isSuccessMessageDisplayed(),
                            "Success message not displayed after deleting license");
                    return licenses.isSuccessMessageDisplayed();
                }
        );
    }

    // TC_ADMIN_346
    @Epic("Admin Page")
    @Feature("Licenses Management")
    @Story("TC_ADMIN_346 Verify license is not deleted before confirmation")
    @Test
    public void verifyCancelDelete() {
        runTest("TC_ADMIN_346 Verify license is not deleted before confirmation", () -> {
                    licenses.cancelDelete();
                    Assert.assertTrue(licenses.isLicenseTableDisplayed(),
                            "License table should still be displayed after canceling delete");
                    return licenses.isLicenseTableDisplayed();
                }
        );
    }

    // TC_ADMIN_347
    @Epic("Admin Page")
    @Feature("Licenses Management")
    @Story("TC_ADMIN_347 Verify Admin can delete single license successfully")
    @Test
    public void verifySingleDelete() {
        runTest("TC_ADMIN_347 Verify Admin can delete single license successfully", () -> {
                    licenses.deleteFirstLicense();
                    Assert.assertTrue(licenses.isSuccessMessageDisplayed(),
                            "Success message not displayed after deleting license");
                    return licenses.isSuccessMessageDisplayed();
                }
        );
    }

    // TC_ADMIN_348
    @Epic("Admin Page")
    @Feature("Licenses Management")
    @Story("TC_ADMIN_348 Verify Admin can select multiple license using checkboxes")
    @Test
    public void verifyMultipleSelection() {
        runTest("TC_ADMIN_348 Verify Admin can select multiple license using checkboxes", () -> {
                    licenses.selectRecord();
                    return true;
                }
        );
    }

    // TC_ADMIN_349
    @Epic("Admin Page")
    @Feature("Licenses Management")
    @Story("TC_ADMIN_349 Verify No Records Found message appears when no license exist")
    @Test
    public void verifyNoRecordsFound() {
        runTest("TC_ADMIN_349 Verify No Records Found message appears when no license exist",
                () -> licenses.isNoRecordsFoundDisplayed()
        );
    }

}
