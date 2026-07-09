package org.example.pages;

import bots.ActionsBot;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class loginPage {
    // locators
    private final By userName = By.name("username");
    private final By password = By.name("password");
    private final By loginBtn = By.cssSelector("button[type='submit']");
    private final By logoutTab = By.cssSelector("span[class='oxd-userdropdown-tab']");
    private final By logoutButton = By.cssSelector("a[href='/web/index.php/auth/logout']");
    private final By companyLogo = By.cssSelector("img[alt='company-branding']");
    private final By forgotPasswordLink = By.xpath("//p[contains(@class,'orangehrm-login-forgot-header')]");
    private final By alertMessage = By.xpath("//p[contains(@class,'oxd-alert-content-text')]");
    private final By requiredErrors = By.xpath("//span[contains(@class,'oxd-input-field-error-message')]");
    private final By eyeToggle = By.xpath("//i[contains(@class,'bi-eye') or contains(@class,'bi-eye-slash')]");
    private final By footerSocialLinks = By.xpath("//div[@class='orangehrm-login-footer-sm']//a");
    // variables
    private WebDriver driver;
    private ActionsBot actions;


    // constructor
    public loginPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }


    // actions
    public loginPage LogIn(String username, String password) {
        System.out.println("try to login");
        actions.type(userName, username);
        actions.type(this.password, password);
        actions.click(loginBtn);
        return this;
    }

    public void loginWithEnter(String username, String password) {
        actions.type(userName, username);
        actions.type(this.password, password);
        driver.findElement(this.password).sendKeys(Keys.ENTER);
    }

    public void doubleClickLogin(String username, String password) {
        actions.type(userName, username);
        actions.type(this.password, password);
        WebElement btn = driver.findElement(loginBtn);
        btn.click();
        btn.click();
    }


    public void logout() {
        try {
            driver.findElement(logoutTab).click();
            Thread.sleep(1000);
            driver.findElement(logoutButton).click();
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println("Logout error: " + e.getMessage());
        }
    }

    public void clickForgotPassword() {
        driver.findElement(forgotPasswordLink).click();
    }


    public boolean isPasswordMasked() {
        return "password".equals(driver.findElement(password).getAttribute("type"));
    }

    public void clickEyeToggle() {
        driver.findElement(eyeToggle).click();
    }

    public loginPage open() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        return this;
    }

    public boolean isLoogedIn(String url) {
//        return driver.getCurrentUrl().equals(url);
        return actions.inTargetPage(url);
    }

    public String getAlertMessage() {
        try {
            Thread.sleep(1500);
            return driver.findElement(alertMessage).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean areUIElementsVisible() {
        try {
            return driver.findElement(userName).isDisplayed()
                    && driver.findElement(password).isDisplayed()
                    && driver.findElement(loginBtn).isDisplayed()
                    && driver.findElement(companyLogo).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areFooterLinksPresent() {
        List<WebElement> links = driver.findElements(footerSocialLinks);
        return !links.isEmpty();
    }

    public boolean isLoginButtonEnabled() {
        return driver.findElement(loginBtn).isEnabled();
    }

    public String getUsernameFieldValue() {
        try {
            return driver.findElement(userName).getAttribute("value");
        } catch (Exception e) {
            return "";
        }
    }

    public String getPasswordFieldValue() {
        try {
            return driver.findElement(password).getAttribute("value");
        } catch (Exception e) {
            return "";
        }
    }

    public void pastePasswordAndLogin(String password) {
        actions.type(userName, "Admin");
        WebElement pass = driver.findElement(this.password);
        pass.sendKeys(password);
        pass.sendKeys(Keys.CONTROL + "a");
        pass.sendKeys(Keys.CONTROL + "c");
        pass.clear();
        pass.sendKeys(Keys.CONTROL + "v");
        actions.click(loginBtn);
    }

}
