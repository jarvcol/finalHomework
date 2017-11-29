package com.globant.trainingTae.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.StaleElementReferenceException;

public class HotelSearchResultsPage extends BasePage {

	public HotelSearchResultsPage(WebDriver pDriver) {
		//Add 10 more seconds cause this page option tends to be so painful slow
		super(pDriver,20);
		getWait().until(ExpectedConditions.elementToBeClickable(priceSortButton));
	}
	
	//Locators......!!!!!!!!!!!!!!!!!
	
	@FindBy(css="button[data-opt-group='Price']")
	private WebElement priceSortButton;
	
	@FindBy(id="resultsContainer")
	private WebElement packageResults;
	
	@FindBy(id="hotelResultTitle")
	private WebElement packageResultHeader;
	
	@FindBy(id="googleMapContainer")
	private WebElement googleMapContainer;
	
	@FindBy(id="multiStepIndicatorContainer")
	private WebElement pkgStepsIndicator;
	
	@FindBy(id="sortContainer")
	private WebElement sortOptionsContainer;
	
	@FindBy(tagName="article")
	private List<WebElement> hotelsResultsCardList;
	
	@FindBy(id="mer-signup-toggle-btn")
	private WebElement emailSignUpDiscountOffer;
		
		
	//Methods......!!!!!!!!!!!!!!!!!

	public void sortByOption(String sortOption){
		switch (sortOption) {
		case "Price":
			priceSortButton.click();
			break;
		default:
			break;
		}
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(hotelsResultsCardList)));
	}
	
	public BookRoomsSelectionPage selectPackageBy(String selectOption, String selectionValue){
		switch (selectOption) {
		case "Stars":
			getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(hotelsResultsCardList)));
			List<WebElement> hotelsList = getDriver().findElements(By.tagName("article"));
			List<WebElement> starsList = getDriver().findElements(By.cssSelector(".star-rating.rating-secondary.star-rating [aria-hidden]"));
			return selectPackageByStars(hotelsList,starsList,selectionValue);
		default:
			return null;
		}
	}
	
	public BookRoomsSelectionPage selectPackageByStars(List<WebElement> hotelsList, List<WebElement> starsList, String starsNumber){
		double starsHotelValue;
		for (int i=0;i<starsList.size();i++) {
			starsHotelValue = Double.parseDouble(starsList.get(i).getAttribute("title"));
			if(starsHotelValue >= Double.parseDouble(starsNumber)){
				hotelsList.get(i).click();
				return new BookRoomsSelectionPage(getDriver());
			}	
		}
		/*If the original list of hotels does not have 3 stars, but there are more options
		if(getDriver().findElement(By.xpath("button[@class='pagination-next']")) != null){
			getDriver().findElement(By.xpath("button[@class='pagination-next']")).click();
			getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("article"))));
			return selectPackageByStars(getDriver().findElements(By.tagName("article")),getDriver().findElements(By.cssSelector(".star-rating.rating-secondary.star-rating [aria-hidden]")),starsNumber);
		}*/
		//Default in case hotel match, selects first element
		hotelsList.get(0).click();
		return new BookRoomsSelectionPage(getDriver()); 
	}
	
	
	
	//Test and assertion methods
	public boolean validateSortOfResults(String sortType){
		switch (sortType) {
		case "Price":
			List<WebElement> resultsList = getDriver().findElements(By.cssSelector(".actualPrice.price.fakeLink"));
			return validateSortByPrice(resultsList,0);
		default:
			return false;
		}
	}
	
	private boolean validateSortByPrice(List<WebElement> resultsList, double prevValue){
		double currValue=0;
		for (WebElement element: resultsList) {
			if(!(prevValue == 0)){
				currValue = Double.parseDouble(element.getText().replaceAll("\\D", ""));
				if(prevValue>currValue){
					return false;
				}
				prevValue = currValue;
			}else{
				prevValue = Double.parseDouble(element.getText().replaceAll("\\D", ""));
			}
		}
		return true;
	}
		
	public String getdataToValidateNextPage(){
		String hotelName, stars, price;
		hotelName = getDriver().findElement(By.cssSelector(".flex-card.visited-hotel .hotelName.fakeLink")).getText();
		stars = getDriver().findElement(By.cssSelector(".flex-card.visited-hotel .icon.stars-grey")).getAttribute("title");
		price = getDriver().findElement(By.cssSelector(".flex-card.visited-hotel .actualPrice.price.fakeLink ")).getText().replaceAll("\\D", "");
		return hotelName+"|"+stars+"|"+price;
	}
	
	public boolean validateSponsoredResultsFirst(){
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(hotelsResultsCardList)));
		if(hotelsResultsCardList.get(0).getAttribute("id").toLowerCase().contains("sponsored"))
			return true;
		else
			return false;
	}
	
	//Getters
	
	public WebElement getPackageResultHeader() {
		return packageResultHeader;
	}

	public WebElement getPackageResults() {
		return packageResults;
	}

	public WebElement getGoogleMapContainer() {
		return googleMapContainer;
	}
	
	public WebElement getPkgStepIndicator() {
		return pkgStepsIndicator;
	}
	
	public WebElement getSortOptionsCont() {
		return sortOptionsContainer;
	}
	
	public WebElement getEmailSignUpDiscountOffer() {
		return emailSignUpDiscountOffer;
	}
}
