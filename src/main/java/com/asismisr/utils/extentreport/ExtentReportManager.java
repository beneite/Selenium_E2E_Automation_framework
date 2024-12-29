package com.asismisr.utils.extentreport;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentReportManager {

    private ExtentReportManager(){
        // making this private to restrict the object creation.
        // making class as final to restrict class getting extended.
    }

    private static ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<>();

    // access modifier should be default
    static ExtentTest getExtentTestFromThreadLocal(){
        return threadLocal.get();
    }

    static void setExtentTestIntoThreadLocal(ExtentTest extentTest){
        threadLocal.set(extentTest);
    }

    static void unloadExtentTestFromThreadLocal(){
        threadLocal.remove();
    }

}
