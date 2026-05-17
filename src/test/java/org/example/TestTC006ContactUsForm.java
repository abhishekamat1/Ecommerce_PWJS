package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
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
import Pages.contactUs;

import java.io.File;
import java.time.Duration;

public class TestTC006ContactUsForm {
    WebDriver driver;

    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options= new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        driver= new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com/");
    }

    @Test
    public void ContactUs() throws InterruptedException {
        homePage homepage= new homePage(driver);
        contactUs contactus= new contactUs(driver);
        Assert.assertEquals(driver.getTitle(), "Automation Exercise");
        homepage.contactUs();
        Assert.assertTrue(contactus.Banner().contains("GET IN TOUCH"));
        contactus.Name("Tester158");
        contactus.Email("Tester158@gmail.com");
        contactus.Subject("website stuck sometimes in login page");
        contactus.Message("Application is getting stuck in login page once user clicks on signup 'Login' button");
        String projectPath = System.getProperty("user.dir");
        String filePath = projectPath + "/src/test/java/org/example/ContactUsform.txt";
        File file= new File(filePath);
        contactus.fileUpload(file.getAbsolutePath());
        contactus.submit();
        Alert alert= driver.switchTo().alert();
        alert.accept();
        Assert.assertEquals(contactus.successMessage(),"Success! Your details have been submitted successfully.");

        contactus.HomeButton();
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()= 'Category']")));
        Assert.assertEquals(driver.getTitle(), "Automation Exercise");
    }

    @AfterTest
    public void browserClose(){
        driver.close();
        driver.quit();
    }
}
