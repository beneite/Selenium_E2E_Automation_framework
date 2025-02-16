package com.asismisr.test.vendorPortal;

import com.asismisr.annotations.TestCategoryAnnotation;
import com.asismisr.configs.Config;
import com.asismisr.enums.TestGroupEnum;
import com.asismisr.pages.vendorPortal.dashboard.DashboardPage;
import com.asismisr.pages.vendorPortal.userLogin.LoginPage;
import com.asismisr.test.BaseTest;
import com.asismisr.test.vendorPortal.model.Employee;
import com.asismisr.constants.Constants;
import com.asismisr.utils.extentreport.ExtentReportLogger;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public final class VendorPortalTest extends BaseTest {

    private VendorPortalTest(){
        // making the test class final so that it should not be inherited
    }

    private static Logger log =  LoggerFactory.getLogger(VendorPortalTest.class);

    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @DataProvider(name = "getEmployeeData", parallel = true)
    public Object[][] getEmployeeData() throws IOException {
        String filePath = System.getProperty("user.dir")+"/src/test/resources/test-data/vendorPortal/EmployeeList.json";

        log.info("[Data provider] Reading from: " + filePath);
        ObjectMapper objectMapper = new ObjectMapper();

        // Read the JSON file into a List of Employee objects
        List<Employee> employeeList = objectMapper.readValue(new File(filePath), new TypeReference<List<Employee>>() {});

        // Create an Object[][] to hold Employee objects for the test
        Object[][] employeeObject = new Object[employeeList.size()][1];

        // Populate the 2D array with individual Employee objects
        int i = 0;
        for (Employee e : employeeList) {
            employeeObject[i][0] = e; // Wrap each Employee object in an array (for Object[][])
            i++;
        }

        return employeeObject;
    }

    @TestCategoryAnnotation(testAuthors = "Ashish", testGroups = {TestGroupEnum.SMOKE})
    @Test(dataProvider = "getEmployeeData")
    public void loginTest(Employee employee){
        this.loginPage = new LoginPage();
        loginPage.goTo(Config.getTestProperty(Constants.VENDOR_PORTAL_URL));
        ExtentReportLogger.info("URL Launched");
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(employee.getUsername(), employee.getPassword());
    }

    @TestCategoryAnnotation(testAuthors = "Mohit", testGroups = {TestGroupEnum.SMOKE, TestGroupEnum.REGRESSION})
    @Test(dataProvider = "getEmployeeData")
    public void dashboardTest(Employee employee){

        // login test [Vendor-001] Login Test
        this.loginPage = new LoginPage();
        loginPage.goTo(Config.getTestProperty(Constants.VENDOR_PORTAL_URL));
        ExtentReportLogger.info("URL Launched");
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(employee.getUsername(), employee.getPassword());

        // dashboard test
        this.dashboardPage = new DashboardPage();
        Assert.assertTrue(dashboardPage.isAt());

        // finance metrics
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), employee.getMonthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(), employee.getAnnualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(), employee.getProfitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), employee.getAvailableInventory());

        // order history search
        dashboardPage.searchOrderHistoryBy(employee.getSearchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), employee.getSearchResultsCount());
    }

    @TestCategoryAnnotation(testAuthors = "Kapil", testGroups = {TestGroupEnum.REGRESSION, TestGroupEnum.E2E})
    @Test(dataProvider = "getEmployeeData")
    public void logoutTest(Employee employee){

        // login test [Vendor-001] Login Test
        this.loginPage = new LoginPage();
        loginPage.goTo(Config.getTestProperty(Constants.VENDOR_PORTAL_URL));
        ExtentReportLogger.info("URL Launched");
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(employee.getUsername(), employee.getPassword());

        // dashboard test [Vendor-002] Dashboard Test
        this.dashboardPage = new DashboardPage();
        Assert.assertTrue(dashboardPage.isAt());

        // finance metrics
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), employee.getMonthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(), employee.getAnnualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(), employee.getProfitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), employee.getAvailableInventory());

        // order history search
        dashboardPage.searchOrderHistoryBy(employee.getSearchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), employee.getSearchResultsCount());

        dashboardPage.logout();
        //TODO: need to add assertJ
        Assert.assertTrue(loginPage.isAt());
    }

}
