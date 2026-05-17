package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestTC010Subscription {
    WebDriver driver;
    JavascriptExecutor js;

    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options= new ChromeOptions();
        options.addArguments("--incognito");
        driver= new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
        js= (JavascriptExecutor) driver;
    }

    @Test
    public void subscription() throws InterruptedException {
        Assert.assertEquals(driver.getTitle(), "Automation Exercise");
        js.executeScript("window.scrollTo({top: document.body.scrollHeight, behaviour: 'smooth'})");
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Your email address']")));
        driver.findElement(By.xpath("//input[@placeholder='Your email address']")).sendKeys("Tester158@gmail.com");
        driver.findElement(By.id("subscribe")).click();
        String subscription_success= driver.findElement(By.xpath("//div[@class='alert-success alert']")).getText();
        Assert.assertEquals(subscription_success, "You have been successfully subscribed!");

    }

    @AfterTest
    public void browserClose(){
        driver.close();
        driver.quit();
    }
}
