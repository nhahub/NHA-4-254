package org.example.pages.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class SkillsPage extends Base {

    // Navigation
    private final By x = By.xpath("//span[text()='Admin']");  //By.xpath("//span[text()='Admin']");
    private final By qualificationsMenu = By.xpath("//span[text()='Qualifications ']");
    private final By skillsOption = By.xpath("//a[text()='Skills']");
    // Table
    private final By skillRows =
            By.cssSelector(".oxd-table-body .oxd-table-row:nth-of-type(1)");
    private final By skillNameColumn =
            By.xpath("(//div[@role='table']//div[@role='row'])[2]");
    // Add Skill
    private final By addButton =
            By.xpath("//button[normalize-space()='Add']");
    private final By skillNameInput =
            By.xpath("(//input[contains(@class,'oxd-input')])[2]");
    private final By editButton = By.cssSelector(".bi-pencil-fill:nth-of-type(1)");
    private final By deleteButton = By.xpath("(//i[contains(@class,'bi-trash')])[1]");
    private final By descriptionTextArea =
            By.tagName("textarea");
    private final By saveButton =
            By.xpath("//button[@type='submit']");
    // Validation
    private final By requiredError =
            By.xpath("//span[text()='Required']");
    private final By alreadyExistsError =
            By.xpath("//span[contains(text(),'Already exists')]");
    private final By limitMessage = By.className("oxd-input-field-error-message");
    private final By successToast =
            By.xpath("//p[contains(text(),'Successfully')]");
    // Delete
    private final By deleteIcon =
            By.xpath("(//i[contains(@class,'bi-trash')])[1]");
    private final By confirmDeleteBtn =
            By.xpath("//button[normalize-space()='Yes, Delete']");
    private final By cancelDeleteBtn =
            By.xpath("//button[normalize-space()='No, Cancel']");
    private final By deleteSelectedBtn =
            By.xpath("//button[contains(.,'Delete Selected')]");
    private final By checkboxes =
            By.cssSelector("input[type='checkbox']");
    // Empty state
    private final By noRecordsFound =
            By.xpath("//*[contains(text(),'No Records Found')]");
    By addbutom = By.className("oxd-button");
    private WebDriver driver;
    private ActionsBot actions;

    public SkillsPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    public boolean pageOpend(String url) {
        return actions.inTargetPage(url);
    }

    // Navigation

    public SkillsPage openSkillsPage() {

        actions.click(x);
        actions.click(qualificationsMenu);
        actions.click(skillsOption);

        return this;
    }

    // Verification

    public boolean isSkillsTableDisplayed() {
        try {
            sleep(3000);
            return !driver.findElements(skillRows).isEmpty();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Add Skill

    public void clickAdd() {
        actions.click(addButton);
//        driver.findElement(addbutom).click();
    }

    public void createSkill(String name, String description) {
        System.out.println("create Skill");

        clickAdd();

        actions.type(skillNameInput, name);

        if (description != null) {
            actions.type(descriptionTextArea, description);
        }
        actions.click(saveButton);
    }

    public boolean isSuccessMessageDisplayed() {
        return actions.isDisplayed(successToast);

    }

    public String getSuccessMessage() {
        return actions.getTextWhenVisible(successToast);
    }


    // Validation

    public void saveWithoutName(String description) {

        clickAdd();

        actions.type(descriptionTextArea, description);

        actions.click(saveButton);
    }

    public boolean isRequiredErrorDisplayed() {
        return actions.isDisplayed(requiredError);
    }

    public boolean isAlreadyExistsErrorDisplayed() {
        return actions.isDisplayed(alreadyExistsError);
    }

    // Update

    public void editFirstSkill(String newName, String newDescription) {

        actions.click(editButton);

        driver.findElement(skillNameInput).clear();
        actions.type(skillNameInput, newName);

        driver.findElement(descriptionTextArea).clear();
        actions.type(descriptionTextArea, newDescription);

        actions.click(saveButton);
    }

    // Delete

    public void deleteFirstSkill() {
        actions.click(deleteIcon);
        actions.click(confirmDeleteBtn);
    }

    public void cancelDelete() {
        actions.click(deleteIcon);
        actions.click(cancelDeleteBtn);
    }

    // Multiple Delete

//    public SkillsPage selectMultipleRows(int count) {
//
////        for (int i = 2; i <= count + 1; i++) {
//        By checkbox = By.xpath("(//input[@type='checkbox'])[3]");
////            sleep();
////        try {
////            sleep(4000);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//        actions.click("");

    /// /        }
//        return this;
//    }
    public SkillsPage selectMultipleRows(int count) {

//        for (int i = 2; i <= count + 1; i++) {

//        By checkbox = By.xpath("(//input[@type='checkbox'])[2]");
//        driver.findElement(checkbox).click();
        By checkbox = By.xpath("(//label[contains(@class,'oxd-checkbox-wrapper')])[2]");

        actions.click(checkbox);
//        actions.click(checkbox);
//        }

        return this;
    }

    public void deleteSelected() {
        actions.click(deleteSelectedBtn);
        actions.click(confirmDeleteBtn);
    }


    public boolean isCharacterLimitErrorDisplayed() {
        return actions.isDisplayed(limitMessage);
    }

    // Empty Table

    public boolean isNoRecordsFoundDisplayed() {
        return actions.isDisplayed(noRecordsFound);
    }
}