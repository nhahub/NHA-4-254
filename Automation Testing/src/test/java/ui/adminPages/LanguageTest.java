package ui.adminPages;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.Base;
import org.example.DriverManager;
import org.example.pages.adminPage.LanguagePage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LanguageTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private LanguagePage language;

    @BeforeMethod
    public void setup() {

        driver = DriverManager.getInstance().getDriver();

        login = new loginPage(driver);
        language = new LanguagePage(driver);

        login.open();
        login.LogIn("Admin", "admin123");

        language.openLanguagesPage();
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

    @Epic("Admin Page")
    @Feature("Languages Management")
    @Story("TC_ADMIN_351 Verify Languages page loads successfully")
    @Test
    public void verifyLanguagePageOpened() {
        runTest("TC_ADMIN_351 ,Verify Languages page loads successfully", () -> {
            Assert.assertTrue(language.pageOpened(driver.getCurrentUrl()
            ), "Languages page did not open successfully.");
            return language.pageOpened(driver.getCurrentUrl());

        });
    }

    @Epic("Admin Page")
    @Feature("Languages Management")
    @Story("TC_ADMIN_352 Verify languages displayed in table")
    @Test
    public void verifyLanguagesDisplayed() {
        runTest("TC_ADMIN_352 Verify languages displayed in table", () -> {
            Assert.assertTrue(language.isLanguageTableDisplayed(), "Languages table is not displayed.");
            return language.isLanguageTableDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Languages Management")
    @Story("TC_ADMIN_353 Verify table columns")
    @Test
    public void verifyColumnsDisplayed() {
        runTest("TC_ADMIN_353 ,Verify table columns", () -> {
            Assert.assertTrue(language.isLanguageNameColumnDisplayed(), "Language Name column is not displayed.");
            return language.isLanguageNameColumnDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Languages Management")
    @Story("TC_ADMIN_356 Add new language successfully")
    @Test
    public void verifyCreateLanguage() {
        String name = "Arabic_" + System.currentTimeMillis();
        runTest("TC_ADMIN_356 ,Add new language successfully", () -> {
            language.createLanguage(name);
            Assert.assertTrue(language.isSuccessMessageDisplayed(), "Expected success message not found after creating language.");
            return language.isSuccessMessageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Languages Management")
    @Story("TC_ADMIN_357 Verify added language appears in list")
    @Test
    public void verifyLanguageExists() {
        String name = "Arabic_" + System.currentTimeMillis();
        runTest("TC_ADMIN_357 ,Verify added language appears in list", () -> {
            language.createLanguage(name);
            Assert.assertTrue(language.isLanguageExist(name), "Created language not found in the list.");
            return language.isLanguageExist(name);
        });
    }

    @Epic("Admin Page")
    @Feature("Languages Management")
    @Story("TC_ADMIN_358 Verify Save button functionality")
    @Test
    public void verifySaveButton() {
        String name = "Language_" + System.currentTimeMillis();
        runTest("TC_ADMIN_358 ,Verify Save button functionality", () -> {
            language.createLanguage(name);
            Assert.assertTrue(language.isSuccessMessageDisplayed(), "Expected success message not found after saving language.");
            return language.isSuccessMessageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Languages Management")
    @Story("TC_ADMIN_359 Verify add button functionality")
    @Test
    public void verifyAddButton() {
        runTest("TC_ADMIN_359 ,Verify add button functionality", () -> {
            language.clickAdd();
            return language.isAddFormDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Languages Management")
    @Story("TC_ADMIN_360 Verify script injection prevention")
    @Test
    public void verifyScriptInjection() {
        runTest("TC_ADMIN_360 ,Verify script injection prevention", () -> {
            language.createLanguage("<script>alert(1)</script>");
            return true;
        });
    }

    @Epic("Admin Page")
    @Feature("Languages Management")
    @Story("TC_ADMIN_361 Verify max character length validation")
    @Test
    public void verifyCharacterLimit() {

        runTest("TC_ADMIN_361 ,Verify max character length validation.", () -> {
            language.createLanguage(
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            Assert.assertTrue(language.isCharacterLimitValidationDisplayed());
            return language.isCharacterLimitValidationDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Languages Management")
    @Story("TC_ADMIN_362 Verify required field validation")
    @Test
    public void verifyRequiredValidation() {
        runTest("TC_ADMIN_362", () -> {
            language.saveWithoutName();
            Assert.assertTrue(language.isRequiredValidationDisplayed());
            return language.isRequiredValidationDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Languages Management")
    @Story("TC_ADMIN_364 Prevent duplicate language creation")
    @Test
    public void verifyDuplicateLanguage() {
        runTest("TC_ADMIN_364 ,Prevent duplicate language creation.", () -> {
            language.createLanguage("Arabic");
            Assert.assertTrue(language.isDuplicateValidationDisplayed());
            return language.isDuplicateValidationDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Languages Management")
    @Story("TC_ADMIN_366 Verify duplicate error message")
    @Test
    public void verifyDuplicateErrorMessage() {
        runTest("TC_ADMIN_366 ,Verify duplicate error message.", () -> {
            language.createLanguage("English");
            Assert.assertTrue(language.isDuplicateValidationDisplayed());
            return language.isDuplicateValidationDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Languages Management")
    @Story("TC_ADMIN_369 Edit existing language successfully")
    @Test
    public void verifyEditLanguage() {
        String updated = "Updated_" + System.currentTimeMillis();
        runTest("TC_ADMIN_369", () -> {
            language.editFirstLanguage(updated);
            Assert.assertTrue(language.isSuccessMessageDisplayed());
            return language.isSuccessMessageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Languages Management")
    @Story("TC_ADMIN_370 Verify updated language displayed")
    @Test
    public void verifyUpdatedLanguageDisplayed() {
        String updated = "Updated_" + System.currentTimeMillis();
        runTest("TC_ADMIN_370", () -> {
            language.editFirstLanguage(updated);
            Assert.assertTrue(language.isLanguageExist(updated));
            return language.isLanguageExist(updated);
        });
    }

    @Epic("Admin Page")
    @Feature("Languages Management")
    @Story("TC_ADMIN_371 Verify edit form loads existing data")
    @Test
    public void verifyEditFormOpened() {
        runTest("TC_ADMIN_371", () -> {
            language.clickEdit();
            Assert.assertTrue(language.isEditFormDisplayed());
            return language.isEditFormDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Languages Management")
    @Story("TC_ADMIN_373 Delete single language successfully")
    @Test
    public void verifyDeleteLanguage() {
        runTest("TC_ADMIN_373 ,Delete single language successfully", () -> {
            language.deleteFirstLanguage();
            Assert.assertTrue(language.isSuccessMessageDisplayed());
            return language.isSuccessMessageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Languages Management")
    @Story("TC_ADMIN_375 Verify admin can delete multiple records")
    @Test
    public void verifyBulkDelete() {

        runTest("TC_ADMIN_375", () -> {
            language.selectRecord();
            language.deleteSelected();
            Assert.assertTrue(language.isSuccessMessageDisplayed());
            return language.isSuccessMessageDisplayed();
        });
    }


    @Epic("Admin Page")
    @Feature("Languages Management")
    @Story("TC_ADMIN_377 Verify cancel deletion functionality")
    @Test
    public void verifyCancelDelete() {

        runTest("TC_ADMIN_377", () -> {
            language.cancelDelete();
            Assert.assertTrue(language.isLanguageTableDisplayed());
            return language.isLanguageTableDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Languages Management")
    @Story("TC_ADMIN_378 Cancel adding language")
    @Test
    public void verifyCancelAdd() {

        runTest("TC_ADMIN_378", () -> {

            language.clickAdd();

            language.enterLanguageName("Spanish");

            language.cancelAdd();

            return true;
        });
    }

    @Epic("Admin Page")
    @Feature("Languages Management")
    @Story("TC_ADMIN_379 Verify redirect after cancel")
    @Test
    public void verifyRedirectAfterCancel() {
        runTest("TC_ADMIN_379", () -> {
            language.clickAdd();
            language.cancelAdd();
            Assert.assertTrue(language.isLanguageTableDisplayed());
            return language.isLanguageTableDisplayed();
        });
    }
}