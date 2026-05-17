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
import Pages.homePage;
import Pages.signupLoginPage;

public class TestTC005RegisterUserExistingCred {
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
    public void RegisterUserWithExistingCredentials(){
        homePage homepage= new homePage(driver);
        signupLoginPage signuploginpage= new signupLoginPage(driver);
        Assert.assertEquals(driver.getTitle(), "Automation Exercise");
        homepage.signup_login();
        Assert.assertEquals(signuploginpage.signupBanner(), "New User Signup!");
        signuploginpage.userName("Tester333");
        signuploginpage.signupEmail("Tester158@gmail.com");
        signuploginpage.signupButton();
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()= \"Email Address already exist!\"]")));
        Assert.assertEquals(signuploginpage.emailExistError(), "Email Address already exist!");
    }

    @AfterTest
    public void browserClose(){
        driver.close();
        driver.quit();
    }
}
