package com.asismisr.pages.sauceDemo;

import com.asismisr.pages.BasePage;

public final class LogInPage extends BasePage {
    private final String USERNAME="//input[@id='user-name']";
    private final String PASSWORD="//input[@id='password']";
    private final String LOGIN_BUTTON="//input[@id='login-button']";
    private final String ATTRIBUTE_LOGIN="value";

    public void openUrl(String url)
    {
        goToUrl(url);
    }

    public void enterUsernameandPassword(String username,String password)
    {
        findElementsXpath(USERNAME).sendKeys(username);
        findElementsXpath(PASSWORD).sendKeys(password);
    }

    public void clickOnButton()
    {
        clickOnElement(LOGIN_BUTTON);
    }

    public String getValueofLoginButton()
    {
        return findElementsXpath(LOGIN_BUTTON).getDomAttribute(ATTRIBUTE_LOGIN);
    }

}
