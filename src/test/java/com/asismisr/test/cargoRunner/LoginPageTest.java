package com.asismisr.test.cargoRunner;

import com.asismisr.annotations.TestCategoryAnnotation;
import com.asismisr.configs.Config;
import com.asismisr.constants.Constants;
import com.asismisr.enums.TestGroupEnum;
import com.asismisr.listeners.ListenersClass;
import com.asismisr.pages.cargoRunner.LoginPage;
import com.asismisr.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @TestCategoryAnnotation(testAuthors = "Ashish", testGroups = {TestGroupEnum.REGRESSION})
    @Test
    public void loginTest()
    {
        LoginPage loginPage=new LoginPage();
        loginPage.goToUrl(Config.getTestProperty(Constants.CARGORUNNER_URL));
        Assert.assertFalse(loginPage.isSigninButtonEnable());
        loginPage.enteringLoginDetails(Config.getTestProperty(Constants.CARGORUNNER_USERNAME),Config.getTestProperty(Constants.CARGORUNNER_PASSWORD));
        Assert.assertTrue(loginPage.isSigninButtonEnable());
        loginPage.clickOnSigninButton();
        ListenersClass.attachScreenshotWithMessage(
                "Login with user "+Config.getTestProperty(Constants.CARGORUNNER_USERNAME)
                        +"With password "+Config.getTestProperty(Constants.CARGORUNNER_PASSWORD));
    }
}
