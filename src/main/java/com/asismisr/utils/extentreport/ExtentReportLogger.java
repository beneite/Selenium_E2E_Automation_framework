package com.asismisr.utils.extentreport;

import com.asismisr.utils.ScreenshotUtils;
import com.aventstack.extentreports.MediaEntityBuilder;

//TODO: need to add a new reporting system and replace extent report.
public final class ExtentReportLogger {

    private ExtentReportLogger(){
        // making this private to restrict the object creation.
        // making class as final to restrict class getting extended.
    }

    public static void info(String message){
        ExtentReportManager.getExtentTestFromThreadLocal().info(message);
    }

    public static void pass(String message){
        ExtentReportManager.getExtentTestFromThreadLocal().pass(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.takeBase64Screenshot()).build());
    }

    public static void fail(String message){
        ExtentReportManager.getExtentTestFromThreadLocal().fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.takeBase64Screenshot()).build());
    }

    public static void skip(String message){
        ExtentReportManager.getExtentTestFromThreadLocal().skip(message);
    }

    public static void info(String message, boolean screenshotFlag){
        if(screenshotFlag){
            ExtentReportManager.getExtentTestFromThreadLocal().info(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.takeBase64Screenshot()).build());
        }
    }
}
