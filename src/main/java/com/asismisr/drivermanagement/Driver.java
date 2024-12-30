package com.asismisr.drivermanagement;

import com.asismisr.configs.Config;
import com.asismisr.constants.Constants;
import com.asismisr.enums.BrowserEnums;
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

import java.net.MalformedURLException;
import java.net.URL;

public final class Driver {

    private static Logger log =  LoggerFactory.getLogger(Driver.class);

    private Driver(){
        // making this private to restrict the object creation.
        // making class as final to restrict class getting extended.
    }

    public static void initDriver() {
        WebDriver driver;
        if(Boolean.parseBoolean(Config.getTestProperty(Constants.SELENIUM_GRID_ENABLED))){
            driver = getRemoteWebDriver();
        }else{
            driver = getLocalWebdriver();
        }
        DriverManager.setWebDriverThreadLocal(driver);
    }

    private static WebDriver getRemoteWebDriver() {
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
        try {
            return new RemoteWebDriver(new URL(url), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }


    private static WebDriver getLocalWebdriver() {
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

    public static void quitDriver(){
        DriverManager.getWebDriverFromThreadLocal().quit();
    }
}
