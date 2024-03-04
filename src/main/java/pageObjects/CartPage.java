package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import abstractComponents.ReuseComponents;

public class CartPage extends ReuseComponents {
	
	WebDriver driver;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productTitles;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public boolean VerfyProductDisplay(String productName)
	{
		boolean Match=productTitles.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return Match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutEle.click();
		return new CheckoutPage(driver);
	}

}
