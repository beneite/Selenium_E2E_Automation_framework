/**
 * this class contains the common page methods that we can use across all other pages for flightRegistration.
 */
package com.asismisr.pages;

import com.asismisr.drivermanagement.DriverManager;
import com.asismisr.enums.WaitStrategyEnums;
import com.asismisr.factory.ExplicitWaitImplementation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;


public class BasePage {

    private static final Logger log = LoggerFactory.getLogger(BasePage.class);

    protected void goToUrl(String url){
        DriverManager.getWebDriverFromThreadLocal().get(url);
        log.info("Navigating to URL: {}", url);
    }

    protected void clickElement(By by){
        DriverManager.getWebDriverFromThreadLocal().findElement(by).click();
        log.info("{} Element Clicked", by.toString());
    }

    protected void sendElement(By by, String textToField){
        DriverManager.getWebDriverFromThreadLocal().findElement(by).sendKeys(textToField);
        log.info("{} Text Entered is {}", by.toString(), textToField);
    }

    protected boolean isElementVisible(By by){
        return new WebDriverWait(DriverManager.getWebDriverFromThreadLocal(), Duration.ofSeconds(10))
                .until(webElement -> webElement.findElement(by).isDisplayed());
    }

    protected String getText(By by){
        String textFetchedFromUi = DriverManager.getWebDriverFromThreadLocal().findElement(by).getText();
        log.info("text fetched from Ui: {} ",textFetchedFromUi);
        return textFetchedFromUi;
    }

    protected void selectByDropDownValue(By by, String optionToSelect){
        Select select = new Select(DriverManager.getWebDriverFromThreadLocal().findElement(by));
        select.selectByValue(optionToSelect);
        log.info("Drop down option selected:{}", optionToSelect);
    }

    protected List<WebElement> returnWebElementsFromBy(By by){
        return DriverManager.getWebDriverFromThreadLocal().findElements(by);
    }

    protected WebElement returnWebElementFromBy(By by, int webElementAtIndexToReturn){
        return DriverManager.getWebDriverFromThreadLocal().findElements(by).get(webElementAtIndexToReturn);
    }

    protected void clickWebElement(WebElement webElement){
        webElement.click();
        log.info("{} Element Clicked by WebElement", webElement.toString());
    }

    /**
     * this method will apply explicit wait before performing click operation
     * @param by by
     * @param waitStrategyEnums can be VISIBLE,CLICKABLE,ISPRESENT;
     */
    protected void clickElementWithWait(By by, WaitStrategyEnums waitStrategyEnums){
        ExplicitWaitImplementation.explicitWaitByStrategy(by, waitStrategyEnums).click();
        log.info("{} Element Clicked", by.toString());
    }

    /**
     * this method will apply explicit wait before performing sendKeys operation
     * @param by by
     * @param waitStrategyEnums can be VISIBLE,CLICKABLE,ISPRESENT;
     */
    protected void sendElementWithWait(By by, String textToField, WaitStrategyEnums waitStrategyEnums){
        ExplicitWaitImplementation.explicitWaitByStrategy(by, waitStrategyEnums).sendKeys(textToField);
        log.info("{} Text Entered is {}", by.toString(), textToField);
    }
}
