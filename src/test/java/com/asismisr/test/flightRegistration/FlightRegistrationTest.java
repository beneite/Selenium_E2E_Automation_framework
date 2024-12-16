package com.asismisr.test.flightRegistration;

import com.asismisr.configs.Config;
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

// making the test class final so that it should not be inherited
public final class FlightRegistrationTest extends BaseTest {

    // making constructor as final so that it should not be inherited
    private FlightRegistrationTest(){}

    private FlightReservationTestData testData;
    private CustomerRegistrationPage registrationPage;
    private RegistrationConfirmationPage registrationConfirmationPage;
    private FlightSearchPage flightsSearchPage;
    private FlightSelectionPage flightsSelectionPage;
    private FlightConfirmationPage flightConfirmationPage;

    @BeforeTest
    @Parameters("testDataPath")
    public void setParameters(String testDataPath){
        this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
    }

    @Test
    public void userRegistrationTest(){
//        //ExtentReportUtils.getTest().info("Verify userRegistrationTest");
        this.registrationPage = new CustomerRegistrationPage(driver);
        registrationPage.goTo(Config.getTestProperty(Constants.FLIGHT_RESERVATION_URL));
//        //ExtentReportUtils.getTest().info("Navigating to URL:"+Config.getTestProperty(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enterUserDetails(testData.firstName(), testData.lastName());
        registrationPage.enterUserCredentials(testData.email(), testData.password());
        registrationPage.enterAddress(testData.street(), testData.city(), testData.zip());
        registrationPage.register();
        //ExtentReportUtils.getTest().pass("User successfully registered");
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest(){
        userRegistrationTest();
        //ExtentReportUtils.getTest().info("Verify registrationConfirmationTest");
        this.registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        Assert.assertEquals(registrationConfirmationPage.getFirstName(), testData.firstName());
        registrationConfirmationPage.goToFlightsSearch();
        //ExtentReportUtils.getTest().pass("User registrationConfirmationTest pass");
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightsSearchTest(){
        userRegistrationTest();
        registrationConfirmationTest();
        //ExtentReportUtils.getTest().info("Verify flightsSearchTest");
        this.flightsSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightsSearchPage.isAt());
        flightsSearchPage.selectPassengers(testData.passengersCount());
        flightsSearchPage.searchFlights();
        //ExtentReportUtils.getTest().pass("User flightsSearchTest pass");
    }

    @Test(dependsOnMethods = "flightsSearchTest")
    public void flightsSelectionTest(){
        userRegistrationTest();
        registrationConfirmationTest();
        flightsSearchTest();
        //ExtentReportUtils.getTest().info("Verify flightsSelectionTest");
        this.flightsSelectionPage = new FlightSelectionPage(driver);
        Assert.assertTrue(flightsSelectionPage.isAt());
        flightsSelectionPage.selectFlights();
        flightsSelectionPage.confirmFlights();
        //ExtentReportUtils.getTest().pass("User flightsSelectionTest pass");
    }

    @Test(dependsOnMethods = "flightsSelectionTest")
    public void flightReservationConfirmationTest(){
        userRegistrationTest();
        registrationConfirmationTest();
        flightsSearchTest();
        flightsSelectionTest();
        //ExtentReportUtils.getTest().info("Verify flightReservationConfirmationTest");
        this.flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(), testData.expectedPrice());
        //ExtentReportUtils.getTest().pass("User flightsSelectionTest pass");
    }

}
