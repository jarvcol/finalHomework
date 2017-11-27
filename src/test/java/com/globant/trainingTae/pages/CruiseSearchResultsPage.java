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
		
		getDriver().findElement(By.xpath("//div[@id='main-results']/div[2]/div["+selectedValue+"]//button[@class='btn-secondary btn-sub-action show-dates-button']")).click();
		getDriver().findElement(By.xpath("//div[@id='main-results']/div[2]/div["+selectedValue+"]//a[@class='btn btn-secondary btn-action select-sailing-button']")).click();
		
		return new CruiseInformationPage(getDriver());
	}
	
	
	//Test and assertion methods

}
