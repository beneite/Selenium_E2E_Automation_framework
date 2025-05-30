package com.asismisr.codeUtils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class CommonUtilis {

    /**
     * This method will return the date in the specified format w.r.t to the current date
     * <br> Enter positive integers for future dates </br>
     * <br> Enter negative integers for past dates </br>
     * @param dateFormat dateFormat
     * @param days days
     * @return
     */
    public static String getDate(String dateFormat, int days) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, days);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getCurrentDateTime() {
        LocalDate currentDate = LocalDate.now();
        LocalTime midnight = LocalTime.of(0, 0, 0);
        LocalDateTime currentDateTime = LocalDateTime.of(currentDate, midnight);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }

    public static String getCurrentDateTimeWithDays(int daysToAddOrSubtract) {
        LocalDate currentDate = LocalDate.now();
        LocalTime midnight = LocalTime.of(0, 0, 0);
        LocalDateTime currentDateTime = LocalDateTime.of(currentDate, midnight);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime modifiedDateTime;
        if(daysToAddOrSubtract>0) {
            modifiedDateTime=currentDateTime.plus(daysToAddOrSubtract, ChronoUnit.DAYS);
        }
        else {
            modifiedDateTime=currentDateTime.minus(daysToAddOrSubtract*-1, ChronoUnit.DAYS);
        }
        return modifiedDateTime.format(formatter);
    }

    public static String getCurrentDateTimeWithDays(int daysToAddOrSubtract, String format) {
        LocalDate currentDate = LocalDate.now();
        LocalTime midnight = LocalTime.of(0, 0, 0);
        LocalDateTime currentDateTime = LocalDateTime.of(currentDate, midnight);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime modifiedDateTime;
        if(daysToAddOrSubtract>0) {
            modifiedDateTime=currentDateTime.plus(daysToAddOrSubtract, ChronoUnit.DAYS);
        }
        else {
            modifiedDateTime=currentDateTime.minus(daysToAddOrSubtract*-1, ChronoUnit.DAYS);
        }
        return modifiedDateTime.format(formatter);
    }

    /**
     * method to convert date time from one format to another
     * @param dateToBeConverted - 2024-06-15T13:58:54.997781
     * @param format - dd-MM-yyyy HH:mm:ss
     * @return 15-06-2024 13:58:54
     */
    public static String convertDateTimeToAnotherFormat(String dateToBeConverted, String format){
        // Parse the input date string to a LocalDate object
        LocalDateTime date = LocalDateTime.parse(dateToBeConverted);

        // Define the desired output format
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(format);

        // Format the LocalDate object to the desired output format
        String formattedDate = date.format(outputFormatter);

        return formattedDate;
    }

    /**
     * Returns the current date and time in the specified format.
     *
     * @param targetFormat the format in which the current date and time should be returned.
     *                     For example, "yyyy-MM-dd HH:mm:ss" or "EEEE, MMM dd, yyyy hh:mm a".
     * @return a string representing the current date and time in the specified format,
     *         or an error message if the format is invalid.
     */
    public static String getCurrentDateTimeInSpecifiedFormat(String targetFormat) {
        try {
            // Get the current date and time
            LocalDateTime currentDateTime = LocalDateTime.now();

            // Create a formatter using the provided format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(targetFormat);

            // Format the current date and time
            return currentDateTime.format(formatter);
        } catch (IllegalArgumentException e) {
            // Handle invalid format strings
            return "Invalid date-time format: " + e.getMessage();
        }
    }

    /**
     * Get current time as Instant converted from local system time zone.
     *
     * @return Instant representing current local time in UTC
     */
    public static Instant getCurrentLocalTimeInstant() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId systemZone = ZoneId.systemDefault();
        return localDateTime.atZone(systemZone).toInstant();
    }

}
