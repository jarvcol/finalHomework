package com.globant.trainingTae.test;

import org.testng.annotations.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import com.globant.trainingTae.pages.MyDriver;
import com.globant.trainingTae.pages.TravelocityHomePage;;

public class BaseTest {

	MyDriver myDriver;
	
	private TravelocityHomePage traelocityHome;
	
	@BeforeTest(alwaysRun=true)
	@Parameters({"browser"})
	public void beforeSuite(String browser){
		myDriver = new MyDriver(browser);
		traelocityHome = new TravelocityHomePage(myDriver.getDriver());
	}
	
	@AfterTest(alwaysRun=true)
	public void afterSuite(){
		traelocityHome.dispose();
	}
	
	public TravelocityHomePage getTravelocityHomePage(){
		return traelocityHome;
	}
	
	@DataProvider(name="excercise1")
	public Object[][] dataProviderExc1(){
		return new Object[][]{
			{"LAS","LAX",1,0,2,0,"duration:asc",1,3}
		};
	}
	
	@DataProvider(name="excercise2")
	public Object[][] dataProviderExc2(){
		return new Object[][]{
			{"LAS","LAX",1,0,2,0,13,0,0,"Price","3",1,1,3}
		};
	}
	
	@DataProvider(name="excercise3")
	public Object[][] dataProviderExc3(){
		return new Object[][]{
			{"Montevideo, Uruguay"}
		};
	}
	
	@DataProvider(name="excercise4")
	public Object[][] dataProviderExc4(){
		return new Object[][]{
			{"LAS","LAX",13,0,0,"Your partial check-in and check-out dates must fall within your arrival and departure dates. Please review your dates.","package"}
		};
	}
	
	@DataProvider(name="excercise5")
	public Object[][] dataProviderExc5(){
		return new Object[][]{
			{"europe",4,"10-14"}
		};
	}
}
