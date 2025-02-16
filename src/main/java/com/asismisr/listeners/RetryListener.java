package com.asismisr.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer {

    private int currentCount = 0;
    private int maxRetryCount = 2;

    /**
     * this method will enable test case to re-run in case the test case fails.
     * @param iTestResult The result of the test method that just ran.
     * @return True: test case will retry. false: test case will not be retried
     */
    @Override
    public boolean retry(ITestResult iTestResult) {

        boolean value = currentCount < maxRetryCount;
        currentCount++;
        return value;
    }
}
