package com.asismisr.test.sauceDemo;

import com.asismisr.annotations.TestCategoryAnnotation;
import com.asismisr.configs.Config;
import com.asismisr.constants.Constants;
import com.asismisr.enums.TestGroupEnum;
import com.asismisr.pages.sauceDemo.LogInPage;
import com.asismisr.pages.sauceDemo.ProductPage;
import com.asismisr.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public final class ProductPageTest extends BaseTest {
    private ProductPageTest()
    {
        // No need of object of this class
    }

    @TestCategoryAnnotation(testAuthors = "Ashish", testGroups = {TestGroupEnum.REGRESSION})
    @Test
    public void clickOnCartTest()
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
    }

}
