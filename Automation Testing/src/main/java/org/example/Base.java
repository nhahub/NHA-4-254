package org.example;

import org.example.utils.NavbarUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.Callable;


public class Base {
    private static WebDriver driver;
    NavbarUtil nav = new NavbarUtil();

    public Base() {
        driver = DriverManager.getInstance().getDriver();
    }

    protected static void takeScreenshot(String name) {
        try {
            System.out.println("start screenshot");
            File bugDir = new File("bug report");
            if (!bugDir.exists()) bugDir.mkdirs();
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String safe = name.replaceAll("[^a-zA-Z0-9_-]", "_");
            Files.copy(src.toPath(), new File(bugDir, safe + "_" + System.currentTimeMillis() + ".png").toPath());
            System.out.println("  -> Screenshot saved to 'bug report' folder");
        } catch (Exception e) {
            System.out.println("  -> Screenshot failed: " + e.getMessage());
        }
    }

    public static void setDriver(WebDriver driver) {
        Base.driver = driver;
    }

    public void showTC(WebDriver driver, String tcId) {
        nav.show(driver, tcId);
    }

    protected void runTest(String id, Callable<Boolean> logic) {
//        displayBanner(id);
        showTC(driver, id);
        System.out.println("[Running] " + id);
//        Assert.assertTrue(result);

        try {
            boolean passed = logic.call();
            if (passed) {
                System.out.println("  -> PASSED: " + id);
            } else {
                System.out.println("  -> FAILED: " + id);
                takeScreenshot(id);
            }
        } catch (Exception e) {
            System.out.println("  -> ERROR in " + id + ": " + e.getMessage());
            takeScreenshot(id + "_ERROR");
        }
    }


}
