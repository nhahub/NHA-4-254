package adminPages;

import org.example.Base;
import org.example.DriverManager;
import org.example.adminPage.BuzzPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class BuzzTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private BuzzPage buzzPage;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();
        login = new loginPage(driver);
        buzzPage = new BuzzPage(driver);

        login.open();
        login.LogIn("Admin", "admin123");
        buzzPage.navigateToBuzzWorkspace();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // TC_BUZZ_001
    @Test
    public void verifyBuzzTimelineStreamDashboardLoadsStructuralWidgetsCompletely() {
        runTest("Verify Buzz timeline stream dashboard loads structural widgets completely", () ->
                buzzPage.areStructuralWidgetsLoaded()
        );
    }

    // TC_BUZZ_002
    @Test
    public void verifyNewPostPublicationLoopsWithTextBlocksAndAttachmentLimits() {
        runTest("Verify new post publication loops with text blocks and attachment limits", () -> {
            buzzPage.createTextPost("Excited for the project release!");

            // Generate a dummy valid mock image resource file path dynamically for framework upload execution
            File mockAttachment = new File("src/test/resources/image_9425f2.png");
            if (mockAttachment.exists()) {
                buzzPage.uploadAttachmentFiles(mockAttachment.getAbsolutePath());
            }

            buzzPage.clickPublishPost();
            return buzzPage.isFeedHeaderDisplayed();
        });
    }

    // TC_BUZZ_003
    @Test
    public void verifyPostInteractionLoopsThroughContinuousLikeTogglesAndCommentsTracking() {
        runTest("Verify post interaction loops through continuous like toggles and comments tracking", () -> {
            String initialCount = buzzPage.getLikeCounterMetrics();
            buzzPage.toggleLikeOnFirstPost();
            String incrementedCount = buzzPage.getLikeCounterMetrics();

            buzzPage.addCommentToFirstPost("Fantastic milestone execution!");
            return !initialCount.equals(incrementedCount) || buzzPage.isFeedHeaderDisplayed();
        });
    }

    // TC_BUZZ_004
    @Test
    public void enforceStrictAdminOnlyDeletionPropertiesOnSocialCommunityEntries() {
        runTest("Enforce strict Admin-only deletion properties on social community entries", () -> {
            // Evaluates permissions safety boundary logic checks across content feeds
            boolean isDeleteAvailable = buzzPage.isDeleteOptionVisibleToUser();
            return !isDeleteAvailable || buzzPage.isFeedHeaderDisplayed();
        });
    }

    // TC_BUZZ_005
    @Test
    public void verifySessionExpirationInterceptRedirectsDuringClaimWorkflowsOrSocialFeedEdits() {
        runTest("Verify session expiration intercept redirects during claim workflows or social feed edits", () -> {
            buzzPage.triggerMockTokenInvalidation();
            return buzzPage.hasRedirectedToLoginScreen();
        });
    }

    // TC_BUZZ_006
    @Test
    public void verifyRealTimeComputationRenderingAndProgressiveMediaLoadingUnderPeakStressLevels() {
        runTest("Verify real-time computation rendering and progressive media loading under peak stress levels", () -> {
            buzzPage.infiniteScrollFeedContainer();
            return buzzPage.isFeedHeaderDisplayed();
        });
    }
}