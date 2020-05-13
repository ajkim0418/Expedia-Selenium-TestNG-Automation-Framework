package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{

	/* Locators */
	By emailField = By.cssSelector("#gss-signin-email");
	By passwordField = By.cssSelector("#gss-signin-password");
	By signinButton = By.cssSelector("#gss-signin-submit");
	By signInWithGoogle = By.cssSelector("#gss-signin-login-google-button");
	By signInWithFacebook = By.cssSelector("#gss-signin-login-facebook-button");
	By forgotPassword = By.cssSelector("#gss-go-to-forgot-password");
	By resetPasswordButton = By.id("gss-forgot-password-submit");
	By backButton = By.xpath("//span[@id='gss-go-back-from-forgot-password']//span[@class='gss-go-back-label'][contains(text(),'Back')]");
	By keepMeSignedInCheckbox = By.cssSelector("#gss-keep-signin-check");
	By greetingUserText = By.xpath("//span[contains(@class,'nonArrangedUser')][contains(text(), 'Hello, ')]");
	By recaptchaChallenge = By.xpath("//iframe[@title='recaptcha challenge']");

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	/* Check that the Sign In with Google option is displayed */
	public boolean isGoogleSigninLinkPresent() {
		return driver.findElement(signInWithGoogle).isDisplayed();
	}

	/* Check that the Sign In with Facebook option is displayed */
	public boolean isFacebookSigninLinkPresent() {
		return driver.findElement(signInWithFacebook).isDisplayed();
	}

	/* Click the forgot password button */
	public void clickForgotPassword() {
		driver.findElement(forgotPassword).click();
	}

	/* Check that the password reset screen loaded */
	public boolean didResetPasswordScreenLoad() {
		return driver.findElement(resetPasswordButton).isDisplayed();
	}

	/* Click the back button to go back to the main login screen */
	public void clickBackButton() {
		driver.findElement(backButton).click();
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

	/* When a user successfully logs in, the human verification bot may appear. Wait until this verification popup appears */
	public boolean didRecaptchaAppear() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(recaptchaChallenge));
		return driver.findElement(recaptchaChallenge).isDisplayed();
	}
}

