package pageObjects;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightSearchPage {
	
	/* Locators */
	By flightsTab = By.id("tab-flight-tab-hp");
	By oneWaytab = By.id("flight-type-one-way-label-hp-flight");
	By flyingFromBox = By.cssSelector("#flight-origin-hp-flight");
	By searchResults = By.xpath("//ul[@id='typeaheadDataPlain']");
	By resultsTagName = By.tagName("a");
	By goingToBox = By.cssSelector("#flight-destination-hp-flight");
	By departingDateBox = By.xpath("//input[@id='flight-departing-single-hp-flight']");
	By departingCalendar = By.xpath("//div[@class='datepicker-cal-month'][2]");
	By errorMessage = By.xpath("//div[@class='alert alert-error validation-alert']");
	By dateTagName = By.tagName("button");
	By advancedOptionsTab = By.cssSelector("#flight-advanced-options-hp-flight");
	By searchButton = By.xpath("//form[@id='gcw-flights-form-hp-flight']//button[@class='btn-primary btn-action gcw-submit']");
	By loadedFlights = By.xpath("//span[contains(text(), 'Details & baggage fees')]");
	By preferredClassDropdown = By.cssSelector("#flight-advanced-preferred-class-hp-flight");
	
	WebDriver driver; 
	WebDriverWait wait;
	
	public FlightSearchPage(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver, 20);
	}
	
	/* Click the 'flights' tab */
	public void clickFlightsTab() {
		driver.findElement(flightsTab).click();
	}
	
	/* Click the 'one way' tab */
	public void clickOneWayTab() {
		driver.findElement(oneWaytab).click();
	}
	
	/* Search for an airport in the 'Flying from' textbox */
	public void searchForDepartingAirport(String departingCity) {
		//Start with partial search
		driver.findElement(flyingFromBox).clear();
		driver.findElement(flyingFromBox).sendKeys(departingCity);
	}
	
	/* Search for an airport in the 'Going to' textbox */
	public void searchForArrivingAirport(String arrivalCity) {
		driver.findElement(goingToBox).clear();
		driver.findElement(goingToBox).sendKeys(arrivalCity);
		
	}

	/* Click on a result from the search suggestion list based on matching text */
	public void clickFromAirportSuggestionsList(String fullAirportName) {
	wait.until(ExpectedConditions.visibilityOfElementLocated(searchResults));
	//Get the element of the entire suggestion list, then the tagname of all the individualresults
	List<WebElement> results = driver.findElement(searchResults).findElements(resultsTagName);
	for(WebElement result : results) {
		if(result.getText().contains(fullAirportName)) {
				result.click();
				break;
			}
		}
	}
	
	/* Click on the calendar textbox to open the calendar list */
	public void openCalendarSelection() {
		driver.findElement(departingDateBox).click();
	}
	
	/* Click on a date based on the entered number */
	public void chooseDate(String day) {
		List<WebElement> dates = driver.findElement(departingCalendar).findElements(dateTagName);
		for(WebElement date : dates) {
			if(date.getAttribute("data-day").equals(day)){
				date.click();
				break;
			}
		}
	}
	
	/* Click the 'Advanced options' dropdown */
	public void clickAdvancedOptions() {
		driver.findElement(advancedOptionsTab).click();
	}
	
	/* Click the 'Preferred class' dropdown */
	public void selectDifferentClass(String value) {
		Select preferredClass = new Select(driver.findElement(preferredClassDropdown));
		preferredClass.selectByValue(value);
	}
	
	/* Return the text of the selected flight class */
	public String selectedOption() {
		return new Select(driver.findElement(preferredClassDropdown)).getFirstSelectedOption().getText();
	}
	
	/* Check that the error message is displayed if a user doesn't fill out the required fields */
	public boolean errorMessageDisplayed() {
		return driver.findElement(errorMessage).isDisplayed();
	}
	
	/* Click the 'Search' button */
	public void clickSearchButton() {
		driver.findElement(searchButton).click();
	}
	
	/* Check that the search results page finished loading */
	public boolean finishedLoading() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(loadedFlights));
		return driver.findElement(loadedFlights).isDisplayed();
	}
	
	/* When a user searches for a flight, an additional Expedia window may open. This method closes the window */
	public void closePopup() {
		try {
			String parentWindow = driver.getWindowHandle();
			Set<String> windows = driver.getWindowHandles();
			for(String newWindow : windows) {
				if(!newWindow.equals(parentWindow)) {
					driver.switchTo().window(newWindow);
					driver.close();
				}
			}
			driver.switchTo().window(parentWindow);
		}catch(Exception e) {

		}
	}
	
}
