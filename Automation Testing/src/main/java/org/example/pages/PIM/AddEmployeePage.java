package org.example.pages.PIM;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class AddEmployeePage extends Base {

    // ─── Navigation ──────────────────────────────────────────────────────────────
    private final By addEmployeeTab =
            By.xpath("//a[text()='Add Employee']");

    // ─── Page Header ─────────────────────────────────────────────────────────────
    private final By pageHeader =
            By.xpath("//h6[contains(@class,'oxd-text--h6')]");

    // ─── Basic Fields ────────────────────────────────────────────────────────────
    private final By firstNameInput =
            By.xpath("//input[@name='firstName']");
    private final By middleNameInput =
            By.xpath("//input[@name='middleName']");
    private final By lastNameInput =
            By.xpath("//input[@name='lastName']");
    private final By employeeIdInput =
            By.xpath("(//label[text()='Employee Id']/following::input)[1]");
    private final By profileImageInput =
            By.xpath("//input[@type='file']");

    // ─── Create Login Details ────────────────────────────────────────────────────
    private final By createLoginToggle =
            By.xpath("(//span[contains(@class,'oxd-switch-input')])[1]");
    private final By usernameInput =
            By.xpath("(//label[text()='Username']/following::input)[1]");
    private final By passwordInput =
            By.xpath("(//label[text()='Password']/following::input[@type='password'])[1]");
    private final By confirmPasswordInput =
            By.xpath("(//label[text()='Confirm Password']/following::input[@type='password'])[1]");
    private final By statusEnabledRadio =
            By.xpath("(//input[@type='radio'])[1]");
    private final By statusDisabledRadio =
            By.xpath("(//input[@type='radio'])[2]");

    // ─── Buttons ──────────────────────────────────────────────────────────────────
    private final By saveButton =
            By.xpath("//button[@type='submit']");
    private final By cancelButton =
            By.xpath("//button[normalize-space()='Cancel']");

    // ─── Validation ───────────────────────────────────────────────────────────────
    private final By requiredErrors =
            By.xpath("//span[text()='Required']");
    private final By requiredError =
            By.xpath("(//span[text()='Required'])[1]");
    private final By passwordMismatchError =
            By.xpath("//span[contains(text(),'Passwords do not match')]");
    private final By usernameLengthError =
            By.xpath("//span[contains(text(),'Should have at least')]");
    private final By weakPasswordError =
            By.xpath("//span[contains(text(),'weak') or contains(text(),'Weak')]");
    private final By duplicateUsernameError =
            By.xpath("//span[contains(text(),'already exists') or contains(text(),'Already exists')]");

    // ─── Toasts / Messages ────────────────────────────────────────────────────────
    private final By successToast =
            By.xpath("//p[contains(text(),'Successfully')]");

    private WebDriver driver;
    private ActionsBot actions;

    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    // ─── Navigation ──────────────────────────────────────────────────────────────

    public AddEmployeePage openAddEmployeeTab() {
        actions.click(addEmployeeTab);
        return this;
    }

    public boolean isPageLoaded() {
        try {
            sleep(2000);
            return actions.isDisplayed(firstNameInput) && actions.isDisplayed(lastNameInput);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ─── Basic Info ──────────────────────────────────────────────────────────────

    public void fillBasicInfo(String firstName, String middleName, String lastName, String employeeId) {
        if (firstName != null) actions.type(firstNameInput, firstName);
        if (middleName != null) actions.type(middleNameInput, middleName);
        if (lastName != null) actions.type(lastNameInput, lastName);
        if (employeeId != null) {
            driver.findElement(employeeIdInput).clear();
            actions.type(employeeIdInput, employeeId);
        }
    }

    public void clearEmployeeId() {
        driver.findElement(employeeIdInput).clear();
    }

    // ─── Save / Cancel ────────────────────────────────────────────────────────────

    public void clickSave() {
        actions.click(saveButton);
    }

    public void clickCancel() {
        actions.click(cancelButton);
    }

    public void saveWithoutData() {
        clickSave();
    }

    public void addEmployeeBasic(String firstName, String middleName, String lastName, String employeeId) {
        fillBasicInfo(firstName, middleName, lastName, employeeId);
        clickSave();
    }

    public void addEmployeeWithEmptyId(String firstName, String lastName) {
        actions.type(firstNameInput, firstName);
        actions.type(lastNameInput, lastName);
        clearEmployeeId();
        clickSave();
    }

    public void addEmployeeWithSpecialChars(String firstName, String lastName) {
        actions.type(firstNameInput, firstName);
        actions.type(lastNameInput, lastName);
        clickSave();
    }

    public void addEmployeeWithSpacedNames(String firstName, String lastName) {
        actions.type(firstNameInput, firstName);
        actions.type(lastNameInput, lastName);
        clickSave();
    }

    public void addEmployeeWithLongFirstName(String firstName, String lastName) {
        actions.type(firstNameInput, firstName);
        actions.type(lastNameInput, lastName);
        clickSave();
    }

    // ─── Create Login Details ────────────────────────────────────────────────────

    public void toggleCreateLoginDetails() {
        actions.click(createLoginToggle);
    }

    public boolean areLoginFieldsDisplayed() {
        toggleCreateLoginDetails();
        return actions.isDisplayed(usernameInput) && actions.isDisplayed(passwordInput);
    }

    public void selectStatusEnabled() {
        actions.click(statusEnabledRadio);
    }

    public void selectStatusDisabled() {
        actions.click(statusDisabledRadio);
    }

    public void fillLoginDetails(String username, String password, String confirmPassword) {
        actions.type(usernameInput, username);
        actions.type(passwordInput, password);
        actions.type(confirmPasswordInput, confirmPassword);
    }

    public void addEmployeeWithLogin(String firstName, String lastName, String employeeId,
                                     String username, String password, boolean enabled) {
        fillBasicInfo(firstName, null, lastName, employeeId);
        toggleCreateLoginDetails();
        if (enabled) {
            selectStatusEnabled();
        } else {
            selectStatusDisabled();
        }
        fillLoginDetails(username, password, password);
        clickSave();
    }

    public void saveWithBlankLoginFields(String firstName, String lastName, String employeeId) {
        fillBasicInfo(firstName, null, lastName, employeeId);
        toggleCreateLoginDetails();
        clickSave();
    }

    public void saveWithShortUsername(String username, String password) {
        toggleCreateLoginDetails();
        actions.type(usernameInput, username);
        actions.type(passwordInput, password);
        actions.type(confirmPasswordInput, password);
        clickSave();
    }

    public void saveWithWeakPassword(String username, String weakPassword) {
        toggleCreateLoginDetails();
        actions.type(usernameInput, username);
        actions.type(passwordInput, weakPassword);
        actions.type(confirmPasswordInput, weakPassword);
        clickSave();
    }

    public void saveWithMismatchedPasswords(String username, String password, String confirmPassword) {
        toggleCreateLoginDetails();
        actions.type(usernameInput, username);
        actions.type(passwordInput, password);
        actions.type(confirmPasswordInput, confirmPassword);
        clickSave();
    }

    public void saveWithDuplicateUsername(String duplicateUsername, String password) {
        toggleCreateLoginDetails();
        actions.type(usernameInput, duplicateUsername);
        actions.type(passwordInput, password);
        actions.type(confirmPasswordInput, password);
        clickSave();
    }

    public void doubleClickSave() {
        actions.click(saveButton);
        actions.click(saveButton);
    }

    // ─── Validation ───────────────────────────────────────────────────────────────

    public boolean isRequiredErrorDisplayed() {
        return actions.isDisplayed(requiredError);
    }

    public boolean areMultipleRequiredErrorsDisplayed() {
        try {
            return driver.findElements(requiredErrors).size() > 1;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPasswordMismatchErrorDisplayed() {
        return actions.isDisplayed(passwordMismatchError);
    }

    public boolean isUsernameLengthErrorDisplayed() {
        return actions.isDisplayed(usernameLengthError);
    }

    public boolean isWeakPasswordErrorDisplayed() {
        return actions.isDisplayed(weakPasswordError);
    }

    public boolean isDuplicateUsernameErrorDisplayed() {
        return actions.isDisplayed(duplicateUsernameError);
    }

    // ─── Toasts / Messages ────────────────────────────────────────────────────────

    public boolean isSuccessMessageDisplayed() {
        return actions.isDisplayed(successToast);
    }

    public String getSuccessMessage() {
        return actions.getTextWhenVisible(successToast);
    }

    // ─── File Upload ──────────────────────────────────────────────────────────────

    public void uploadProfileImage(String filePath) {
        driver.findElement(profileImageInput).sendKeys(filePath);
    }
}