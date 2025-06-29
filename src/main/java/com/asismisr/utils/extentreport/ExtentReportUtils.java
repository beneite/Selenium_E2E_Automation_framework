package com.asismisr.utils.extentreport;

import com.asismisr.codeUtils.CommonUtilis;
import com.asismisr.configs.Config;
import com.asismisr.constants.Constants;
import com.asismisr.enums.TestGroupEnum;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public final class ExtentReportUtils {

    private ExtentReportUtils(){
        // making this private to restrict the object creation.
        // making class as final to restrict class getting extended.
    }

    private static final Logger log = LoggerFactory.getLogger(ExtentReportUtils.class);
    public static String TIMESTAMP = CommonUtilis.getCurrentDateTimeInSpecifiedFormat( "yyyy_MM_dd_HH_mm_ss");
    public static final String EXTENT_REPORT_PATH = "target/test-output/extent-Report/extentReport"+TIMESTAMP+".html";
    private static ExtentReports extentReports;
    public static ExtentSparkReporter extentSparkReporter;

    public static void initializeExtentReport(){
        if(Objects.isNull(extentReports)) {
            extentSparkReporter = new ExtentSparkReporter(EXTENT_REPORT_PATH);
            extentSparkReporter.config().setDocumentTitle("Automation Report");
            extentSparkReporter.config().setReportName("Test Execution Report");
            extentSparkReporter.config().setTheme(Theme.DARK);

            extentReports = new ExtentReports();
            extentReports.attachReporter(extentSparkReporter);

            // Optional: Set system information
            extentReports.setSystemInfo("Tester", "Ashish");
            extentReports.setSystemInfo("Selenium Grid", Config.getTestProperty(Constants.SELENIUM_GRID_ENABLED));
            extentReports.setSystemInfo("Browser", Config.getTestProperty(Constants.BROWSER));
            extentReports.setSystemInfo("OS name", System.getProperty("os.name"));
            extentReports.setSystemInfo("OS version", System.getProperty("os.version"));
            extentReports.setSystemInfo("Java version", System.getProperty("java.version"));
        }
        log.info("*********************** Extent report initialization completed Successfully ***********************");
    }

    // Create a test instance
    public static void extentCreateTest(String testName) {
        ExtentTest extentTest = extentReports.createTest(testName);
        ExtentReportManager.setExtentTestIntoThreadLocal(extentTest);
    }

//    // Capture results in the report
//    public static void captureResult(int status, Throwable throwable) {
//        if (status == 1) { // Success
//            test.get().pass("Test passed successfully.");
//        } else if (status == 2) { // Failure
//            test.get().fail(throwable);
//        } else if (status == 3) { // Skip
//            test.get().skip(throwable);
//        }
//    }

    // Flush the report
    public static void tearDown() {
        if(!Objects.isNull(extentReports)){
            extentReports.flush();
            ExtentReportManager.unloadExtentTestFromThreadLocal();
        }
    }

    public static void addTestMethodAuthors(String author){
        ExtentReportManager.getExtentTestFromThreadLocal().assignAuthor(author);
    }

    public static void addTestMethodGroups(TestGroupEnum[] groups){
        for(TestGroupEnum group : groups){
            ExtentReportManager.getExtentTestFromThreadLocal().assignCategory(group.toString());
        }
    }

}
