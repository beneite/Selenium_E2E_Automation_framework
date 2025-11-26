/**
 * this class contains the common page methods that we can use across all other pages for flightRegistration.
 */
package com.asismisr.pages;

import com.asismisr.drivermanagement.DriverManager;
import com.asismisr.enums.WaitStrategyEnums;
import com.asismisr.factory.ExplicitWaitImplementation;
import com.asismisr.utils.extentreport.ExtentReportLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;


public class BasePage {

    private static final Logger log = LoggerFactory.getLogger(BasePage.class);

    // todo: make this protected
    public void goToUrl(String url){
        DriverManager.getWebDriverFromThreadLocal().get(url);
        ExtentReportLogger.info("URL launched:"+url);
        log.info("Navigating to URL: {}", url);
    }

    protected void navigatingURL(String url)
    {
        DriverManager.getWebDriverFromThreadLocal().navigate().to(url);
        ExtentReportLogger.info("navigated to:"+url);
        log.info("Navigating to URL: {}", url);
    }

    protected String xPathEditor(String xpath,String nameOfField)
    {
        return String.format(xpath,nameOfField);
    }

    protected void clickElement(By by){
        DriverManager.getWebDriverFromThreadLocal().findElement(by).click();
        ExtentReportLogger.info("Element Clicked:"+ by.toString());
        log.info("{} Element Clicked", by.toString());
    }

    protected String getElementText(String element){
        ExtentReportLogger.info("Element text:"+ element.toString());
        log.info("{} Element text", element.toString());
        return DriverManager.getWebDriverFromThreadLocal().findElement(By.xpath(element)).getText();
    }

    protected WebElement findElementsXpath(String input)
    {
        ExtentReportLogger.info("Element found:"+input);
        log.info("Element found: {}",input);
        return DriverManager.getWebDriverFromThreadLocal().findElement(By.xpath(input));
    }

    protected void clickOnElement(String element)
    {
        DriverManager.getWebDriverFromThreadLocal().findElement(By.xpath(element)).click();
        ExtentReportLogger.info("Element clicked:"+element);
        log.info("Element clicked: {}",element);
    }

    protected WebElement clickOnElementToSendText(String element)
    {
        ExtentReportLogger.info("Sending Text:"+element);
        log.info("Sending Text: {}",element);
        return DriverManager.getWebDriverFromThreadLocal().findElement(By.xpath(element));
    }
    protected WebElement clickOnElementusingIdToSendText(String element)
    {
        ExtentReportLogger.info("Sending Text:"+element);
        log.info("Sending Text: {}",element);
        return DriverManager.getWebDriverFromThreadLocal().findElement(By.id(element));
    }

    protected WebElement waitingForElementsToLoad(WebElement element,long seconds)
    {
        WebDriverWait wait=new WebDriverWait(DriverManager.getWebDriverFromThreadLocal(),Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.visibilityOf(element));

    }

    protected boolean isElementDisplayed(String element)
    {
        ExtentReportLogger.info("Element located:"+element);
        log.info("Element located: {}",element);
        return DriverManager.getWebDriverFromThreadLocal().findElement(By.xpath(element)).isDisplayed();
    }
    protected boolean isElementEnabled(String element)
    {
        ExtentReportLogger.info("Element located:"+element);
        log.info("Element located: {}",element);
        return DriverManager.getWebDriverFromThreadLocal().findElement(By.xpath(element)).isEnabled();
    }

    protected void scrollToElement(By by)
    {
        Actions actions=new Actions(DriverManager.getWebDriverFromThreadLocal());
        WebElement element=DriverManager.getWebDriverFromThreadLocal().findElement(by);
        actions.scrollToElement(element).perform();
        ExtentReportLogger.info("Element Scrolled:"+ by.toString());
        log.info("{} Element Scrolled", by.toString());
    }

    protected void sendElement(By by, String textToField){
        DriverManager.getWebDriverFromThreadLocal().findElement(by).sendKeys(textToField);
        ExtentReportLogger.info(by.toString()+" Text Entered is "+ textToField);
        log.info("{} Text Entered is {}", by.toString(), textToField);
    }

