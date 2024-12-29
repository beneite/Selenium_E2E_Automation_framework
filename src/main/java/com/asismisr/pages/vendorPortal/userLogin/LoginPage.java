package com.asismisr.pages.vendorPortal.userLogin;

import com.asismisr.enums.WaitStrategyEnums;
import com.asismisr.pages.BasePage;
import com.asismisr.utils.extentreport.ExtentReportLogger;
import org.openqa.selenium.By;


public final class LoginPage extends BasePage {

    private static final By USERNAME_INPUT = By.xpath("//*[@id='username']");
    private static final By PASSWORD_INPUT = By.xpath("//*[@id='password']");
    private static final By LOGIN_BUTTON = By.xpath("//*[@id='login']");


    public LoginPage() {
    }

    public boolean isAt() {
        return isElementVisible(LOGIN_BUTTON);
    }

    public void goTo(String url){
        goToUrl(url);
    }

    public void login(String username, String password){
        sendElementWithWait(USERNAME_INPUT, username, WaitStrategyEnums.ISPRESENT);
        ExtentReportLogger.info("USERNAME_INPUT is Entered for:"+username);
        sendElementWithWait(PASSWORD_INPUT, password, WaitStrategyEnums.ISPRESENT);
        ExtentReportLogger.info("PASSWORD_INPUT is Entered");
        clickElementWithWait(LOGIN_BUTTON, WaitStrategyEnums.CLICKABLE);
        ExtentReportLogger.info("Login button is clicked");
    }

}
