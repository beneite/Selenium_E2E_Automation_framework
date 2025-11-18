package com.asismisr.pages.sauceDemo;

import com.asismisr.pages.BasePage;

public final class ProductPageTitles extends BasePage {
    private final String USEERNAME="//input[@id='user-name']";
    private final String PASSWORD="//input[@id='password']";
    private final String LOGIN_BUTTON="//input[@id='login-button']";
    private final String PRICE_XPATH="//div[text()='%s']/../../../div[@class='pricebar']/div[@class='inventory_item_price']";

    public void openUrl(String url)
    {
        goToUrl(url);
    }
    public void enterDetails(String username,String password)
    {
        findElementsXpath(USEERNAME).sendKeys(username);
        findElementsXpath(PASSWORD).sendKeys(password);
    }

    public void clickOnLoginButton()
    {
        clickOnElement(LOGIN_BUTTON);
    }

    public boolean isLoginButtonVisible()
    {
        return isElementDisplayed(LOGIN_BUTTON);
    }

    public String getItemPrice(String item)
    {
        String newPriceXpath=String.format(PRICE_XPATH,item);
        return getElementText(newPriceXpath);
    }







}
