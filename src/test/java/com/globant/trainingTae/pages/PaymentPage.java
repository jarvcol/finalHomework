package com.globant.trainingTae.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PaymentPage extends BasePage{

	public PaymentPage(WebDriver pDriver) {
		super(pDriver);
		getWait().until(ExpectedConditions.elementToBeClickable(By.id("complete-booking")));	}
	
	//Locators
	@FindBy(tagName="h1")
	private WebElement headerLev1;
	
	@FindBy(id="creditCardInput")
	private WebElement creditCardNumberInput;
	
	@FindBy(id="complete-booking")
	private WebElement completeBookingButton;
	
	//Methods
	
	
	//Getters
	
	public String getPageTitle(){
		return getDriver().getTitle().toString();
	}
	
	public WebElement getTravelerDetailSection(){
		return getDriver().findElement(By.cssSelector(".allTravelerDetails"));
	}

	public WebElement getHeaderLev1() {
		return headerLev1;
	}

	public WebElement getCreditCardNumberInput() {
		return creditCardNumberInput;
	}

	public WebElement getCompleteBookingButton() {
		return completeBookingButton;
	}
	
	
	

}
