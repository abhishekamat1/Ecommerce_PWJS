package org.example;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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

public class TestTC002Login extends BaseTest{
   // WebDriver driver;
//   BaseTest baseTest= new BaseTest();
//
//    @BeforeTest
//    public void setup(){
//        baseTest.setup("chrome");
//    }

    @Test
    public void LoginPage(){
        homePage homepage= new homePage(driver);
        signupLoginPage signupLogin = new signupLoginPage(driver);
        Assert.assertEquals(driver.getTitle(), "Automation Exercise");
        homepage.signup_login();
        Assert.assertEquals(signupLogin.loginBanner(), "Login to your account");
        signupLogin.loginEmail("Tester158@gmail.com");
        signupLogin.password("Tester@158");
        signupLogin.loginButton();
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class= \"fa fa-lock\"]")));
        Assert.assertEquals(homepage.homePageUserName(),"Tester158");

    }

//    @AfterTest
//    public void close(){
//        baseTest.tearDown();
//    }
}
