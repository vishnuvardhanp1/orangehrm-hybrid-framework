package utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.Report;
import com.google.common.io.Files;

public class ListenerImplementation  extends BaseClass implements ITestListener {
	private static ExtentReports extentReport =
	        ExtentReportsProgram.getReportInstance();

	private static ExtentTest extentTest;
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(result.getName()+" Test Started");//prints the testcase name which has started
		 extentTest = extentReport.createTest(result.getName());
	}
	@Override
	public void onTestSuccess(ITestResult result) {
	    System.out.println(result.getName() + " Test Passed");
	    extentTest.pass("Test Passed");

	    if(driver != null) {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
			TakesScreenshot screenshot = (TakesScreenshot)driver;
			File src = screenshot.getScreenshotAs(OutputType.FILE);
			File folder = new File("./Screenshots");
			String fileName = result.getName() + "_" + System.currentTimeMillis() + ".png";
			
			if (!folder.exists()) {
			    folder.mkdirs();
			}
			
			File destFile = new File("./Screenshots/" + fileName);
			
			try {
				Files.copy(src, destFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String screenshotPath = destFile.getAbsolutePath();
			extentTest.pass("Test Passed")
	          .addScreenCaptureFromPath(screenshotPath);
			
			}
	}
		
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println(result.getName() + " Test Failed");
		System.out.println("Reason : " + result.getThrowable());
	    extentTest.fail(result.getThrowable());

		if(driver != null) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		File folder = new File("./Screenshots");
		if (!folder.exists()) {
		    folder.mkdirs();
		}

		File destFile = new File(folder, result.getName() + ".png");

		String screenshotPath = destFile.getAbsolutePath();
		extentTest.pass("Test Passed")
          .addScreenCaptureFromPath(screenshotPath);
		try {
			Files.copy(src, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		}

	    
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println(result.getName() + " Test Skipped");
		extentTest.skip("Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		 System.out.println(result.getName()
		            + " failed but is within the configured success percentage.");
	}
	
	@Override
	public void onStart(ITestContext context) {
		 System.out.println("Execution Started");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extentReport.flush();

	    System.out.println("===== Execution Summary =====");

	    System.out.println("Passed : " + context.getPassedTests().size());
	    System.out.println("Failed : " + context.getFailedTests().size());
	    System.out.println("Skipped : " + context.getSkippedTests().size());

	    System.out.println("=============================");
	}
}
