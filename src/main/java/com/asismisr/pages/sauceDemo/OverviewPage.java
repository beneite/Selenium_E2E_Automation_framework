package com.asismisr.pages.sauceDemo;

import com.asismisr.pages.BasePage;

public final class OverviewPage extends BasePage {
    private final String PAYMENT_INFORMATIONXPATH="//div[text()='Payment Information:']";
    private final String PRICE_TOTALXPATH="//div[text()='Price Total']";
    private final String FINAL_BUTTON="//button[@id='finish']";

    public boolean checkPaymentInformationtextisVisible()
    {
        return isElementDisplayed(PAYMENT_INFORMATIONXPATH);
    }

    public String getTextofPriceTotal()
    {
        return getElementText(PRICE_TOTALXPATH);
    }

    public void clickOnFinishButton()
    {
        clickOnElement(FINAL_BUTTON);
    }

}
