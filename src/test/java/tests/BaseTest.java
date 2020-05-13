package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest {
	public static WebDriver driver;
	private static final Logger log = LogManager.getLogger(BaseTest.class);
	String url = "https://www.expedia.com/";
	
	/* Open browser, maximize, and go to link */
	@BeforeClass(alwaysRun=true)
	public void setup(ITestContext context) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		log.info("WebDriver configured");
		driver.manage().window().maximize();
		log.info("Opening browser and maximizing window");
		driver.get(url);
		context.setAttribute("WebDriver", driver); //Set attribute to call in listener class
		log.info("Navigating to expedia.com");
		
	}
	
	/* Cleanup */
	@AfterClass(alwaysRun=true)
	public void tearDown() {
		driver.quit();
		log.info("Quitting browser");
	}
}
