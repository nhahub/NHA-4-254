package org.example.pages.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class OAuthClientPage extends Base {

    // ─── Navigation ──────────────────────────────────────────────────────────────
    private final By adminMenu = By.xpath("//span[text()='Admin']");
    private final By configurationMenu = By.xpath("//span[text()='Configuration ']");
    private final By oauthClientsOption = By.xpath("//a[text()='Register OAuth Client']");

    // ─── Table ───────────────────────────────────────────────────────────────────
    private final By clientRows =
            By.cssSelector(".oxd-table-body .oxd-table-row");
    private final By nameColumn =
            By.xpath("(//div[@role='table']//div[@role='row'])[2]//div[1]");
    private final By redirectUriColumn =
            By.xpath("(//div[@role='table']//div[@role='row'])[2]//div[2]");
    private final By statusColumn =
            By.xpath("(//div[@role='table']//div[@role='row'])[2]//div[3]");

    // ─── Column Headers ───────────────────────────────────────────────────────────
    private final By nameHeader =
            By.xpath("//div[@role='columnheader'][contains(.,'Name')]");
    private final By redirectUriHeader =
            By.xpath("//div[@role='columnheader'][contains(.,'Redirect URI')]");
    private final By statusHeader =
            By.xpath("//div[@role='columnheader'][contains(.,'Status') or contains(.,'Enabled')]");

    // ─── Add Client ───────────────────────────────────────────────────────────────
    private final By addButton =
            By.xpath("//button[normalize-space()='Add']");
    private final By nameInput =
            By.xpath("(//input[contains(@class,'oxd-input')])[2]");
    private final By redirectUriInput =
            By.xpath("(//input[contains(@class,'oxd-input')])[3]");
    private final By enableClientToggle =
            By.xpath("(//span[contains(@class,'oxd-switch-input')])[1]");
    private final By confidentialClientToggle =
            By.xpath("(//span[contains(@class,'oxd-switch-input')])[2]");
    private final By saveButton =
            By.xpath("//button[@type='submit']");
    private final By cancelButton =
            By.xpath("//button[normalize-space()='Cancel']");

    // ─── Edit ─────────────────────────────────────────────────────────────────────
    private final By editButton =
            By.cssSelector(".bi-pencil-fill:nth-of-type(1)");

    // ─── Delete ───────────────────────────────────────────────────────────────────
    private final By deleteIcon =
            By.xpath("(//i[contains(@class,'bi-trash')])[1]");
    private final By confirmDeleteBtn =
            By.xpath("//button[normalize-space()='Yes, Delete']");
    private final By cancelDeleteBtn =
            By.xpath("//button[normalize-space()='No, Cancel']");

    // ─── Validation ───────────────────────────────────────────────────────────────
    private final By requiredError =
            By.xpath("(//span[text()='Required'])[1]");
    private final By requiredErrors =
            By.xpath("//span[text()='Required']");
    private final By invalidUrlError =
            By.xpath("//span[contains(text(),'Invalid') or contains(text(),'valid URI') or contains(text(),'valid URL')]");
    private final By alreadyExistsError =
            By.xpath("//span[contains(text(),'Already exists') or contains(text(),'already exists')]");

    // ─── Toasts / Messages ────────────────────────────────────────────────────────
    private final By successToast =
            By.xpath("//p[contains(text(),'Successfully')]");

    // ─── Empty state ──────────────────────────────────────────────────────────────
    private final By noRecordsFound =
            By.xpath("//*[contains(text(),'No Records Found')]");

    private WebDriver driver;
    private ActionsBot actions;

    public OAuthClientPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    // ─── Navigation ──────────────────────────────────────────────────────────────

    public OAuthClientPage openOAuthClientPage() {
        actions.click(adminMenu);
        actions.click(configurationMenu);
        actions.click(oauthClientsOption);
        return this;
    }

    public boolean pageOpened(String url) {
        return actions.inTargetPage(url);
    }

    // ─── Table ───────────────────────────────────────────────────────────────────

    public boolean isClientTableDisplayed() {
        try {
            sleep(3000);
            return !driver.findElements(clientRows).isEmpty();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean areRequiredColumnsDisplayed() {
        return actions.isDisplayed(nameHeader)
                && actions.isDisplayed(redirectUriHeader)
                && actions.isDisplayed(statusHeader);
    }

    public boolean isNoRecordsFoundDisplayed() {
        return actions.isDisplayed(noRecordsFound);
    }

    // ─── Add Client ───────────────────────────────────────────────────────────────

    public void clickAdd() {
        actions.click(addButton);
    }

    public boolean isAddButtonDisplayed() {
        return actions.isDisplayed(addButton);
    }

    public void createClient(String name, String redirectUri,
                             boolean enableClient, boolean confidentialClient) {
        clickAdd();
        actions.type(nameInput, name);
        actions.type(redirectUriInput, redirectUri);

        // Toggles are ON by default in OrangeHRM; click only if we need to switch state
        if (enableClient) {
            actions.click(enableClientToggle);
        }
        if (confidentialClient) {
            actions.click(confidentialClientToggle);
        }
        actions.click(saveButton);
    }

    public void createClientWithTogglesOn(String name, String redirectUri) {
        clickAdd();
        actions.type(nameInput, name);
        actions.type(redirectUriInput, redirectUri);
        // Enable Client toggle ON
        actions.click(enableClientToggle);
        // Confidential Client toggle ON
        actions.click(confidentialClientToggle);
        actions.click(saveButton);
    }

    public void submitAddFormEmpty() {
        clickAdd();
        actions.click(saveButton);
    }

    public void submitWithInvalidUrl(String name, String invalidUrl) {
        clickAdd();
        actions.type(nameInput, name);
        actions.type(redirectUriInput, invalidUrl);
        actions.click(saveButton);
    }

    public void submitDuplicateName(String duplicateName, String redirectUri) {
        clickAdd();
        actions.type(nameInput, duplicateName);
        actions.type(redirectUriInput, redirectUri);
        actions.click(saveButton);
    }

    public void clickCancelOnAddDialog() {
        clickAdd();
        actions.click(cancelButton);
    }

    // ─── Edit Client ──────────────────────────────────────────────────────────────

    public void editFirstClient(String newRedirectUri) {
        actions.click(editButton);
        driver.findElement(redirectUriInput).clear();
        actions.type(redirectUriInput, newRedirectUri);
        actions.click(saveButton);
    }

    public void editFirstClientStatusToggle() {
        actions.click(editButton);
        actions.click(enableClientToggle);
        actions.click(saveButton);
    }

    // ─── Delete Client ────────────────────────────────────────────────────────────

    public void deleteFirstClient() {
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

    public boolean isInvalidUrlErrorDisplayed() {
        return actions.isDisplayed(invalidUrlError);
    }

    public boolean isAlreadyExistsErrorDisplayed() {
        return actions.isDisplayed(alreadyExistsError);
    }

    // ─── Toasts / Messages ────────────────────────────────────────────────────────

    public boolean isSuccessMessageDisplayed() {
        return actions.isDisplayed(successToast);
    }

    public String getSuccessMessage() {
        return actions.getTextWhenVisible(successToast);
    }
}