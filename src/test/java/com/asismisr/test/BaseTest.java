package com.asismisr.test;

import com.asismisr.configs.Config;
import com.asismisr.constants.Constants;
import com.asismisr.drivermanagement.Driver;
import com.asismisr.drivermanagement.DriverManager;
import com.asismisr.utils.ekl.PublishResults;
import com.asismisr.utils.extentreport.ExtentReportLogger;
import com.asismisr.utils.extentreport.ExtentReportUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Objects;

public class BaseTest {

    protected BaseTest(){}

    private static Logger log =  LoggerFactory.getLogger(BaseTest.class);

    @BeforeSuite
    protected void setupConfigurations(){

    }

    @BeforeMethod
    protected void setUpTestMethod()  {
        Driver.initDriver();
    }

    @AfterMethod
    protected void addingSleepBetweenTestCaseExecution(ITestResult iTestResult){
        if(Boolean.parseBoolean(Config.getTestProperty(Constants.SELENIUM_GRID_ENABLED))){
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        }

        // quiting the browser if it is !NULL
        if(Objects.nonNull(DriverManager.getWebDriverFromThreadLocal())){
            Driver.quitDriver();
            DriverManager.unloadWebDriverThreadLocal();
        }

        // this will be used to publish results.
        PublishResults.publishResults(iTestResult);
    }

    @AfterSuite
    public void tearDownConfiguration(){

    }

}
