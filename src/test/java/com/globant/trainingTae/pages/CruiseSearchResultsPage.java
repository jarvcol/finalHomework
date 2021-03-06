package com.globant.trainingTae.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CruiseSearchResultsPage extends BasePage {

	public CruiseSearchResultsPage(WebDriver pDriver) {
		super(pDriver);
		getWait().until(ExpectedConditions.visibilityOfAllElements(cruiseResultsList));
	}
	
	//Locators
	
	@FindBy(xpath="//div[@id='main-results']/div[2]/div")
	private List<WebElement> cruiseResultsList;
	
	@FindBy(id="destination-toggle")
	private WebElement filterByGoingToLink;
	
	@FindBy(id="month-toggle")
	private WebElement filterByDepartureMonthLink;
	
	@FindBy(id="passenger-toggle")
	private WebElement filterByTravelersLink;
	
	private String textAssertForSelect;
	
	//Methods

	public void filterByCruiseLength(String filterBy){
		getDriver().findElement(By.id("length-"+filterBy)).click();
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(cruiseResultsList)));
	}
	
	public CruiseInformationPage selectFirstOptionWithHigherDiscount(){
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(cruiseResultsList)));
		int highestDiscount=0, currentDiscount=0, selectedValue=0;
		for(int i=0;i<cruiseResultsList.size();i++){
			if(highestDiscount != 0 || currentDiscount != 0){
				try{
					currentDiscount = Integer.parseInt(getDriver().findElement(
							By.xpath("//div[@id='main-results']/div[2]/div["+(i+1)+"]//*[@class='message-flag flex-flag']")).getText().replaceAll("\\D", ""));
					if(currentDiscount>highestDiscount){
						highestDiscount = currentDiscount;
						selectedValue = i+1;
					}
				}catch(Exception exc){
					//In case card n does not have discount
					currentDiscount=0;
				}
			}else{
				try{
					highestDiscount = Integer.parseInt(getDriver().findElement(
							By.xpath("//div[@id='main-results']/div[2]/div["+(i+1)+"]//*[@class='message-flag flex-flag']")).getText().replaceAll("\\D", ""));
					selectedValue = i+1;
				}catch(Exception exc){
					//In case first card does not have discount
					highestDiscount = 0;
				}
			}
		}
		setSelectionAssertInformation(selectedValue);
		
		getDriver().findElement(By.xpath("//div[@id='main-results']/div[2]/div["+selectedValue+"]//button[@class='btn-secondary btn-sub-action show-dates-button']")).click();
		getDriver().findElement(By.xpath("//div[@id='main-results']/div[2]/div["+selectedValue+"]//a[@class='btn btn-secondary btn-action select-sailing-button']")).click();
		
		return new CruiseInformationPage(getDriver());
	}
	
	
	//Test and assertion methods

	public boolean validateResultsWithOffers(){
		List<WebElement> discountOffers = getDriver().findElements(By.xpath("//div[@class='message-flag flex-flag']"));
		if(cruiseResultsList.size() > 0 && discountOffers.size() > 0 && cruiseResultsList.size() != discountOffers.size())
			return true;
		else
			return false;
		
	}
	
	public String getGoingToFilterValue(){
		return filterByGoingToLink.getText();
	}
	
	public String getDepartureMonthFilterValue(){
		return filterByDepartureMonthLink.getText();
	}
	
	public String getTravelersFilterValue(){
		return filterByTravelersLink.getText();
	}
	
	private void setSelectionAssertInformation(int selectedValue){
		textAssertForSelect = getDriver().findElement(
				By.xpath("//div[@id='main-results']/div[2]/div["+selectedValue+"]//div[@class='card-header']")).getText()+"|";
		
		textAssertForSelect = textAssertForSelect + getDriver().findElement(
				By.xpath("//div[@id='main-results']/div[2]/div["+selectedValue+"]//div[@class='secondary ship-infosite']")).getText();
	}
	
	public String getTextAssertForSelect() {
		return textAssertForSelect;
	}
	
}
