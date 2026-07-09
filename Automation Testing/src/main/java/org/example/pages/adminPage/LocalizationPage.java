package org.example.pages.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LocalizationPage extends Base {

    // Navigation
    private final By adminMenu = By.xpath("//span[text()='Admin']");
    private final By configurationMenu = By.xpath("//span[text()='Configuration ']");
    private final By localizationOption = By.xpath("//a[text()='Localization']");
    // Form Fields
    private final By languageDropdown =
            By.xpath("(//div[contains(@class,'oxd-select-text-input')])[1]");
    private final By dateFormatDropdown =
            By.xpath("(//div[contains(@class,'oxd-select-text-input')])[2]");
    private final By saveButton =
            By.xpath("//button[@type='submit']");
    private final By resetButton =
            By.xpath("//button[normalize-space()='Reset']");
    // Messages
    private final By successToast =
            By.xpath("//p[contains(text(),'Successfully')]");
    private WebDriver driver;
    private ActionsBot actions;

    public LocalizationPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    public LocalizationPage openLocalizationPage() {

        actions.click(adminMenu);
        actions.click(configurationMenu);
        actions.click(localizationOption);

        return this;
    }

    public boolean pageOpened(String url) {
        return actions.inTargetPage(url);
    }

    public boolean isLocalizationPageDisplayed() {
        return driver.getCurrentUrl().contains("localization");
    }

    public boolean isLanguageDropdownDisplayed() {
        return actions.isDisplayed(languageDropdown);
    }

    public boolean isDateFormatDropdownDisplayed() {
        return actions.isDisplayed(dateFormatDropdown);
    }

    public void openLanguageDropdown() {
        actions.click(languageDropdown);
    }

    public void openDateFormatDropdown() {
        actions.click(dateFormatDropdown);
    }

    public void clickSave() {
        actions.click(saveButton);
    }

    public void clickReset() {
        actions.click(resetButton);
    }

    public boolean isSuccessMessageDisplayed() {
        return actions.isDisplayed(successToast);
    }

    public String getSuccessMessage() {
        return actions.getTextWhenVisible(successToast);
    }
}