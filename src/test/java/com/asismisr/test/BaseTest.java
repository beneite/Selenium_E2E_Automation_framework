package com.asismisr.test;

import com.asismisr.configs.Config;
import com.asismisr.constants.Constants;
import com.asismisr.drivermanagement.DriverManager;
import com.asismisr.enums.BrowserEnums;
import com.asismisr.utils.ekl.PublishResults;
import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
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

    @BeforeSuite
    protected void setupConfigurations(){

        // initialising the configurations
        Config.initializeProperties();

    }

    @BeforeMethod
    protected void setUpTestMethod(Method method) throws MalformedURLException {
        WebDriver driver;
        if(Boolean.parseBoolean(Config.getTestProperty(Constants.SELENIUM_GRID_ENABLED))){
             driver = getRemoteWebDriver();
        }else{
             driver = getLocalWebdriver();
        }
        DriverManager.setWebDriverThreadLocal(driver);
    }


    private WebDriver getRemoteWebDriver() throws MalformedURLException {
        String configBrowserType = Config.getTestProperty(Constants.BROWSER);
        Capabilities capabilities ;
        switch (BrowserEnums.valueOf(configBrowserType.toUpperCase())) {
            case CHROME -> capabilities = new ChromeOptions();
            case FIREFOX -> capabilities = new FirefoxOptions();
            case EDGE -> capabilities = new EdgeOptions();
            default -> throw new IllegalArgumentException(Constants.ERROR_MSG_BROWSER_NOT_SUPPORT);
        }
        String seleniumHubUrlFormat = Config.getTestProperty(Constants.SELENIUM_GRID_URL_FORMAT);
        String seleniumGridHubHost = Config.getTestProperty(Constants.SELENIUM_GRID_HUB_HOST);
        String url = String.format(seleniumHubUrlFormat, seleniumGridHubHost);
        log.info("Url:"+url);
        return new RemoteWebDriver(new URL(url), capabilities);
    }


    private WebDriver getLocalWebdriver() {
        String configBrowserType = Config.getTestProperty(Constants.BROWSER);
        switch (BrowserEnums.valueOf(configBrowserType.toUpperCase())) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }
            case EDGE -> {
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            }
            default -> throw new IllegalArgumentException(Constants.ERROR_MSG_BROWSER_NOT_SUPPORT);
        }

    }

    @AfterMethod
    protected void addingSleepBetweenTestCaseExecution(ITestResult iTestResult){
        if(Boolean.parseBoolean(Config.getTestProperty(Constants.SELENIUM_GRID_ENABLED))){
            Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
        }

        // quiting the browser if it is !NULL
        if(Objects.nonNull(DriverManager.getWebDriverFromThreadLocal())){
            DriverManager.getWebDriverFromThreadLocal().quit();
            DriverManager.unloadWebDriverThreadLocal();
        }


        // this will be used to publish results.
        PublishResults.publishResults(iTestResult);
    }

}
