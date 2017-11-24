package com.globant.trainingTae.test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.globant.trainingTae.pages.BookRoomsSelectionPage;
import com.globant.trainingTae.pages.FlightsPlusHotelSearchResultsPage;
import com.globant.trainingTae.pages.FlightsSearchResultsPage;
import com.globant.trainingTae.pages.PaymentPage;
import com.globant.trainingTae.pages.TravelocityHomePage;
import com.globant.trainingTae.pages.TripDetailPage;

public class TravelocityTest extends BaseTest {
	
	private TravelocityHomePage home;
	private FlightsSearchResultsPage flightSearchResult;
	private TripDetailPage tripDetails;
	private PaymentPage payment;
	private FlightsPlusHotelSearchResultsPage flightHotelSearchResult;
	private BookRoomsSelectionPage bookRoomsSelectionPage;
	private SoftAssert softAssertions;
	
	//AssertionVariables
	private boolean selectButtonOnResults=false, flightDurationOnResult=false, flightDetailOnResults=false, sortCorrect=false;
	
	
	//TestMethods
	@Test(groups={"excercise1"},dataProvider = "excercise1")
	public void testExcercise1(String departureCode, String arrivalCode, int adultPassanger, 
			int plusD, int plusM, int plusY, String sortResults, int position1, int position2){
		
		softAssertions = new SoftAssert();
		home = getTravelocityHomePage();
		
		//1. Search
		home.clickOnFligthButton();
		home.clickOnRoundTrip();
		home.selectAdultPassangers(adultPassanger);
		home.selectFlightDepartureCity(departureCode);
		home.selectFlightArrivalCity(arrivalCode);
		
		//Dates - Code to select currentDate plus 2 months		
		LocalDate departureDate = setTestDate(plusD, plusM, plusY);
		home.selectDepartureDate(departureDate.getYear()+"", (departureDate.getMonthValue()-1)+"", departureDate.getDayOfMonth()+"","flight");
		
		flightSearchResult = home.clickOnSearchButton();
		
		//2. Verify Results Page
		softAssertions.assertNotNull(flightSearchResult.getSortButton(), "Sort button not found");
		checkElementsOnList(flightSearchResult.getListOfElementsToCheck(),"validateComponents");
		softAssertions.assertTrue(selectButtonOnResults, "No all elements have the select button");
		softAssertions.assertTrue(flightDurationOnResult, "No all elements have the duration");
		softAssertions.assertTrue(flightDetailOnResults, "No all elements have the details link");
		
		//3. Sort
		flightSearchResult.sortResultList(sortResults);
		//checkElementsOnList(flightSearchResult.getListOfElementsToCheck(),"validateSort");
		//softAssertions.assertTrue(sortCorrect,"Invalid sort order");
		
		//4. Select first result
		flightSearchResult.selectDepartureFlight(position1);
		
		//5. Select third result
		tripDetails = flightSearchResult.selectReturnFlight(position2);
		
		//6. Verify trip details
		softAssertions.assertNotNull(tripDetails.getPackagePriceTotal(), "Trip total price is not present");
		softAssertions.assertTrue(((tripDetails.getTripDetailSection().size() == 2) ? true : false), "Trip details is not present");
		softAssertions.assertTrue(tripDetails.validateGuarateePrice(), "Price guarantee text is not present");		
		
		//7. Press Continue button
		payment = tripDetails.clickOnContinueBookingButton();
		
		//8. Verify who's traveling
		softAssertions.assertTrue(payment.getHeaderLev1().getText().equals("Secure booking - only takes a few minutes!"),"Header level one is not correct");		
		softAssertions.assertTrue(payment.getPageTitle().equals("Travelocity: Payment"), "Payment title is not as expected");		
		softAssertions.assertNotNull(payment.getTravelerDetailSection(), "Traveler detail section not present");		
		softAssertions.assertNotNull(payment.getCreditCardNumberInput(), "Credit card number input not present");		
		softAssertions.assertNotNull(payment.getCompleteBookingButton(), "Complete booking button is not present");
				
		//AssertAll
		softAssertions.assertAll();
	}
	
