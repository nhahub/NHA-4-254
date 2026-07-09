//package ui.adminPages;
//
//import org.example.Base;
//import org.example.DriverManager;
//import org.example.pages.adminPage.EducationPage;
//import org.example.pages.loginPage;
//import org.openqa.selenium.WebDriver;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//public class EducationTest extends Base {
//
//    private WebDriver driver;
//    private loginPage login;
//    private EducationPage education;
//
//    @BeforeMethod
//    public void setup() {
//        driver = DriverManager.getInstance().getDriver();
//        login = new loginPage(driver);
//        education = new EducationPage(driver);
//        login.open();
//        login.LogIn("Admin", "admin123");
//        education.openEducationPage();
//    }
//
//    @AfterMethod
//    public void tearDown() {
//
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//
//    // TC_ADMIN_291
//
//    @Test
//    public void verifyOpenEducationPage() {
//        runTest(
//                "Verify Education page opens successfully",
//                () -> education.pageOpened(driver.getCurrentUrl())
//        );
//    }
//
//    // TC_ADMIN_292
//
//    @Test
//    public void verifyEducationTableDisplayed() {
//
//        runTest(
//                "Verify Education table displayed",
//                () -> education.isEducationTableDisplayed()
//        );
//    }
//
//    // TC_ADMIN_293
//
//    @Test
//    public void verifyEducationColumnDisplayed() {
//
//        runTest(
//                "Verify Education Level column displayed",
//                () -> education.isEducationColumnDisplayed()
//        );
//    }
//
//    // TC_ADMIN_296
//
//    @Test
//    public void verifyRequiredValidation() {
//
//        runTest(
//                "Verify Required validation",
//                () -> {
//
//                    education.saveWithoutName();
//
//                    return education.isRequiredErrorDisplayed();
//                }
//        );
//    }
//
//    // TC_ADMIN_298
//
//    @Test
//    public void verifyCreateEducation() {
//
//        String educationName =
//                "Automation Degree " + System.currentTimeMillis();
//
//        runTest(
//                "Verify Create Education",
//                () -> {
//
//                    education.createEducation(educationName);
//
//                    return education.isSuccessMessageDisplayed();
//                }
//        );
//    }
//
//    // TC_ADMIN_302
//
//    @Test
//    public void verifyDuplicateEducation() {
//
//        runTest(
//                "Verify Duplicate Education",
//                () -> {
//
//                    education.createEducation("Bachelor's Degree");
//
//                    return education.isDuplicateErrorDisplayed();
//                }
//        );
//    }
//
//    // TC_ADMIN_304 + 305 + 306
//
//    @Test
//    public void verifyUpdateEducation() {
//
//        runTest(
//                "Verify Update Education",
//                () -> {
//
//                    education.editFirstEducation(
//                            "Updated Education "
//                                    + System.currentTimeMillis()
//                    );
//
//                    return education.isSuccessMessageDisplayed();
//                }
//        );
//    }
//
//    // TC_ADMIN_310
//
//    @Test
//    public void verifySelectSingleRecord() {
//
//        runTest(
//                "Verify Select Record",
//                () -> education.selectSingleRecord()
//        );
//    }
//
//    // TC_ADMIN_312
//
//    @Test
//    public void verifyDeleteEducation() {
//
//        runTest(
//                "Verify Delete Education",
//                () -> {
//
//                    education.deleteFirstEducation();
//
//                    return education.isSuccessMessageDisplayed();
//                }
//        );
//    }
//
//    // TC_ADMIN_316
//
//    @Test
//    public void verifyCancelDelete() {
//
//        runTest(
//                "Verify Cancel Delete",
//                () -> {
//
//                    education.cancelDelete();
//
//                    return true;
//                }
//        );
//    }
//
//    // TC_ADMIN_318
//
//    @Test
//    public void verifyMassDelete() {
//
//        runTest(
//                "Verify Mass Delete",
//                () -> {
//
//                    education.selectMultipleRows(2)
//                            .deleteSelected();
//
//                    return education.isSuccessMessageDisplayed();
//                }
//        );
//    }
//
//    // TC_ADMIN_319
//
//    @Test
//    public void verifyNoRecordsFound() {
//
//        runTest(
//                "Verify No Records Found",
//                () -> education.isNoRecordsFoundDisplayed()
//        );
//    }
//
//    //try test case
//    @Test
//    public void try_add() {
//        runTest("add edu",
//                () -> {
//                    education.clickAdd().createEducation("Test Education");
//                    return true;
//                }
//        );
//    }
//
//}
package ui.adminPages;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.Base;
import org.example.DriverManager;
import org.example.pages.adminPage.EducationPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EducationTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private EducationPage education;

    @BeforeMethod
    public void setup() {

        driver = DriverManager.getInstance().getDriver();

        login = new loginPage(driver);
        education = new EducationPage(driver);

        login.open();
        login.LogIn("Admin", "admin123");

        education.openEducationPage();
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

    // TC_ADMIN_291
    @Epic("Admin Page")
    @Feature("Education Management")
    @Story("TC_ADMIN_291 Verify Admin can navigate to education level page")
    @Test
    public void verifyEducationPageOpened() {

        runTest(
                "TC_ADMIN_291 Verify Admin can navigate to education level page",
                () -> education.pageOpened(driver.getCurrentUrl())
        );
    }

    // TC_ADMIN_292
    @Epic("Admin Page")
    @Feature("Education Management")
    @Story("TC_ADMIN_292 Verify education levels records table is displayed")
    @Test
    public void verifyEducationTableDisplayed() {
        runTest("TC_ADMIN_292 Verify education levels records table is displayed", () -> {
                    Assert.assertTrue(education.isEducationTableDisplayed(),
                            "Education table is not displayed");
                    return education.isEducationTableDisplayed();
                }
        );
    }

    // TC_ADMIN_293
    @Epic("Admin Page")
    @Feature("Education Management")
    @Story("TC_ADMIN_293 Verify education level Name column is displayed correctly")
    @Test
    public void verifyEducationColumnDisplayed() {

        runTest("TC_ADMIN_293 Verify education level Name column is displayed correctly", () -> {
                    Assert.assertTrue(education.isEducationNameColumnDisplayed(),
                            "Education Level column is not displayed");
                    return education.isEducationNameColumnDisplayed();
                }
        );
    }

    // TC_ADMIN_294
    @Epic("Admin Page")
    @Feature("Education Management")
    @Story("TC_ADMIN_294 Verify existing education level records appear in table")
    @Test
    public void verifyExistingEducationRecords() {
        runTest("TC_ADMIN_294 Verify existing education level records appear in table", () -> {
                    Assert.assertTrue(education.isEducationTableDisplayed(),
                            "Education records are not displayed");
                    return education.isEducationTableDisplayed();
                }
        );
    }

    // TC_ADMIN_295
    @Epic("Admin Page")
    @Feature("Education Management")
    @Story("TC_ADMIN_295 Verify Add button opens Add education level form")
    @Test
    public void verifyAddButtonOpensForm() {
        runTest("TC_ADMIN_295 Verify Add button opens Add education level form", () -> {
                    education.clickAdd();
                    Assert.assertTrue(education.isAddFormDisplayed(),
                            "Add form was not opened");
                    return education.isAddFormDisplayed();
                }
        );
    }

    // TC_ADMIN_296
    @Epic("Admin Page")
    @Feature("Education Management")
    @Story("TC_ADMIN_296 Verify system prevents submission with empty required fields")
    @Test
    public void verifyRequiredValidation() {
        runTest("TC_ADMIN_296 Verify system prevents submission with empty required fields", () -> {
                    education.saveWithoutName();
                    Assert.assertTrue(education.isRequiredValidationDisplayed(),
                            "Required validation message not displayed");
                    return education.isRequiredValidationDisplayed();
                }
        );
    }

    // TC_ADMIN_297
    @Epic("Admin Page")
    @Feature("Education Management")
    @Story("TC_ADMIN_297 Verify validation error is cleared after entering valid education level Name")
    @Test
    public void verifyValidationCleared() {
        runTest("TC_ADMIN_297 Verify validation error is cleared after entering valid education level Name", () -> {
                    education.saveWithoutName();
                    education.enterEducationName("Internship");
                    Assert.assertTrue(education.isRequiredValidationDisplayed());

                    return education.isRequiredValidationDisplayed();
                }
        );
    }

    // TC_ADMIN_298
    @Epic("Admin Page")
    @Feature("Education Management")
    @Story("TC_ADMIN_298 Verify education level is created successfully with valid data")
    @Test
    public void verifyCreateEducation() {
        String educationName = "Education_" + System.currentTimeMillis();
        runTest("TC_ADMIN_298 Verify education level is created successfully with valid data", () -> {
                    education.createEducation(educationName);
                    Assert.assertTrue(education.isSuccessMessageDisplayed(),
                            "Success message not displayed");
                    return education.isSuccessMessageDisplayed();
                }
        );
    }

    // TC_ADMIN_299
    @Epic("Admin Page")
    @Feature("Education Management")
    @Story("TC_ADMIN_299 Verify new education level appears immediately in table")
    @Test
    public void verifyNewEducationDisplayed() {
        String educationName = "Education_" + System.currentTimeMillis();
        runTest("TC_ADMIN_299 Verify new education level appears immediately in table", () -> {
                    education.createEducation(educationName);
                    Assert.assertTrue(education.isEducationExist(educationName),
                            "Created education level not found");
                    return education.isEducationExist(educationName);
                }
        );
    }

    // TC_ADMIN_300
    @Epic("Admin Page")
    @Feature("Education Management")
    @Story("TC_ADMIN_300 Verify success message appears after adding education level")
    @Test
    public void verifySuccessMessageAfterCreate() {
        String educationName = "Education_" + System.currentTimeMillis();
        runTest("TC_ADMIN_300 Verify success message appears after adding education level", () -> {
                    education.createEducation(educationName);
                    Assert.assertTrue(education.isSuccessMessageDisplayed(), "Success message not displayed after creating education level");
                    return education.isSuccessMessageDisplayed();
                }
        );
    }

    // TC_ADMIN_301
    @Epic("Admin Page")
    @Feature("Education Management")
    @Story("TC_ADMIN_301 Verify system handles special characters in education level names correctly")
    @Test
    public void verifySpecialCharacters() {
        runTest("TC_ADMIN_301 Verify system handles special characters in education level names correctly", () -> {
                    education.createEducation("@@@###%%%HR_Method!!!");
                    return true;
                }
        );
    }

    // TC_ADMIN_302
    @Epic("Admin Page")
    @Feature("Education Management")
    @Story("TC_ADMIN_302 Verify system prevents duplicate education level")
    @Test
    public void verifyDuplicateEducationValidation() {
        runTest("TC_ADMIN_302 Verify system prevents duplicate education level", () -> {
                    education.createEducation("Bachelor's Degree");
                    Assert.assertTrue(education.isDuplicateValidationDisplayed(),
                            "Duplicate validation not displayed");
                    return education.isDuplicateValidationDisplayed();
                }
        );
    }


    // TC_ADMIN_304
    @Epic("Admin Page")
    @Feature("Education Management")
    @Story("TC_ADMIN_304 Verify Edit form opens successfully for selected education level")
    @Test
    public void verifyEditFormOpened() {
        runTest("TC_ADMIN_304 Verify Edit form opens successfully for selected education level", () -> {
                    education.clickEdit();
                    return education.isEditFormDisplayed();
                }
        );
    }

    // TC_ADMIN_305
    @Epic("Admin Page")
    @Feature("Education Management")
    @Story("TC_ADMIN_305 Verify Edit button opens form with existing data")
    @Test
    public void verifyEditButtonOpensForm() {

        runTest("TC_ADMIN_305 Verify Edit button opens form with existing data", () -> {
                    education.clickEdit();
                    return education.isEditFormDisplayed();
                }
        );
    }

    // TC_ADMIN_306
    @Epic("Admin Page")
    @Feature("Education Management")
    @Story("TC_ADMIN_306 Verify admin can update education level details")
    @Test
    public void verifyUpdateEducation() {
        String updatedName =
                "Updated_" + System.currentTimeMillis();

        runTest(
                "TC_ADMIN_306 Verify admin can update education level details",
                () -> {

                    education.editFirstEducation(updatedName);
                    Assert.assertTrue(education.isSuccessMessageDisplayed(),
                            "Success message not displayed after updating education level");
                    return education.isSuccessMessageDisplayed();
                }
        );
    }

    // TC_ADMIN_307
    @Epic("Admin Page")
    @Feature("Education Management")
    @Story("TC_ADMIN_307 Verify updated education level appears immediately in table")
    @Test
    public void verifyUpdatedEducationDisplayed() {

        String updatedName =
                "Updated_" + System.currentTimeMillis();

        runTest(
                "TC_ADMIN_307 Verify updated education level appears immediately in table",
                () -> {

                    education.editFirstEducation(updatedName);
                    Assert.assertTrue(education.isEducationExist(updatedName),
                            "Updated education level not found in table");
                    return education.isEducationExist(updatedName);
                }
        );
    }

    // TC_ADMIN_308
    @Epic("Admin Page")
    @Feature("Education Management")
    @Story("TC_ADMIN_308 Verify success message appears after updating education level")
    @Test
    public void verifySuccessMessageAfterUpdate() {

        String updatedName =
                "Updated_" + System.currentTimeMillis();

        runTest(
                "TC_ADMIN_308 Verify success message appears after updating education level",
                () -> {

                    education.editFirstEducation(updatedName);
                    Assert.assertTrue(education.isSuccessMessageDisplayed(),
                            "Success message not displayed after updating education level");
                    return education.isSuccessMessageDisplayed();
                }
        );
    }

    // TC_ADMIN_310
    @Epic("Admin Page")
    @Feature("Education Management")
    @Story("TC_ADMIN_310 Verify education level records can be selected using checkboxes")
    @Test
    public void verifyRecordSelection() {

        runTest(
                "TC_ADMIN_310 Verify education level records can be selected using checkboxes",
                () -> {

                    education.selectRecord();

                    return true;
                }
        );
    }

    // TC_ADMIN_312
    @Epic("Admin Page")
    @Feature("Education Management")
    @Story("TC_ADMIN_312 Verify education level is deleted successfully after confirmation")
    @Test
    public void verifyDeleteEducation() {

        runTest(
                "TC_ADMIN_312 Verify education level is deleted successfully after confirmation",
                () -> {

                    education.deleteFirstEducation();
                    Assert.assertTrue(education.isSuccessMessageDisplayed(),
                            "Success message not displayed after deleting education level");
                    return education.isSuccessMessageDisplayed();
                }
        );
    }

    // TC_ADMIN_314
    @Epic("Admin Page")
    @Feature("Education Management")
    @Story("TC_ADMIN_314 Verify success message appears after deleting education level")
    @Test
    public void verifySuccessMessageAfterDelete() {

        runTest(
                "TC_ADMIN_314 Verify success message appears after deleting education level",
                () -> {

                    education.deleteFirstEducation();
                    Assert.assertTrue(education.isSuccessMessageDisplayed(),
                            "Success message not displayed after deleting education level");
                    return education.isSuccessMessageDisplayed();
                }
        );
    }

    // TC_ADMIN_316
    @Epic("Admin Page")
    @Feature("Education Management")
    @Story("TC_ADMIN_316 Verify education level is not deleted before confirmation")
    @Test
    public void verifyCancelDelete() {

        runTest(
                "TC_ADMIN_316 Verify education level is not deleted before confirmation",
                () -> {

                    education.cancelDelete();
                    Assert.assertTrue(education.isEducationTableDisplayed(),
                            "Education table should still be displayed after canceling delete");
                    return education.isEducationTableDisplayed();
                }
        );
    }

    // TC_ADMIN_317
    @Epic("Admin Page")
    @Feature("Education Management")
    @Story("TC_ADMIN_317 Verify Admin can delete single education level successfully")
    @Test
    public void verifySingleDelete() {

        runTest(
                "TC_ADMIN_317 Verify Admin can delete single education level successfully",
                () -> {

                    education.deleteFirstEducation();
                    Assert.assertTrue(education.isSuccessMessageDisplayed(),
                            "Success message not displayed after deleting education level");
                    return education.isSuccessMessageDisplayed();
                }
        );
    }

    // TC_ADMIN_318
    @Epic("Admin Page")
    @Feature("Education Management")
    @Story("TC_ADMIN_318 Verify Admin can select multiple education level using checkboxes")
    @Test
    public void verifyMultipleSelection() {

        runTest(
                "TC_ADMIN_318 Verify Admin can select multiple education level using checkboxes",
                () -> {

                    education.selectRecord();
                    education.selectRecord();

                    return true;
                }
        );
    }
}

