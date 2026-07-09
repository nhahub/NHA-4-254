package org.example.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class DirectoryPage extends Base {

    // --- Locators ---
    private final By directoryMenu = By.xpath("//span[text()='Directory']");
    private final By directoryHeader = By.xpath("//h5[text()='Directory']");
    // Search Filters
    private final By employeeNameInput = By.xpath("//input[@placeholder='Type for hints...']");
    private final By jobTitleDropdown = By.xpath("(//div[contains(@class, 'oxd-select-text')])[1]");
    private final By locationDropdown = By.xpath("(//div[contains(@class, 'oxd-select-text-input')])[2]");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By resetButton = By.xpath("//button[@type='reset' and normalize-space()='Reset']");
    // Directory Records Elements
    private final By directoryGrid = By.cssSelector(".orangehrm-directory-card");
    private final By recordsFoundLabel = By.cssSelector(".orangehrm-horizontal-padding .oxd-text--span");
    private final By noRecordsFoundMsg = By.xpath("//*[contains(text(),'No Records Found')]");
    // Dynamic Dropdown Options Helper
    private final By dropdownListBox = By.xpath("//div[@role='listbox']");
    private WebDriver driver;
    private ActionsBot actions;

    public DirectoryPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    // --- Action Methods ---

    public DirectoryPage navigateToDirectory() {
        actions.click(directoryMenu);
        return this;
    }

    public boolean isPageOpened(String url) {
        return actions.inTargetPage(url);
    }

    public boolean isDirectoryHeaderDisplayed() {
        return actions.isDisplayed(directoryHeader);
    }

    public boolean isDirectoryGridDisplayed() {
        try {
            sleep(1500);
            return actions.isDisplayed(directoryGrid);
        } catch (Exception e) {
            return false;
        }
    }

    public void typeEmployeeName(String employeeName) {
        actions.type(employeeNameInput, employeeName);
        try {
            sleep(1500);
        } catch (InterruptedException e) {
        }
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(), '" + employeeName + "')]"));
    }

    public void selectJobTitle(String jobTitle) {
        actions.click(jobTitleDropdown);
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + jobTitle + "')]"));
    }

    public void selectLocation(String location) {
        actions.click(locationDropdown);
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + location + "')]"));
    }

    public void clickSearch() {
        actions.click(searchButton);
    }

    public void clickReset() {
        actions.click(resetButton);
    }

    public void clickFirstDirectoryRecordCard() {
        actions.click(directoryGrid);
    }

    public String getRecordsFoundText() {
        return actions.getText(recordsFoundLabel);
    }

    // --- Validation Checks ---

    public boolean isNoRecordsFoundDisplayed() {
        return actions.isDisplayed(noRecordsFoundMsg);
    }

    public boolean isDropdownOptionsVisible() {
        return actions.isDisplayed(dropdownListBox);
    }

    public void clickJobTitleDropdownContainer() {
        actions.click(jobTitleDropdown);
    }
}