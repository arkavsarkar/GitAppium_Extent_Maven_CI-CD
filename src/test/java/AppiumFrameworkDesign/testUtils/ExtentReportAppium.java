package AppiumFrameworkDesign.testUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportAppium {
	static ExtentReports extent;
 public static ExtentReports getExtentReport() {
	// ExtentReport, ExtentSparkReporter --useful to generate extent report
			// need to create object and specify where to save the report
			String path = System.getProperty("user.dir") + "//reports/index.html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(path);
			// configuring according to our requirement
			reporter.config().setReportName("Mobileautomation Result");
			reporter.config().setDocumentTitle("Test Results");
	//need to create object of ExtentRepor type, responsible to drive all your reporting execution
			extent = new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("Tester", "Bubun");
			return extent;
 }
}
