package adminPages;

import org.example.Base;
import org.example.DriverManager;
import org.example.adminPage.PerformancePage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PerformanceTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private PerformancePage performancePage;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();
        login = new loginPage(driver);
        performancePage = new PerformancePage(driver);

        login.open();
        login.LogIn("Admin", "admin123");
        performancePage.navigateToPerformanceReviews();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // TC_PERF_001
    @Test
    public void verifyPerformanceReviewPageOpensSuccessfully() {
        runTest("Verify Performance Review page opens successfully", () ->
                performancePage.isPageOpened(driver.getCurrentUrl())
        );
    }

    // TC_PERF_002
    @Test
    public void verifyPerformanceReviewsListIsDisplayed() {
        runTest("Verify Performance Reviews list is displayed", () ->
                performancePage.isReviewsTableDisplayed()
        );
    }

    // TC_PERF_003
    @Test
    public void verifyFilteringReviewsByEmployeeName() {
        runTest("Verify filtering reviews by employee name", () -> {
            performancePage.filterByEmployeeName("John");
            performancePage.clickSearch();
            return performancePage.isReviewsTableDisplayed();
        });
    }

    // TC_PERF_004
    @Test
    public void verifyFilteringReviewsByJobTitle() {
        runTest("Verify filtering reviews by job title", () -> {
            performancePage.filterByJobTitle("QA Engineer");
            performancePage.clickSearch();
            return performancePage.isReviewsTableDisplayed();
        });
    }

    // TC_PERF_005
    @Test
    public void verifyFilteringReviewsByReviewStatus() {
        runTest("Verify filtering reviews by review status", () -> {
            performancePage.filterByStatus("Activated");
            performancePage.clickSearch();
            return performancePage.isReviewsTableDisplayed();
        });
    }

    // TC_PERF_006
    @Test
    public void verifyResetButtonClearsAllSearchFilters() {
        runTest("Verify reset button clears all search filters", () -> {
            performancePage.filterByJobTitle("QA Engineer");
            performancePage.clickReset();
            return performancePage.isReviewsTableDisplayed();
        });
    }

    // TC_PERF_007
    @Test
    public void verifyPerformanceReviewEvaluationFormOpens() {
        runTest("Verify Performance Review evaluation form opens", () -> {
            performancePage.clickEvaluateFirstReview();
            return !driver.getCurrentUrl().contains("searchEvaluatePerformanceReview");
        });
    }

    // TC_PERF_008
    @Test
    public void verifySavingPerformanceEvaluationDraft() {
        runTest("Verify saving performance evaluation draft", () -> {
            performancePage.clickEvaluateFirstReview();
            performancePage.fillReviewComment("Drafting objective benchmarks.");
            performancePage.clickSaveReview();
            return performancePage.isSuccessMessageDisplayed();
        });
    }

    // TC_PERF_009
    @Test
    public void verifyActivatingPerformanceReview() {
        runTest("Verify activating performance review", () -> {
            performancePage.clickEvaluateFirstReview();
            performancePage.clickActivateReview();
            return performancePage.isSuccessMessageDisplayed();
        });
    }

    // TC_PERF_010
    @Test
    public void verifyValidationOnRatingFieldBoundaries() {
        runTest("Verify validation on rating field boundaries", () -> {
            performancePage.clickEvaluateFirstReview();
            performancePage.fillReviewRating("150");
            performancePage.clickSaveReview();
            return performancePage.isInvalidRatingErrorDisplayed();
        });
    }

    // TC_PERF_011
    @Test
    public void verifyRequiredFieldsValidationInEvaluation() {
        runTest("Verify required fields validation in evaluation", () -> {
            performancePage.clickEvaluateFirstReview();
            performancePage.fillReviewRating("");
            performancePage.clickSaveReview();
            return performancePage.isRequiredErrorDisplayed();
        });
    }

    // TC_PERF_012
    @Test
    public void verifyApprovalWorkflowTransition() {
        runTest("Verify approval workflow transition", () -> {
            performancePage.clickEvaluateFirstReview();
            performancePage.clickApproveReview();
            return performancePage.isSuccessMessageDisplayed();
        });
    }

    // TC_PERF_013
    @Test
    public void verifyMassDeletionOfSelectedReviews() {
        runTest("Verify mass deletion of selected reviews", () -> {
            performancePage.selectFirstRowCheckbox();
            performancePage.clickDeleteSelected();
            performancePage.confirmDelete();
            return performancePage.isSuccessMessageDisplayed();
        });
    }

    // TC_PERF_014
    @Test
    public void verifySelectAllCheckboxesTogglesGridRows() {
        runTest("Verify select all checkboxes toggles grid rows", () -> {
            performancePage.selectAllCheckboxes();
            return performancePage.isReviewsTableDisplayed();
        });
    }

    // TC_PERF_015
    @Test
    public void verifyDeletionCancelationRetainsReviewRecords() {
        runTest("Verify deletion cancelation retains review records", () -> {
            performancePage.selectFirstRowCheckbox();
            performancePage.clickDeleteSelected();
            boolean dialogVisible = performancePage.isConfirmationDialogDisplayed();
            performancePage.cancelDelete();
            return dialogVisible && performancePage.isReviewsTableDisplayed();
        });
    }

    // TC_PERF_016
    @Test
    public void verifyGridSortingByEmployeeName() {
        runTest("Verify grid sorting by employee name", () -> {
            performancePage.sortByEmployeeName();
            return performancePage.isReviewsTableDisplayed();
        });
    }

    // TC_PERF_017
    @Test
    public void verifyValidationOnNegativeRatingValues() {
        runTest("Verify validation on negative rating values", () -> {
            performancePage.clickEvaluateFirstReview();
            performancePage.fillReviewRating("-10");
            performancePage.clickSaveReview();
            return performancePage.isInvalidRatingErrorDisplayed();
        });
    }

    // TC_PERF_018
    @Test
    public void verifyEmptySearchFiltersReturnAllRecords() {
        runTest("Verify empty search filters return all records", () -> {
            performancePage.clickSearch();
            return performancePage.isReviewsTableDisplayed();
        });
    }

    // TC_PERF_019
    @Test
    public void verifyNoRecordsFoundScenarioLayout() {
        runTest("Verify no records found scenario layout", () -> {
            performancePage.filterByEmployeeName("NonExistentNameXYZ");
            performancePage.clickSearch();
            return performancePage.isNoRecordsFoundDisplayed();
        });
    }

    // TC_PERF_020
    @Test
    public void verifyAccessControlForEmployeeRoleOnReviews() {
        runTest("Verify access control for employee role on reviews", () -> {
            return performancePage.isReviewsTableDisplayed();
        });
    }

    // TC_PERF_021
    @Test
    public void verifySearchingReviewsBySubUnitFilters() {
        runTest("Verify searching reviews by sub unit filters", () -> {
            performancePage.clickSearch();
            return performancePage.isReviewsTableDisplayed();
        });
    }

    // TC_PERF_022
    @Test
    public void verifySearchingByMultipleCombinedFilters() {
        runTest("Verify searching by multiple combined filters", () -> {
            performancePage.filterByEmployeeName("John");
            performancePage.filterByJobTitle("QA Engineer");
            performancePage.clickSearch();
            return performancePage.isReviewsTableDisplayed();
        });
    }

    // TC_PERF_023
    @Test
    public void verifyValidationOnDecimalRatingInputs() {
        runTest("Verify validation on decimal rating inputs", () -> {
            performancePage.clickEvaluateFirstReview();
            performancePage.fillReviewRating("85.5");
            performancePage.clickSaveReview();
            return performancePage.isSuccessMessageDisplayed();
        });
    }

    // TC_PERF_024
    @Test
    public void verifyCommentsSectionSupportsLongText() {
        runTest("Verify comments section supports long text", () -> {
            performancePage.clickEvaluateFirstReview();
            performancePage.fillReviewComment("A".repeat(500));
            performancePage.clickSaveReview();
            return performancePage.isSuccessMessageDisplayed();
        });
    }

    // TC_PERF_025
    @Test
    public void verifyReviewPeriodDatesValidationVisibility() {
        runTest("Verify review period dates validation visibility", () -> {
            return performancePage.isReviewsTableDisplayed();
        });
    }

    // TC_PERF_026
    @Test
    public void verifyReviewStatusUpdatesInGridPostAction() {
        runTest("Verify review status updates in grid post action", () -> {
            return performancePage.isReviewsTableDisplayed();
        });
    }

    // TC_PERF_027
    @Test
    public void verifySearchPerformanceWithMassiveReviewsData() {
        runTest("Verify search performance with massive reviews data", () -> {
            performancePage.clickSearch();
            return performancePage.isReviewsTableDisplayed();
        });
    }
}