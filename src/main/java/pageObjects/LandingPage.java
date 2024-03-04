package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.ReuseComponents;

public class LandingPage extends ReuseComponents {
	WebDriver driver;
	
	@FindBy(id="userEmail")
	private WebElement userName;
	
	@FindBy(id="userPassword")
	private WebElement password;
	
	@FindBy(id = "login")
	private WebElement submit;
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalogue loginApplication(String emailId, String Password)
	{	
		userName.sendKeys(emailId);
		password.sendKeys(Password);
		submit.click();
		return new ProductCatalogue(driver);

	}
	
    
	
	

}
