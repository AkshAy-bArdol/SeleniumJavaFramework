package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.ReuseComponents;

public class ConfirmationPage extends ReuseComponents {
    WebDriver driver;
	
    @FindBy(css=".hero-primary")
    private WebElement confirmationMessage;
    
    public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
    
    public String getConfirmationMessage()
    {
    	return confirmationMessage.getText();
    }
	
	

}
