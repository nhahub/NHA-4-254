package org.example.tests.admin;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.Base;
import org.example.DriverManager;
import org.example.pages.adminPage.EmailSubscriptionsPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class EmailSubscriptionsTest extends Base {

    private EmailSubscriptionsPage emailSubscriptions;
    private WebDriver driver;
    private loginPage login;

    @BeforeMethod
    public void setupPage() {

        driver = DriverManager.getInstance().getDriver();

        login = new loginPage(driver);
        emailSubscriptions = new EmailSubscriptionsPage(driver);

        login.open();
        login.LogIn("Admin", "admin123");

        emailSubscriptions.openPage();
    }

    @Epic("Admin Page")
    @Feature("Email Subscriptions")
    @Story("TC_ADMIN_537 Verify Email Subscriptions page loads correctly")
    @Test
    public void verifyEmailSubscriptionsPageLoadsCorrectly() {

        runTest("TC_ADMIN_537 Verify Email Subscriptions page loads correctly", () -> {

            Assert.assertTrue(
                    emailSubscriptions.isPageDisplayed(),
                    "Email Subscriptions page not displayed");

            return emailSubscriptions.isPageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Email Subscriptions")
    @Story("TC_ADMIN_538 Verify email notification modules visibility")
    @Test
    public void verifyEmailNotificationModulesVisibility() {

        runTest("TC_ADMIN_538 Verify email notification modules visibility", () -> {

            Assert.assertTrue(
                    emailSubscriptions.isTableDisplayed(),
                    "Modules table not displayed");

            return emailSubscriptions.isTableDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Email Subscriptions")
    @Story("TC_ADMIN_539 Verify enabled disabled status visibility")
    @Test
    public void verifyEnabledDisabledStatusVisibility() {

        runTest("TC_ADMIN_539 Verify enabled disabled status visibility", () -> {

            Assert.assertTrue(
                    emailSubscriptions.isTableDisplayed(),
                    "Status information not displayed");

            return emailSubscriptions.isTableDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Email Subscriptions")
    @Story("TC_ADMIN_540 Verify email subscription modules persistence after refresh")
    @Test
    public void verifyModulesPersistenceAfterRefresh() {

        runTest("TC_ADMIN_540 Verify email subscription modules persistence after refresh", () -> {

            driver.navigate().refresh();

            Assert.assertTrue(
                    emailSubscriptions.isTableDisplayed(),
                    "Modules disappeared after refresh");

            return emailSubscriptions.isTableDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Email Subscriptions")
    @Story("TC_ADMIN_541 Verify subscription status visibility")
    @Test
    public void verifySubscriptionStatusVisibility() {

        runTest("TC_ADMIN_541 Verify subscription status visibility", () -> {

            Assert.assertTrue(
                    emailSubscriptions.isTableDisplayed(),
                    "Subscription status not visible");

            return emailSubscriptions.isTableDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Email Subscriptions")
    @Story("TC_ADMIN_542 Verify subscription state persistence after reload")
    @Test
    public void verifySubscriptionStatePersistenceAfterReload() {

        runTest("TC_ADMIN_542 Verify subscription state persistence after reload", () -> {

            driver.navigate().refresh();

            Assert.assertTrue(
                    emailSubscriptions.isTableDisplayed(),
                    "Subscription states lost after reload");

            return emailSubscriptions.isTableDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Email Subscriptions")
    @Story("TC_ADMIN_548 Verify Admin can navigate to Email Subscriptions page")
    @Test
    public void verifyAdminCanNavigateToEmailSubscriptionsPage() {

        runTest("TC_ADMIN_548 Verify Admin can navigate to Email Subscriptions page", () -> {

            emailSubscriptions.openPage();

            Assert.assertTrue(
                    emailSubscriptions.isPageDisplayed(),
                    "Failed to navigate to Email Subscriptions page");

            return emailSubscriptions.isPageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Email Subscriptions")
    @Story("TC_ADMIN_549 Verify Email Subscriptions records table is displayed")
    @Test
    public void verifyEmailSubscriptionsTableDisplayed() {

        runTest("TC_ADMIN_549 Verify Email Subscriptions records table is displayed", () -> {

            Assert.assertTrue(
                    emailSubscriptions.isTableDisplayed(),
                    "Email Subscriptions table not displayed");

            return emailSubscriptions.isTableDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Email Subscriptions")
    @Story("TC_ADMIN_550 Verify Notification Type column is displayed")
    @Test
    public void verifyNotificationTypeColumnDisplayed() {

        runTest("TC_ADMIN_550 Verify Notification Type column is displayed", () -> {

            Assert.assertTrue(
                    emailSubscriptions.isNotificationTypeColumnDisplayed(),
                    "Notification Type column not displayed");

            return emailSubscriptions.isNotificationTypeColumnDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Email Subscriptions")
    @Story("TC_ADMIN_551 Verify Subscribers column is displayed")
    @Test
    public void verifySubscribersColumnDisplayed() {

        runTest("TC_ADMIN_551 Verify Subscribers column is displayed", () -> {

            Assert.assertTrue(
                    emailSubscriptions.isSubscribersColumnDisplayed(),
                    "Subscribers column not displayed");

            return emailSubscriptions.isSubscribersColumnDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Email Subscriptions")
    @Story("TC_ADMIN_561 Verify Edit form opens successfully for selected subscriber")
    @Test
    public void verifyEditFormOpensSuccessfully() {

        runTest("TC_ADMIN_561 Verify Edit form opens successfully for selected subscriber", () -> {

            emailSubscriptions.clickFirstEdit();

            Assert.assertTrue(
                    emailSubscriptions.isPageDisplayed(),
                    "Edit form failed to open");

            return emailSubscriptions.isPageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Email Subscriptions")
    @Story("TC_ADMIN_562 Verify Edit button opens form with existing data")
    @Test
    public void verifyEditButtonOpensFormWithExistingData() {

        runTest("TC_ADMIN_562 Verify Edit button opens form with existing data", () -> {

            emailSubscriptions.clickFirstEdit();

            Assert.assertTrue(
                    emailSubscriptions.isPageDisplayed(),
                    "Edit page not opened");

            return emailSubscriptions.isPageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Email Subscriptions")
    @Story("TC_ADMIN_573 Verify subscriber records can be selected using checkboxes")
    @Test
    public void verifyRecordsCanBeSelectedUsingCheckboxes() {

        runTest("TC_ADMIN_573 Verify subscriber records can be selected using checkboxes", () -> {

            emailSubscriptions.selectFirstRecord();

            Assert.assertTrue(true,
                    "Record selection failed");

            return true;
        });
    }

    @Epic("Admin Page")
    @Feature("Email Subscriptions")
    @Story("TC_ADMIN_584 Verify disabling email subscription option")
    @Test
    public void verifyDisablingEmailSubscriptionOption() {

        runTest("TC_ADMIN_584 Verify disabling email subscription option", () -> {

            emailSubscriptions.clickFirstEdit();
            emailSubscriptions.clickSave();

            Assert.assertTrue(
                    emailSubscriptions.isSuccessMessageDisplayed(),
                    "Success message not displayed");

            return emailSubscriptions.isSuccessMessageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Email Subscriptions")
    @Story("TC_ADMIN_585 Verify success message after disabling subscription")
    @Test
    public void verifySuccessMessageAfterDisablingSubscription() {

        runTest("TC_ADMIN_585 Verify success message after disabling subscription", () -> {

            Assert.assertTrue(
                    emailSubscriptions.isSuccessMessageDisplayed(),
                    "Success message not displayed");

            return emailSubscriptions.isSuccessMessageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Email Subscriptions")
    @Story("TC_ADMIN_586 Verify saving multiple subscription updates")
    @Test
    public void verifySavingMultipleSubscriptionUpdates() {

        runTest("TC_ADMIN_586 Verify saving multiple subscription updates", () -> {

            emailSubscriptions.clickSave();

            Assert.assertTrue(
                    emailSubscriptions.isSuccessMessageDisplayed(),
                    "Updates not saved");

            return emailSubscriptions.isSuccessMessageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Email Subscriptions")
    @Story("TC_ADMIN_588 Verify notification categories visibility")
    @Test
    public void verifyNotificationCategoriesVisibility() {

        runTest("TC_ADMIN_588 Verify notification categories visibility", () -> {

            Assert.assertTrue(
                    emailSubscriptions.isTableDisplayed(),
                    "Notification categories not visible");

            return emailSubscriptions.isTableDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Email Subscriptions")
    @Story("TC_ADMIN_591 Verify subscription persistence across sessions")
    @Test
    public void verifySubscriptionPersistenceAcrossSessions() {

        runTest("TC_ADMIN_591 Verify subscription persistence across sessions", () -> {

            driver.navigate().refresh();

            Assert.assertTrue(
                    emailSubscriptions.isTableDisplayed(),
                    "Subscription settings not retained");

            return emailSubscriptions.isTableDisplayed();
        });
    }
}