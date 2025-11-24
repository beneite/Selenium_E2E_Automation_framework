package com.asismisr.factory;

import com.asismisr.constants.Constants;
import com.asismisr.drivermanagement.DriverManager;
import com.asismisr.enums.WaitStrategyEnums;
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
     * @param waitStrategyEnums
     * @return
     */
    public static WebElement explicitWaitByStrategy(By by, WaitStrategyEnums waitStrategyEnums, long timeDuration){

        WebElement webElement;
        webElement = switch(waitStrategyEnums){
            case CLICKABLE -> new WebDriverWait(DriverManager.getWebDriverFromThreadLocal(), Duration.ofSeconds(timeDuration))
                    .until(ExpectedConditions.elementToBeClickable(by));
            case VISIBLE -> new WebDriverWait(DriverManager.getWebDriverFromThreadLocal(), Duration.ofSeconds(timeDuration))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
            case ISPRESENT -> new WebDriverWait(DriverManager.getWebDriverFromThreadLocal(), Duration.ofSeconds(timeDuration))
                    .until(ExpectedConditions.presenceOfElementLocated(by));
            default -> throw new IllegalStateException("Unexpected value: " + waitStrategyEnums);
        };
        return webElement;
    }
}
