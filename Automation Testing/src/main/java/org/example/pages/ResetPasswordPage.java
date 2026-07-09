package org.example.pages;

import org.example.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage {
    private WebDriver driver;

    private By usernameInput = By.name("username");
    private By resetButton = By.cssSelector("button[type='submit']");
    private By cancelButton = By.cssSelector("button[type='button']");
    private By pageHeader = By.xpath("//h6[contains(@class,'orangehrm-forgot-password-title')]");
    private By successHeader = By.xpath("//h6[contains(text(),'Reset Password link sent successfully')]");

    public ResetPasswordPage() {
        this.driver = DriverManager.getInstance().getDriver();
    }

    public boolean isOnPage() {
        try {
            return driver.findElement(pageHeader).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterUsername(String username) {
        driver.findElement(usernameInput).clear();
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void clickReset() {
        driver.findElement(resetButton).click();
    }

    public void clickCancel() {
        driver.findElement(cancelButton).click();
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            Thread.sleep(2000);
            return driver.findElement(successHeader).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
