package org.example.pages.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LanguagePage extends Base {

    // Navigation
    private final By adminMenu = By.xpath("//span[text()='Admin']");
    private final By qualificationsMenu = By.xpath("//span[text()='Qualifications ']");
    private final By languagesOption = By.xpath("//a[text()='Languages']");
    // Table
    private final By languageRows =
            By.cssSelector(".oxd-table-body");
    private final By languageNameColumn =
            By.cssSelector(".oxd-table-header-cell.oxd-padding-cell");

    // Add / Edit
    private final By addButton =
            By.xpath("//button[normalize-space()='Add']");
    private final By languageNameInput =
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
            By.className("oxd-input-field-error-message");
    private final By successToast =
            By.xpath("//p[contains(text(),'Successfully')]");
    private final By inputError =
            By.className("oxd-input-field-error-message");
    // Checkbox
    private final By firstCheckbox =
            By.className("oxd-table-card-cell-checkbox");
    // Empty State
    private final By noRecordsFound =
            By.xpath("//*[contains(text(),'No Records Found')]");
    private WebDriver driver;
    private ActionsBot actions;

    public LanguagePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    public LanguagePage openLanguagesPage() {

        actions.click(adminMenu);
        actions.click(qualificationsMenu);
        actions.click(languagesOption);

        return this;
    }

    public boolean pageOpened(String url) {
        return actions.inTargetPage(url);
    }

    public boolean isLanguageTableDisplayed() {
        return !driver.findElements(languageRows).isEmpty();
    }

    public boolean isLanguageNameColumnDisplayed() {
        return actions.isDisplayed(languageNameColumn);
    }

    public void clickAdd() {
        actions.click(addButton);
    }

    public boolean isAddFormDisplayed() {
        return actions.isDisplayed(languageNameInput);
    }

    public void createLanguage(String name) {
        clickAdd();
        actions.type(languageNameInput, name);
        actions.click(saveButton);
    }

    public void saveWithoutName() {

        clickAdd();

        actions.click(saveButton);
    }

    public void enterLanguageName(String name) {
        actions.type(languageNameInput, name);
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

    public String getSuccessMessage() {
        return actions.getTextWhenVisible(successToast);
    }

    public void clickEdit() {
        actions.click(editButton);
    }

    public boolean isEditFormDisplayed() {
        return actions.isDisplayed(languageNameInput);
    }

    public void editFirstLanguage(String newName) {

        clickEdit();

//        driver.findElement(languageNameInput).clear();
        actions.clearElement(languageNameInput);
        actions.type(languageNameInput, newName);
        actions.click(saveButton);
    }

    public boolean isLanguageExist(String languageName) {

        By record = By.xpath(
                "//div[@class='oxd-table-card']//div[text()='"
                        + languageName + "']");

        try {
            return actions.isDisplayed(record);
        } catch (Exception e) {
            return false;
        }
    }

    public void deleteFirstLanguage() {

        actions.click(deleteButton);

        actions.click(confirmDeleteBtn);
    }

    public void cancelDelete() {

        actions.click(deleteButton);

        actions.click(cancelDeleteBtn);
    }

    public LanguagePage selectRecord() {

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