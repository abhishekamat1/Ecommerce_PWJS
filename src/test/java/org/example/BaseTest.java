package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseTest {
    WebDriver driver;

    static {
        // 1. Silence the exact CDP findNearestMatch logger
        Logger.getLogger("org.openqa.selenium.devtools.CdpVersionFinder").setLevel(Level.OFF);
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

        System.setProperty("org.slf4j.simpleLogger.log.org.testng", "warn");

        // 2. Set SLF4J simple logger default level to ERROR/OFF
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "error");

        // 3. Mute ChromeDriver native logs
        System.setProperty("webdriver.chrome.silentOutput", "true");
    }
    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") String browser){
        if(browser.equalsIgnoreCase("chrome")){
            driver= new ChromeDriver();
        }

        else if(browser.equalsIgnoreCase("firefox")){
            driver= new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
        driver.quit();
    }

}
