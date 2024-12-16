package com.asismisr.test;

import com.asismisr.configs.Config;
import com.asismisr.constants.Constants;
import com.asismisr.drivermanagement.DriverManager;
import com.asismisr.utils.ekl.PublishResults;
import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Objects;

public abstract class BaseTest {

    private static Logger log =  LoggerFactory.getLogger(BaseTest.class);
    protected WebDriver driver;

    @BeforeSuite
    public void setupConfigurations(){

        // initialising the configurations
        Config.initializeProperties();

    }

    @BeforeMethod
    public void setUpTestMethod(Method method) throws MalformedURLException {
        if(Boolean.parseBoolean(Config.getTestProperty(Constants.SELENIUM_GRID_ENABLED))){
            this.driver = getRemoteWebDriver();
        }else{
            this.driver = getLocalWebdriver();
        }

    }


    public WebDriver getRemoteWebDriver() throws MalformedURLException {

        Capabilities capabilities ;

        switch (Config.getTestProperty(Constants.BROWSER)) {
            case "chrome" -> capabilities = new ChromeOptions();
            case "firefox" -> capabilities = new FirefoxOptions();
            default -> throw new IllegalArgumentException("Browser type not supported");
        }
        String seleniumHubUrlFormat = Config.getTestProperty(Constants.SELENIUM_GRID_URL_FORMAT);
        String seleniumGridHubHost = Config.getTestProperty(Constants.SELENIUM_GRID_HUB_HOST);
        String url = String.format(seleniumHubUrlFormat, seleniumGridHubHost);
        log.info("Url:"+url);
        return new RemoteWebDriver(new URL(url), capabilities);
    }


    public WebDriver getLocalWebdriver() {
        switch (Config.getTestProperty(Constants.BROWSER)) {
            case Constants.CHROME -> {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
            case Constants.FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }
            default -> throw new IllegalArgumentException(Constants.ERROR_MSG_BROWSER_NOT_SUPPORT);
        }

    }

    @AfterMethod
    public void addingSleepBetweenTestCaseExecution(ITestResult iTestResult){
        if(Boolean.parseBoolean(Config.getTestProperty(Constants.SELENIUM_GRID_ENABLED))){
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        }

        // quiting the browser if it is !NULL
        if(Objects.nonNull(driver)){
            this.driver.quit();
        }


        // this will be used to publish results.
        PublishResults.publishResults(iTestResult);
    }

}
