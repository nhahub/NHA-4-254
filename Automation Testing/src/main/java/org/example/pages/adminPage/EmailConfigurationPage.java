package org.example.pages.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmailConfigurationPage extends Base {

    // Navigation
    private final By adminMenu = By.xpath("//span[text()='Admin']");
    private final By configurationMenu = By.xpath("//span[text()='Configuration ']");
    private final By emailConfigurationOption =
            By.xpath("//a[contains(@href,'listMailConfiguration')]");
    // Fields
    private final By mailSentAsInput =
            By.xpath("//label[text()='Mail Sent As']/../following-sibling::div//input");
    private final By smtpHostInput =
            By.xpath("//label[text()='SMTP Host']/../following-sibling::div//input");
    private final By smtpPortInput =
            By.xpath("//label[text()='SMTP Port']/../following-sibling::div//input");
    private final By smtpUsernameInput =
            By.xpath("//label[text()='SMTP Username']/../following-sibling::div//input");
    private final By smtpPasswordInput =
            By.xpath("//label[text()='SMTP Password']/../following-sibling::div//input");
    // Authentication
    private final By authenticationCheckbox =
            By.xpath("//span[contains(@class,'oxd-checkbox-input')]");
    // Security
    private final By tlsRadio =
            By.xpath("//label[contains(.,'TLS')]");
    private final By sslRadio =
            By.xpath("//label[contains(.,'SSL')]");
    // Buttons
    private final By saveButton =
            By.xpath("//button[@type='submit']");
    private final By resetButton =
            By.xpath("//button[normalize-space()='Reset']");
    private final By sendTestEmailButton =
            By.xpath("//button[contains(.,'Send Test Email')]");
    private final By sendButton =
            By.xpath("//button[contains(.,'Send')]");
    // Test Email Popup
    private final By recipientEmailInput =
            By.xpath("//label[contains(.,'Recipient')]/../following-sibling::div//input");
    // Validation
    private final By requiredError =
            By.xpath("//span[text()='Required']");
    private final By successToast =
            By.xpath("//p[contains(text(),'Successfully')]");
    private final By errorToast =
            By.xpath("//p[contains(text(),'Failed')]");
    private WebDriver driver;
    private ActionsBot actions;

    public EmailConfigurationPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    public EmailConfigurationPage openEmailConfigurationPage() {
        actions.click(adminMenu);
        actions.click(configurationMenu);
        actions.click(emailConfigurationOption);
        return this;
    }

    public boolean pageOpened(String url) {
        return actions.inTargetPage(url);
    }

    public boolean isMailSentAsDisplayed() {
        return actions.isDisplayed(mailSentAsInput);
    }

    public boolean isSMTPHostDisplayed() {
        return actions.isDisplayed(smtpHostInput);
    }

    public boolean isSMTPPortDisplayed() {
        return actions.isDisplayed(smtpPortInput);
    }

    public boolean isAuthenticationSectionDisplayed() {
        return actions.isDisplayed(authenticationCheckbox);
    }

    public boolean isTLSDisplayed() {
        return actions.isDisplayed(tlsRadio);
    }

    public boolean isSSLDisplayed() {
        return actions.isDisplayed(sslRadio);
    }

    public void enterSMTPConfiguration(
            String mailSentAs,
            String host,
            String port,
            String username,
            String password) {

        actions.type(mailSentAsInput, mailSentAs);
        actions.type(smtpHostInput, host);
        actions.type(smtpPortInput, port);
        actions.type(smtpUsernameInput, username);
        actions.type(smtpPasswordInput, password);
    }

    public void enableAuthentication() {
        actions.click(authenticationCheckbox);
    }

    public void selectTLS() {
        actions.click(tlsRadio);
    }

    public void selectSSL() {
        actions.click(sslRadio);
    }

    public void clickSave() {
        actions.click(saveButton);
    }

    public void clickReset() {
        actions.click(resetButton);
    }

    public void clickSendTestEmail() {
        actions.click(sendTestEmailButton);
    }

    public void enterRecipientEmail(String email) {
        actions.type(recipientEmailInput, email);
    }

    public void clickSendEmail() {
        actions.click(sendButton);
    }

    public boolean isRequiredValidationDisplayed() {
        return actions.isDisplayed(requiredError);
    }

    public boolean isSuccessMessageDisplayed() {
        return actions.isDisplayed(successToast);
    }

    public boolean isErrorMessageDisplayed() {
        return actions.isDisplayed(errorToast);
    }

    public String getSuccessMessage() {
        return actions.getTextWhenVisible(successToast);
    }

    public String getErrorMessage() {
        return actions.getTextWhenVisible(errorToast);
    }
}