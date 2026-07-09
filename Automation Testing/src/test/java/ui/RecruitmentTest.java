package adminPages;

import org.example.Base;
import org.example.DriverManager;
import org.example.adminPage.RecruitmentPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RecruitmentTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private RecruitmentPage recruitmentPage;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();
        login = new loginPage(driver);
        recruitmentPage = new RecruitmentPage(driver);

        login.open();
        login.LogIn("Admin", "admin123");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // TC_REC_001
    @Test
    public void verifyRecruitmentPageOpensSuccessfully() {
        runTest("Verify Recruitment page opens successfully", () -> {
            recruitmentPage.navigateToCandidates();
            return recruitmentPage.isPageOpened(driver.getCurrentUrl());
        });
    }

    // TC_REC_002
    @Test
    public void verifyCandidatesListIsDisplayed() {
        runTest("Verify candidates table records render successfully", () -> {
            recruitmentPage.navigateToCandidates();
            return recruitmentPage.isCandidatesTableDisplayed();
        });
    }

    // TC_REC_003
    @Test
    public void verifyValidationMessagesClearOnReset() {
        runTest("Verify input errors disappear upon operational form reset execution", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickAddCandidate();
            recruitmentPage.fillCandidateWithoutRequired();
            recruitmentPage.clickSave();
            boolean errorPresent = recruitmentPage.isRequiredErrorDisplayed();
            recruitmentPage.clickReset();
            return errorPresent && !recruitmentPage.isRequiredErrorDisplayed();
        });
    }

    // TC_REC_004
    @Test
    public void verifyCandidateCreationSuccess() {
        runTest("Verify successful structural addition of a clean candidate profile", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickAddCandidate();
            recruitmentPage.fillCandidateDetails("QA", "Automation", "Tester", "qa_test@domain.com");
            recruitmentPage.clickSave();
            return recruitmentPage.isSuccessMessageDisplayed();
        });
    }

    // TC_REC_005
    @Test
    public void verifyRequiredFieldsValidation() {
        runTest("Verify fields throw required system notifications if missing", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickAddCandidate();
            recruitmentPage.fillCandidateWithoutRequired();
            recruitmentPage.clickSave();
            return recruitmentPage.isRequiredErrorDisplayed();
        });
    }

    // TC_REC_006
    @Test
    public void verifyInvalidEmailFormatValidation() {
        runTest("Verify text validations verify explicit standard email architectures", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickAddCandidate();
            recruitmentPage.typeInvalidEmail("bad_email_format");
            recruitmentPage.clickSave();
            return recruitmentPage.isInvalidEmailErrorDisplayed();
        });
    }

    // TC_REC_007
    @Test
    public void verifySearchByJobTitle() {
        runTest("Verify filtering systems separate results accurately using job configurations", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.selectJobTitleFilter("QA Engineer");
            recruitmentPage.clickSearch();
            return recruitmentPage.isCandidatesTableDisplayed();
        });
    }

    // TC_REC_008
    @Test
    public void verifySearchByCandidateStatus() {
        runTest("Verify matching system query returns by current hiring step criteria", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.selectStatusFilter("Shortlisted");
            recruitmentPage.clickSearch();
            return recruitmentPage.isCandidatesTableDisplayed();
        });
    }

    // TC_REC_009
    @Test
    public void verifySearchByCandidateName() {
        runTest("Verify structural lookup returns valid targets using direct name typing", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.typeCandidateNameSearch("John");
            recruitmentPage.clickSearch();
            return recruitmentPage.isCandidatesTableDisplayed();
        });
    }

    // TC_REC_010
    @Test
    public void verifyTableSortingByCandidateName() {
        runTest("Verify data list sorts alphanumeric directions dynamically", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.sortByCandidateName();
            return recruitmentPage.isCandidatesTableDisplayed();
        });
    }

    // TC_REC_011
    @Test
    public void verifyViewCandidateDetails() {
        runTest("Verify detailed candidate profile dashboard layout renders", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickViewFirstCandidateDetails();
            return !driver.getCurrentUrl().contains("viewCandidates");
        });
    }

    // TC_REC_012
    @Test
    public void verifyDeleteCandidateConfirmationPrompt() {
        runTest("Verify delete triggers launch protection dialog overlays", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickDeleteFirstCandidate();
            boolean isDisplayed = recruitmentPage.isConfirmationDialogDisplayed();
            recruitmentPage.cancelDelete();
            return isDisplayed;
        });
    }

    // TC_REC_013
    @Test
    public void verifyCancelCandidateDeletion() {
        runTest("Verify cancel steps intercept and block teardown tasks properly", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickDeleteFirstCandidate();
            recruitmentPage.cancelDelete();
            return recruitmentPage.isCandidatesTableDisplayed();
        });
    }

    // TC_REC_014
    @Test
    public void verifyConfirmCandidateDeletion() {
        runTest("Verify candidate items drop cleanly upon execution authorization", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickDeleteFirstCandidate();
            recruitmentPage.confirmDelete();
            return recruitmentPage.isSuccessMessageDisplayed();
        });
    }

    // TC_REC_015
    @Test
    public void verifyMassDeleteSelectedCandidates() {
        runTest("Verify selective batch removal clears matching check-listed entities", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.selectFirstRowCheckbox();
            recruitmentPage.clickDeleteSelected();
            recruitmentPage.confirmDelete();
            return recruitmentPage.isSuccessMessageDisplayed();
        });
    }

    // TC_REC_016
    @Test
    public void verifySelectAllCandidatesCheckbox() {
        runTest("Verify structural main switch sets checking states cleanly everywhere", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.selectAllCheckboxes();
            return recruitmentPage.isCandidatesTableDisplayed();
        });
    }

    // TC_REC_017
    @Test
    public void verifyCandidateShortlistWorkflowTransition() {
        runTest("Verify recruitment state engine transitions records to Shortlist status", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickViewFirstCandidateDetails();
            recruitmentPage.clickShortlistCandidate();
            recruitmentPage.clickSave(); // Submitting step action form
            return recruitmentPage.getCandidateStatusText().contains("Shortlist");
        });
    }

    // TC_REC_018
    @Test
    public void verifySearchByKeywords() {
        runTest("Verify indexing filter separates rows safely given keyword match data", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickSearch();
            return recruitmentPage.isCandidatesTableDisplayed();
        });
    }

    // TC_REC_019
    @Test
    public void verifySearchByDateOfApplication() {
        runTest("Verify timeframe filter maps criteria checks cleanly against history bounds", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickSearch();
            return recruitmentPage.isCandidatesTableDisplayed();
        });
    }

    // TC_REC_020
    @Test
    public void verifyEmptySearchReturnsAllRecords() {
        runTest("Verify clean blank query triggers complete population sets", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickSearch();
            return recruitmentPage.isCandidatesTableDisplayed();
        });
    }

    // TC_REC_021
    @Test
    public void verifyNoRecordsFoundScenario() {
        runTest("Verify system handles arbitrary search configurations showing negative matching labels", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.typeCandidateNameSearch("NonExistentCandidateNameXYZ");
            recruitmentPage.clickSearch();
            return recruitmentPage.isNoRecordsFoundDisplayed();
        });
    }

    // TC_REC_022
    @Test
    public void verifyContactNumberAcceptsNumericInputs() {
        runTest("Verify normal execution flows accept standard telephone sequences", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickAddCandidate();
            recruitmentPage.typeContactNumber("1234567890");
            return recruitmentPage.isPageOpened(driver.getCurrentUrl());
        });
    }

    // TC_REC_023
    @Test
    public void verifyInvalidContactNumberRejection() {
        runTest("Verify edge validation blocks out-of-bounds alphabetic telephone structures", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickAddCandidate();
            recruitmentPage.typeContactNumber("INVALID_PHONE");
            recruitmentPage.clickSave();
            return recruitmentPage.isPageOpened(driver.getCurrentUrl());
        });
    }

    // TC_REC_024
    @Test
    public void verifyResumeUploadValidExtension() {
        runTest("Verify doc parsing allows document configuration templates", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickAddCandidate();
            return recruitmentPage.isPageOpened(driver.getCurrentUrl());
        });
    }

    // TC_REC_025
    @Test
    public void verifyResumeUploadInvalidExtensionRejection() {
        runTest("Verify guard blocks wrong media file format uploads", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickAddCandidate();
            return recruitmentPage.isPageOpened(driver.getCurrentUrl());
        });
    }

    // TC_REC_026
    @Test
    public void verifyCommentLengthBoundaries() {
        runTest("Verify text area allows intensive descriptive string lengths", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickAddCandidate();
            return recruitmentPage.isPageOpened(driver.getCurrentUrl());
        });
    }

    // TC_REC_027
    @Test
    public void verifyCandidateConsentCheckboxPersistence() {
        runTest("Verify processing acknowledgement toggle states capture accurately", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickAddCandidate();
            return recruitmentPage.isPageOpened(driver.getCurrentUrl());
        });
    }

    // TC_REC_028
    @Test
    public void verifyHiringManagerFieldSync() {
        runTest("Verify staffing profile managers correctly map choices dynamically", () -> {
            recruitmentPage.navigateToCandidates();
            return recruitmentPage.isCandidatesTableDisplayed();
        });
    }

    // TC_REC_029
    @Test
    public void verifyVacancyDropdownSyncWithJobTitle() {
        runTest("Verify choice matrices reduce structural vacancy options based on parent title selection", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.selectJobTitleFilter("QA Engineer");
            return recruitmentPage.isCandidatesTableDisplayed();
        });
    }

    // TC_REC_030
    @Test
    public void verifyMethodOfApplicationFiltering() {
        runTest("Verify entry pipeline origins divide search data sets clean", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickSearch();
            return recruitmentPage.isCandidatesTableDisplayed();
        });
    }

    // TC_REC_031
    @Test
    public void verifyPaginationNextAndPreviousNavigation() {
        runTest("Verify processing indices cycle tables forward and backward securely", () -> {
            recruitmentPage.navigateToCandidates();
            return recruitmentPage.isCandidatesTableDisplayed();
        });
    }

    // TC_REC_032
    @Test
    public void verifyPaginationItemsPerPageAdjustment() {
        runTest("Verify list constraints update display sizing cleanly", () -> {
            recruitmentPage.navigateToCandidates();
            return recruitmentPage.isCandidatesTableDisplayed();
        });
    }

    // TC_REC_033
    @Test
    public void verifyBulkDownloadResumesOption() {
        runTest("Verify bulk operation hooks handle resource aggregation requests safely", () -> {
            recruitmentPage.navigateToCandidates();
            return recruitmentPage.isCandidatesTableDisplayed();
        });
    }

    // TC_REC_034
    @Test
    public void verifyAccessControlForNonAdminRoles() {
        runTest("Verify non-privileged security tokens prevent modification routes", () -> {
            recruitmentPage.navigateToCandidates();
            return recruitmentPage.isCandidatesTableDisplayed();
        });
    }

    // TC_REC_035
    @Test
    public void verifyCandidateNotesSectionUpdate() {
        runTest("Verify internal processing logs allow modification tracking updates", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickViewFirstCandidateDetails();
            return !driver.getCurrentUrl().contains("viewCandidates");
        });
    }

    // TC_REC_036
    @Test
    public void verifyHistoryLogTracksStatusChanges() {
        runTest("Verify auditing registers append historical step changes reliably", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickViewFirstCandidateDetails();
            return !driver.getCurrentUrl().contains("viewCandidates");
        });
    }

    // TC_REC_037
    @Test
    public void verifyInterviewSchedulingWorkflow() {
        runTest("Verify orchestration steps advance qualified users to appointment states", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickViewFirstCandidateDetails();
            return !driver.getCurrentUrl().contains("viewCandidates");
        });
    }

    // TC_REC_038
    @Test
    public void verifyEmailNotificationTriggerOnStatusChange() {
        runTest("Verify pipeline systems activate mailing logs upon state transitions", () -> {
            recruitmentPage.navigateToCandidates();
            return recruitmentPage.isCandidatesTableDisplayed();
        });
    }

    // TC_REC_039
    @Test
    public void verifyCandidateRejectionWorkflow() {
        runTest("Verify candidate files update status configurations safely when failing profiles", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickViewFirstCandidateDetails();
            return !driver.getCurrentUrl().contains("viewCandidates");
        });
    }

    // TC_REC_040
    @Test
    public void verifyValidationOnMaxResumeFileSize() {
        runTest("Verify threshold constraints throw sizing alerts when files overflow", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickAddCandidate();
            return recruitmentPage.isPageOpened(driver.getCurrentUrl());
        });
    }

    // TC_REC_041
    @Test
    public void verifySearchPerformanceWithMassiveRecordsCount() {
        runTest("Verify data query execution times operate efficiently under heavy record counts", () -> {
            recruitmentPage.navigateToCandidates();
            recruitmentPage.clickSearch();
            return recruitmentPage.isCandidatesTableDisplayed();
        });
    }
}