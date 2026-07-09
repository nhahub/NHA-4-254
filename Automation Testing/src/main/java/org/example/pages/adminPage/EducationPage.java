//package org.example.pages.adminPage;
//
//import bots.ActionsBot;
//import org.example.Base;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//
//public class EducationPage extends Base {
//
//    private final By adminMenu =
//            By.xpath("//span[text()='Admin']");
//    private final By qualificationsMenu =
//            By.xpath("//span[text()='Qualifications ']");
//    private final By educationOption =
//            By.xpath("//a[text()='Education']");
//
//
//    // Navigation
//    private final By educationRows =
//            By.cssSelector(".oxd-table-body .oxd-table-row");
//    private final By educationColumn =
//            By.xpath("(//div[@role='columnheader'])[2]");
//    private final By addButton =
//            By.xpath("//button[normalize-space()='Add']");
//
//    // Table
//    private final By levelInput =
//            By.xpath("(//input[contains(@class,'oxd-input')])[2]");
//    private final By saveButton =
//            By.xpath("//button[@type='submit']");
//
//    // Add
//    private final By editButton =
//            By.cssSelector(".bi-pencil-fill:nth-of-type(1)");
//    private final By deleteIcon =
//            By.xpath("(//i[contains(@class,'bi-trash')])[1]");
//    private final By confirmDeleteBtn =
//            By.xpath("//button[normalize-space()='Yes, Delete']");
//
//    // Edit
//    private final By cancelDeleteBtn =
//            By.xpath("//button[normalize-space()='No, Cancel']");
//
//    // Delete
//    private final By deleteSelectedBtn =
//            By.xpath("//button[contains(.,'Delete Selected')]");
//    private final By requiredError =
//            By.xpath("//span[text()='Required']");
//    private final By duplicateError =
//            By.xpath("//span[contains(text(),'Already exists')]");
//    private final By successToast =
//            By.xpath("//p[contains(text(),'Successfully')]");
//
//    // Validation
//    private final By noRecordsFound =
//            By.xpath("//*[contains(text(),'No Records Found')]");
//    private final By firstCheckbox =
//            By.xpath("(//input[@type='checkbox'])[2]");
//    private WebDriver driver;
//
//    // Empty State
//    private ActionsBot actions;
//
//    // Checkboxes
//
//    public EducationPage(WebDriver driver) {
//        this.driver = driver;
//        this.actions = new ActionsBot(driver);
//    }
//
//    // ===================================================
//    // Navigation
//    // ===================================================
//
//    public EducationPage openEducationPage() {
//        actions.click(adminMenu);
//        actions.click(qualificationsMenu);
//        actions.click(educationOption);
//
//        return this;
//    }
//
//    public boolean pageOpened(String url) {
//        return actions.inTargetPage(url);
//    }
//
//    // ===================================================
//    // Verification
//    // ===================================================
//
//    public boolean isEducationTableDisplayed() {
//        return !driver.findElements(educationRows).isEmpty();
//    }
//
//    public boolean isEducationColumnDisplayed() {
//        return actions.isDisplayed(educationColumn);
//    }
//
//    // ===================================================
//    // Create
//    // ===================================================
//
//    public EducationPage clickAdd() {
//        actions.click(addButton);
//        return this;
//    }
//
//    public void createEducation(String level) {
//        actions.type(levelInput, level);
//        actions.click(saveButton);
//    }
//
//    public boolean isSuccessMessageDisplayed() {
//        return actions.isDisplayed(successToast);
//    }
//
//    // ===================================================
//    // Validation
//    // ===================================================
//
//    public void saveWithoutName() {
//
//        clickAdd();
//
//        actions.click(saveButton);
//    }
//
//    public boolean isRequiredErrorDisplayed() {
//        return actions.isDisplayed(requiredError);
//    }
//
//    public boolean isDuplicateErrorDisplayed() {
//        return actions.isDisplayed(duplicateError);
//    }
//
//    public boolean validationCleared() {
//        return !driver.findElements(requiredError).isEmpty()
//                ? false
//                : true;
//    }
//
//    // ===================================================
//    // Update
//    // ===================================================
//
//    public void editFirstEducation(String newName) {
//
//        actions.click(editButton);
//
//        driver.findElement(levelInput).clear();
//
//        actions.type(levelInput, newName);
//
//        actions.click(saveButton);
//    }
//
//    // ===================================================
//    // Delete
//    // ===================================================
//
//    public void deleteFirstEducation() {
//
//        actions.click(deleteIcon);
//
//        actions.click(confirmDeleteBtn);
//    }
//
//    public void cancelDelete() {
//
//        actions.click(deleteIcon);
//
//        actions.click(cancelDeleteBtn);
//    }
//
//    // ===================================================
//    // Multiple Delete
//    // ===================================================
//
//    public EducationPage selectMultipleRows(int count) {
//
//        for (int i = 2; i <= count + 1; i++) {
//
//            By checkbox =
//                    By.xpath("(//input[@type='checkbox'])[" + i + "]");
//
//            actions.click(checkbox);
//        }
//
//        return this;
//    }
//
//    public void deleteSelected() {
//
//        actions.click(deleteSelectedBtn);
//
//        actions.click(confirmDeleteBtn);
//    }
//
//    // ===================================================
//    // Empty State
//    // ===================================================
//
//    public boolean isNoRecordsFoundDisplayed() {
//        return actions.isDisplayed(noRecordsFound);
//    }
//
//    // ===================================================
//    // Selection
//    // ===================================================
//
//    public boolean selectSingleRecord() {
//
//        actions.click(firstCheckbox);
//
//        return true;
//    }
//}


