package org.example.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyInfoPage extends Base {

    // --- Locators ---
    private final By myInfoMenu = By.xpath("//span[text()='My Info']");
    private final By personalDetailsHeader = By.xpath("//h6[text()='Personal Details']");
    // Form Inputs
    private final By firstNameInput = By.name("firstName");
    private final By middleNameInput = By.name("middleName");
    private final By lastNameInput = By.name("lastName");
    private final By employeeIdInput = By.xpath("(//input[contains(@class, 'oxd-input')])[2]");
    private final By otherIdInput = By.xpath("(//input[contains(@class, 'oxd-input')])[3]");
    private final By driverLicenseInput = By.xpath("(//input[contains(@class, 'oxd-input')])[4]");
    private final By licenseExpiryDateInput = By.xpath("//label[text()=\"License Expiry Date\"]/parent::div/following-sibling::div//input");
    private final By ssnNumberInput = By.xpath("(//input[contains(@class, 'oxd-input')])[5]");
    private final By sinNumberInput = By.xpath("(//input[contains(@class, 'oxd-input')])[6]");
    // Dropdowns & Radio Buttons
    private final By nationalityDropdown = By.xpath("(//div[contains(@class, 'oxd-select-text')])[1]");
    private final By maritalStatusDropdown = By.xpath("(//div[contains(@class, 'oxd-select-text')])[2]");
    private final By dateOfBirthInput = By.xpath("//label[text()=\"Date of Birth\"]/parent::div/following-sibling::div//input");
    private final By genderMaleRadio = By.xpath("//label[text()='Male']/span");
    private final By genderFemaleRadio = By.xpath("//label[text()='Female']/span");
    private final By militaryServiceInput = By.xpath("(//input[contains(@class, 'oxd-input')])[7]");
    private final By smokerCheckbox = By.xpath("//label[text()='Smoker']/span/i");
    // Action Buttons
    private final By savePersonalDetailsBtn = By.xpath("(//button[@type='submit'])[1]");
    private final By saveCustomFieldsBtn = By.xpath("(//button[@type='submit'])[2]");
    private final By profileAttachmentBtn = By.xpath("//button[normalize-space()='Add']");
    // Side Menu Sub-modules
    private final By contactDetailsLink = By.xpath("//a[text()='Contact Details']");
    private final By emergencyContactsLink = By.xpath("//a[text()='Emergency Contacts']");
    private final By dependentsLink = By.xpath("//a[text()='Dependents']");
    private final By immigrationLink = By.xpath("//a[text()='Immigration']");
    private final By qualificationsLink = By.xpath("//a[text()='Qualifications']");
    private final By membershipsLink = By.xpath("//a[text()='Memberships']");
    // Validations & Toast Indicators
    private final By successToast = By.xpath("//p[contains(text(),'Successfully')]");
    private final By requiredFieldError = By.xpath("//span[text()='Required']");
    private WebDriver driver;
    private ActionsBot actions;

    public MyInfoPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    // --- Actions ---

    public MyInfoPage navigateToMyInfo() {
        actions.click(myInfoMenu);
        return this;
    }

    public boolean isPageOpened(String url) {
        return actions.inTargetPage(url);
    }

    public boolean isPersonalDetailsHeaderDisplayed() {
        return actions.isDisplayed(personalDetailsHeader);
    }

    public void updateNameDetails(String first, String middle, String last) {
        actions.type(firstNameInput, first);
        actions.type(middleNameInput, middle);
        actions.type(lastNameInput, last);
    }

    public void clearFirstName() {
        driver.findElement(firstNameInput).clear();
    }

    public void updateIdentificationDetails(String employeeId, String otherId, String licenseNum) {
        actions.type(employeeIdInput, employeeId);
        actions.type(otherIdInput, otherId);
        actions.type(driverLicenseInput, licenseNum);
    }

    public void updateLicenseExpiry(String expiryDate) {
        actions.type(licenseExpiryDateInput, expiryDate);
    }

    public void updateTaxIdentifiers(String ssn, String sin) {
        actions.type(ssnNumberInput, ssn);
        actions.type(sinNumberInput, sin);
    }

    public void selectNationality(String country) {
        actions.click(nationalityDropdown);
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + country + "')]"));
    }

    public void selectMaritalStatus(String status) {
        actions.click(maritalStatusDropdown);
        actions.click(By.xpath("//div[@role='listbox']//*[contains(text(),'" + status + "')]"));
    }

    public void updateDateOfBirth(String dob) {
        actions.type(dateOfBirthInput, dob);
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            actions.click(genderMaleRadio);
        } else {
            actions.click(genderFemaleRadio);
        }
    }

    public void updateMilitaryService(String status) {
        actions.type(militaryServiceInput, status);
    }

    public void toggleSmokerStatus() {
        actions.click(smokerCheckbox);
    }

    public void clickSavePersonalDetails() {
        actions.click(savePersonalDetailsBtn);
    }

    // --- Side Menu Navigations ---
    public void navigateToContactDetails() {
        actions.click(contactDetailsLink);
    }

    public void navigateToEmergencyContacts() {
        actions.click(emergencyContactsLink);
    }

    public void navigateToDependents() {
        actions.click(dependentsLink);
    }

    public void navigateToImmigration() {
        actions.click(immigrationLink);
    }

    public void navigateToQualifications() {
        actions.click(qualificationsLink);
    }

    public void navigateToMemberships() {
        actions.click(membershipsLink);
    }

    // --- Validations ---
    public boolean isSuccessMessageDisplayed() {
        return actions.isDisplayed(successToast);
    }

    public boolean isRequiredErrorDisplayed() {
        return actions.isDisplayed(requiredFieldError);
    }
}