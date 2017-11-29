package com.globant.trainingTae.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;

public class CarSearchResultsPage extends BasePage {

	public CarSearchResultsPage(WebDriver pDriver) {
		super(pDriver);
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(carResultList)));
	}
	
	//Locators
	@FindBy(css="#search-results .listing-wrapper")
	private List<WebElement> carResultList;
	
	@FindBy(css="a.btn.btn-secondary.btn-action.ember-view")
	private List<WebElement> carButtonsList;
	
	//Methods
	public PaymentPage clickOnFirstCarBook(){
		getWait().until(ExpectedConditions.visibilityOfAllElements(carButtonsList));
		carButtonsList.get(0).click();
		return new PaymentPage(getDriver());
	}
	
	//Asserts and test methods

}
