package pageObjects;


import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{

	/* Locators */
	By accountsDropDown = By.cssSelector("#header-account-menu");
	By signinLink = By.cssSelector("#account-signin");
	By signinButton = By.cssSelector("#gss-signin-submit");
	By expediaLogo = By.xpath("//a[@id='header-logo']//img");
	By navigationTab = By.cssSelector(".tabs.cf.col");
	By individualTabs = By.cssSelector(".tab");
	By iframe = By.id("web-messenger-container");
	By virtualChatIframe = By.cssSelector("#web-messenger-container");
	By virtualChatIcon = By.xpath("//div[contains(@class,'messenger-button-icon')]//img");
	By virtualChatHeader = By.xpath("//div[@id='header'][contains(text(), 'How can we help?')]");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	/* Click on Account drop down and Sign In button to open login screen */
	public HomePage navigateToLoginScreen() {
		driver.findElement(accountsDropDown).click();
		driver.findElement(signinLink).click();
		return this;
	}

	public boolean isLoginScreenPresent() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(signinButton));
		return driver.findElement(signinButton).isDisplayed();
	}

	/* Get the title of the current page */
	public String getTitle() {
		return driver.getTitle();
	}

	/* Check if logo is displayed */
	public boolean getLogo() {
		return driver.findElement(expediaLogo).isDisplayed();
	}

	/* Check if chat icon loads */
	public boolean isChatIconPresent() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(virtualChatIframe));
		return driver.findElement(virtualChatIframe).isDisplayed();
	}

	/* Get all the elements of the different navigation buttons */
	public List<WebElement> getNavigationTab(){
		return driver.findElement(navigationTab).findElements(individualTabs);
	}


}

