package com.asismisr.utils;

import com.asismisr.drivermanagement.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public final class ScreenshotUtils {

    private ScreenshotUtils(){
        // making this private to restrict the object creation.
        // making class as final to restrict class getting extended.
    }

    /**
     * Captures screenshot of the current page, constructs a base64 string from the image and return to the caller.
     *There is no temporary screenshot image generated here. If user needs separate screenshot image, they can construct
     * a new method. It is advisable to use this method for many reasons.
     * @return String
     */
    public static String takeBase64Screenshot(){
        return ((TakesScreenshot) DriverManager.getWebDriverFromThreadLocal()).getScreenshotAs(OutputType.BASE64);
    }
}
