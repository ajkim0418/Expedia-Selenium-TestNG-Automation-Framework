package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import base.BasePage;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class LoginPageTest extends BasePage{
	private static final Logger log = LogManager.getLogger(LoginPageTest.class);
	
	/* Go to login screen from home page*/
	@BeforeClass(alwaysRun=true)
	public void navigateToLoginScreen() {
		HomePage homePage = new HomePage(driver);
		homePage.navigateToLoginScreen();
		Assert.assertTrue(homePage.isLoginScreenPresent());
	}

	/* Verify that the Sign In with Google option is present */
	@Test(priority=0)
	public void verifyGoogleLoginLinkDisplayed() {
		LoginPage login = new LoginPage(driver);
		Assert.assertTrue(login.isGoogleSigninLinkPresent());
	}

	/* Verify that the Sign In with Facebook option is present */
	@Test(priority=1)
	public void verifyFacebookLoginLinkDisplayed() {
		LoginPage login = new LoginPage(driver);
		Assert.assertTrue(login.isFacebookSigninLinkPresent());
	}

	/* Verify clicking the forgot password link loads reset password screen */
	@Test(priority=2)
	public void clickForgotPasswordLink() {
		LoginPage login = new LoginPage(driver);
		login.clickForgotPassword();
		Assert.assertTrue(login.didResetPasswordScreenLoad());
		login.clickBackButton();
	}

	/* Test a successful login with correct credentials */
	@Test(priority=3, groups="regression")
	public void loginCorrectCredentials() throws InterruptedException {
		LoginPage login = new LoginPage(driver);
		login.typeEmailAddress("juntestuser@gmail.com");
		login.typePassword("testing123");
		login.uncheckKeepSignedIn();
		login.clickLoginButton();
		try {
			Assert.assertTrue(login.isLoginSuccessful());
			log.info("Login successful, returning to home page");
		} catch(Exception e) {
			Assert.assertTrue(login.didRecaptchaAppear());
			log.debug("Login successful, but recaptcha human verification bot appeared");
		}
	}
}
