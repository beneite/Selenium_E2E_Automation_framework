package com.asismisr.pages.sauseLabDemo;

import com.asismisr.pages.BasePage;

// Making class final so no one can inherit this class
public final class LogInPage extends BasePage {

    private final String USER_NAME_INPUT = "//input[@id='user-name']";
    private final String PASSWORD_INPUT = "//input[@id='password']";
    private final String LOGIN_BUTTON = "//input[@id='login-button']";


    public LogInPage()
    {

    }

    public void enterLogInDetails(String username, String password)
    {
        sendElement(USER_NAME_INPUT,username);
        sendElement(PASSWORD_INPUT,password);
    }

    public void clickLoginButton()
    {
        clickElement(LOGIN_BUTTON);
    }

    public String  getElementText(String attributeKey)
    {
        return getElementAttribute(LOGIN_BUTTON,attributeKey);
    }

    public boolean elementVisiblity()
    {
        return elementVisiblity(LOGIN_BUTTON);
    }

    public String getLoginPageURL()
    {
        return getPageURL();
    }
}
