package org.example.pages.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class ProviderListPage extends Base {

    // ─── Navigation ──────────────────────────────────────────────────────────────
    private final By adminMenu = By.xpath("//span[text()='Admin']");
    private final By configurationMenu = By.xpath("//span[text()='Configuration ']");
    private final By openIdOption = By.xpath("//a[text()='OpenID Connect']");

    // ─── Table ───────────────────────────────────────────────────────────────────
    private final By providerRows =
            By.cssSelector(".oxd-table-body .oxd-table-row");
    private final By providerNameCells =
            By.xpath("(//div[@role='table']//div[@role='row'])[2]//div[1]");

    // ─── Add Provider ─────────────────────────────────────────────────────────────
    private final By addButton =
            By.xpath("//button[normalize-space()='Add']");
    private final By providerNameInput =
            By.xpath("(//input[contains(@class,'oxd-input')])[2]");
    private final By providerUrlInput =
            By.xpath("(//input[contains(@class,'oxd-input')])[3]");
    private final By clientIdInput =
            By.xpath("(//input[contains(@class,'oxd-input')])[4]");
    private final By clientSecretInput =
            By.xpath("(//input[contains(@class,'oxd-input')])[5]");
    private final By saveButton =
            By.xpath("//button[@type='submit']");
    private final By cancelButton =
            By.xpath("//button[normalize-space()='Cancel']");

    // ─── Edit ────────────────────────────────────────────────────────────────────
    private final By editButton =
            By.cssSelector(".bi-pencil-fill:nth-of-type(1)");

    // ─── Delete ──────────────────────────────────────────────────────────────────
    private final By deleteIcon =
            By.xpath("(//i[contains(@class,'bi-trash')])[1]");
    private final By confirmDeleteBtn =
            By.xpath("//button[normalize-space()='Yes, Delete']");
    private final By cancelDeleteBtn =
            By.xpath("//button[normalize-space()='No, Cancel']");

    // ─── Validation ──────────────────────────────────────────────────────────────
    private final By requiredErrors =
            By.xpath("//span[text()='Required']");
    private final By requiredError =
            By.xpath("(//span[text()='Required'])[1]");

    // ─── Toasts / Messages ────────────────────────────────────────────────────────
    private final By successToast =
            By.xpath("//p[contains(text(),'Successfully')]");

    // ─── Empty state ──────────────────────────────────────────────────────────────
    private final By noRecordsFound =
            By.xpath("//*[contains(text(),'No Records Found')]");

    private WebDriver driver;
    private ActionsBot actions;

    public ProviderListPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    // ─── Navigation ──────────────────────────────────────────────────────────────

    public ProviderListPage openProviderListPage() {
        actions.click(adminMenu);
        actions.click(configurationMenu);
        actions.click(openIdOption);
        return this;
    }

    public boolean pageOpened(String url) {
        return actions.inTargetPage(url);
    }

    // ─── Table ───────────────────────────────────────────────────────────────────

    public boolean isProviderTableDisplayed() {
        try {
            sleep(3000);
            return !driver.findElements(providerRows).isEmpty();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isNoRecordsFoundDisplayed() {
        return actions.isDisplayed(noRecordsFound);
    }

    // ─── Add Provider ─────────────────────────────────────────────────────────────

    public void clickAdd() {
        actions.click(addButton);
    }

    public boolean isAddButtonDisplayed() {
        return actions.isDisplayed(addButton);
    }

    public void createProvider(String name, String url, String clientId, String clientSecret) {
        clickAdd();
        actions.type(providerNameInput, name);
        actions.type(providerUrlInput, url);
        actions.type(clientIdInput, clientId);
        actions.type(clientSecretInput, clientSecret);
        actions.click(saveButton);
    }

    public void submitAddFormEmpty() {
        clickAdd();
        actions.click(saveButton);
    }

    public void submitAddFormWithOnlyName(String name) {
        clickAdd();
        actions.type(providerNameInput, name);
        actions.click(saveButton);
    }

    public void clickCancelOnAddDialog() {
        clickAdd();
        actions.click(cancelButton);
    }

    // ─── Edit Provider ────────────────────────────────────────────────────────────

    public void editFirstProvider(String newClientId, String newClientSecret) {
        actions.click(editButton);
        driver.findElement(clientIdInput).clear();
        actions.type(clientIdInput, newClientId);
        driver.findElement(clientSecretInput).clear();
        actions.type(clientSecretInput, newClientSecret);
        actions.click(saveButton);
    }

    // ─── Delete Provider ──────────────────────────────────────────────────────────

    public void deleteFirstProvider() {
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

    // ─── Validation ──────────────────────────────────────────────────────────────

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

    // ─── Toasts / Messages ────────────────────────────────────────────────────────

    public boolean isSuccessMessageDisplayed() {
        return actions.isDisplayed(successToast);
    }

    public String getSuccessMessage() {
        return actions.getTextWhenVisible(successToast);
    }
}