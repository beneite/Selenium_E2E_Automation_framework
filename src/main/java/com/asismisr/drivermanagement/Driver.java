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

    //TODO: need to add support for headless for remote web driver/selenium hub execution too
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
        boolean configHeadlessModeFlag = Boolean.parseBoolean(Config.getTestProperty(Constants.HEADLESS_MODE_FLAG));
        WebDriver driver;

        driver = switch (BrowserEnums.valueOf(configBrowserType.toUpperCase())) {
            case CHROME -> createChromeDriver(configHeadlessModeFlag);
            case FIREFOX -> createFirefoxDriver(configHeadlessModeFlag);
            case EDGE -> createEdgeDriver(configHeadlessModeFlag);

            default -> throw new IllegalArgumentException(Constants.ERROR_MSG_BROWSER_NOT_SUPPORT);
        };

        return driver;
    }

    public static void quitDriver(){
        DriverManager.getWebDriverFromThreadLocal().quit();
    }

    // create a Chrome driver and set options
    private static WebDriver createChromeDriver(boolean isHeadless) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        setHeadlessOptions(options, isHeadless);
        return new ChromeDriver(options);
    }

    // create a Firefox driver and set options
    private static WebDriver createFirefoxDriver(boolean isHeadless) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        setHeadlessOptions(options, isHeadless);
        return new FirefoxDriver(options);
    }

    // create a Edge driver and set options
    private static WebDriver createEdgeDriver(boolean isHeadless) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        setHeadlessOptions(options, isHeadless);
        return new EdgeDriver(options);
    }

    private static void setHeadlessOptions(org.openqa.selenium.MutableCapabilities capabilities, boolean isHeadless){
        if(isHeadless){
            // Use W3C-compliant way to enable headless mode
            if (capabilities instanceof ChromeOptions chromeOptions) {
                chromeOptions.addArguments("--headless=new", "--disable-gpu", "--window-size=1920,1080");
            } else if (capabilities instanceof EdgeOptions edgeOptions) {
                edgeOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080");
            } else if (capabilities instanceof FirefoxOptions firefoxOptions) {
                firefoxOptions.addArguments("--headless");
            }
        }
    }
}
