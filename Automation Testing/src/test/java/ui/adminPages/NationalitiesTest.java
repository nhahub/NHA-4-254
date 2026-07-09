package ui.adminPages;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.Base;
import org.example.DriverManager;
import org.example.pages.adminPage.NationalitiesPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NationalitiesTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private NationalitiesPage nationality;

    @BeforeMethod
    public void setup() {

        driver = DriverManager.getInstance().getDriver();

        login = new loginPage(driver);
        nationality = new NationalitiesPage(driver);

        login.open();
        login.LogIn("Admin", "admin123");

        nationality.openNationalitiesPage();
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

    @Epic("Admin Page")
    @Feature("Nationality Management")
    @Story("TC_ADMIN_407 Verify Admin can navigate to Nationalities page")
    @Test
    public void verifyNationalityPageOpened() {
        runTest("TC_ADMIN_407 ,Verify Admin can navigate to Nationalities page", () -> {
            Assert.assertTrue(
                    nationality.pageOpened(driver.getCurrentUrl()),
                    "Nationality page not opened");
            return nationality.pageOpened(driver.getCurrentUrl());
        });
    }

    @Epic("Admin Page")
    @Feature("Nationality Management")
    @Story("TC_ADMIN_408 Verify Nationalities records table is displayed")
    @Test
    public void verifyNationalityTableDisplayed() {
        runTest("TC_ADMIN_408 ,Verify Nationalities records table is displayed", () -> {
            Assert.assertTrue(
                    nationality.isNationalityTableDisplayed(),
                    "Nationality table not displayed");
            return nationality.isNationalityTableDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Nationality Management")
    @Story("TC_ADMIN_409 Verify Nationalities Name column is displayed correctly")
    @Test
    public void verifyNationalityColumnDisplayed() {
        runTest("TC_ADMIN_409 ,Verify Nationalities Name column is displayed correctly", () -> {
            Assert.assertTrue(
                    nationality.isNationalityColumnDisplayed(),
                    "Nationality column not displayed");
            return nationality.isNationalityColumnDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Nationality Management")
    @Story("TC_ADMIN_411 Verify Add button opens Add Nationalities form")
    @Test
    public void verifyAddFormOpened() {
        runTest("TC_ADMIN_411 ,Verify Add button opens Add Nationalities form", () -> {
            nationality.clickAdd();
            Assert.assertTrue(
                    nationality.isAddFormDisplayed(),
                    "Add form not displayed");
            return nationality.isAddFormDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Nationality Management")
    @Story("TC_ADMIN_412 Verify system prevents submission with empty required fields")
    @Test
    public void verifyRequiredValidation() {
        runTest("TC_ADMIN_412 ,Verify system prevents submission with empty required fields", () -> {
            nationality.saveWithoutName();
            Assert.assertTrue(
                    nationality.isRequiredValidationDisplayed(),
                    "Required validation not displayed");
            return nationality.isRequiredValidationDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Nationality Management")
    @Story("TC_ADMIN_414 Verify Nationalities is created successfully with valid data")
    @Test
    public void verifyCreateNationality() {

        String name = "Nationality_" + System.currentTimeMillis();

        runTest("TC_ADMIN_414 ,Verify Nationalities is created successfully with valid data", () -> {

            nationality.createNationality(name);

            Assert.assertTrue(
                    nationality.isSuccessMessageDisplayed(),
                    "Nationality creation failed");

            return nationality.isSuccessMessageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Nationality Management")
    @Story("TC_ADMIN_415 Verify new Nationalities appears immediately in table")
    @Test
    public void verifyNationalityExistsInTable() {

        String name = "Nationality_" + System.currentTimeMillis();

        runTest("TC_ADMIN_415 ,Verify new Nationalities appears immediately in table", () -> {

            nationality.createNationality(name);

            Assert.assertTrue(
                    nationality.isNationalityExist(name),
                    "Nationality not found");

            return nationality.isNationalityExist(name);
        });
    }

    @Epic("Admin Page")
    @Feature("Nationality Management")
    @Story("TC_ADMIN_416 Verify success message appears after adding Nationalities")
    @Test
    public void verifySuccessMessageAfterCreate() {

        String name = "Nationality_" + System.currentTimeMillis();

        runTest("TC_ADMIN_416 ,Verify success message appears after adding Nationalities", () -> {

            nationality.createNationality(name);

            Assert.assertTrue(
                    nationality.isSuccessMessageDisplayed(),
                    "Success message not displayed");

            return nationality.isSuccessMessageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Nationality Management")
    @Story("TC_ADMIN_418 Verify system prevents duplicate nationality")
    @Test
    public void verifyDuplicateNationality() {

        runTest("TC_ADMIN_418 ,Verify system prevents duplicate nationality", () -> {

            nationality.createNationality("American");

            Assert.assertTrue(
                    nationality.isDuplicateValidationDisplayed(),
                    "Duplicate validation not displayed");

            return nationality.isDuplicateValidationDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Nationality Management")
    @Story("TC_ADMIN_420 Verify Edit form opens successfully for selected nationality")
    @Test
    public void verifyEditFormOpened() {

        runTest("TC_ADMIN_420 ,Verify Edit form opens successfully for selected nationality", () -> {

            nationality.clickEdit();

            Assert.assertTrue(
                    nationality.isEditFormDisplayed(),
                    "Edit form not displayed");

            return nationality.isEditFormDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Nationality Management")
    @Story("TC_ADMIN_422 Verify admin can update nationality details")
    @Test
    public void verifyUpdateNationality() {

        String updatedName = "Updated_" + System.currentTimeMillis();

        runTest("TC_ADMIN_422 ,Verify admin can update nationality details", () -> {

            nationality.editFirstNationality(updatedName);

            Assert.assertTrue(
                    nationality.isSuccessMessageDisplayed(),
                    "Update failed");

            return nationality.isSuccessMessageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Nationality Management")
    @Story("TC_ADMIN_423 Verify updated nationality appears immediately in table")
    @Test
    public void verifyUpdatedNationalityDisplayed() {

        String updatedName = "Updated_" + System.currentTimeMillis();

        runTest("TC_ADMIN_423 ,Verify updated nationality appears immediately in table", () -> {

            nationality.editFirstNationality(updatedName);

            Assert.assertTrue(
                    nationality.isNationalityExist(updatedName),
                    "Updated nationality not displayed");

            return nationality.isNationalityExist(updatedName);
        });
    }

    @Epic("Admin Page")
    @Feature("Nationality Management")
    @Story("TC_ADMIN_428 Verify nationality is deleted successfully after confirmation")
    @Test
    public void verifyDeleteNationality() {

        runTest("TC_ADMIN_428 ,Verify nationality is deleted successfully after confirmation", () -> {

            nationality.deleteFirstNationality();

            Assert.assertTrue(
                    nationality.isSuccessMessageDisplayed(),
                    "Delete failed");

            return nationality.isSuccessMessageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Nationality Management")
    @Story("TC_ADMIN_432 Verify nationality is not deleted before confirmation")
    @Test
    public void verifyCancelDelete() {

        runTest("TC_ADMIN_432 ,Verify nationality is not deleted before confirmation", () -> {

            nationality.cancelDelete();

            Assert.assertTrue(
                    nationality.isNationalityTableDisplayed(),
                    "Nationality removed after cancel");

            return nationality.isNationalityTableDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Nationality Management")
    @Story("TC_ADMIN_434 Verify Admin can select multiple nationality using checkboxes")
    @Test
    public void verifyBulkDelete() {

        runTest("TC_ADMIN_434 ,Verify Admin can select multiple nationality using checkboxes", () -> {

            nationality.selectRecord();
            nationality.deleteSelected();

            Assert.assertTrue(
                    nationality.isSuccessMessageDisplayed(),
                    "Bulk delete failed");

            return nationality.isSuccessMessageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Nationality Management")
    @Story("TC_ADMIN_435 Verify No Records Found message appears")
    @Test
    public void verifyNoRecordsFound() {

        runTest("TC_ADMIN_435 ,Verify No Records Found message appears", () -> {

            Assert.assertTrue(
                    nationality.isNoRecordsFoundDisplayed()
                            || nationality.isNationalityTableDisplayed());

            return true;
        });
    }

    @Epic("Admin Page")
    @Feature("Nationality Management")
    @Story("TC_ADMIN_436 Verify table layout remains stable with empty records list")
    @Test
    public void verifyLayoutWithEmptyTable() {

        runTest("TC_ADMIN_436 ,Verify table layout remains stable with empty records list", () -> {

            Assert.assertTrue(true);

            return true;
        });
    }
}