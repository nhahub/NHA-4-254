package ui.adminPages;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.Base;
import org.example.DriverManager;
import org.example.pages.adminPage.LanguagePackagesPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LanguagePackagesTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private LanguagePackagesPage languagePackages;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();

        login = new loginPage(driver);
        languagePackages = new LanguagePackagesPage(driver);

        login.open();
        login.LogIn("Admin", "admin123");

        languagePackages.openLanguagePackagesPage();
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
        runTest("Verify Language Packages page opens successfully",
                () -> languagePackages.pageOpened(driver.getCurrentUrl()));
    }

    // TC_ADMIN_626 - Verify Language Packages page displays all installed packages
    @Epic("Admin Page")
    @Feature("Language Packages Management")
    @Story("TC_ADMIN_626 - Verify Admin can view all installed language packages on Language Packages page")
    @Test
    public void verifyLanguagePackagesDisplayed() {
        runTest("TC_ADMIN_626 - Verify Admin can view all installed language packages on Language Packages page", () -> {
            Assert.assertTrue(languagePackages.isLanguagePackagesTableDisplayed(),
                    "Language Packages table is not displayed.");
            return languagePackages.isLanguagePackagesTableDisplayed();
        });
    }

    // TC_ADMIN_627 - Verify Admin can add/install a new language package
    @Epic("Admin Page")
    @Feature("Language Packages Management")
    @Story("TC_ADMIN_627 - Verify Admin can successfully install a new language package")
    @Test
    public void verifyAddLanguagePackage() {
        runTest("TC_ADMIN_627 - Verify Admin can successfully install a new language package", () -> {
            languagePackages.addLanguagePackage("French");
            Assert.assertTrue(languagePackages.isSuccessMessageDisplayed(),
                    "Expected success message not found after adding language package.");
            return languagePackages.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_628 - Verify system prevents installing an already-installed language package
    @Epic("Admin Page")
    @Feature("Language Packages Management")
    @Story("TC_ADMIN_628 - Verify system prevents installing a duplicate language package")
    @Test
    public void verifyDuplicateLanguagePackagePrevented() {
        runTest("TC_ADMIN_628 - Verify system prevents installing a duplicate language package", () -> {
            languagePackages.addLanguagePackage("French");
            Assert.assertTrue(languagePackages.isAlreadyExistsErrorDisplayed(),
                    "Expected 'Already exists' error was not displayed for duplicate package.");
            return languagePackages.isAlreadyExistsErrorDisplayed();
        });
    }

    // TC_ADMIN_630 - Verify Admin can delete a non-default language package
    @Epic("Admin Page")
    @Feature("Language Packages Management")
    @Story("TC_ADMIN_630 - Verify Admin can remove a non-default language package")
    @Test
    public void verifyDeleteLanguagePackage() {
        runTest("TC_ADMIN_630 - Verify Admin can remove a non-default language package", () -> {
            languagePackages.deleteFirstPackage();
            Assert.assertTrue(languagePackages.isSuccessMessageDisplayed(),
                    "Expected success message not found after deletion.");
            return languagePackages.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_632 - Verify search functionality for language packages
    @Epic("Admin Page")
    @Feature("Language Packages Management")
    @Story("TC_ADMIN_632 - Verify Admin can search for a language package by name")
    @Test
    public void verifySearchLanguagePackage() {
        runTest("TC_ADMIN_632 - Verify Admin can search for a language package by name", () -> {
            languagePackages.searchLanguagePackage("Arabic");
            Assert.assertTrue(languagePackages.isLanguagePackagesTableDisplayed(),
                    "Search results should display matching language packages.");
            return languagePackages.isLanguagePackagesTableDisplayed();
        });
    }

    // TC_ADMIN_633 - Verify search shows no results when no match found
    @Epic("Admin Page")
    @Feature("Language Packages Management")
    @Story("TC_ADMIN_633 - Verify behavior when searching for non-existent language package")
    @Test
    public void verifySearchNoResults() {
        runTest("TC_ADMIN_633 - Verify behavior when searching for non-existent language package", () -> {
            languagePackages.searchLanguagePackage("XYZLang123");
            Assert.assertTrue(languagePackages.isNoRecordsFoundDisplayed(),
                    "Expected 'No Records Found' message not displayed for invalid search.");
            return languagePackages.isNoRecordsFoundDisplayed();
        });
    }

    // TC_ADMIN_638 - Verify all required columns shown in Language Packages list
    @Epic("Admin Page")
    @Feature("Language Packages Management")
    @Story("TC_ADMIN_638 - Verify Language Name, Status, and Translation Progress columns are visible")
    @Test
    public void verifyRequiredColumnsDisplayed() {
        runTest("TC_ADMIN_638 - Verify Language Name, Status, and Translation Progress columns are visible", () -> {
            Assert.assertTrue(languagePackages.areRequiredColumnsDisplayed(),
                    "One or more required columns (Language Name, Status, Translation Progress, Actions) are missing.");
            return languagePackages.areRequiredColumnsDisplayed();
        });
    }

    // TC_ADMIN_639 - Verify validation when no language selected during add
    @Epic("Admin Page")
    @Feature("Language Packages Management")
    @Story("TC_ADMIN_639 - Verify system shows error if Admin submits add form without selecting a language")
    @Test
    public void verifyRequiredValidationOnAdd() {
        runTest("TC_ADMIN_639 - Verify system shows error if Admin submits add form without selecting a language", () -> {
            languagePackages.submitAddFormWithoutSelection();
            Assert.assertTrue(languagePackages.isRequiredErrorDisplayed(),
                    "Expected required field validation error not displayed.");
            return languagePackages.isRequiredErrorDisplayed();
        });
    }

    // TC_ADMIN_640 - Verify confirmation dialog appears before deleting a language package
    @Epic("Admin Page")
    @Feature("Language Packages Management")
    @Story("TC_ADMIN_640 - Verify confirmation dialog appears when deleting a language package")
    @Test
    public void verifyDeleteConfirmationDialog() {
        runTest("TC_ADMIN_640 - Verify confirmation dialog appears when deleting a language package", () -> {
            // Click delete icon to trigger dialog (do not confirm yet)
            driver.findElement(org.openqa.selenium.By.xpath("(//i[contains(@class,'bi-trash')])[1]")).click();
            Assert.assertTrue(languagePackages.isConfirmationDialogDisplayed(),
                    "Confirmation dialog with Confirm and Cancel options was not displayed.");
            return languagePackages.isConfirmationDialogDisplayed();
        });
    }

    // TC_ADMIN_641 - Verify Cancel on delete confirmation keeps package intact
    @Epic("Admin Page")
    @Feature("Language Packages Management")
    @Story("TC_ADMIN_641 - Verify clicking Cancel on delete confirmation dialog keeps the package")
    @Test
    public void verifyCancelDelete() {
        runTest("TC_ADMIN_641 - Verify clicking Cancel on delete confirmation dialog keeps the package", () -> {
            languagePackages.cancelDelete();
            Assert.assertTrue(languagePackages.isLanguagePackagesTableDisplayed(),
                    "Language Packages table should still be displayed after canceling delete.");
            return languagePackages.isLanguagePackagesTableDisplayed();
        });
    }

    // TC_ADMIN_642 - Verify translation progress displayed as percentage
    @Epic("Admin Page")
    @Feature("Language Packages Management")
    @Story("TC_ADMIN_642 - Verify translation progress is shown as percentage in packages list")
    @Test
    public void verifyTranslationProgressAsPercentage() {
        runTest("TC_ADMIN_642 - Verify translation progress is shown as percentage in packages list", () -> {
            Assert.assertTrue(languagePackages.isTranslationProgressDisplayedAsPercentage(),
                    "Translation progress is not displayed as a percentage value.");
            return languagePackages.isTranslationProgressDisplayedAsPercentage();
        });
    }

    // TC_ADMIN_644 - Verify Add button is present on Language Packages page
    @Epic("Admin Page")
    @Feature("Language Packages Management")
    @Story("TC_ADMIN_644 - Verify the 'Add' button is visible on Language Packages page")
    @Test
    public void verifyAddButtonVisible() {
        runTest("TC_ADMIN_644 - Verify the 'Add' button is visible on Language Packages page", () -> {
            Assert.assertTrue(languagePackages.isAddButtonDisplayed(),
                    "Add button is not visible on Language Packages page.");
            return languagePackages.isAddButtonDisplayed();
        });
    }

    // TC_ADMIN_649 - Verify Add dialog closes when Cancel is clicked
    @Epic("Admin Page")
    @Feature("Language Packages Management")
    @Story("TC_ADMIN_649 - Verify clicking Cancel in Add Language Package dialog closes it without adding")
    @Test
    public void verifyCancelAddDialog() {
        runTest("TC_ADMIN_649 - Verify clicking Cancel in Add Language Package dialog closes it without adding", () -> {
            languagePackages.clickCancelOnAddDialog();
            Assert.assertTrue(languagePackages.isLanguagePackagesTableDisplayed(),
                    "Language Packages table should still be displayed after canceling the add dialog.");
            return languagePackages.isLanguagePackagesTableDisplayed();
        });
    }

    // TC_ADMIN_654 - Verify Admin can enable and disable an installed language package
    @Epic("Admin Page")
    @Feature("Language Packages Management")
    @Story("TC_ADMIN_654 - Verify Admin can toggle the status of an installed language package between Enabled and Disabled")
    @Test
    public void verifyEnableDisablePackage() {
        runTest("TC_ADMIN_654 - Verify Admin can toggle the status of an installed language package between Enabled and Disabled", () -> {
            languagePackages.toggleFirstPackageStatus();
            Assert.assertTrue(languagePackages.isSuccessMessageDisplayed(),
                    "Expected success message not found after toggling language package status.");
            return languagePackages.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_661 - Verify language package search returns results for partial name matches
    @Epic("Admin Page")
    @Feature("Language Packages Management")
    @Story("TC_ADMIN_661 - Verify that searching with a partial language name returns correct matching results")
    @Test
    public void verifyPartialSearchReturnsResults() {
        runTest("TC_ADMIN_661 - Verify that searching with a partial language name returns correct matching results", () -> {
            languagePackages.searchLanguagePackage("Ara");
            Assert.assertTrue(languagePackages.isLanguagePackagesTableDisplayed(),
                    "Partial search should return matching language packages.");
            return languagePackages.isLanguagePackagesTableDisplayed();
        });
    }

    // TC_ADMIN_662 - Verify bulk selection and action on language packages
    @Epic("Admin Page")
    @Feature("Language Packages Management")
    @Story("TC_ADMIN_662 - Verify that Admin can select multiple language packages and perform bulk delete")
    @Test
    public void verifyBulkDelete() {
        Allure.getLifecycle().updateTestCase(testResult ->
                testResult.setName("TC_ADMIN_662 - Verify bulk selection and deletion of language packages."));
        runTest("TC_ADMIN_662 - Verify that Admin can select multiple language packages and perform bulk delete", () -> {
            languagePackages.selectMultipleRows(2).deleteSelected();
            return languagePackages.isSuccessMessageDisplayed();
        });
    }
}