    protected void sendElement(String xpathString, String textToField){
        DriverManager.getWebDriverFromThreadLocal().findElement(By.xpath(xpathString)).sendKeys(textToField);
        ExtentReportLogger.info(xpathString+" Text Entered is "+ textToField);
        log.info("{} Text Entered is {}", xpathString, textToField);
    }

    protected boolean isElementVisible(By by){
        return new WebDriverWait(DriverManager.getWebDriverFromThreadLocal(), Duration.ofSeconds(10))
                .until(webElement -> webElement.findElement(by).isDisplayed());
    }



    protected String getPageURL()
    {
        String  pageURL=DriverManager.getWebDriverFromThreadLocal().getCurrentUrl();
        ExtentReportLogger.info(" URL returned is "+ pageURL);
        log.info("{} URL returned is {}", pageURL);
        return pageURL;
    }



    protected String getText(By by){
        String textFetchedFromUi = DriverManager.getWebDriverFromThreadLocal().findElement(by).getText();
        ExtentReportLogger.info("text fetched from Ui:"+ textFetchedFromUi);
        log.info("text fetched from Ui: {} ",textFetchedFromUi);
        return textFetchedFromUi;
    }

    protected void selectByDropDownValue(By by, String optionToSelect){
        Select select = new Select(DriverManager.getWebDriverFromThreadLocal().findElement(by));
        select.selectByValue(optionToSelect);
        ExtentReportLogger.info("Drop down option selected:"+ optionToSelect);
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
        ExtentReportLogger.info("Element Clicked by WebElement:"+ webElement.toString());
        log.info("{} Element Clicked by WebElement", webElement.toString());
    }

    /**
     * this method will apply explicit wait before performing click operation
     * @param by by
     * @param waitStrategyEnums can be VISIBLE,CLICKABLE,ISPRESENT;
     */
    protected void clickElementWithWait(By by, WaitStrategyEnums waitStrategyEnums,long durationInSecond){
        ExplicitWaitImplementation.explicitWaitByStrategy(by, waitStrategyEnums,durationInSecond).click();
        ExtentReportLogger.info("Element Clicked:"+by.toString());
        log.info("{} Element Clicked", by.toString());
    }

    /**
     * this method will apply explicit wait before performing sendKeys operation
     * @param by by
     * @param waitStrategyEnums can be VISIBLE,CLICKABLE,ISPRESENT;
     */
    protected void sendElementWithWait(By by, String textToField, WaitStrategyEnums waitStrategyEnums,long durationInSecond){
        ExplicitWaitImplementation.explicitWaitByStrategy(by, waitStrategyEnums,durationInSecond).sendKeys(textToField);
        ExtentReportLogger.info(by.toString()+" Text Entered is "+ textToField);
        log.info("{} Text Entered is {}", by.toString(), textToField);
    }

    protected WebElement clickElementWithWait(String xpath, WaitStrategyEnums waitStrategyEnums,long durationInSecond){
        By by=By.xpath(xpath);
        WebElement element=ExplicitWaitImplementation.explicitWaitByStrategy(by, waitStrategyEnums,durationInSecond);
        ExtentReportLogger.info("Element Clicked:"+by.toString());
        log.info("{} Element Clicked", by.toString());
        return element;
    }

    protected WebElement clearField(String xpath,WaitStrategyEnums waitStrategyEnums,long durationInSecond)
    {
        By by=By.xpath(xpath);
        WebElement element=ExplicitWaitImplementation.explicitWaitByStrategy(by, waitStrategyEnums,durationInSecond);
        ExtentReportLogger.info("Field Cleared:"+by.toString());
        log.info("{} Field Cleared", by.toString());
        return element;

    }
    protected WebElement findElementWithWait(String xpath, WaitStrategyEnums waitStrategyEnums,long durationInSecond){
        By by=By.xpath(xpath);
        WebElement element=ExplicitWaitImplementation.explicitWaitByStrategy(by, waitStrategyEnums,durationInSecond);
        ExtentReportLogger.info("Element Clicked:"+by.toString());
        log.info("{} Element Clicked", by.toString());
        return element;
    }


}
