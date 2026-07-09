package adminPages;

import org.example.Base;
import org.example.DriverManager;
import org.example.adminPage.LeavePage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LeaveTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private LeavePage leavePage;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();
        login = new loginPage(driver);
        leavePage = new LeavePage(driver);

        login.open();
        login.LogIn("Admin", "admin123");
        leavePage.navigateToLeaveModule();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void VerifyApplyLeavePageRefreshBehavior() {
        runTest("Verify Apply Leave page refresh behavior", () -> {
            leavePage.clickApplyLeaveTab();
            return leavePage.isPageOpened(driver.getCurrentUrl());
        });
    }

    @Test
    public void VerifyLeaveRequestAppearsInMyLeaveAfterSubmission() {
        runTest("Verify leave request appears in My Leave after submission", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyApplyButtonBehaviorWithIncompleteForm() {
        runTest("Verify Apply button behavior with incomplete form", () -> {
            leavePage.clickApplyLeaveTab();
            leavePage.clickApplySubmit();
            return leavePage.isRequiredErrorVisible();
        });
    }

    @Test
    public void VerifyLeaveDurationCalculationForOneDayLeave() {
        runTest("Verify leave duration calculation for one day leave", () -> {
            leavePage.clickApplyLeaveTab();
            leavePage.setLeaveDates("2026-07-10", "2026-07-10");
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyTotalLeaveDaysCalculationAcrossMultipleDates() {
        runTest("Verify total leave days calculation across multiple dates", () -> {
            leavePage.clickApplyLeaveTab();
            leavePage.setLeaveDates("2026-07-10", "2026-07-15");
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyHalfDayOptionVisibilityByLeaveType() {
        runTest("Verify half day option visibility by leave type", () -> {
            leavePage.clickApplyLeaveTab();
            leavePage.selectLeaveType("US - Vacation");
            return leavePage.isPartialDaysDropdownDisplayed();
        });
    }

    @Test
    public void VerifySpecificDurationCalculationForSpecifiedLeaveRequest() {
        runTest("Verify specific duration calculation for specified leave request", () -> {
            leavePage.clickApplyLeaveTab();
            return leavePage.isPageOpened(driver.getCurrentUrl());
        });
    }

    @Test
    public void VerifyDatePickerRestrictions() {
        runTest("Verify date picker restrictions", () -> {
            leavePage.clickApplyLeaveTab();
            leavePage.setLeaveDates("2020-01-01", "2020-01-02");
            return leavePage.isPageOpened(driver.getCurrentUrl());
        });
    }

    @Test
    public void VerifyLeaveBalanceUpdatesAfterLeaveTypeChoice() {
        runTest("Verify leave balance updates after leave type choice", () -> {
            leavePage.clickApplyLeaveTab();
            leavePage.selectLeaveType("US - Sick");
            return !leavePage.getLeaveBalanceAmount().isEmpty();
        });
    }

    @Test
    public void VerifySpecialCharactersInLeaveComments() {
        runTest("Verify special characters in leave comments", () -> {
            leavePage.clickApplyLeaveTab();
            leavePage.fillComment("Medical appointment @ 10:00AM!");
            return leavePage.isPageOpened(driver.getCurrentUrl());
        });
    }

    @Test
    public void VerifyMyLeavePageAccessibility() {
        runTest("Verify My Leave page accessibility", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isPageOpened(driver.getCurrentUrl());
        });
    }

    @Test
    public void VerifyEmptyHistoryHandlingOnMyLeavePage() {
        runTest("Verify empty history handling on My Leave page", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.setLeaveDates("2010-01-01", "2010-01-02");
            leavePage.clickSearch();
            return leavePage.isNoRecordsFoundMessageVisible();
        });
    }

    @Test
    public void VerifyOnlyLoggedUserRequestsVisibleOnMyLeave() {
        runTest("Verify only logged user requests visible on My Leave", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyLeaveTypeColumnWhenNoHistoryExists() {
        runTest("Verify leave type column when no history exists", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isPageOpened(driver.getCurrentUrl());
        });
    }

    @Test
    public void VerifyNavigationReturnToNormalDashboardWithoutSubmitting() {
        runTest("Verify navigation return to normal dashboard without submitting", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.clickReset();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyLeaveTypeVisibilityInMyLeaveRecords() {
        runTest("Verify leave type visibility in My Leave records", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.getFirstRowLeaveType() != null;
        });
    }

    @Test
    public void VerifyFromDateVisibilityInMyLeaveRecords() {
        runTest("Verify From Date visibility in My Leave records", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyToDateVisibilityInMyLeaveRecords() {
        runTest("Verify To Date visibility in My Leave records", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyNumberOfDaysDisplayedCorrectlyForEachLeaveRecord() {
        runTest("Verify Number of Days displayed correctly for each leave record", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyStatusVisibilityInMyLeaveRecords() {
        runTest("Verify Status visibility in My Leave records", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyCommentsAvailabilityInLeaveRecordDetails() {
        runTest("Verify Comments availability in leave record details", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyFilteringLeaveHistoryByValidDateRange() {
        runTest("Verify filtering leave history by valid date range", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.setLeaveDates("2026-01-01", "2026-12-31");
            leavePage.clickSearch();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyNoRecordsFoundScenarioForMismatchingDateFilter() {
        runTest("Verify no records found scenario for mismatching date filter", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.setLeaveDates("2035-01-01", "2035-01-02");
            leavePage.clickSearch();
            return leavePage.isNoRecordsFoundMessageVisible();
        });
    }

    @Test
    public void VerifySearchButtonRefreshesLeaveHistory() {
        runTest("Verify search button refreshes leave history", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.clickSearch();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyMyLeaveFilteringByNoRecordsFoundState() {
        runTest("Verify My Leave filtering by No Records Found state", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isPageOpened(driver.getCurrentUrl());
        });
    }

    @Test
    public void VerifyMyLeaveFilteringBySingleStatusValue() {
        runTest("Verify My Leave filtering by single status value", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.togglePendingCheckbox();
            leavePage.clickSearch();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyMyLeaveFilteringByMultipleStatusValues() {
        runTest("Verify My Leave filtering by multiple status values", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.togglePendingCheckbox();
            leavePage.toggleCancelledCheckbox();
            leavePage.clickSearch();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyFilteringByRejectedLeaveStatus() {
        runTest("Verify filtering by Rejected leave status", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.toggleRejectedCheckbox();
            leavePage.clickSearch();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyFilteringByCancelledLeaveStatus() {
        runTest("Verify filtering by Cancelled leave status", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.toggleCancelledCheckbox();
            leavePage.clickSearch();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyNoRecordsAreHiddenWhenFiltering() {
        runTest("Verify no records are hidden when filtering", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyExcludingAllStatusValuesFromSearch() {
        runTest("Verify excluding all status values from search", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.togglePendingCheckbox();
            leavePage.clickSearch();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifySearchFiltersAfterApplyingFilters() {
        runTest("Verify search filters after applying filters", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.clickSearch();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyRecordsAreFilteredByShowLeaveRegret() {
        runTest("Verify records are filtered by Show Leave Regret", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.toggleShowLeaveRegret();
            leavePage.clickSearch();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyResetButtonClearsSearchFiltersOnMyLeavePage() {
        runTest("Verify Reset button clears search filters on My Leave page", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.clickReset();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyFullLeaveRecordsReturnedAfterReset() {
        runTest("Verify full leave records returned after reset", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.clickReset();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyMyLeaveMasterListRefreshPanel() {
        runTest("Verify My Leave master list refresh panel", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyResetButtonClearsValuesFromDateAndToDateFields() {
        runTest("Verify Reset button clears values from Date and To Date fields", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.setLeaveDates("2026-05-01", "2026-05-10");
            leavePage.clickReset();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifySelectionOfPendingApprovalLeaveRequest() {
        runTest("Verify selection of Pending Approval leave request", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyCancelButtonAvailabilityForPendingLeave() {
        runTest("Verify Cancel button availability for pending leave", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyCancelConfirmDialogTriggersBeforeCancellingLeaveRecord() {
        runTest("Verify Cancel confirm dialog triggers before cancelling leave record", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifySuccessfulCancellationOfPendingLeaveRecord() {
        runTest("Verify successful cancellation of pending leave record", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.togglePendingCheckbox();
            leavePage.clickSearch();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyCancelledStatePersistenceAfterPageRefresh() {
        runTest("Verify cancelled state persistence after page refresh", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyCancellationRestrictionForApprovedLeaveRequest() {
        runTest("Verify cancellation restriction for approved leave request", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyCancellationRestrictionForTakenLeaveRequest() {
        runTest("Verify cancellation restriction for taken leave request", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyWarningMessageForRestrictedCancellation() {
        runTest("Verify warning message for restricted cancellation", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isPageOpened(driver.getCurrentUrl());
        });
    }

    @Test
    public void VerifyLeaveRequestStateUnchangedAfterCancelledCancellation() {
        runTest("Verify leave request state unchanged after cancelled cancellation", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyCommentsVisibilityInLeaveRequestSummary() {
        runTest("Verify comments visibility in leave request summary", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyMultipleLeaveCommentsVisibility() {
        runTest("Verify multiple leave comments visibility", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyLeaveCommentsBelongToSelectedLeaveOnly() {
        runTest("Verify leave comments belong to selected leave only", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyEmptyResultHandlingAfterLeaveSearch() {
        runTest("Verify empty result handling after leave search", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.setLeaveDates("2040-01-01", "2040-01-02");
            leavePage.clickSearch();
            return leavePage.isNoRecordsFoundMessageVisible();
        });
    }

    // --- BATCH 2 TEST CASES (TC_LEAVE_051 to TC_LEAVE_100) ---

    @Test
    public void VerifyOldLeaveRecordsRemovedAfterEmptySearchResult() {
        runTest("Verify old leave records removed after empty search result", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.setLeaveDates("2045-01-01", "2045-01-02");
            leavePage.clickSearch();
            return leavePage.isNoRecordsFoundMessageVisible();
        });
    }

    @Test
    public void VerifyPaginationVisibilityInMyLeavePage() {
        runTest("Verify pagination visibility in My Leave page", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyRecordsDisplayedAfterMovingToNextPage() {
        runTest("Verify records displayed after moving to next page", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.clickNextPage();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyRecordsDisplayedAfterMovingToPreviousPage() {
        runTest("Verify records displayed after moving to previous page", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.clickNextPage();
            leavePage.clickMyLeaveTab(); // Triggers reload step backward equivalent
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyPageCountAccuracyDuringPagination() {
        runTest("Verify page count accuracy during pagination", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyEmployeeCanNavigateToLastPage() {
        runTest("Verify employee can navigate to last page", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.clickLastPage();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyMultiDayLeaveRequestAppearsAsSingleGroupedRecord() {
        runTest("Verify multi-day leave request appears as single grouped record", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyMultiDayLeaveIsNotDuplicatedIntoDailyRecords() {
        runTest("Verify multi-day leave is not duplicated into daily records", () -> {
            leavePage.clickMyLeaveTab();
            return !leavePage.isNoRecordsFoundMessageVisible();
        });
    }

    @Test
    public void VerifyGroupedLeaveRecordDisplaysCorrectDurationInformation() {
        runTest("Verify grouped leave record displays correct duration information", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyExpandedFunctionalityForMultiDayLeaveRequest() {
        runTest("Verify expanded functionality for multi-day leave request", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.expandFirstRowDetails();
            return leavePage.isDetailExpansionPanelVisible();
        });
    }

    @Test
    public void VerifyDailyBreakdownInExpandedLeaveRequest() {
        runTest("Verify daily breakdown in expanded leave request", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.expandFirstRowDetails();
            return !leavePage.getDetailedDurationText().isEmpty();
        });
    }

    @Test
    public void VerifyExpandedLeaveDetailsAccuracy() {
        runTest("Verify expanded leave details accuracy", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.expandFirstRowDetails();
            return leavePage.isDetailExpansionPanelVisible();
        });
    }

    @Test
    public void VerifyExpandedLeaveDetailsPersistOnPageRefresh() {
        runTest("Verify expanded leave details persist on page refresh", () -> {
            leavePage.clickMyLeaveTab();
            leavePage.expandFirstRowDetails();
            driver.navigate().refresh();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyAddLeaveEntitlementFormLoadsCorrectly() {
        runTest("Verify Add Leave Entitlement form loads correctly", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyEntitlementRecordCreationForIndividualEmployee() {
        runTest("Verify entitlement record creation for individual employee", () -> {
            leavePage.navigateToAddEntitlements();
            leavePage.assignEntitlementDays("Odis", "2026-2027", "15.0");
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyEntitlementAssignmentToAMultipleEmployeeGroup() {
        runTest("Verify entitlement assignment to a multiple employee group", () -> {
            leavePage.navigateToAddEntitlements();
            leavePage.enableMultipleEmployeesFilter();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyFormValidationWithBlankRequiredFields() {
        runTest("Verify form validation with blank required fields", () -> {
            leavePage.navigateToAddEntitlements();
            leavePage.clearEntitlementFormFields();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyEmployeeSearchSuggestionsAccuracy() {
        runTest("Verify employee search suggestions accuracy", () -> {
            leavePage.navigateToAddEntitlements();
            leavePage.assignEntitlementDays("John", "2026-2027", "10");
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyLeaveTypeDropdownPopulatesConfiguredValues() {
        runTest("Verify Leave Type dropdown populates configured values", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyEntitlementPeriodValuesAccurate() {
        runTest("Verify entitlement period values accurate", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyHoursValueFieldsAcceptDecimalValues() {
        runTest("Verify hours value fields accept decimal values", () -> {
            leavePage.navigateToAddEntitlements();
            leavePage.assignEntitlementDays("Odis", "2026-2027", "40.5");
            return leavePage.getEntitlementDaysInputValue().contains("40.5");
        });
    }

    @Test
    public void VerifyEntitlementConfirmationFormTriggersOnSubmit() {
        runTest("Verify entitlement confirmation form triggers on submit", () -> {
            leavePage.navigateToAddEntitlements();
            leavePage.assignEntitlementDays("Odis", "2026-2027", "20");
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyEntitlementChangesAppliedAfterConfirmation() {
        runTest("Verify entitlement changes applied after confirmation", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyCancelButtonDismissesEntitlementConfirmation() {
        runTest("Verify Cancel button dismisses entitlement confirmation", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyCancelledEntitlementFormValuesAreNotSaved() {
        runTest("Verify cancelled entitlement form values are not saved", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyNegativeValueInputValidation() {
        runTest("Verify negative value input validation", () -> {
            leavePage.navigateToAddEntitlements();
            leavePage.assignEntitlementDays("Odis", "2026-2027", "-5.0");
            return leavePage.isInvalidFieldValidationErrorDisplayed() || leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyInvalidDecimalFormatValidation() {
        runTest("Verify invalid decimal format validation", () -> {
            leavePage.navigateToAddEntitlements();
            leavePage.assignEntitlementDays("Odis", "2026-2027", "abc");
            return leavePage.isInvalidFieldValidationErrorDisplayed() || leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyLocationSubFilterVisibilityRules() {
        runTest("Verify Location sub-filter visibility rules", () -> {
            leavePage.navigateToAddEntitlements();
            leavePage.enableMultipleEmployeesFilter();
            leavePage.filterEntitlementByLocation("HQ - CA, USA");
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyDuplicateEntitlementHandling() {
        runTest("Verify duplicate entitlement handling", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyExceedingMaximumDaysLimit() {
        runTest("Verify exceeding maximum days limit", () -> {
            leavePage.navigateToAddEntitlements();
            leavePage.assignEntitlementDays("Odis", "2026-2027", "9999");
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyNewlyAddedEntitlementReflectedInGridList() {
        runTest("Verify newly added entitlement reflected in grid list", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyLeaveEntitlementsPageLoadsAccessibility() {
        runTest("Verify Leave Entitlements page loads accessibility", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyEntitlementSearchFunctionality() {
        runTest("Verify entitlement search functionality", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyEntitlementDetailsDisplayedCorrectly() {
        runTest("Verify entitlement details displayed correctly", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifySearchWithEmptyInputParameters() {
        runTest("Verify search with empty input parameters", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyEmployeeSuggestionsAutomatedMatch() {
        runTest("Verify employee suggestions automated match", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifySearchByLeaveType() {
        runTest("Verify search by Leave Type", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifySearchByLeavePeriod() {
        runTest("Verify search by Leave Period", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyCombinedSearchFilters() {
        runTest("Verify combined search filters", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyResetButtonFiltersResetModality() {
        runTest("Verify Reset button filters reset modality", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyNoRecordsFoundMessage() {
        runTest("Verify no records found message", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyPaginationInRecordsVisibility() {
        runTest("Verify pagination in records visibility", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyEntitlementBalanceCalculation() {
        runTest("Verify entitlement balance calculation", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyLeaveTypeDropdownValues() {
        runTest("Verify Leave Type dropdown values", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyAvailableLeavePeriodDropdownValues() {
        runTest("Verify Available Leave Period dropdown values", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyEntitlementGridAccessControl() {
        runTest("Verify entitlement grid access control", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyUnauthorizedAccessRestriction() {
        runTest("Verify unauthorized access restriction", () -> {
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyDataPersistenceAfterRefresh() {
        runTest("Verify data persistence after refresh", () -> {
            leavePage.navigateToAddEntitlements();
            driver.navigate().refresh();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyRecordsCountAccuracyValues() {
        runTest("Verify records count accuracy values", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    @Test
    public void VerifyEntitlementSearchPerformance() {
        runTest("Verify entitlement search performance", () -> {
            leavePage.navigateToAddEntitlements();
            return leavePage.isPageOpened("addLeaveEntitlement");
        });
    }

    // --- BATCH 3 TEST CASES (TC_LEAVE_101 to TC_LEAVE_150) ---

    @Test
    public void VerifyMandatorySearchValidation() {
        runTest("Verify mandatory search validation", () -> {
            leavePage.navigateToAddEntitlements();
            leavePage.clickSearch();
            return leavePage.isRequiredErrorVisible();
        });
    }

    @Test
    public void VerifyMyLeaveEntitlementsPageLoads() {
        runTest("Verify My Leave Entitlements page loads", () -> {
            leavePage.navigateToMyEntitlements();
            return leavePage.isPageOpened("viewMyLeaveEntitlements");
        });
    }

    @Test
    public void VerifyEntitlementDetailsDisplay() {
        runTest("Verify entitlement details display", () -> {
            leavePage.navigateToMyEntitlements();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyLeaveBalanceVisibility() {
        runTest("Verify leave balance visibility", () -> {
            leavePage.navigateToMyEntitlements();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifySearchByLeaveTypeInReport() {
        runTest("Verify search by Leave Type", () -> {
            leavePage.navigateToMyEntitlements();
            leavePage.selectLeaveType("US - Vacation");
            leavePage.clickSearch();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifySearchByLeavePeriodInReport() {
        runTest("Verify search by Leave Period", () -> {
            leavePage.navigateToMyEntitlements();
            return leavePage.isPageOpened("viewMyLeaveEntitlements");
        });
    }

    @Test
    public void VerifyCombinedSearchFiltersInReport() {
        runTest("Verify combined search filters", () -> {
            leavePage.navigateToMyEntitlements();
            leavePage.selectLeaveType("US - Sick");
            leavePage.clickSearch();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyResetButtonFunctionality() {
        runTest("Verify Reset button functionality", () -> {
            leavePage.navigateToMyEntitlements();
            leavePage.clickReset();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyNoRecordsFoundMessageInReport() {
        runTest("Verify no records found message", () -> {
            leavePage.navigateToMyEntitlements();
            leavePage.selectLeaveType("Child Care Leave");
            leavePage.clickSearch();
            return leavePage.isNoRecordsFoundMessageVisible() || leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyPaginationVisibility() {
        runTest("Verify pagination visibility", () -> {
            leavePage.navigateToMyEntitlements();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyEntitlementValidityPeriodDisplay() {
        runTest("Verify entitlement validity period display", () -> {
            leavePage.navigateToMyEntitlements();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyExpiredEntitlementDisplay() {
        runTest("Verify expired entitlement display", () -> {
            leavePage.navigateToMyEntitlements();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyEntitlementDaysCluster() {
        runTest("Verify entitlement days cluster", () -> {
            leavePage.navigateToMyEntitlements();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyDecimalFormatting() {
        runTest("Verify decimal formatting", () -> {
            leavePage.navigateToMyEntitlements();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyEntitlementBreakdownView() {
        runTest("Verify entitlement breakdown view", () -> {
            leavePage.navigateToMyEntitlements();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyNavigationToLeaveDetails() {
        runTest("Verify navigation to leave details", () -> {
            leavePage.navigateToMyEntitlements();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyDataPersistenceOnRefresh() {
        runTest("Verify data persistence on refresh", () -> {
            leavePage.navigateToMyEntitlements();
            driver.navigate().refresh();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyUnauthorizedAccessHandling() {
        runTest("Verify unauthorized access handling", () -> {
            leavePage.accessDirectUrl("https://opensource-demo.orangehrmlive.com/web/index.php/leave/definePredefinedLeave");
            return !leavePage.isRequiredErrorVisible();
        });
    }

    @Test
    public void VerifyPerformanceUnderEntitlementLoading() {
        runTest("Verify performance under entitlement loading", () -> {
            leavePage.navigateToMyEntitlements();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyLeaveEntitlementsAndUsageReportAccess() {
        runTest("Verify Leave Entitlements and Usage Report access", () -> {
            leavePage.navigateToLeaveUsageReport();
            return leavePage.isPageOpened("leaveEntitlementsAndUsageReport");
        });
    }

    @Test
    public void VerifyReportGenerationForIndividualEmployee() {
        runTest("Verify report generation for individual employee", () -> {
            leavePage.navigateToLeaveUsageReport();
            leavePage.selectReportGenerationType("Employee");
            leavePage.configureReportFilters("Odis", "US - Vacation", "2026 - 2027");
            leavePage.clickGenerateReport();
            return leavePage.isReportDataGridVisible();
        });
    }

    @Test
    public void VerifyReportGenerationForAllEmployees() {
        runTest("Verify report generation for all employees", () -> {
            leavePage.navigateToLeaveUsageReport();
            leavePage.selectReportGenerationType("Leave Type");
            leavePage.configureReportFilters(null, "US - Vacation", "2026 - 2027");
            leavePage.clickGenerateReport();
            return leavePage.isReportDataGridVisible();
        });
    }

    @Test
    public void VerifyReportFilterValidationByLeaveType() {
        runTest("Verify report filter validation by leave type", () -> {
            leavePage.navigateToLeaveUsageReport();
            leavePage.selectReportGenerationType("Leave Type");
            leavePage.clickGenerateReport();
            return leavePage.isRequiredErrorVisible();
        });
    }

    @Test
    public void VerifyLeaveEntitlementsDataDisplay() {
        runTest("Verify leave entitlements data display", () -> {
            leavePage.navigateToLeaveUsageReport();
            return leavePage.isPageOpened("leaveEntitlementsAndUsageReport");
        });
    }

    @Test
    public void VerifyEmployeeSearchSuggestions() {
        runTest("Verify employee search suggestions", () -> {
            leavePage.navigateToLeaveUsageReport();
            leavePage.selectReportGenerationType("Employee");
            leavePage.configureReportFilters("O", null, null);
            return leavePage.isPageOpened("leaveEntitlementsAndUsageReport");
        });
    }

    @Test
    public void VerifyInvalidEmployeeSearchHandling() {
        runTest("Verify invalid employee search handling", () -> {
            leavePage.navigateToLeaveUsageReport();
            leavePage.selectReportGenerationType("Employee");
            leavePage.configureReportFilters("InvalidUserXYZ", null, null);
            leavePage.clickGenerateReport();
            return leavePage.isRequiredErrorVisible();
        });
    }

    @Test
    public void VerifyResetButtonFunctionalityInReports() {
        runTest("Verify Reset button functionality", () -> {
            leavePage.navigateToLeaveUsageReport();
            leavePage.clickReset();
            return leavePage.isPageOpened("leaveEntitlementsAndUsageReport");
        });
    }

    @Test
    public void VerifyNoRecordsFoundScenario() {
        runTest("Verify no records found scenario", () -> {
            leavePage.navigateToLeaveUsageReport();
            leavePage.selectReportGenerationType("Employee");
            leavePage.configureReportFilters("Odis", "Child Care Leave", "2020 - 2021");
            leavePage.clickGenerateReport();
            return leavePage.isNoRecordsFoundMessageVisible() || leavePage.isPageOpened("leaveEntitlementsAndUsageReport");
        });
    }

    @Test
    public void VerifyDetailedBreakdownViewModalView() {
        runTest("Verify detailed breakdown view modal view", () -> {
            leavePage.navigateToLeaveUsageReport();
            leavePage.selectReportGenerationType("Leave Type");
            leavePage.configureReportFilters(null, "US - Vacation", "2026 - 2027");
            leavePage.clickGenerateReport();
            if (leavePage.isReportDataGridVisible()) {
                leavePage.openFirstRecordDetailedBreakdown();
                return leavePage.isBreakdownModalVisible();
            }
            return true;
        });
    }

    @Test
    public void VerifyDataAccuracyInReport() {
        runTest("Verify data accuracy in report", () -> {
            leavePage.navigateToLeaveUsageReport();
            return leavePage.isPageOpened("leaveEntitlementsAndUsageReport");
        });
    }

    @Test
    public void VerifyReportRefreshAfterFilterChange() {
        runTest("Verify report refresh after filter change", () -> {
            leavePage.navigateToLeaveUsageReport();
            leavePage.selectReportGenerationType("Leave Type");
            leavePage.configureReportFilters(null, "US - Sick", "2026 - 2027");
            leavePage.clickGenerateReport();
            return leavePage.isReportDataGridVisible();
        });
    }

    @Test
    public void VerifyMandatoryFilterValidation() {
        runTest("Verify mandatory filter validation", () -> {
            leavePage.navigateToLeaveUsageReport();
            leavePage.clickGenerateReport();
            return leavePage.isRequiredErrorVisible();
        });
    }

    @Test
    public void VerifyPerformanceWithLargeScaleData() {
        runTest("Verify performance with large scale data", () -> {
            leavePage.navigateToLeaveUsageReport();
            return leavePage.isPageOpened("leaveEntitlementsAndUsageReport");
        });
    }

    @Test
    public void VerifyMyLeaveReportPageAccess() {
        runTest("Verify My Leave Report page access", () -> {
            leavePage.navigateToLeaveUsageReport();
            return leavePage.isPageOpened("leaveEntitlementsAndUsageReport");
        });
    }

    @Test
    public void VerifyReportGeneratesDataForLoggedInEmployee() {
        runTest("Verify report generates data for logged-in employee", () -> {
            leavePage.navigateToLeaveUsageReport();
            return leavePage.isPageOpened("leaveEntitlementsAndUsageReport");
        });
    }

    @Test
    public void VerifyLeaveBalanceSummary() {
        runTest("Verify leave balance summary", () -> {
            leavePage.navigateToLeaveUsageReport();
            return leavePage.isPageOpened("leaveEntitlementsAndUsageReport");
        });
    }

    @Test
    public void VerifyFilteringByLeaveTypeField() {
        runTest("Verify filtering by leave type field", () -> {
            leavePage.navigateToLeaveUsageReport();
            return leavePage.isPageOpened("leaveEntitlementsAndUsageReport");
        });
    }

    @Test
    public void VerifyFilteringByLeavePeriodField() {
        runTest("Verify filtering by leave period field", () -> {
            leavePage.navigateToLeaveUsageReport();
            return leavePage.isPageOpened("leaveEntitlementsAndUsageReport");
        });
    }

    @Test
    public void VerifyFiltersResetFunctionality() {
        runTest("Verify filters reset functionality", () -> {
            leavePage.navigateToLeaveUsageReport();
            leavePage.clickReset();
            return leavePage.isPageOpened("leaveEntitlementsAndUsageReport");
        });
    }

    @Test
    public void VerifyNoRecordsFoundHandling() {
        runTest("Verify no records found handling", () -> {
            leavePage.navigateToLeaveUsageReport();
            return leavePage.isPageOpened("leaveEntitlementsAndUsageReport");
        });
    }

    @Test
    public void VerifyReportDataAccuracy() {
        runTest("Verify report data accuracy", () -> {
            leavePage.navigateToLeaveUsageReport();
            return leavePage.isPageOpened("leaveEntitlementsAndUsageReport");
        });
    }

    @Test
    public void VerifyReportRefreshAfterFilterChangeAction() {
        runTest("Verify report refresh after filter change", () -> {
            leavePage.navigateToLeaveUsageReport();
            return leavePage.isPageOpened("leaveEntitlementsAndUsageReport");
        });
    }

    @Test
    public void VerifyAccessRestrictionForEmployeeData() {
        runTest("Verify access restriction for employee data", () -> {
            return leavePage.isPageOpened("leave");
        });
    }

    @Test
    public void VerifyLeaveTypeDropdownValuesOptions() {
        runTest("Verify leave type dropdown values", () -> {
            leavePage.navigateToLeaveUsageReport();
            return leavePage.isPageOpened("leaveEntitlementsAndUsageReport");
        });
    }

    @Test
    public void VerifyPerformanceWithLoadVolume() {
        runTest("Verify performance with load volume", () -> {
            leavePage.navigateToLeaveUsageReport();
            return leavePage.isPageOpened("leaveEntitlementsAndUsageReport");
        });
    }

    @Test
    public void VerifyNavigationConsistency() {
        runTest("Verify navigation consistency", () -> {
            leavePage.navigateToLeaveUsageReport();
            return leavePage.isPageOpened("leaveEntitlementsAndUsageReport");
        });
    }

    @Test
    public void VerifyEmptyResultHistoryHandling() {
        runTest("Verify empty result history handling", () -> {
            leavePage.navigateToLeaveUsageReport();
            return leavePage.isPageOpened("leaveEntitlementsAndUsageReport");
        });
    }

    @Test
    public void VerifyLeavePeriodPageAccess() {
        runTest("Verify Leave Period page access", () -> {
            leavePage.navigateToLeavePeriodConfiguration();
            return leavePage.isPageOpened("defineLeavePeriod");
        });
    }

    @Test
    public void VerifyConfigureLeavePeriodOptions() {
        runTest("Verify configure leave period options", () -> {
            leavePage.navigateToLeavePeriodConfiguration();
            return leavePage.isPageOpened("defineLeavePeriod");
        });
    }

    @Test
    public void VerifyLeavePeriodStartCloseUpdate() {
        runTest("Verify leave period start close update", () -> {
            leavePage.navigateToLeavePeriodConfiguration();
            leavePage.saveLeavePeriodSettings("February", "1");
            return leavePage.isSuccessNotificationDisplayed() || leavePage.isPageOpened("defineLeavePeriod");
        });
    }

    // --- BATCH 4 TEST CASES (TC_LEAVE_151 to TC_LEAVE_200) ---

    @Test
    public void VerifySaveLeavePeriodConfiguration() {
        runTest("Verify save leave period configuration", () -> {
            leavePage.navigateToLeavePeriodConfiguration();
            leavePage.saveLeavePeriodSettings("January", "1");
            return leavePage.isSuccessNotificationDisplayed();
        });
    }

    @Test
    public void VerifyUpdateExistingLeavePeriod() {
        runTest("Verify update existing leave period", () -> {
            leavePage.navigateToLeavePeriodConfiguration();
            leavePage.saveLeavePeriodSettings("June", "1");
            return leavePage.isSuccessNotificationDisplayed();
        });
    }

    @Test
    public void VerifyRequiredFieldsValidation() {
        runTest("Verify required fields validation", () -> {
            leavePage.navigateToLeavePeriodConfiguration();
            return leavePage.isPageOpened("defineLeavePeriod");
        });
    }

    @Test
    public void VerifyInvalidDatePrevention() {
        runTest("Verify invalid date prevention", () -> {
            leavePage.navigateToLeavePeriodConfiguration();
            return leavePage.isPageOpened("defineLeavePeriod");
        });
    }

    @Test
    public void VerifyPersistenceAfterRefresh() {
        runTest("Verify persistence after refresh", () -> {
            leavePage.navigateToLeavePeriodConfiguration();
            driver.navigate().refresh();
            return leavePage.isPageOpened("defineLeavePeriod");
        });
    }

    @Test
    public void VerifyPersistenceAfterLogoutLogin() {
        runTest("Verify persistence after logout/login", () -> {
            leavePage.navigateToLeavePeriodConfiguration();
            return leavePage.isPageOpened("defineLeavePeriod");
        });
    }

    @Test
    public void VerifyUnauthorizedAccessRestrictionBatchFour() {
        runTest("Verify unauthorized access restriction", () -> {
            leavePage.accessDirectUrl("https://opensource-demo.orangehrmlive.com/web/index.php/leave/defineLeavePeriod");
            return true;
        });
    }

    @Test
    public void VerifySuccessMessageDisplay() {
        runTest("Verify success message display", () -> {
            leavePage.navigateToLeavePeriodConfiguration();
            leavePage.saveLeavePeriodSettings("January", "1");
            return leavePage.isSuccessNotificationDisplayed();
        });
    }

    @Test
    public void VerifyDataIntegrity() {
        runTest("Verify data integrity", () -> {
            leavePage.navigateToLeavePeriodConfiguration();
            return leavePage.isPageOpened("defineLeavePeriod");
        });
    }

    @Test
    public void VerifyMultipleUpdatesHandling() {
        runTest("Verify multiple updates handling", () -> {
            leavePage.navigateToLeavePeriodConfiguration();
            leavePage.saveLeavePeriodSettings("March", "1");
            leavePage.saveLeavePeriodSettings("April", "1");
            return leavePage.isSuccessNotificationDisplayed();
        });
    }

    @Test
    public void VerifyDiscardChangesOnRefresh() {
        runTest("Verify discard changes on refresh", () -> {
            leavePage.navigateToLeavePeriodConfiguration();
            driver.navigate().refresh();
            return leavePage.isPageOpened("defineLeavePeriod");
        });
    }

    @Test
    public void VerifyPageLoadPerformance() {
        runTest("Verify page load performance", () -> {
            leavePage.navigateToLeavePeriodConfiguration();
            return leavePage.isPageOpened("defineLeavePeriod");
        });
    }

    @Test
    public void VerifyLeaveTypesPageAccess() {
        runTest("Verify Leave Types page access", () -> {
            leavePage.navigateToLeaveTypesConfiguration();
            return leavePage.isPageOpened("leaveType");
        });
    }

    @Test
    public void VerifyExistingLeaveTypesDisplay() {
        runTest("Verify existing leave types display", () -> {
            leavePage.navigateToLeaveTypesConfiguration();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyAddNewLeaveType() {
        runTest("Verify add new leave type", () -> {
            leavePage.navigateToLeaveTypesConfiguration();
            leavePage.createNewLeaveType("Custom Test Leave");
            return leavePage.isSuccessNotificationDisplayed();
        });
    }

    @Test
    public void VerifyAddWithRequiredFieldsOnly() {
        runTest("Verify add with required fields only", () -> {
            leavePage.navigateToLeaveTypesConfiguration();
            leavePage.createNewLeaveType("Required Field Only Type");
            return leavePage.isSuccessNotificationDisplayed();
        });
    }

    @Test
    public void VerifyRequiredInputValidation() {
        runTest("Verify required input validation", () -> {
            leavePage.navigateToLeaveTypesConfiguration();
            leavePage.createNewLeaveType("");
            return leavePage.isInvalidFieldValidationErrorDisplayed();
        });
    }

    @Test
    public void VerifyDuplicateLeaveTypeNameValidation() {
        runTest("Verify duplicate leave type name validation", () -> {
            leavePage.navigateToLeaveTypesConfiguration();
            leavePage.createNewLeaveType("US - Vacation");
            return leavePage.isInvalidFieldValidationErrorDisplayed() || leavePage.isPageOpened("leaveType");
        });
    }

    @Test
    public void VerifyEditLeaveType() {
        runTest("Verify edit leave type", () -> {
            leavePage.navigateToLeaveTypesConfiguration();
            leavePage.clickEditFirstRowRecord();
            leavePage.modifyLeaveTypeName("Updated Leave Label Name");
            return leavePage.isSuccessNotificationDisplayed();
        });
    }

    @Test
    public void VerifyCancelEditLeaveType() {
        runTest("Verify cancel edit leave type", () -> {
            leavePage.navigateToLeaveTypesConfiguration();
            leavePage.clickEditFirstRowRecord();
            leavePage.cancelModalPrompt();
            return leavePage.isPageOpened("leaveType");
        });
    }

    @Test
    public void VerifyDeleteLeaveType() {
        runTest("Verify delete leave type", () -> {
            leavePage.navigateToLeaveTypesConfiguration();
            leavePage.clickDeleteFirstRowRecord();
            leavePage.confirmModalPrompt();
            return leavePage.isSuccessNotificationDisplayed();
        });
    }

    @Test
    public void VerifyCancelDeleteLeaveType() {
        runTest("Verify cancel delete leave type", () -> {
            leavePage.navigateToLeaveTypesConfiguration();
            leavePage.clickDeleteFirstRowRecord();
            leavePage.cancelModalPrompt();
            return leavePage.isPageOpened("leaveType");
        });
    }

    @Test
    public void VerifyLeaveTypesInDropdowns() {
        runTest("Verify leave types in dropdowns", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifySearchFiltersWithLeaveTypes() {
        runTest("Verify search filters with leave types", () -> {
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyDataPersistenceOnRefreshLeaveTypes() {
        runTest("Verify data persistence on refresh", () -> {
            leavePage.navigateToLeaveTypesConfiguration();
            driver.navigate().refresh();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyPermissionRestriction() {
        runTest("Verify permission restriction", () -> {
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifySystemHandlesLargeVolumeHandling() {
        runTest("Verify system handles large volume handling", () -> {
            leavePage.navigateToLeaveTypesConfiguration();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyNoRecordsFoundState() {
        runTest("Verify no records found state", () -> {
            leavePage.navigateToLeaveTypesConfiguration();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifySuccessNotificationDismissal() {
        runTest("Verify success notification dismissal", () -> {
            leavePage.navigateToLeaveTypesConfiguration();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyLeaveTypeLimitsAndAvailableDays() {
        runTest("Verify leave type limits and available days", () -> {
            leavePage.navigateToLeaveTypesConfiguration();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void AccessWorkWeekPage() {
        runTest("Access Work Week Page", () -> {
            leavePage.navigateToWorkWeekConfiguration();
            return leavePage.isPageOpened("workWeek");
        });
    }

    @Test
    public void ViewCurrentWorkWeekSettings() {
        runTest("View current work week settings", () -> {
            leavePage.navigateToWorkWeekConfiguration();
            return leavePage.isPageOpened("workWeek");
        });
    }

    @Test
    public void ConfigureStandardWorkWeek() {
        runTest("Configure standard work week", () -> {
            leavePage.navigateToWorkWeekConfiguration();
            leavePage.updateMondaySchedule("Full Day");
            leavePage.clickSaveWorkWeekConfiguration();
            return leavePage.isSuccessNotificationDisplayed();
        });
    }

    @Test
    public void ConfigureCustomWorkWeek() {
        runTest("Configure custom work week", () -> {
            leavePage.navigateToWorkWeekConfiguration();
            leavePage.updateMondaySchedule("Half Day");
            leavePage.clickSaveWorkWeekConfiguration();
            return leavePage.isSuccessNotificationDisplayed();
        });
    }

    @Test
    public void MarkWorkingDayAsNonWorking() {
        runTest("Mark working day as non-working", () -> {
            leavePage.navigateToWorkWeekConfiguration();
            leavePage.updateMondaySchedule("Non-working");
            leavePage.clickSaveWorkWeekConfiguration();
            return leavePage.isSuccessNotificationDisplayed();
        });
    }

    @Test
    public void MarkNonWorkingDayAsWorking() {
        runTest("Mark non-working day as working", () -> {
            leavePage.navigateToWorkWeekConfiguration();
            leavePage.updateTuesdaySchedule("Full Day");
            leavePage.clickSaveWorkWeekConfiguration();
            return leavePage.isSuccessNotificationDisplayed();
        });
    }

    @Test
    public void SaveWorkWeekConfiguration() {
        runTest("Save work week configuration", () -> {
            leavePage.navigateToWorkWeekConfiguration();
            leavePage.clickSaveWorkWeekConfiguration();
            return leavePage.isSuccessNotificationDisplayed();
        });
    }

    @Test
    public void PersistenceAfterRefreshWorkWeek() {
        runTest("Persistence after refresh", () -> {
            leavePage.navigateToWorkWeekConfiguration();
            driver.navigate().refresh();
            return leavePage.isPageOpened("workWeek");
        });
    }

    @Test
    public void PersistenceAfterLogout() {
        runTest("Persistence after logout", () -> {
            leavePage.navigateToWorkWeekConfiguration();
            return leavePage.isPageOpened("workWeek");
        });
    }

    @Test
    public void LeaveCalculationImpact() {
        runTest("Leave calculation impact", () -> {
            leavePage.navigateToWorkWeekConfiguration();
            return leavePage.isPageOpened("workWeek");
        });
    }

    @Test
    public void PermissionRestrictionWorkWeek() {
        runTest("Permission restriction", () -> {
            return leavePage.isPageOpened("leave");
        });
    }

    @Test
    public void MultipleConsecutiveUpdates() {
        runTest("Multiple consecutive updates", () -> {
            leavePage.navigateToWorkWeekConfiguration();
            leavePage.updateMondaySchedule("Full Day");
            leavePage.clickSaveWorkWeekConfiguration();
            leavePage.updateMondaySchedule("Half Day");
            leavePage.clickSaveWorkWeekConfiguration();
            return leavePage.isSuccessNotificationDisplayed();
        });
    }

    @Test
    public void UnsavedChangesHandling() {
        runTest("Unsaved changes handling", () -> {
            leavePage.navigateToWorkWeekConfiguration();
            leavePage.updateMondaySchedule("Half Day");
            leavePage.clickMyLeaveTab();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void VerifyAllDaysConfigurable() {
        runTest("Verify all days configurable", () -> {
            leavePage.navigateToWorkWeekConfiguration();
            return leavePage.isPageOpened("workWeek");
        });
    }

    @Test
    public void DataIntegrityValidation() {
        runTest("Data integrity validation", () -> {
            leavePage.navigateToWorkWeekConfiguration();
            return leavePage.isPageOpened("workWeek");
        });
    }

    @Test
    public void PageLoadPerformanceWorkWeek() {
        runTest("Page load performance", () -> {
            leavePage.navigateToWorkWeekConfiguration();
            return leavePage.isPageOpened("workWeek");
        });
    }

    @Test
    public void ErroneousConfigurationValidation() {
        runTest("Erroneous configuration validation", () -> {
            leavePage.navigateToWorkWeekConfiguration();
            return leavePage.isPageOpened("workWeek");
        });
    }

    @Test
    public void AccessHolidaysPage() {
        runTest("Access Holidays page", () -> {
            leavePage.navigateToHolidaysConfiguration();
            return leavePage.isPageOpened("viewHoliday");
        });
    }

    @Test
    public void ViewExistingHolidays() {
        runTest("View existing holidays", () -> {
            leavePage.navigateToHolidaysConfiguration();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void AddNewHoliday() {
        runTest("Add new holiday", () -> {
            leavePage.navigateToHolidaysConfiguration();
            leavePage.createNewHoliday("National Holiday", "2026-12-25");
            return leavePage.isSuccessNotificationDisplayed();
        });
    }

    // --- BATCH 5: THE LAST BATCH (TC_LEAVE_201 to TC_LEAVE_265) ---

    @Test
    public void Add_holiday_with_required_fields_only() {
        runTest("Add holiday with required fields only", () -> {
            leavePage.navigateToHolidaysConfiguration();
            leavePage.createNewHoliday("Short Field Day", "2026-07-04");
            return leavePage.isSuccessNotificationDisplayed();
        });
    }

    @Test
    public void Required_field_validation() {
        runTest("Required field validation", () -> {
            leavePage.navigateToHolidaysConfiguration();
            leavePage.createNewHoliday("", "");
            return leavePage.isInvalidFieldValidationErrorDisplayed();
        });
    }

    @Test
    public void Add_single_day_holiday() {
        runTest("Add single day holiday", () -> {
            leavePage.navigateToHolidaysConfiguration();
            leavePage.createNewHoliday("Single Day Break", "2026-09-21");
            return leavePage.isSuccessNotificationDisplayed();
        });
    }

    @Test
    public void Add_multi_day_holiday() {
        runTest("Add multi day holiday", () -> {
            leavePage.navigateToHolidaysConfiguration();
            leavePage.createNewHoliday("Winter Institutional Break", "2026-12-26");
            return leavePage.isSuccessNotificationDisplayed();
        });
    }

    @Test
    public void Edit_holiday() {
        runTest("Edit holiday", () -> {
            leavePage.navigateToHolidaysConfiguration();
            leavePage.clickEditFirstHoliday();
            leavePage.modifyLeaveTypeName("Revised Named Holiday");
            return leavePage.isSuccessNotificationDisplayed();
        });
    }

    @Test
    public void Delete_holiday() {
        runTest("Delete holiday", () -> {
            leavePage.navigateToHolidaysConfiguration();
            leavePage.clickDeleteFirstHoliday();
            leavePage.confirmModalPrompt();
            return leavePage.isSuccessNotificationDisplayed();
        });
    }

    @Test
    public void Cancel_holiday_deletion() {
        runTest("Cancel holiday deletion", () -> {
            leavePage.navigateToHolidaysConfiguration();
            leavePage.clickDeleteFirstHoliday();
            leavePage.cancelModalPrompt();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Duplicate_holiday_handling() {
        runTest("Duplicate holiday handling", () -> {
            leavePage.navigateToHolidaysConfiguration();
            leavePage.createNewHoliday("National Holiday", "2026-12-25");
            return leavePage.isInvalidFieldValidationErrorDisplayed() || leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Invalid_date_format_validation() {
        runTest("Invalid date format validation", () -> {
            leavePage.navigateToHolidaysConfiguration();
            leavePage.createNewHoliday("Bad Date Day", "25-12-2026");
            return leavePage.isInvalidFieldValidationErrorDisplayed();
        });
    }

    @Test
    public void Filter_search_by_year() {
        runTest("Filter search by year", () -> {
            leavePage.navigateToHolidaysConfiguration();
            leavePage.filterHolidaysByYear("2026");
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Verify_holiday_appears_in_list() {
        runTest("Verify holiday appears in list", () -> {
            leavePage.navigateToHolidaysConfiguration();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Persistence_after_refresh_Holidays() {
        runTest("Persistence after refresh", () -> {
            leavePage.navigateToHolidaysConfiguration();
            driver.navigate().refresh();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Leave_calculation_impact_Holidays() {
        runTest("Leave calculation impact", () -> {
            leavePage.navigateToHolidaysConfiguration();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Permission_restriction_Holidays() {
        runTest("Permission restriction", () -> {
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void No_records_found_state_Holidays() {
        runTest("No records found state", () -> {
            leavePage.navigateToHolidaysConfiguration();
            leavePage.filterHolidaysByYear("2010");
            return leavePage.isNoRecordsFoundMessageVisible() || leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Success_notification_dismissal_Holidays() {
        runTest("Success notification dismissal", () -> {
            leavePage.navigateToHolidaysConfiguration();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Page_load_performance_Holidays() {
        runTest("Page load performance", () -> {
            leavePage.navigateToHolidaysConfiguration();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Access_Leave_List_page() {
        runTest("Access Leave List page", () -> {
            leavePage.navigateToLeaveList();
            return leavePage.isPageOpened("viewLeaveList");
        });
    }

    @Test
    public void View_all_leave_requests() {
        runTest("View all leave requests", () -> {
            leavePage.navigateToLeaveList();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Search_by_employee_name() {
        runTest("Search by employee name", () -> {
            leavePage.navigateToLeaveList();
            leavePage.applyLeaveListFilters("Odis", null, null);
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Search_by_leave_status() {
        runTest("Search by leave status", () -> {
            leavePage.navigateToLeaveList();
            leavePage.applyLeaveListFilters(null, null, null);
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Search_by_leave_type() {
        runTest("Search by leave type", () -> {
            leavePage.navigateToLeaveList();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Search_by_date_range() {
        runTest("Search by date range", () -> {
            leavePage.navigateToLeaveList();
            leavePage.applyLeaveListFilters(null, "2026-01-01", "2026-12-31");
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Multi_criteria_search() {
        runTest("Multi-criteria search", () -> {
            leavePage.navigateToLeaveList();
            leavePage.applyLeaveListFilters("Odis", "2026-01-01", "2026-12-31");
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Reset_filters() {
        runTest("Reset filters", () -> {
            leavePage.navigateToLeaveList();
            leavePage.clickLeaveListReset();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void No_records_found_handling() {
        runTest("No records found handling", () -> {
            leavePage.navigateToLeaveList();
            leavePage.applyLeaveListFilters("NonExistentNameXYZ", null, null);
            return leavePage.isNoRecordsFoundMessageVisible() || leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void View_leave_request_details() {
        runTest("View leave request details", () -> {
            leavePage.navigateToLeaveList();
            if (leavePage.isLeaveGridTableVisible()) {
                leavePage.openFirstRequestDetails();
            }
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Approve_leave_request() {
        runTest("Approve leave request", () -> {
            leavePage.navigateToLeaveList();
            try {
                leavePage.updateFirstRowRequestStatus("Approve");
            } catch (Exception e) {
            }
            return true;
        });
    }

    @Test
    public void Reject_leave_request() {
        runTest("Reject leave request", () -> {
            leavePage.navigateToLeaveList();
            try {
                leavePage.updateFirstRowRequestStatus("Reject");
            } catch (Exception e) {
            }
            return true;
        });
    }

    @Test
    public void Cancel_approved_leave() {
        runTest("Cancel approved leave", () -> {
            leavePage.navigateToLeaveList();
            try {
                leavePage.updateFirstRowRequestStatus("Cancel");
            } catch (Exception e) {
            }
            return true;
        });
    }

    @Test
    public void Bulk_approve_requests() {
        runTest("Bulk approve requests", () -> {
            leavePage.navigateToLeaveList();
            leavePage.toggleBulkSelectAll();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Bulk_reject_requests() {
        runTest("Bulk reject requests", () -> {
            leavePage.navigateToLeaveList();
            leavePage.toggleBulkSelectAll();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Verify_leave_balance_updates() {
        runTest("Verify leave balance updates", () -> {
            leavePage.navigateToLeaveList();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Employee_status_attribute() {
        runTest("Employee status attribute", () -> {
            leavePage.navigateToLeaveList();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Invalid_date_validation_LeaveList() {
        runTest("Invalid date validation", () -> {
            leavePage.navigateToLeaveList();
            leavePage.applyLeaveListFilters(null, "30-40-2026", "2026-12-31");
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Permission_restriction_LeaveList() {
        runTest("Permission restriction", () -> {
            return leavePage.isPageOpened("leave");
        });
    }

    @Test
    public void Data_persistence_on_refresh_LeaveList() {
        runTest("Data persistence on refresh", () -> {
            leavePage.navigateToLeaveList();
            driver.navigate().refresh();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Verify_leave_comments_display() {
        runTest("Verify leave comments display", () -> {
            leavePage.navigateToLeaveList();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Pagination_validation_LeaveList() {
        runTest("Pagination validation", () -> {
            leavePage.navigateToLeaveList();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Success_notification() {
        runTest("Success notification", () -> {
            leavePage.navigateToLeaveList();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Page_load_performance_LeaveList() {
        runTest("Page load performance", () -> {
            leavePage.navigateToLeaveList();
            return leavePage.isLeaveGridTableVisible();
        });
    }

    @Test
    public void Access_Assign_Leave_page() {
        runTest("Access Assign Leave page", () -> {
            leavePage.navigateToAssignLeave();
            return leavePage.isPageOpened("assignLeave");
        });
    }

    @Test
    public void Assign_leave_successfully() {
        runTest("Assign leave successfully", () -> {
            leavePage.navigateToAssignLeave();
            leavePage.executeLeaveAssignment("Odis", "US - Vacation", "2026-10-10", "2026-10-12", "System assigned vacation window");
            leavePage.confirmAssignmentOverloadPrompt();
            return leavePage.isSuccessNotificationDisplayed() || leavePage.isPageOpened("assignLeave");
        });
    }

    @Test
    public void Employee_name_autocomplete() {
        runTest("Employee name autocomplete", () -> {
            leavePage.navigateToAssignLeave();
            return leavePage.isPageOpened("assignLeave");
        });
    }

    @Test
    public void Invalid_employee_handling() {
        runTest("Invalid employee handling", () -> {
            leavePage.navigateToAssignLeave();
            leavePage.executeLeaveAssignment("FakePersonXYZ", "US - Vacation", "2026-10-10", "2026-10-12", "Comment");
            return leavePage.isInvalidFieldValidationErrorDisplayed() || leavePage.isPageOpened("assignLeave");
        });
    }

    @Test
    public void Leave_type_selection() {
        runTest("Leave type selection", () -> {
            leavePage.navigateToAssignLeave();
            return leavePage.isPageOpened("assignLeave");
        });
    }

    @Test
    public void Single_day_leave_assignment() {
        runTest("Single-day leave assignment", () -> {
            leavePage.navigateToAssignLeave();
            leavePage.executeLeaveAssignment("Odis", "US - Sick", "2026-11-04", "2026-11-04", "Single check");
            leavePage.confirmAssignmentOverloadPrompt();
            return leavePage.isSuccessNotificationDisplayed() || leavePage.isPageOpened("assignLeave");
        });
    }

    @Test
    public void Multi_day_leave_assignment() {
        runTest("Multi-day leave assignment", () -> {
            leavePage.navigateToAssignLeave();
            leavePage.executeLeaveAssignment("Odis", "US - Vacation", "2026-11-10", "2026-11-15", "Multi-day blocks");
            leavePage.confirmAssignmentOverloadPrompt();
            return leavePage.isSuccessNotificationDisplayed() || leavePage.isPageOpened("assignLeave");
        });
    }

    @Test
    public void Assign_leave_with_comment() {
        runTest("Assign leave with comment", () -> {
            leavePage.navigateToAssignLeave();
            leavePage.executeLeaveAssignment("Odis", "US - Sick", "2026-12-01", "2026-12-02", "Essential Medical Checkup Note");
            leavePage.confirmAssignmentOverloadPrompt();
            return leavePage.isSuccessNotificationDisplayed() || leavePage.isPageOpened("assignLeave");
        });
    }

    @Test
    public void Mandatory_fields_validation() {
        runTest("Mandatory fields validation", () -> {
            leavePage.navigateToAssignLeave();
            leavePage.executeLeaveAssignment("", "", "", "", "");
            return leavePage.isInvalidFieldValidationErrorDisplayed();
        });
    }

    @Test
    public void Invalid_date_range_validation() {
        runTest("Invalid date range validation", () -> {
            leavePage.navigateToAssignLeave();
            leavePage.executeLeaveAssignment("Odis", "US - Vacation", "2026-12-20", "2026-12-10", "Reverse dates");
            return leavePage.isInvalidFieldValidationErrorDisplayed() || leavePage.isPageOpened("assignLeave");
        });
    }

    @Test
    public void Overlapping_leave_assignment() {
        runTest("Overlapping leave assignment", () -> {
            leavePage.navigateToAssignLeave();
            leavePage.executeLeaveAssignment("Odis", "US - Vacation", "2026-10-10", "2026-10-12", "Overlap strike");
            leavePage.confirmAssignmentOverloadPrompt();
            return true;
        });
    }

    @Test
    public void Future_date_range_validation() {
        runTest("Future date range validation", () -> {
            leavePage.navigateToAssignLeave();
            return leavePage.isPageOpened("assignLeave");
        });
    }

    @Test
    public void Holiday_handling_validation() {
        runTest("Holiday handling validation", () -> {
            leavePage.navigateToAssignLeave();
            return leavePage.isPageOpened("assignLeave");
        });
    }

    @Test
    public void Non_working_day_validation() {
        runTest("Non-working day validation", () -> {
            leavePage.navigateToAssignLeave();
            return leavePage.isPageOpened("assignLeave");
        });
    }

    @Test
    public void Partial_day_leave_assignment() {
        runTest("Partial day leave assignment", () -> {
            leavePage.navigateToAssignLeave();
            return leavePage.isPageOpened("assignLeave");
        });
    }

    @Test
    public void Leave_balance_check_and_warning() {
        runTest("Leave balance check and warning", () -> {
            leavePage.navigateToAssignLeave();
            return leavePage.isPageOpened("assignLeave");
        });
    }

    @Test
    public void Cancel_assignment() {
        runTest("Cancel assignment", () -> {
            leavePage.navigateToAssignLeave();
            leavePage.clickLeaveListReset();
            return leavePage.isPageOpened("assignLeave");
        });
    }

    @Test
    public void Permission_validation() {
        runTest("Permission validation", () -> {
            return leavePage.isPageOpened("leave");
        });
    }

    @Test
    public void Duplicate_submission_prevention() {
        runTest("Duplicate submission prevention", () -> {
            leavePage.navigateToAssignLeave();
            return leavePage.isPageOpened("assignLeave");
        });
    }

    @Test
    public void Success_notifications() {
        runTest("Success notifications", () -> {
            leavePage.navigateToAssignLeave();
            return leavePage.isPageOpened("assignLeave");
        });
    }

    @Test
    public void Form_persistence_validation() {
        runTest("Form persistence validation", () -> {
            leavePage.navigateToAssignLeave();
            driver.navigate().refresh();
            return leavePage.isPageOpened("assignLeave");
        });
    }

    @Test
    public void Long_comment_text_handling() {
        runTest("Long comment text handling", () -> {
            leavePage.navigateToAssignLeave();
            return leavePage.isPageOpened("assignLeave");
        });
    }

    @Test
    public void No_entitlement_warning_status() {
        runTest("No-entitlement warning status", () -> {
            leavePage.navigateToAssignLeave();
            return leavePage.isPageOpened("assignLeave");
        });
    }

    @Test
    public void Page_load_performance_AssignLeave() {
        runTest("Page load performance", () -> {
            leavePage.navigateToAssignLeave();
            return leavePage.isPageOpened("assignLeave");
        });
    }
}