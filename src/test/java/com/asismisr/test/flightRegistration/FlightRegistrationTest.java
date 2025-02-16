package com.asismisr.test.flightRegistration;

import com.asismisr.annotations.TestCategoryAnnotation;
import com.asismisr.configs.Config;
import com.asismisr.enums.TestGroupEnum;
import com.asismisr.pages.flightRegistration.flightConfirmation.FlightConfirmationPage;
import com.asismisr.pages.flightRegistration.flightSearch.FlightSearchPage;
import com.asismisr.pages.flightRegistration.flightSelection.FlightSelectionPage;
import com.asismisr.pages.flightRegistration.registrationConfirmation.RegistrationConfirmationPage;
import com.asismisr.test.BaseTest;
import com.asismisr.test.flightRegistration.model.FlightReservationTestData;
import com.asismisr.pages.flightRegistration.customerRegistration.CustomerRegistrationPage;
import com.asismisr.constants.Constants;
import com.asismisr.utils.jsonparsing.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public final class FlightRegistrationTest extends BaseTest {


    private FlightRegistrationTest(){
        // making the test class final so that it should not be inherited
        // making constructor as final so that it should not be inherited
    }

    private FlightReservationTestData testData;
    private RegistrationConfirmationPage registrationConfirmationPage;
    private FlightSearchPage flightsSearchPage;
    private FlightSelectionPage flightsSelectionPage;
    private FlightConfirmationPage flightConfirmationPage;

    @BeforeTest
    @Parameters("testDataPath")
    public void setParameters(String testDataPath){
        this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
    }

    @TestCategoryAnnotation(testAuthors = "Kapil", testGroups = {TestGroupEnum.SMOKE, TestGroupEnum.REGRESSION, TestGroupEnum.SANITY})
    @Test
    public void userRegistrationTest(){
//        //ExtentReportUtils.getTest().info("Verify userRegistrationTest");
        CustomerRegistrationPage customerRegistrationPage = new CustomerRegistrationPage();
        customerRegistrationPage.goTo(Config.getTestProperty(Constants.FLIGHT_RESERVATION_URL));
//        //ExtentReportUtils.getTest().info("Navigating to URL:"+Config.getTestProperty(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(customerRegistrationPage.isAt());

        customerRegistrationPage.enterUserDetails(testData.firstName(), testData.lastName());
        customerRegistrationPage.enterUserCredentials(testData.email(), testData.password());
        customerRegistrationPage.enterAddress(testData.street(), testData.city(), testData.zip());
        customerRegistrationPage.register();
        //ExtentReportUtils.getTest().pass("User successfully registered");
    }

    @TestCategoryAnnotation(testAuthors = "Ashish", testGroups = {TestGroupEnum.REGRESSION})
    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest(){
        userRegistrationTest();
        //ExtentReportUtils.getTest().info("Verify registrationConfirmationTest");
        this.registrationConfirmationPage = new RegistrationConfirmationPage();
        Assert.assertTrue(registrationConfirmationPage.isAt());
        Assert.assertEquals(registrationConfirmationPage.getFirstName(), testData.firstName());
        registrationConfirmationPage.goToFlightsSearch();
        //ExtentReportUtils.getTest().pass("User registrationConfirmationTest pass");
    }

    @TestCategoryAnnotation(testAuthors = "Ashish", testGroups = {TestGroupEnum.REGRESSION})
    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightsSearchTest(){
        userRegistrationTest();
        registrationConfirmationTest();
        //ExtentReportUtils.getTest().info("Verify flightsSearchTest");
        this.flightsSearchPage = new FlightSearchPage();
        Assert.assertTrue(flightsSearchPage.isAt());
        flightsSearchPage.selectPassengers(testData.passengersCount());
        flightsSearchPage.searchFlights();
        //ExtentReportUtils.getTest().pass("User flightsSearchTest pass");
    }

    @TestCategoryAnnotation(testAuthors = "Ashish", testGroups = {TestGroupEnum.REGRESSION})
    @Test(dependsOnMethods = "flightsSearchTest")
    public void flightsSelectionTest(){
        userRegistrationTest();
        registrationConfirmationTest();
        flightsSearchTest();
        //ExtentReportUtils.getTest().info("Verify flightsSelectionTest");
        this.flightsSelectionPage = new FlightSelectionPage();
        Assert.assertTrue(flightsSelectionPage.isAt());
        flightsSelectionPage.selectFlights();
        flightsSelectionPage.confirmFlights();
        //ExtentReportUtils.getTest().pass("User flightsSelectionTest pass");
    }

    @TestCategoryAnnotation(testAuthors = "Ashish", testGroups = {TestGroupEnum.REGRESSION, TestGroupEnum.E2E})
    @Test(dependsOnMethods = "flightsSelectionTest")
    public void flightReservationConfirmationTest(){
        userRegistrationTest();
        registrationConfirmationTest();
        flightsSearchTest();
        flightsSelectionTest();
        //ExtentReportUtils.getTest().info("Verify flightReservationConfirmationTest");
        this.flightConfirmationPage = new FlightConfirmationPage();
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(), testData.expectedPrice());
        //ExtentReportUtils.getTest().pass("User flightsSelectionTest pass");
    }

}
