package ui.adminPages;

import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.example.Base;
import org.example.DriverManager;
import org.example.pages.adminPage.SkillsPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SkillsTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private SkillsPage skills;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();

        login = new loginPage(driver);
        skills = new SkillsPage(driver);

        login.open();
        login.LogIn("Admin", "admin123");

        skills.openSkillsPage();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    //us_000
    @Test
    public void openPage() {
        runTest("Verify Skills page opens successfully",
                () -> skills.pageOpend(driver.getCurrentUrl()));
    }


    // US_012_TC_ADMIN_281 ,Verify Admin can view the list of skills with Name and Description.
    @Epic("Admin Page")
    @Feature("Skills Management")
    @Story("US_012_TC_ADMIN_281 ,Verify Admin can view the list of skills with Name and Description.")
    @Test
    public void verifySkillsDisplayed() {
        runTest("TC_ADMIN_281 ,Verify Admin can view the list of skills with Name and Description.", () -> {
                    Assert.assertTrue(skills.isSkillsTableDisplayed(), "Skills table is not displayed.");
                    return skills.isSkillsTableDisplayed();

                }
        );
    }

    // US_012_TC_ADMIN_282 ,Verify successful creation of a new skill with optional description.
    @Epic("Admin Page")
    @Feature("Skills Management")
    @Story("US_012_TC_ADMIN_282 ,Verify successful creation of a new skill with optional description.")
    @Test
    public void verifyCreateSkill() {
        String skillName = "Manual Testing QA " + System.currentTimeMillis();
        runTest("US_012_TC_ADMIN_282 ,Verify successful creation of a new skill with optional description.", () -> {
                    skills.createSkill(skillName, "Test Description");
                    Assert.assertTrue(skills.isSuccessMessageDisplayed(), "Expected success message not found.");
                    return skills.isSuccessMessageDisplayed();
                }
        );
    }


    // US_012_TC_ADMIN_282  ,Verify form validation error when mandatory Skill Name is omitted.
    @Epic("Admin Page")
    @Feature("Skills Management")
    @Story("US_012_TC_ADMIN_283 ,Verify form validation error when mandatory Skill Name is omitted.")
    @Test
    public void verifyRequiredValidation() {
        runTest("US_012_TC_ADMIN_283  ,Verify form validation error when mandatory Skill Name is omitted.", () -> {
                    skills.saveWithoutName("Description");
                    Assert.assertTrue(skills.isRequiredErrorDisplayed(), "Expected required error message not found.");
                    return skills.isRequiredErrorDisplayed();
                }
        );
    }

    // US_012_TC_ADMIN_284 ,Verify Admin can successfully update and save modifications to an existing skill.
    @Epic("Admin Page")
    @Feature("Skills Management")
    @Story("US_012_TC_ADMIN_284 ,Verify Admin can successfully update and save modifications to an existing skill.")
    @Test
    public void verifyUpdateSkill() {
        runTest("US_012_TC_ADMIN_284 ,Verify Admin can successfully update and save modifications to an existing skill.", () -> {
                    skills.editFirstSkill("Automation Testing", "Updated Description");
                    Assert.assertTrue(skills.isSuccessMessageDisplayed(), "Expected success message not found after update.");
                    return skills.isSuccessMessageDisplayed();
                }
        );
    }

    // US_012_TC_ADMIN_285 ,Verify that a single skill item can be removed permanently following confirmation.
    @Epic("Admin Page")
    @Feature("Skills Management")
    @Story("US_012_TC_ADMIN_285 ,Verify that a single skill item can be removed permanently following confirmation.")
    @Test
    public void verifyDeleteSkill() {
        runTest("US_012_TC_ADMIN_285 ,Verify that a single skill item can be removed permanently following confirmation.", () -> {
                    skills.deleteFirstSkill();
                    Assert.assertTrue(skills.isSuccessMessageDisplayed(), "Expected success message not found after deletion.");
                    return skills.isSuccessMessageDisplayed();
                }
        );
    }

    // US_012_TC_ADMIN_286 ,Verify mass-deletion feature using master list selection checkboxes.
    @Epic("Admin Page")
    @Feature("Skills Management")
    @Story("US_012_TC_ADMIN_286 ,Verify mass-deletion feature using master list selection checkboxes.")
    @Test
    public void verifyMassDelete() {
        Allure.getLifecycle().updateTestCase(testResult ->
                testResult.setName("US_012_TC_ADMIN_286 ,Verify that multiple skill items can be removed permanently following confirmation."));
        runTest("US_012_TC_ADMIN_286 ,Verify that multiple skill items can be removed permanently following confirmation.", () ->
                {
                    skills.selectMultipleRows(2).deleteSelected();
                    return skills.isSuccessMessageDisplayed();
                }
        );
    }

    // US_012_TC_ADMIN_287 ,Verify canceling a deletion operation retains the skill data record intact.
    @Epic("Admin Page")
    @Feature("Skills Management")
    @Story("US_012_TC_ADMIN_287 ,Verify canceling a deletion operation retains the skill data record intact.")
    @Test
    public void verifyCancelDelete() {
        runTest("US_012_TC_ADMIN_287 ,Verify canceling a deletion operation retains the skill data record intact.", () -> {
                    skills.cancelDelete();
                    Assert.assertTrue(skills.isSkillsTableDisplayed(), "Skills table should still be displayed after canceling delete.");
                    return skills.isSkillsTableDisplayed();
                }
        );
    }

    // US_012_TC_ADMIN_288 ,Verify unique constraint enforcement prevents saving a duplicate skill name.
    @Epic("Admin Page")
    @Feature("Skills Management")
    @Story("US_012_TC_ADMIN_288 ,Verify unique constraint enforcement prevents saving a duplicate skill name.")
    @Test
    public void verifyDuplicateSkillValidation() {

        runTest("US_012_TC_ADMIN_288 ,Verify unique constraint enforcement prevents saving a duplicate skill name.", () -> {
                    skills.createSkill("Java Programming", "Duplicate");
                    Assert.assertTrue(skills.isAlreadyExistsErrorDisplayed(), "Expected 'already exists' error message not found.");
                    return skills.isAlreadyExistsErrorDisplayed();
                }
        );
    }


    // US_012_TC_ADMIN_289 ,Verify character boundary restriction limitations on the Description field.
    @Epic("Admin Page")
    @Feature("Skills Management")
    @Story("US_012_TC_ADMIN_289 ,Verify character boundary restriction limitations on the Description field.")
    @Test
    public void vrifyLimit() {
        runTest("US_012_TC_ADMIN_289 ,Verify character boundary restriction limitations on the Description field.", () -> {
            skills.createSkill("Boundary Test",
                    "This description exceeds the maximum allowed character limit for the description field in the skills management section of the admin page.This description exceeds the maximum allowed character limit for the description field in the skills management section of the admin page.This description exceeds the maximum allowed character limit for the description field in the skills management section of the admin page.This description exceeds the maximum allowed character limit for the description field in the skills management section of the admin page.This description exceeds the maximum allowed character limit for the description field in the skills management section of the admin page.");
            Assert.assertTrue(skills.isCharacterLimitErrorDisplayed(), "Expected character limit error message not found.");
            return skills.isCharacterLimitErrorDisplayed();
        });
    }

}