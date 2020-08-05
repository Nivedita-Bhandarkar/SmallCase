package com.Amazon_ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class A_CartPage {
	WebDriver driver;

	public A_CartPage(WebDriver driver) {
		this.driver = driver;
	}

	// This element is used to locate price text of the product
	private By cartPricevalueAmazon = By.cssSelector(".a-spacing-small span");

	public WebElement getCartprice() {
		return driver.findElement(cartPricevalueAmazon);
	}

}
