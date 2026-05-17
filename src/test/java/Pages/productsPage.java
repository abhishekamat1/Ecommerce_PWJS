package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class productsPage {
    WebDriver driver;
    public productsPage(WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath= "//h2[@class= 'title text-center']")
    WebElement allProductsBanner;

    public String AllProductsBanner(){
        return allProductsBanner.getText();
    }
}
