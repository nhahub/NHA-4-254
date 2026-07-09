package org.example.pages.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CorporateBranding extends Base {

    // Navigation
    private final By adminMenu =
            By.xpath("//span[text()='Admin']");
    private final By configurationMenu =
            By.xpath("//span[text()='Configuration ']");
    private final By corporateBrandingOption =
            By.xpath("//a[contains(@href,'addTheme')]");
    // Branding Fields
    private final By primaryColor =
            By.cssSelector("input[name='primaryColor']");
    private final By secondaryColor =
            By.cssSelector("input[name='secondaryColor']");
    // Uploads
    private final By companyLogoUpload =
            By.xpath("(//input[@type='file'])[1]");
    private final By clientLogoUpload =
            By.xpath("(//input[@type='file'])[2]");
    // Buttons
    private final By publishButton =
            By.xpath("//button[normalize-space()='Publish']");
    private final By resetButton =
            By.xpath("//button[contains(.,'Reset')]");
    private final By cancelButton =
            By.xpath("//button[normalize-space()='Cancel']");
    // Validation
    private final By successToast =
            By.xpath("//p[contains(text(),'Successfully')]");
    private final By errorMessage =
            By.className("oxd-input-field-error-message");
    // Preview
    private final By logoPreview =
            By.cssSelector("img");
    private WebDriver driver;
    private ActionsBot actions;

    public CorporateBranding(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    public CorporateBranding openCorporateBrandingPage() {

        actions.click(adminMenu);
        actions.click(configurationMenu);
        actions.click(corporateBrandingOption);

        return this;
    }

    public boolean pageOpened(String url) {
        return actions.inTargetPage(url);
    }

    public boolean isBrandingPageDisplayed() {
        return actions.isDisplayed(primaryColor);
    }

    public boolean isPrimaryColorDisplayed() {
        return actions.isDisplayed(primaryColor);
    }

    public boolean isSecondaryColorDisplayed() {
        return actions.isDisplayed(secondaryColor);
    }

    public void uploadCompanyLogo(String filePath) {
        driver.findElement(companyLogoUpload)
                .sendKeys(filePath);
    }

    public void uploadClientLogo(String filePath) {
        driver.findElement(clientLogoUpload)
                .sendKeys(filePath);
    }

    public void enterPrimaryColor(String color) {
        actions.type(primaryColor, color);
    }

    public void enterSecondaryColor(String color) {
        actions.type(secondaryColor, color);
    }

    public void clickPublish() {
        actions.click(publishButton);
    }

    public void clickReset() {
        actions.click(resetButton);
    }

    public void clickCancel() {
        actions.click(cancelButton);
    }

    public boolean isSuccessMessageDisplayed() {
        return actions.isDisplayed(successToast);
    }

    public boolean isValidationErrorDisplayed() {
        return actions.isDisplayed(errorMessage);
    }

    public boolean isLogoPreviewDisplayed() {
        return actions.isDisplayed(logoPreview);
    }

    public boolean isPublishButtonDisplayed() {
        return actions.isDisplayed(publishButton);
    }

    public boolean isResetButtonDisplayed() {
        return actions.isDisplayed(resetButton);
    }

    public boolean isCancelButtonDisplayed() {
        return actions.isDisplayed(cancelButton);
    }
}