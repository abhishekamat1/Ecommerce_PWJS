package Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homePage {
    WebDriver driver;
     public homePage(WebDriver driver){
        this.driver= driver;
         PageFactory.initElements(driver, this);
    }
    //Locators
    @FindBy(xpath= "//a[@href= '/products']")
    WebElement Product;

     @FindBy(xpath= "//li/a[@href= '/view_cart']")
    WebElement Cart;

    @FindBy(xpath= "//li/a[@href= '/login']")
    WebElement signup_login;

    @FindBy(xpath= "//li/a[@href= '/test_cases']")
    WebElement TestCases;

    @FindBy(xpath= "//li/a[@href= '/contact_us']")
    WebElement Contact_us;

    @FindBy(xpath= "//i[@class= 'fa fa-user']/following-sibling::b")
    public WebElement homePage_userName;

        @FindBy(xpath="//a[@href= '/logout']")
    WebElement logout_button;

    public void product(){
        Product.click();
    }

    public void cart(){
        Cart.click();
    }

    public void signup_login(){
        signup_login.click();
    }

    public void testCases(){
        TestCases.click();
    }

    public void contactUs(){
        Contact_us.click();
    }

    public void logoutButton(){
        logout_button.click();
    }

    public String homePageUserName(){
        return homePage_userName.getText();
    }
}
