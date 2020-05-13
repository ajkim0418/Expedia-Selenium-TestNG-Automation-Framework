package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.FlightSearchPage;

public class FlightSearchPageTest extends BaseTest{
	
	/* This test does not enter a departing date. Verify that it does not let you proceed if there are insufficient information */
	@Test(priority=0)
	public void cannotProceedSearchWithInsufficientInformation() {
		FlightSearchPage flightSearch = new FlightSearchPage(driver);
		flightSearch.clickFlightsTab()
				.clickOneWayTab()
				.searchForDepartingAirport("san francisco")
				.searchForArrivingAirport("seattle")
				.clickSearchButton()
				.closePopup();
		Assert.assertTrue(flightSearch.errorMessageDisplayed());
	}
	
	/* Verify the search flow of a one way flight */
	@Test(priority=1, groups="regression")
	public void searchForFlight() {
		FlightSearchPage flightSearch = new FlightSearchPage(driver);
		try {
			flightSearch.clickFlightsTab()
					.clickOneWayTab();
		} catch(Exception e) {
			
		}
		//Search for an airport in the 'Flying from' textbox and select the matching text
		flightSearch.searchForDepartingAirport("san francisco")
					.clickFromAirportSuggestionsList("San Francisco (SFO - San Francisco Intl.)")

		//Search for an airport in the 'Going to' textbox and select the matching text
					.searchForArrivingAirport("seattle")
					.clickFromAirportSuggestionsList("Seattle (SEA - Seattle-Tacoma Intl.)")
		
		//Select the date in the calendar box
					.openCalendarSelection()
					.chooseDate("30");
	}

	/* Verify the 'Advanced options' dropdown can be opened, and the preferred class can be changed */
	@Test(priority=2)
	public void editFlight() {
		FlightSearchPage flightSearch = new FlightSearchPage(driver);
		flightSearch.clickAdvancedOptions();
		//Soft Assertion
		SoftAssert verifyClass = new SoftAssert();
		flightSearch.selectDifferentClass("first");
		verifyClass.assertEquals(flightSearch.selectedOption(), "First class");
		flightSearch.selectDifferentClass("business");
		verifyClass.assertEquals(flightSearch.selectedOption(), "Business");
		flightSearch.selectDifferentClass("premium");
		verifyClass.assertEquals(flightSearch.selectedOption(), "Premium economy");
		verifyClass.assertAll();
	}
	
	/* Verify that clicking on 'Search' loads a new page and flights are loaded */
	@Test(priority=3, dependsOnMethods="searchForFlight", groups="regression")
	public void verifySearchButtonLoadsNewPage() {
		FlightSearchPage flightSearch = new FlightSearchPage(driver);
		flightSearch.clickSearchButton();
		Assert.assertTrue(flightSearch.finishedLoading());
	}
}
