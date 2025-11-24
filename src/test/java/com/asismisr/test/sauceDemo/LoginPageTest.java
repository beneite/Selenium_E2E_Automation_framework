package com.asismisr.test.sauceDemo;

import com.asismisr.annotations.TestCategoryAnnotation;
import com.asismisr.configs.Config;
import com.asismisr.constants.Constants;
import com.asismisr.enums.TestGroupEnum;
import com.asismisr.pages.sauceDemo.LogInPage;
import com.asismisr.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public final class LoginPageTest extends BaseTest {
    private LoginPageTest()
    {
       // No need of object of this class
    }

    @TestCategoryAnnotation(testAuthors = "Ashish", testGroups = {TestGroupEnum.REGRESSION})
    @Test
    public void loginTest()
    {
        LogInPage logInPage=new LogInPage();
        logInPage.openUrl(Config.getTestProperty(Constants.SAUCEDEMO_URL));
        logInPage.enterUsernameandPassword(Config.getTestProperty(Constants.LOGIN_USERNAME),Config.getTestProperty(Constants.LOGIN_PASSWORD));
        Assert.assertEquals(logInPage.getValueofLoginButton(),Constants.LOGIN_BUTTON_ATTRIBUTE);
        logInPage.clickOnButton();

    }

}
