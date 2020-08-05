package Resources;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Extentreport {
	static ExtentReports report;

	public static ExtentReports getReportobject() {
		String path = System.getProperty("user.dir") + "\\Extentreports\\index.html";
		ExtentSparkReporter ex = new ExtentSparkReporter(path);
		ex.config().setReportName("Web Automation results");
		ex.config().setDocumentTitle("Test Results");
		report = new ExtentReports();
		report.attachReporter(ex);
		report.setSystemInfo("Tester", "Nivedita");
		return report;
	}
}
