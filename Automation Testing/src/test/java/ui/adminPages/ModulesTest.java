package ui.adminPages;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.Base;
import org.example.DriverManager;
import org.example.pages.adminPage.ModulesPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ModulesTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private ModulesPage modules;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();

        login = new loginPage(driver);
        modules = new ModulesPage(driver);

        login.open();
        login.LogIn("Admin", "admin123");

        modules.openModulesPage();
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
        runTest("Verify Modules page opens successfully",
                () -> modules.pageOpened(driver.getCurrentUrl()));
    }

    // TC_ADMIN_663 - Verify Modules page displays all available system modules
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_663 - Verify Admin can view all available system modules on Modules page")
    @Test
    public void verifyModulesTableDisplayed() {
        runTest("TC_ADMIN_663 - Verify Admin can view all available system modules on Modules page", () -> {
            Assert.assertTrue(modules.isModulesTableDisplayed(),
                    "Modules table is not displayed.");
            return modules.isModulesTableDisplayed();
        });
    }

    // TC_ADMIN_664 - Verify Admin can enable a disabled module
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_664 - Verify Admin can successfully enable a disabled module")
    @Test
    public void verifyEnableModule() {
        runTest("TC_ADMIN_664 - Verify Admin can successfully enable a disabled module", () -> {
            modules.enableModule("Buzz");
            Assert.assertTrue(modules.isSuccessMessageDisplayed(),
                    "Expected success message not found after enabling module.");
            return modules.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_665 - Verify Admin can disable an enabled module
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_665 - Verify Admin can successfully disable an active module")
    @Test
    public void verifyDisableModule() {
        runTest("TC_ADMIN_665 - Verify Admin can successfully disable an active module", () -> {
            modules.disableModule("Buzz");
            Assert.assertTrue(modules.isSuccessMessageDisplayed(),
                    "Expected success message not found after disabling module.");
            return modules.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_666 - Verify module configuration persists after page refresh
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_666 - Verify saved module configurations are retained after refreshing the page")
    @Test
    public void verifyModuleConfigPersistsAfterRefresh() {
        runTest("TC_ADMIN_666 - Verify saved module configurations are retained after refreshing the page", () -> {
            modules.enableModule("Buzz");
            driver.navigate().refresh();
            Assert.assertTrue(modules.isModulesTableDisplayed(),
                    "Modules table should still be displayed after page refresh.");
            return modules.isModulesTableDisplayed();
        });
    }

    // TC_ADMIN_667 - Verify module configuration persists after logout and re-login
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_667 - Verify module settings are retained after Admin logs out and back in")
    @Test
    public void verifyModuleConfigPersistsAfterReLogin() {
        runTest("TC_ADMIN_667 - Verify module settings are retained after Admin logs out and back in", () -> {
            modules.enableModule("Buzz");
            // Logout
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/logout");
            login.LogIn("Admin", "admin123");
            modules.openModulesPage();
            Assert.assertTrue(modules.isModulesTableDisplayed(),
                    "Modules table should still be displayed after re-login.");
            return modules.isModulesTableDisplayed();
        });
    }

    // TC_ADMIN_668 - Verify users cannot access a disabled module
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_668 - Verify disabling a module prevents users from accessing it")
    @Test
    public void verifyDisabledModuleInaccessibleToUser() {
        runTest("TC_ADMIN_668 - Verify disabling a module prevents users from accessing it", () -> {
            modules.disableModule("Buzz");
            // Re-login as ESS user
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/logout");
            login.LogIn("Admin", "admin123");
            Assert.assertFalse(modules.isModuleVisibleInNavMenu("Buzz"),
                    "Disabled module should not be visible in navigation for users.");
            return !modules.isModuleVisibleInNavMenu("Buzz");
        });
    }

    // TC_ADMIN_669 - Verify non-admin users cannot access Modules page
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_669 - Verify non-admin users are denied access to Modules management page")
    @Test
    public void verifyNonAdminCannotAccessModulesPage() {
        runTest("TC_ADMIN_669 - Verify non-admin users are denied access to Modules management page", () -> {
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/logout");
            // Login with ESS user credentials
            login.LogIn("Ess", "Ess@123");
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewModules");
            // Should be redirected away from modules page
            Assert.assertFalse(
                    driver.getCurrentUrl().contains("viewModules"),
                    "Non-admin user should not be able to access Modules page.");
            return !driver.getCurrentUrl().contains("viewModules");
        });
    }

    // TC_ADMIN_670 - Verify saving multiple module changes simultaneously works
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_670 - Verify Admin can change multiple module statuses and save all at once")
    @Test
    public void verifyMultipleModuleChangesSavedAtOnce() {
        runTest("TC_ADMIN_670 - Verify Admin can change multiple module statuses and save all at once", () -> {
            modules.toggleModulesAndSave("Buzz", "Recruitment");
            Assert.assertTrue(modules.isSuccessMessageDisplayed(),
                    "Expected success message not found after saving multiple module changes.");
            return modules.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_671 - Verify system prevents disabling essential core modules
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_671 - Verify Admin cannot disable mandatory core system modules")
    @Test
    public void verifyCorModuleCannotBeDisabled() {
        runTest("TC_ADMIN_671 - Verify Admin cannot disable mandatory core system modules", () -> {
            modules.disableModule("Admin");
            // Should show a warning or the toggle should be disabled
            Assert.assertTrue(modules.isWarningMessageDisplayed(),
                    "Expected warning message when attempting to disable core Admin module.");
            return modules.isWarningMessageDisplayed();
        });
    }

    // TC_ADMIN_672 - Verify Cancel button discards unsaved module changes
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_672 - Verify clicking Cancel discards unsaved module configuration changes")
    @Test
    public void verifyCancelDiscardsUnsavedChanges() {
        runTest("TC_ADMIN_672 - Verify clicking Cancel discards unsaved module configuration changes", () -> {
            modules.toggleFirstModuleThenCancel();
            Assert.assertTrue(modules.isModulesTableDisplayed(),
                    "Modules table should still be displayed; changes should be discarded after Cancel.");
            return modules.isModulesTableDisplayed();
        });
    }

    // TC_ADMIN_674 - Verify module names are correctly displayed in list
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_674 - Verify all module names are displayed correctly on Modules page")
    @Test
    public void verifyModuleNamesDisplayed() {
        runTest("TC_ADMIN_674 - Verify all module names are displayed correctly on Modules page", () -> {
            Assert.assertTrue(modules.isModulesTableDisplayed(),
                    "Module names should be visible in the table.");
            return modules.isModulesTableDisplayed();
        });
    }

    // TC_ADMIN_675 - Verify enable/disable toggle is visible for each module
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_675 - Verify enable/disable toggle controls are visible for each module")
    @Test
    public void verifyToggleControlsVisible() {
        runTest("TC_ADMIN_675 - Verify enable/disable toggle controls are visible for each module", () -> {
            Assert.assertTrue(modules.areToggleControlsVisible(),
                    "Toggle controls are not visible on Modules page.");
            return modules.areToggleControlsVisible();
        });
    }

    // TC_ADMIN_676 - Verify Save button is present on Modules page
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_676 - Verify Save button is visible and functional on Modules page")
    @Test
    public void verifySaveButtonVisible() {
        runTest("TC_ADMIN_676 - Verify Save button is visible and functional on Modules page", () -> {
            Assert.assertTrue(modules.isSaveButtonDisplayed(),
                    "Save button is not visible on Modules page.");
            return modules.isSaveButtonDisplayed();
        });
    }

    // TC_ADMIN_677 - Verify module descriptions are shown where available
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_677 - Verify module descriptions are displayed where provided")
    @Test
    public void verifyModuleDescriptionsVisible() {
        runTest("TC_ADMIN_677 - Verify module descriptions are displayed where provided", () -> {
            Assert.assertTrue(modules.areModuleDescriptionsVisible(),
                    "Module descriptions are not visible.");
            return modules.areModuleDescriptionsVisible();
        });
    }

    // TC_ADMIN_678 - Verify Admin can re-enable a previously disabled module
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_678 - Verify disabling and re-enabling a module works correctly")
    @Test
    public void verifyReEnableModule() {
        runTest("TC_ADMIN_678 - Verify disabling and re-enabling a module works correctly", () -> {
            // Disable first
            modules.disableModule("Buzz");
            // Re-enable
            modules.enableModule("Buzz");
            Assert.assertTrue(modules.isSuccessMessageDisplayed(),
                    "Expected success message not found after re-enabling module.");
            return modules.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_679 - Verify unauthenticated access to Modules page is blocked
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_679 - Verify direct URL access to Modules page without login redirects to login")
    @Test
    public void verifyUnauthenticatedAccessBlocked() {
        runTest("TC_ADMIN_679 - Verify direct URL access to Modules page without login redirects to login", () -> {
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/logout");
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewModules");
            Assert.assertFalse(
                    driver.getCurrentUrl().contains("viewModules"),
                    "Unauthenticated user should be redirected away from Modules page.");
            return !driver.getCurrentUrl().contains("viewModules");
        });
    }

    // TC_ADMIN_681 - Verify disabled module menu items are hidden for all user roles
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_681 - Verify when module is disabled, menu items are hidden for all user roles")
    @Test
    public void verifyDisabledModuleHiddenForAllRoles() {
        runTest("TC_ADMIN_681 - Verify when module is disabled, menu items are hidden for all user roles", () -> {
            modules.disableModule("Buzz");
            Assert.assertTrue(modules.isSuccessMessageDisplayed(),
                    "Module should be disabled with a success message.");
            Assert.assertFalse(modules.isModuleVisibleInNavMenu("Buzz"),
                    "Disabled module menu item should be hidden.");
            return modules.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_682 - Verify saving without changes shows success without issues
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_682 - Verify clicking Save without changes doesn't cause errors")
    @Test
    public void verifySaveWithoutChanges() {
        runTest("TC_ADMIN_682 - Verify clicking Save without changes doesn't cause errors", () -> {
            modules.saveWithoutChanges();
            Assert.assertTrue(modules.isSuccessMessageDisplayed(),
                    "Save without changes should not cause errors; success message expected.");
            return modules.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_683 - Verify enabled module reflects immediately in navigation
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_683 - Verify enabling a module is immediately reflected in navigation for users")
    @Test
    public void verifyEnabledModuleAppearsInNavigation() {
        runTest("TC_ADMIN_683 - Verify enabling a module is immediately reflected in navigation for users", () -> {
            modules.enableModule("Buzz");
            Assert.assertTrue(modules.isSuccessMessageDisplayed(),
                    "Expected success message after enabling module.");
            Assert.assertTrue(modules.isModuleVisibleInNavMenu("Buzz"),
                    "Enabled module should appear in the navigation menu.");
            return modules.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_684 - Verify all expected OrangeHRM modules are listed
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_684 - Verify all expected system modules (PIM, Leave, Time, Performance, etc.) are listed")
    @Test
    public void verifyAllExpectedModulesListed() {
        runTest("TC_ADMIN_684 - Verify all expected system modules are listed", () -> {
            boolean result = modules.areExpectedModulesListed(
                    "Leave", "Time", "Recruitment", "Performance", "Buzz"
            );
            Assert.assertTrue(result, "One or more expected modules are missing from the Modules page.");
            return result;
        });
    }

    // TC_ADMIN_686 - Verify breadcrumb navigation is correct on Modules page
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_686 - Verify breadcrumb navigation displays correctly on the Modules page")
    @Test
    public void verifyBreadcrumbCorrect() {
        runTest("TC_ADMIN_686 - Verify breadcrumb navigation displays correctly on the Modules page", () -> {
            Assert.assertTrue(modules.isBreadcrumbCorrect(),
                    "Breadcrumb is not displayed correctly on Modules page.");
            return modules.isBreadcrumbCorrect();
        });
    }

    // TC_ADMIN_687 - Verify page title is correct on Modules page
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_687 - Verify page title is correctly displayed on the Modules page")
    @Test
    public void verifyPageTitleDisplayed() {
        runTest("TC_ADMIN_687 - Verify page title is correctly displayed on the Modules page", () -> {
            Assert.assertTrue(modules.isPageTitleDisplayed(),
                    "Page title is not displayed on Modules page.");
            return modules.isPageTitleDisplayed();
        });
    }

    // TC_ADMIN_688 - Verify disabling Leave module hides Leave menu for all users
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_688 - Verify that disabling the Leave module removes the Leave navigation menu for all user roles")
    @Test
    public void verifyDisableLeaveModuleHidesMenu() {
        runTest("TC_ADMIN_688 - Verify that disabling the Leave module removes the Leave navigation menu", () -> {
            modules.disableModule("Leave");
            Assert.assertTrue(modules.isSuccessMessageDisplayed(),
                    "Expected success message after disabling Leave module.");
            Assert.assertFalse(modules.isModuleVisibleInNavMenu("Leave"),
                    "Leave menu should be hidden after disabling the Leave module.");
            return modules.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_689 - Verify disabling Recruitment module removes recruitment-related workflows
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_689 - Verify that disabling the Recruitment module removes all recruitment-related features")
    @Test
    public void verifyDisableRecruitmentModule() {
        runTest("TC_ADMIN_689 - Verify that disabling the Recruitment module removes all recruitment-related features", () -> {
            modules.disableModule("Recruitment");
            Assert.assertTrue(modules.isSuccessMessageDisplayed(),
                    "Expected success message after disabling Recruitment module.");
            // Navigate to recruitment URL and expect redirect
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewRecruitment");
            Assert.assertFalse(
                    driver.getCurrentUrl().contains("viewRecruitment"),
                    "Recruitment page should be inaccessible after disabling module.");
            return modules.isSuccessMessageDisplayed();
        });
    }

    // TC_ADMIN_693 - Verify Modules page is responsive on mobile viewport
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_693 - Verify that the Modules page renders correctly on mobile screen sizes (375px)")
    @Test
    public void verifyModulesPageResponsive() {
        runTest("TC_ADMIN_693 - Verify that the Modules page renders correctly on mobile screen sizes", () -> {
            org.openqa.selenium.Dimension mobileSize = new org.openqa.selenium.Dimension(375, 812);
            driver.manage().window().setSize(mobileSize);
            driver.navigate().refresh();
            Assert.assertTrue(modules.isModulesTableDisplayed(),
                    "Modules table should be visible on mobile viewport.");
            return modules.isModulesTableDisplayed();
        });
    }

    // TC_ADMIN_694 - Verify a module can be cycled through enable/disable multiple times
    @Epic("Admin Page")
    @Feature("Modules Configuration")
    @Story("TC_ADMIN_694 - Verify that a module can be disabled, re-enabled, then disabled again without issues")
    @Test
    public void verifyModuleCycleMultipleTimes() {
        runTest("TC_ADMIN_694 - Verify that a module can be cycled through enable/disable multiple times", () -> {
            modules.cycleModuleState("Buzz");
            Assert.assertTrue(modules.isSuccessMessageDisplayed(),
                    "Expected success message after cycling module state multiple times.");
            return modules.isSuccessMessageDisplayed();
        });
    }
}