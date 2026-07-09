package org.example.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class LeavePage extends Base {

    // --- Locators ---
    private final By leaveMenu = By.xpath("//span[text()='Leave']");
    private final By applyLeaveLink = By.xpath("//a[text()='Apply']");
    private final By myLeaveLink = By.xpath("//a[text()='My Leave']");
    // Form Inputs & Calendars
    private final By leaveTypeDropdown = By.xpath("//label[text()='Leave Type']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text')]");
    private final By fromDateInput = By.xpath("//label[text()='From Date']/parent::div/following-sibling::div//input");
    private final By toDateInput = By.xpath("//label[text()='To Date']/parent::div/following-sibling::div//input");
    private final By partialDaysDropdown = By.xpath("//label[text()='Partial Days']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text')]");
    private final By durationDropdown = By.xpath("//label[text()='Duration']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text')]");
    private final By commentTextArea = By.xpath("//label[text()='Comment']/parent::div/following-sibling::div//textarea");
    // Status Checkboxes / Grid Filters
    private final By statusPendingCheckbox = By.xpath("//label[text()='Pending Approval']/span/i");
    private final By statusCancelledCheckbox = By.xpath("//label[text()='Cancelled']/span/i");
    private final By statusRejectedCheckbox = By.xpath("//label[text()='Rejected']/span/i");
    private final By showLeaveRegretCheckbox = By.xpath("//label[contains(.,'Show Leave Regret')]/span/i");
    // Action Triggers
    private final By applySubmitBtn = By.xpath("//button[@type='submit']");
    private final By searchBtn = By.xpath("//button[@type='submit' and normalize-space()='Search']");
    private final By resetBtn = By.xpath("//button[normalize-space()='Reset']");
    private final By actionMoreMenuBtn = By.xpath("(//div[@class='oxd-table-cell-actions']//button)[1]");
    private final By cancelLeaveBtn = By.xpath("//button[normalize-space()='Cancel']");
    // Grid Details
    private final By leaveBalanceText = By.xpath("//p[contains(@class,'orangehrm-leave-balance')]");
    private final By leaveGridTable = By.className("oxd-table-body");
    private final By firstRowLeaveTypeCell = By.xpath("(//div[@role='row'])[2]/div[@role='cell'][3]");
    private final By confirmationDialog = By.cssSelector(".orangehrm-modal-header");
    private final By fieldRequiredSpan = By.xpath("//span[text()='Required']");
    private final By noRecordsFoundLabel = By.xpath("//*[contains(text(),'No Records Found')]");
    // --- Additional Locators for Batch 2 ---
    private final By nextPaginationButton = By.xpath("//button[contains(@class,'oxd-pagination-page-item') and .//i[contains(@class,'chevron-right')]]");
    private final By lastPaginationButton = By.xpath("//button[contains(@class,'oxd-pagination-page-item') and text()='Last']");
    private final By firstRowExpandDetailCardBtn = By.xpath("(//div[@class='oxd-table-card']//button[contains(@class,'oxd-icon-button')])[1]");

    // --- Action Methods ---
    private final By detailCardExpandPanel = By.xpath("//div[contains(@class,'orangehrm-leave-detail-panel')]");
    private final By detailedDurationValue = By.xpath("//div[contains(@class,'orangehrm-leave-detail-panel')]//p[contains(text(),'Day') or contains(text(),'Hour')]");
    // Entitlements Sub-Menu Components
    private final By entitlementsSubMenuToggle = By.xpath("//span[text()='Entitlements ']");
    private final By addEntitlementsOption = By.xpath("//a[text()='Add Entitlements']");
    private final By employeeEntitlementSearchInput = By.xpath("//input[@placeholder='Type for hints...']");
    private final By entitlementPeriodDropdown = By.xpath("//label[text()='Leave Period']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text')]");
    private final By entitlementDaysInput = By.xpath("//label[text()='Entitlement']/parent::div/following-sibling::div//input");
    private final By saveEntitlementBtn = By.xpath("//button[@type='submit' and normalize-space()='Save']");
    private final By checkboxMultipleEmployees = By.xpath("//label[text()='Multiple Employees']/span/i");
    private final By locationDropdownWidget = By.xpath("//label[text()='Location']/parent::div/following-sibling::div//div[contains(@class,'oxd-select-text')]");
    // Dialog and State Confirmations
    private final By genericConfirmModalActionBtn = By.xpath("//button[contains(@class,'oxd-button--label-danger') or normalize-space()='Confirm']");
    private final By genericCancelModalActionBtn = By.xpath("//button[normalize-space()='Cancel']");
    private final By invalidFieldAlertMessage = By.xpath("//span[contains(@class,'oxd-input-field-error-message')]");
    // --- Additional Locators for Batch 3 ---
    private final By entitlementsMyEntitlementsOption = By.xpath("//a[text()='My Entitlements']");
    private final By reportsDropdownMenuToggle = By.xpath("//span[text()='Reports ']");
    private final By leaveEntitlementsAndUsageReportOption = By.xpath("//a[text()='Leave Entitlements and Usage Report']");
    // Configuration & Management Components
    private final By configureSubMenuToggle = By.xpath("//span[text()='Configure ']");
    private final By leavePeriodOption = By.xpath("//a[text()='Leave Period']");
    // Report Generation Inputs
    private final By reportGenerateTypeDropdown = By.xpath("(//div[contains(@class, 'oxd-select-text')])[1]");
    private final By reportEmployeeNameInput = By.xpath("//input[@placeholder='Type for hints...']");
    private final By reportLeaveTypeDropdown = By.xpath("(//div[contains(@class, 'oxd-select-text')])[2]");
    private final By reportPeriodDropdown = By.xpath("(//div[contains(@class, 'oxd-select-text')])[3]");
    private final By generateReportBtn = By.xpath("//button[@type='submit' and normalize-space()='Generate']");
    // Summary Grids & Breakdown Panels
    private final By reportDataGridTable = By.className("orangehrm-paper-container");
    private final By detailedBreakdownViewBtn = By.xpath("(//div[@class='oxd-table-body']//button[contains(@class,'oxd-icon-button')])[1]");
    private final By detailBreakdownModalContainer = By.cssSelector(".orangehrm-modal-header, .oxd-dialog-container");
    private final By leavePeriodStartMonthSelect = By.xpath("(//div[contains(@class, 'oxd-select-text')])[1]");
    private final By leavePeriodStartDaySelect = By.xpath("(//div[contains(@class, 'oxd-select-text')])[2]");
    private final By saveLeavePeriodBtn = By.xpath("//button[@type='submit' and normalize-space()='Save']");
    // --- Add to Locators section ---
    private final By toastSuccessNotification = By.xpath("//p[contains(text(),'Successfully') or contains(@class, 'toast-message')]");
    // --- Additional Locators for Batch 4 ---
    private final By configureLeaveTypesOption = By.xpath("//a[text()='Leave Types']");
    private final By configureWorkWeekOption = By.xpath("//a[text()='Work Week']");
    private final By configureHolidaysOption = By.xpath("//a[text()='Holidays']");
    // Leave Type Actions
    private final By addLeaveTypeBtn = By.xpath("//button[normalize-space()='Add']");
    private final By leaveTypeNameInput = By.xpath("//label[text()='Name']/parent::div/following-sibling::div//input");
    private final By saveLeaveTypeBtn = By.xpath("//button[@type='submit' and normalize-space()='Save']");
    private final By deleteRecordRowBtn = By.xpath("(//i[contains(@class, 'oxd-icon bi-trash')])[1]");
    private final By editRecordRowBtn = By.xpath("(//i[contains(@class, 'oxd-icon bi-pencil-fill')])[1]");
    // Work Week Grid Selectors
    private final By mondayWorkWeekDropdown = By.xpath("//label[text()='Monday']/parent::div/following-sibling::div//div[contains(@class, 'oxd-select-text')]");
    private final By tuesdayWorkWeekDropdown = By.xpath("//label[text()='Tuesday']/parent::div/following-sibling::div//div[contains(@class, 'oxd-select-text')]");

    // --- Additional Action Methods for Batch 2 ---
    private final By saveWorkWeekBtn = By.xpath("//button[@type='submit' and normalize-space()='Save']");
    // Holidays Setup Selectors
    private final By addHolidayBtn = By.xpath("//button[normalize-space()='Add']");
    private final By holidayNameInput = By.xpath("//label[text()='Name']/parent::div/following-sibling::div//input");
    private final By holidayDateInput = By.xpath("//label[text()='Date']/parent::div/following-sibling::div//input");
    private final By saveHolidayBtn = By.xpath("//button[@type='submit' and normalize-space()='Save']");
    // --- Additional Locators for the Last Batch (Batch 5) ---
    private final By configureHolidaysFilterYear = By.xpath("//div[contains(@class, 'oxd-select-text')]");
    private final By leaveListTopLevelMenuOption = By.xpath("//a[text()='Leave List']");
    private final By assignLeaveTopLevelMenuOption = By.xpath("//a[text()='Assign Leave']");
    // Holiday Inline Actions
    private final By firstHolidayRowEditIcon = By.xpath("(//div[@class='oxd-table-body']//i[contains(@class, 'bi-pencil-fill')])[1]");
    private final By firstHolidayRowDeleteIcon = By.xpath("(//div[@class='oxd-table-body']//i[contains(@class, 'bi-trash')])[1]");
    // Leave List Grid Filter & Action Panel
    private final By leaveListEmployeeNameInput = By.xpath("//input[@placeholder='Type for hints...']");
    private final By leaveListFromDateInput = By.xpath("(//input[@placeholder='yyyy-dd-mm' or contains(@class, 'oxd-input')])[1]");
    private final By leaveListToDateInput = By.xpath("(//input[@placeholder='yyyy-dd-mm' or contains(@class, 'oxd-input')])[2]");
    private final By leaveListSearchBtn = By.xpath("//button[@type='submit' and normalize-space()='Search']");
    private final By leaveListResetBtn = By.xpath("//button[@type='reset' and normalize-space()='Reset']");
    // Workflow Action Selectors (Approve/Reject)
    private final By firstRowActionDropdown = By.xpath("(//div[@class='oxd-table-body']//div[contains(@class, 'oxd-select-text')])[1]");
    private final By inlineSaveActionBtn = By.xpath("(//div[@class='oxd-table-body']//button[normalize-space()='Save'])[1]");
    private final By firstRowDetailedViewBtn = By.xpath("(//div[@class='oxd-table-body']//button[contains(@class, 'oxd-icon-button')])[1]");
    private final By globalBulkCheckbox = By.xpath("(//div[@class='oxd-table-header']//input[@type='checkbox']/following-sibling::span)[1]");
    // Assign Leave Screen Selectors
    private final By assignEmployeeInput = By.xpath("//input[@placeholder='Type for hints...']");
    private final By assignLeaveTypeDropdown = By.xpath("//label[text()='Leave Type']/parent::div/following-sibling::div//div[contains(@class, 'oxd-select-text')]");
    private final By assignFromDateInput = By.xpath("//label[text()='From Date']/parent::div/following-sibling::div//input");
    private final By assignToDateInput = By.xpath("//label[text()='To Date']/parent::div/following-sibling::div//input");
    private final By assignPartialDaysDropdown = By.xpath("//label[text()='Partial Days']/parent::div/following-sibling::div//div[contains(@class, 'oxd-select-text')]");
    private final By assignCommentsTextArea = By.xpath("//textarea");
    private final By assignSubmitBtn = By.xpath("//button[@type='submit' and normalize-space()='Assign']");
    private final By assignConfirmModalBtn = By.xpath("//button[contains(@class, 'orangehrm-left-space') or normalize-space()='Ok']");
    private WebDriver driver;
    private ActionsBot actions;

    public LeavePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    // --- Additional Action Methods for Batch 3 ---

    public LeavePage navigateToLeaveModule() {
        actions.click(leaveMenu);
        return this;
    }

    public void clickApplyLeaveTab() {
        actions.click(applyLeaveLink);
    }

    public void clickMyLeaveTab() {
        actions.click(myLeaveLink);
    }

    public boolean isPageOpened(String url) {
        return actions.inTargetPage(url);
    }

    public void selectLeaveType(String leaveType) {
        actions.click(leaveTypeDropdown);
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + leaveType + "')]"));
    }

    public void setLeaveDates(String fromDate, String toDate) {
        actions.type(fromDateInput, fromDate);
        actions.type(toDateInput, toDate);
    }

    public void fillComment(String text) {
        actions.type(commentTextArea, text);
    }

    public void clickApplySubmit() {
        actions.click(applySubmitBtn);
    }

    public void clickSearch() {
        actions.click(searchBtn);
    }

    public void clickReset() {
        actions.click(resetBtn);
    }

    public String getLeaveBalanceAmount() {
        return actions.getText(leaveBalanceText);
    }

    public boolean isLeaveGridTableVisible() {
        try {
            sleep(1000);
        } catch (Exception e) {
        }
        return actions.isDisplayed(leaveGridTable);
    }

    public String getFirstRowLeaveType() {
        return actions.getText(firstRowLeaveTypeCell);
    }

    public void togglePendingCheckbox() {
        actions.click(statusPendingCheckbox);
    }

    public void toggleCancelledCheckbox() {
        actions.click(statusCancelledCheckbox);
    }

    public void toggleRejectedCheckbox() {
        actions.click(statusRejectedCheckbox);
    }

    public void toggleShowLeaveRegret() {
        actions.click(showLeaveRegretCheckbox);
    }

    public void cancelFirstPendingLeave() {
        actions.click(actionMoreMenuBtn);
        actions.click(cancelLeaveBtn);
    }

    public void clearDateFields() {
        driver.findElement(fromDateInput).clear();
        driver.findElement(toDateInput).clear();
    }

    public boolean isPartialDaysDropdownDisplayed() {
        return actions.isDisplayed(partialDaysDropdown);
    }

    public boolean isDurationDropdownDisplayed() {
        return actions.isDisplayed(durationDropdown);
    }

    public boolean isRequiredErrorVisible() {
        return actions.isDisplayed(fieldRequiredSpan);
    }

    public boolean isNoRecordsFoundMessageVisible() {
        return actions.isDisplayed(noRecordsFoundLabel);
    }

    public boolean isConfirmationDialogVisible() {
        return actions.isDisplayed(confirmationDialog);
    }

    public void navigateToAddEntitlements() {
        actions.click(entitlementsSubMenuToggle);
        actions.click(addEntitlementsOption);
    }

    public void clickNextPage() {
        actions.click(nextPaginationButton);
    }

    public void clickLastPage() {
        actions.click(lastPaginationButton);
    }

    public void expandFirstRowDetails() {
        actions.click(firstRowExpandDetailCardBtn);
    }

    // --- Additional Action Methods for Batch 4 ---

    public boolean isDetailExpansionPanelVisible() {
        return actions.isDisplayed(detailCardExpandPanel);
    }

    public String getDetailedDurationText() {
        return actions.getText(detailedDurationValue);
    }

    public void assignEntitlementDays(String employeeName, String period, String days) {
        actions.type(employeeEntitlementSearchInput, employeeName);
        try {
            sleep(1500);
        } catch (InterruptedException e) {
        }
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + employeeName + "')]"));

        actions.click(entitlementPeriodDropdown);
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + period + "')]"));

        actions.type(entitlementDaysInput, days);
    }

    public void enableMultipleEmployeesFilter() {
        actions.click(checkboxMultipleEmployees);
    }

    public void filterEntitlementByLocation(String locationName) {
        actions.click(locationDropdownWidget);
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + locationName + "')]"));
    }

    public void clearEntitlementFormFields() {
        driver.findElement(entitlementDaysInput).clear();
    }

    public void confirmModalPrompt() {
        actions.click(genericConfirmModalActionBtn);
    }

    public void cancelModalPrompt() {
        actions.click(genericCancelModalActionBtn);
    }

    public boolean isInvalidFieldValidationErrorDisplayed() {
        return actions.isDisplayed(invalidFieldAlertMessage);
    }

    public String getEntitlementDaysInputValue() {
        return driver.findElement(entitlementDaysInput).getAttribute("value");
    }

    public void navigateToMyEntitlements() {
        actions.click(entitlementsSubMenuToggle);
        actions.click(entitlementsMyEntitlementsOption);
    }

    public void navigateToLeaveUsageReport() {
        actions.click(reportsDropdownMenuToggle);
        actions.click(leaveEntitlementsAndUsageReportOption);
    }

    public void navigateToLeavePeriodConfiguration() {
        actions.click(configureSubMenuToggle);
        actions.click(leavePeriodOption);
    }

    public void selectReportGenerationType(String typeLabel) {
        actions.click(reportGenerateTypeDropdown);
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + typeLabel + "')]"));
    }

    public void configureReportFilters(String employeeName, String leaveType, String period) {
        if (employeeName != null && !employeeName.isEmpty()) {
            actions.type(reportEmployeeNameInput, employeeName);
            try {
                sleep(1500);
            } catch (InterruptedException e) {
            }
            actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + employeeName + "')]"));
        }
        if (leaveType != null && !leaveType.isEmpty()) {
            actions.click(reportLeaveTypeDropdown);
            actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + leaveType + "')]"));
        }
        if (period != null && !period.isEmpty()) {
            actions.click(reportPeriodDropdown);
            actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + period + "')]"));
        }
    }

    public void clickGenerateReport() {
        actions.click(generateReportBtn);
    }

    public boolean isReportDataGridVisible() {
        try {
            sleep(1000);
        } catch (Exception e) {
        }
        return actions.isDisplayed(reportDataGridTable);
    }

    public void openFirstRecordDetailedBreakdown() {
        actions.click(detailedBreakdownViewBtn);
    }

    public boolean isBreakdownModalVisible() {
        return actions.isDisplayed(detailBreakdownModalContainer);
    }

    public void saveLeavePeriodSettings(String startMonth, String startDay) {
        actions.click(leavePeriodStartMonthSelect);
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + startMonth + "')]"));
        actions.click(leavePeriodStartDaySelect);
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + startDay + "')]"));
        actions.click(saveLeavePeriodBtn);
    }

    public void accessDirectUrl(String targetUrl) {
        driver.get(targetUrl);
    }

    // --- Add to Validation Methods section ---
    public boolean isSuccessNotificationDisplayed() {
        try {
            return actions.isDisplayed(toastSuccessNotification);
        } catch (Exception e) {
            return false;
        }
    }

    public void navigateToLeaveTypesConfiguration() {
        actions.click(configureSubMenuToggle);
        actions.click(configureLeaveTypesOption);
    }

    public void navigateToWorkWeekConfiguration() {
        actions.click(configureSubMenuToggle);
        actions.click(configureWorkWeekOption);
    }

    public void navigateToHolidaysConfiguration() {
        actions.click(configureSubMenuToggle);
        actions.click(configureHolidaysOption);
    }

    public void createNewLeaveType(String name) {
        actions.click(addLeaveTypeBtn);
        actions.type(leaveTypeNameInput, name);
        actions.click(saveLeaveTypeBtn);
    }

    public void clickEditFirstRowRecord() {
        actions.click(editRecordRowBtn);
    }

    public void modifyLeaveTypeName(String updatedName) {
        driver.findElement(leaveTypeNameInput).clear();
        actions.type(leaveTypeNameInput, updatedName);
        actions.click(saveLeaveTypeBtn);
    }

    public void clickDeleteFirstRowRecord() {
        actions.click(deleteRecordRowBtn);
    }

    public void updateMondaySchedule(String statusOption) {
        actions.click(mondayWorkWeekDropdown);
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + statusOption + "')]"));
    }

    public void updateTuesdaySchedule(String statusOption) {
        actions.click(tuesdayWorkWeekDropdown);
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + statusOption + "')]"));
    }

    public void clickSaveWorkWeekConfiguration() {
        actions.click(saveWorkWeekBtn);
    }

    public void createNewHoliday(String name, String dateValue) {
        actions.click(addHolidayBtn);
        actions.type(holidayNameInput, name);
        actions.type(holidayDateInput, dateValue);
        actions.click(saveHolidayBtn);
    }

    public String getLeaveTypeNameInputValue() {
        return driver.findElement(leaveTypeNameInput).getAttribute("value");
    }

    // --- Additional Action Methods for the Last Batch (Batch 5) ---

    public void navigateToLeaveList() {
        actions.click(leaveListTopLevelMenuOption);
    }

    public void navigateToAssignLeave() {
        actions.click(assignLeaveTopLevelMenuOption);
    }

    public void clickEditFirstHoliday() {
        actions.click(firstHolidayRowEditIcon);
    }

    public void clickDeleteFirstHoliday() {
        actions.click(firstHolidayRowDeleteIcon);
    }

    public void filterHolidaysByYear(String year) {
        actions.click(configureHolidaysFilterYear);
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + year + "')]"));
    }

    public void applyLeaveListFilters(String employeeName, String fromDate, String toDate) {
        if (employeeName != null && !employeeName.isEmpty()) {
            actions.type(leaveListEmployeeNameInput, employeeName);
            try {
                sleep(1500);
            } catch (InterruptedException e) {
            }
            actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + employeeName + "')]"));
        }
        if (fromDate != null && !fromDate.isEmpty()) {
            actions.type(leaveListFromDateInput, fromDate);
        }
        if (toDate != null && !toDate.isEmpty()) {
            actions.type(leaveListToDateInput, toDate);
        }
        actions.click(leaveListSearchBtn);
    }

    public void clickLeaveListReset() {
        actions.click(leaveListResetBtn);
    }

    public void openFirstRequestDetails() {
        actions.click(firstRowDetailedViewBtn);
    }

    public void updateFirstRowRequestStatus(String statusAction) {
        actions.click(firstRowActionDropdown);
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + statusAction + "')]"));
        actions.click(inlineSaveActionBtn);
    }

    public void toggleBulkSelectAll() {
        actions.click(globalBulkCheckbox);
    }

    public void executeLeaveAssignment(String employeeName, String leaveType, String fromDate, String toDate, String comment) {
        actions.type(assignEmployeeInput, employeeName);
        try {
            sleep(1500);
        } catch (InterruptedException e) {
        }
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + employeeName + "')]"));

        actions.click(assignLeaveTypeDropdown);
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + leaveType + "')]"));

        actions.type(assignFromDateInput, fromDate);
        actions.type(assignToDateInput, toDate);

        if (comment != null) {
            actions.type(assignCommentsTextArea, comment);
        }
        actions.click(assignSubmitBtn);
    }

    public void confirmAssignmentOverloadPrompt() {
        try {
            if (actions.isDisplayed(assignConfirmModalBtn)) {
                actions.click(assignConfirmModalBtn);
            }
        } catch (Exception e) {
            // Context prompt didn't fire due to clean limits balance
        }
    }
}