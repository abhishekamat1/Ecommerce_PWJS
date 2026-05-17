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
import Pages.signupLoginPage;

import java.time.Duration;

public class TestTC004LogOut {
    WebDriver driver;

    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions option= new ChromeOptions();
        option.addArguments("--incognito");
        driver= new ChromeDriver(option);
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
    }

    @Test
    public void Logout(){
        homePage homepage= new homePage(driver);
        signupLoginPage signuploginpage= new signupLoginPage(driver);
        homepage.signup_login();
        Assert.assertEquals(signuploginpage.loginBanner(), "Login to your account");
        signuploginpage.loginEmail("Tester158@gmail.com");
        signuploginpage.password("Tester@158");
        signuploginpage.loginButton();
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class= \"fa fa-user\"]/following-sibling::*")));
        Assert.assertEquals(homepage.homePageUserName(), "Tester158");
        homepage.logoutButton();
        String login_page= driver.getTitle();
        if(login_page != null) {
            Assert.assertTrue(login_page.contains("Login"));
        }
    }

    @AfterTest()
    public void browserClose(){
        driver.close();
        driver.quit();
    }
}
