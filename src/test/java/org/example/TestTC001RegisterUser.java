package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestTC001RegisterUser {
    WebDriver driver;
    @BeforeTest
    public void browser(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions option=  new ChromeOptions();
        option.addArguments("--incognito");
        driver= new ChromeDriver(option);
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com/");
        driver.getTitle();
    }

    @Test
    public void Signup() throws InterruptedException {
        driver.findElement(By.xpath("//a[@href= \"/login\"]")).click();
        WebElement New_Signup= driver.findElement(By.xpath("//div[@class= \"signup-form\"]//h2"));
        //Assertion1
        Assert.assertTrue(New_Signup.isDisplayed());
        WebElement signup_name= driver.findElement(By.xpath("//input[@name= 'name']"));
        signup_name.sendKeys("Abcqwert");
        WebElement signup_email= driver.findElement(By.xpath("//input[@data-qa= 'signup-email']"));
        signup_email.sendKeys("abcqwert@gmail.com");
        WebElement signup= driver.findElement(By.xpath("//button[text()= 'Signup']"));
        signup.click();
        //Explicit wait used
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()= 'Enter Account Information']")));
        String account_info= driver.findElement(By.xpath("//b[text()= 'Enter Account Information']")).getText();
        //Assertion2
        String expected_result= "Enter Account Information".toUpperCase();
        Assert.assertEquals(account_info, expected_result);
        driver.findElement(By.xpath("//input[@id= 'id_gender1']")).click();
        WebElement password= driver.findElement(By.xpath("//input[@data-qa= 'password']"));
        password.sendKeys("Abc@123");
        Select select_day = new Select(driver.findElement(By.xpath("//select[@data-qa= 'days']")));
        select_day.selectByVisibleText("29");
        Select select_month = new Select(driver.findElement(By.xpath("//select[@data-qa= 'months']")));
        select_month.selectByVisibleText("May");
        Select select_year = new Select(driver.findElement(By.xpath("//select[@data-qa= 'years']")));
        select_year.selectByVisibleText("1990");
        driver.findElement(By.xpath("//input[@name= 'newsletter']")).click();
        driver.findElement(By.xpath("//input[@name= 'optin']")).click();
        driver.findElement(By.id("first_name")).sendKeys("abc");
        driver.findElement(By.id("last_name")).sendKeys("qwert");
        driver.findElement(By.id("address1")).sendKeys("Jaipur");
        Select select_country= new Select(driver.findElement(By.id("country")));
        select_country.selectByVisibleText("Canada");
        driver.findElement(By.id("state")).sendKeys("Toronto");
        driver.findElement(By.id("city")).sendKeys("Okalahoma");
        driver.findElement(By.id("zipcode")).sendKeys("588444");
        driver.findElement(By.id("mobile_number")).sendKeys("9999988888");
        WebElement create_acc= driver.findElement(By.xpath("//button[@data-qa= \"create-account\"]"));
        create_acc.click();
//        Actions mouse= new Actions(driver);
//        mouse.scrollToElement(create_acc).click(create_acc).perform();
        //added explicit wait
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//b[text()= \"Account Created!\"]"))));
        //Assertion4
        String account_create= driver.findElement(By.xpath("//b[text()= \"Account Created!\"]")).getText();
        Assert.assertEquals(account_create, "Account Created!".toUpperCase());
        driver.findElement(By.xpath("//a[@data-qa= \"continue-button\"]")).click();
        //added explicit wait
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()= 'Abcqwert']")));
        //Assertion5
        String user_name= driver.findElement(By.xpath("//b[text()= 'Abcqwert']")).getText();
        Assert.assertEquals(user_name, "Abcqwert");
        driver.findElement(By.xpath("//a[@href= \"/delete_account\"]")).click();
        //Added explicit wait
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[text()= 'Account Deleted!']")));
        //Assertion6
        String acc_delete= driver.findElement(By.xpath("//b[text()= 'Account Deleted!']")).getText();
        Assert.assertEquals(acc_delete, "Account Deleted!".toUpperCase());
    }

    @AfterTest
    public void closeBrowser(){
        driver.close();
        driver.quit();
    }
}
