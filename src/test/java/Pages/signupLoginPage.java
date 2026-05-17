package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class signupLoginPage {
    WebDriver driver;

    public signupLoginPage(WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    //Locators
    @FindBy(xpath= "//input[@placeholder= 'Password']/preceding-sibling::input[@placeholder='Email Address']")
    WebElement Login_email;

    @FindBy(xpath= "//input[@placeholder= 'Password']")
    WebElement password;

    @FindBy(xpath= "//button[@data-qa= 'login-button']")
    WebElement Login_button;

    @FindBy(xpath= "//input[@placeholder= 'Name']")
    WebElement signup_name;

    @FindBy(xpath= "//input[@placeholder= 'Name']/following-sibling::input[@placeholder='Email Address']")
    WebElement signup_email;

    @FindBy(xpath= "//button[@data-qa= 'signup-button']")
    WebElement signup_button;

    @FindBy(xpath= "//input[@placeholder= 'Your email address']")
    WebElement subscription_email;

    @FindBy(id="#subscribe")
    WebElement subscription_button;

    @FindBy(xpath= "//h2[text()='Login to your account']")
    WebElement login_banner;

    @FindBy(xpath= "//h2[contains(text(), 'Signup!')]")
    WebElement signup_banner;

    @FindBy(xpath= "//p[contains(text(),'password is incorrect')]")
    WebElement invalidLoginErrorMessage;

    @FindBy(xpath= "//p[contains(text(), 'already exist!')]")
    WebElement emailExistErrorMessage;


    //Actions

    public void loginEmail(String email){
        Login_email.sendKeys(email);
    }

    public void password(String pwd){
        password.sendKeys(pwd);
    }

    public void loginButton(){
        Login_button.click();
    }

    public void userName(String userName){
        signup_name.sendKeys(userName);
    }

    public void signupEmail(String email){
        signup_email.sendKeys(email);
    }

    public void signupButton(){
        signup_button.click();
    }

    public void subscriptionEmail(String email){
        subscription_email.sendKeys(email);
    }

    public void subscriptionButton(){
        subscription_button.click();
    }

    public String loginBanner(){
        return login_banner.getText();
    }

    public String signupBanner(){
        return signup_banner.getText();
    }

    public String invalidLoginError(){
        return invalidLoginErrorMessage.getText();
    }

    public String emailExistError(){
        return emailExistErrorMessage.getText();
    }
}
