package bots;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionsBot {
    private WebDriver driver;
    private Wait wait;

    public ActionsBot(WebDriver driver) {
        this.driver = driver;
        this.wait = new Wait(driver);
    }

    // Clicking
    public void click(By locator) {
        wait.fluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        new Actions(d).scrollToElement(element);
                        element.click();
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
        );
    }

    // Typing
    public void type(By locator, String text) {
        wait.fluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        new Actions(d).scrollToElement(element);
                        element.clear();
                        element.sendKeys(text);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }


                }
        );
    }

    // Hovering
    public void hover(By locator) {
        wait.fluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        new Actions(d).scrollToElement(element);
                        element.click();
                        return true;
                    } catch (Exception e) {
                        return false;
                    }


                }
        );
    }

    // Getting Text
    public String getText(By locator) {
        return wait.fluentWait().until(d ->
                {
                    try {
                        WebElement element = d.findElement(locator);
                        new Actions(d).scrollToElement(element);
                        String msg = element.getText();
                        return !msg.isEmpty() ? msg : null;
                    } catch (Exception e) {
                        return null;
                    }


                }
        );
    }

    // Get current page
    public boolean inTargetPage(String url) {
        return wait.fluentWait().until(d ->
                {
                    try {
                        return driver.getCurrentUrl().equals(url);
                    } catch (Exception e) {
                        return false;
                    }

                }
        );
    }


    public boolean isDisplayed(By locator) {
        return wait.fluentWait().until(d ->
                {
                    try {
//                        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//                        // Wait until the element is visible on the DOM
//                        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(successToast));
                        return driver.findElement(locator).isDisplayed();
                    } catch (Exception e) {
                        return false;
                    }
                }
        );
    }

    public boolean clearElement(By locator) {
        return wait.fluentWait().until(d ->
                {
                    try {
                        driver.findElement(locator).clear();
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
        );
    }


    public WebElement waitUntilVisible(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String getTextWhenVisible(By locator) {
        try {
            return waitUntilVisible(locator).getText();
        } catch (Exception e) {
            return "";
        }
    }
}