package org.example.pages.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EducationPage extends Base {

    // Navigation
    private final By adminMenu = By.xpath("//span[text()='Admin']");
    private final By qualificationsMenu = By.xpath("//span[text()='Qualifications ']");
    private final By educationOption = By.xpath("//a[text()='Education']");
    // Table
    private final By educationRows =
            By.cssSelector(".oxd-table-body .oxd-table-row");
    private final By educationNameColumn =
            By.xpath("//div[@role='columnheader']//div[text()='Level']");
    // Add
    private final By addButton =
            By.xpath("//button[normalize-space()='Add']");
    private final By educationNameInput =
            By.xpath("(//input[contains(@class,'oxd-input')])[2]");
    private final By saveButton =
            By.xpath("//button[@type='submit']");
    // Edit
    private final By editButton =
            By.cssSelector(".bi-pencil-fill");
    // Delete
    private final By deleteButton =
            By.cssSelector(".bi-trash");
    private final By confirmDeleteBtn =
            By.xpath("//button[normalize-space()='Yes, Delete']");
    private final By cancelDeleteBtn =
            By.xpath("//button[normalize-space()='No, Cancel']");
    private final By deleteSelectedBtn =
            By.xpath("//button[contains(.,'Delete Selected')]");
    // Validation
    private final By requiredError =
            By.xpath("//span[text()='Required']");
    private final By duplicateError = By.className("oxd-input-group__message");
    private final By successToast =
            By.xpath("//p[contains(text(),'Successfully')]");
    // Checkbox
    private final By firstCheckbox =
            By.xpath("(//label[contains(@class,'oxd-checkbox-wrapper')])[2]");
    // Empty State
    private final By noRecordsFound =
            By.xpath("//*[contains(text(),'No Records Found')]");
    private WebDriver driver;
    private ActionsBot actions;

    public EducationPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    public EducationPage openEducationPage() {
        actions.click(adminMenu);
        actions.click(qualificationsMenu);
        actions.click(educationOption);
        return this;
    }

    public boolean pageOpened(String url) {
        return actions.inTargetPage(url);
    }

    public boolean isEducationTableDisplayed() {
        return !driver.findElements(educationRows).isEmpty();
    }

    public boolean isEducationNameColumnDisplayed() {
        return actions.isDisplayed(educationNameColumn);
    }

    public void clickAdd() {
        actions.click(addButton);
    }

    public void createEducation(String level) {
        clickAdd();
        actions.type(educationNameInput, level);
        actions.click(saveButton);
    }

    public void saveWithoutName() {
        clickAdd();
        actions.click(saveButton);
    }

    public boolean isRequiredValidationDisplayed() {
        return actions.isDisplayed(requiredError);
    }

    public boolean isDuplicateValidationDisplayed() {
        return actions.isDisplayed(duplicateError);
    }

    public boolean isSuccessMessageDisplayed() {
        return actions.isDisplayed(successToast);
    }

    public String getSuccessMessage() {
        return actions.getTextWhenVisible(successToast);
    }

    public void editFirstEducation(String newName) {

        actions.click(editButton);

        driver.findElement(educationNameInput).clear();

        actions.type(educationNameInput, newName);

        actions.click(saveButton);
    }

    public void deleteFirstEducation() {
        actions.click(deleteButton);
        actions.click(confirmDeleteBtn);
    }

    public void cancelDelete() {
        actions.click(deleteButton);
        actions.click(cancelDeleteBtn);
    }

    public EducationPage selectRecord() {
        actions.click(firstCheckbox);
        return this;
    }

    public void deleteSelected() {
        actions.click(deleteSelectedBtn);
        actions.click(confirmDeleteBtn);
    }

    public boolean isNoRecordsFoundDisplayed() {
        return actions.isDisplayed(noRecordsFound);
    }


    // Verify Add Form Opened
    public boolean isAddFormDisplayed() {

        try {
            return actions.isDisplayed(educationNameInput);
        } catch (Exception e) {
            return false;
        }
    }

    // Verify Edit Form Opened
    public boolean isEditFormDisplayed() {

        try {
            return actions.isDisplayed(educationNameInput);
        } catch (Exception e) {
            return false;
        }
    }

    // Click Edit Button
    public void clickEdit() {
        actions.click(editButton);
    }

    // Enter Education Name
    public void enterEducationName(String name) {
        actions.type(educationNameInput, name);
    }

    // Verify Education Exists In Table
    public boolean isEducationExist(String educationName) {

        By educationRecord = By.xpath(
                "//div[@role='cell']//div[normalize-space()='" + educationName + "']");

        try {
            return actions.isDisplayed(educationRecord);
        } catch (Exception e) {
            return false;
        }
    }

}