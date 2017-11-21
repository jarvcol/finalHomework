package com.globant.trainingTae.test;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import com.globant.trainingTae.pages.MyDriver;
import com.globant.trainingTae.pages.TravelocityHomePage;;

public class BaseTest {

	MyDriver myDriver;
	
	private TravelocityHomePage traelocityHome;
	
	@BeforeSuite
	@Parameters({"browser"})
	public void beforeSuite(String browser){
		myDriver = new MyDriver(browser);
		traelocityHome = new TravelocityHomePage(myDriver.getDriver());
	}
	
	@AfterSuite(alwaysRun=true)
	public void afterSuite(){
		traelocityHome.dispose();
	}
	
	public TravelocityHomePage getTravelocityHomePage(){
		return traelocityHome;
	}
	
	@DataProvider(name="excercise1")
	public Object[][] dataProviderExc1(){
		return new Object[][]{
			{"LAS","LAX",1,"duration:asc",1,3}
		};
	}
	
	@DataProvider(name="excercise2")
	public Object[][] dataProviderExc2(){
		return new Object[][]{
			{"LAS","LAX",1,"19/0/2018",13,"duration:asc",3}
		};
	}
}
