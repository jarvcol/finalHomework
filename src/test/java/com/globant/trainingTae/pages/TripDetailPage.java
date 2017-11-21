package com.globant.trainingTae.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class TripDetailPage extends BasePage{

	public TripDetailPage(WebDriver pDriver) {
		super(pDriver);
		getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[1]);
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("bookButton")));
	}
	
	//Locators......!!!!!!!!!!!!!!!!!
	
		@FindBy(id="bookButton")
		private WebElement continueBookingButton;		
		
		
	//Methods......!!!!!!!!!!!!!!!!!
		
		public PaymentPage clickOnContinueBookingButton(){
			continueBookingButton.click();
			return new PaymentPage(getDriver());
		}
	
	

}
