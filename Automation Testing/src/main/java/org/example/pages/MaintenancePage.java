package org.example.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class MaintenancePage extends Base {

    // --- Locators ---
    private final By maintenanceMenu = By.xpath("//span[text()='Maintenance']");
    private final By purgeRecordsSubMenu = By.xpath("//span[contains(text(),'Purge Records')]");
    private final By purgeEmployeeOption = By.xpath("//a[text()='Purge Employee']");
    // Administrator Access Verification Barrier
    private final By adminPasswordInput = By.name("password");
    private final By adminVerifyBtn = By.xpath("//button[@type='submit']");
    // Main UI Input Fields & Actions
    private final By employeeSearchInput = By.xpath("//input[@placeholder='Type for hints...']");
    private final By purgeButton = By.xpath("//button[normalize-space()='Purge']");
    private final By resetButton = By.xpath("//button[normalize-space()='Reset']");
    // Modal Context Elements
    private final By confirmPurgeModal = By.cssSelector(".orangehrm-modal-header");
    private final By confirmPurgeYesBtn = By.xpath("//button[normalize-space()='Yes, Purge']");
    private final By cancelPurgeNoBtn = By.xpath("//button[normalize-space()='No, Cancel']");
    // Interface State Markers
    private final By sectionHeaderTitle = By.xpath("//h6[contains(@class,'oxd-text') and (text()='Purge Employee' or text()='Purge Records')]");
    private final By toastSuccessNotification = By.xpath("//p[contains(text(),'Successfully')]");
    private final By fieldValidationRequiredError = By.xpath("//span[text()='Required']");
    private final By hintsDropdownListBox = By.xpath("//div[@role='listbox']");
    private WebDriver driver;
    private ActionsBot actions;

    public MaintenancePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    // --- Actions ---

    public MaintenancePage navigateToPurgeEmployee() {
        actions.click(maintenanceMenu);
        // Bypass or open submenu if present before target click
        try {
            actions.click(purgeRecordsSubMenu);
        } catch (Exception ignored) {
        }
        actions.click(purgeEmployeeOption);
        return this;
    }

    public void authenticateAdminSession(String password) {
        if (actions.isDisplayed(adminPasswordInput)) {
            actions.type(adminPasswordInput, password);
            actions.click(adminVerifyBtn);
        }
    }

    public boolean isPageOpened(String url) {
        return actions.inTargetPage(url);
    }

    public boolean isMaintenanceHeaderDisplayed() {
        return actions.isDisplayed(sectionHeaderTitle);
    }

    public void typeEmployeeQueryHint(String partialName) {
        actions.type(employeeSearchInput, partialName);
    }

    public void selectEmployeeFromDropdownHints(String fullEmployeeName) {
        actions.type(employeeSearchInput, fullEmployeeName);
        try {
            sleep(1500);
        } catch (InterruptedException e) {
        }
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + fullEmployeeName + "')]"));
    }

    public void clickPurge() {
        actions.click(purgeButton);
    }

    public void clickReset() {
        actions.click(resetButton);
    }

    public void confirmPurgeAction() {
        actions.click(confirmPurgeYesBtn);
    }

    public void cancelPurgeAction() {
        actions.click(cancelPurgeNoBtn);
    }

    public String getEmployeeSearchInputValue() {
        return driver.findElement(employeeSearchInput).getAttribute("value");
    }

    // --- Validation states ---

    public boolean isSuccessNotificationDisplayed() {
        return actions.isDisplayed(toastSuccessNotification);
    }

    public boolean isRequiredFieldErrorDisplayed() {
        return actions.isDisplayed(fieldValidationRequiredError);
    }

    public boolean isPurgeModalConfirmationVisible() {
        return actions.isDisplayed(confirmPurgeModal);
    }

    public boolean isDropdownListPopulated() {
        return actions.isDisplayed(hintsDropdownListBox);
    }
}