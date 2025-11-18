package com.asismisr.pages.sauceDemo;

import com.asismisr.pages.BasePage;
import org.openqa.selenium.WebElement;

public final class ProductPage extends BasePage {
    private final String PAGE_TITLE_XPATH="//span[@class='title']";
    private final String ADD_TO_CART_BUTTON="//div[text()='%s']/../../../div[@class='pricebar']/button";
    private final String CART_ICON="//a[@class='shopping_cart_link']";


    public String productTitle()
    {

        return findElementsXpath(PAGE_TITLE_XPATH).getText();
    }

    public void addToCartButton(String itemName)
    {
        String formatedXpath=String.format(ADD_TO_CART_BUTTON,itemName);
        WebElement itemTobeAdded=findElementsXpath(formatedXpath);
        if (itemTobeAdded.getText().equals("Add to cart"))
        {
            clickOnElement(formatedXpath);
        }

    }

    public void clickOnCartIcon()
    {
        clickOnElement(CART_ICON);
    }

}
