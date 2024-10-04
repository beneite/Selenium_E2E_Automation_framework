package com.asismisr.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    public void setDriver() throws MalformedURLException {

        if(Boolean.getBoolean("selenium.grid.enabled")){
            this.driver = getRemoteWebDriver();
        }else{
            this.driver = getLocalWebdriver();
        }

    }

    public WebDriver getRemoteWebDriver() throws MalformedURLException {

        Capabilities capabilities ;

        switch (System.getProperty("browser")) {
            case "chrome" -> capabilities = new ChromeOptions();
            case "firefox" -> capabilities = new FirefoxOptions();
            default -> throw new IllegalArgumentException("Browser type not supported");
        }
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
    }


    public WebDriver getLocalWebdriver() {
        switch (System.getProperty("browser")) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }
            default -> throw new IllegalArgumentException("Browser type not supported");
        }

    }

    @AfterTest
    public void quitDriver() {
        this.driver.quit();
    }
}
