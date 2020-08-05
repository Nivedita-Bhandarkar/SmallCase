package com.Flipkart_ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FK_HomePage {
WebDriver driver;
public FK_HomePage(WebDriver driver){
	this.driver=driver;
}

//This element is used to locate search bar 
private By searchBarinFK = By.xpath("//input[@type='text']");

//This element is used to  close the login pop-up
private By closeButton = By.cssSelector("._2AkmmA._29YdH8");

public WebElement enterSeachtext() {
	return driver.findElement(searchBarinFK);
}

public WebElement closePopupButton() {
	return driver.findElement(closeButton);
}

}
