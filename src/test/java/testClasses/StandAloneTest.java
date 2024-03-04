package testClasses;

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {
	WebDriver driver;
	
	@Test
	public void Method() throws InterruptedException
	{
		String productName ="ZARA COAT 3";
		WebDriverManager.chromedriver().setup(); 
		 driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		Thread.sleep(2000);
		driver.findElement(By.id("userEmail")).sendKeys("bardolakshay@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Akshay@123");
		driver.findElement(By.id("login")).click();
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		Thread.sleep(2000);
		WebElement prod=products.stream().filter(product->
		product.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));//.ta-results//.ta-results button:last-of-type
		Thread.sleep(2000);
		driver.findElement(By.xpath("//ul//li[4]//button")).click();
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		
		boolean Match=cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(Match);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".ta-results button:last-of-type")).click();
		driver.findElement(By.xpath("//a[.='Place Order ']")).click();
		String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
		
	}
	@AfterMethod()
	public void tearDown()
	{
//		driver.close();
	}

}
