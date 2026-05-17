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

import java.time.Duration;
import java.util.List;

public class TestTC009ProductSearch {
    WebDriver driver;

    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options= new ChromeOptions();
        options.addArguments("--incognito");
        driver= new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
    }

    @Test
    public void productSearch(){
        Assert.assertEquals(driver.getTitle(),"Automation Exercise");
        driver.findElement(By.xpath("//a[@href='/products']")).click();
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='title text-center']")));
        String allProducts= driver.findElement(By.xpath("//h2[@class='title text-center']")).getText();
        Assert.assertEquals(allProducts, "ALL PRODUCTS");
        driver.findElement(By.xpath("//input[@placeholder='Search Product']")).sendKeys("tshirt");
        driver.findElement(By.id("submit_search")).click();
        List<WebElement> shirts= driver.findElements(By.xpath("//div[@class='productinfo text-center']/p"));
        for(WebElement shirt: shirts){
            String shirt_text= shirt.getText();
            Assert.assertTrue(shirt_text.toLowerCase().contains("shirt"));
        }
    }

    @AfterTest
    public void browserClose(){
        driver.close();
        driver.quit();
    }
}
