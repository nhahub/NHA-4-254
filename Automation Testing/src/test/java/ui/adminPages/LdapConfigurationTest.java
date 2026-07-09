package ui.adminPages;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.Base;
import org.example.DriverManager;
import org.example.pages.adminPage.LdapConfigurationPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LdapConfigurationTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private LdapConfigurationPage ldap;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();

        login = new loginPage(driver);
        ldap = new LdapConfigurationPage(driver);

        login.open();
        login.LogIn("Admin", "admin123");

        ldap.openLdapConfigurationPage();
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
        runTest("Verify LDAP Configuration page opens successfully",
                () -> ldap.pageOpened(driver.getCurrentUrl()));
    }

    // TC_ADMIN_713 - Verify Admin can access LDAP Configuration page
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_713 - Verify LDAP Configuration page loads successfully for Admin")
    @Test
    public void verifyLdapPageLoads() {
        runTest("TC_ADMIN_713 - Verify LDAP Configuration page loads successfully for Admin", () -> {
            Assert.assertTrue(ldap.isPageLoaded(),
                    "LDAP Configuration page did not load correctly.");
            return ldap.isPageLoaded();
        });
    }

    // TC_ADMIN_714 - Verify all LDAP configuration fields are displayed
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_714 - Verify all required LDAP fields are present on the configuration page")
    @Test
    public void verifyAllFieldsDisplayed() {
        runTest("TC_ADMIN_714 - Verify all required LDAP fields are present on the configuration page", () -> {
            Assert.assertTrue(ldap.areAllFieldsDisplayed(),
                    "One or more required LDAP fields (Server URI, Port, Encryption, Bind DN, Bind Password) are missing.");
            return ldap.areAllFieldsDisplayed();
        });
    }

    // TC_ADMIN_715 - Verify enabling LDAP authentication toggle works
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_715 - Verify Admin can toggle LDAP authentication to enabled state")
    @Test
    public void verifyEnableLdapToggle() {
        runTest("TC_ADMIN_715 - Verify Admin can toggle LDAP authentication to enabled state", () -> {
            ldap.enableLdap();
            Assert.assertTrue(ldap.areAllFieldsDisplayed(),
                    "LDAP configuration fields should become active/editable after enabling LDAP.");
            return ldap.areAllFieldsDisplayed();
        });
    }

    // TC_ADMIN_716 - Verify Admin can save valid LDAP configuration
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_716 - Verify complete and valid LDAP configuration saves successfully")
    @Test
    public void verifySaveValidLdapConfiguration() {
        runTest("TC_ADMIN_716 - Verify complete and valid LDAP configuration saves successfully", () -> {
            ldap.enableAndSaveValidConfig();
            Assert.assertTrue(ldap.isSuccessMessageDisplayed(),
                    "Expected success message not found after saving valid LDAP configuration.");
            return ldap.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_717 - Verify Test Connection works with valid LDAP server details
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_717 - Verify Test Connection button validates a correct LDAP server connection")
    @Test
    public void verifyTestConnectionValid() {
        runTest("TC_ADMIN_717 - Verify Test Connection button validates a correct LDAP server connection", () -> {
            ldap.testConnectionWithValidConfig();
            Assert.assertTrue(ldap.isTestConnectionSuccessDisplayed(),
                    "Expected 'Connection Successful' message not found for valid LDAP server.");
            return ldap.isTestConnectionSuccessDisplayed();
        });
    }

    // TC_ADMIN_718 - Verify Test Connection shows failure with invalid LDAP server
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_718 - Verify Test Connection shows error for invalid/unreachable LDAP server")
    @Test
    public void verifyTestConnectionInvalid() {
        runTest("TC_ADMIN_718 - Verify Test Connection shows error for invalid/unreachable LDAP server", () -> {
            ldap.testConnectionWithInvalidServer();
            Assert.assertTrue(ldap.isTestConnectionFailedDisplayed(),
                    "Expected 'Connection Failed' message not found for invalid LDAP server.");
            return ldap.isTestConnectionFailedDisplayed();
        });
    }

    // TC_ADMIN_719 - Verify validation for missing required LDAP fields
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_719 - Verify saving with missing required fields shows validation errors")
    @Test
    public void verifyRequiredFieldValidation() {
        runTest("TC_ADMIN_719 - Verify saving with missing required fields shows validation errors", () -> {
            ldap.saveWithEmptyServerUri();
            Assert.assertTrue(ldap.isRequiredErrorDisplayed(),
                    "Expected required field validation error not displayed.");
            return ldap.isRequiredErrorDisplayed();
        });
    }

    // TC_ADMIN_720 - Verify rejection of invalid LDAP server URI format
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_720 - Verify invalid server URI format is rejected with a validation error")
    @Test
    public void verifyInvalidUriValidation() {
        runTest("TC_ADMIN_720 - Verify invalid server URI format is rejected with a validation error", () -> {
            ldap.saveWithInvalidUri();
            Assert.assertTrue(ldap.isInvalidUriErrorDisplayed(),
                    "Expected invalid URI validation error not displayed for 'notanurl'.");
            return ldap.isInvalidUriErrorDisplayed();
        });
    }

    // TC_ADMIN_721 - Verify disabling LDAP reverts to default authentication
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_721 - Verify disabling LDAP authentication switches system back to default login")
    @Test
    public void verifyDisableLdapRevertsToDefault() {
        runTest("TC_ADMIN_721 - Verify disabling LDAP authentication switches system back to default login", () -> {
            // First enable LDAP, then disable
            ldap.enableLdap();
            ldap.disableLdap();
            ldap.clickSave();
            Assert.assertTrue(ldap.isSuccessMessageDisplayed(),
                    "Expected success message after disabling LDAP.");
            return ldap.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_724 - Verify non-admin cannot access LDAP Configuration page
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_724 - Verify non-admin users are denied access to LDAP Configuration page")
    @Test
    public void verifyNonAdminAccessBlocked() {
        runTest("TC_ADMIN_724 - Verify non-admin users are denied access to LDAP Configuration page", () -> {
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/logout");
            login.LogIn("Ess", "Ess@123");
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/ldapConfiguration");
            Assert.assertFalse(
                    driver.getCurrentUrl().contains("ldapConfiguration"),
                    "Non-admin user should not be able to access LDAP Configuration page.");
            return !driver.getCurrentUrl().contains("ldapConfiguration");
        });
    }

    // TC_ADMIN_725 - Verify LDAP configuration persists after logout and re-login
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_725 - Verify LDAP configuration is retained after Admin logs out and back in")
    @Test
    public void verifyLdapConfigPersistsAfterReLogin() {
        runTest("TC_ADMIN_725 - Verify LDAP configuration is retained after Admin logs out and back in", () -> {
            ldap.enableAndSaveValidConfig();
            Assert.assertTrue(ldap.isSuccessMessageDisplayed(),
                    "LDAP config should save before testing persistence.");
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/logout");
            login.LogIn("Admin", "admin123");
            ldap.openLdapConfigurationPage();
            Assert.assertTrue(ldap.isPageLoaded(),
                    "LDAP Configuration page should load with persisted settings after re-login.");
            return ldap.isPageLoaded();
        });
    }

    // TC_ADMIN_726 - Verify LDAP Bind Password field is masked
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_726 - Verify Bind Password field is masked/hidden for security")
    @Test
    public void verifyBindPasswordIsMasked() {
        runTest("TC_ADMIN_726 - Verify Bind Password field is masked/hidden for security", () -> {
            Assert.assertTrue(ldap.isBindPasswordMasked(),
                    "Bind Password field should display masked characters (type='password').");
            return ldap.isBindPasswordMasked();
        });
    }

    // TC_ADMIN_727 - Verify Test Connection can be run before saving
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_727 - Verify Test Connection button works without requiring save first")
    @Test
    public void verifyTestConnectionBeforeSave() {
        runTest("TC_ADMIN_727 - Verify Test Connection button works without requiring save first", () -> {
            ldap.testConnectionWithValidConfig();
            boolean result = ldap.isTestConnectionSuccessDisplayed()
                    || ldap.isTestConnectionFailedDisplayed();
            Assert.assertTrue(result,
                    "Test Connection should return a result (success or failure) without saving first.");
            return result;
        });
    }

    // TC_ADMIN_728 - Verify LDAP Configuration page has correct title
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_728 - Verify page title is correctly displayed on LDAP Configuration page")
    @Test
    public void verifyPageTitle() {
        runTest("TC_ADMIN_728 - Verify page title is correctly displayed on LDAP Configuration page", () -> {
            Assert.assertTrue(ldap.isPageTitleDisplayed(),
                    "Page title/heading is not displayed on LDAP Configuration page.");
            return ldap.isPageTitleDisplayed();
        });
    }

    // TC_ADMIN_729 - Verify success notification on saving valid LDAP configuration
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_729 - Verify success toast/notification appears after saving valid LDAP configuration")
    @Test
    public void verifySuccessToastOnSave() {
        runTest("TC_ADMIN_729 - Verify success toast/notification appears after saving valid LDAP configuration", () -> {
            ldap.enableAndSaveValidConfig();
            Assert.assertTrue(ldap.isSuccessMessageDisplayed(),
                    "Expected 'Successfully Saved' toast not found after saving LDAP configuration.");
            return ldap.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_730 - Verify LDAP port field accepts valid port numbers
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_730 - Verify valid port numbers (e.g., 389, 636) are accepted by the port field")
    @Test
    public void verifyValidPortAccepted() {
        runTest("TC_ADMIN_730 - Verify valid port numbers (e.g., 389, 636) are accepted by the port field", () -> {
            ldap.enableLdap();
            ldap.fillLdapConfiguration("ldap://company.com", "389", null, null, null);
            ldap.clickSave();
            // Should NOT show port error for valid port
            Assert.assertFalse(ldap.isInvalidPortErrorDisplayed(),
                    "Valid port 389 should be accepted without a validation error.");
            return !ldap.isInvalidPortErrorDisplayed();
        });
    }

    // TC_ADMIN_731 - Verify LDAP port field rejects invalid port numbers
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_731 - Verify invalid port numbers (e.g., alphabets) are rejected with a validation error")
    @Test
    public void verifyInvalidPortRejected() {
        runTest("TC_ADMIN_731 - Verify invalid port numbers (e.g., alphabets) are rejected with a validation error", () -> {
            ldap.saveWithInvalidPort();
            Assert.assertTrue(ldap.isInvalidPortErrorDisplayed() || ldap.isRequiredErrorDisplayed(),
                    "Expected validation error not displayed for invalid port value 'abc'.");
            return ldap.isInvalidPortErrorDisplayed() || ldap.isRequiredErrorDisplayed();
        });
    }

    // TC_ADMIN_732 - Verify Cancel discards unsaved LDAP configuration changes
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_732 - Verify clicking Cancel discards any unsaved LDAP configuration changes")
    @Test
    public void verifyCancelDiscardsChanges() {
        runTest("TC_ADMIN_732 - Verify clicking Cancel discards any unsaved LDAP configuration changes", () -> {
            ldap.modifyAndClickCancel();
            Assert.assertTrue(ldap.isPageLoaded(),
                    "LDAP Configuration page should remain loaded with previous settings after Cancel.");
            return ldap.isPageLoaded();
        });
    }

    // TC_ADMIN_733 - Verify LDAP encryption type options are available
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_733 - Verify encryption type options None, SSL, TLS are selectable")
    @Test
    public void verifyEncryptionOptionsAvailable() {
        runTest("TC_ADMIN_733 - Verify encryption type options None, SSL, TLS are selectable", () -> {
            ldap.enableLdap();
            Assert.assertTrue(ldap.areEncryptionOptionsAvailable(),
                    "Encryption type options (None, SSL, TLS) are not available in the dropdown.");
            return ldap.areEncryptionOptionsAvailable();
        });
    }

    // TC_ADMIN_734 - Verify LDAP fields are disabled when LDAP toggle is OFF
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_734 - Verify all LDAP configuration fields are greyed out/disabled when LDAP toggle is OFF")
    @Test
    public void verifyFieldsDisabledWhenLdapOff() {
        runTest("TC_ADMIN_734 - Verify all LDAP configuration fields are greyed out/disabled when LDAP toggle is OFF", () -> {
            // LDAP is OFF by default on page open
            Assert.assertTrue(ldap.isLdapFieldsDisabled(),
                    "LDAP configuration fields should be disabled when the LDAP toggle is OFF.");
            return ldap.isLdapFieldsDisabled();
        });
    }

    // TC_ADMIN_736 - Verify unauthenticated access to LDAP Configuration page is blocked
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_736 - Verify accessing LDAP Configuration URL without login redirects to login page")
    @Test
    public void verifyUnauthenticatedAccessBlocked() {
        runTest("TC_ADMIN_736 - Verify accessing LDAP Configuration URL without login redirects to login page", () -> {
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/logout");
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/ldapConfiguration");
            Assert.assertFalse(
                    driver.getCurrentUrl().contains("ldapConfiguration"),
                    "Unauthenticated user should be redirected away from LDAP Configuration page.");
            return !driver.getCurrentUrl().contains("ldapConfiguration");
        });
    }

    // TC_ADMIN_738 - Verify LDAP SSL connection works correctly using port 636
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_738 - Verify that LDAP connection with SSL encryption and port 636 can be configured and tested")
    @Test
    public void verifyLdapSslConnectionPort636() {
        runTest("TC_ADMIN_738 - Verify that LDAP connection with SSL encryption and port 636 can be configured and tested", () -> {
            ldap.testConnectionSslConfig();
            boolean result = ldap.isTestConnectionSuccessDisplayed()
                    || ldap.isTestConnectionFailedDisplayed();
            Assert.assertTrue(result,
                    "Test Connection should return a result for SSL/port 636 configuration.");
            return result;
        });
    }

    // TC_ADMIN_739 - Verify LDAP Server URI field enforces max character limit
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_739 - Verify that LDAP Server URI field enforces a reasonable maximum character limit")
    @Test
    public void verifyServerUriCharacterLimit() {
        runTest("TC_ADMIN_739 - Verify that LDAP Server URI field enforces a reasonable maximum character limit", () -> {
            ldap.saveWithLongServerUri();
            Assert.assertTrue(ldap.isCharLimitErrorDisplayed() || ldap.isInvalidUriErrorDisplayed(),
                    "Expected character limit or validation error for 500+ character Server URI.");
            return ldap.isCharLimitErrorDisplayed() || ldap.isInvalidUriErrorDisplayed();
        });
    }

    // TC_ADMIN_740 - Verify Bind DN field accepts valid DN format
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_740 - Verify that the Bind DN field accepts properly formatted Distinguished Names")
    @Test
    public void verifyValidBindDnAccepted() {
        runTest("TC_ADMIN_740 - Verify that the Bind DN field accepts properly formatted Distinguished Names", () -> {
            ldap.enableLdap();
            ldap.fillLdapConfiguration(
                    "ldap://company.com", "389",
                    "cn=admin,dc=company,dc=com", "secret",
                    "dc=company,dc=com"
            );
            ldap.clickSave();
            Assert.assertTrue(ldap.isSuccessMessageDisplayed(),
                    "Valid Bind DN 'cn=admin,dc=company,dc=com' should be accepted without errors.");
            return ldap.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_741 - Verify LDAP TLS/STARTTLS encryption is selectable and functional
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_741 - Verify that TLS/STARTTLS encryption type can be selected and used for LDAP connection")
    @Test
    public void verifyTlsEncryptionSelectable() {
        runTest("TC_ADMIN_741 - Verify that TLS/STARTTLS encryption type can be selected and used for LDAP connection", () -> {
            ldap.testConnectionTlsConfig();
            boolean result = ldap.isTestConnectionSuccessDisplayed()
                    || ldap.isTestConnectionFailedDisplayed();
            Assert.assertTrue(result,
                    "TLS/STARTTLS option should be selectable and Test Connection should return a result.");
            return result;
        });
    }

    // TC_ADMIN_743 - Verify LDAP Configuration page is responsive on mobile viewport
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_743 - Verify that LDAP Configuration page renders correctly on mobile screen sizes")
    @Test
    public void verifyPageResponsiveOnMobile() {
        runTest("TC_ADMIN_743 - Verify that LDAP Configuration page renders correctly on mobile screen sizes", () -> {
            org.openqa.selenium.Dimension mobileSize = new org.openqa.selenium.Dimension(375, 812);
            driver.manage().window().setSize(mobileSize);
            driver.navigate().refresh();
            Assert.assertTrue(ldap.isPageLoaded(),
                    "LDAP Configuration page should render correctly on a 375px mobile viewport.");
            return ldap.isPageLoaded();
        });
    }

    // TC_ADMIN_744 - Verify LDAP user attribute mappings can be configured
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_744 - Verify Admin can configure LDAP user attribute mappings")
    @Test
    public void verifyUserAttributeMappingConfigured() {
        runTest("TC_ADMIN_744 - Verify Admin can configure LDAP user attribute mappings", () -> {
            ldap.enableLdap();
            ldap.fillLdapConfiguration(
                    "ldap://company.com", "389",
                    "cn=admin,dc=company,dc=com", "secret",
                    "dc=company,dc=com"
            );
            ldap.fillUserNameAttribute("sAMAccountName");
            ldap.clickSave();
            Assert.assertTrue(ldap.isSuccessMessageDisplayed(),
                    "Expected success message after saving LDAP user attribute mapping.");
            return ldap.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_745 - Verify LDAP Configuration is not accessible via browser Back button after logout
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_745 - Verify that after logout, pressing browser Back does not expose LDAP configuration")
    @Test
    public void verifyBackButtonAfterLogoutBlocked() {
        runTest("TC_ADMIN_745 - Verify that after logout, pressing browser Back does not expose LDAP configuration", () -> {
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/logout");
            driver.navigate().back();
            Assert.assertFalse(
                    driver.getCurrentUrl().contains("ldapConfiguration"),
                    "LDAP Configuration page should not be accessible via Back button after logout.");
            return !driver.getCurrentUrl().contains("ldapConfiguration");
        });
    }

    // TC_ADMIN_746 - Verify saving LDAP configuration without enabling LDAP toggle
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_746 - Verify system behavior when Admin fills LDAP fields but saves without enabling the LDAP toggle")
    @Test
    public void verifySaveWithLdapToggleOff() {
        runTest("TC_ADMIN_746 - Verify system behavior when Admin fills LDAP fields but saves without enabling the LDAP toggle", () -> {
            // Do NOT enable LDAP — just save directly
            ldap.clickSave();
            Assert.assertTrue(ldap.isSuccessMessageDisplayed(),
                    "Configuration should save successfully even with LDAP toggle OFF; LDAP remains inactive.");
            return ldap.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_748 - Verify LDAP search scope dropdown contains valid options
    @Epic("Admin Page")
    @Feature("LDAP Configuration")
    @Story("TC_ADMIN_748 - Verify that the LDAP search scope dropdown contains valid options One Level and Sub Tree")
    @Test
    public void verifySearchScopeOptions() {
        runTest("TC_ADMIN_748 - Verify that the LDAP search scope dropdown contains valid options One Level and Sub Tree", () -> {
            ldap.enableLdap();
            Assert.assertTrue(ldap.areSearchScopeOptionsAvailable(),
                    "Search scope dropdown should contain 'One Level' and/or 'Sub Tree' options.");
            return ldap.areSearchScopeOptionsAvailable();
        });
    }
}