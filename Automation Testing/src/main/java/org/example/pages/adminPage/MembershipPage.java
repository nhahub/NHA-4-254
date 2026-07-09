package org.example.pages.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MembershipPage extends Base {

    // Navigation
    private final By adminMenu = By.xpath("//span[text()='Admin']");
    private final By qualificationsMenu = By.xpath("//span[text()='Qualifications ']");
    private final By membershipsOption = By.xpath("//a[text()='Memberships']");
    // Table
    private final By membershipRows =
            By.cssSelector(".oxd-table-body .oxd-table-row");
    private final By membershipNameColumn =
            By.xpath("//div[@role='columnheader']//div[text()='Name']");
    // Add/Edit
    private final By addButton =
            By.xpath("//button[normalize-space()='Add']");
    private final By membershipNameInput =
            By.xpath("(//input[contains(@class,'oxd-input')])[2]");
    private final By saveButton =
            By.xpath("//button[@type='submit']");
    private final By cancelButton =
            By.xpath("//button[normalize-space()='Cancel']");
    // Edit
    private final By editButton =
            By.cssSelector(".bi-pencil-fill");
    // Delete
    private final By deleteButton =
            By.cssSelector(".bi-trash");
    private final By confirmDeleteBtn =
            By.xpath("//button[normalize-space()='Yes, Delete']");
    private final By cancelDeleteBtn =
            By.xpath("//button[normalize-space()='No, Cancel']");
    private final By deleteSelectedBtn =
            By.xpath("//button[contains(.,'Delete Selected')]");
    // Validation
    private final By requiredError =
            By.xpath("//span[text()='Required']");
    private final By duplicateError =
            By.xpath("//span[contains(text(),'Already exists')]");
    private final By successToast =
            By.xpath("//p[contains(text(),'Successfully')]");
    private final By inputError =
            By.className("oxd-input-field-error-message");
    // Checkbox
    private final By firstCheckbox =
            By.xpath("(//label[contains(@class,'oxd-checkbox-wrapper')])[2]");
    // Empty Table
    private final By noRecordsFound =
            By.xpath("//*[contains(text(),'No Records Found')]");
    private WebDriver driver;
    private ActionsBot actions;

    public MembershipPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    public MembershipPage openMembershipPage() {

        actions.click(adminMenu);
        actions.click(qualificationsMenu);
        actions.click(membershipsOption);

        return this;
    }

    public boolean pageOpened(String url) {
        return actions.inTargetPage(url);
    }

    public boolean isMembershipTableDisplayed() {
        return !driver.findElements(membershipRows).isEmpty();
    }

    public boolean isMembershipNameColumnDisplayed() {
        return actions.isDisplayed(membershipNameColumn);
    }

    public void clickAdd() {
        actions.click(addButton);
    }

    public boolean isAddFormDisplayed() {
        return actions.isDisplayed(membershipNameInput);
    }

    public void createMembership(String name) {

        clickAdd();
        actions.type(membershipNameInput, name);
        actions.click(saveButton);
    }

    public void saveWithoutName() {

        clickAdd();
        actions.click(saveButton);
    }

    public void enterMembershipName(String name) {
        actions.type(membershipNameInput, name);
    }

    public boolean isRequiredValidationDisplayed() {
        return actions.isDisplayed(requiredError);
    }

    public boolean isDuplicateValidationDisplayed() {
        return actions.isDisplayed(duplicateError);
    }

    public boolean isCharacterLimitValidationDisplayed() {
        return actions.isDisplayed(inputError);
    }

    public boolean isSuccessMessageDisplayed() {
        return actions.isDisplayed(successToast);
    }

    public void clickEdit() {
        actions.click(editButton);
    }

    public boolean isEditFormDisplayed() {
        return actions.isDisplayed(membershipNameInput);
    }

    public void editFirstMembership(String newName) {

        clickEdit();

        driver.findElement(membershipNameInput).clear();

        actions.type(membershipNameInput, newName);

        actions.click(saveButton);
    }

    public boolean isMembershipExist(String membershipName) {

        By record = By.xpath(
                "//div[@class='oxd-table-card']//div[text()='"
                        + membershipName + "']");

        return actions.isDisplayed(record);
    }

    public void deleteFirstMembership() {

        actions.click(deleteButton);
        actions.click(confirmDeleteBtn);
    }

    public void cancelDelete() {

        actions.click(deleteButton);
        actions.click(cancelDeleteBtn);
    }

    public MembershipPage selectRecord() {

        actions.click(firstCheckbox);

        return this;
    }

    public void deleteSelected() {

        actions.click(deleteSelectedBtn);
        actions.click(confirmDeleteBtn);
    }

    public void cancelAdd() {
        actions.click(cancelButton);
    }

    public boolean isNoRecordsFoundDisplayed() {
        return actions.isDisplayed(noRecordsFound);
    }
}