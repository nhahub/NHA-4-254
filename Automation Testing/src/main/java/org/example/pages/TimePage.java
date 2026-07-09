package org.example.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class TimePage extends Base {

    // Global Navigation & Headers
    private final By timeMenu = By.xpath("//span[text()='Time']");
    private final By timesheetsSubMenu = By.xpath("//span[text()='Timesheets ']");

    // --- Locators ---
    private final By myTimesheetsOption = By.xpath("//a[text()='My Timesheets']");
    private final By employeeTimesheetsOption = By.xpath("//a[text()='Employee Timesheets']");
    private final By timesheetHeaderPeriod = By.cssSelector(".orangehrm-timesheet-header-text");
    private final By timesheetTable = By.cssSelector(".orangehrm-timesheet-table");
    private final By timesheetStatusLabel = By.cssSelector(".orangehrm-timesheet-footer-status");
    // Form Inputs & Controls
    private final By employeeNameSearchInput = By.xpath("//input[@placeholder='Type for hints...']");
    private final By viewSearchButton = By.xpath("//button[@type='submit']");
    private final By editTimesheetButton = By.xpath("//button[normalize-space()='Edit']");
    private final By addRowButton = By.xpath("//button[normalize-space()='Add Row']");
    private final By saveTimesheetButton = By.xpath("//button[normalize-space()='Save']");
    private final By submitTimesheetButton = By.xpath("//button[normalize-space()='Submit']");
    // Dynamic Row Elements (Assuming first row indexes for actions)
    private final By projectDropdownFirstRow = By.xpath("(//div[contains(@class, 'oxd-select-text')])[1]");
    private final By firstProjectOption = By.xpath("//div[@role='listbox']//div[@role='option'][2]");
    private final By activityDropdownFirstRow = By.xpath("(//div[contains(@class, 'oxd-select-text')])[2]");
    private final By firstActivityOption = By.xpath("//div[@role='listbox']//div[@role='option'][2]");
    private final By hourInputFirstRowFirstDay = By.xpath("(//input[contains(@class, 'oxd-input')])[1]");
    // Validation Feedback Elements
    private final By projectRequiredError = By.xpath("//span[text()='Project Required' or text()='Required']");
    private final By activityRequiredError = By.xpath("//span[text()='Activity Required' or text()='Required']");
    private final By invalidHoursError = By.xpath("//span[contains(text(),'Should be a number') or contains(text(),'Invalid')]");
    private final By outOfBoundsHoursError = By.xpath("//span[contains(text(),'Hours should be between')]");
    private final By generalValidationAlert = By.xpath("//span[contains(@class,'oxd-input-field-error-msg')]");
    private final By successToast = By.xpath("//p[contains(text(),'Successfully')]");
    // Row Management & Modals
    private final By deleteRowIconFirst = By.xpath("(//i[contains(@class, 'bi-trash')])[1]");
    private final By clearRowIconFirst = By.xpath("(//i[contains(@class, 'bi-eraser')])[1]");
    private final By confirmDeleteBtn = By.xpath("//button[normalize-space()='Yes, Delete']");
    private final By cancelDeleteBtn = By.xpath("//button[normalize-space()='No, Cancel']");
    private final By deleteSelectedBtn = By.xpath("//button[contains(.,'Delete Selected')]");
    private final By firstRowCheckbox = By.xpath("(//div[contains(@class, 'oxd-checkbox-wrapper')])[2]");
    private final By noRecordsFoundMsg = By.xpath("//*[contains(text(),'No Records Found')]");
    // Calendar / Filter Fields
    private final By dateFromFilterInput = By.xpath("(//input[@placeholder='yyyy-dd-mm' or placeholder='yyyy-mm-dd'])[1]");
    private final By cancelEditBtn = By.xpath("//button[normalize-space()='Cancel']");
    private final By pageHeader = By.cssSelector(".oxd-topbar-header-title");
    private final By adminMenuLink = By.xpath("//span[text()='Admin']");
    private final By navigationWarningDialog = By.cssSelector(".orangehrm-modal-header"); // Adjust class if custom popup elements differ
    private final By confirmationModal = By.cssSelector(".orangehrm-modal-header");
    private WebDriver driver;
    private ActionsBot actions;

    public TimePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    // --- Action Methods & Fluent Interface Flows ---

    public boolean isPageOpened(String url) {
        return actions.inTargetPage(url);
    }

    public TimePage navigateToEmployeeTimesheets() {
        actions.click(timeMenu);
        actions.click(timesheetsSubMenu);
        actions.click(employeeTimesheetsOption);
        return this;
    }

    public TimePage navigateToMyTimesheets() {
        actions.click(timeMenu);
        actions.click(timesheetsSubMenu);
        actions.click(myTimesheetsOption);
        return this;
    }

    public boolean isTimesheetPeriodDisplayed() {
        return actions.isDisplayed(timesheetHeaderPeriod);
    }

    public boolean isTimesheetTableDisplayed() {
        try {
            sleep(2000);
            return actions.isDisplayed(timesheetTable);
        } catch (Exception e) {
            return false;
        }
    }

    public String getTimesheetStatusText() {
        return actions.getText(timesheetStatusLabel);
    }

    public void searchEmployeeTimesheet(String employeeName) {
        actions.type(employeeNameSearchInput, employeeName);
        try {
            sleep(1500);
        } catch (InterruptedException e) {
        } // Wait for auto-complete hint list
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + employeeName + "')]"));
        actions.click(viewSearchButton);
    }

    public void filterTimesheetByDate(String dateStr) {
        actions.type(dateFromFilterInput, dateStr);
        actions.click(viewSearchButton);
    }

    public void clickEditTimesheet() {
        actions.click(editTimesheetButton);
    }

    public void clickAddRow() {
        actions.click(addRowButton);
    }

    public void fillFirstRowDetails(String hours) {
        actions.click(projectDropdownFirstRow);
        actions.click(firstProjectOption);
        actions.click(activityDropdownFirstRow);
        actions.click(firstActivityOption);
        actions.type(hourInputFirstRowFirstDay, hours);
    }

    public void clearHoursInput() {
        // Simple element native action handling for direct clean-up
        driver.findElement(hourInputFirstRowFirstDay).clear();
    }

    public void typeHoursOnly(String hours) {
        actions.type(hourInputFirstRowFirstDay, hours);
    }

    public void fillRowWithoutProject(String hours) {
        actions.click(activityDropdownFirstRow);
        actions.click(firstActivityOption);
        actions.type(hourInputFirstRowFirstDay, hours);
    }

    public void fillRowWithoutActivity(String hours) {
        actions.click(projectDropdownFirstRow);
        actions.click(firstProjectOption);
        actions.type(hourInputFirstRowFirstDay, hours);
    }

    public void clickSaveTimesheet() {
        actions.click(saveTimesheetButton);
    }

    public void clickSubmitTimesheet() {
        actions.click(submitTimesheetButton);
    }

    public void deleteFirstRow() {
        actions.click(deleteRowIconFirst);
    }

    public void clearFirstRow() {
        actions.click(clearRowIconFirst);
    }

    public void confirmDeleteRow() {
        actions.click(confirmDeleteBtn);
    }

    public void cancelDeleteRow() {
        actions.click(cancelDeleteBtn);
    }

    public void selectFirstRowCheckbox() {
        actions.click(firstRowCheckbox);
    }

    public void clickDeleteSelected() {
        actions.click(deleteSelectedBtn);
    }

    // --- Validation Checks ---

    public boolean isSuccessMessageDisplayed() {
        return actions.isDisplayed(successToast);
    }

    public boolean isProjectRequiredErrorDisplayed() {
        return actions.isDisplayed(projectRequiredError);
    }

    public boolean isActivityRequiredErrorDisplayed() {
        return actions.isDisplayed(activityRequiredError);
    }

    public boolean isInvalidHoursErrorDisplayed() {
        return actions.isDisplayed(invalidHoursError) || actions.isDisplayed(generalValidationAlert);
    }

    public boolean isOutOfBoundsHoursErrorDisplayed() {
        return actions.isDisplayed(outOfBoundsHoursError) || actions.isDisplayed(generalValidationAlert);
    }

    public boolean isNoRecordsFoundDisplayed() {
        return actions.isDisplayed(noRecordsFoundMsg);
    }

    public void clickCancelEdit() {
        actions.click(cancelEditBtn);
    }

    public void attemptNavigationAway() {
        actions.click(By.xpath("//span[text()='Admin']")); // Click another menu item to trigger warning
    }

    public String getPageHeaderTitle() {
        return actions.getText(pageHeader);
    }

    public void navigateToAdminModule() {
        actions.click(adminMenuLink);
    }

    public boolean isNavigationWarningDisplayed() {
        return actions.isDisplayed(navigationWarningDialog) || actions.isDisplayed(By.xpath("//*[contains(text(),'Unsaved Changes')]"));
    }

    public boolean isConfirmationDialogDisplayed() {
        return actions.isDisplayed(confirmationModal);
    }
}