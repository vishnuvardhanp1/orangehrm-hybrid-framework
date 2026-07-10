package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryImplementation implements IRetryAnalyzer {
	  private int maxRetryCount = 2;
	    private int retryCount = 0;

	    @Override
	    public boolean retry(ITestResult result) {

	        if (retryCount < maxRetryCount) {

	            retryCount++;

	            System.out.println("Retrying Test : " + result.getName()
	                    + " | Attempt : " + retryCount);

	            return true;
	        }

	        return false;
	    }
}
