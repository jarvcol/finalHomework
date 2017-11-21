package com.globant.trainingTae.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PaymentPage extends BasePage{

	public PaymentPage(WebDriver pDriver) {
		super(pDriver);
		getWait().until(ExpectedConditions.elementToBeClickable(By.id("complete-booking")));	}

}
