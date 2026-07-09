package org.example.pages.adminPage;

import bots.ActionsBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserManagementPage {

    private final WebDriver driver;
    private final ActionsBot actions;

    // Username
    private final By userNameInput =
            By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");

    // User Role Dropdown
    private final By userRoleDropdown =
            By.xpath("(//div[contains(@class,'oxd-select-text')])[1]");

    // Status Dropdown
    private final By statusDropdown =
            By.xpath("(//div[contains(@class,'oxd-select-text')])[2]");

    private final By searchBtn =
            By.xpath("//button[@type='submit']");

    private final By resetBtn =
            By.xpath("//button[normalize-space()='Reset']");

    public UserManagementPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ActionsBot(driver);
    }

    public UserManagementPage searchByUsername(String username) {
        actions.type(userNameInput, username);
        return this;
    }

    public UserManagementPage selectUserRole(String role) {

        actions.click(userRoleDropdown);

        By option = By.xpath(
                "//div[@role='listbox']//span[text()='" + role + "']"
        );

        actions.click(option);

        return this;
    }

    public UserManagementPage selectStatus(String status) {

        actions.click(statusDropdown);

        By option = By.xpath(
                "//div[@role='listbox']//span[text()='" + status + "']"
        );

        actions.click(option);

        return this;
    }

    public UserManagementPage clickSearch() {
        actions.click(searchBtn);
        return this;
    }

    public UserManagementPage resetSearch() {
        actions.click(resetBtn);
        return this;
    }

    public boolean isUserFound(String username) {

        By result =
                By.xpath("//div[@role='row']//div[text()='" + username + "']");

        return driver.findElements(result).size() > 0;
    }
}