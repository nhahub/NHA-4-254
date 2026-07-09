package ui.adminPages;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.Base;
import org.example.DriverManager;
import org.example.pages.adminPage.MembershipPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MembershipsTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private MembershipPage membership;

    @BeforeMethod
    public void setup() {

        driver = DriverManager.getInstance().getDriver();

        login = new loginPage(driver);
        membership = new MembershipPage(driver);

        login.open();
        login.LogIn("Admin", "admin123");

        membership.openMembershipPage();
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

    @Epic("Admin Page")
    @Feature("Membership Management")
    @Story("TC_ADMIN_380 Verify Memberships page loads successfully")
    @Test
    public void verifyMembershipPageOpened() {

        runTest("TC_ADMIN_380", () -> {

            Assert.assertTrue(
                    membership.pageOpened(driver.getCurrentUrl()),
                    "Membership page not opened");

            return membership.pageOpened(driver.getCurrentUrl());
        });
    }

    @Epic("Admin Page")
    @Feature("Membership Management")
    @Story("TC_ADMIN_381 Verify memberships displayed in table")
    @Test
    public void verifyMembershipsDisplayed() {

        runTest("TC_ADMIN_381", () -> {

            Assert.assertTrue(
                    membership.isMembershipTableDisplayed(),
                    "Membership table not displayed");

            return membership.isMembershipTableDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Membership Management")
    @Story("TC_ADMIN_382 Verify table columns")
    @Test
    public void verifyColumnsDisplayed() {

        runTest("TC_ADMIN_382", () -> {

            Assert.assertTrue(
                    membership.isMembershipNameColumnDisplayed(),
                    "Membership Name column not displayed");

            return membership.isMembershipNameColumnDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Membership Management")
    @Story("TC_ADMIN_385 Add new membership successfully")
    @Test
    public void verifyCreateMembership() {

        String name = "PMI_" + System.currentTimeMillis();

        runTest("TC_ADMIN_385", () -> {

            membership.createMembership(name);

            Assert.assertTrue(
                    membership.isSuccessMessageDisplayed(),
                    "Success message not displayed");

            return membership.isSuccessMessageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Membership Management")
    @Story("TC_ADMIN_386 Verify added membership appears in list")
    @Test
    public void verifyMembershipExists() {

        String name = "IEEE_" + System.currentTimeMillis();

        runTest("TC_ADMIN_386", () -> {

            membership.createMembership(name);

            Assert.assertTrue(
                    membership.isMembershipExist(name),
                    "Membership not found in table");

            return membership.isMembershipExist(name);
        });
    }

    @Epic("Admin Page")
    @Feature("Membership Management")
    @Story("TC_ADMIN_388 Verify Add button functionality")
    @Test
    public void verifyAddButton() {

        runTest("TC_ADMIN_388", () -> {

            membership.clickAdd();

            Assert.assertTrue(
                    membership.isAddFormDisplayed(),
                    "Add form not opened");

            return membership.isAddFormDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Membership Management")
    @Story("TC_ADMIN_390 Verify max character length validation")
    @Test
    public void verifyCharacterLimit() {

        runTest("TC_ADMIN_390", () -> {

            membership.createMembership(
                    "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

            Assert.assertTrue(
                    membership.isCharacterLimitValidationDisplayed(),
                    "Character limit validation not displayed");

            return membership.isCharacterLimitValidationDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Membership Management")
    @Story("TC_ADMIN_391 Verify required field validation")
    @Test
    public void verifyRequiredValidation() {

        runTest("TC_ADMIN_391", () -> {

            membership.saveWithoutName();

            Assert.assertTrue(
                    membership.isRequiredValidationDisplayed(),
                    "Required validation not displayed");

            return membership.isRequiredValidationDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Membership Management")
    @Story("TC_ADMIN_393 Prevent duplicate membership creation")
    @Test
    public void verifyDuplicateMembership() {

        runTest("TC_ADMIN_393", () -> {

            membership.createMembership("ACCA");

            Assert.assertTrue(
                    membership.isDuplicateValidationDisplayed(),
                    "Duplicate validation not displayed");

            return membership.isDuplicateValidationDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Membership Management")
    @Story("TC_ADMIN_397 Edit existing membership successfully")
    @Test
    public void verifyEditMembership() {

        String updated =
                "Updated_" + System.currentTimeMillis();

        runTest("TC_ADMIN_397", () -> {

            membership.editFirstMembership(updated);

            Assert.assertTrue(
                    membership.isSuccessMessageDisplayed(),
                    "Update success message not displayed");

            return membership.isSuccessMessageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Membership Management")
    @Story("TC_ADMIN_398 Verify updated membership displayed")
    @Test
    public void verifyUpdatedMembershipDisplayed() {

        String updated =
                "Updated_" + System.currentTimeMillis();

        runTest("TC_ADMIN_398", () -> {

            membership.editFirstMembership(updated);

            Assert.assertTrue(
                    membership.isMembershipExist(updated),
                    "Updated membership not displayed");

            return membership.isMembershipExist(updated);
        });
    }

    @Epic("Admin Page")
    @Feature("Membership Management")
    @Story("TC_ADMIN_399 Verify edit form loads existing data")
    @Test
    public void verifyEditFormOpened() {

        runTest("TC_ADMIN_399", () -> {

            membership.clickEdit();

            Assert.assertTrue(
                    membership.isEditFormDisplayed(),
                    "Edit form not opened");

            return membership.isEditFormDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Membership Management")
    @Story("TC_ADMIN_401 Delete single membership successfully")
    @Test
    public void verifyDeleteMembership() {

        runTest("TC_ADMIN_401", () -> {

            membership.deleteFirstMembership();

            Assert.assertTrue(
                    membership.isSuccessMessageDisplayed(),
                    "Delete success message not displayed");

            return membership.isSuccessMessageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Membership Management")
    @Story("TC_ADMIN_403 Verify admin can delete multiple records")
    @Test
    public void verifyBulkDelete() {

        runTest("TC_ADMIN_403", () -> {

            membership.selectRecord();
            membership.deleteSelected();

            Assert.assertTrue(
                    membership.isSuccessMessageDisplayed(),
                    "Bulk delete failed");

            return membership.isSuccessMessageDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Membership Management")
    @Story("TC_ADMIN_405 Verify cancel deletion functionality")
    @Test
    public void verifyCancelDelete() {

        runTest("TC_ADMIN_405", () -> {

            membership.cancelDelete();

            Assert.assertTrue(
                    membership.isMembershipTableDisplayed(),
                    "Table disappeared after cancel");

            return membership.isMembershipTableDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Membership Management")
    @Story("TC_ADMIN_406 Cancel adding membership")
    @Test
    public void verifyCancelAdd() {

        runTest("TC_ADMIN_406", () -> {

            membership.clickAdd();
            membership.enterMembershipName("Test Membership");
            membership.cancelAdd();

            Assert.assertTrue(
                    membership.isMembershipTableDisplayed(),
                    "Failed to return to membership page");

            return membership.isMembershipTableDisplayed();
        });
    }

    @Epic("Admin Page")
    @Feature("Membership Management")
    @Story("TC_ADMIN_407 Verify redirect after cancel")
    @Test
    public void verifyRedirectAfterCancel() {
        runTest("TC_ADMIN_407 ,Verify redirect after cancel", () -> {
            membership.clickAdd();
            membership.cancelAdd();
            Assert.assertTrue(
                    membership.isMembershipTableDisplayed(),
                    "Not redirected to memberships page");
            return membership.isMembershipTableDisplayed();
        });
    }
}