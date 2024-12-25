package com.asismisr.factory;

import com.asismisr.constants.Constants;
import com.asismisr.drivermanagement.DriverManager;
import com.asismisr.enums.WaitStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitWaitImplementation {

    /**
     * this method will define a strategy for wait using explicit wait.
     * u can add waits if not available.
     * @param by
     * @param waitStrategy
     * @return
     */
    public static WebElement explicitWaitByStrategy(By by, WaitStrategy waitStrategy){

        WebElement webElement;
        webElement = switch(waitStrategy){
            case CLICKABLE -> new WebDriverWait(DriverManager.getWebDriverFromThreadLocal(), Duration.ofSeconds(Constants.LONG_TEN))
                    .until(ExpectedConditions.elementToBeClickable(by));
            case VISIBLE -> new WebDriverWait(DriverManager.getWebDriverFromThreadLocal(), Duration.ofSeconds(Constants.LONG_TEN))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
            case ISPRESENT -> new WebDriverWait(DriverManager.getWebDriverFromThreadLocal(), Duration.ofSeconds(Constants.LONG_TEN))
                    .until(ExpectedConditions.presenceOfElementLocated(by));
            default -> throw new IllegalStateException("Unexpected value: " + waitStrategy);
        };
        return webElement;
    }
}
