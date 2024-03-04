package testClasses;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.ProductCatalogue;
import testComponents.BaseTest;

public class TestClass extends BaseTest{
	
	@Test
	public void submitOrder() throws IOException, InterruptedException
	{
		String productName ="ZARA COAT 3";
		LandingPage landingPage=launchApplication();
		
        ProductCatalogue productCatalogue=landingPage.loginApplication(prop.getProperty("username"),
		 prop.getProperty("password"));
        
        productCatalogue.getProductList();
        productCatalogue.getProductByName(productName); 
        productCatalogue.addProductToCart(productName);
        CartPage cartPage=productCatalogue.gotoCartPage();
        
        boolean match=cartPage.VerfyProductDisplay(productName); 
		Assert.assertTrue(match);
		
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage=confirmationPage.getConfirmationMessage();
	
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
	    
		
	}

}
