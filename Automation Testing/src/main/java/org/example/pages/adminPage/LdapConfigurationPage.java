package org.example.pages.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class LdapConfigurationPage extends Base {

    // ─── Navigation ──────────────────────────────────────────────────────────────
    private final By adminMenu = By.xpath("//span[text()='Admin']");
    private final By configurationMenu = By.xpath("//span[text()='Configuration ']");
    private final By ldapOption = By.xpath("//a[text()='LDAP Configuration']");

    // ─── Page Header ─────────────────────────────────────────────────────────────
    private final By pageTitle =
            By.xpath("//h6[contains(@class,'oxd-topbar-header-breadcrumb-module')]");

    // ─── Enable LDAP Toggle ───────────────────────────────────────────────────────
    private final By enableLdapToggle =
            By.xpath("(//span[contains(@class,'oxd-switch-input')])[1]");

    // ─── Connection Fields ────────────────────────────────────────────────────────
    private final By serverUriInput =
            By.xpath("(//input[contains(@class,'oxd-input')])[2]");
    private final By portInput =
            By.xpath("(//input[contains(@class,'oxd-input')])[3]");
    private final By encryptionDropdown =
            By.xpath("(//div[contains(@class,'oxd-select-wrapper')])[1]");
    private final By bindDnInput =
            By.xpath("(//input[contains(@class,'oxd-input')])[4]");
    private final By bindPasswordInput =
            By.xpath("//input[@type='password']");

    // ─── User Search Fields ───────────────────────────────────────────────────────
    private final By userSearchBaseInput =
            By.xpath("(//input[contains(@class,'oxd-input')])[5]");
    private final By searchScopeDropdown =
            By.xpath("(//div[contains(@class,'oxd-select-wrapper')])[2]");
    private final By userNameAttributeInput =
            By.xpath("(//input[contains(@class,'oxd-input')])[6]");
    // ─── Buttons ──────────────────────────────────────────────────────────────────
    private final By saveButton = By.xpath("//button[@type='submit']");
    private final By cancelButton = By.xpath("//button[normalize-space()='Cancel']");
    private final By testConnectionBtn =
            By.xpath("//button[normalize-space()='Test Connection']");
    // ─── Validation ───────────────────────────────────────────────────────────────
    private final By requiredError =
            By.xpath("(//span[text()='Required'])[1]");
    private final By requiredErrors =
            By.xpath("//span[text()='Required']");
    private final By invalidUriError =
            By.xpath("//span[contains(text(),'Invalid') or contains(text(),'valid URI') or contains(text(),'valid URL')]");
    private final By invalidPortError =
            By.xpath("//span[contains(text(),'Invalid') or contains(text(),'valid port') or contains(text(),'numeric')]");
    private final By charLimitError =
            By.className("oxd-input-field-error-message");
    // ─── Toasts / Messages ────────────────────────────────────────────────────────
    private final By successToast =
            By.xpath("//p[contains(text(),'Successfully')]");
    private final By testConnectionSuccess =
            By.xpath("//*[contains(text(),'Connection Successful') or contains(text(),'connection successful')]");
    private final By testConnectionFailed =
            By.xpath("//*[contains(text(),'Connection Failed') or contains(text(),'connection failed') or contains(text(),'Failed')]");
    // ─── Field visibility (when LDAP disabled) ────────────────────────────────────
    private final By serverUriField =
            By.xpath("(//div[contains(@class,'oxd-input-group')])[2]");
    private WebDriver driver;
    private ActionsBot actions;
    public LdapConfigurationPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    // ─── Encryption Dropdown Options ──────────────────────────────────────────────
    private By encryptionOption(String type) {
        return By.xpath("//span[text()='" + type + "']");
    }

    // ─── Navigation ──────────────────────────────────────────────────────────────

    public LdapConfigurationPage openLdapConfigurationPage() {
        actions.click(adminMenu);
        actions.click(configurationMenu);
        actions.click(ldapOption);
        return this;
    }

    public boolean pageOpened(String url) {
        return actions.inTargetPage(url);
    }

    // ─── Page Assertions ─────────────────────────────────────────────────────────

    public boolean isPageLoaded() {
        try {
            sleep(3000);
            return actions.isDisplayed(enableLdapToggle);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isPageTitleDisplayed() {
        return actions.isDisplayed(pageTitle);
    }

    public boolean areAllFieldsDisplayed() {
        return actions.isDisplayed(serverUriInput)
                && actions.isDisplayed(portInput)
                && actions.isDisplayed(encryptionDropdown)
                && actions.isDisplayed(bindDnInput)
                && actions.isDisplayed(bindPasswordInput);
    }

    public boolean isSaveButtonDisplayed() {
        return actions.isDisplayed(saveButton);
    }

    public boolean isTestConnectionButtonDisplayed() {
        return actions.isDisplayed(testConnectionBtn);
    }

    public boolean isBindPasswordMasked() {
        try {
            String inputType = driver.findElement(bindPasswordInput).getAttribute("type");
            return "password".equals(inputType);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areEncryptionOptionsAvailable() {
        actions.click(encryptionDropdown);
        return actions.isDisplayed(encryptionOption("None"))
                || actions.isDisplayed(By.xpath("//span[contains(text(),'SSL')]"))
                || actions.isDisplayed(By.xpath("//span[contains(text(),'TLS')]"));
    }

    public boolean areSearchScopeOptionsAvailable() {
        actions.click(searchScopeDropdown);
        return actions.isDisplayed(By.xpath("//span[contains(text(),'One Level') or contains(text(),'Sub Tree')]"));
    }

    public boolean isLdapFieldsDisabled() {
        try {
            String disabled = driver.findElement(serverUriInput).getAttribute("disabled");
            return "true".equals(disabled) || disabled != null;
        } catch (Exception e) {
            return false;
        }
    }

    // ─── Enable / Disable LDAP ───────────────────────────────────────────────────

    public void enableLdap() {
        actions.click(enableLdapToggle);
    }

    public void disableLdap() {
        actions.click(enableLdapToggle);
    }

    // ─── Fill Configuration ───────────────────────────────────────────────────────

    public void fillLdapConfiguration(String serverUri, String port,
                                      String bindDn, String bindPassword,
                                      String userSearchBase) {
        actions.type(serverUriInput, serverUri);
        actions.type(portInput, port);
        if (bindDn != null) actions.type(bindDnInput, bindDn);
        if (bindPassword != null) actions.type(bindPasswordInput, bindPassword);
        if (userSearchBase != null) actions.type(userSearchBaseInput, userSearchBase);
    }

    public void setEncryption(String encryptionType) {
        actions.click(encryptionDropdown);
        actions.click(encryptionOption(encryptionType));
    }

    public void fillBindDn(String bindDn) {
        actions.type(bindDnInput, bindDn);
    }

    public void fillUserNameAttribute(String attribute) {
        actions.type(userNameAttributeInput, attribute);
    }

    // ─── Save / Cancel ────────────────────────────────────────────────────────────

    public void clickSave() {
        actions.click(saveButton);
    }

    public void clickCancel() {
        actions.click(cancelButton);
    }

    public void enableAndSaveValidConfig() {
        enableLdap();
        fillLdapConfiguration(
                "ldap://company.com", "389",
                "cn=admin,dc=company,dc=com", "secret",
                "dc=company,dc=com"
        );
        clickSave();
    }

    public void saveWithEmptyServerUri() {
        enableLdap();
        clickSave();
    }

    public void saveWithInvalidUri() {
        enableLdap();
        actions.type(serverUriInput, "notanurl");
        clickSave();
    }

    public void saveWithInvalidPort() {
        enableLdap();
        actions.type(serverUriInput, "ldap://company.com");
        actions.type(portInput, "abc");
        clickSave();
    }

    public void saveWithLongServerUri() {
        enableLdap();
        String longUri = "ldap://" + "a".repeat(500) + ".com";
        actions.type(serverUriInput, longUri);
        clickSave();
    }

    public void saveWithSslConfig() {
        enableLdap();
        actions.type(serverUriInput, "ldaps://company.com");
        actions.type(portInput, "636");
        setEncryption("SSL");
        clickSave();
    }

    public void modifyAndClickCancel() {
        actions.type(serverUriInput, "ldap://modified-server.com");
        clickCancel();
    }

    // ─── Test Connection ──────────────────────────────────────────────────────────

    public void clickTestConnection() {
        actions.click(testConnectionBtn);
    }

    public void testConnectionWithValidConfig() {
        enableLdap();
        fillLdapConfiguration(
                "ldap://company.com", "389",
                "cn=admin,dc=company,dc=com", "secret",
                "dc=company,dc=com"
        );
        clickTestConnection();
    }

    public void testConnectionWithInvalidServer() {
        enableLdap();
        actions.type(serverUriInput, "ldap://invalid.server");
        clickTestConnection();
    }

    public void testConnectionSslConfig() {
        enableLdap();
        actions.type(serverUriInput, "ldaps://company.com");
        actions.type(portInput, "636");
        setEncryption("SSL");
        clickTestConnection();
    }

    public void testConnectionTlsConfig() {
        enableLdap();
        actions.type(serverUriInput, "ldap://company.com");
        actions.type(portInput, "389");
        setEncryption("TLS");
        clickTestConnection();
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

    public boolean isInvalidUriErrorDisplayed() {
        return actions.isDisplayed(invalidUriError);
    }

    public boolean isInvalidPortErrorDisplayed() {
        return actions.isDisplayed(invalidPortError);
    }

    public boolean isCharLimitErrorDisplayed() {
        return actions.isDisplayed(charLimitError);
    }

    // ─── Toasts / Messages ────────────────────────────────────────────────────────

    public boolean isSuccessMessageDisplayed() {
        return actions.isDisplayed(successToast);
    }

    public String getSuccessMessage() {
        return actions.getTextWhenVisible(successToast);
    }

    public boolean isTestConnectionSuccessDisplayed() {
        return actions.isDisplayed(testConnectionSuccess);
    }

    public boolean isTestConnectionFailedDisplayed() {
        return actions.isDisplayed(testConnectionFailed);
    }
}