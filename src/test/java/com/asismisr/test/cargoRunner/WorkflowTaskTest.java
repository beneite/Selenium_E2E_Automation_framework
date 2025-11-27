package com.asismisr.test.cargoRunner;

import com.asismisr.annotations.TestCategoryAnnotation;
import com.asismisr.configs.Config;
import com.asismisr.constants.Constants;
import com.asismisr.enums.TestGroupEnum;
import com.asismisr.listeners.ListenersClass;
import com.asismisr.pages.cargoRunner.LoginPage;
import com.asismisr.pages.cargoRunner.WorkflowManagementPage;
import com.asismisr.test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WorkflowTaskTest extends BaseTest {

    @TestCategoryAnnotation(testAuthors = "Aditya", testGroups = {TestGroupEnum.REGRESSION, TestGroupEnum.SMOKE})
    @Test
    public void verifyTaskCreationWithMandatoryFieldsOnly() throws InterruptedException {
        LoginPage loginPage=new LoginPage();
        loginPage.goToUrl(Config.getTestProperty(Constants.CARGORUNNER_URL));
        Assert.assertFalse(loginPage.isSigninButtonEnable());
        loginPage.enteringLoginDetails(Config.getTestProperty(Constants.CARGORUNNER_USERNAME),Config.getTestProperty(Constants.CARGORUNNER_PASSWORD));
        Assert.assertTrue(loginPage.isSigninButtonEnable());
        loginPage.clickOnSigninButton();
        Thread.sleep(5000);
        ListenersClass.attachScreenshotWithMessage("Login done");
        WorkflowManagementPage workflowManagementPage=new WorkflowManagementPage();
        workflowManagementPage.navigateToURL(Config.getTestProperty(Constants.CARGORUNNER_NAVIGATING_URL));
        Assert.assertTrue(workflowManagementPage.isCreateNewTaskButtonPresent());
        workflowManagementPage.clickOnCreateNewTask();
        Assert.assertFalse(workflowManagementPage.isCreateButtonEnable());
        workflowManagementPage.fillingDetailsinCreateNewTask(Config.getTestProperty(Constants.CARGORUNNER_FILLING_TASKNAME),Config.getTestProperty(Constants.CARGORUNNER_FILLING_TASKCODE),Config.getTestProperty(Constants.CARGORUNNER_FILLING_WORKFLOWTYPE));
        Assert.assertTrue(workflowManagementPage.isCreateButtonEnable());
        workflowManagementPage.clickonCreateButton();
        Assert.assertEquals(workflowManagementPage.getTextFromTaskTable(Config.getTestProperty(Constants.CARGORUNNER_FILLING_TASKNAME)),Config.getTestProperty(Constants.CARGORUNNER_FILLING_TASKNAME));

    }
}
