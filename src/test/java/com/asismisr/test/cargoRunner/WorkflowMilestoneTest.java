package com.asismisr.test.cargoRunner;

import com.asismisr.annotations.TestCategoryAnnotation;
import com.asismisr.configs.Config;
import com.asismisr.constants.Constants;
import com.asismisr.enums.TestGroupEnum;
import com.asismisr.listeners.ListenersClass;
import com.asismisr.pages.cargoRunner.LoginPage;
import com.asismisr.pages.cargoRunner.WfmMilestonePage;
import com.asismisr.pages.cargoRunner.WorkflowManagementPage;
import com.asismisr.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WorkflowMilestoneTest extends BaseTest {

    @TestCategoryAnnotation(testAuthors = "Aditya", testGroups = {TestGroupEnum.REGRESSION, TestGroupEnum.SMOKE})
    @Test(dataProviderClass = com.asismisr.utils.dataProvide.DataSender.class ,dataProvider = "dataproviding")
    public void createMiletoneTest(String name, String code, String type) throws InterruptedException {
        LoginPage loginPage=new LoginPage();
        loginPage.goToUrl(Config.getTestProperty(Constants.CARGORUNNER_URL));
        Assert.assertFalse(loginPage.isSigninButtonEnable());
        loginPage.enteringLoginDetails(Config.getTestProperty(Constants.CARGORUNNER_USERNAME),Config.getTestProperty(Constants.CARGORUNNER_PASSWORD));
        Assert.assertTrue(loginPage.isSigninButtonEnable());
        loginPage.clickOnSigninButton();
        ListenersClass.attachScreenshotWithMessage("Login done");
        WorkflowManagementPage workflowManagementPage=new WorkflowManagementPage();
        workflowManagementPage.navigateToURL(Config.getTestProperty(Constants.CARGORUNNER_NAVIGATING_URL));
        WfmMilestonePage wfmMilestonePage=new WfmMilestonePage();
        Assert.assertEquals(wfmMilestonePage.returnsTitleText(),Config.getTestProperty(Constants.CARGORUNNER_TITLE_WF));
        wfmMilestonePage.clickOnMilestoneTab();
        Assert.assertTrue(wfmMilestonePage.isCreatenewmilestoneVisible());
        wfmMilestonePage.clickonMilestoneButton();
        Assert.assertTrue(wfmMilestonePage.isCreatemilestoneTitleVisible());
        Assert.assertFalse(wfmMilestonePage.isCreateButtonEnabled());
        wfmMilestonePage.fillingMilestoneName(name);
        wfmMilestonePage.fillingMilestoneCode(code);
        wfmMilestonePage.fillingMilestoneType(type);
        ListenersClass.attachScreenshotWithMessage("Milestone datas filled");
        Assert.assertTrue(wfmMilestonePage.isCreateButtonEnabled());
        wfmMilestonePage.clickOnCreateButton();
        Assert.assertTrue(wfmMilestonePage.isMilestoneNameListed(name));
        Assert.assertTrue(wfmMilestonePage.isMilestoneCodeListed(code));
        Assert.assertTrue(wfmMilestonePage.isMilestoneTypeListed(type));
        ListenersClass.attachScreenshotWithMessage("Present in milestone listing");
    }
}
