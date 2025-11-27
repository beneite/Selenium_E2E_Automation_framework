package com.asismisr.pages.cargoRunner;

import com.asismisr.enums.WaitStrategyEnums;
import com.asismisr.pages.BasePage;
import org.openqa.selenium.Keys;

public class WfmMilestonePage extends BasePage {
    private final String WFM_TILE="//div[text()='WORKFLOW MANAGEMENT']";
    private final String MILESTONE_TAB="//div[text()='Milestone']";
    private final String CREATE_NEW_MILESTONE_BUTTON="//button[text()='Create New Milestone']";
    private final String CREATE_MILESTONE_TITLE="//div[text()='Create New Milestone']";
    private final String CREATE_BUTTON="//button[text()='Create']";
    private final String FIELD_MILESTONE_NAME="//label[text()='Milestone Name']/../div/input";
    private final String FIELD_MILESTONE_CODE="//label[text()='Milestone Code']/../div/input";
    private final String FIELD_WORKFLOWTYPE_CODE="//label[text()='Workflow Type*']/../div/input";
    private final String FIELD_MILESTONE_DESCRIPTION="//label[text()='Milestone Description']/../div/textarea[@aria-invalid='false']";
    private final String FIELD_CHECKBOX_TICK="//div[text()='%s']/../../div/span/input";
    private final String CUSTOMER_FIELD_CHECKBOX="//div[text()='Show on Customer Portal']/../../div/span/input";
    private final String DYNAMIC_FIELD_CHECKBOX="//div[text()='Dynamic']/../../div/span/input";

    private final String LIST_NAME="//span[text()='%s']";
    private final String LIST_CODE="//span[text()='%s']/../../../../td[3]/div/div/span";
    private final String LIST_WORKTYPE="//span[text()='%s']/../../../../td[4]/div/div/span";
    private final String LIST_DISCRIPTION="//span[text()='%s']/../../../../td[2]/div/div/span";
    

    public String returnsTitleText()
    {
        return findElementWithWait(WFM_TILE, WaitStrategyEnums.VISIBLE, 10).getText();
    }

    public void clickOnMilestoneTab()
    {
        clickOnElement(MILESTONE_TAB);
    }

    public boolean isCreatenewmilestoneVisible()
    {
        return findElementWithWait(CREATE_NEW_MILESTONE_BUTTON, WaitStrategyEnums.VISIBLE,10).isDisplayed();
    }

    public void clickonMilestoneButton()
    {
        clickOnElement(CREATE_NEW_MILESTONE_BUTTON);
    }

    public boolean isCreatemilestoneTitleVisible()
    {
        return findElementWithWait(CREATE_MILESTONE_TITLE,WaitStrategyEnums.VISIBLE,10).isDisplayed();
    }

    public boolean isCreateButtonEnabled()
    {
        return findElementWithWait(CREATE_BUTTON,WaitStrategyEnums.VISIBLE,10).isEnabled();
    }

    public void fillingMilestoneName(String milestoneName)
    {
        findElementsXpath(FIELD_MILESTONE_NAME).sendKeys(milestoneName);
    }

    public void fillingMilestoneCode(String milestoneCode)
    {
        findElementsXpath(FIELD_MILESTONE_CODE).sendKeys(milestoneCode);
    }

    public void fillingMilestoneType(String workflowType)
    {
        findElementsXpath(FIELD_WORKFLOWTYPE_CODE).sendKeys(workflowType,Keys.ARROW_DOWN,Keys.ENTER);
    }

    public void fillingMilestoneDiscription(String discriptionText)
    {
        findElementsXpath(FIELD_MILESTONE_DESCRIPTION).sendKeys(discriptionText);
    }

    public void tickOnShowonCustomerCheckbox()
    {
        clickOnElement(CUSTOMER_FIELD_CHECKBOX);
    }

    public void tickOnDynamicCheckbox()
    {
        clickOnElement(DYNAMIC_FIELD_CHECKBOX);
    }
    

    public void clickOnCreateButton()
    {
        clickOnElement(CREATE_BUTTON);
    }

    public boolean isMilestoneNameListed(String milestoneName)
    {
        String newXpath=xPathEditor(LIST_NAME,milestoneName);
        return clickElementWithWait(newXpath,WaitStrategyEnums.VISIBLE,10).isDisplayed();
    }

    public boolean isMilestoneCodeListed(String milestoneCode)
    {
        String newXpath=xPathEditor(LIST_CODE,milestoneCode);
        return clickElementWithWait(newXpath,WaitStrategyEnums.VISIBLE,10).isDisplayed();
    }

    public boolean isMilestoneTypeListed(String milestoneType)
    {
        String newXpath=xPathEditor(LIST_WORKTYPE,milestoneType);
        return clickElementWithWait(newXpath,WaitStrategyEnums.VISIBLE,10).isDisplayed();
    }

    public boolean isMilestoneDiscriptionListed(String discription)
    {
        String newXpath=xPathEditor(LIST_DISCRIPTION,discription);
        return clickElementWithWait(newXpath,WaitStrategyEnums.VISIBLE,10).isDisplayed();
    }








}
