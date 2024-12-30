package com.asismisr.test.vendorPortal;

import com.asismisr.configs.Config;
import com.asismisr.pages.vendorPortal.dashboard.DashboardPage;
import com.asismisr.pages.vendorPortal.userLogin.LoginPage;
import com.asismisr.test.BaseTest;
import com.asismisr.test.vendorPortal.model.Employee;
import com.asismisr.test.vendorPortal.model.EmployeeList;
import com.asismisr.test.vendorPortal.model.VendorPortalTestData;
import com.asismisr.constants.Constants;
import com.asismisr.utils.extentreport.ExtentReportLogger;
import com.asismisr.utils.jsonparsing.JsonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public final class VendorPortalTest extends BaseTest {

    private VendorPortalTest(){
        // making the test class final so that it should not be inherited
    }

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;
    private EmployeeList employeeList;


    @DataProvider
    @Parameters("testDataPath")
    public Object[][] getEmployeeData(String testDataPath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        EmployeeList employeeList1 = objectMapper.readValue(new File(testDataPath), EmployeeList.class);
        Object[][] employeeObject = new Object[employeeList1.getEmployeeList().size()][1];
        for(int i=0; i<employeeList1.getEmployeeList().size(); i++){
            employeeObject[i][1] = employeeList1.getEmployeeList().get(i);
        }

        return employeeObject;
    }

//    @BeforeTest
//    @Parameters("testDataPath")
//    public void setPageObjects(String testDataPath){
//        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
//    }

    @Test
    public void loginTest(Employee employee){
        this.loginPage = new LoginPage();
        loginPage.goTo(Config.getTestProperty(Constants.VENDOR_PORTAL_URL));
        ExtentReportLogger.info("URL Launched");
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(employee.getUsername(), employee.getPassword());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){

        // login test [Vendor-001] Login Test
        this.loginPage = new LoginPage();
        loginPage.goTo(Config.getTestProperty(Constants.VENDOR_PORTAL_URL));
        ExtentReportLogger.info("URL Launched");
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
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){

        // login test [Vendor-001] Login Test
        this.loginPage = new LoginPage();
        loginPage.goTo(Config.getTestProperty(Constants.VENDOR_PORTAL_URL));
        ExtentReportLogger.info("URL Launched");
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
    }

}
