package com.Flipkart_ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FK_SpecifiedProductPage {
	WebDriver driver;

	public FK_SpecifiedProductPage(WebDriver driver) {
		this.driver = driver;
	}

	// This element is used to locate price text of specific product
	private By productPricetextFK = By.cssSelector("._1vC4OE._3qQ9m1");

	// This element is used to locate Add to Cart button
	private By addProducttoCart = By.cssSelector("._2AkmmA._2Npkh4._2MWPVK");

	public WebElement getPricetext() {
		return driver.findElement(productPricetextFK);
	}

	public WebElement addTocart() {
		return driver.findElement(addProducttoCart);
	}

}
