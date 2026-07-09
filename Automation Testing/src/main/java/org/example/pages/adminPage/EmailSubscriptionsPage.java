package org.example.pages.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmailSubscriptionsPage extends Base {

    private final WebDriver driver;
    private final ActionsBot actions;

    // Navigation
    private final By adminMenu = By.xpath("//span[text()='Admin']");
    private final By configurationMenu = By.xpath("//span[text()='Configuration ']");
    private final By emailSubscriptionsOption =
            By.xpath("//a[text()='Email Subscriptions']");

    // Table
    private final By tableRows =
            By.cssSelector(".oxd-table-body .oxd-table-row");

    private final By notificationTypeColumn =
            By.xpath("//div[text()='Notification Type']");

    private final By subscribersColumn =
            By.xpath("//div[text()='Subscribers']");

    // Checkbox
    private final By firstCheckbox =
            By.xpath("(//label[contains(@class,'oxd-checkbox-wrapper')])[2]");

    // Edit
    private final By editButton =
            By.cssSelector(".bi-pencil-fill");

    // Save
    private final By saveButton =
            By.xpath("//button[@type='submit']");

    // Success Message
    private final By successToast =
            By.xpath("//p[contains(text(),'Successfully')]");

    public EmailSubscriptionsPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    public EmailSubscriptionsPage openPage() {

        actions.click(adminMenu);
        actions.click(configurationMenu);
        actions.click(emailSubscriptionsOption);

        return this;
    }

    public boolean isPageDisplayed() {
        return actions.inTargetPage(driver.getCurrentUrl());
    }

    public boolean isTableDisplayed() {
        return !driver.findElements(tableRows).isEmpty();
    }

    public boolean isNotificationTypeColumnDisplayed() {
        return actions.isDisplayed(notificationTypeColumn);
    }

    public boolean isSubscribersColumnDisplayed() {
        return actions.isDisplayed(subscribersColumn);
    }

    public void clickFirstEdit() {
        actions.click(editButton);
    }

    public void clickSave() {
        actions.click(saveButton);
    }

    public boolean isSuccessMessageDisplayed() {
        return actions.isDisplayed(successToast);
    }

    public String getSuccessMessage() {
        return actions.getTextWhenVisible(successToast);
    }

    public void selectFirstRecord() {
        actions.click(firstCheckbox);
    }
}