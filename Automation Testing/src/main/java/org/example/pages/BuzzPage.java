package org.example.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class BuzzPage extends Base {

    // --- Locators ---
    private final By buzzMenu = By.xpath("//span[text()='Buzz']");
    private final By buzzNewsFeedHeader = By.xpath("//h6[text()='Buzz']");
    // Feed Structural Widgets
    private final By postTextBoxArea = By.className("oxd-buzz-post-input");
    private final By buzzTabsContainer = By.className("orangehrm-buzz-tabs");
    private final By sidebarWidgetsContainer = By.className("orangehrm-buzz-widgets");
    // Post Generation Elements
    private final By postTextContainer = By.xpath("//textarea[contains(@class, 'oxd-buzz-post-input')]");
    private final By postButton = By.xpath("//button[@type='submit']");
    private final By sharePhotosBtn = By.xpath("//button[contains(.,'Share Photos')]");
    private final By photoUploadInput = By.cssSelector("input[type='file']");
    // Interactive Metadata Trackers
    private final By likeButtonIcon = By.xpath("(//div[@class='orangehrm-buzz-post-actions']//i[contains(@class,'oxd-icon')])[1]");
    private final By likeCounterText = By.xpath("(//p[contains(@class, 'oxd-text') and contains(.,'Like')])[1]");
    private final By commentButtonIcon = By.xpath("(//div[@class='orangehrm-buzz-post-actions']//i[contains(@class,'oxd-icon')])[2]");
    private final By commentTextInput = By.xpath("//input[@placeholder='Write your comment...']");
    private final By postCommentSubmitBtn = By.xpath("//input[@placeholder='Write your comment...']/following::button[@type='submit' or contains(.,'Post')]");
    // Social Controls and Safety Indicators
    private final By peerPostMoreOptionsBtn = By.xpath("(//div[contains(@class,'orangehrm-buzz-post-header-config')]//button)[1]");
    private final By deletePostOption = By.xpath("//p[text()='Delete Post']");
    private final By loginPageContainer = By.className("orangehrm-login-layout");
    private WebDriver driver;
    private ActionsBot actions;

    public BuzzPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    // --- Action Methods ---

    public BuzzPage navigateToBuzzWorkspace() {
        actions.click(buzzMenu);
        return this;
    }

    public boolean isPageOpened(String url) {
        return actions.inTargetPage(url);
    }

    public boolean areStructuralWidgetsLoaded() {
        boolean postAreaVisible = actions.isDisplayed(postTextBoxArea);
        boolean tabsVisible = actions.isDisplayed(buzzTabsContainer);
        boolean widgetsVisible = actions.isDisplayed(sidebarWidgetsContainer);
        return postAreaVisible && tabsVisible && widgetsVisible;
    }

    public void createTextPost(String content) {
        actions.type(postTextContainer, content);
    }

    public void uploadAttachmentFiles(String absoluteFilePath) {
        actions.click(sharePhotosBtn);
        driver.findElement(photoUploadInput).sendKeys(absoluteFilePath);
    }

    public void clickPublishPost() {
        actions.click(postButton);
        try {
            sleep(2000);
        } catch (InterruptedException e) {
        }
    }

    public void toggleLikeOnFirstPost() {
        actions.click(likeButtonIcon);
    }

    public String getLikeCounterMetrics() {
        return actions.getText(likeCounterText);
    }

    public void addCommentToFirstPost(String commentText) {
        actions.click(commentButtonIcon);
        actions.type(commentTextInput, commentText);
        actions.click(postCommentSubmitBtn);
    }

    public boolean isDeleteOptionVisibleToUser() {
        try {
            actions.click(peerPostMoreOptionsBtn);
            return actions.isDisplayed(deletePostOption);
        } catch (Exception e) {
            return false; // Option hidden or element missing for standard employee sessions
        }
    }

    public void triggerMockTokenInvalidation() {
        // Simulates backend token expiry by clearing browser local application states
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear(); window.sessionStorage.clear();");
        driver.navigate().refresh();
    }

    public boolean hasRedirectedToLoginScreen() {
        return actions.isDisplayed(loginPageContainer);
    }

    public void infiniteScrollFeedContainer() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 5; i++) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            try {
                sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }

    public boolean isFeedHeaderDisplayed() {
        return actions.isDisplayed(buzzNewsFeedHeader);
    }
}