package com.asismisr.test.sauceDemo;

import com.asismisr.annotations.TestCategoryAnnotation;
import com.asismisr.configs.Config;
import com.asismisr.constants.Constants;
import com.asismisr.enums.TestGroupEnum;
import com.asismisr.pages.sauceDemo.*;
import com.asismisr.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public final class OverviewPageTest extends BaseTest {
    private OverviewPageTest()
    {
        // No nee of object for this class
    }

    @TestCategoryAnnotation(testAuthors = "Ashish", testGroups = {TestGroupEnum.REGRESSION})
    @Test(dataProvider = "dataSendingPath")
    public void overviewPageTesting(String firstname_data,String lastname_data,String zipcode_data)
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
        CheckoutPage checkoutPage=new CheckoutPage();
        Assert.assertTrue(checkoutPage.continueButtonVisiblity());
        Assert.assertEquals(checkoutPage.getPageTitle(),Config.getTestProperty(Constants.CHECKOUT_TITLE));
        checkoutPage.addYourInformation(firstname_data,lastname_data,zipcode_data);
        checkoutPage.clickOnContinueButton();
        OverviewPage overviewPage=new OverviewPage();
        Assert.assertTrue(overviewPage.checkPaymentInformationtextisVisible());
        Assert.assertEquals(overviewPage.getTextofPriceTotal(),Config.getTestProperty(Constants.PRICETOTAL_TEXT));
        overviewPage.clickOnFinishButton();

    }
}
