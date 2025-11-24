package com.asismisr.pages.sauceDemo;

import com.asismisr.pages.BasePage;

public final class YourCartPage extends BasePage {
    private final String ITEM_PRESENT_NAME="//div[@class='cart_list']/div[@class='cart_item']/div[@class='cart_item_label']/a/div[@class='inventory_item_name']";
    private final String YOUR_CART_TITLE="//span[text()='Your Cart']";
    private final String CHECKOUT_BUTTON="//button[@id='checkout']";

    public String namePresenceOnCartPage()
    {
        return findElementsXpath(ITEM_PRESENT_NAME).getText();
    }

    public String yourcartPagetitle()
    {
        return findElementsXpath(YOUR_CART_TITLE).getText();
    }

    public void clickOnCheckoutButton()
    {
        clickOnElement(CHECKOUT_BUTTON);
    }

}
