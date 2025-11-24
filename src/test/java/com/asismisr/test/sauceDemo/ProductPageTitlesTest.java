package com.asismisr.test.sauceDemo;

import com.asismisr.annotations.TestCategoryAnnotation;
import com.asismisr.configs.Config;
import com.asismisr.constants.Constants;
import com.asismisr.enums.TestGroupEnum;
import com.asismisr.pages.sauceDemo.ProductPageTitles;
import com.asismisr.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public final class ProductPageTitlesTest extends BaseTest {
    private ProductPageTitlesTest()
    {
        // no need of object of this class
    }

    @TestCategoryAnnotation(testAuthors = "Ashish", testGroups = {TestGroupEnum.REGRESSION})
    @Test
    public void productPageTitleTest()
    {
        ProductPageTitles productPageTitles=new ProductPageTitles();
        productPageTitles.openUrl(Config.getTestProperty(Constants.SAUCEDEMO_URL));
        productPageTitles.enterDetails(Config.getTestProperty(Constants.LOGIN_USERNAME),Config.getTestProperty(Constants.LOGIN_PASSWORD));
        Assert.assertTrue(productPageTitles.isLoginButtonVisible());
        productPageTitles.clickOnLoginButton();
        System.out.println("price is="+productPageTitles.getItemPrice("Sauce Labs Bolt T-Shirt"));
    }

}
