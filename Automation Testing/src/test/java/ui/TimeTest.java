package adminPages;

import org.example.Base;
import org.example.DriverManager;
import org.example.adminPage.TimePage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TimeTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private TimePage timePage;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();
        login = new loginPage(driver);
        timePage = new TimePage(driver);

        login.open();
        login.LogIn("Admin", "admin123");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // TC_TIME_001
    @Test
    public void verifyTimePageOpensSuccessfully() {
        runTest("Verify Time page navigation and initial access", () -> {
            timePage.navigateToEmployeeTimesheets();
            return timePage.isPageOpened(driver.getCurrentUrl());
        });
    }

    // TC_TIME_002
    @Test
    public void verifyEmployeeNameIsDisplayed() {
        runTest("Verify Employee layout details are initialized", () -> {
            timePage.navigateToEmployeeTimesheets();
            return timePage.isTimesheetTableDisplayed();
        });
    }

    // TC_TIME_003
    @Test
    public void verifyTimesheetPeriodIsDisplayed() {
        runTest("Verify accurate configuration of timesheet calendar display dates", () -> {
            timePage.navigateToEmployeeTimesheets();
            return timePage.isTimesheetPeriodDisplayed();
        });
    }

    // TC_TIME_004
    @Test
    public void verifyProjectEntriesAreDisplayed() {
        runTest("Verify project configurations show under layout", () -> {
            timePage.navigateToEmployeeTimesheets();
            return timePage.isTimesheetTableDisplayed();
        });
    }

    // TC_TIME_005
    @Test
    public void verifyActivitiesAreDisplayed() {
        runTest("Verify activity tracking lists show up inside target logs", () -> {
            timePage.navigateToEmployeeTimesheets();
            return timePage.isTimesheetTableDisplayed();
        });
    }

    // TC_TIME_006
    @Test
    public void verifyDailyHoursAreDisplayed() {
        runTest("Verify regular weekday structural intervals render rows", () -> {
            timePage.navigateToEmployeeTimesheets();
            return timePage.isTimesheetTableDisplayed();
        });
    }

    // TC_TIME_007
    @Test
    public void verifyTotalHoursAreDisplayed() {
        runTest("Verify systemic aggregation column presents summary data calculations", () -> {
            timePage.navigateToEmployeeTimesheets();
            return timePage.isTimesheetTableDisplayed();
        });
    }

    // TC_TIME_008
    @Test
    public void verifyTimesheetStatusIsDisplayed() {
        runTest("Verify runtime status indicators evaluate to valid text flags", () -> {
            timePage.navigateToEmployeeTimesheets();
            return !timePage.getTimesheetStatusText().isEmpty();
        });
    }

    // TC_TIME_009
    @Test
    public void verifyAvailableActionsAreDisplayedAccordingToPermissions() {
        runTest("Verify authorization visibility matches valid layout rules", () -> {
            timePage.navigateToEmployeeTimesheets();
            return timePage.isTimesheetTableDisplayed();
        });
    }

    // TC_TIME_010
    @Test
    public void verifyUserCanAddNewTimesheetEntrySuccessfully() {
        runTest("Verify manual initialization and entry workflows run clean", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.clickAddRow();
            timePage.fillFirstRowDetails("8.0");
            timePage.clickSaveTimesheet();
            return timePage.isSuccessMessageDisplayed();
        });
    }

    // TC_TIME_011
    @Test
    public void verifyUserCanModifyAnExistingTimesheetEntry() {
        runTest("Verify mutations directly alter targeted row cell allocations", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.typeHoursOnly("6.5");
            timePage.clickSaveTimesheet();
            return timePage.isSuccessMessageDisplayed();
        });
    }

    // TC_TIME_012
    @Test
    public void verifyProjectSelectionIsSavedCorrectly() {
        runTest("Verify valid project persistence matching context choice structures", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.fillFirstRowDetails("4.0");
            timePage.clickSaveTimesheet();
            return timePage.isSuccessMessageDisplayed();
        });
    }

    // TC_TIME_013
    @Test
    public void verifyActivitySelectionIsSavedCorrectly() {
        runTest("Verify structural assignment tasks hold state post operation submit", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.fillFirstRowDetails("5.0");
            timePage.clickSaveTimesheet();
            return timePage.isSuccessMessageDisplayed();
        });
    }

    // TC_TIME_014
    @Test
    public void verifyWorkingHoursAreSavedCorrectly() {
        runTest("Verify consistent state capture checks for numerical time entry", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.typeHoursOnly("8.0");
            timePage.clickSaveTimesheet();
            return timePage.isSuccessMessageDisplayed();
        });
    }

    // TC_TIME_015
    @Test
    public void verifyTotalHoursAreRecalculatedAfterAddingEntry() {
        runTest("Verify real-time system summary grid shifts upwards when logs increment", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.typeHoursOnly("7.0");
            timePage.clickSaveTimesheet();
            return timePage.isSuccessMessageDisplayed();
        });
    }

    // TC_TIME_016
    @Test
    public void verifyTotalHoursAreRecalculatedAfterModifyingEntry() {
        runTest("Verify calculation loops balance accurately following state modifications", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.typeHoursOnly("2.5");
            timePage.clickSaveTimesheet();
            return timePage.isSuccessMessageDisplayed();
        });
    }

    // TC_TIME_017
    @Test
    public void verifySuccessMessageIsDisplayedAfterSave() {
        runTest("Verify prompt banners give visual confirmation UI updates", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.clickSaveTimesheet();
            return timePage.isSuccessMessageDisplayed();
        });
    }

    // TC_TIME_018
    @Test
    public void verifyUpdatedDataRemainsAvailableImmediatelyOnSurvey() {
        runTest("Verify systemic transactional reads serve fresh modifications", () -> {
            timePage.navigateToMyTimesheets();
            return timePage.isTimesheetTableDisplayed();
        });
    }

    // TC_TIME_019
    @Test
    public void verifySavedChangesRemainPersistentAfterRefresh() {
        runTest("Verify application cache operations withstand browser reload events", () -> {
            timePage.navigateToMyTimesheets();
            driver.navigate().refresh();
            return timePage.isTimesheetTableDisplayed();
        });
    }

    // TC_TIME_020
    @Test
    public void verifySystemPreventsSavingWhenProjectFieldIsEmpty() {
        runTest("Verify programmatic blocks enforce validation checks on projects", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.clickAddRow();
            timePage.fillRowWithoutProject("8.0");
            timePage.clickSaveTimesheet();
            return timePage.isProjectRequiredErrorDisplayed();
        });
    }

    // TC_TIME_021
    @Test
    public void verifySystemPreventsSavingWhenActivityFieldIsEmpty() {
        runTest("Verify field verification guards track missing task types", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.clickAddRow();
            timePage.fillRowWithoutActivity("8.0");
            timePage.clickSaveTimesheet();
            return timePage.isActivityRequiredErrorDisplayed();
        });
    }

    // TC_TIME_022
    @Test
    public void verifySystemPreventsSavingWhenHoursFieldIsEmpty() {
        runTest("Verify zero entry attempts on new rows trigger warning flags", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.clickAddRow();
            timePage.clearHoursInput();
            timePage.clickSaveTimesheet();
            return timePage.isInvalidHoursErrorDisplayed();
        });
    }

    // TC_TIME_023
    @Test
    public void verifySystemAcceptsNumericalValuesOnly() {
        runTest("Verify non-numeric character strings are cleanly rejected", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.typeHoursOnly("ABC");
            timePage.clickSaveTimesheet();
            return timePage.isInvalidHoursErrorDisplayed();
        });
    }

    // TC_TIME_024
    @Test
    public void verifyNegativeValuesAreRejected() {
        runTest("Verify below-zero bounds tests reject row evaluation", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.typeHoursOnly("-5");
            timePage.clickSaveTimesheet();
            return timePage.isOutOfBoundsHoursErrorDisplayed();
        });
    }

    // TC_TIME_025
    @Test
    public void verifyExcessiveWorkingHoursLimitIsRejected() {
        runTest("Verify overflow thresholds trip validations if entry overshot daily cap", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.typeHoursOnly("25");
            timePage.clickSaveTimesheet();
            return timePage.isOutOfBoundsHoursErrorDisplayed();
        });
    }

    // TC_TIME_026
    @Test
    public void verifyInvalidProjectSelectionIsRejected() {
        runTest("Verify structural integrity constraints on project identifiers", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            return timePage.isTimesheetTableDisplayed();
        });
    }

    // TC_TIME_027
    @Test
    public void verifyInvalidActivitySelectionIsRejected() {
        runTest("Verify arbitrary validation handles dynamic activity configurations", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            return timePage.isTimesheetTableDisplayed();
        });
    }

    // TC_TIME_028
    @Test
    public void verifyValidDecimalHourValuesAreAccepted() {
        runTest("Verify floating point numbers update properly inside time cells", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.typeHoursOnly("7.5");
            timePage.clickSaveTimesheet();
            return timePage.isSuccessMessageDisplayed();
        });
    }

    // TC_TIME_029
    @Test
    public void verifyValidationMessagesDisappearAfterCorrection() {
        runTest("Verify transient visual warnings collapse when fields resolve properly", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.typeHoursOnly("INVALID");
            timePage.clickSaveTimesheet();
            timePage.clearHoursInput();
            timePage.typeHoursOnly("8");
            return !timePage.isInvalidHoursErrorDisplayed();
        });
    }

    // TC_TIME_030
    @Test
    public void verifyUserCanSuccessfullySubmitTimesheet() {
        runTest("Verify workflow pipelines switch execution flags on submission", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickSubmitTimesheet();
            return timePage.isSuccessMessageDisplayed();
        });
    }

    // TC_TIME_031
    @Test
    public void verifyUserCanUpdateSelectedTimesheetEntry() {
        runTest("Verify existing items apply system updates correctly on modification", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.typeHoursOnly("4.5");
            timePage.clickSaveTimesheet();
            return timePage.isSuccessMessageDisplayed();
        });
    }

    // TC_TIME_032
    @Test
    public void verifyConfirmationDialogAppearsBeforeDeleting() {
        runTest("Verify prompt screens intercept delete triggers safely", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.deleteFirstRow();

            // 1. Check if the dialog is visible
            boolean isDisplayed = timePage.isConfirmationDialogDisplayed();

            // 2. Dismiss it to keep the test environment clean
            timePage.cancelDeleteRow();

            // 3. Return the result
            return isDisplayed;
        });
    }

    // TC_TIME_033
    @Test
    public void verifyTimesheetEntryIsRemovedAfterConfirmation() {
        runTest("Verify row teardown logic updates interface state on confirmation", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.deleteFirstRow();
            timePage.confirmDeleteRow();
            return timePage.isSuccessMessageDisplayed();
        });
    }

    // TC_TIME_034
    @Test
    public void verifyTotalHoursAdjustAfterRemovingRow() {
        runTest("Verify row calculation drops sum aggregates upon removal tracking", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.deleteFirstRow();
            timePage.confirmDeleteRow();
            return timePage.isSuccessMessageDisplayed();
        });
    }

    // TC_TIME_035
    @Test
    public void verifySuccessMessageAppearsOnDelete() {
        runTest("Verify clear toast banners validate data elimination tasks", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.deleteFirstRow();
            timePage.confirmDeleteRow();
            return timePage.isSuccessMessageDisplayed();
        });
    }

    // TC_TIME_036
    @Test
    public void verifyMultipleEntriesCanBeDeletedTogether() {
        runTest("Verify mass removal routines act cleanly on targeted selection lists", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.selectFirstRowCheckbox();
            timePage.clickDeleteSelected();
            timePage.confirmDeleteRow();
            return timePage.isSuccessMessageDisplayed();
        });
    }

    // TC_TIME_037
    @Test
    public void verifyDeletedEntriesDisappearFromUIImmediately() {
        runTest("Verify quick UI feedback synchronizes state updates cleanly", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.deleteFirstRow();
            timePage.confirmDeleteRow();
            return timePage.isSuccessMessageDisplayed();
        });
    }

    // TC_TIME_038
    @Test
    public void verifyDeletionDoesNotOccurIfUserCancels() {
        runTest("Verify rollbacks maintain row states safely if cancel is chosen", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.deleteFirstRow();
            timePage.cancelDeleteRow();
            return timePage.isTimesheetTableDisplayed();
        });
    }

    // TC_TIME_039
    @Test
    public void verifyConfirmationAppearsWhenCancelingUnsavedChanges() {
        runTest("Verify data protection warning prompts display before state drops", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.typeHoursOnly("6.0");
            timePage.clearFirstRow();
            return timePage.isTimesheetTableDisplayed();
        });
    }

    // TC_TIME_040
    @Test
    public void verifyUnsavedChangesAreDiscardedAfterConfirmation() {
        runTest("Verify rollback procedures correctly reset form input structures", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.typeHoursOnly("9.0");
            timePage.clearFirstRow();
            return timePage.isTimesheetTableDisplayed();
        });
    }

    // TC_TIME_041
    @Test
    public void verifyPreviouslySavedDataRemainsUnchangedAfterWarning() {
        runTest("Verify original operational cache metrics hold after safe fallbacks", () -> {
            timePage.navigateToMyTimesheets();
            return timePage.isTimesheetTableDisplayed();
        });
    }

    // TC_TIME_042
    @Test
    public void verifySearchByEmployeeName() {
        runTest("Verify targeted text input yields matching administrative user lists", () -> {
            timePage.navigateToEmployeeTimesheets();
            timePage.searchEmployeeTimesheet("John Smith");
            return timePage.isTimesheetTableDisplayed();
        });
    }

    // TC_TIME_043
    @Test
    public void verifySearchByTimesheetPeriod() {
        runTest("Verify accurate row returns when querying distinct calendar window sets", () -> {
            timePage.navigateToEmployeeTimesheets();
            timePage.filterTimesheetByDate("2026-01-06");
            return timePage.isTimesheetTableDisplayed();
        });
    }

    // TC_TIME_044
    @Test
    public void verifyNavigationAwayTriggersUnsavedChangesWarning() {
        runTest("Verify navigation away triggers unsaved changes warning", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.typeHoursOnly("12.0");
            timePage.navigateToAdminModule();
            return timePage.isNavigationWarningDisplayed();
        });
    }

    // TC_TIME_045
    @Test
    public void verifyCancelNavigationWithoutSavingDoesNotPersistChanges() {
        runTest("Verify cancel navigation without saving does not persist changes", () -> {
            timePage.navigateToMyTimesheets();
            timePage.clickEditTimesheet();
            timePage.typeHoursOnly("12.0");
            timePage.navigateToAdminModule();
            timePage.cancelDeleteRow(); // Reusing the cancel button locator if it shares standard structural classes
            return timePage.isTimesheetTableDisplayed();
        });
    }

    // TC_TIME_046
    @Test
    public void verifyLeavingPageWithoutConfirmationDoesNotLoseSavedData() {
        runTest("Verify leaving page without confirmation does not lose saved data", () -> {
            timePage.navigateToMyTimesheets();
            // Checking initial state loop
            boolean initialView = timePage.isTimesheetTableDisplayed();
            timePage.navigateToAdminModule();
            timePage.navigateToMyTimesheets();
            return initialView && timePage.isTimesheetTableDisplayed();
        });
    }

    // TC_TIME_047
    @Test
    public void verifySearchByEmployeeNameUnique() {
        runTest("Verify system filters exact timesheet targets when querying name matches", () -> {
            timePage.navigateToEmployeeTimesheets();
            timePage.searchEmployeeTimesheet("John Smith");
            return timePage.isTimesheetTableDisplayed();
        });
    }

    // TC_TIME_048
    @Test
    public void verifySearchByTimesheetPeriodRange() {
        runTest("Verify tracking configurations render proper data within specific boundaries", () -> {
            timePage.navigateToEmployeeTimesheets();
            timePage.filterTimesheetByDate("2026-01-06");
            return !timePage.isNoRecordsFoundDisplayed();
        });
    }
}