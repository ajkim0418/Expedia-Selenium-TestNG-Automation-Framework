package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	/* Locators */
  	By emailField = By.cssSelector("#gss-signin-email");
	By passwordField = By.cssSelector("#gss-signin-password");
	By keepMeSignedInCheckbox = By.cssSelector("#gss-keep-signin-check");
	By signinButton = By.cssSelector("#gss-signin-submit");
	By signInWithGoogle = By.cssSelector("#gss-signin-login-google-button");
	By signInWithFacebook = By.cssSelector("#gss-signin-login-facebook-button");
	By forgotPassword = By.cssSelector("#gss-go-to-forgot-password");
	By greetingUserText = By.xpath("//span[contains(@class,'nonArrangedUser')][contains(text(), 'Hello, ')]");
	
	WebDriver driver;
	WebDriverWait wait;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver, 5);
	}
	
	/* Check that the forgot password link is present */
	public boolean isForgotPasswordLinkPresent() {
		return driver.findElement(forgotPassword).isDisplayed();
	}
	
	/* Check that the Sign In with Google option is displayed */
	public boolean isGoogleSigninLinkPresent() {
		return driver.findElement(signInWithGoogle).isDisplayed();
	}
	
	/* Check that the Sign In with Facebook option is displayed */
	public boolean isFacebookSigninLinkPresent() {
		return driver.findElement(signInWithFacebook).isDisplayed();
	}
	
	/* Get the email textbox element and type entered email */
	public void typeEmailAddress(String email) {
		driver.findElement(emailField).clear();
		driver.findElement(emailField).sendKeys(email);
	}
	
	/* Get the password textbox element and type entered password */
	public void typePassword(String password) {
		driver.findElement(passwordField).clear();
		driver.findElement(passwordField).sendKeys(password);
	}
	
	/* If the Keep me signed in checkbox is checked, uncheck it */
	public void uncheckKeepSignedIn() {
		if(driver.findElement(keepMeSignedInCheckbox).isSelected()) {
			driver.findElement(keepMeSignedInCheckbox).click();
		}
	}
	
	/* Click the Sign in button */
	public void clickLoginButton() {
		driver.findElement(signinButton).click();
	}
	
	/* When a user successfully logs in, the home page displays "Hello, username." Check that this text appears */
	public boolean isLoginSuccessful() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(signinButton));
		return driver.findElement(greetingUserText).isDisplayed();
	}
}
