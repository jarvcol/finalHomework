package com.globant.trainingTae.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PaymentPage extends BasePage{

	public PaymentPage(WebDriver pDriver) {
		super(pDriver);
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(tripSummarySection)));
	}
	
	//Locators
	@FindBy(tagName="h1")
	private WebElement headerLev1;
	
	@FindBy(id="creditCardInput")
	private WebElement creditCardNumberInput;
	
	@FindBy(id="complete-booking")
	private WebElement completeBookingButton;
	
	@FindBy(css="#page-header .progress-bar")
	private WebElement progressBar;
	
	@FindBy(id="trip-summary")
	private WebElement tripSummarySection;
	
	@FindBy(css=".allTravelerDetails")
	private WebElement travelerDetailSection;
	
	@FindBy(css="fieldset.hotel")
	private WebElement hotelDetailSection;
	
	
	//Methods
	
	
	//Getters
	
	public String getPageTitle(){
		getWait().until(ExpectedConditions.titleIs("Travelocity: Payment"));
		return getDriver().getTitle().toString();
	}
	
	public WebElement getTravelerDetailSection(){
		return travelerDetailSection;
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
	
	public WebElement getProgressBar() {
		return progressBar;
	}
	
	public WebElement getTripSummarySection() {
		return tripSummarySection;
	}
	
	public WebElement getHotelDetailSection() {
		return hotelDetailSection;
	}

}
