package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class testCasesPage {
    WebDriver driver;
    public testCasesPage(WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath= "//h2[@class= 'title text-center']/b")
    public WebElement test_case_banner;

    public String testCaseBanner(){
        return test_case_banner.getText();
    }
}
