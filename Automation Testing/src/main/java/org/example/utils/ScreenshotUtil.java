package org.example.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

public class ScreenshotUtil {

    public static void capture(
            WebDriver driver,
            String testCase
    ) {

        try {

            File src =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(
                                    OutputType.FILE
                            );

            String fileName =
                    "screenshots/"
                            + testCase
                            + "_"
                            + LocalDateTime.now()
                            .toString()
                            .replace(":", "-")
                            + ".png";

            File target =
                    new File(fileName);

            target.getParentFile().mkdirs();

            Files.copy(
                    src.toPath(),
                    target.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

            System.out.println(
                    "Screenshot Saved : "
                            + fileName
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}