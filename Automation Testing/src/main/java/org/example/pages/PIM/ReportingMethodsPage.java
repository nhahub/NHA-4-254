package org.example.pages.PIM;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class ReportingMethodsPage extends Base {

    // ─── Navigation ──────────────────────────────────────────────────────────────
    private final By pimMenu = By.xpath("//span[text()='PIM']");
    private final By configurationTab = By.className("oxd-topbar-body-nav-tab");
    private final By reportingMethodsOption = By.xpath("//a[text()='Reporting Methods']");

    // ─── Page Header ─────────────────────────────────────────────────────────────
    private final By pageHeader =
            By.xpath("//h6[contains(@class,'oxd-text--h6')]");

    // ─── Table ───────────────────────────────────────────────────────────────────
    private final By methodsTable =
            By.cssSelector(".oxd-table");
    private final By methodRows =
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

    // ─── Add / Edit Reporting Method Form ─────────────────────────────────────────
    private final By nameInput =
            By.xpath("(//label[text()='Name']/following::input)[1]");
    private final By saveButton =
            By.xpath("//button[@type='submit']");
    private final By cancelButton =
            By.xpath("//button[normalize-space()='Cancel']");

    // ─── Delete Confirmation ──────────────────────────────────────────────────────
    private final By deleteSelectedBtn =
            By.xpath("//button[contains(.,'Delete Selected')]");
    private final By confirmDeleteBtn =
            By.xpath("//button[normalize-space()='Yes, Delete']");
    private final By cancelDeleteBtn =
            By.xpath("//button[normalize-space()='No, Cancel']");

    // ─── Validation ───────────────────────────────────────────────────────────────
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

    public ReportingMethodsPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    // ─── Navigation ──────────────────────────────────────────────────────────────

    public ReportingMethodsPage openConfigurationMenu() {
        actions.click(pimMenu);
        actions.click(configurationTab);
        return this;
    }

    public boolean isReportingMethodsOptionDisplayed() {
        openConfigurationMenu();
        return actions.isDisplayed(reportingMethodsOption);
    }

    public ReportingMethodsPage openReportingMethodsPage() {
        openConfigurationMenu();
        actions.click(reportingMethodsOption);
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
            return actions.isDisplayed(methodsTable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean hasMethodRows() {
        try {
            sleep(2000);
            return !driver.findElements(methodRows).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areRequiredColumnsDisplayed() {
        try {
            int headerCount = driver.findElements(tableColumnHeaders).size();
            return headerCount >= 2; // Reporting Method Name, (Description), Actions
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

    public boolean isMethodNamePresentInTable(String name) {
        By locator = By.xpath("//div[@role='row']//div[contains(@class,'oxd-table-cell')][text()='" + name + "']");
        return actions.isDisplayed(locator);
    }

    // ─── Add Button ───────────────────────────────────────────────────────────────

    public boolean isAddButtonDisplayed() {
        return actions.isDisplayed(addButton);
    }

    public void clickAdd() {
        actions.click(addButton);
    }

    // ─── Add / Create Reporting Method ────────────────────────────────────────────

    public boolean isAddFormOpened() {
        clickAdd();
        return actions.isDisplayed(nameInput);
    }

    public void createReportingMethod(String name) {
        clickAdd();
        actions.type(nameInput, name);
        actions.click(saveButton);
    }

    public void saveAddFormEmpty() {
        clickAdd();
        actions.click(saveButton);
    }

    public void createReportingMethodWithSpecialChars(String specialName) {
        clickAdd();
        actions.type(nameInput, specialName);
        actions.click(saveButton);
    }

    public void attemptDuplicateThenCancelThenSave(String existingName) {
        // Step 1: open Add, type duplicate name, click Cancel
        clickAdd();
        actions.type(nameInput, existingName);
        actions.click(cancelButton);
        // Step 2: open Add again, type the same duplicate name, click Save
        clickAdd();
        actions.type(nameInput, existingName);
        actions.click(saveButton);
    }

    public void doubleClickSaveOnAddForm(String name) {
        clickAdd();
        actions.type(nameInput, name);
        actions.click(saveButton);
        actions.click(saveButton);
    }

    // ─── Edit Reporting Method ────────────────────────────────────────────────────

    public void clickEditFirstRecord() {
        actions.click(firstRowEditButton);
    }

    public boolean isEditFormPrefilled() {
        clickEditFirstRecord();
        try {
            String value = driver.findElement(nameInput).getAttribute("value");
            return value != null && !value.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public void editFirstRecord(String newName) {
        clickEditFirstRecord();
        driver.findElement(nameInput).clear();
        actions.type(nameInput, newName);
        actions.click(saveButton);
    }

    public void editFirstRecordWithSpecialChars(String specialName) {
        clickEditFirstRecord();
        driver.findElement(nameInput).clear();
        actions.type(nameInput, specialName);
        actions.click(saveButton);
    }

    /**
     * Captures the original name, opens edit, types a new name but does NOT save,
     * then refreshes the page. Returns the original name so the caller can verify
     * it was not corrupted afterward.
     */
    public String modifyWithoutSavingThenRefresh(String newUnsavedName) {
        clickEditFirstRecord();
        String originalValue = driver.findElement(nameInput).getAttribute("value");
        driver.findElement(nameInput).clear();
        actions.type(nameInput, newUnsavedName);
        driver.navigate().refresh();
        return originalValue;
    }

    // ─── Delete Reporting Method ──────────────────────────────────────────────────

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