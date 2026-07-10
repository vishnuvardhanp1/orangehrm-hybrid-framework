package utilities;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsProgram extends BaseClass {
	  private static ExtentReports extentReport;

	    public static ExtentReports getReportInstance() {

	    	if (extentReport == null) {

	            File folder = new File("./extentReports");

	            if (!folder.exists()) {
	                folder.mkdirs();
	            }

	            String reportPath = System.getProperty("user.dir")
	                    + "/extentReports/ExtentReport.html";

	            ExtentSparkReporter sparkReporter =
	                    new ExtentSparkReporter(reportPath);

	            BaseClass base = new BaseClass();

	            sparkReporter.config().setReportName(
	                    base.readDataFromPropertyFile("reportName"));

	            sparkReporter.config().setDocumentTitle(
	                    base.readDataFromPropertyFile("documentTitle"));

	            String theme = base.readDataFromPropertyFile("theme");

	            if (theme.equalsIgnoreCase("dark")) {
	                sparkReporter.config().setTheme(Theme.DARK);
	            } else {
	                sparkReporter.config().setTheme(Theme.STANDARD);
	            }

	            extentReport = new ExtentReports();

	            extentReport.attachReporter(sparkReporter);

	            extentReport.setSystemInfo(
	                    "Tester",
	                    base.readDataFromPropertyFile("tester"));

	            extentReport.setSystemInfo(
	                    "Environment",
	                    base.readDataFromPropertyFile("environment"));

	            extentReport.setSystemInfo(
	                    "Browser",
	                    base.readDataFromPropertyFile("browser"));
	        }

	        return extentReport;
	 }
}
