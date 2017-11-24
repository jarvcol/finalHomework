package com.globant.trainingTae.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightsSearchResultsPage extends BasePage {

	public FlightsSearchResultsPage(WebDriver pDriver) {
		super(pDriver);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("flight-listing-container")));
		closeOtherWindows();
	}
	
	//Locators......!!!!!!!!!!!!!!!!!
	
	@FindBy(name="sort")
	private WebElement sortButton;

	@FindBy(id="flightModuleList")
	private WebElement flightResultsList;
	
	
	//Methods......!!!!!!!!!!!!!!!!!
	
	public void sortResultList(String sortOption){
		Select sortOptionSelect = new Select(sortButton);
		sortOptionSelect.selectByValue(sortOption);
		getWait().until(ExpectedConditions.elementToBeClickable(sortButton));
	}
	
	public void selectDepartureFlight(int position){
		selectFlightByNumberOnList(position);
		getWait().until(ExpectedConditions.elementToBeClickable(sortButton));
	}
	
	public TripDetailPage selectReturnFlight(int position){
		selectFlightByNumberOnList(position);
		return new TripDetailPage(getDriver());
	}
	
	private void selectFlightByNumberOnList(int position){
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("flightModuleList")));
		WebElement flightSearchButton = getDriver().findElement(By.xpath("//*[@id='flightModuleList']/li["+position+"]/div[2]/div/div[2]/div/button"));
		flightSearchButton.click();
	}
	
	private void closeOtherWindows(){
		if(getDriver().getWindowHandles().toArray().length > 1){
			getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[1]);
			getDriver().close();
			getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[0]);
		}
	}
	
	
	//Getters
	
	public WebElement getSortButton() {
		return sortButton;
	}
	
	public List<WebElement> getListOfElementsToCheck(){
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#flightModuleList > .flight-module.segment.offer-listing"))));
		return getDriver().findElements(By.cssSelector("#flightModuleList > .flight-module.segment.offer-listing"));
	}
}
