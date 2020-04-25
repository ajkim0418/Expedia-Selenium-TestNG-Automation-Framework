package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import pageObjects.HomePage;
import pageObjects.LoginPage;

public class LoginPageTest extends BaseTest{

	/* Go to login screen from home page*/
	@BeforeClass
	public void navigateToLoginScreen() {
		HomePage homePage = new HomePage(driver);
		homePage.navigateToLoginScreen();
		Assert.assertTrue(homePage.isLoginScreenPresent());
	}
	
	/* Check that the Sign In with Google option is present */
	@Test(priority=0)
	public void verifyGoogleLoginLinkDisplayed() {
		LoginPage login = new LoginPage(driver);
		Assert.assertTrue(login.isGoogleSigninLinkPresent());
	}
	
	/* Check that the Sign In with Facebook option is present */
	@Test(priority=1)
	public void verifyFacebookLoginLinkDisplayed() {
		LoginPage login = new LoginPage(driver);
		Assert.assertTrue(login.isFacebookSigninLinkPresent());
	}
	
	/* Test a successful login with correct credentials */
	@Test(priority=2)
	public void loginCorrectCredentials() {
		LoginPage login = new LoginPage(driver);
		login.typeEmailAddress("username");
		login.typePassword("password");
		login.uncheckKeepSignedIn();
		login.clickLoginButton();
		Assert.assertTrue(login.isLoginSuccessful());
	}
}
