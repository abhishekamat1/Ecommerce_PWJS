package Pages;

import org.openqa.selenium.By;
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

    @FindBy(xpath= "//input[@id= 'search_product']")
    public WebElement productSearch;

    @FindBy(xpath="//a[@href='/product_details/1']/i[@class='fa fa-plus-square']")
    WebElement viewProductButton;

    public String AllProductsBanner(){
        return allProductsBanner.getText();
    }

    public void viewProduct(int productNum){
        driver.findElement(By.xpath("//a[@href='/product_details/"+productNum+"']/i[@class='fa fa-plus-square']")).click();
    }
}
