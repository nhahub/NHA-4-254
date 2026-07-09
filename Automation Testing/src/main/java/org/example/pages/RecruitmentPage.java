package org.example.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class RecruitmentPage extends Base {

    // Top-Level / Navigation Elements
    private final By recruitmentMenu = By.xpath("//span[text()='Recruitment']");
    private final By candidatesSubMenu = By.xpath("//a[text()='Candidates']");

    // --- Locators ---
    private final By candidatesTable = By.cssSelector(".oxd-table-body");
    private final By candidateTableHeader = By.cssSelector(".oxd-table-header");
    private final By addCandidateButton = By.xpath("//button[normalize-space()='Add']");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By resetButton = By.xpath("//button[@type='button' and normalize-space()='Reset']");
    // Filtering Dropdowns & Inputs
    private final By jobTitleDropdown = By.xpath("(//div[contains(@class, 'oxd-select-text')])[1]");
    private final By vacancyDropdown = By.xpath("(//div[contains(@class, 'oxd-select-text')])[2]");
    private final By hiringManagerDropdown = By.xpath("(//div[contains(@class, 'oxd-select-text')])[3]");
    private final By statusDropdown = By.xpath("(//div[contains(@class, 'oxd-select-text')])[4]");
    private final By candidateNameAutocompleteInput = By.xpath("//input[@placeholder='Type for hints...']");
    private final By keywordsInput = By.xpath("//input[@placeholder='Enter comma seperated words...']");
    private final By dateOfApplicationFromInput = By.xpath("(//input[@placeholder='yyyy-dd-mm' or @placeholder='yyyy-mm-dd'])[1]");
    private final By methodOfApplicationDropdown = By.xpath("(//div[contains(@class, 'oxd-select-text')])[5]");
    // Form Fields inside Add / Edit Candidate
    private final By firstNameInput = By.name("firstName");
    private final By middleNameInput = By.name("middleName");
    private final By lastNameInput = By.name("lastName");
    private final By emailInput = By.xpath("(//input[@placeholder='Type here'])[1]");
    private final By contactNumberInput = By.xpath("(//input[@placeholder='Type here'])[2]");
    private final By resumeUploadInput = By.xpath("//input[@type='file']");
    private final By saveButton = By.xpath("//button[@type='submit']");
    private final By commentTextArea = By.tagName("textarea");
    private final By consentCheckbox = By.cssSelector(".oxd-checkbox-wrapper i");
    // Row specific actions / Dynamic Content selectors
    private final By viewDetailsIconFirstRow = By.xpath("(//i[contains(@class, 'bi-eye-fill')])[1]");
    private final By deleteIconFirstRow = By.xpath("(//i[contains(@class, 'bi-trash')])[1]");
    private final By tableRows = By.cssSelector(".oxd-table-card");
    private final By selectAllCheckbox = By.xpath("(//div[contains(@class, 'oxd-table-header')]//i)[1]");
    private final By firstRowCheckbox = By.xpath("(//div[contains(@class, 'oxd-table-card')]//i)[1]");
    private final By deleteSelectedBtn = By.xpath("//button[contains(.,'Delete Selected')]");
    // Sorting Elements
    private final By candidateNameHeaderSort = By.xpath("//div[@role='columnheader' and contains(.,'Candidate')]//i");
    // Modal Confirmation Dialog elements
    private final By confirmationModal = By.cssSelector(".orangehrm-modal-header");
    private final By confirmDeleteBtn = By.xpath("//button[normalize-space()='Yes, Delete']");
    private final By cancelDeleteBtn = By.xpath("//button[normalize-space()='No, Cancel']");
    // Status Transitions Workflow
    private final By shortlistButton = By.xpath("//button[normalize-space()='Shortlist']");
    private final By statusBadge = By.cssSelector(".orangehrm-recruitment-statusText");
    // Validation Prompts / Alert Toasts
    private final By successToast = By.xpath("//p[contains(text(),'Successfully')]");
    private final By requiredFieldError = By.xpath("//span[text()='Required']");
    private final By invalidEmailError = By.xpath("//span[text()='Expected format is name@domain.com']");
    private final By noRecordsFoundMsg = By.xpath("//*[contains(text(),'No Records Found')]");
    private WebDriver driver;
    private ActionsBot actions;

    public RecruitmentPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    // --- Action Methods ---

    public boolean isPageOpened(String url) {
        return actions.inTargetPage(url);
    }

    public RecruitmentPage navigateToCandidates() {
        actions.click(recruitmentMenu);
        actions.click(candidatesSubMenu);
        return this;
    }

    public boolean isCandidatesTableDisplayed() {
        try {
            sleep(2000);
            return actions.isDisplayed(candidatesTable);
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAddCandidate() {
        actions.click(addCandidateButton);
    }

    public void fillCandidateDetails(String fName, String mName, String lName, String email) {
        actions.type(firstNameInput, fName);
        actions.type(middleNameInput, mName);
        actions.type(lastNameInput, lName);
        actions.type(emailInput, email);
    }

    public void typeContactNumber(String contactNum) {
        actions.type(contactNumberInput, contactNum);
    }

    public void fillCandidateWithoutRequired() {
        actions.type(firstNameInput, "");
        actions.type(lastNameInput, "");
    }

    public void typeInvalidEmail(String badEmail) {
        actions.type(emailInput, badEmail);
    }

    public void clickSave() {
        actions.click(saveButton);
    }

    public void clickReset() {
        actions.click(resetButton);
    }

    public void selectJobTitleFilter(String text) {
        actions.click(jobTitleDropdown);
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + text + "')]"));
    }

    public void selectStatusFilter(String statusName) {
        actions.click(statusDropdown);
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + statusName + "')]"));
    }

    public void typeCandidateNameSearch(String name) {
        actions.type(candidateNameAutocompleteInput, name);
        try {
            sleep(1500);
        } catch (InterruptedException e) {
        }
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + name + "')]"));
    }

    public void clickSearch() {
        actions.click(searchButton);
    }

    public void sortByCandidateName() {
        actions.click(candidateNameHeaderSort);
        actions.click(By.xpath("//span[contains(text(),'Ascending')]"));
    }

    public void clickViewFirstCandidateDetails() {
        actions.click(viewDetailsIconFirstRow);
    }

    public void clickDeleteFirstCandidate() {
        actions.click(deleteIconFirstRow);
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

    public void selectFirstRowCheckbox() {
        actions.click(firstRowCheckbox);
    }

    public void selectAllCheckboxes() {
        actions.click(selectAllCheckbox);
    }

    public void clickDeleteSelected() {
        actions.click(deleteSelectedBtn);
    }

    public void clickShortlistCandidate() {
        actions.click(shortlistButton);
    }

    public String getCandidateStatusText() {
        return actions.getText(statusBadge);
    }

    // --- Validation Checks ---

    public boolean isSuccessMessageDisplayed() {
        return actions.isDisplayed(successToast);
    }

    public boolean isRequiredErrorDisplayed() {
        return actions.isDisplayed(requiredFieldError);
    }

    public boolean isInvalidEmailErrorDisplayed() {
        return actions.isDisplayed(invalidEmailError);
    }

    public boolean isNoRecordsFoundDisplayed() {
        return actions.isDisplayed(noRecordsFoundMsg);
    }
}