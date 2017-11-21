package com.globant.trainingTae.test;

import java.time.LocalDate;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.globant.trainingTae.pages.FlightsSearchResultsPage;
import com.globant.trainingTae.pages.PaymentPage;
import com.globant.trainingTae.pages.TravelocityHomePage;
import com.globant.trainingTae.pages.TripDetailPage;

public class TravelocityTest extends BaseTest {
	
	private TravelocityHomePage home;
	private FlightsSearchResultsPage flightSearchResult;
	private TripDetailPage tripDetails;
	private PaymentPage payment;
	private SoftAssert softAssertions;
	
	//AssertionVariables
	private boolean selectButtonOnResults=false, flightDurationOnResult=false, flightDetailOnResults=false;
	
	
	//TestMethods
	@Test(groups={"excercise1"},dataProvider = "excercise1")
	public void testExcercise1(String departureCode, String arrivalCode, int adultPassanger, String sortResults, int position1, int position2){
		softAssertions = new SoftAssert();
		home = getTravelocityHomePage();
		
		//1. Search
		home.clickOnFligthButton();
		home.clickOnRoundTrip();
		home.selectAdultPassangers(adultPassanger);
		home.selectDepartureCity(departureCode);
		home.selectArrivalCity(arrivalCode);
		
		//Dates - Code to select currentDate plus 2 months
		//home.selectDepartureDate(date.split("/")[2],date.split("/")[1],date.split("/")[0]);			
		LocalDate futureDate = LocalDate.now().plusMonths(2);
		//System.out.println(futureDate.getDayOfMonth()+" "+futureDate.getMonthValue()+" "+futureDate.getYear());
		home.selectDepartureDate(futureDate.getYear()+"", (futureDate.getMonthValue()-1)+"", futureDate.getDayOfMonth()+"");
		
		flightSearchResult = home.clickOnSearchButton();
		
		//2. Verify Results Page
		softAssertions.assertNotNull(flightSearchResult.getDriver().findElement(By.name("sort")), "Sort button not found");
		checkElementsOnList(flightSearchResult.getDriver().findElements(By.cssSelector("#flightModuleList li")),"excercise1");
		softAssertions.assertTrue(selectButtonOnResults, "No all elements have the select button");
		softAssertions.assertTrue(flightDurationOnResult, "No all elements have the duration");
		softAssertions.assertTrue(flightDetailOnResults, "No all elements have the details link");
		
		//3. Sort
		flightSearchResult.sortResultList(sortResults);
		
		//4. Select first result
		flightSearchResult.selectDepartureFlight(position1);
		
		//5. Select third result
		tripDetails = flightSearchResult.selectReturnFlight(position2);
		
		//6. Verify trip details
		softAssertions.assertNotNull(tripDetails.getDriver().findElement(By.cssSelector(".packagePriceTotal")), "Trip total price is not present");
		softAssertions.assertTrue(((tripDetails.getDriver().findElements(By.cssSelector(".flex-card.flex-tile.details")).size() == 2) ? true : false), "Trip total price is not present");
		softAssertions.assertTrue(tripDetails.getDriver().getPageSource().contains("Price Guarantee"), "Price guarantee text is not present");		
		
		//7. Press Continue button
		payment = tripDetails.clickOnContinueBookingButton();
		
		//8. Verify who's traveling
		softAssertions.assertTrue(payment.getDriver().findElement(By.tagName("h1")).getText().equals("Secure booking - only takes a few minutes!"),"Header level one is not correct");		
		softAssertions.assertTrue(payment.getDriver().getTitle().equals("Travelocity: Payment"), "Payment title is not as expected");		
		softAssertions.assertNotNull(payment.getDriver().findElement(By.cssSelector(".allTravelerDetails")), "Traveler detail section not present");		
		softAssertions.assertNotNull(payment.getDriver().findElement(By.id("creditCardInput")), "Credit card number input not present");		
		softAssertions.assertNotNull(payment.getDriver().findElement(By.id("complete-booking")), "Complete booking button is not present");
				
		//AssertAll
		softAssertions.assertAll();
	}	
	
	
	//Checking aux methods
	private void checkElementsOnList(List<WebElement> listOfItems, String assertType){
		WebElement testElement;
		for (WebElement element: listOfItems) {
			
			if(assertType.equals("excercise1")){
				excersice1Checker(element);
			}
			if(assertType.equals("excercise2")){
				
			}
		      
		}
	}
	
	private void excersice1Checker(WebElement element){
		WebElement testElement;
		try{
			testElement = element.findElement(By.xpath("./div[2]/div/div[2]/div/button"));
			selectButtonOnResults=true;
		}catch(NoSuchElementException excep){
			selectButtonOnResults=false;
		}
		try{
			testElement = element.findElement(By.cssSelector("div[data-test-id='duration']"));
			flightDurationOnResult=true;
		}catch(NoSuchElementException excep){
			flightDurationOnResult=false;
		}
		try{
			testElement = element.findElement(By.cssSelector(".flight-details-link.toggle-trigger"));
			flightDetailOnResults=true;
		}catch(NoSuchElementException excep){
			flightDetailOnResults=false;
		}
	}
	
}
