package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class contactUs {
    WebDriver driver;
    public contactUs(WebDriver driver){
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath= "//h2[text()= 'Get In Touch']")
    WebElement banner;

    @FindBy(xpath= "//input[@placeholder= 'Name']")
    WebElement nameText;

    @FindBy(xpath= "//input[@placeholder= 'Email']")
    WebElement emailText;

    @FindBy(xpath= "//input[@placeholder= 'Subject']")
    WebElement subjectText;

    @FindBy(xpath= "//textarea[@placeholder= 'Your Message Here']")
    WebElement messageAreaText;

    @FindBy(xpath= "//input[@name= 'upload_file']")
    WebElement fileUploadBox;

    @FindBy(xpath= "//input[@name= 'submit']")
    WebElement submitButton;

    @FindBy(xpath= "//div[@class= 'status alert alert-success']")
    WebElement successAlert;

    @FindBy(xpath= "//a[@class= 'btn btn-success']")
    WebElement homebutton;

    public String Banner(){
        return banner.getText();
    }

    public void Name(String name){
        nameText.sendKeys(name);
    }

    public void Email(String email){
        emailText.sendKeys(email);
    }

    public void Subject(String subject){
        subjectText.sendKeys(subject);
    }

    public void Message(String message){
        messageAreaText.sendKeys(message);
    }

    public void fileUpload(String file){
        fileUploadBox.sendKeys(file);
    }

    public void submit(){
        submitButton.click();
    }

    public String successMessage(){
        return successAlert.getText();
    }

    public void HomeButton(){
        homebutton.click();
    }

}
