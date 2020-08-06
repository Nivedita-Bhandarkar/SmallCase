package Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class DriverInitializationBase {
Logger getLogs =LogManager.getLogger(DriverInitializationBase.class.getName());

	WebDriver driver;
	Properties dataFileforAssessment;
	String directoryPathofAssessment = System.getProperty("user.dir");
	FileInputStream readDatafile;

	// Below method is to initialize Remote Driver
	public WebDriver initializeRemotedriver() throws IOException {

		// Get the browsername during runtime
		propertyFile();
		String browserName = dataFileforAssessment.getProperty("browser");
		getLogs.info("The browser is : "+browserName);
		
		// This block will execute if user invokes chrome browser
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",directoryPathofAssessment + "\\src\\test\\java\\Resources\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		// This block will execute if user invokes firefox browser
		else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",directoryPathofAssessment + "\\src\\test\\java\\Resources\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		// This block will execute if user invokes internet explorer browser
		else if (browserName.equalsIgnoreCase("internet")) {
			System.setProperty("webdriver.ie.driver",directoryPathofAssessment + "\\src\\test\\java\\Resources\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();

			// This block will execute if user invokes edge browser
		} else {
			System.setProperty("webdriver.edge.driver",directoryPathofAssessment + "\\src\\test\\java\\Resources\\msedgedriver.exe");
			driver = new EdgeDriver();
		}

		// This is to set a wait time if application is delayed to operate
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		return driver;

	}

	public Properties propertyFile() throws IOException {
		dataFileforAssessment = new Properties();
		readDatafile = new FileInputStream(directoryPathofAssessment + "\\src\\main\\java\\Resources\\DataFile.properties");
		dataFileforAssessment.load(readDatafile);
		return dataFileforAssessment;
	}
	
	public String getscreenshot(String testcasename, WebDriver driver) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\reports\\" + testcasename + ".png";
		FileUtils.copyFile(src, new File(destination));
		return destination;
	}
}
