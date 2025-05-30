package com.asismisr.utils.influxDb;


import com.asismisr.codeUtils.CommonUtilis;
import com.asismisr.configs.Config;
import com.asismisr.constants.Constants;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public final class InfluxUtils {

    private InfluxUtils(){}

    private static Logger log =  LoggerFactory.getLogger(InfluxUtils.class);

    private static final String TOKEN = "mysecrettoken";
    private static final String ORG = "qa-org";
    private static final String BUCKET = "qa-tests";
    private static final String URL = "http://localhost:8086"; // or "http://influxdb_service:8086" if inside Docker

    private static final InfluxDBClient influxDBClient = InfluxDBClientFactory.create(URL, TOKEN.toCharArray(), ORG, BUCKET);

    public static void send(Point point) {
        try (WriteApi writeApi = influxDBClient.getWriteApi()) {
            writeApi.writePoint(point);
        }
    }

    public static void sendResultsToInfluxDb(ITestResult iTestResult) {
        if(Config.getTestProperty(Constants.INFLUX_REALTIME_EXECUTION_REPORT).equalsIgnoreCase(Constants.TRUE)) {

            Point point = Point.measurement("testmethod_v2")
                    .time(CommonUtilis.getCurrentLocalTimeInstant(), WritePrecision.MS)
                    .addTag("testclass", iTestResult.getTestClass().getName())
                    .addTag("testname", iTestResult.getName())
                    .addTag("testresult", returnStringStatus(iTestResult.getStatus()))
                    .addTag("description", iTestResult.getMethod().getDescription())
                    .addField("duration", (iTestResult.getEndMillis() - iTestResult.getStartMillis()));

            send(point);
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

}
