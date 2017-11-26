package com.globant.trainingTae.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PackageTripDetailPage extends BasePage {

	public PackageTripDetailPage(WebDriver pDriver) {
		super(pDriver);
		getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".flightUDPBookNowButton > button")));
	}
	
	//Locators......!!!!!!!!!!!!!!!!!
	
	@FindBy(css=".btn-secondary.btn-sub-action.gt-add-btn")
	private List<WebElement> carButtonsList;
		
	@FindBy(css=".btn-secondary.btn-sub-action")
	private List<WebElement> activitiesButtonsList;
		
	@FindBy(css=".flightUDPBookNowButton > button")
	private List<WebElement> continueButtonsList;
		
	@FindBy(id="tripTotal")
	private WebElement tripTotal;
	
	@FindBy(css=".saveToItin a")
	private WebElement saveTripLink;
			
			
	//Methods......!!!!!!!!!!!!!!!!!
		
	public void clickOnFirstCarBook(){
		getWait().until(ExpectedConditions.visibilityOfAllElements(carButtonsList));
		carButtonsList.get(0).click();
	}
		
	public PaymentPage clickOnContinueButton(){
		continueButtonsList.get(0).click();
		return new PaymentPage(getDriver());
	}
		
	
	//Test and assert methods
		
	public boolean validateFlightRoute(String departure, String arrival){
		String routeAirpotCodes = getDriver().findElement(By.cssSelector("#rspFlightsContainer div.secondary.sub-info")).getText();
		if(routeAirpotCodes.contains(departure) && routeAirpotCodes.contains(arrival))
			return true;
		else
			return false;
	}
		
		
	//Getters

	public List<WebElement> getCarButtonsList() {
		return carButtonsList;
	}

	public List<WebElement> getContinueButtonsList() {
		return continueButtonsList;
	}
	
	public WebElement getTripTotal() {
		return tripTotal;
	}
	
	public WebElement getSaveTripLink() {
		return saveTripLink;
	}
	
}
