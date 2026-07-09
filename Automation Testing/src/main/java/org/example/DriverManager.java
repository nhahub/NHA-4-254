//package org.example;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.remote.RemoteWebDriver;
//
//import java.net.URL;
//
//public class DriverManager {
//    private static DriverManager instance;
//    private WebDriver driver;                              //creational pattern - singleton
//    private Process driverProcess;
//
//    private DriverManager() {
//        try {
//            System.setProperty("java.net.preferIPv4Stack", "true");
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--remote-allow-origins=*");
//            options.addArguments("--no-sandbox");
//            options.addArguments("--disable-dev-shm-usage");
//
/// /            String chromeDriverPath = System.getProperty("user.home") + "\\.cache\\selenium\\chromedriver\\win64\\147.0.7727.117\\chromedriver.exe";
//            String chromeDriverPath =
//                    System.getProperty("user.home")
//                            + "\\.cache\\selenium\\chromedriver\\win64\\149.x.x.x\\chromedriver.exe";
//            ProcessBuilder pb = new ProcessBuilder(chromeDriverPath, "--port=9515");
//            pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
//            pb.redirectError(ProcessBuilder.Redirect.INHERIT);
//            driverProcess = pb.start();
//
//            Runtime.getRuntime().addShutdownHook(new Thread(driverProcess::destroy));
//
//            Thread.sleep(2000);
//
//            driver = new RemoteWebDriver(new URL("http://localhost:9515"), options);
//            driver.manage().window().maximize();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static synchronized DriverManager getInstance() {
//
//        if (instance == null) {
//            instance = new DriverManager(); //  lazy initialization - driver is created only when first requested
//        }
//        return instance;
//
//    }
//
//    public WebDriver getDriver() {
//        return driver;
//    }
//
//    public void quitDriver() {
//        if (driver != null) {
//            driver.quit();
//            driver = null;
//        }
//        if (driverProcess != null) {
//            driverProcess.destroy();
//        }
//        instance = null;
//    }
//}


package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    private static DriverManager instance;
    private WebDriver driver;

    private DriverManager() {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    public static synchronized DriverManager getInstance() {

        if (instance == null) {
            instance = new DriverManager();
        }

        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quitDriver() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }

        instance = null;
    }
}