package com.Amazon_ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class A_HomePage {
	WebDriver driver;

	public A_HomePage(WebDriver driver) {
		this.driver = driver;
	}

	// This element is used to locate search text of the Amazon screen
	private By searchBarofAmazon = By.cssSelector("#twotabsearchtextbox");

	public WebElement getSearchProduct() {
		return driver.findElement(searchBarofAmazon);
	}

}
