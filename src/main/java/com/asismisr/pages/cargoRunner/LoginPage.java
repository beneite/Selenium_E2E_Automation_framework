package com.asismisr.pages.cargoRunner;

import com.asismisr.pages.BasePage;

public class LoginPage extends BasePage {
    private final String LOGINXPATH="//input[@id='username']";
    private final String PASSWORDXPATH="//input[@id='password']";
    private final String LOGINBUTTON="//input[@value='Sign In']";


    public void goToWebsite(String url)
    {
        goToUrl(url);
    }


    public void enteringLoginDetails(String username,String password)
    {
        clickOnElementToSendText(LOGINXPATH).sendKeys(username);
        clickOnElementToSendText(PASSWORDXPATH).sendKeys(password);
    }

    public void clickOnSigninButton()
    {
        clickOnElement(LOGINBUTTON);
    }

    public boolean isSigninButtonEnable()
    {
        return findElementsXpath(LOGINBUTTON).isEnabled();
    }


}
