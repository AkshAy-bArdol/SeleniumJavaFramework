package abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.CartPage;

public class ReuseComponents {
	
	WebDriver driver;
	
	public ReuseComponents(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//ul//li[4]//button")
	WebElement cartHeader;
	
	public CartPage gotoCartPage()
	{
		cartHeader.click();
		return new CartPage(driver);
	}
	
	public void waitForElementToAppear(By findBy)
	{
	  WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
	  wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(1000);
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

}
