package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;

public class HomePageTest extends BaseTest{

	/* Check that the title of the homepage matches expected title */
	@Test(priority=0)
	public void verifyTitle() {
		HomePage homePage = new HomePage(driver);
		Assert.assertEquals(homePage.getTitle(), "Expedia Travel: Search Hotels, Cheap Flights, Car Rentals & Vacations");
	}
	
	/* Check that the logo is present in the homepage */
	@Test(priority=1)
	public void verifyLogoIsPresent() {
		HomePage homePage = new HomePage(driver);
		Assert.assertTrue(homePage.getLogo());
	}
	
	/* Check that the virtual chat icon is loaded in the homepage */
	@Test(priority=2)
	public void verifyVirtualChatIconLoaded() {
		HomePage homePage = new HomePage(driver);
		Assert.assertTrue(homePage.isChatIconPresent());
	}
	
	/* Iterate through and click each of the navigation tab */
	@Test(priority=3)
	public void clickEachNavigationTab() {
		HomePage homePage = new HomePage(driver);
		for(int i=0; i<homePage.getNavigationTab().size(); i++) {
				homePage.getNavigationTab().get(i).click();
			}
			String lastTab = homePage.getNavigationTab().get(6).getText();
			// The last tab should be the Vacation Rentals tab. Check that it clicked the tab
			Assert.assertEquals(lastTab, "Vacation Rentals\n" + 
					"Tab 7 of 7");
		}
	

}
