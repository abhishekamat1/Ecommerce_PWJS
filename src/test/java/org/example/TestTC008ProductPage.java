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
import Pages.productsPage;
import java.time.Duration;

public class TestTC008ProductPage {
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
    public void productPage() throws InterruptedException {
        homePage homepage= new homePage(driver);
        productsPage productpage= new productsPage(driver);
        Assert.assertEquals(driver.getTitle(),"Automation Exercise");
        homepage.product();
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id= 'search_product']")));
        System.out.println(productpage.AllProductsBanner());
        Assert.assertTrue(productpage.AllProductsBanner().contains("ALL PRODUCTS"));
        driver.findElement(By.xpath("//a[@href='/product_details/1']/i[@class='fa fa-plus-square']")).click();
        WebElement name= driver.findElement(By.xpath("//h2[text()= 'Blue Top']"));
        Assert.assertTrue(name.isDisplayed());
        WebElement category= driver.findElement(By.xpath("//p[text()= 'Category: Women > Tops']"));
        Assert.assertTrue(category.isDisplayed());
        WebElement availability= driver.findElement(By.xpath("//p[contains(text(),' In Stock')]"));
        Assert.assertTrue(availability.isDisplayed());
        WebElement condition= driver.findElement(By.xpath("//p[contains(text(),'New')]"));
        Assert.assertTrue(condition.isDisplayed());
        WebElement brand= driver.findElement(By.xpath("//p[contains(text(),'Polo')]"));
        Assert.assertTrue(brand.isDisplayed());
    }

    @AfterTest
    public void browserClose(){
        driver.close();
        driver.quit();
    }
}
