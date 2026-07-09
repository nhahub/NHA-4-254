package org.example.pages.PIM;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class EmployeeListPage extends Base {

    // ─── Navigation ──────────────────────────────────────────────────────────────
    private final By pimMenu = By.xpath("//span[text()='PIM']");

    // ─── Page Header ─────────────────────────────────────────────────────────────
    private final By pageHeader =
            By.xpath("//h6[contains(@class,'oxd-topbar-header-breadcrumb-module')]");

    // ─── Top Navigation Tabs ─────────────────────────────────────────────────────
    private final By employeeListTab =
            By.xpath("//a[text()='Employee List']");
    private final By addEmployeeTab =
            By.xpath("//a[text()='Add Employee']");
    private final By reportsTab =
            By.xpath("//a[text()='Reports']");
    private final By configurationTab =
            By.xpath("//a[text()='Configuration']");

    // ─── Configuration Menu Items ────────────────────────────────────────────────
    private final By optionalFieldsOption =
            By.xpath("//a[text()='Optional Fields']");
    private final By customFieldsOption =
            By.xpath("//a[text()='Custom Fields']");
    private final By dataImportOption =
            By.xpath("//a[text()='Data Import']");
    private final By reportingMethodsOption =
            By.xpath("//a[text()='Reporting Methods']");
    private final By terminationReasonsOption =
            By.xpath("//a[text()='Termination Reasons']");

    // ─── User Dropdown Menu ──────────────────────────────────────────────────────
    private final By userDropdown =
            By.cssSelector(".oxd-userdropdown-tab");
    private final By aboutMenuItem =
            By.xpath("//a[text()='About']");
    private final By supportMenuItem =
            By.xpath("//a[text()='Support']");
    private final By changePasswordMenuItem =
            By.xpath("//a[text()='Change Password']");
    private final By logoutMenuItem =
            By.xpath("//a[text()='Logout']");

    // ─── Search / Filter Section ─────────────────────────────────────────────────
    private final By searchSection =
            By.xpath("//form[contains(@class,'oxd-form')]");
    private final By employeeNameInput =
            By.xpath("(//label[text()='Employee Name']/following::input)[1]");
    private final By employeeIdInput =
            By.xpath("(//label[text()='Employee Id']/following::input)[1]");
    private final By jobTitleDropdown =
            By.xpath("(//label[text()='Job Title']/following::div[contains(@class,'oxd-select-text')])[1]");
    private final By employmentStatusDropdown =
            By.xpath("(//label[text()='Employment Status']/following::div[contains(@class,'oxd-select-text')])[1]");
    private final By subUnitDropdown =
            By.xpath("(//label[text()='Sub Unit']/following::div[contains(@class,'oxd-select-text')])[1]");
    private final By supervisorNameInput =
            By.xpath("(//label[text()='Supervisor Name']/following::input)[1]");
    private final By includeDropdown =
            By.xpath("(//label[text()='Include']/following::div[contains(@class,'oxd-select-text')])[1]");
    private final By searchButton =
            By.xpath("//button[normalize-space()='Search']");
    private final By resetButton =
            By.xpath("//button[normalize-space()='Reset']");

    // ─── Table ───────────────────────────────────────────────────────────────────
    private final By employeeTable =
            By.cssSelector(".oxd-table");
    private final By employeeRows =
            By.cssSelector(".oxd-table-body .oxd-table-row");
    private final By tableColumnHeaders =
            By.xpath("//div[@role='columnheader']");
    private final By firstEmployeeNameLink =
            By.xpath("(//div[@role='row'])[2]//div[contains(@class,'oxd-table-cell')]//a");
    private final By firstEditButton =
            By.xpath("(//div[@role='row'])[2]//i[contains(@class,'bi-pencil-fill')]");
    private final By firstCheckbox =
            By.xpath("(//div[@role='row'])[2]//input[@type='checkbox']");

    // ─── Action Buttons ──────────────────────────────────────────────────────────
    private final By addButton =
            By.xpath("//button[normalize-space()='Add']");
    private final By deleteSelectedButton =
            By.xpath("//button[contains(.,'Delete Selected')]");
    private final By confirmDeleteBtn =
            By.xpath("//button[normalize-space()='Yes, Delete']");

    // ─── Messages ────────────────────────────────────────────────────────────────
    private final By noRecordsFound =
            By.xpath("//*[contains(text(),'No Records Found')]");
    private final By successToast =
            By.xpath("//p[contains(text(),'Successfully')]");
    private final By loadingSpinner =
            By.cssSelector(".oxd-loading-spinner");

    private WebDriver driver;
    private ActionsBot actions;

    public EmployeeListPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    // ─── Navigation ──────────────────────────────────────────────────────────────

    public EmployeeListPage openEmployeeListPage() {
        actions.click(pimMenu);
        return this;
    }

    public boolean pageOpened(String url) {
        return actions.inTargetPage(url);
    }

    // ─── Page Header / Menu ──────────────────────────────────────────────────────

    public boolean isPageHeaderDisplayed() {
        return actions.isDisplayed(pageHeader);
    }

    public boolean isNavigationMenuComplete() {
        actions.click(userDropdown);
        return actions.isDisplayed(aboutMenuItem)
                && actions.isDisplayed(supportMenuItem)
                && actions.isDisplayed(changePasswordMenuItem)
                && actions.isDisplayed(logoutMenuItem);
    }

    public boolean isConfigurationMenuComplete() {
        actions.click(configurationTab);
        return actions.isDisplayed(optionalFieldsOption)
                && actions.isDisplayed(customFieldsOption)
                && actions.isDisplayed(dataImportOption)
                && actions.isDisplayed(reportingMethodsOption)
                && actions.isDisplayed(terminationReasonsOption);
    }

    // ─── Search Section ───────────────────────────────────────────────────────────

    public boolean isSearchSectionDisplayed() {
        return actions.isDisplayed(searchSection);
    }

    public boolean areAllSearchFieldsDisplayed() {
        return actions.isDisplayed(employeeNameInput)
                && actions.isDisplayed(employeeIdInput)
                && actions.isDisplayed(jobTitleDropdown)
                && actions.isDisplayed(employmentStatusDropdown)
                && actions.isDisplayed(subUnitDropdown)
                && actions.isDisplayed(supervisorNameInput);
    }

    public void searchByEmployeeName(String name) {
        actions.type(employeeNameInput, name);
        actions.click(searchButton);
    }

    public void searchByEmployeeId(String id) {
        actions.type(employeeIdInput, id);
        actions.click(searchButton);
    }

    public void searchBySupervisorName(String supervisorName) {
        actions.type(supervisorNameInput, supervisorName);
        actions.click(searchButton);
    }

    public void searchByDropdownValue(By dropdown, String value) {
        actions.click(dropdown);
        By option = By.xpath("//div[@role='option']//span[text()='" + value + "']");
        actions.click(option);
        actions.click(searchButton);
    }

    public void searchByEmploymentStatus(String status) {
        searchByDropdownValue(employmentStatusDropdown, status);
    }

    public void searchByJobTitle(String jobTitle) {
        searchByDropdownValue(jobTitleDropdown, jobTitle);
    }

    public void searchBySubUnit(String subUnit) {
        searchByDropdownValue(subUnitDropdown, subUnit);
    }

    public void searchByInclude(String includeOption) {
        searchByDropdownValue(includeDropdown, includeOption);
    }

    public void fillAllSearchFields(String name, String id, String supervisor) {
        actions.type(employeeNameInput, name);
        actions.type(employeeIdInput, id);
        actions.type(supervisorNameInput, supervisor);
    }

    public void clickSearch() {
        actions.click(searchButton);
    }

    public void clickReset() {
        actions.click(resetButton);
    }

    // ─── Table ───────────────────────────────────────────────────────────────────

    public boolean isEmployeeTableDisplayed() {
        try {
            sleep(2000);
            return actions.isDisplayed(employeeTable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean hasEmployeeRows() {
        try {
            sleep(2000);
            return !driver.findElements(employeeRows).isEmpty();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean areTableColumnsCorrect() {
        try {
            int headerCount = driver.findElements(tableColumnHeaders).size();
            return headerCount >= 6; // ID, First Name, Last Name, Job Title, Status, Sub Unit, Supervisor
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNoRecordsFoundDisplayed() {
        return actions.isDisplayed(noRecordsFound);
    }

    public void clickFirstEmployeeName() {
        actions.click(firstEmployeeNameLink);
    }

    public void clickFirstEditButton() {
        actions.click(firstEditButton);
    }

    public void selectFirstEmployeeCheckbox() {
        actions.click(firstCheckbox);
    }

    // ─── Action Buttons ──────────────────────────────────────────────────────────

    public boolean areAddDeleteButtonsDisplayed() {
        return actions.isDisplayed(addButton);
    }

    public void clickAddButton() {
        actions.click(addButton);
    }

    public void deleteSelectedEmployees() {
        actions.click(deleteSelectedButton);
        actions.click(confirmDeleteBtn);
    }

    public void deleteFirstEmployee() {
        selectFirstEmployeeCheckbox();
        deleteSelectedEmployees();
    }

    // ─── Loading / Messages ───────────────────────────────────────────────────────

    public boolean isLoadingIndicatorDisplayed() {
        return actions.isDisplayed(loadingSpinner);
    }

    public boolean isSuccessMessageDisplayed() {
        return actions.isDisplayed(successToast);
    }

    public String getSuccessMessage() {
        return actions.getTextWhenVisible(successToast);
    }
}