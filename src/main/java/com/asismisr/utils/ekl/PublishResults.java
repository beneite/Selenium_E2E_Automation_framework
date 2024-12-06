package com.asismisr.utils.ekl;

import com.asismisr.configs.Config;
import com.asismisr.constants.Constants;
import com.asismisr.pages.vendorPortal.dashboard.DashboardPage;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PublishResults {

    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    public static HashMap<String, String> getTestResultsDataToPublish(ITestResult iTestResult){
        log.info("****Test Name"+iTestResult.getTestName());
        HashMap<String, String> map = new HashMap<>();

        map.put("testcaseId", iTestResult.getName());
        map.put("testcaseDescription", "desc_"+iTestResult.getName());
        map.put("status", returnStringStatus(iTestResult.getStatus()));
        map.put("module", "Test_module");
        map.put("executionTime", getCurrentDateTime());

        return map;
    }

    public static void publishResults(ITestResult iTestResult){
        if(Config.getTestProperty(Constants.ELASTIC_REALTIME_EXECUTION_REPORT).equalsIgnoreCase(Constants.TRUE)){
            HashMap<String, String> testPayload = getTestResultsDataToPublish(iTestResult);
            String addResultsResponse = given().header("content-type","application/json")
                    .log()
                    .all()
                    .body(testPayload)
                    .post("http://localhost:9200/results/_doc")
                    .then()
                    .log()
                    .all()
                    .extract()
                    .response()
                    .asString();
            log.info("Response:"+ addResultsResponse);
        }

    }

    public static String returnStringStatus(int intStatus){
        return switch (intStatus) {
            case 1 -> "PASS";
            case 2 -> "FAIL";
            case 3 -> "SKIP";
            default -> "UNKNOWN"; // Optional: Handle unexpected values
        };
    }

    public static String getCurrentDateTime() {
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define a format for the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format and return as a string
        return currentDateTime.format(formatter);
    }

}
