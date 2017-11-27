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
	
	
	//Methods
	
	
	
	//Test and assertion methods

} 
