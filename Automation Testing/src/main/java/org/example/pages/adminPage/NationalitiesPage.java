package org.example.pages.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NationalitiesPage extends Base {

    // Navigation
    private final By adminMenu = By.xpath("//span[text()='Admin']");
    private final By nationalitiesOption = By.xpath("//a[text()='Nationalities']");
    // Table
    private final By nationalityRows =
            By.cssSelector(".oxd-table-body .oxd-table-row");
    private final By nationalityNameColumn =
            By.xpath("//div[@role='columnheader']//div[text()='Nationality']");
    // Add/Edit
    private final By addButton =
            By.xpath("//button[normalize-space()='Add']");
    private final By nationalityInput =
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
    // Checkbox
    private final By firstCheckbox =
            By.xpath("(//label[contains(@class,'oxd-checkbox-wrapper')])[2]");
    // Empty State
    private final By noRecordsFound =
            By.xpath("//*[contains(text(),'No Records Found')]");
    private WebDriver driver;
    private ActionsBot actions;

    public NationalitiesPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    public NationalitiesPage openNationalitiesPage() {

        actions.click(adminMenu);
        actions.click(nationalitiesOption);

        return this;
    }

    public boolean pageOpened(String url) {
        return actions.inTargetPage(url);
    }

    public boolean isNationalityTableDisplayed() {
        return !driver.findElements(nationalityRows).isEmpty();
    }

    public boolean isNationalityColumnDisplayed() {
        return actions.isDisplayed(nationalityNameColumn);
    }

    public void clickAdd() {
        actions.click(addButton);
    }

    public boolean isAddFormDisplayed() {
        return actions.isDisplayed(nationalityInput);
    }

    public void createNationality(String nationality) {

        clickAdd();
        actions.type(nationalityInput, nationality);
        actions.click(saveButton);
    }

    public void saveWithoutName() {

        clickAdd();
        actions.click(saveButton);
    }

    public boolean isRequiredValidationDisplayed() {
        return actions.isDisplayed(requiredError);
    }

    public boolean isDuplicateValidationDisplayed() {
        return actions.isDisplayed(duplicateError);
    }

    public boolean isSuccessMessageDisplayed() {
        return actions.isDisplayed(successToast);
    }

    public String getSuccessMessage() {
        return actions.getTextWhenVisible(successToast);
    }

    public void clickEdit() {
        actions.click(editButton);
    }

    public boolean isEditFormDisplayed() {
        return actions.isDisplayed(nationalityInput);
    }

    public void editFirstNationality(String newName) {

        clickEdit();

        driver.findElement(nationalityInput).clear();

        actions.type(nationalityInput, newName);

        actions.click(saveButton);
    }

    public void enterNationalityName(String name) {
        actions.type(nationalityInput, name);
    }

    public boolean isNationalityExist(String nationality) {

        By record = By.xpath(
                "//div[@class='oxd-table-card']//div[text()='"
                        + nationality + "']");

        return actions.isDisplayed(record);
    }

    public void deleteFirstNationality() {

        actions.click(deleteButton);
        actions.click(confirmDeleteBtn);
    }

    public void cancelDelete() {

        actions.click(deleteButton);
        actions.click(cancelDeleteBtn);
    }

    public NationalitiesPage selectRecord() {

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