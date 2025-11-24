package com.asismisr.test.sauceDemo;

import com.asismisr.annotations.TestCategoryAnnotation;
import com.asismisr.configs.Config;
import com.asismisr.constants.Constants;
import com.asismisr.enums.TestGroupEnum;
import com.asismisr.pages.sauceDemo.LogInPage;
import com.asismisr.pages.sauceDemo.ProductPage;
import com.asismisr.pages.sauceDemo.YourCartPage;
import com.asismisr.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public final class YourCartPageTest extends BaseTest {
    private YourCartPageTest()
    {
        // No need to create object of this page
    }

    @TestCategoryAnnotation(testAuthors = "Ashish", testGroups = {TestGroupEnum.REGRESSION})
    @Test
    public void cartPageTest()
    {
        LogInPage logInPage=new LogInPage();
        logInPage.openUrl(Config.getTestProperty(Constants.SAUCEDEMO_URL));
        logInPage.enterUsernameandPassword(Config.getTestProperty(Constants.LOGIN_USERNAME),Config.getTestProperty(Constants.LOGIN_PASSWORD));
        Assert.assertEquals(logInPage.getValueofLoginButton(),Constants.LOGIN_BUTTON_ATTRIBUTE);
        logInPage.clickOnButton();
        ProductPage productPage=new ProductPage();
        Assert.assertEquals(productPage.productTitle(),Config.getTestProperty(Constants.PRODUCTPAGE_TITLE));
        productPage.addToCartButton(Config.getTestProperty(Constants.ITEM_NAME));
        productPage.clickOnCartIcon();
        YourCartPage yourCartPage=new YourCartPage();
        Assert.assertEquals(yourCartPage.namePresenceOnCartPage(),Config.getTestProperty(Constants.ITEM_NAME));
        Assert.assertEquals(yourCartPage.yourcartPagetitle(),Config.getTestProperty(Constants.YOURCART_TITLE));
        yourCartPage.clickOnCheckoutButton();

    }
}
