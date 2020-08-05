package com.Flipkart_ObjectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FK_ProductSearchPage {
WebDriver driver;
	public FK_ProductSearchPage(WebDriver driver) {
		this.driver = driver;	
	}
	
	// This element is used to the locate products after the product search
	private By productListofFK = By.cssSelector("._3liAhj");

	
	public List<WebElement> selectProduct() {
		return driver.findElements(productListofFK);
		
	}
	

	
	
}
