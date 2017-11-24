package com.globant.trainingTae.pages;

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
	private WebElement departureFlightDate;
	
	@FindBy(id="flight-returning-hp-flight")
	private WebElement returningFlightDate;
	
	@FindBy(id="flight-adults-hp-flight")
	private WebElement adultsDropDown;
	
	@FindBy(xpath="/html/body/section/div/div/div/div[3]/div/div[1]/div/section[1]/form/div[7]/label/button")
	private WebElement searchButton;
	
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
	
	
	//Methods......!!!!!!!!!!!!!!!!!
	
	public void clickOnFlightPlusHotelButton(){
		flightPlusHotelButton.click();
	}
	
	public void clickOnFligthButton(){
		flightsButton.click();
	}
	
	public void clickOnRoundTrip(){
		roundTripButton.click();
	}
	
	public void selectAdultPassangers(int adultPassangers){
		Select adultsDropDownSelect = new Select(adultsDropDown);
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
	
	public void selectDepartureDate(String year, String month, String day, String type){
		boolean found = false;
		if(type.equals("flight")){
			departureFlightDate.click();
		}else{
			if(type.equals("package")){
				packageDepartureDateInput.click();
			}
		}
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
		if(type.equals("flight")){
			returningFlightDate.click();
		}else{
			if(type.equals("package")){
				packageReturnDateInput.click();
			}
		}
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
	
	public FlightsSearchResultsPage clickOnSearchButton(){
		searchButton.click();
		return new FlightsSearchResultsPage(getDriver());
	}
	
	public FlightsPlusHotelSearchResultsPage clickOnFlightPlusHotelSearchButton(){
		flightPlusHotelSearchButton.click();
		return new FlightsPlusHotelSearchResultsPage(getDriver());
	}
	
	public void selectPackageDepartureCity(String departureAirportCode){
		flightPlusHotelDepartureInput.sendKeys(departureAirportCode);
		flightPlusHotelDepartureInput.sendKeys(Keys.TAB);
	}
	
	public void selectPackageArrivalCity(String arrivalAirportCode){
		flightPlusHotelReturnInput.sendKeys(arrivalAirportCode);
		flightPlusHotelReturnInput.sendKeys(Keys.TAB);
	}
	
		
}
