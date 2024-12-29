package com.asismisr.test.vendorPortal;

import com.asismisr.configs.Config;
import com.asismisr.pages.vendorPortal.dashboard.DashboardPage;
import com.asismisr.pages.vendorPortal.userLogin.LoginPage;
import com.asismisr.test.BaseTest;
import com.asismisr.test.vendorPortal.model.VendorPortalTestData;
import com.asismisr.constants.Constants;
import com.asismisr.utils.extentreport.ExtentReportManager;
import com.asismisr.utils.extentreport.ExtentReportUtils;
import com.asismisr.utils.jsonparsing.JsonUtil;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public final class VendorPortalTest extends BaseTest {

    private VendorPortalTest(){
        // making the test class final so that it should not be inherited
    }

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath){
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }

    @Test
    public void loginTest(){
        ExtentReportUtils.extentCreateTest("[Vendor-001] Login Test");
        this.loginPage = new LoginPage();
        loginPage.goTo(Config.getTestProperty(Constants.VENDOR_PORTAL_URL));
        ExtentReportManager.getExtentTestFromThreadLocal().info("URL Launched");
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(), testData.password());
        ExtentReportManager.getExtentTestFromThreadLocal().pass("[Vendor-001] Test passed");
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
        ExtentReportUtils.extentCreateTest("[Vendor-002] Dashboard Test");

        // login test [Vendor-001] Login Test
        this.loginPage = new LoginPage();
        loginPage.goTo(Config.getTestProperty(Constants.VENDOR_PORTAL_URL));
        ExtentReportManager.getExtentTestFromThreadLocal().info("URL Launched");
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(), testData.password());

        // dashboard test
        this.dashboardPage = new DashboardPage();
        Assert.assertTrue(dashboardPage.isAt());

        // finance metrics
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());

        // order history search
        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), testData.searchResultsCount());
        ExtentReportManager.getExtentTestFromThreadLocal().pass("[Vendor-002] Test passed");
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){
        ExtentReportUtils.extentCreateTest("[Vendor-003] Logout Test");

        // login test [Vendor-001] Login Test
        this.loginPage = new LoginPage();
        loginPage.goTo(Config.getTestProperty(Constants.VENDOR_PORTAL_URL));
        ExtentReportManager.getExtentTestFromThreadLocal().info("URL Launched");
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(), testData.password());

        // dashboard test [Vendor-002] Dashboard Test
        this.dashboardPage = new DashboardPage();
        Assert.assertTrue(dashboardPage.isAt());

        // finance metrics
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());

        // order history search
        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), testData.searchResultsCount());

        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
        ExtentReportManager.getExtentTestFromThreadLocal().pass("[Vendor-003] Test passed");
    }

}
