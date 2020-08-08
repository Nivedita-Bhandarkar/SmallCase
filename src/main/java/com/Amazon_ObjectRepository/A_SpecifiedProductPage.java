package com.Amazon_ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class A_SpecifiedProductPage {
	WebDriver driver;

	public A_SpecifiedProductPage(WebDriver driver) {
		this.driver = driver;
	}

	// This element is used to locate price text of specific product
	private By productPricetextAmazon = By.cssSelector("#priceblock_dealprice");

	// This element is used to locate Add to Cart button
	private By addProducttoCart = By.cssSelector("#mbc-buybutton-addtocart-1-announce");
	

	
	// This element is used to locate Go to Cart button
	private By goTocartAmazon= By.cssSelector("#hlb-view-cart-announce");
	
	public WebElement getPricetext() {
		return driver.findElement(productPricetextAmazon);
	}

	public WebElement clickAddtoCart() {
		return driver.findElement(addProducttoCart);
	}
	
	public WebElement clickGotoCart() {
		return driver.findElement(goTocartAmazon);
	}

}
