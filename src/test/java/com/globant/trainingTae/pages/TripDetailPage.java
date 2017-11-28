package com.globant.trainingTae.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class TripDetailPage extends BasePage{

	public TripDetailPage(WebDriver pDriver) {
		super(pDriver);
	}
	
	//Locators......!!!!!!!!!!!!!!!!!
	
		@FindBy(id="bookButton")
		private WebElement continueBookingButton;
		
		@FindBy(css=".packagePriceTotal")
		private WebElement packagePriceTotal;
		
		@FindBy(css=".flex-card.flex-tile.details")
		private List<WebElement> tripDetailSection;
		
		
	//Methods......!!!!!!!!!!!!!!!!!
		
		public PaymentPage clickOnContinueBookingButton(){
			continueBookingButton.click();
			return new PaymentPage(getDriver());
		}
		
		public boolean validateGuarateePrice(){
			return getDriver().getPageSource().contains("Price Guarantee");
		}
		
		public void switchWindow(){
			if(getDriver().getWindowHandles().toArray().length > 1){
				getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[1]);
			}
			getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("bookButton")));
		}
	
	
	//Getters
		public WebElement getPackagePriceTotal() {
			return packagePriceTotal;
		}

		public List<WebElement> getTripDetailSection() {
			return tripDetailSection;
		}
	

}
