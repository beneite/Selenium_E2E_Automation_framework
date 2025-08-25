package com.asismisr.test.sauseLabDemo;

import com.asismisr.annotations.TestCategoryAnnotation;
import com.asismisr.configs.Config;
import com.asismisr.constants.Constants;
import com.asismisr.enums.TestGroupEnum;
import com.asismisr.pages.sauseLabDemo.LogInPage;
import com.asismisr.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public final class SauseLabLoginTest extends BaseTest {

    private SauseLabLoginTest()
    {
        // making the test class final so that it should not be inherited
        // making constructor as private so that object should not be created
    }

    @TestCategoryAnnotation(testAuthors = "Aditya", testGroups = {TestGroupEnum.SMOKE, TestGroupEnum.REGRESSION, TestGroupEnum.SANITY})
    @Test
    public void logInPageTest()
    {
        LogInPage logInPage=new LogInPage();
        logInPage.goToUrl(Config.getTestProperty("sauseDemo.url"));
        logInPage.enterLogInDetails("standard_user","secret_sauce");
        Assert.assertEquals(logInPage.elementVisiblity(),Constants.LOGIN_BUTTON);
        logInPage.clickLoginButton();
        Assert.assertEquals(logInPage.getLoginPageURL(),Constants.PRODUCT_PAGE_URL);
    }
}
