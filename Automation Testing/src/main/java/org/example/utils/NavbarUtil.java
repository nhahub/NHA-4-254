package org.example.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class NavbarUtil {


    public static void show(
            WebDriver driver,
            String testCase
    ) {

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                """
                        var banner =
                        document.getElementById('tc-banner');
                        
                        if(!banner){
                        
                            banner =
                            document.createElement('div');
                        
                            banner.id='tc-banner';
                        
                            banner.style.position='fixed';
                            banner.style.bottom='0';
                            banner.style.left='0';
                            banner.style.width='100%';
                        
                            banner.style.background='#ff9800';
                            banner.style.color='white';
                        
                            banner.style.fontSize='22px';
                            banner.style.fontWeight='bold';
                        
                            banner.style.padding='10px';
                            banner.style.textAlign='center';
                        
                            banner.style.zIndex='999999';
                        
                            document.body.appendChild(
                                banner
                            );
                        }
                        
                        banner.innerHTML =
                        arguments[0];
                        """,
                "▶ RUNNING : " + testCase
        );
    }
}