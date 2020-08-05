package com.Flipkart_ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FK_CartPage {
WebDriver driver;
	public FK_CartPage(WebDriver driver) {
		this.driver = driver;	
	}
	
	// This element is used to locate increase the quantity of the product
	public By increaseQuantityinCart = By.xpath("//div[@class='_3md1dr']/button[2]");
	
	// This element is used to locate price text of the product
	public By cartPrice = By.xpath("//span[@class='pMSy0p XU9vZa']");
	
	// This element is used to locate name of the product
	private By productNameinCart = By.cssSelector("._1Ox9a7");
	
	// This element is used to locate colour of the product
	private By productColourinCart = By.cssSelector(".v7-Wbf");
	
	public WebElement increaseQuantity() {
		return driver.findElement(increaseQuantityinCart);
	}
	
	public WebElement getCartprice() {
		return driver.findElement(cartPrice);
	}
	
	public WebElement getProductname() {
		return driver.findElement(productNameinCart);
	}
	
	public WebElement getProductcolour() {
		return driver.findElement(productColourinCart);
	}
}
