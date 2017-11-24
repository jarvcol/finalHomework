package com.globant.trainingTae.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.StaleElementReferenceException;

public class FlightsPlusHotelSearchResultsPage extends BasePage {

	public FlightsPlusHotelSearchResultsPage(WebDriver pDriver) {
		//Add 10 more seconds cause this page option tends to be so painful slow
		super(pDriver,20);
		getWait().until(ExpectedConditions.elementToBeClickable(priceSortButton));
	}
	
	//Locators......!!!!!!!!!!!!!!!!!
	
	@FindBy(css="button[data-opt-group='Price']")
	private WebElement priceSortButton;
	
	@FindBy(id="resultsContainer")
	private WebElement packageResults;
		
		
	//Methods......!!!!!!!!!!!!!!!!!
		
	public void sortByOption(String sortOption){
		switch (sortOption) {
		case "Price":
			priceSortButton.click();
			break;
		default:
			break;
		}
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("article"))));
	}
	
	public BookRoomsSelectionPage selectPackageBy(String sortOption, String value){
		List<WebElement> resultsList = getDriver().findElements(By.tagName("article"));
		switch (sortOption) {
		case "Stars":
			return selectPackageByStars(resultsList,value);
		default:
			return null;
		}
	}
	
	public BookRoomsSelectionPage selectPackageByStars(List<WebElement> resultsList,String starsNumber){
		WebElement testElement; double starsHotelValue;
		for (WebElement element: resultsList) {
			getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
			testElement = element.findElement(By.xpath("//span[@class='icon icon-stars3-5 stars-grey value-title']"));
			starsHotelValue = Double.parseDouble(testElement.getAttribute("title"));
			if(starsHotelValue >= Double.parseDouble(starsNumber)){
				testElement.click();
				return new BookRoomsSelectionPage(getDriver());
			}	
		}
		//Default in case hotel match, selects first element
		resultsList.get(0).click();
		return new BookRoomsSelectionPage(getDriver()); 
	}
}
	
	/*
	 
	//Locators for cards information
	//Locators for linkCards
	//article
	//.flex-link[data-track]
	//.flex-card > .flex-link-wrap
	//.flex-content.info-and-price.CITY
	//#resultsContainer
	  
	Option 1
	public void selectPackageBy(String byOption, String value){
		List<WebElement> resultsList = getDriver().findElements(By.tagName("article"));
		switch (byOption) {
		case "Stars":
			selectPackageByStars(resultsList, Integer.parseInt(value));
			break;
		default:
			break;
		}
	}
	
	public void selectPackageByStars(List<WebElement> resultsList, int starsNumber){
		WebElement testElement; int i=1;
		for (WebElement element: resultsList) {
			try{
				getWait().until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
				System.out.println(i);
				System.out.println(element.getAttribute("id"));
				System.out.println(element.getAttribute("class"));
				i++;
			}catch(StaleElementReferenceException exc){
				i++;
			}
			//testElement = element.findElement(By.xpath("./div[2]/div/div[1]/div[2]/ul[1]/li[7]/strong/span[2]"));
			/*
			testElement = element.findElement(By.xpath("./div[2]/div/div[1]/div[2]/ul[1]/li[7]/strong/span[2]"));
			if(Double.parseDouble(testElement.getAttribute("title").toString()) >= starsNumber){
				testElement.click();
			}
			
		    
		}
	
	Option 2
	public BookRoomsSelectionPage selectPackageBy(String byOption, String value){
		List<WebElement> resultsList = getDriver().findElements(By.tagName("article"));
		switch (byOption) {
		case "Stars":
			return selectPackageByStars(resultsList, Integer.parseInt(value));
		default:
			return null;
		}
	}
	
	public BookRoomsSelectionPage selectPackageByStars(List<WebElement> resultsList, int starsNumber){
		WebElement testElement = resultsList.get(0);
		System.out.println(testElement.getAttribute("id"));
		System.out.println(testElement.getAttribute("class"));
		testElement.click();
		return new BookRoomsSelectionPage(getDriver());
	}
	
	Option 3
	public void sortByOption(String sortOption){
		switch (sortOption) {
		case "Price":
			priceSortButton.click();
			break;
		default:
			break;
		}
		getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("article")));
	}
	
	public BookRoomsSelectionPage selectPackageBy(String byOption, String value){
		List<WebElement> resultsList = getDriver().findElements(By.tagName("article"));
		getWait().until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(resultsList.get(0))));
		resultsList.get(0).click();
		return new BookRoomsSelectionPage(getDriver());
	}

*/
