package ui.adminPages;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.Base;
import org.example.DriverManager;
import org.example.pages.adminPage.OAuthClientPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OAuthClientTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private OAuthClientPage oauthClient;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();

        login = new loginPage(driver);
        oauthClient = new OAuthClientPage(driver);

        login.open();
        login.LogIn("Admin", "admin123");

        oauthClient.openOAuthClientPage();
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
        runTest("Verify OAuth Client page opens successfully",
                () -> oauthClient.pageOpened(driver.getCurrentUrl()));
    }

    // TC_ADMIN_703 - Verify Admin can view the list of registered OAuth clients with essential metrics
    @Epic("Admin Page")
    @Feature("OAuth Client Management")
    @Story("TC_ADMIN_703 - Verify Admin can view the list of registered OAuth clients with essential metrics")
    @Test
    public void verifyOAuthClientListDisplayed() {
        runTest("TC_ADMIN_703 - Verify Admin can view the list of registered OAuth clients with essential metrics", () -> {
            Assert.assertTrue(oauthClient.isClientTableDisplayed(),
                    "OAuth Client table is not displayed.");
            return oauthClient.isClientTableDisplayed();
        });
    }

    // TC_ADMIN_703 - Verify required columns (Name, Redirect URI, Status, Actions) are visible
    @Epic("Admin Page")
    @Feature("OAuth Client Management")
    @Story("TC_ADMIN_703 - Verify required columns Name, Redirect URI, Status and Actions are visible")
    @Test
    public void verifyRequiredColumnsDisplayed() {
        runTest("TC_ADMIN_703 - Verify required columns Name, Redirect URI, Status and Actions are visible", () -> {
            Assert.assertTrue(oauthClient.areRequiredColumnsDisplayed(),
                    "One or more required columns (Name, Redirect URI, Status) are missing.");
            return oauthClient.areRequiredColumnsDisplayed();
        });
    }

    // TC_ADMIN_704 - Verify successful registration of a new OAuth client with valid parameters
    @Epic("Admin Page")
    @Feature("OAuth Client Management")
    @Story("TC_ADMIN_704 - Verify Admin can successfully register a new OAuth client with all valid fields")
    @Test
    public void verifyAddOAuthClient() {
        String clientName = "MobileAppClient " + System.currentTimeMillis();
        runTest("TC_ADMIN_704 - Verify Admin can successfully register a new OAuth client with all valid fields", () -> {
            oauthClient.createClientWithTogglesOn(
                    clientName,
                    "https://my-authorized-app.com/oauth/callback"
            );
            Assert.assertTrue(oauthClient.isSuccessMessageDisplayed(),
                    "Expected success message not found after adding OAuth client.");
            return oauthClient.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_705 - Verify validation errors when mandatory fields are omitted
    @Epic("Admin Page")
    @Feature("OAuth Client Management")
    @Story("TC_ADMIN_705 - Verify Required validation errors appear when Name and Redirect URI are left empty")
    @Test
    public void verifyRequiredValidationAllFieldsEmpty() {
        runTest("TC_ADMIN_705 - Verify Required validation errors appear when Name and Redirect URI are left empty", () -> {
            oauthClient.submitAddFormEmpty();
            Assert.assertTrue(oauthClient.areMultipleRequiredErrorsDisplayed(),
                    "Expected multiple 'Required' validation errors when all mandatory fields are blank.");
            return oauthClient.areMultipleRequiredErrorsDisplayed();
        });
    }

    // TC_ADMIN_706 - Verify invalid URL format triggers a validation error
    @Epic("Admin Page")
    @Feature("OAuth Client Management")
    @Story("TC_ADMIN_706 - Verify that an invalid Redirect URI format triggers an Invalid URL validation error")
    @Test
    public void verifyInvalidUrlValidation() {
        runTest("TC_ADMIN_706 - Verify that an invalid Redirect URI format triggers an Invalid URL validation error", () -> {
            oauthClient.submitWithInvalidUrl("TestingClient", "invalid_url_string_example");
            Assert.assertTrue(oauthClient.isInvalidUrlErrorDisplayed(),
                    "Expected 'Invalid URL' validation error was not displayed for malformed Redirect URI.");
            return oauthClient.isInvalidUrlErrorDisplayed();
        });
    }

    // TC_ADMIN_707 - Verify duplicate client name triggers Already Exists error
    @Epic("Admin Page")
    @Feature("OAuth Client Management")
    @Story("TC_ADMIN_707 - Verify system unique-constraint check prevents registering a duplicate client name")
    @Test
    public void verifyDuplicateClientNameValidation() {
        runTest("TC_ADMIN_707 - Verify system unique-constraint check prevents registering a duplicate client name", () -> {
            oauthClient.submitDuplicateName(
                    "MobileAppClient",
                    "https://my-authorized-app.com/oauth/callback"
            );
            Assert.assertTrue(oauthClient.isAlreadyExistsErrorDisplayed(),
                    "Expected 'Already exists' error was not displayed for duplicate client name.");
            return oauthClient.isAlreadyExistsErrorDisplayed();
        });
    }

    // TC_ADMIN_708 - Verify Admin can edit an existing OAuth client's metadata
    @Epic("Admin Page")
    @Feature("OAuth Client Management")
    @Story("TC_ADMIN_708 - Verify Admin can update the Redirect URI of an existing OAuth client successfully")
    @Test
    public void verifyEditOAuthClient() {
        runTest("TC_ADMIN_708 - Verify Admin can update the Redirect URI of an existing OAuth client successfully", () -> {
            oauthClient.editFirstClient("https://new-endpoint.com/oauth2/callback");
            Assert.assertTrue(oauthClient.isSuccessMessageDisplayed(),
                    "Expected success message not found after editing OAuth client.");
            return oauthClient.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_709 - Verify permanent removal of an OAuth client after confirmation
    @Epic("Admin Page")
    @Feature("OAuth Client Management")
    @Story("TC_ADMIN_709 - Verify Admin can permanently delete an OAuth client after confirming the prompt")
    @Test
    public void verifyDeleteOAuthClient() {
        runTest("TC_ADMIN_709 - Verify Admin can permanently delete an OAuth client after confirming the prompt", () -> {
            oauthClient.deleteFirstClient();
            Assert.assertTrue(oauthClient.isSuccessMessageDisplayed(),
                    "Expected success message not found after deleting OAuth client.");
            return oauthClient.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_709 (dialog) - Verify confirmation dialog appears before deletion
    @Epic("Admin Page")
    @Feature("OAuth Client Management")
    @Story("TC_ADMIN_709 - Verify confirmation dialog appears when deleting an OAuth client")
    @Test
    public void verifyDeleteConfirmationDialog() {
        runTest("TC_ADMIN_709 - Verify confirmation dialog appears when deleting an OAuth client", () -> {
            driver.findElement(
                    org.openqa.selenium.By.xpath("(//i[contains(@class,'bi-trash')])[1]")
            ).click();
            Assert.assertTrue(oauthClient.isConfirmationDialogDisplayed(),
                    "Confirmation dialog with Yes/No options was not displayed.");
            return oauthClient.isConfirmationDialogDisplayed();
        });
    }

    // TC_ADMIN_709 (cancel) - Verify canceling delete keeps client intact
    @Epic("Admin Page")
    @Feature("OAuth Client Management")
    @Story("TC_ADMIN_709 - Verify canceling the delete confirmation keeps the OAuth client record intact")
    @Test
    public void verifyCancelDelete() {
        runTest("TC_ADMIN_709 - Verify canceling the delete confirmation keeps the OAuth client record intact", () -> {
            oauthClient.cancelDelete();
            Assert.assertTrue(oauthClient.isClientTableDisplayed(),
                    "OAuth Client table should still be displayed after canceling delete.");
            return oauthClient.isClientTableDisplayed();
        });
    }

    // TC_ADMIN_710 - Verify status toggle updates the client status successfully
    @Epic("Admin Page")
    @Feature("OAuth Client Management")
    @Story("TC_ADMIN_710 - Verify Admin can toggle an OAuth client status between Enabled and Disabled")
    @Test
    public void verifyStatusToggle() {
        runTest("TC_ADMIN_710 - Verify Admin can toggle an OAuth client status between Enabled and Disabled", () -> {
            oauthClient.editFirstClientStatusToggle();
            Assert.assertTrue(oauthClient.isSuccessMessageDisplayed(),
                    "Expected success message not found after toggling OAuth client status.");
            return oauthClient.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_711 - Verify non-admin roles are blocked from accessing the OAuth Clients page
    @Epic("Admin Page")
    @Feature("OAuth Client Management")
    @Story("TC_ADMIN_711 - Verify non-admin users are redirected when accessing OAuth Clients URL directly")
    @Test
    public void verifyNonAdminAccessBlocked() {
        runTest("TC_ADMIN_711 - Verify non-admin users are redirected when accessing OAuth Clients URL directly", () -> {
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/logout");
            login.LogIn("Ess", "Ess@123");
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/oauthClients");
            Assert.assertFalse(
                    driver.getCurrentUrl().contains("oauthClients"),
                    "Non-admin user should not be able to access the OAuth Clients page.");
            return !driver.getCurrentUrl().contains("oauthClients");
        });
    }

    // TC_ADMIN_712 - Verify OAuth client configurations persist after page refresh
    @Epic("Admin Page")
    @Feature("OAuth Client Management")
    @Story("TC_ADMIN_712 - Verify OAuth client configurations persist after a hard page refresh")
    @Test
    public void verifyConfigPersistsAfterRefresh() {
        runTest("TC_ADMIN_712 - Verify OAuth client configurations persist after a hard page refresh", () -> {
            driver.navigate().refresh();
            Assert.assertTrue(oauthClient.isClientTableDisplayed(),
                    "OAuth Client table should still be displayed after page refresh.");
            return oauthClient.isClientTableDisplayed();
        });
    }

    // TC_ADMIN_712 - Verify OAuth client configurations persist after logout and re-login
    @Epic("Admin Page")
    @Feature("OAuth Client Management")
    @Story("TC_ADMIN_712 - Verify OAuth client configurations persist after logout and re-login")
    @Test
    public void verifyConfigPersistsAfterReLogin() {
        String clientName = "PersistenceClient " + System.currentTimeMillis();
        runTest("TC_ADMIN_712 - Verify OAuth client configurations persist after logout and re-login", () -> {
            oauthClient.createClientWithTogglesOn(
                    clientName,
                    "https://persistence-check.com/oauth/callback"
            );
            Assert.assertTrue(oauthClient.isSuccessMessageDisplayed(),
                    "Client should be created successfully before testing persistence.");
            // Logout and re-login
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/logout");
            login.LogIn("Admin", "admin123");
            oauthClient.openOAuthClientPage();
            Assert.assertTrue(oauthClient.isClientTableDisplayed(),
                    "OAuth Client table should still be present after re-login — data should persist.");
            return oauthClient.isClientTableDisplayed();
        });
    }
}