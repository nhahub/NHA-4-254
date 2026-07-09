package org.example.pages.PIM;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class EmployeeReportsPage extends Base {

    // ─── Navigation ──────────────────────────────────────────────────────────────
    private final By pimMenu = By.xpath("//span[text()='PIM']");
    private final By reportsTab = By.xpath("//a[text()='Reports']");

    // ─── Page Header ─────────────────────────────────────────────────────────────
    private final By pageHeader =
            By.xpath("//h6[contains(@class,'oxd-text--h6')]");

    // ─── Search Section ───────────────────────────────────────────────────────────
    private final By reportNameInput =
            By.xpath("(//label[text()='Report Name']/following::input)[1]");
    private final By searchButton =
            By.xpath("//button[normalize-space()='Search']");
    private final By resetButton =
            By.xpath("//button[normalize-space()='Reset']");

    // ─── Table ───────────────────────────────────────────────────────────────────
    private final By reportsTable =
            By.cssSelector(".oxd-table");
    private final By reportRows =
            By.cssSelector(".oxd-table-body .oxd-table-row");
    private final By tableColumnHeaders =
            By.xpath("//div[@role='columnheader']");
    private final By noRecordsFound =
            By.xpath("//*[contains(text(),'No Records Found')]");

    private final By firstRowViewButton =
            By.xpath("(//div[@role='row'])[2]//i[contains(@class,'bi-eye')]");
    private final By firstRowEditButton =
            By.xpath("(//div[@role='row'])[2]//i[contains(@class,'bi-pencil-fill')]");
    private final By firstRowDeleteButton =
            By.xpath("(//div[@role='row'])[2]//i[contains(@class,'bi-trash')]");
    private final By firstRowCheckbox =
            By.xpath("(//div[@role='row'])[2]//input[@type='checkbox']");

    // ─── Add Button ───────────────────────────────────────────────────────────────
    private final By addButton =
            By.xpath("//button[normalize-space()='Add']");

    // ─── Add / Edit Report Form ────────────────────────────────────────────────────
    private final By reportNameFormInput =
            By.xpath("(//label[text()='Report Name']/following::input)[1]");
    private final By saveButton =
            By.xpath("//button[@type='submit']");
    private final By cancelButton =
            By.xpath("//button[normalize-space()='Cancel']");

    // Selection Criteria / Display Field controls (generic, dropdown-driven)
    private final By selectionCriteriaDropdown =
            By.xpath("(//label[text()='Job Title']/following::div[contains(@class,'oxd-select-text')])[1]");
    private final By addCriteriaButton =
            By.xpath("//button[contains(.,'Add Criteria') or contains(.,'+')][1]");
    private final By displayFieldAvailableList =
            By.xpath("//div[contains(@class,'employee-distinct-list')]//div[@role='listbox'][1]");
    private final By displayFieldSelectedList =
            By.xpath("//div[contains(@class,'employee-distinct-list')]//div[@role='listbox'][2]");
    private final By addDisplayFieldButton =
            By.xpath("//button[contains(@class,'oxd-icon-button')][contains(.,'')][1]");

    // ─── Delete Confirmation ──────────────────────────────────────────────────────
    private final By confirmDeleteBtn =
            By.xpath("//button[normalize-space()='Yes, Delete']");
    private final By cancelDeleteBtn =
            By.xpath("//button[normalize-space()='No, Cancel']");

    // ─── Validation ───────────────────────────────────────────────────────────────
    private final By requiredError =
            By.xpath("(//span[text()='Required'])[1]");
    private final By warningToast =
            By.xpath("//p[contains(text(),'Warning')] | //div[contains(@class,'oxd-toast--warn')]");
    private final By displayFieldWarningMessage =
            By.xpath("//*[contains(text(),'At least one display field')]");

    // ─── Toasts / Messages ────────────────────────────────────────────────────────
    private final By successToast =
            By.xpath("//p[contains(text(),'Successfully')]");
    private final By updatedToast =
            By.xpath("//p[contains(text(),'Successfully Updated')]");
    private final By deletedToast =
            By.xpath("//p[contains(text(),'Successfully Deleted')]");
    private final By savedToast =
            By.xpath("//p[contains(text(),'Successfully Saved')]");

    // ─── Report View Page ──────────────────────────────────────────────────────────
    private final By reportViewHeading =
            By.xpath("//h6[contains(@class,'oxd-text--h6')]");
    private final By reportViewTable =
            By.cssSelector(".oxd-table");

    private WebDriver driver;
    private ActionsBot actions;

    public EmployeeReportsPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    // ─── Navigation ──────────────────────────────────────────────────────────────

    public EmployeeReportsPage openReportsPage() {
        actions.click(pimMenu);
        actions.click(reportsTab);
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

    public boolean isReportsNavLinkDisplayed() {
        return actions.isDisplayed(reportsTab);
    }

    // ─── Table ───────────────────────────────────────────────────────────────────

    public boolean isTableDisplayed() {
        try {
            sleep(2000);
            return actions.isDisplayed(reportsTable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean hasReportRows() {
        try {
            sleep(2000);
            return !driver.findElements(reportRows).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areRequiredColumnsDisplayed() {
        try {
            int headerCount = driver.findElements(tableColumnHeaders).size();
            return headerCount >= 2; // Report Name, Actions
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNoRecordsFoundDisplayed() {
        return actions.isDisplayed(noRecordsFound);
    }

    public boolean isReportNamePresentInTable(String name) {
        By locator = By.xpath("//div[@role='row']//div[contains(@class,'oxd-table-cell')][text()='" + name + "']");
        return actions.isDisplayed(locator);
    }

    // ─── Search ──────────────────────────────────────────────────────────────────

    public void searchByReportName(String name) {
        actions.type(reportNameInput, name);
        actions.click(searchButton);
    }

    public void clickReset() {
        actions.click(resetButton);
    }

    // ─── View Report ──────────────────────────────────────────────────────────────

    public void clickViewOnFirstReport() {
        actions.click(firstRowViewButton);
    }

    public boolean isReportViewPageDisplayed() {
        try {
            sleep(2000);
            return actions.isDisplayed(reportViewHeading) && actions.isDisplayed(reportViewTable);
        } catch (Exception e) {
            return false;
        }
    }

    // ─── Edit Report ────────────────────────────────────────────────────────────────

    public void clickEditOnFirstReport() {
        actions.click(firstRowEditButton);
    }

    public boolean isEditFormPrefilled() {
        clickEditOnFirstReport();
        try {
            String value = driver.findElement(reportNameFormInput).getAttribute("value");
            return value != null && !value.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public void editFirstReportName(String newName) {
        clickEditOnFirstReport();
        driver.findElement(reportNameFormInput).clear();
        actions.type(reportNameFormInput, newName);
        actions.click(saveButton);
    }

    // ─── Delete Report ──────────────────────────────────────────────────────────────

    public void clickDeleteOnFirstReport() {
        actions.click(firstRowDeleteButton);
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

    public void deleteFirstReport() {
        clickDeleteOnFirstReport();
        confirmDelete();
    }

    // ─── Add Report ─────────────────────────────────────────────────────────────────

    public void clickAdd() {
        actions.click(addButton);
    }

    public boolean isAddFormDisplayed() {
        clickAdd();
        return actions.isDisplayed(reportNameFormInput) && actions.isDisplayed(saveButton);
    }

    public void fillReportName(String name) {
        actions.type(reportNameFormInput, name);
    }

    public void clickSave() {
        actions.click(saveButton);
    }

    /**
     * Creates a report with name + at least one display field.
     * Display field selection in OrangeHRM uses a dual-list (available/selected)
     * widget; this performs a simplified select-and-add gesture.
     */
    public void createReportWithDisplayField(String name, String displayFieldLabel) {
        clickAdd();
        fillReportName(name);
        By fieldOption = By.xpath("//span[text()='" + displayFieldLabel + "']");
        actions.click(fieldOption);
        actions.click(addDisplayFieldButton);
        actions.click(saveButton);
    }

    public void createReportNameOnlyNoDisplayField(String name) {
        clickAdd();
        fillReportName(name);
        actions.click(saveButton);
    }

    public void saveAddFormWithEmptyName(String displayFieldLabel) {
        clickAdd();
        By fieldOption = By.xpath("//span[text()='" + displayFieldLabel + "']");
        actions.click(fieldOption);
        actions.click(addDisplayFieldButton);
        actions.click(saveButton);
    }

    // ─── Validation ───────────────────────────────────────────────────────────────

    public boolean isRequiredErrorDisplayed() {
        return actions.isDisplayed(requiredError);
    }

    public boolean isDisplayFieldWarningDisplayed() {
        return actions.isDisplayed(displayFieldWarningMessage) || actions.isDisplayed(warningToast);
    }

    // ─── Toasts / Messages ────────────────────────────────────────────────────────

    public boolean isSuccessMessageDisplayed() {
        return actions.isDisplayed(successToast);
    }

    public boolean isSavedMessageDisplayed() {
        return actions.isDisplayed(savedToast) || actions.isDisplayed(successToast);
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