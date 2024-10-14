package com.asismisr.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportUtils {

    public static final String EXTENT_REPORT_PATH = "target/test-output/extent-Report/extentReport.html";
    private static ExtentReports extentReports;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    public static ExtentSparkReporter extentSparkReporter;

    public static void initializeExtentReport(){

        extentSparkReporter = new ExtentSparkReporter(EXTENT_REPORT_PATH);
        extentSparkReporter.config().setReportName("Test Execution Report");
        extentSparkReporter.config().setDocumentTitle("Automation Report");
        extentSparkReporter.config().setTheme(Theme.STANDARD);

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);

        // Optional: Set system information
        extentReports.setSystemInfo("Tester", "Ashish");
        extentReports.setSystemInfo("Selenium Grid", Config.getTestProperty(Constants.SELENIUM_GRID_ENABLED));
        extentReports.setSystemInfo("Browser", Config.getTestProperty(Constants.BROWSER));
    }

    // Create a test instance
    public static void startTest(String testName) {
        ExtentTest extentTest = extentReports.createTest(testName);
        test.set(extentTest);
    }

    // Capture results in the report
    public static void captureResult(int status, Throwable throwable) {
        if (status == 1) { // Success
            test.get().pass("Test passed successfully.");
        } else if (status == 2) { // Failure
            test.get().fail(throwable);
        } else if (status == 3) { // Skip
            test.get().skip(throwable);
        }
    }

    // Flush the report
    public static void tearDown() {
        extentReports.flush();
    }

    // Get the current test instance
    public static ExtentTest getTest() {
        return test.get();
    }
}
