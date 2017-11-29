package com.globant.trainingTae.pages;

import java.time.Duration;
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
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("flightModuleList")));
	}
	
	//Locators......!!!!!!!!!!!!!!!!!
	
	@FindBy(name="sort")
	private WebElement sortButton;

	@FindBy(css="#flightModuleList li.flight-module.segment.offer-listing")
	private List<WebElement> flightResultsList;
	
	
	//Methods......!!!!!!!!!!!!!!!!!
	
	public void sortResultList(String sortOption){
		getWait().until(ExpectedConditions.elementToBeClickable(sortButton));
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
	
	public CarSearchResultsPage selectReturnFlightPkg(int position){
		selectFlightByNumberOnList(position);
		return new CarSearchResultsPage(getDriver());
	}
	
	private void selectFlightByNumberOnList(int position){
		getWait().until(ExpectedConditions.visibilityOfAllElements(flightResultsList));
		flightResultsList.get(position-1).findElement(By.xpath(".//button[@data-test-id='select-button']")).click();
	}
	
	public void closeOtherWindows(){
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
	
	
	//Test and assertion methods
	public boolean validateSortOfResults(String sortType){
		switch (sortType) {
		case "duration:asc":
			return validateSortByDurationDesc();
		default:
			return false;
		}
	}
	
	private boolean validateSortByDurationDesc(){
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(flightResultsList)));
		Duration prevValue=null, currValue=null;
		for (WebElement element: flightResultsList) {
			if(prevValue != null){
				currValue = convertToDuration(element.findElement(By.xpath(".//*[@data-test-id='duration']")).getText());
				if(prevValue.toMinutes()>currValue.toMinutes()){
					return false;
				}
				prevValue = currValue;
			}else{
				prevValue = convertToDuration(element.findElement(By.xpath(".//*[@data-test-id='duration']")).getText());
			}
		}
		return true;
	}
	
	private Duration convertToDuration(String valueString){
		Duration auxConvert=null;
		//Convert each StringValue to a totalDuration
		if(valueString.matches("^\\d+[h].+")){
			auxConvert = Duration.ofHours(Long.parseLong(valueString.split("h")[0]));
			auxConvert = auxConvert.plus(Duration.ofMinutes(Long.parseLong(valueString.split(" ")[1].replaceAll("m", ""))));
		}
		if(valueString.matches("^\\d+[m]")){
			auxConvert = Duration.ofMinutes(Long.parseLong(valueString.replaceAll("m", "")));
		}
		return auxConvert;
	}
	
	public boolean testAllElementsHaveSelectButton(){
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(flightResultsList)));
		if(flightResultsList.size() == getDriver().findElements(By.xpath("//button[@data-test-id='select-button']")).size()){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean testAllElementsHaveFlightDuration(){
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(flightResultsList)));
		if(flightResultsList.size() == getDriver().findElements(By.xpath("//*[@data-test-id='duration']")).size()){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean testAllElementsHaveFlightDetails(){
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(flightResultsList)));
		if(flightResultsList.size() == getDriver().findElements(By.cssSelector(".flight-details-link.toggle-trigger")).size()){
			return true;
		}else{
			return false;
		}
	}
	
}
