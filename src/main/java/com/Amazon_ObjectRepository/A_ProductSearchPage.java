package com.Amazon_ObjectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class A_ProductSearchPage {

	WebDriver driver;
	public A_ProductSearchPage(WebDriver driver) {
		this.driver = driver;	
	}
	
	// This element is used to the locate products after the product search
	private By productListofAmazon = By.cssSelector(".a-link-normal.a-text-normal");

	
	public List<WebElement> selectProduct() {
		return driver.findElements(productListofAmazon);
		
	}
}
