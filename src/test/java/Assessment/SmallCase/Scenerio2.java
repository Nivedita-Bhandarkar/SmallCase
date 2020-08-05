package Assessment.SmallCase;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
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

import com.Amazon_ObjectRepository.A_CartPage;
import com.Amazon_ObjectRepository.A_HomePage;
import com.Amazon_ObjectRepository.A_ProductSearchPage;
import com.Amazon_ObjectRepository.A_SpecifiedProductPage;
import com.Flipkart_ObjectRepository.FK_CartPage;

import Resources.DriverInitializationBase;

public class Scenerio2 extends DriverInitializationBase {
	Logger getLogs =LogManager.getLogger(Scenerio2.class.getName());
	Properties fetchData;
	WebDriver driver;
	WebDriverWait wait;
	Scenerio1 invokeMethods;
	int cartPriceofFlipKart;
	String flipkartSelectedproductCompletename = null;
	String flipkartSelectedproductName = null;
	int cartPriceofAmazon;

	@BeforeTest(alwaysRun = true, description = "This block executes from required methods present in Scenerio1")
	public void initialize() throws IOException {

		invokeMethods = new Scenerio1();
		getLogs.info("The method is calling from Scenerio1");
		driver = invokeMethods.initialize();
		fetchData = propertyFile();
		invokeMethods.search_Product_from_Flipkart();
		invokeMethods.groups_Of_products_Screen_flipkart();
		invokeMethods.add_Item_to_Cart_fk();
	}

	@Test(priority = 0)
	public void cart_Screen_of_Flipkart() throws ParseException {

		FK_CartPage cartScreenofFlipkart = new FK_CartPage(driver);
		wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(cartScreenofFlipkart.cartPrice));

		String flipkartSelectedproductPrice = cartScreenofFlipkart.getCartprice().getText();
		getLogs.info("The price of the product in FLipkart is: "+flipkartSelectedproductPrice);
		NumberFormat textFormatter = NumberFormat.getCurrencyInstance();
		Number number = textFormatter.parse(flipkartSelectedproductPrice);
		cartPriceofFlipKart = number.intValue();

		flipkartSelectedproductName = cartScreenofFlipkart.getProductname().getText();
		String flipkartSelectedproductColour = cartScreenofFlipkart.getProductcolour().getText();
		flipkartSelectedproductCompletename = flipkartSelectedproductName.concat(" " + flipkartSelectedproductColour);
		//System.out.println(flipkartSelectedproductCompletename);
		getLogs.info("The name of the product selected is: "+flipkartSelectedproductCompletename);
		Set<String> id = driver.getWindowHandles();
		Iterator<String> it = id.iterator();
		driver.switchTo().window(it.next());
	}

	@Test(priority = 1)
	public void amazon_Search() {
		getLogs.info("The amazon url is: "+fetchData.getProperty("AmazonURL"));
		driver.get(fetchData.getProperty("AmazonURL"));

		A_HomePage landingScreenofAmazon = new A_HomePage(driver);
		landingScreenofAmazon.getSearchProduct().sendKeys(flipkartSelectedproductCompletename);
		landingScreenofAmazon.getSearchProduct().sendKeys(Keys.ENTER);

	}

	@Test(priority = 1)
	public void groups_Of_products_Screen_of_Amazon() {
		A_ProductSearchPage productsOfamazon = new A_ProductSearchPage(driver);
		int listOfProducts = productsOfamazon.selectProduct().size();
		for (int i = 0; i < listOfProducts; i++) {
			String searchedProductnameText = productsOfamazon.selectProduct().get(i).getText();
			if (searchedProductnameText.contains(flipkartSelectedproductName)) {
				productsOfamazon.selectProduct().get(i).click();
				break;
			}
		}
		Set<String> ids = driver.getWindowHandles();
		Iterator<String> iterators = ids.iterator();
		while (iterators.hasNext()) {
			driver.switchTo().window(iterators.next());
		}

	}

	@Test(priority = 2)
	public void obtained_Product_screen() throws ParseException {

		A_SpecifiedProductPage selectedProduct = new A_SpecifiedProductPage(driver);
		String amazonProductprice=selectedProduct.getPricetext().getText();
		getLogs.info("The price of the amazon product before adding to cart: "+amazonProductprice);
		selectedProduct.clickAddtoCart().click();
		selectedProduct.clickGotoCart().click();
	}

	@Test(priority = 3)
	public void cart_Screen_amazon() throws ParseException {
		A_CartPage cartScreenofAmazon = new A_CartPage(driver);
		String priceOftheAmazonproduct = cartScreenofAmazon.getCartprice().getText().trim();
		NumberFormat format = NumberFormat.getNumberInstance();
		Number number = format.parse(priceOftheAmazonproduct);
		cartPriceofAmazon = number.intValue();
		getLogs.info("The price of the amazon product before adding to cart: "+cartPriceofAmazon);
	}

	@Test(priority = 4)
	public void product_Comparison() throws ParseException {

		if (cartPriceofFlipKart < cartPriceofAmazon) {
			getLogs.info(fetchData.getProperty("FlipkartURL") + " Price of the product :"+ flipkartSelectedproductCompletename + " is: " + cartPriceofFlipKart);
		} else if (cartPriceofFlipKart == cartPriceofAmazon) {
			getLogs.info("Both sites are having same price");
		} else {

			getLogs.info(fetchData.getProperty("AmazonURL") + " Price of the product :"
					+ flipkartSelectedproductCompletename + " is: " + cartPriceofAmazon);
		}
	}

	@AfterTest
	public void teardown() {
		driver.quit();

	}
}
