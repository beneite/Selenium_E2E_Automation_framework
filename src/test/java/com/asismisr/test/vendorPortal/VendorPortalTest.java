package com.asismisr.test.vendorPortal;

import com.asismisr.configs.Config;
import com.asismisr.drivermanagement.DriverManager;
import com.asismisr.pages.vendorPortal.dashboard.DashboardPage;
import com.asismisr.pages.vendorPortal.userLogin.LoginPage;
import com.asismisr.test.BaseTest;
import com.asismisr.test.vendorPortal.model.VendorPortalTestData;
import com.asismisr.constants.Constants;
import com.asismisr.utils.jsonparsing.JsonUtil;
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
        this.loginPage = new LoginPage(DriverManager.getWebDriverFromThreadLocal());
        loginPage.goTo(Config.getTestProperty(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(), testData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
        loginTest();
        this.dashboardPage = new DashboardPage(DriverManager.getWebDriverFromThreadLocal());
        Assert.assertTrue(dashboardPage.isAt());

        // finance metrics
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(), testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.availableInventory());

        // order history search
        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), testData.searchResultsCount());
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){
        loginTest();
        dashboardTest();
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }

}
