package com.asismisr.utils.extentreport;

public final class ExtentReportLogger {

    private ExtentReportLogger(){
        // making this private to restrict the object creation.
        // making class as final to restrict class getting extended.
    }

    public static void info(String message){
        ExtentReportManager.getExtentTestFromThreadLocal().info(message);
    }

    public static void pass(String message){
        ExtentReportManager.getExtentTestFromThreadLocal().pass(message);
    }

    public static void fail(String message){
        ExtentReportManager.getExtentTestFromThreadLocal().fail(message);
    }

    public static void skip(String message){
        ExtentReportManager.getExtentTestFromThreadLocal().skip(message);
    }
}
