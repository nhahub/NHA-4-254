package org.example.pages.PIM;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class OptionalFieldsPage extends Base {

    // ─── Navigation ──────────────────────────────────────────────────────────────
    private final By pimMenu = By.xpath("//span[text()='PIM']");
    private final By configurationTab = By.className("oxd-topbar-body-nav-tab");
    private final By optionalFieldsOption = By.xpath("//a[text()='Optional Fields']");

    // ─── Page Header ─────────────────────────────────────────────────────────────
    private final By pageHeader =
            By.xpath("//h6[contains(@class,'oxd-text--h6')]");

    // ─── Deprecated Fields Section ────────────────────────────────────────────────
    private final By deprecatedFieldsSection =
            By.xpath("//p[contains(text(),'Deprecated Fields')]/ancestor::div[contains(@class,'oxd-sheet')]");
    private final By showNickNameToggle =
            By.xpath("//p[text()='Show Nick Name']/following::span[contains(@class,'oxd-switch-input')][1]");
    private final By showSmokerToggle =
            By.xpath("//p[text()='Show Smoker']/following::span[contains(@class,'oxd-switch-input')][1]");
    private final By showMilitaryServiceToggle =
            By.xpath("//p[text()='Show Military Service']/following::span[contains(@class,'oxd-switch-input')][1]");

    // ─── Country Specific Information Section ─────────────────────────────────────
    private final By countrySpecificSection =
            By.xpath("//p[contains(text(),'Country Specific Information')]/ancestor::div[contains(@class,'oxd-sheet')]");
    // ─── Toasts / Messages ────────────────────────────────────────────────────────
    private final By successToast =
            By.xpath("//p[contains(text(),'Successfully')]");
    private WebDriver driver;
    private ActionsBot actions;

    public OptionalFieldsPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    // ─── Generic toggle locator by label text ─────────────────────────────────────
    private By toggleByLabel(String labelText) {
        return By.xpath("//p[text()='" + labelText + "']/following::span[contains(@class,'oxd-switch-input')][1]");
    }

    // ─── Navigation ──────────────────────────────────────────────────────────────

    public OptionalFieldsPage openConfigurationMenu() {
        actions.click(pimMenu);
        actions.click(configurationTab);
        return this;
    }

    public boolean isOptionalFieldsOptionDisplayed() {
        openConfigurationMenu();
        return actions.isDisplayed(optionalFieldsOption);
    }

    public OptionalFieldsPage openOptionalFieldsPage() {
        openConfigurationMenu();
        actions.click(optionalFieldsOption);
        return this;
    }

    public boolean pageOpened(String url) {
        return actions.inTargetPage(url);
    }

    public boolean isPageLoaded() {
        try {
            sleep(2000);
            return actions.isDisplayed(pageHeader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ─── Deprecated Fields Section ────────────────────────────────────────────────

    public boolean isDeprecatedFieldsSectionDisplayed() {
        return actions.isDisplayed(deprecatedFieldsSection);
    }

    public boolean areDeprecatedFieldsToggleOptionsDisplayed() {
        return actions.isDisplayed(showNickNameToggle)
                && actions.isDisplayed(showSmokerToggle)
                && actions.isDisplayed(showMilitaryServiceToggle);
    }

    // ─── Country Specific Information Section ─────────────────────────────────────

    public boolean isCountrySpecificSectionDisplayed() {
        return actions.isDisplayed(countrySpecificSection);
    }

    // ─── Toggle Actions ───────────────────────────────────────────────────────────

    public void toggleShowNickName() {
        actions.click(showNickNameToggle);
    }

    public void toggleShowSmoker() {
        actions.click(showSmokerToggle);
    }

    public void toggleShowMilitaryService() {
        actions.click(showMilitaryServiceToggle);
    }

    public void toggleByLabelText(String labelText) {
        actions.click(toggleByLabel(labelText));
    }

    public boolean isToggleEnabled(By toggleLocator) {
        try {
            String state = driver.findElement(toggleLocator).getAttribute("class");
            return state != null && state.contains("oxd-switch-input--active");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isShowNickNameEnabled() {
        return isToggleEnabled(showNickNameToggle);
    }

    public boolean isShowSmokerEnabled() {
        return isToggleEnabled(showSmokerToggle);
    }

    public boolean isShowMilitaryServiceEnabled() {
        return isToggleEnabled(showMilitaryServiceToggle);
    }

    // ─── Toasts / Messages ────────────────────────────────────────────────────────

    public boolean isSuccessMessageDisplayed() {
        return actions.isDisplayed(successToast);
    }

    public String getSuccessMessage() {
        return actions.getTextWhenVisible(successToast);
    }
}