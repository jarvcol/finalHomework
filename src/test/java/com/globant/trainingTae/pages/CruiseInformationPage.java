package com.globant.trainingTae.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CruiseInformationPage extends BasePage {

	public CruiseInformationPage(WebDriver pDriver) {
		super(pDriver);
		getWait().until(ExpectedConditions.visibilityOfAllElements(cabinTypeSelectionList));
	}
	
	//Locators
	@FindBy(xpath="//div[@id='cabin-tabs']/ul/li")
	private List<WebElement> cabinTypeSelectionList;
	
	@FindBy(css="h1.main-title [aria-hidden]")
	private WebElement mainHeaderOptionName;
	
	@FindBy(css="aside .secondary.ship-info>div")
	private WebElement shipNameInfo;
	
	@FindBy(css="aside .secondary.details-section .details-content")
	private List<WebElement> shipSailingDates;
	
	//Methods
	
	
	//Test and assertion methods
	
	public boolean validateCruiseExpectedInformation(String  expectedInfo){
		if(expectedInfo.equals(mainHeaderOptionName.getText()+"|"+shipNameInfo.getText().replaceAll("Ship: ", "")))
			return true;
		else
			return false;
	}

} 


