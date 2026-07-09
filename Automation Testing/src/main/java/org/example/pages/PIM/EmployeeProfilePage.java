package org.example.pages.PIM;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class EmployeeProfilePage extends Base {

    // ─── Left Sidebar Tabs ───────────────────────────────────────────────────────
    private final By personalDetailsTab =
            By.xpath("//a[contains(text(),'Personal Details')]");
    private final By contactDetailsTab =
            By.xpath("//a[contains(text(),'Contact Details')]");

    // ─── Personal Details Fields ─────────────────────────────────────────────────
    private final By firstNameInput =
            By.xpath("//input[@name='firstName']");
    private final By middleNameInput =
            By.xpath("//input[@name='middleName']");
    private final By lastNameInput =
            By.xpath("//input[@name='lastName']");
    private final By otherIdInput =
            By.xpath("(//label[text()='Other Id']/following::input)[1]");
    private final By driversLicenseInput =
            By.xpath("(//label[text()='Driver\\'s License Number']/following::input)[1]");
    private final By licenseExpiryDateInput =
            By.xpath("(//label[contains(text(),'License Expiry Date')]/following::input)[1]");
    private final By nationalityDropdown =
            By.xpath("(//label[text()='Nationality']/following::div[contains(@class,'oxd-select-text')])[1]");
    private final By maritalStatusDropdown =
            By.xpath("(//label[text()='Marital Status']/following::div[contains(@class,'oxd-select-text')])[1]");
    private final By dobInput =
            By.xpath("(//label[contains(text(),'Date of Birth')]/following::input)[1]");
    private final By genderMaleRadio =
            By.xpath("(//input[@type='radio'])[1]");
    private final By genderFemaleRadio =
            By.xpath("(//input[@type='radio'])[2]");
    private final By savePersonalDetailsButton =
            By.xpath("(//button[@type='submit'])[1]");

    // ─── Contact Details Fields ──────────────────────────────────────────────────
    private final By street1Input =
            By.xpath("(//label[text()='Street 1']/following::input)[1]");
    private final By street2Input =
            By.xpath("(//label[text()='Street 2']/following::input)[1]");
    private final By cityInput =
            By.xpath("(//label[text()='City']/following::input)[1]");
    private final By stateInput =
            By.xpath("(//label[text()='State/Province']/following::input)[1]");
    private final By zipCodeInput =
            By.xpath("(//label[text()='Zip/Postal Code']/following::input)[1]");
    private final By countryDropdown =
            By.xpath("(//label[text()='Country']/following::div[contains(@class,'oxd-select-text')])[1]");
    private final By homeTelephoneInput =
            By.xpath("(//label[text()='Home Telephone']/following::input)[1]");
    private final By mobileInput =
            By.xpath("(//label[text()='Mobile']/following::input)[1]");
    private final By workTelephoneInput =
            By.xpath("(//label[text()='Work Telephone']/following::input)[1]");
    private final By workEmailInput =
            By.xpath("(//label[text()='Work Email']/following::input)[1]");
    private final By otherEmailInput =
            By.xpath("(//label[text()='Other Email']/following::input)[1]");
    private final By saveContactDetailsButton =
            By.xpath("(//button[@type='submit'])[1]");

    // ─── Attachments Section ─────────────────────────────────────────────────────
    private final By attachmentAddButton =
            By.xpath("//div[contains(@class,'orangehrm-attachment')]//button[normalize-space()='+ Add']");
    private final By attachmentFileInput =
            By.xpath("//input[@type='file']");
    private final By attachmentCommentInput =
            By.tagName("textarea");
    private final By attachmentSaveButton =
            By.xpath("//button[@type='submit']");
    private final By attachmentRows =
            By.xpath("//div[contains(@class,'orangehrm-attachment')]//div[@role='row']");
    private final By firstAttachmentEditButton =
            By.xpath("(//div[contains(@class,'orangehrm-attachment')]//i[contains(@class,'bi-pencil-fill')])[1]");
    private final By firstAttachmentDownloadButton =
            By.xpath("(//div[contains(@class,'orangehrm-attachment')]//i[contains(@class,'bi-download')])[1]");
    private final By firstAttachmentDeleteButton =
            By.xpath("(//div[contains(@class,'orangehrm-attachment')]//i[contains(@class,'bi-trash')])[1]");
    private final By confirmDeleteBtn =
            By.xpath("//button[normalize-space()='Yes, Delete']");

    // ─── Validation ───────────────────────────────────────────────────────────────
    private final By requiredError =
            By.xpath("(//span[text()='Required'])[1]");

    // ─── Toasts / Messages ────────────────────────────────────────────────────────
    private final By successToast =
            By.xpath("//p[contains(text(),'Successfully')]");
    private final By updatedToast =
            By.xpath("//p[contains(text(),'Successfully Updated')]");
    private final By deletedToast =
            By.xpath("//p[contains(text(),'Successfully Deleted')]");

    private WebDriver driver;
    private ActionsBot actions;

    public EmployeeProfilePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    // ─── Navigation ──────────────────────────────────────────────────────────────

    public EmployeeProfilePage openPersonalDetailsTab() {
        actions.click(personalDetailsTab);
        return this;
    }

    public EmployeeProfilePage openContactDetailsTab() {
        actions.click(contactDetailsTab);
        return this;
    }

    public boolean isProfilePageLoaded() {
        try {
            sleep(2000);
            return actions.isDisplayed(firstNameInput);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ─── Personal Details ────────────────────────────────────────────────────────

    public void updatePersonalDetails(String firstName, String middleName, String lastName,
                                      String otherId, String driversLicense) {
        driver.findElement(firstNameInput).clear();
        actions.type(firstNameInput, firstName);
        driver.findElement(middleNameInput).clear();
        actions.type(middleNameInput, middleName);
        driver.findElement(lastNameInput).clear();
        actions.type(lastNameInput, lastName);
        if (otherId != null) {
            driver.findElement(otherIdInput).clear();
            actions.type(otherIdInput, otherId);
        }
        if (driversLicense != null) {
            driver.findElement(driversLicenseInput).clear();
            actions.type(driversLicenseInput, driversLicense);
        }
    }

    public void selectNationality(String nationality) {
        actions.click(nationalityDropdown);
        actions.click(By.xpath("//div[@role='option']//span[text()='" + nationality + "']"));
    }

    public void selectMaritalStatus(String status) {
        actions.click(maritalStatusDropdown);
        actions.click(By.xpath("//div[@role='option']//span[text()='" + status + "']"));
    }

    public void selectGenderMale() {
        actions.click(genderMaleRadio);
    }

    public void selectGenderFemale() {
        actions.click(genderFemaleRadio);
    }

    public void savePersonalDetails() {
        actions.click(savePersonalDetailsButton);
    }

    // ─── Contact Details ─────────────────────────────────────────────────────────

    public void updateContactDetails(String street1, String street2, String city,
                                     String zipCode, String homePhone,
                                     String mobile, String workPhone,
                                     String workEmail, String otherEmail) {
        actions.type(street1Input, street1);
        actions.type(street2Input, street2);
        actions.type(cityInput, city);
        actions.type(zipCodeInput, zipCode);
        actions.type(homeTelephoneInput, homePhone);
        actions.type(mobileInput, mobile);
        actions.type(workTelephoneInput, workPhone);
        actions.type(workEmailInput, workEmail);
        actions.type(otherEmailInput, otherEmail);
    }

    public void selectCountry(String country) {
        actions.click(countryDropdown);
        actions.click(By.xpath("//div[@role='option']//span[text()='" + country + "']"));
    }

    public void saveContactDetails() {
        actions.click(saveContactDetailsButton);
    }

    // ─── Attachments ─────────────────────────────────────────────────────────────

    public void clickAddAttachment() {
        actions.click(attachmentAddButton);
    }

    public void uploadAttachment(String filePath, String comment) {
        clickAddAttachment();
        driver.findElement(attachmentFileInput).sendKeys(filePath);
        actions.type(attachmentCommentInput, comment);
        actions.click(attachmentSaveButton);
    }

    public void attemptSaveWithoutFile(String comment) {
        clickAddAttachment();
        actions.type(attachmentCommentInput, comment);
        actions.click(attachmentSaveButton);
    }

    public void editFirstAttachment(String newFilePath, String newComment) {
        actions.click(firstAttachmentEditButton);
        if (newFilePath != null) {
            driver.findElement(attachmentFileInput).sendKeys(newFilePath);
        }
        driver.findElement(attachmentCommentInput).clear();
        actions.type(attachmentCommentInput, newComment);
        actions.click(attachmentSaveButton);
    }

    public void downloadFirstAttachment() {
        actions.click(firstAttachmentDownloadButton);
    }

    public void deleteFirstAttachment() {
        actions.click(firstAttachmentDeleteButton);
        actions.click(confirmDeleteBtn);
    }

    public boolean hasAttachmentRows() {
        try {
            return !driver.findElements(attachmentRows).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    // ─── Validation ───────────────────────────────────────────────────────────────

    public boolean isRequiredErrorDisplayed() {
        return actions.isDisplayed(requiredError);
    }

    // ─── Toasts / Messages ────────────────────────────────────────────────────────

    public boolean isSuccessMessageDisplayed() {
        return actions.isDisplayed(successToast);
    }

    public boolean isUpdatedMessageDisplayed() {
        return actions.isDisplayed(updatedToast);
    }

    public boolean isDeletedMessageDisplayed() {
        return actions.isDisplayed(deletedToast);
    }

    public String getSuccessMessage() {
        return actions.getTextWhenVisible(successToast);
    }
}