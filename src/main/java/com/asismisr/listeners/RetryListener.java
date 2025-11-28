package com.asismisr.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer {

    private int count = 0;
    private static final int MAX_RETRY_COUNT = 0; // set the maximum number of retries here

    /**
     * this method will enable test case to re-run in case the test case fails.
     * @param iTestResult The result of the test method that just ran.
     * @return True: test case will retry. false: test case will not be retried
     */
    @Override
    public boolean retry(ITestResult iTestResult) {
        if (count < MAX_RETRY_COUNT) {
            // if the test has failed, retry it
            if (iTestResult.getStatus() == ITestResult.FAILURE) {
                count++;
                return true;
            }
        }
        return false;
    }
}
