package org.example.pages.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LicensesPage extends Base {

    // Navigation
    private final By adminMenu = By.xpath("//span[text()='Admin']");
    private final By qualificationsMenu = By.xpath("//span[text()='Qualifications ']");
    private final By licensesOption = By.xpath("//a[text()='Licenses']");
    // Table
    private final By licenseRows =
            By.cssSelector(".oxd-table-body .oxd-table-row");
    private final By licenseNameColumn =
            By.xpath("//div[@role='columnheader']//div[text()='Name']");
    // Add/Edit
    private final By addButton =
            By.xpath("//button[normalize-space()='Add']");
    private final By licenseNameInput =
            By.xpath("(//input[contains(@class,'oxd-input')])[2]");
    private final By saveButton =
            By.xpath("//button[@type='submit']");
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
            By.xpath("(//input[@type='checkbox'])[2]");
    // Empty Table
    private final By noRecordsFound =
            By.xpath("//*[contains(text(),'No Records Found')]");
    private WebDriver driver;
    private ActionsBot actions;

    public LicensesPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    public LicensesPage openLicensesPage() {

        actions.click(adminMenu);
        actions.click(qualificationsMenu);
        actions.click(licensesOption);

        return this;
    }

    public boolean pageOpened(String url) {
        return actions.inTargetPage(url);
    }

    public boolean isLicenseTableDisplayed() {
        return !driver.findElements(licenseRows).isEmpty();
    }

    public boolean isLicenseNameColumnDisplayed() {
        return driver.findElement(licenseNameColumn).isDisplayed();
    }

    public void clickAdd() {
        actions.click(addButton);
    }

    public boolean isAddFormDisplayed() {
        return actions.isDisplayed(licenseNameInput);
    }

    public void createLicense(String name) {

        clickAdd();

        actions.type(licenseNameInput, name);

        actions.click(saveButton);
    }

    public void saveWithoutName() {

        clickAdd();

        actions.click(saveButton);
    }

    public void enterLicenseName(String name) {
        actions.type(licenseNameInput, name);
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
        return actions.isDisplayed(licenseNameInput);
    }

    public void editFirstLicense(String newName) {

        clickEdit();

        driver.findElement(licenseNameInput).clear();

        actions.type(licenseNameInput, newName);

        actions.click(saveButton);
    }

    public boolean isLicenseExist(String licenseName) {

        By record = By.xpath(
                "//div[@class='oxd-table-card']//div[text()='"
                        + licenseName + "']");

        try {
            return actions.isDisplayed(record);
        } catch (Exception e) {
            return false;
        }
    }

    public void deleteFirstLicense() {

        actions.click(deleteButton);

        actions.click(confirmDeleteBtn);
    }

    public void cancelDelete() {

        actions.click(deleteButton);

        actions.click(cancelDeleteBtn);
    }

    public LicensesPage selectRecord() {

        actions.click(firstCheckbox);

        return this;
    }

    public void deleteSelected() {

        actions.click(deleteSelectedBtn);

        actions.click(confirmDeleteBtn);
    }

    public boolean isNoRecordsFoundDisplayed() {
        return actions.isDisplayed(noRecordsFound);
    }
}