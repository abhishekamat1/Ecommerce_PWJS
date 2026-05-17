package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Pages.homePage;
import Pages.testCasesPage;

import java.time.Duration;

public class TestTC007TestCasePage {
    WebDriver driver;
    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options= new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
    }

    @Test
    public void TestCasePage() throws InterruptedException {
        homePage homepage= new homePage(driver);
        testCasesPage testcasespage= new testCasesPage(driver);
        Assert.assertEquals(driver.getTitle(), "Automation Exercise");
        homepage.testCases();
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class= 'title text-center']/b")));
        Assert.assertEquals(testcasespage.testCaseBanner(), "TEST CASES");
    }

    @AfterTest
    public void browserClose(){
        driver.close();
        driver.quit();
    }
}
