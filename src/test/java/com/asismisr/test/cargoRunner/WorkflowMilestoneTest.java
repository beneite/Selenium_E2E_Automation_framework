package com.asismisr.test.cargoRunner;

import com.asismisr.annotations.TestCategoryAnnotation;
import com.asismisr.codeUtils.CommonUtilis;
import com.asismisr.configs.Config;
import com.asismisr.constants.Constants;
import com.asismisr.enums.TestGroupEnum;
import com.asismisr.listeners.ListenersClass;
import com.asismisr.pages.cargoRunner.LoginPage;
import com.asismisr.pages.cargoRunner.WfmMilestonePage;
import com.asismisr.pages.cargoRunner.WorkflowManagementPage;
import com.asismisr.pojo.wfm.MilestoneCreationPojo;
import com.asismisr.test.BaseTest;
import com.asismisr.utils.dataProvide.DataSender;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WorkflowMilestoneTest extends BaseTest {

    @TestCategoryAnnotation(testAuthors = "Aditya", testGroups = {TestGroupEnum.REGRESSION, TestGroupEnum.SMOKE})
    @Test(priority = 0, dataProviderClass = com.asismisr.utils.dataProvide.DataSender.class ,dataProvider = "dataproviding")
    public void createMiletoneTest(MilestoneCreationPojo milestoneCreationPojo) throws InterruptedException {
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
        wfmMilestonePage.fillingMilestoneName(milestoneCreationPojo.getMileStoneName());
        wfmMilestonePage.fillingMilestoneCode(milestoneCreationPojo.getMileStoneCode());
        wfmMilestonePage.fillingMilestoneType(milestoneCreationPojo.getMileStoneWorkflowType());
        ListenersClass.attachScreenshotWithMessage("Milestone datas filled");
        Assert.assertTrue(wfmMilestonePage.isCreateButtonEnabled());
        wfmMilestonePage.clickOnCreateButton();
        Assert.assertTrue(wfmMilestonePage.isMilestoneNameListed(milestoneCreationPojo.getMileStoneName()));
        Assert.assertTrue(wfmMilestonePage.isMilestoneCodeListed(milestoneCreationPojo.getMileStoneCode()));
        Assert.assertTrue(wfmMilestonePage.isMilestoneTypeListed(milestoneCreationPojo.getMileStoneWorkflowType()));
        ListenersClass.attachScreenshotWithMessage("Present in milestone listing");
    }

    @TestCategoryAnnotation(testAuthors = "Aditya", testGroups = {TestGroupEnum.REGRESSION, TestGroupEnum.SMOKE})
    @Test(dataProviderClass = DataSender.class ,dataProvider = "dataproviding")
    public void createMilestoneWithallFieldsTest(MilestoneCreationPojo milestoneCreationPojo)
    {
        LoginPage loginPage=new LoginPage();
        milestoneCreationPojo.setMileStoneName(CommonUtilis.randomString(6)+"_MilestoneName");
        milestoneCreationPojo.setMileStoneCode(CommonUtilis.randomString(4)+"cde");
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
        wfmMilestonePage.fillingMilestoneName(milestoneCreationPojo.getMileStoneName());
        wfmMilestonePage.fillingMilestoneCode(milestoneCreationPojo.getMileStoneCode());
        wfmMilestonePage.fillingMilestoneDiscription(milestoneCreationPojo.getMileStoneDescription());
        wfmMilestonePage.fillingMilestoneType(milestoneCreationPojo.getMileStoneWorkflowType());
        Assert.assertTrue(wfmMilestonePage.isCreateButtonEnabled());
        if (milestoneCreationPojo.getMileStoneCustomerPortalFlag().equals("TRUE"))
        {
            wfmMilestonePage.tickOnShowonCustomerCheckbox();
        }
        if(milestoneCreationPojo.getMileStoneDynamicFlag().equals("TRUE")){
            wfmMilestonePage.tickOnDynamicCheckbox();
        }

        ListenersClass.attachScreenshotWithMessage("Milestone datas filled: customerPortalFlag:"
                +milestoneCreationPojo.getMileStoneCustomerPortalFlag()+"; dynamicFlag:"+milestoneCreationPojo.getMileStoneDynamicFlag());
        wfmMilestonePage.clickOnCreateButton();
        Assert.assertTrue(wfmMilestonePage.isMilestoneNameListed(milestoneCreationPojo.getMileStoneName()));
        Assert.assertTrue(wfmMilestonePage.isMilestoneCodeListed(milestoneCreationPojo.getMileStoneCode()));
        Assert.assertTrue(wfmMilestonePage.isMilestoneDiscriptionListed(milestoneCreationPojo.getMileStoneDescription()));
        Assert.assertTrue(wfmMilestonePage.isMilestoneTypeListed(milestoneCreationPojo.getMileStoneWorkflowType()));
        ListenersClass.attachScreenshotWithMessage("Present in milestone listing");
    }
}
