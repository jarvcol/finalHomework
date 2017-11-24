package com.globant.trainingTae.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BookRoomsSelectionPage extends BasePage {

	public BookRoomsSelectionPage(WebDriver pDriver) {
		super(pDriver,20);
		getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("rooms-and-rates")));
	}
	
	//Locators......!!!!!!!!!!!!!!!!!
	
			
	//Methods......!!!!!!!!!!!!!!!!!
			
	public FlightsSearchResultsPage selectRoomOption() {
		List<WebElement> resultsList = getDriver().findElements(By.cssSelector(".room-price-book-button-wrapper"));
		resultsList.get(0).findElement(By.xpath("./div[2]/a")).click();
		return new FlightsSearchResultsPage(getDriver());
	}
		
	public void selectRoomOption(int optionNumber) {
	}

}
