package com.asismisr.drivermanagement;

import org.openqa.selenium.WebDriver;

public final class DriverManager {

    private DriverManager(){

    }

    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();


    public static WebDriver getWebDriverThreadLocal() {
        return webDriverThreadLocal.get();
    }

    public static void setWebDriverThreadLocal(WebDriver webDriver) {
        webDriverThreadLocal.set(webDriver);
    }

    public static void removeWebDriverThreadLocal() {
        webDriverThreadLocal.remove();
    }

}