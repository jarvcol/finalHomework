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
	}
	
	//Locators......!!!!!!!!!!!!!!!!!
	
	@FindBy(id="hotel-name")
	private WebElement hotelName;
	
	@FindBy(css=".msi-visited-state")
	private WebElement completedPhase;
	
	@FindBy(id="rooms-and-rates")
	private WebElement roomAndRatesSection;
	
			
	//Methods......!!!!!!!!!!!!!!!!!
		
	public FlightsSearchResultsPage selectRoomOption(int optionNumber) {
		List<WebElement> resultsList = getDriver().findElements(By.xpath("//a[@class='btn btn-secondary btn-sub-action book-button']"));
		resultsList.get(0).click();
		return new FlightsSearchResultsPage(getDriver());
	}
	
	public void switchWindow(){
		if(getDriver().getWindowHandles().toArray().length > 1){
			getDriver().switchTo().window((String) getDriver().getWindowHandles().toArray()[1]);
		}
		//getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("rooms-and-rates")));
		getWait().until(ExpectedConditions.visibilityOf(roomAndRatesSection));
	}
	
	//Test and assert methods
	public boolean verifyCorrectHotelSelected(String selectedHotelData){
		switchWindow();
		
		String currentPageHotelName,currentPageHotelStars,currentPageHotelPrice;
		currentPageHotelName = hotelName.getText();
		currentPageHotelStars = getDriver().findElement(By.cssSelector(".icon.stars-grey")).getAttribute("title");
		currentPageHotelPrice = getDriver().findElement(By.cssSelector(".price.link-to-rooms ")).getText().replaceAll("\\D", "");
		
		if (selectedHotelData.equals(currentPageHotelName+"|"+currentPageHotelStars+"|"+currentPageHotelPrice))
			return true;
		else
			return false;
	}
	
	//Getters
	
	public WebElement getHotelName() {
		return hotelName;
	}

	public WebElement getCompletedPhase() {
		return completedPhase;
	}

	public WebElement getRoomAndRatesSection() {
		return roomAndRatesSection;
	}
	

}
