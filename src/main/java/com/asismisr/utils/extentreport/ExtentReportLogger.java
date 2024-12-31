package com.asismisr.utils.extentreport;

import com.asismisr.drivermanagement.DriverManager;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public final class ExtentReportLogger {

    private ExtentReportLogger(){
        // making this private to restrict the object creation.
        // making class as final to restrict class getting extended.
    }

    public static void info(String message){
        ExtentReportManager.getExtentTestFromThreadLocal().info(message);
    }

    public static void pass(String message){
        ExtentReportManager.getExtentTestFromThreadLocal().pass(message, MediaEntityBuilder.createScreenCaptureFromBase64String(takeBase64Screenshot()).build());
    }

    public static void fail(String message){
        ExtentReportManager.getExtentTestFromThreadLocal().fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(takeBase64Screenshot()).build());
    }

    public static void skip(String message){
        ExtentReportManager.getExtentTestFromThreadLocal().skip(message);
    }

    public static String takeBase64Screenshot(){
        return ((TakesScreenshot)DriverManager.getWebDriverFromThreadLocal()).getScreenshotAs(OutputType.BASE64);
    }
}
