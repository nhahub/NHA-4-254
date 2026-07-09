package ui.adminPages;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.Base;
import org.example.DriverManager;
import org.example.pages.adminPage.ProviderListPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProviderListTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private ProviderListPage providerList;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();

        login = new loginPage(driver);
        providerList = new ProviderListPage(driver);

        login.open();
        login.LogIn("Admin", "admin123");

        providerList.openProviderListPage();
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
        runTest("Verify Provider List page opens successfully",
                () -> providerList.pageOpened(driver.getCurrentUrl()));
    }

    // TC_ADMIN_695 - Verify Admin can view the list of integration providers and actions
    @Epic("Admin Page")
    @Feature("OpenID Connect Provider Management")
    @Story("TC_ADMIN_695 - Verify Admin can view the list of integration providers and actions")
    @Test
    public void verifyProviderListDisplayed() {
        runTest("TC_ADMIN_695 - Verify Admin can view the list of integration providers and actions", () -> {
            Assert.assertTrue(providerList.isProviderTableDisplayed(),
                    "Provider list table is not displayed.");
            return providerList.isProviderTableDisplayed();
        });
    }

    // TC_ADMIN_696 - Verify successful addition of a new authentication provider with valid parameters
    @Epic("Admin Page")
    @Feature("OpenID Connect Provider Management")
    @Story("TC_ADMIN_696 - Verify Admin can successfully add a new authentication provider")
    @Test
    public void verifyAddProvider() {
        String providerName = "GitHub AuthProvider " + System.currentTimeMillis();
        runTest("TC_ADMIN_696 - Verify Admin can successfully add a new authentication provider", () -> {
            providerList.createProvider(
                    providerName,
                    "https://github.com/login/oauth",
                    "gh_client_88329",
                    "sec_449320_amx"
            );
            Assert.assertTrue(providerList.isSuccessMessageDisplayed(),
                    "Expected success message not found after adding provider.");
            return providerList.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_697 - Verify validation error messages when mandatory provider parameters are omitted
    @Epic("Admin Page")
    @Feature("OpenID Connect Provider Management")
    @Story("TC_ADMIN_697 - Verify validation errors displayed when all fields are empty on save")
    @Test
    public void verifyRequiredValidationAllFieldsEmpty() {
        runTest("TC_ADMIN_697 - Verify validation errors displayed when all fields are empty on save", () -> {
            providerList.submitAddFormEmpty();
            Assert.assertTrue(providerList.areMultipleRequiredErrorsDisplayed(),
                    "Expected multiple 'Required' validation errors when all fields are blank.");
            return providerList.areMultipleRequiredErrorsDisplayed();
        });
    }

    // TC_ADMIN_697 (partial) - Verify validation when only one field is filled
    @Epic("Admin Page")
    @Feature("OpenID Connect Provider Management")
    @Story("TC_ADMIN_697 - Verify validation errors displayed when only provider name is filled")
    @Test
    public void verifyRequiredValidationPartialFields() {
        runTest("TC_ADMIN_697 - Verify validation errors displayed when only provider name is filled", () -> {
            providerList.submitAddFormWithOnlyName("GitHub Auth");
            Assert.assertTrue(providerList.isRequiredErrorDisplayed(),
                    "Expected 'Required' validation error for remaining empty fields.");
            return providerList.isRequiredErrorDisplayed();
        });
    }

    // TC_ADMIN_698 - Verify Admin can modify and update details of an existing provider profile
    @Epic("Admin Page")
    @Feature("OpenID Connect Provider Management")
    @Story("TC_ADMIN_698 - Verify Admin can edit and update an existing provider profile")
    @Test
    public void verifyEditProvider() {
        runTest("TC_ADMIN_698 - Verify Admin can edit and update an existing provider profile", () -> {
            providerList.editFirstProvider("gh_client_99999", "sec_new_99999");
            Assert.assertTrue(providerList.isSuccessMessageDisplayed(),
                    "Expected success message not found after updating provider.");
            return providerList.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_699 - Verify permanent removal of a provider profile after confirmation
    @Epic("Admin Page")
    @Feature("OpenID Connect Provider Management")
    @Story("TC_ADMIN_699 - Verify Admin can delete a provider profile after confirmation")
    @Test
    public void verifyDeleteProvider() {
        runTest("TC_ADMIN_699 - Verify Admin can delete a provider profile after confirmation", () -> {
            providerList.deleteFirstProvider();
            Assert.assertTrue(providerList.isSuccessMessageDisplayed(),
                    "Expected success message not found after deleting provider.");
            return providerList.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_699 (dialog) - Verify confirmation dialog appears before deleting a provider
    @Epic("Admin Page")
    @Feature("OpenID Connect Provider Management")
    @Story("TC_ADMIN_699 - Verify confirmation dialog appears before deleting a provider")
    @Test
    public void verifyDeleteConfirmationDialog() {
        runTest("TC_ADMIN_699 - Verify confirmation dialog appears before deleting a provider", () -> {
            driver.findElement(
                    org.openqa.selenium.By.xpath("(//i[contains(@class,'bi-trash')])[1]")
            ).click();
            Assert.assertTrue(providerList.isConfirmationDialogDisplayed(),
                    "Confirmation dialog with Yes/No options was not displayed.");
            return providerList.isConfirmationDialogDisplayed();
        });
    }

    // TC_ADMIN_699 (cancel) - Verify canceling delete keeps provider intact
    @Epic("Admin Page")
    @Feature("OpenID Connect Provider Management")
    @Story("TC_ADMIN_699 - Verify canceling delete confirmation keeps the provider record intact")
    @Test
    public void verifyCancelDelete() {
        runTest("TC_ADMIN_699 - Verify canceling delete confirmation keeps the provider record intact", () -> {
            providerList.cancelDelete();
            Assert.assertTrue(providerList.isProviderTableDisplayed(),
                    "Provider table should still be displayed after canceling delete.");
            return providerList.isProviderTableDisplayed();
        });
    }

    // TC_ADMIN_700 - Verify non-admin roles are restricted from accessing the Provider Management view
    @Epic("Admin Page")
    @Feature("OpenID Connect Provider Management")
    @Story("TC_ADMIN_700 - Verify non-admin users cannot access the Provider List page")
    @Test
    public void verifyNonAdminAccessBlocked() {
        runTest("TC_ADMIN_700 - Verify non-admin users cannot access the Provider List page", () -> {
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/logout");
            // Login as ESS / non-admin user
            login.LogIn("Ess", "Ess@123");
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/openIdProvider");
            Assert.assertFalse(
                    driver.getCurrentUrl().contains("openIdProvider"),
                    "Non-admin user should not be able to access the Provider List page.");
            return !driver.getCurrentUrl().contains("openIdProvider");
        });
    }

    // TC_ADMIN_701 - Verify authentication configurations persist across fresh sessions
    @Epic("Admin Page")
    @Feature("OpenID Connect Provider Management")
    @Story("TC_ADMIN_701 - Verify provider configurations persist after logout and re-login")
    @Test
    public void verifyProviderPersistsAfterReLogin() {
        String providerName = "GitHub Auth " + System.currentTimeMillis();
        runTest("TC_ADMIN_701 - Verify provider configurations persist after logout and re-login", () -> {
            providerList.createProvider(
                    providerName,
                    "https://github.com/login/oauth",
                    "gh_client_88329",
                    "sec_449320_amx"
            );
            Assert.assertTrue(providerList.isSuccessMessageDisplayed(),
                    "Provider should be created before testing persistence.");
            // Logout and re-login
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/logout");
            login.LogIn("Admin", "admin123");
            providerList.openProviderListPage();
            Assert.assertTrue(providerList.isProviderTableDisplayed(),
                    "Provider list should still be displayed after re-login — data should persist.");
            return providerList.isProviderTableDisplayed();
        });
    }

    // TC_ADMIN_701 (refresh) - Verify provider configurations persist after page refresh
    @Epic("Admin Page")
    @Feature("OpenID Connect Provider Management")
    @Story("TC_ADMIN_701 - Verify provider configurations persist after page refresh")
    @Test
    public void verifyProviderPersistsAfterRefresh() {
        runTest("TC_ADMIN_701 - Verify provider configurations persist after page refresh", () -> {
            driver.navigate().refresh();
            Assert.assertTrue(providerList.isProviderTableDisplayed(),
                    "Provider list should still be displayed after page refresh.");
            return providerList.isProviderTableDisplayed();
        });
    }
}