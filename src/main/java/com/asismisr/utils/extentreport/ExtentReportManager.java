package com.asismisr.utils.extentreport;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentReportManager {

    private ExtentReportManager(){
        // making this private to restrict the object creation.
        // making class as final to restrict class getting extended.
    }

    private static ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<>();

    public static ExtentTest getExtentTestFromThreadLocal(){
        return threadLocal.get();
    }

    public static void setExtentTestIntoThreadLocal(ExtentTest extentTest){
        threadLocal.set(extentTest);
    }

    public static void unloadExtentTestFromThreadLocal(){
        threadLocal.remove();
    }

}