	@Test(groups={"excercise2"},dataProvider = "excercise2")
	public void testExcercise2(String departureCode, String arrivalCode, int adultPassanger, 
			int plusD, int plusM, int plusY, int tripD, int tripM, int tripY, String sortResults, String stars,
			int position1, int position2){
		
		softAssertions = new SoftAssert();
		home = getTravelocityHomePage();
		
		//1. Go to Flight + Hotel
		//home.clickOnFlightPlusHotelButton();
		
		//2. Search for a flight from LAS to LAX
		home.selectAdultPassangers(adultPassanger);
		home.selectPackageDepartureCity(departureCode);
		home.selectPackageArrivalCity(arrivalCode);
		//Dates		
		LocalDate departureDate = setTestDate(plusD, plusM, plusY);
		home.selectDepartureDate(departureDate.getYear()+"", (departureDate.getMonthValue()-1)+"", departureDate.getDayOfMonth()+"","package");
		LocalDate returningDate = setTestDatePlusDays(departureDate, tripD, tripM, tripY);
		home.selectArrivalDate(returningDate.getYear()+"", (returningDate.getMonthValue()-1)+"", returningDate.getDayOfMonth()+"","package");
		
		flightHotelSearchResult = home.clickOnFlightPlusHotelSearchButton();
		
		//3. Verify results page
		
		//4. Sort by price. Verify the results were correctly sorted
		flightHotelSearchResult.sortByOption(sortResults);
		
		//5. Select the first result with at least 3 stars
		bookRoomsSelectionPage = flightHotelSearchResult.selectPackageBy("Stars",stars);
		
		//6. In the new page, verify the hotel is the selected in the previous step
		
		//7. Select the first room option 
		//flightSearchResult = bookRoomsSelectionPage.selectRoomOption();
		
		//8. In the new page,(Now select your departing flight), select the first result.
		//flightSearchResult.selectDepartureFlight(position1);
		
		//9. In the new page (Now select your return flight), select the third result. 
		//flightSearchResult.selectReturnFlight(position2);
		
		//10. Select a car
		
		//11. Verify Trip Details.Press Continue button
		
		//12. Verify the trip details are still correct. Continue
		
		//13. Verify the “Who’s travelling” page
		
	}	
	
	
	//Test  aux methods
	private void checkElementsOnList(List<WebElement> listOfItems, String assertType){
		String duration=null;
		for (WebElement element: listOfItems) {
			
			if(assertType.equals("validateComponents")){
				validateComponents(element);
			}
			if(assertType.equals("validateSort")){
				sortCorrect = validateSort(element, duration);
				duration = element.findElement(By.xpath("//span[@data-test-id='duration']")).getText();
				//System.out.println(duration);
				if(!sortCorrect){
					break;
				}
			}
		      
		}
	}
	
	private boolean validateSort(WebElement element, String prevValue){
		Duration currentDuration, prevDurarion;
		String currentValue;
		if(prevValue != null){
			currentValue = element.findElement(By.xpath("//span[@data-test-id='duration']")).getText();
			//Convert each StringValue to a totalDuration
			if(prevValue.matches("^\\d+[h].+")){
				prevDurarion = Duration.ofHours(Long.parseLong(prevValue.split("h")[0]));
				prevDurarion = prevDurarion.plus(Duration.ofHours(Long.parseLong(prevValue.split(" ")[1].replaceAll("m", ""))));
				System.out.println("Horas: "+prevValue);
				System.out.println("Convert: "+prevDurarion.toMinutes());
			}
			if(prevValue.matches("^\\d+[m]")){
				prevDurarion = Duration.ofHours(Long.parseLong(prevValue.replaceAll("m", "")));
				System.out.println("Horas: "+prevValue);
				System.out.println("Convert: "+prevDurarion.toMinutes());
			}		
		}
		return true;
	}
	
	private void validateComponents(WebElement element){
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
	
	private LocalDate setTestDatePlusDays(LocalDate currentDate, int plusDays, int plusMonths, int plusYears){
		currentDate = currentDate.plusYears(plusYears);
		currentDate = currentDate.plusMonths(plusMonths);
		currentDate = currentDate.plusDays(plusDays);
		return currentDate;
	}
	
	private LocalDate setTestDate(int plusDays, int plusMonths, int plusYears){
		LocalDate futureDate = LocalDate.now().plusYears(plusYears);
		futureDate = futureDate.plusMonths(plusMonths);
		futureDate = futureDate.plusDays(plusDays);
		return futureDate;
	}
}

//Duration

/*
private boolean validateSort(WebElement element, String prevValue){
Duration currentDuration, prevDurarion;
String currentValue;
if(prevValue != null){
	try{
		currentValue = element.findElement(By.xpath("./div[2]/div/div[1]/div/div[2]/div/div/div[2]/div[1]")).getText();
		if(Integer.parseInt(prevValue.split(" ")[0].replaceAll("h", "")) < Integer.parseInt(currentValue.split(" ")[0].replaceAll("h", ""))){
			return true;
		}else{
			if(Integer.parseInt(prevValue.split(" ")[0].replaceAll("h", "")) == Integer.parseInt(currentValue.split(" ")[0].replaceAll("h", ""))){
				if(Integer.parseInt(prevValue.split(" ")[1].replaceAll("m", "")) <= Integer.parseInt(currentValue.split(" ")[1].replaceAll("m", ""))){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}
	}catch(NoSuchElementException exce){
		//This case is to avoid the error made by banner include on results
		return true;
	}
}
else{
	return true;
}
}*/