package org.example.pages.PIM;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class CustomFieldsPage extends Base {

    // ─── Navigation ──────────────────────────────────────────────────────────────
    private final By pimMenu = By.xpath("//span[text()='PIM']");
    private final By configurationTab = By.className("oxd-topbar-body-nav-tab");
    private final By customFieldsOption = By.xpath("//a[text()='Custom Fields']");

    // ─── Page Header ─────────────────────────────────────────────────────────────
    private final By pageHeader =
            By.xpath("//h6[contains(@class,'oxd-text--h6')]");

    // ─── Table ───────────────────────────────────────────────────────────────────
    private final By customFieldsTable =
            By.cssSelector(".oxd-table");
    private final By customFieldRows =
            By.cssSelector(".oxd-table-body .oxd-table-row");
    private final By tableColumnHeaders =
            By.xpath("//div[@role='columnheader']");
    private final By firstRowEditButton =
            By.xpath("(//div[@role='row'])[2]//i[contains(@class,'bi-pencil-fill')]");
    private final By firstRowDeleteButton =
            By.xpath("(//div[@role='row'])[2]//i[contains(@class,'bi-trash')]");
    private final By firstRowCheckbox =
            By.xpath("(//div[@role='row'])[2]//input[@type='checkbox']");
    private final By allCheckboxes =
            By.cssSelector("input[type='checkbox']");
    private final By noRecordsFound =
            By.xpath("//*[contains(text(),'No Records Found')]");

    // ─── Add Button ───────────────────────────────────────────────────────────────
    private final By addButton =
            By.xpath("//button[normalize-space()='Add']");

    // ─── Add / Edit Custom Field Form ─────────────────────────────────────────────
    private final By fieldNameInput =
            By.xpath("(//label[text()='Name']/following::input)[1]");
    private final By screenDropdown =
            By.xpath("(//label[text()='Screen']/following::div[contains(@class,'oxd-select-text')])[1]");
    private final By typeDropdown =
            By.xpath("(//label[text()='Type']/following::div[contains(@class,'oxd-select-text')])[1]");
    private final By saveButton =
            By.xpath("//button[@type='submit']");
    private final By cancelButton =
            By.xpath("//button[normalize-space()='Cancel']");

    // ─── Delete Confirmation ──────────────────────────────────────────────────────
    private final By confirmDeleteBtn =
            By.xpath("//button[normalize-space()='Yes, Delete']");
    private final By cancelDeleteBtn =
            By.xpath("//button[normalize-space()='No, Cancel']");

    // ─── Validation ───────────────────────────────────────────────────────────────
    private final By requiredErrors =
            By.xpath("//span[text()='Required']");
    private final By requiredError =
            By.xpath("(//span[text()='Required'])[1]");
    private final By alreadyExistsError =
            By.xpath("//span[contains(text(),'Already exists') or contains(text(),'already exists')]");

    // ─── Toasts / Messages ────────────────────────────────────────────────────────
    private final By successToast =
            By.xpath("//p[contains(text(),'Successfully')]");
    private final By updatedToast =
            By.xpath("//p[contains(text(),'Successfully Updated')]");
    private final By deletedToast =
            By.xpath("//p[contains(text(),'Successfully Deleted')]");

    private WebDriver driver;
    private ActionsBot actions;

    public CustomFieldsPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    // ─── Navigation ──────────────────────────────────────────────────────────────

    public CustomFieldsPage openConfigurationMenu() {
        actions.click(pimMenu);
        actions.click(configurationTab);
        return this;
    }

    public boolean isConfigurationMenuOpened() {
        openConfigurationMenu();
        return actions.isDisplayed(customFieldsOption);
    }

    public boolean isCustomFieldsOptionDisplayed() {
        openConfigurationMenu();
        return actions.isDisplayed(customFieldsOption);
    }

    public CustomFieldsPage openCustomFieldsPage() {
        openConfigurationMenu();
        actions.click(customFieldsOption);
        return this;
    }

    public boolean pageOpened(String url) {
        return actions.inTargetPage(url);
    }

    public boolean isPageLoaded() {
        try {
            sleep(2000);
            return actions.isDisplayed(pageHeader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ─── Table ───────────────────────────────────────────────────────────────────

    public boolean isTableDisplayed() {
        try {
            sleep(2000);
            return actions.isDisplayed(customFieldsTable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean hasCustomFieldRows() {
        try {
            sleep(2000);
            return !driver.findElements(customFieldRows).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areRequiredColumnsDisplayed() {
        try {
            int headerCount = driver.findElements(tableColumnHeaders).size();
            return headerCount >= 3; // Name, Screen, Field Type (+ Actions)
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areActionButtonsDisplayedForRecords() {
        return actions.isDisplayed(firstRowEditButton) && actions.isDisplayed(firstRowDeleteButton);
    }

    public boolean isNoRecordsFoundDisplayed() {
        return actions.isDisplayed(noRecordsFound);
    }

    public boolean isFieldNamePresentInTable(String fieldName) {
        By fieldLocator = By.xpath("//div[@role='row']//div[contains(@class,'oxd-table-cell')][text()='" + fieldName + "']");
        return actions.isDisplayed(fieldLocator);
    }

    // ─── Add Button ───────────────────────────────────────────────────────────────

    public boolean isAddButtonDisplayed() {
        return actions.isDisplayed(addButton);
    }

    public void clickAdd() {
        actions.click(addButton);
    }

    // ─── Add / Create Custom Field ────────────────────────────────────────────────

    public boolean areAddFormFieldsDisplayed() {
        clickAdd();
        return actions.isDisplayed(fieldNameInput)
                && actions.isDisplayed(screenDropdown)
                && actions.isDisplayed(typeDropdown);
    }

    public void fillCustomFieldForm(String name, String screen, String type) {
        actions.type(fieldNameInput, name);
        actions.click(screenDropdown);
        actions.click(By.xpath("//div[@role='option']//span[text()='" + screen + "']"));
        actions.click(typeDropdown);
        actions.click(By.xpath("//div[@role='option']//span[text()='" + type + "']"));
    }

    public void createCustomField(String name, String screen, String type) {
        clickAdd();
        fillCustomFieldForm(name, screen, type);
        actions.click(saveButton);
    }

    public void saveAddFormEmpty() {
        clickAdd();
        actions.click(saveButton);
    }

    public void createCustomFieldWithLongName(String longName, String screen, String type) {
        clickAdd();
        fillCustomFieldForm(longName, screen, type);
        actions.click(saveButton);
    }

    public void createCustomFieldWithSpecialChars(String specialName, String screen, String type) {
        clickAdd();
        fillCustomFieldForm(specialName, screen, type);
        actions.click(saveButton);
    }

    public void createDuplicateCustomField(String existingName, String screen, String type) {
        clickAdd();
        fillCustomFieldForm(existingName, screen, type);
        actions.click(saveButton);
    }

    public void doubleClickSaveOnAddForm(String name, String screen, String type) {
        clickAdd();
        fillCustomFieldForm(name, screen, type);
        actions.click(saveButton);
        actions.click(saveButton);
    }

    public void clickCancelOnAddForm() {
        clickAdd();
        actions.click(cancelButton);
    }

    // ─── Edit Custom Field ────────────────────────────────────────────────────────

    public void clickEditFirstRecord() {
        actions.click(firstRowEditButton);
    }

    public boolean isEditFormPrefilled() {
        clickEditFirstRecord();
        try {
            String value = driver.findElement(fieldNameInput).getAttribute("value");
            return value != null && !value.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public void editFirstRecord(String newName) {
        clickEditFirstRecord();
        driver.findElement(fieldNameInput).clear();
        actions.type(fieldNameInput, newName);
        actions.click(saveButton);
    }

    public void modifyFirstRecordThenRefresh(String newName) {
        clickEditFirstRecord();
        driver.findElement(fieldNameInput).clear();
        actions.type(fieldNameInput, newName);
        driver.navigate().refresh();
    }

    // ─── Delete Custom Field ──────────────────────────────────────────────────────

    public void selectFirstRecordCheckbox() {
        actions.click(firstRowCheckbox);
    }

    public boolean areMultipleCheckboxesSelectable() {
        try {
            int count = driver.findElements(allCheckboxes).size();
            return count > 1;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickDeleteOnSelected() {
        By deleteSelectedBtn = By.xpath("//button[contains(.,'Delete Selected')]");
        actions.click(deleteSelectedBtn);
    }

    public boolean isDeleteConfirmationDisplayed() {
        return actions.isDisplayed(confirmDeleteBtn) && actions.isDisplayed(cancelDeleteBtn);
    }

    public void confirmDelete() {
        actions.click(confirmDeleteBtn);
    }

    public void cancelDelete() {
        actions.click(cancelDeleteBtn);
    }

    public void deleteFirstRecord() {
        selectFirstRecordCheckbox();
        clickDeleteOnSelected();
        confirmDelete();
    }

    public void selectFirstRecordAndCancelDelete() {
        selectFirstRecordCheckbox();
        clickDeleteOnSelected();
        cancelDelete();
    }

    public boolean isAnyCheckboxSelected() {
        try {
            return driver.findElements(allCheckboxes).stream()
                    .anyMatch(cb -> cb.isSelected());
        } catch (Exception e) {
            return false;
        }
    }

    // ─── Validation ───────────────────────────────────────────────────────────────

    public boolean isRequiredErrorDisplayed() {
        return actions.isDisplayed(requiredError);
    }

    public boolean areMultipleRequiredErrorsDisplayed() {
        try {
            return driver.findElements(requiredErrors).size() > 1;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAlreadyExistsErrorDisplayed() {
        return actions.isDisplayed(alreadyExistsError);
    }

    // ─── Toasts / Messages ────────────────────────────────────────────────────────

    public boolean isSuccessMessageDisplayed() {
        return actions.isDisplayed(successToast);
    }

    public boolean isUpdatedMessageDisplayed() {
        return actions.isDisplayed(updatedToast);
    }

    public boolean isDeletedMessageDisplayed() {
        return actions.isDisplayed(deletedToast);
    }

    public String getSuccessMessage() {
        return actions.getTextWhenVisible(successToast);
    }
}