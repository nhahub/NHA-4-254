package org.example.pages.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class LanguagePackagesPage extends Base {

    // Navigation
    private final By adminMenu = By.xpath("//span[text()='Admin']");
    private final By configurationMenu = By.xpath("//span[text()='Configuration ']");
    private final By languagePackagesOption = By.xpath("//a[text()='Language Packages']");

    // Table
    private final By packageRows =
            By.cssSelector(".oxd-table-body .oxd-table-row:nth-of-type(1)");
    private final By languageNameColumn =
            By.xpath("(//div[@role='table']//div[@role='row'])[2]");

    // Column Headers
    private final By languageNameHeader =
            By.xpath("//div[@role='columnheader'][contains(.,'Language')]");
    private final By statusHeader =
            By.xpath("//div[@role='columnheader'][contains(.,'Status')]");
    private final By translationProgressHeader =
            By.xpath("//div[@role='columnheader'][contains(.,'Translation Progress')]");
    private final By actionsHeader =
            By.xpath("//div[@role='columnheader'][contains(.,'Actions')]");

    // Add Package
    private final By addButton =
            By.xpath("//button[normalize-space()='Add']");
    private final By languageDropdown =
            By.xpath("(//div[contains(@class,'oxd-select-wrapper')])[1]");
    private final By saveButton =
            By.xpath("//button[@type='submit']");
    private final By cancelButton =
            By.xpath("//button[normalize-space()='Cancel']");

    // Search
    private final By searchInput =
            By.xpath("(//input[contains(@class,'oxd-input')])[2]");
    private final By searchButton =
            By.xpath("//button[normalize-space()='Search']");

    // Validation
    private final By requiredError =
            By.xpath("//span[text()='Required']");
    private final By alreadyExistsError =
            By.xpath("//span[contains(text(),'Already exists')]");
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

    // Edit / Update
    private final By editButton =
            By.cssSelector(".bi-pencil-fill:nth-of-type(1)");

    // Status toggle (Enable/Disable)
    private final By statusToggle =
            By.xpath("(//span[contains(@class,'oxd-switch-input')])[1]");

    // Empty state
    private final By noRecordsFound =
            By.xpath("//*[contains(text(),'No Records Found')]");

    // Translation Progress column values
    private final By translationProgressValues =
            By.xpath("//div[@role='table']//div[@role='row'][position()>1]//div[contains(@class,'progress')]");

    // Checkboxes for bulk selection
    private final By checkboxes =
            By.cssSelector("input[type='checkbox']");

    private WebDriver driver;
    private ActionsBot actions;

    public LanguagePackagesPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    // ─── Navigation ─────────────────────────────────────────────────────────────

    public LanguagePackagesPage openLanguagePackagesPage() {
        actions.click(adminMenu);
        actions.click(configurationMenu);
        actions.click(languagePackagesOption);
        return this;
    }

    public boolean pageOpened(String url) {
        return actions.inTargetPage(url);
    }

    // ─── Table Verification ──────────────────────────────────────────────────────

    public boolean isLanguagePackagesTableDisplayed() {
        try {
            sleep(3000);
            return !driver.findElements(packageRows).isEmpty();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean areRequiredColumnsDisplayed() {
        return actions.isDisplayed(languageNameHeader)
                && actions.isDisplayed(statusHeader)
                && actions.isDisplayed(translationProgressHeader)
                && actions.isDisplayed(actionsHeader);
    }

    public boolean isTranslationProgressDisplayedAsPercentage() {
        // Verify at least one progress value is visible in the table
        return actions.isDisplayed(translationProgressValues);
    }

    // ─── Add Package ─────────────────────────────────────────────────────────────

    public void clickAdd() {
        actions.click(addButton);
    }

    public boolean isAddButtonDisplayed() {
        return actions.isDisplayed(addButton);
    }

    public void addLanguagePackage(String languageName) {
        clickAdd();
        // Select language from dropdown
        actions.click(languageDropdown);
        By languageOption = By.xpath("//span[text()='" + languageName + "']");
        actions.click(languageOption);
        actions.click(saveButton);
    }

    public void submitAddFormWithoutSelection() {
        clickAdd();
        actions.click(saveButton);
    }

    public void clickCancelOnAddDialog() {
        clickAdd();
        actions.click(cancelButton);
    }

    // ─── Search ──────────────────────────────────────────────────────────────────

    public void searchLanguagePackage(String name) {
        actions.type(searchInput, name);
        actions.click(searchButton);
    }

    // ─── Validation / Messages ───────────────────────────────────────────────────

    public boolean isSuccessMessageDisplayed() {
        return actions.isDisplayed(successToast);
    }

    public String getSuccessMessage() {
        return actions.getTextWhenVisible(successToast);
    }

    public boolean isRequiredErrorDisplayed() {
        return actions.isDisplayed(requiredError);
    }

    public boolean isAlreadyExistsErrorDisplayed() {
        return actions.isDisplayed(alreadyExistsError);
    }

    public boolean isNoRecordsFoundDisplayed() {
        return actions.isDisplayed(noRecordsFound);
    }

    // ─── Delete ──────────────────────────────────────────────────────────────────

    public void deleteFirstPackage() {
        actions.click(deleteIcon);
        actions.click(confirmDeleteBtn);
    }

    public void cancelDelete() {
        actions.click(deleteIcon);
        actions.click(cancelDeleteBtn);
    }

    public boolean isConfirmationDialogDisplayed() {
        return actions.isDisplayed(confirmDeleteBtn) && actions.isDisplayed(cancelDeleteBtn);
    }

    // ─── Bulk Delete ─────────────────────────────────────────────────────────────

    public LanguagePackagesPage selectMultipleRows(int count) {
        By checkbox = By.xpath("(//label[contains(@class,'oxd-checkbox-wrapper')])[2]");
        actions.click(checkbox);
        return this;
    }

    public void deleteSelected() {
        actions.click(deleteSelectedBtn);
        actions.click(confirmDeleteBtn);
    }

    // ─── Edit / Update ───────────────────────────────────────────────────────────

    public void clickEditOnFirstPackage() {
        actions.click(editButton);
    }

    // ─── Enable / Disable ────────────────────────────────────────────────────────

    public void toggleFirstPackageStatus() {
        actions.click(statusToggle);
        actions.click(saveButton);
    }
}