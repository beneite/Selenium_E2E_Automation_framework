package com.asismisr.pages.sauceDemo;

import com.asismisr.pages.BasePage;

public final class CheckoutPage extends BasePage {
    private final String FIRST_NAME="//input[@id='first-name']";
    private final String LAST_NAME="//input[@id='last-name']";
    private final String ZIP_CODE="//input[@id='postal-code']";
    private final String CONTINUE_BUTTON="//input[@id='continue']";
    private final String CHECKOUTPAGE_TITLEXPATH="//span[text()='Checkout: Your Information']";

    public String getPageTitle()
    {
        return findElementsXpath(CHECKOUTPAGE_TITLEXPATH).getText();
    }
    public boolean continueButtonVisiblity()
    {
        return isElementDisplayed(CONTINUE_BUTTON);
    }

    public void addYourInformation(String firstname,String lastname,String zipcode)
    {
        findElementsXpath(FIRST_NAME).sendKeys(firstname);
        findElementsXpath(LAST_NAME).sendKeys(lastname);
        findElementsXpath(ZIP_CODE).sendKeys(zipcode);
    }

    public void clickOnContinueButton()
    {
        clickOnElement(CONTINUE_BUTTON);
    }




}
