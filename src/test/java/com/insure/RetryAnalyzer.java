package com.insure;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	private int retryCount = 0;
    // Set the max number of retries
    private static final int MAX_RETRY_COUNT = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            System.out.println("Retry test: " + result.getName() + " for the " + retryCount + " time(s).");
            return true; // Tells TestNG to retry the test
        }
        return false; 
    }
}
