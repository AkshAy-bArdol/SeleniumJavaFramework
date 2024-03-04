package testComponents;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;

public class BaseTest {
	
//	WebDriver driver;
	public Properties prop;
	ChromeOptions options;
	RemoteWebDriver driver;

	
	public RemoteWebDriver initializeDriver() throws IOException
	{
		 options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--start-maximized");
		options.addArguments("--window-size=1920,1080");
		prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\globalData.properties");
		prop.load(file);
		String browserName=prop.getProperty("browser");

		if(browserName.equalsIgnoreCase("chrome"))
		{
			  
		     driver = new ChromeDriver();

		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			 
		     driver = new EdgeDriver();
		}
		else if (browserName.equalsIgnoreCase("headless"))
		{
			driver = new ChromeDriver(options);
		}
		else if(browserName.equalsIgnoreCase("docker"))
		{

			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability(ChromeOptions.CAPABILITY, options);
			URL url = new URL("http://localhost:4444/wd/hub");
			 driver = new RemoteWebDriver(url,dc);
			
		}
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.manage().window().maximize();
	    return driver;
	}
    public LandingPage launchApplication() throws IOException
    {
    	driver = initializeDriver();
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }
    
    @AfterMethod
    public void teardown() throws InterruptedException {
    	Thread.sleep(3000);
    	driver.quit();
    }

}
