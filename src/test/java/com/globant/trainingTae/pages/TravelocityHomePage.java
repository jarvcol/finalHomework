package com.globant.trainingTae.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class TravelocityHomePage extends BasePage{

	public TravelocityHomePage(WebDriver pDriver) {
		super(pDriver);
		pDriver.get("https://www.travelocity.com/");
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("package-origin-hp-package")));
	}
	
	//Locators......!!!!!!!!!!!!!!!!!
	
	@FindBy(id="flight-origin-hp-flight")
	private WebElement departureFlightAirportInput;
	
	@FindBy(id="flight-destination-hp-flight")
	private WebElement arrivalFlightAirportInput;
	
	@FindBy(id="tab-flight-tab-hp")
	private WebElement flightsButton;
	
	@FindBy(id="flight-type-roundtrip-label-hp-flight")
	private WebElement roundTripButton;
	
	@FindBy(id="flight-departing-hp-flight")
	private WebElement departureFlightDateInput;
	
	@FindBy(id="flight-returning-hp-flight")
	private WebElement returningFlightDateInput;
	
	@FindBy(id="flight-adults-hp-flight")
	private WebElement adultsDropDown;
	
	@FindBy(id="package-1-adults-hp-package")
	private WebElement adultsDropDownPackage;
	
	@FindBy(xpath="/html/body/section/div/div/div/div[3]/div/div[1]/div/section[1]/form/div[7]/label/button")
	private WebElement onlyFlightSearchButton;
	
	@FindBy(css=".datepicker-paging.datepicker-next.btn-paging.btn-secondary.next")
	private WebElement datePickerNext;
	
	@FindBy(css=".datepicker-paging.datepicker-prev.btn-paging.btn-secondary.prev")
	private WebElement datePickerBack;
	
	@FindBy(id="tab-package-tab-hp")
	private WebElement flightPlusHotelButton;
	
	@FindBy(id="search-button-hp-package")
	private WebElement flightPlusHotelSearchButton;
	
	@FindBy(id="package-origin-hp-package")
	private WebElement flightPlusHotelDepartureInput;
	
	@FindBy(id="package-destination-hp-package")
	private WebElement flightPlusHotelReturnInput;
	
	@FindBy(id="package-departing-hp-package")
	private WebElement packageDepartureDateInput;
	
	@FindBy(id="package-returning-hp-package")
	private WebElement packageReturnDateInput;
	
	@FindBy(id="tab-hotel-tab-hp")
	private WebElement hotelButton;
	
	@FindBy(id="hotel-destination-hp-hotel")
	private WebElement hotelDestinationInput;
	
	@FindBy(xpath="/html/body/section/div/div/div/div[3]/div/div[1]/div/section[2]/form/div[7]/label/button")
	private WebElement onlyHotelSearchButton;
	
	@FindBy(id="partialHotelBooking-hp-package")
	private WebElement hotelForPartOfMyStayCheckBox;
	
	@FindBy(id="package-checkin-hp-package")
	private WebElement pkgCheckInDateInput;
	
	@FindBy(id="package-checkout-hp-package")
	private WebElement pkgCheckOutDateInput;
	
	@FindBy(css="div.alert.alert-error.validation-alert .error-link")
	private List<WebElement> errorMessagesList;
	
	@FindBy(id="tab-cruise-tab-hp")
	private WebElement cruiseButton;
	
	@FindBy(id="cruise-destination-hp-cruise")
	private WebElement goingToDropDown;
	
	@FindBy(id="cruise-departure-month-hp-cruise")
	private WebElement departureMonthDropDown;
	
	@FindBy(xpath="/html/body/section/div/div/div/div[3]/div/div[1]/div/section[5]/form/button")
	private WebElement onlyCruiseSearchButton;
	
	@FindBy(id="fhc-fhc-hp-package")
	private WebElement flightHotelAndCarSubTypeButton;
	
	
	//Methods......!!!!!!!!!!!!!!!!!
	
	public void clickOnCruiseButton(){
		cruiseButton.click();
	}
	
	public void clickOnFlightHotelAndCarSubTypeButton(){
		flightHotelAndCarSubTypeButton.click();
	}
	
	public void checkHotelForPartOfMyStay(){
		hotelForPartOfMyStayCheckBox.sendKeys(Keys.SPACE);
	}
	
	public void clickOnHotelButton(){
		hotelButton.click();
	}
	
	public void clickOnFlightPlusHotelButton(){
		flightPlusHotelButton.click();
	}
	
	public void clickOnFligthButton(){
		flightsButton.click();
	}
	
	public void clickOnRoundTrip(){
		roundTripButton.click();
	}
	
	public void selectDepartureMonth(String departureMonth){
		getWait().until(ExpectedConditions.elementToBeClickable(departureMonthDropDown));
		Select departureMonthDropDownSelect = new Select(departureMonthDropDown);
		departureMonthDropDownSelect.selectByValue(departureMonth);
	}
	
	public void selectGoingToCruise(String destination){
		getWait().until(ExpectedConditions.elementToBeClickable(goingToDropDown));
		Select goingToDropDownSelect = new Select(goingToDropDown);
		goingToDropDownSelect.selectByValue(destination);
	}
	
	public void selectAdultPassangers(int adultPassangers){
		Select adultsDropDownSelect = new Select(adultsDropDown);
		adultsDropDownSelect.selectByValue(adultPassangers+"");;
	}
	
	public void selectAdultPassangersPkg(int adultPassangers){
		Select adultsDropDownSelect = new Select(adultsDropDownPackage);
		adultsDropDownSelect.selectByValue(adultPassangers+"");;
	}
	
	public void selectFlightDepartureCity(String departureAirportCode){
		departureFlightAirportInput.sendKeys(departureAirportCode);
		departureFlightAirportInput.sendKeys(Keys.TAB);
	}
	
	public void selectFlightArrivalCity(String arrivalAirportCode){
		arrivalFlightAirportInput.sendKeys(arrivalAirportCode);
		arrivalFlightAirportInput.sendKeys(Keys.TAB);
	}
	
	public void selectHotelDestinationName(String hotelDestination){
		hotelDestinationInput.sendKeys(hotelDestination);
	}
	
	public void selectDepartureDate(String year, String month, String day, String type){
		boolean found = false;
		if(type.equals("flight"))
			departureFlightDateInput.click();
		if(type.equals("package"))
			packageDepartureDateInput.click();
		if(type.equals("hotel"))
			pkgCheckInDateInput.click();
		WebElement date=null;
		do{
			try{
				date = getDriver().findElement(By.cssSelector("button[data-year='"+year+"'][data-month='"+month+"'][data-day='"+day+"']"));
				found=true;
			}catch(NoSuchElementException exc){
				found=false;
				datePickerNext.click();
			}
		}while(!found);
		date.click();
	}

	public void selectArrivalDate(String year, String month, String day, String type){
		boolean found = false;
		if(type.equals("flight"))
			returningFlightDateInput.click();
		if(type.equals("package"))
			packageReturnDateInput.click();
		if(type.equals("hotel"))
			pkgCheckOutDateInput.click();
		WebElement date=null;
		do{
			try{
				date = getDriver().findElement(By.cssSelector("button[data-year='"+year+"'][data-month='"+month+"'][data-day='"+day+"']"));
				found=true;
			}catch(NoSuchElementException exc){
				found=false;
				datePickerNext.click();
			}
		}while(!found);
		date.click();
	}
	
	public HotelSearchResultsPage clickOnOnlyHotelSearchButton(){
		onlyHotelSearchButton.click();
		return new HotelSearchResultsPage(getDriver());
	}
	
	public FlightsSearchResultsPage clickOnFlightSearchButton(){
		onlyFlightSearchButton.click();
		return new FlightsSearchResultsPage(getDriver());
	}
	
	public HotelSearchResultsPage clickOnFlightPlusHotelSearchButton(){
		flightPlusHotelSearchButton.click();
		return new HotelSearchResultsPage(getDriver());
	}
	
	public CruiseSearchResultsPage clickOnCruiseSearchButton(){
		onlyCruiseSearchButton.click();
		return new CruiseSearchResultsPage(getDriver());
	}
	
	public void selectPackageDepartureCity(String departureAirportCode){
		flightPlusHotelDepartureInput.sendKeys(departureAirportCode);
		flightPlusHotelDepartureInput.sendKeys(Keys.TAB);
	}
	
	public void selectPackageArrivalCity(String arrivalAirportCode){
		flightPlusHotelReturnInput.sendKeys(arrivalAirportCode);
		flightPlusHotelReturnInput.sendKeys(Keys.TAB);
	}
	
	public void clickSearchForErros(String formType){
		if(formType.equals("flight"))
			onlyFlightSearchButton.click();
		if(formType.equals("package"))
			flightPlusHotelSearchButton.click();
		if(formType.equals("packageHotel"))
			onlyHotelSearchButton.click();
	}
	
	
	//Test and assert methods
	
	public boolean checkPresenceOfErrorMessage(String expectedMsg){
		getWait().until(ExpectedConditions.visibilityOfAllElements(errorMessagesList));
		for(WebElement element : errorMessagesList){
			if(element.getText().equals(expectedMsg))
				return true;
		}
		return false;
	}
		
}
