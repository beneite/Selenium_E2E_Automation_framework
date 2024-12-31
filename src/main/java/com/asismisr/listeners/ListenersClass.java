package com.asismisr.listeners;

import com.asismisr.configs.Config;
import com.asismisr.drivermanagement.DriverManager;
import com.asismisr.utils.extentreport.ExtentReportLogger;
import com.asismisr.utils.extentreport.ExtentReportUtils;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenersClass implements ITestListener, ISuiteListener {

    private static Logger log =  LoggerFactory.getLogger(ListenersClass.class);
    @Override
    public void onStart(ISuite suite) {

        // initialising the configurations
        Config.initializeProperties();

        // initializing the extent report
        ExtentReportUtils.initializeExtentReport();
    }

    @Override
    public void onFinish(ISuite suite) {
        // flush the extent report
        ExtentReportUtils.tearDown();
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("[onTestStart]");
        ExtentReportUtils.extentCreateTest(result.getMethod().getMethodName());
        ExtentReportLogger.info("Browser:"+((RemoteWebDriver)DriverManager.getWebDriverFromThreadLocal()).getCapabilities().getCapability(CapabilityType.BROWSER_NAME).toString());
        ExtentReportLogger.info("Browser version:"+((RemoteWebDriver) DriverManager.getWebDriverFromThreadLocal()).getCapabilities().getCapability(CapabilityType.BROWSER_VERSION).toString());
        ExtentReportLogger.info("Test started:"+result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportLogger.pass("Test case:"+result.getMethod().getMethodName()+" is PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentReportLogger.fail("Test case:"+result.getMethod().getMethodName()+" is FAILED");
        ExtentReportLogger.info("Exception:"+result.getThrowable().toString());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportLogger.skip("Test case:"+result.getMethod().getMethodName()+" is SKIPPED");
    }
}
