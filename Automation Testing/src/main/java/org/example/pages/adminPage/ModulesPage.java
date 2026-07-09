package org.example.pages.adminPage;

import bots.ActionsBot;
import org.example.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class ModulesPage extends Base {

    // Navigation
    private final By adminMenu = By.xpath("//span[text()='Admin']");
    private final By configurationMenu = By.xpath("//span[text()='Configuration ']");
    private final By modulesOption = By.xpath("//a[text()='Modules']");

    // Page Header / Breadcrumb
    private final By pageTitle =
            By.xpath("//h6[contains(@class,'oxd-topbar-header-breadcrumb-module')]");
    private final By breadcrumb =
            By.xpath("//nav[contains(@class,'oxd-topbar-header-breadcrumb')]");

    // Module list
    private final By moduleRows =
            By.className("orangehrm-full-width-grid");
    private final By moduleNameCells =
            By.xpath("//div[@role='table']//div[@role='row']//div[1]");
    private final By moduleDescriptionCells =
            By.xpath("//div[@role='table']//div[@role='row']//div[2]");

    // Toggle (Enable / Disable) — generic first toggle
    private final By firstModuleToggle =
            By.xpath("(//span[contains(@class,'oxd-switch-input')])[1]");
    // Save / Cancel
    private final By saveButton = By.xpath("//button[@type='submit']");
    private final By cancelButton = By.xpath("//button[normalize-space()='Cancel']");
    // Validation / Notifications
    private final By successToast =
            By.xpath("//p[contains(text(),'Successfully')]");
    private final By warningMessage =
            By.xpath("//p[contains(@class,'oxd-toast--warn')] | //div[contains(@class,'oxd-alert--error')]");
    private WebDriver driver;
    private ActionsBot actions;

    public ModulesPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    // Specific module toggles by module name
    private By moduleToggleByName(String moduleName) {
        return By.xpath("//p[text()='" + moduleName + "']/ancestor::div[@role='row']"
                + "//span[contains(@class,'oxd-switch-input')]");
    }

    // Navigation menu items (used to verify module visibility for users)
    private By navMenuItemByText(String label) {
        return By.xpath("//span[text()='" + label + "']");
    }

    // ─── Navigation ─────────────────────────────────────────────────────────────

    public ModulesPage openModulesPage() {
        actions.click(adminMenu);
        actions.click(configurationMenu);
        actions.click(modulesOption);
        return this;
    }

    public boolean pageOpened(String url) {
        return actions.inTargetPage(url);
    }

    // ─── Page Assertions ─────────────────────────────────────────────────────────

    public boolean isModulesTableDisplayed() {
        try {
            sleep(3000);
            return !driver.findElements(moduleRows).isEmpty();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isSaveButtonDisplayed() {
        return actions.isDisplayed(saveButton);
    }

    public boolean isPageTitleDisplayed() {
        return actions.isDisplayed(pageTitle);
    }

    public boolean isBreadcrumbCorrect() {
        return actions.isDisplayed(breadcrumb);
    }

    public boolean areToggleControlsVisible() {
        return actions.isDisplayed(firstModuleToggle);
    }

    public boolean areModuleDescriptionsVisible() {
        return actions.isDisplayed(moduleDescriptionCells);
    }

    // ─── Enable / Disable (specific module by name) ──────────────────────────────

    public void enableModule(String moduleName) {
        actions.click(moduleToggleByName(moduleName));
        actions.click(saveButton);
    }

    public void disableModule(String moduleName) {
        actions.click(moduleToggleByName(moduleName));
        actions.click(saveButton);
    }

    // ─── Enable / Disable (first toggle — generic) ───────────────────────────────

    public void toggleFirstModule() {
        actions.click(firstModuleToggle);
    }

    // ─── Save ────────────────────────────────────────────────────────────────────

    public void clickSave() {
        actions.click(saveButton);
    }

    /**
     * Save without making any changes first
     */
    public void saveWithoutChanges() {
        actions.click(saveButton);
    }

    // ─── Cancel ──────────────────────────────────────────────────────────────────

    public void toggleFirstModuleThenCancel() {
        actions.click(firstModuleToggle);
        actions.click(cancelButton);
    }

    // ─── Multiple toggles then Save ──────────────────────────────────────────────

    public void toggleModulesAndSave(String... moduleNames) {
        for (String name : moduleNames) {
            actions.click(moduleToggleByName(name));
        }
        actions.click(saveButton);
    }

    // ─── Cycle a module through multiple states ───────────────────────────────────

    public void cycleModuleState(String moduleName) {
        // Disable
        actions.click(moduleToggleByName(moduleName));
        actions.click(saveButton);
        // Re-enable
        actions.click(moduleToggleByName(moduleName));
        actions.click(saveButton);
        // Disable again
        actions.click(moduleToggleByName(moduleName));
        actions.click(saveButton);
    }

    // ─── Navigation menu visibility (for cross-role checks) ─────────────────────

    public boolean isModuleVisibleInNavMenu(String menuLabel) {
        return actions.isDisplayed(navMenuItemByText(menuLabel));
    }

    // ─── Success / Warning messages ───────────────────────────────────────────────

    public boolean isSuccessMessageDisplayed() {
        return actions.isDisplayed(successToast);
    }

    public String getSuccessMessage() {
        return actions.getTextWhenVisible(successToast);
    }

    public boolean isWarningMessageDisplayed() {
        return actions.isDisplayed(warningMessage);
    }

    // ─── Expected modules list ────────────────────────────────────────────────────

    public boolean areExpectedModulesListed(String... expectedModules) {
        for (String module : expectedModules) {
            By locator = By.xpath("//p[text()='" + module + "']");
            if (!actions.isDisplayed(locator)) {
                return false;
            }
        }
        return true;
    }
}