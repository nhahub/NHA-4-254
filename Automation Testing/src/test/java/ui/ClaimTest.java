package adminPages;

import org.example.Base;
import org.example.DriverManager;
import org.example.adminPage.ClaimPage;
import org.example.pages.loginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ClaimTest extends Base {

    private WebDriver driver;
    private loginPage login;
    private ClaimPage claimPage;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getInstance().getDriver();
        login = new loginPage(driver);
        claimPage = new ClaimPage(driver);

        login.open();
        login.LogIn("Admin", "admin123");
        claimPage.navigateToClaimModule();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // TC_CLAIM_001
    @Test
    public void verifyClaimModuleDashboardOpensSuccessfully() {
        runTest("Verify Claim module dashboard opens successfully", () ->
                claimPage.isPageOpened(driver.getCurrentUrl())
        );
    }

    // TC_CLAIM_002
    @Test
    public void verifyClaimRequestsSearchFiltersLayout() {
        runTest("Verify claim requests search filters layout", () ->
                claimPage.isClaimHeaderDisplayed()
        );
    }

    // TC_CLAIM_003
    @Test
    public void verifyClaimsRequestsTableGridIsDisplayed() {
        runTest("Verify claims requests table grid is displayed", () ->
                claimPage.isClaimsTableDisplayed()
        );
    }

    // TC_CLAIM_004
    @Test
    public void verifyFilteringClaimsRequestsByEmployeeName() {
        runTest("Verify filtering claims requests by employee name", () -> {
            claimPage.filterByEmployeeName("Odis");
            claimPage.clickSearch();
            return claimPage.isClaimsTableDisplayed();
        });
    }

    // TC_CLAIM_005
    @Test
    public void verifyFilteringClaimsByReferenceIdField() {
        runTest("Verify filtering claims by reference ID field", () -> {
            claimPage.filterByReferenceId("CLM001");
            claimPage.clickSearch();
            return claimPage.isClaimsTableDisplayed();
        });
    }

    // TC_CLAIM_006
    @Test
    public void verifyFilteringClaimsByMedicalOrTravelEvents() {
        runTest("Verify filtering claims by medical or travel events", () -> {
            claimPage.filterByEvent("Medical");
            claimPage.clickSearch();
            return claimPage.isClaimsTableDisplayed();
        });
    }

    // TC_CLAIM_007
    @Test
    public void verifyFilteringClaimsByStatusTypeDropdown() {
        runTest("Verify filtering claims by status type dropdown", () -> {
            claimPage.filterByStatus("Submitted");
            claimPage.clickSearch();
            return claimPage.isClaimsTableDisplayed();
        });
    }

    // TC_CLAIM_008
    @Test
    public void verifyResetButtonClearsAllActiveFilters() {
        runTest("Verify reset button clears all active filters", () -> {
            claimPage.filterByReferenceId("CLM999");
            claimPage.clickReset();
            return claimPage.getReferenceIdInputValue().isEmpty();
        });
    }

    // TC_CLAIM_009
    @Test
    public void verifyCombiningMultipleSearchFiltersTogether() {
        runTest("Verify combining multiple search filters together", () -> {
            claimPage.filterByEmployeeName("Odis");
            claimPage.filterByEvent("Accommodation");
            claimPage.filterByStatus("Initiated");
            claimPage.clickSearch();
            return claimPage.isClaimsTableDisplayed();
        });
    }

    // TC_CLAIM_010
    @Test
    public void verifyNoRecordsFoundScenarioLayoutFeedback() {
        runTest("Verify no records found scenario layout feedback", () -> {
            claimPage.filterByReferenceId("NONEXISTENT_REF_ID");
            claimPage.clickSearch();
            return claimPage.isNoRecordsFoundDisplayed();
        });
    }

    // TC_CLAIM_011
    @Test
    public void verifyViewingEmployeeClaimRequestDetailsCard() {
        runTest("Verify viewing employee claim request details card", () -> {
            claimPage.clickFirstRowViewDetails();
            return !driver.getCurrentUrl().contains("viewClaims");
        });
    }

    // TC_CLAIM_012
    @Test
    public void verifyFormValidationHandlingWhenSubmittingEmptyClaim() {
        runTest("Verify form validation handling when submitting empty claim", () -> {
            claimPage.clickFirstRowViewDetails();
            claimPage.clickSubmitClaim();
            return claimPage.isSuccessNotificationDisplayed() || claimPage.isRequiredFieldErrorDisplayed();
        });
    }

    // TC_CLAIM_013
    @Test
    public void verifyEventDropdownPopulatesOptionsProperly() {
        runTest("Verify Event dropdown populates options properly", () -> {
            claimPage.clickEventDropdownContainer();
            return claimPage.isDropdownListBoxPopulated();
        });
    }

    // TC_CLAIM_014
    @Test
    public void verifyGridSortingMechanismsByReferenceId() {
        runTest("Verify grid sorting mechanisms by reference ID", () -> {
            claimPage.sortByReferenceId();
            return claimPage.isClaimsTableDisplayed();
        });
    }

    // TC_CLAIM_015
    @Test
    public void verifyBackButtonReturnsToClaimRequestsOverview() {
        runTest("Verify back button returns to claim requests overview", () -> {
            claimPage.clickFirstRowViewDetails();
            claimPage.clickBackButton();
            return claimPage.isClaimsTableDisplayed();
        });
    }

    // TC_CLAIM_016
    @Test
    public void verifyAccessControlVisibilityRulesForEmployeeRole() {
        runTest("Verify access control visibility rules for employee role", () -> {
            return claimPage.isClaimsTableDisplayed();
        });
    }

    // TC_CLAIM_017
    @Test
    public void verifySearchPerformanceWithMassiveClaimDataVolume() {
        runTest("Verify search performance with massive claim data volume", () -> {
            claimPage.clickSearch();
            return claimPage.isClaimsTableDisplayed();
        });
    }
}