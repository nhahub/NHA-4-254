package org.example.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class ClaimPage extends Base {

    // --- Locators ---
    private final By claimMenu = By.xpath("//span[text()='Claim']");
    private final By claimHeader = By.xpath("//h5[text()='Claim Claims'] or //h6[text()='Claim']");
    // Search Filters
    private final By employeeNameInput = By.xpath("//input[@placeholder='Type for hints...']");
    private final By referenceIdInput = By.xpath("(//input[contains(@class, 'oxd-input')])[2]");
    private final By eventDropdown = By.xpath("(//div[contains(@class, 'oxd-select-text')])[1]");
    private final By statusDropdown = By.xpath("(//div[contains(@class, 'oxd-select-text')])[2]");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By resetButton = By.xpath("//button[normalize-space()='Reset']");
    // Grid Elements
    private final By claimsTableBody = By.cssSelector(".oxd-table-body");
    private final By columnHeaderReferenceId = By.xpath("//div[@role='columnheader' and contains(.,'Reference ID')]");
    private final By viewDetailsBtn = By.xpath("(//button[normalize-space()='View Details' or contains(.,'View')])[1]");
    private final By noRecordsFoundMsg = By.xpath("//*[contains(text(),'No Records Found')]");
    // Action Buttons & Modals
    private final By submitClaimBtn = By.xpath("//button[normalize-space()='Submit']");
    private final By backButton = By.xpath("//button[normalize-space()='Back']");
    private final By dropdownListBox = By.xpath("//div[@role='listbox']");
    private final By fieldValidationError = By.xpath("//span[text()='Required']");
    private final By toastSuccessNotification = By.xpath("//p[contains(text(),'Successfully')]");
    private WebDriver driver;
    private ActionsBot actions;

    public ClaimPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    // --- Action Methods ---

    public ClaimPage navigateToClaimModule() {
        actions.click(claimMenu);
        return this;
    }

    public boolean isPageOpened(String url) {
        return actions.inTargetPage(url);
    }

    public boolean isClaimHeaderDisplayed() {
        return actions.isDisplayed(claimHeader);
    }

    public boolean isClaimsTableDisplayed() {
        try {
            sleep(1500);
            return actions.isDisplayed(claimsTableBody);
        } catch (Exception e) {
            return false;
        }
    }

    public void filterByEmployeeName(String employeeName) {
        actions.type(employeeNameInput, employeeName);
        try {
            sleep(1500);
        } catch (InterruptedException e) {
        }
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + employeeName + "')]"));
    }

    public void filterByReferenceId(String refId) {
        actions.type(referenceIdInput, refId);
    }

    public void filterByEvent(String eventName) {
        actions.click(eventDropdown);
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + eventName + "')]"));
    }

    public void filterByStatus(String statusValue) {
        actions.click(statusDropdown);
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + statusValue + "')]"));
    }

    public void clickSearch() {
        actions.click(searchButton);
    }

    public void clickReset() {
        actions.click(resetButton);
    }

    public void clickFirstRowViewDetails() {
        actions.click(viewDetailsBtn);
    }

    public void clickSubmitClaim() {
        actions.click(submitClaimBtn);
    }

    public void clickBackButton() {
        actions.click(backButton);
    }

    public void clickEventDropdownContainer() {
        actions.click(eventDropdown);
    }

    public String getReferenceIdInputValue() {
        return driver.findElement(referenceIdInput).getAttribute("value");
    }

    // --- Validation Methods ---

    public boolean isSuccessNotificationDisplayed() {
        return actions.isDisplayed(toastSuccessNotification);
    }

    public boolean isRequiredFieldErrorDisplayed() {
        return actions.isDisplayed(fieldValidationError);
    }

    public boolean isNoRecordsFoundDisplayed() {
        return actions.isDisplayed(noRecordsFoundMsg);
    }

    public boolean isDropdownListBoxPopulated() {
        return actions.isDisplayed(dropdownListBox);
    }

    public void sortByReferenceId() {
        actions.click(columnHeaderReferenceId);
    }
}