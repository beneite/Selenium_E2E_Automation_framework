package com.asismisr.pages.cargoRunner;

import com.asismisr.enums.WaitStrategyEnums;
import com.asismisr.pages.BasePage;
import org.openqa.selenium.Keys;

public class WorkflowManagementPage extends BasePage {

    private final String CREATE_NEW_TASK_BUTTON="//button[text()='Create New Task']";
    private final String TASK_NAME_FIELD="//label[text()='Task Name']/../div/input";
    private final String TASK_CODE_FIELD="//label[text()='Task Code']/../div/input";
    private final String WORKFLOW_TYPE_FIELD="//label[text()='Workflow Type*']/../div/input";
    private final String CREATE_BUTTON="//button[text()='Create']";
    private final String GET_ACTION_BUTTON ="//span[text()='%s']/../../../../td[7]/div/div/div/span";
    private final String UPDATE_BUTTON="//button[text()='Update']";
    private final String GET_TASK_CODE="//span[text()='%s']/../../../../td[3]/div/div/span";
    private final String TASK_DISCRIPTION="//label[text()='Task Description']";
    private final String EVENT_FIELD="//label[text()='Event']/../div/input";


    public String getTextFromTaskTable(String nameOfField)
    {
        String newXpath=String.format("//span[text()='%s']",nameOfField);
        return findElementWithWait(newXpath,WaitStrategyEnums.VISIBLE,10).getText();
    }

    public void navigateToURL(String url)
    {
        navigatingURL(url);
    }

    public boolean isCreateNewTaskButtonPresent()
    {
        return clickElementWithWait(CREATE_NEW_TASK_BUTTON, WaitStrategyEnums.VISIBLE,8).isDisplayed();
    }

    public void clickOnCreateNewTask()
    {
        clickOnElement(CREATE_NEW_TASK_BUTTON);
    }

    public boolean isCreateButtonEnable()
    {
        return isElementEnabled(CREATE_BUTTON);

    }

    public void fillingDetailsinCreateNewTask(String name,String code,String workflowfild)
    {
        clickElementWithWait(TASK_NAME_FIELD,WaitStrategyEnums.VISIBLE,8).sendKeys(name);
        clickElementWithWait(TASK_CODE_FIELD,WaitStrategyEnums.VISIBLE,8).sendKeys(code);
        clickElementWithWait(WORKFLOW_TYPE_FIELD,WaitStrategyEnums.VISIBLE,8).sendKeys(workflowfild,Keys.ARROW_DOWN,Keys.ENTER);
    }

    public void clickonCreateButton()
    {
        clickOnElement(CREATE_BUTTON);
    }

    public void clickOnAction(String nameOfTask)
    {
        String newXpath=xPathEditor(GET_ACTION_BUTTON,nameOfTask);
        clickOnElement(newXpath);
    }

    public void editingExistingTask(String name, String code, String workflowfield)
    {
        clearField(TASK_NAME_FIELD,WaitStrategyEnums.VISIBLE,8).clear();
        clearField(TASK_CODE_FIELD,WaitStrategyEnums.VISIBLE,8).clear();
        clearField(WORKFLOW_TYPE_FIELD,WaitStrategyEnums.VISIBLE,8).clear();
        fillingDetailsinCreateNewTask(name,code,workflowfield);
    }

    public void clearingFieldsInedittingTask()
    {

    }

    public void clickOnUpdateButton()
    {
        clickOnElement(UPDATE_BUTTON);
    }











}
