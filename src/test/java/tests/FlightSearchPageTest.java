package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BasePage;
import pageObjects.FlightSearchPage;

public class FlightSearchPageTest extends BasePage{
	
	/* This test does not enter a departing date. Verify that it does not let you proceed if there are insufficient information */
	@Test(priority=0)
	public void cannotProceedSearchWithInsufficientInformation() {
		FlightSearchPage flightSearch = new FlightSearchPage(driver);
		flightSearch.clickFlightsTab();
		flightSearch.clickOneWayTab();
		flightSearch.searchForDepartingAirport("san francisco");
		flightSearch.searchForArrivingAirport("seattle");
		flightSearch.clickSearchButton();
		flightSearch.closePopup();
		Assert.assertTrue(flightSearch.errorMessageDisplayed());
	}
	
	/* Verify the search flow of a one way flight */
	@Test(priority=1)
	public void searchForFlight() {
		FlightSearchPage flightSearch = new FlightSearchPage(driver);
		//Search for an airport in the 'Flying from' textbox and select the matching text
		flightSearch.searchForDepartingAirport("san francisco");
		flightSearch.clickFromAirportSuggestionsList("San Francisco (SFO - San Francisco Intl.)");

		//Search for an airport in the 'Going to' textbox and select the matching text
		flightSearch.searchForArrivingAirport("seattle");
		flightSearch.clickFromAirportSuggestionsList("Seattle (SEA - Seattle-Tacoma Intl.)");
		
		//Select the date in the calendar box
		flightSearch.openCalendarSelection();
		flightSearch.chooseDate("30");
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
	@Test(priority=3, dependsOnMethods="searchForFlight")
	public void verifySearchButtonLoadsNewPage() {
		FlightSearchPage flightSearch = new FlightSearchPage(driver);
		flightSearch.clickSearchButton();
		Assert.assertTrue(flightSearch.finishedLoading());
	}
}
