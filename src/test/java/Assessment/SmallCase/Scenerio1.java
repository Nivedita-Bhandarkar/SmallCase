package Assessment.SmallCase;

import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Flipkart_ObjectRepository.FK_CartPage;
import com.Flipkart_ObjectRepository.FK_HomePage;
import com.Flipkart_ObjectRepository.FK_ProductSearchPage;
import com.Flipkart_ObjectRepository.FK_SpecifiedProductPage;

import Resources.DriverInitializationBase;

public class Scenerio1 extends DriverInitializationBase {
	Logger getLogs = LogManager.getLogger(Scenerio1.class.getName());
	WebDriver driver;
	Properties fetchData;
	WebDriverWait wait;

	@BeforeTest(description = "This block will execute for initializing the driver and fetching the property file")
	public WebDriver initialize() throws IOException {
		// Driver is initialised
		driver = initializeRemotedriver();
		fetchData = propertyFile();
		getLogs.info("Flipkart Url :" + fetchData.getProperty("FlipkartURL"));
		//1.Go to Flipkart
		driver.get(fetchData.getProperty("FlipkartURL"));
		return driver;

	}

	@Test(description = "This Block will execute for searching the product")
	public void search_Product_from_Flipkart() {

		FK_HomePage landingScreenofFlipkart = new FK_HomePage(driver);
		getLogs.info("Pop-up for guestmode is closed");
		landingScreenofFlipkart.closePopupButton().click();
		getLogs.info("Product Name : " + fetchData.getProperty("ItemsToBeSearched"));
		//2.Search for the items
		landingScreenofFlipkart.enterSeachtext().sendKeys(fetchData.getProperty("ItemsToBeSearched"));
		landingScreenofFlipkart.enterSeachtext().sendKeys(Keys.ENTER);
	}

	@Test(dependsOnMethods = {"search_Product_from_Flipkart" }, description = "This Block with excute for groups of product after search")
	public void groups_Of_products_Screen_flipkart() {
		FK_ProductSearchPage flipKartproductList = new FK_ProductSearchPage(driver);
		//3. Click on the first item list
		flipKartproductList.selectProduct().get(0).click();
		Set<String> windowIDs = driver.getWindowHandles();
		Iterator<String> iterate = windowIDs.iterator();
		while (iterate.hasNext()) {

			driver.switchTo().window(iterate.next());
		}
	}

	@Test(dependsOnMethods = {"groups_Of_products_Screen_flipkart" }, description = "This block is used to execute to add an item to the cart")
	public void add_Item_to_Cart_fk() {
		FK_SpecifiedProductPage selectedProduct = new FK_SpecifiedProductPage(driver);
		//4. Prints the price of the item
		String productPrice=selectedProduct.getPricetext().getText();
		getLogs.info("The price of product before adding to cart" +productPrice );
		//5. Add to cart in guest mode.
		selectedProduct.addTocart().click();
	}

	@Test(dependsOnMethods = { "add_Item_to_Cart_fk" }, description = "This block is used to execute the cart")
	public void cart_Screen_of_Flipkart() {
		//6. Go to Cart Page.
		FK_CartPage cartScreenofFlipkart = new FK_CartPage(driver);
		wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(cartScreenofFlipkart.increaseQuantityinCart));
		//7. Increase Quantity by 1.
		cartScreenofFlipkart.increaseQuantity().click();
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfElementLocated(cartScreenofFlipkart.cartPrice));
		//8. Print the Price after addition of Quantity.
		getLogs.info("The price of the product after increasing the quantity: "+ cartScreenofFlipkart.getCartprice().getText());
	}

	@AfterTest
	public void teardown() {
		getLogs.info("Close the browser");
		driver.quit();
	}

}
