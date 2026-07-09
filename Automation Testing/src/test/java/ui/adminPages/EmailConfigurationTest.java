//package tests.admin;
//
//import io.qameta.allure.Epic;
//import io.qameta.allure.Feature;
//import io.qameta.allure.Story;
//import org.example.Base;
//import org.example.DriverManager;
//import org.example.pages.adminPage.EmailConfigurationPage;
//import org.example.pages.loginPage;
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//
//@Epic("Admin Page")
//@Feature("Email Configuration")
//public class EmailConfigurationTest extends Base {
//    private WebDriver driver;
//    private loginPage login;
//    private EmailConfigurationPage emailConfiguration;
//
//    @BeforeMethod
//    public void setupPage() {
//
//        driver = DriverManager.getInstance().getDriver();
//
//        login = new loginPage(driver);
//        emailConfiguration = new EmailConfigurationPage(driver);
//
//        login.open();
//        login.LogIn("Admin", "admin123");
//
//        emailConfiguration.openEmailConfigurationPage();
//    }
//
//    @Epic("Admin Page")
//    @Feature("Email Configuration")
//    @Story("TC_ADMIN_474 Verify Admin can navigate to Email Configuration page")
//    @Test
//    public void verifyNavigateToEmailConfigurationPage() {
//
//        runTest("TC_ADMIN_474 Verify Admin can navigate to Email Configuration page", () -> {
//
//            Assert.assertTrue(
//                    emailConfig.isEmailConfigurationPageDisplayed(),
//                    "Email Configuration page not displayed");
//
//            return emailConfig.isEmailConfigurationPageDisplayed();
//        });
//    }
//
//    @Epic("Admin Page")
//    @Feature("Email Configuration")
//    @Story("TC_ADMIN_475 Verify system displays current email configuration settings")
//    @Test
//    public void verifyCurrentEmailSettingsDisplayed() {
//
//        runTest("TC_ADMIN_475 Verify system displays current email configuration settings", () -> {
//
//            Assert.assertTrue(
//                    emailConfig.areCurrentSettingsDisplayed(),
//                    "Current email settings are not displayed");
//
//            return emailConfig.areCurrentSettingsDisplayed();
//        });
//    }
//
//    @Epic("Admin Page")
//    @Feature("Email Configuration")
//    @Story("TC_ADMIN_476 Verify Mail Sent As field is visible")
//    @Test
//    public void verifyMailSentAsFieldVisible() {
//
//        runTest("TC_ADMIN_476 Verify Mail Sent As field is visible", () -> {
//
//            Assert.assertTrue(
//                    emailConfig.isMailSentAsFieldDisplayed(),
//                    "Mail Sent As field not displayed");
//
//            return emailConfig.isMailSentAsFieldDisplayed();
//        });
//    }
//
//    @Epic("Admin Page")
//    @Feature("Email Configuration")
//    @Story("TC_ADMIN_477 Verify Sending Method field is visible")
//    @Test
//    public void verifySendingMethodFieldVisible() {
//
//        runTest("TC_ADMIN_477 Verify Sending Method field is visible", () -> {
//
//            Assert.assertTrue(
//                    emailConfig.isSendingMethodDisplayed(),
//                    "Sending Method field not displayed");
//
//            return emailConfig.isSendingMethodDisplayed();
//        });
//    }
//
//    @Epic("Admin Page")
//    @Feature("Email Configuration")
//    @Story("TC_ADMIN_478 Verify SMTP Host field is visible")
//    @Test
//    public void verifySmtpHostFieldVisible() {
//
//        runTest("TC_ADMIN_478 Verify SMTP Host field is visible", () -> {
//
//            Assert.assertTrue(
//                    emailConfig.isSmtpHostDisplayed(),
//                    "SMTP Host field not displayed");
//
//            return emailConfig.isSmtpHostDisplayed();
//        });
//    }
//
//    @Epic("Admin Page")
//    @Feature("Email Configuration")
//    @Story("TC_ADMIN_479 Verify SMTP Port field is visible")
//    @Test
//    public void verifySmtpPortFieldVisible() {
//
//        runTest("TC_ADMIN_479 Verify SMTP Port field is visible", () -> {
//
//            Assert.assertTrue(
//                    emailConfig.isSmtpPortDisplayed(),
//                    "SMTP Port field not displayed");
//
//            return emailConfig.isSmtpPortDisplayed();
//        });
//    }
//
//    @Epic("Admin Page")
//    @Feature("Email Configuration")
//    @Story("TC_ADMIN_480 Verify authentication settings are visible")
//    @Test
//    public void verifyAuthenticationSectionVisible() {
//
//        runTest("TC_ADMIN_480 Verify authentication settings are visible", () -> {
//
//            Assert.assertTrue(
//                    emailConfig.isAuthenticationSectionDisplayed(),
//                    "Authentication section not displayed");
//
//            return emailConfig.isAuthenticationSectionDisplayed();
//        });
//    }
//
//    @Epic("Admin Page")
//    @Feature("Email Configuration")
//    @Story("TC_ADMIN_481 Verify security protocol settings are visible")
//    @Test
//    public void verifySecurityProtocolVisible() {
//
//        runTest("TC_ADMIN_481 Verify security protocol settings are visible", () -> {
//
//            Assert.assertTrue(
//                    emailConfig.isSecurityProtocolDisplayed(),
//                    "Security Protocol section not displayed");
//
//            return emailConfig.isSecurityProtocolDisplayed();
//        });
//    }
//
//    @Epic("Admin Page")
//    @Feature("Email Configuration")
//    @Story("TC_ADMIN_482 Verify authentication section remains visible after page refresh")
//    @Test
//    public void verifyAuthenticationSectionAfterRefresh() {
//
//        runTest("TC_ADMIN_482 Verify authentication section remains visible after page refresh", () -> {
//
//            emailConfig.refreshPage();
//
//            Assert.assertTrue(
//                    emailConfig.isAuthenticationSectionDisplayed(),
//                    "Authentication section disappeared after refresh");
//
//            return emailConfig.isAuthenticationSectionDisplayed();
//        });
//    }
//
//    @Epic("Admin Page")
//    @Feature("Email Configuration")
//    @Story("TC_ADMIN_483 Verify navigation layout returns correctly after switching from mobile to desktop view")
//    @Test
//    public void verifyNavigationLayoutAfterResize() {
//
//        runTest("TC_ADMIN_483 Verify navigation layout returns correctly after switching from mobile to desktop view", () -> {
//
//            emailConfig.switchToMobileView();
//            emailConfig.switchToDesktopView();
//
//            Assert.assertTrue(
//                    emailConfig.isDesktopLayoutDisplayed(),
//                    "Desktop layout not restored");
//
//            return emailConfig.isDesktopLayoutDisplayed();
//        });
//    }
//
//    @Epic("Admin Page")
//    @Feature("Email Configuration")
//    @Story("TC_ADMIN_484 Verify email settings load performance")
//    @Test
//    public void verifyEmailSettingsLoadPerformance() {
//
//        runTest("TC_ADMIN_484 Verify email settings load performance", () -> {
//
//            Assert.assertTrue(
//                    emailConfig.isPageLoadedWithinAcceptableTime(),
//                    "Email settings loading time exceeded threshold");
//
//            return emailConfig.isPageLoadedWithinAcceptableTime();
//        });
//    }
//
//    @Epic("Admin Page")
//    @Feature("Email Configuration")
//    @Story("TC_ADMIN_485 Verify page loads correctly when email configuration is empty")
//    @Test
//    public void verifyPageLoadsWithEmptyConfiguration() {
//
//        runTest("TC_ADMIN_485 Verify page loads correctly when email configuration is empty", () -> {
//
//            Assert.assertTrue(
//                    emailConfig.isEmailConfigurationPageDisplayed(),
//                    "Page failed to load with empty configuration");
//
//            return emailConfig.isEmailConfigurationPageDisplayed();
//        });
//    }
//
//    @Epic("Admin Page")
//    @Feature("Email Configuration")
//    @Story("TC_ADMIN_486 Verify security protocol settings visibility")
//    @Test
//    public void verifySecurityProtocolSettingsVisibility() {
//
//        runTest("TC_ADMIN_486 Verify security protocol settings visibility", () -> {
//
//            Assert.assertTrue(
//                    emailConfig.isSecurityProtocolDisplayed(),
//                    "Security protocol settings not visible");
//
//            return emailConfig.isSecurityProtocolDisplayed();
//        });
//    }
//
//    @Epic("Admin Page")
//    @Feature("Email Configuration")
//    @Story("TC_ADMIN_487 Verify authentication credentials are not displayed in plain text")
//    @Test
//    public void verifyPasswordMasked() {
//
//        runTest("TC_ADMIN_487 Verify authentication credentials are not displayed in plain text", () -> {
//
//            Assert.assertTrue(
//                    emailConfig.isPasswordMasked(),
//                    "Password is displayed in plain text");
//
//            return emailConfig.isPasswordMasked();
//        });
//    }
//
//    @Epic("Admin Page")
//    @Feature("Email Configuration")
//    @Story("TC_ADMIN_488 Verify SMTP configuration fields accept valid input values")
//    @Test
//    public void verifyValidSmtpConfigurationInput() {
//
//        runTest("TC_ADMIN_488 Verify SMTP configuration fields accept valid input values", () -> {
//
//            emailConfig.fillSMTPConfiguration(
//                    "smtp.gmail.com",
//                    "587",
//                    "admin@test.com",
//                    "Test@123");
//
//            Assert.assertTrue(
//                    emailConfig.areSMTPFieldsFilledCorrectly(),
//                    "SMTP configuration values not accepted");
//
//            return emailConfig.areSMTPFieldsFilledCorrectly();
//        });
//    }
//
//    @Epic("Admin Page")
//    @Feature("Email Configuration")
//    @Story("TC_ADMIN_489 Verify system saves SMTP configuration successfully")
//    @Test
//    public void verifySaveSMTPConfiguration() {
//
//        runTest("TC_ADMIN_489 Verify system saves SMTP configuration successfully", () -> {
//
//            emailConfig.save();
//
//            Assert.assertTrue(
//                    emailConfig.isSuccessMessageDisplayed(),
//                    "SMTP configuration was not saved");
//
//            return emailConfig.isSuccessMessageDisplayed();
//        });
//    }
//
//    @Epic("Admin Page")
//    @Feature("Email Configuration")
//    @Story("TC_ADMIN_490 Verify success message after saving SMTP settings")
//    @Test
//    public void verifySuccessMessageAfterSave() {
//
//        runTest("TC_ADMIN_490 Verify success message after saving SMTP settings", () -> {
//
//            Assert.assertTrue(
//                    emailConfig.isSuccessMessageDisplayed(),
//                    "Success message not displayed");
//
//            return emailConfig.isSuccessMessageDisplayed();
//        });
//    }
//
//    @Epic("Admin Page")
//    @Feature("Email Configuration")
//    @Story("TC_ADMIN_491 Verify SMTP settings remain after browser refresh")
//    @Test
//    public void verifySettingsRemainAfterRefresh() {
//
//        runTest("TC_ADMIN_491 Verify SMTP settings remain after browser refresh", () -> {
//
//            emailConfig.refreshPage();
//
//            Assert.assertTrue(
//                    emailConfig.areSMTPSettingsPersisted(),
//                    "SMTP settings not retained after refresh");
//
//            return emailConfig.areSMTPSettingsPersisted();
//        });
//    }
//
//    @Epic("Admin Page")
//    @Feature("Email Configuration")
//    @Story("TC_ADMIN_492 Verify SMTP save operation with valid security protocols")
//    @Test
//    public void verifySaveWithTLSAndSSL() {
//
//        runTest("TC_ADMIN_492 Verify SMTP save operation with valid security protocols", () -> {
//
//            emailConfig.selectTLS();
//            emailConfig.save();
//
//            Assert.assertTrue(
//                    emailConfig.isSuccessMessageDisplayed(),
//                    "TLS configuration failed");
//
//            return emailConfig.isSuccessMessageDisplayed();
//        });
//    }
//
//    @Epic("Admin Page")
//    @Feature("Email Configuration")
//    @Story("TC_ADMIN_493 Verify success message behavior when test email sending fails")
//    @Test
//    public void verifySaveAndFailedEmailBehavior() {
//
//        runTest("TC_ADMIN_493 Verify success message behavior when test email sending fails", () -> {
//
//            Assert.assertTrue(
//                    emailConfig.isFailureAndSuccessMessageHandledProperly(),
//                    "System displayed conflicting messages");
//
//            return emailConfig.isFailureAndSuccessMessageHandledProperly();
//        });
//    }
//
//    @Epic("Admin Page")
//    @Feature("Email Configuration")
//    @Story("TC_ADMIN_494 Verify SMTP settings remain after logout and login")
//    @Test
//    public void verifySettingsPersistAfterRelogin() {
//
//        runTest("TC_ADMIN_494 Verify SMTP settings remain after logout and login", () -> {
//
//            emailConfig.logoutAndLogin();
//
//            Assert.assertTrue(
//                    emailConfig.areSMTPSettingsPersisted(),
//                    "SMTP settings lost after relogin");
//
//            return emailConfig.areSMTPSettingsPersisted();
//        });
//    }
//
//    @Epic("Admin Page")
//    @Feature("Email Configuration")
//    @Story("TC_ADMIN_495 Verify system validation for invalid SMTP Port values")
//    @Test
//    public void verifyInvalidSMTPPortValidation() {
//
//        runTest("TC_ADMIN_495 Verify system validation for invalid SMTP Port values", () -> {
//
//            emailConfig.enterSMTPPort("abc");
//            emailConfig.save();
//
//            Assert.assertTrue(
//                    emailConfig.isValidationMessageDisplayed(),
//                    "Validation message not displayed for invalid port");
//
//            return emailConfig.isValidationMessageDisplayed();
//        });
//    }
//}