package org.example.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class PerformancePage extends Base {

    // --- Locators ---
    private final By performanceMenu = By.xpath("//span[text()='Performance']");
    private final By manageReviewsSubMenu = By.xpath("//span[text()='Manage Reviews ']");
    private final By performanceReviewsOption = By.xpath("//a[text()='Manage Reviews']");
    // Search Filter Inputs & Dropdowns
    private final By employeeNameInput = By.xpath("//input[@placeholder='Type for hints...']");
    private final By jobTitleDropdown = By.xpath("(//div[contains(@class, 'oxd-select-text')])[1]");
    private final By statusDropdown = By.xpath("(//div[contains(@class, 'oxd-select-text')])[2]");
    private final By subUnitDropdown = By.xpath("(//div[contains(@class, 'oxd-select-text')])[3]");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By resetButton = By.xpath("//button[normalize-space()='Reset']");
    // Performance Review Grid Elements
    private final By reviewsTable = By.cssSelector(".oxd-table-body");
    private final By firstRowCheckbox = By.xpath("(//div[contains(@class, 'oxd-table-card')]//i)[1]");
    private final By selectAllCheckbox = By.xpath("(//div[contains(@class, 'oxd-table-header')]//i)[1]");
    private final By deleteSelectedBtn = By.xpath("//button[contains(.,'Delete Selected')]");
    private final By firstRowEvaluateIcon = By.xpath("(//button[normalize-space()='Evaluate' or contains(.,'Evaluate')])[1]");
    private final By columnHeaderEmployeeName = By.xpath("//div[@role='columnheader' and contains(.,'Employee')]");
    // Review Execution Actions / Modals
    private final By ratingInputFirstIndicator = By.xpath("(//input[@placeholder='0 - 100' or contains(@class, 'oxd-input')])[2]");
    private final By reviewCommentTextArea = By.tagName("textarea");
    private final By saveReviewButton = By.xpath("//button[normalize-space()='Save']");
    private final By activateReviewButton = By.xpath("//button[normalize-space()='Activate']");
    private final By approveReviewButton = By.xpath("//button[normalize-space()='Approve']");
    // Confirmation Dialog Modals
    private final By confirmationModal = By.cssSelector(".orangehrm-modal-header");
    private final By confirmDeleteBtn = By.xpath("//button[normalize-space()='Yes, Delete']");
    private final By cancelDeleteBtn = By.xpath("//button[normalize-space()='No, Cancel']");
    // Validation Messages & Feedback states
    private final By successToast = By.xpath("//p[contains(text(),'Successfully')]");
    private final By invalidRatingError = By.xpath("//span[contains(text(),'Should be between') or contains(text(),'Invalid')]");
    private final By requiredFieldError = By.xpath("//span[text()='Required']");
    private final By noRecordsFoundMsg = By.xpath("//*[contains(text(),'No Records Found')]");
    private WebDriver driver;
    private ActionsBot actions;

    public PerformancePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    // --- Action Methods ---

    public PerformancePage navigateToPerformanceReviews() {
        actions.click(performanceMenu);
        actions.click(manageReviewsSubMenu);
        actions.click(performanceReviewsOption);
        return this;
    }

    public boolean isPageOpened(String url) {
        return actions.inTargetPage(url);
    }

    public boolean isReviewsTableDisplayed() {
        try {
            sleep(2000);
            return actions.isDisplayed(reviewsTable);
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

    public void filterByJobTitle(String jobTitle) {
        actions.click(jobTitleDropdown);
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + jobTitle + "')]"));
    }

    public void filterByStatus(String status) {
        actions.click(statusDropdown);
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + status + "')]"));
    }

    public void clickSearch() {
        actions.click(searchButton);
    }

    public void clickReset() {
        actions.click(resetButton);
    }

    public void clickEvaluateFirstReview() {
        actions.click(firstRowEvaluateIcon);
    }

    public void fillReviewRating(String score) {
        actions.type(ratingInputFirstIndicator, score);
    }

    public void fillReviewComment(String comment) {
        actions.type(reviewCommentTextArea, comment);
    }

    public void clickSaveReview() {
        actions.click(saveReviewButton);
    }

    public void clickActivateReview() {
        actions.click(activateReviewButton);
    }

    public void clickApproveReview() {
        actions.click(approveReviewButton);
    }

    public void selectFirstRowCheckbox() {
        actions.click(firstRowCheckbox);
    }

    public void selectAllCheckboxes() {
        actions.click(selectAllCheckbox);
    }

    public void clickDeleteSelected() {
        actions.click(deleteSelectedBtn);
    }

    public boolean isConfirmationDialogDisplayed() {
        return actions.isDisplayed(confirmationModal);
    }

    public void confirmDelete() {
        actions.click(confirmDeleteBtn);
    }

    public void cancelDelete() {
        actions.click(cancelDeleteBtn);
    }

    public void sortByEmployeeName() {
        actions.click(columnHeaderEmployeeName);
    }

    // --- Validation Checks ---

    public boolean isSuccessMessageDisplayed() {
        return actions.isDisplayed(successToast);
    }

    public boolean isInvalidRatingErrorDisplayed() {
        return actions.isDisplayed(invalidRatingError);
    }

    public boolean isRequiredErrorDisplayed() {
        return actions.isDisplayed(requiredFieldError);
    }

    public boolean isNoRecordsFoundDisplayed() {
        return actions.isDisplayed(noRecordsFoundMsg);
    }
}