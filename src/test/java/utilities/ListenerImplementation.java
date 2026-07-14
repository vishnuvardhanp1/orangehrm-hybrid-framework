package utilities;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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


public class ListenerImplementation extends BaseClass implements ITestListener {
	private static final Logger logger =
			LogManager.getLogger(ListenerImplementation.class);
    private static ExtentReports extentReport =
            ExtentReportsProgram.getReportInstance();

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {

    	logger.info(result.getName() + " Test Started");

        extentTest.set(extentReport.createTest(result.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {

    	logger.info(result.getName() + " Test Passed");

        extentTest.get().pass("Test Passed");

        if (getDriver() != null) {

            try {

                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

                TakesScreenshot screenshot = (TakesScreenshot) getDriver();
                File src = screenshot.getScreenshotAs(OutputType.FILE);

                File folder = new File("./Screenshots");
                if (!folder.exists()) {
                    folder.mkdirs();
                }

                String fileName = result.getName() + "_" + System.currentTimeMillis() + ".png";
                File destFile = new File(folder, fileName);

                Files.copy(src.toPath(), destFile.toPath());

                extentTest.get()
                          .pass("Screenshot")
                          .addScreenCaptureFromPath(destFile.getAbsolutePath());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {

    	logger.info(result.getName() + " Test Failed");
    	logger.info("Reason : " + result.getThrowable());

        extentTest.get().fail(result.getThrowable());

        if (getDriver() != null) {

            try {

                WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

                TakesScreenshot screenshot = (TakesScreenshot) getDriver();
                File src = screenshot.getScreenshotAs(OutputType.FILE);

                File folder = new File("./Screenshots");
                if (!folder.exists()) {
                    folder.mkdirs();
                }

                String fileName = result.getName() + "_" + System.currentTimeMillis() + ".png";
                File destFile = new File(folder, fileName);

                Files.copy(src.toPath(), destFile.toPath());

                extentTest.get()
                          .fail("Failure Screenshot")
                          .addScreenCaptureFromPath(destFile.getAbsolutePath());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    	logger.info(result.getName() + " Test Skipped");

        extentTest.get().skip("Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    	logger.info(result.getName()
                + " failed but is within the configured success percentage.");
    }

    @Override
    public void onStart(ITestContext context) {

        System.out.println("Execution Started");
    }

    @Override
    public void onFinish(ITestContext context) {

        extentReport.flush();

        extentTest.remove();

        logger.info("===== Execution Summary =====");

        logger.info("Passed : " + context.getPassedTests().size());
        logger.info("Failed : " + context.getFailedTests().size());
        logger.info("Skipped : " + context.getSkippedTests().size());

        logger.info("=============================");
    }
}