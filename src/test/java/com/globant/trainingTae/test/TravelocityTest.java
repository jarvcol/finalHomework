package com.globant.trainingTae.test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.globant.trainingTae.pages.BookRoomsSelectionPage;
import com.globant.trainingTae.pages.FlightsPlusHotelSearchResultsPage;
import com.globant.trainingTae.pages.FlightsSearchResultsPage;
import com.globant.trainingTae.pages.PackageTripDetailPage;
import com.globant.trainingTae.pages.PaymentPage;
import com.globant.trainingTae.pages.TravelocityHomePage;
import com.globant.trainingTae.pages.TripDetailPage;

public class TravelocityTest extends BaseTest {
	
	private TravelocityHomePage home;
	private FlightsSearchResultsPage flightSearchResult;
	private TripDetailPage tripDetails;
	private PackageTripDetailPage pkgtripDetails;
	private PaymentPage payment;
	private FlightsPlusHotelSearchResultsPage flightHotelSearchResult;
	private BookRoomsSelectionPage bookRoomsSelectionPage;
	private SoftAssert softAssertions;
	
	
	//TestMethods
	@Test(groups={"excercise1"},dataProvider = "excercise1")
	public void testExcercise1(String departureCode, String arrivalCode, int adultPassanger, 
			int plusD, int plusM, int plusY, String sortResults, int positionFlight1, int positionFlight2){
		
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
		flightSearchResult.closeOtherWindows();
		softAssertions.assertNotNull(flightSearchResult.getSortButton(), "Sort button not found on the flight results page");
		softAssertions.assertTrue(flightSearchResult.testAllElementsHaveSelectButton(), "No all flight results have the select button");
		softAssertions.assertTrue(flightSearchResult.testAllElementsHaveFlightDuration(), "No all flight results have the duration");
		softAssertions.assertTrue(flightSearchResult.testAllElementsHaveFlightDetails(), "No all flight results have the details link");
		
		//3. Sort
		flightSearchResult.sortResultList(sortResults);
		softAssertions.assertTrue(flightSearchResult.validateSortOfResults(sortResults), "Flight list result is not sort as expected");
		
		//4. Select first result
		flightSearchResult.selectDepartureFlight(positionFlight1);
		
		//5. Select third result
		tripDetails = flightSearchResult.selectReturnFlight(positionFlight2);
		tripDetails.switchWindow();
		
		//6. Verify trip details
		softAssertions.assertNotNull(tripDetails.getPackagePriceTotal(), "Trip total price is not present on the trip detail page");
		softAssertions.assertTrue(((tripDetails.getTripDetailSection().size() == 2) ? true : false), "Trip details is not present on the trip detail page");
		softAssertions.assertTrue(tripDetails.validateGuarateePrice(), "Price guarantee text is not present on the trip detail page");		
		
		//7. Press Continue button
		payment = tripDetails.clickOnContinueBookingButton();
		
		//8. Verify who's traveling
		softAssertions.assertTrue(payment.getHeaderLev1().getText().equals("Secure booking - only takes a few minutes!"),"Header level one is not correct on the payment page");		
		softAssertions.assertTrue(payment.getPageTitle().equals("Travelocity: Payment"), "Page title is not as expected on the payment page");		
		softAssertions.assertNotNull(payment.getTravelerDetailSection(), "Traveler detail section not present on the payment page");		
		softAssertions.assertNotNull(payment.getCreditCardNumberInput(), "Credit card number input not present on the payment page");		
		softAssertions.assertNotNull(payment.getCompleteBookingButton(), "Complete booking button is not present on the payment page");
				
		//AssertAll
		softAssertions.assertAll();
	}
	
	@Test(groups={"excercise2"},dataProvider = "excercise2")
	public void testExcercise2(String departureCode, String arrivalCode, int adultPassanger, 
			int plusD, int plusM, int plusY, int tripD, int tripM, int tripY, String sortResults, String stars,
			int positionHotel, int positionFlight1, int positionFlight2){
		
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
		softAssertions.assertNotNull(flightHotelSearchResult.getPackageResultHeader(), "There is no header section on the package hotel results page");
		softAssertions.assertNotNull(flightHotelSearchResult.getPackageResults(), "There are no results on the package hotel results page");
		softAssertions.assertNotNull(flightHotelSearchResult.getGoogleMapContainer(), "There is no google map section on the package hotel results page");
		softAssertions.assertNotNull(flightHotelSearchResult.getPkgStepIndicator(), "There is no step indicator on the package hotel results page");
		softAssertions.assertNotNull(flightHotelSearchResult.getSortOptionsCont(), "There is no sort options on the package hotel results page");
		
		//4. Sort by price. Verify the results were correctly sorted
		flightHotelSearchResult.sortByOption(sortResults);
		softAssertions.assertTrue(flightHotelSearchResult.validateSortOfResults(sortResults), "List of results is not sort as expected");
		
		//5. Select the first result with at least 3 stars
		bookRoomsSelectionPage = flightHotelSearchResult.selectPackageBy("Stars",stars);
		
		//6. In the new page, verify the hotel is the selected in the previous step
		softAssertions.assertTrue(bookRoomsSelectionPage.verifyCorrectHotelSelected(flightHotelSearchResult.getdataToValidateNextPage()), "Room selection page is not showing the selected hotel");
		
		//7. Select the first room option 
		flightSearchResult = bookRoomsSelectionPage.selectRoomOption(positionHotel);
		
		//8. In the new page,(Now select your departing flight), select the first result.
		flightSearchResult.selectDepartureFlight(positionFlight1);
		
		//9. In the new page (Now select your return flight), select the third result. 
		pkgtripDetails = flightSearchResult.selectReturnFlightPkg(positionFlight2);
		
		//10. Select a car
		pkgtripDetails.clickOnFirstCarBook();
		
		//11. Verify Trip Details.Press Continue button //Page where car is selected
		//Asserts
		softAssertions.assertNotNull(pkgtripDetails.getContinueButtonsList(), "Package trip detail page does not have the continue button");
		softAssertions.assertNotNull(pkgtripDetails.getSaveTripLink(), "Package trip detail page does not have the save the trip link");
		softAssertions.assertNotNull(pkgtripDetails.getTripTotal(), "Package trip detail page does not have the trip total value");
		softAssertions.assertNotNull(pkgtripDetails.getCarButtonsList(), "Package trip detail page does not have the add car buttons");
		softAssertions.assertTrue(pkgtripDetails.validateFlightRoute(departureCode, arrivalCode), "Package trip detail page does the expected airport codes");
		payment = pkgtripDetails.clickOnContinueButton();
		
		//12. Verify the trip details are still correct. Continue //13. Verify the “Who’s travelling” page
		//Asserts
		softAssertions.assertTrue(payment.getPageTitle().equals("Travelocity: Payment"), "Payment title is not as expected");
		softAssertions.assertNotNull(payment.getProgressBar(),"Package payment does not have the progress bar");
		softAssertions.assertNotNull(payment.getTravelerDetailSection(), "Package payment does not have the traveler detail section");
		softAssertions.assertNotNull(payment.getTripSummarySection(),"Package payment does not have the trip summary detail section");
		softAssertions.assertNotNull(payment.getHotelDetailSection(),"Package payment does not have the hotel detail section");
		
		softAssertions.assertAll();
	}	
	
	
	
	//Test aux methods
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
