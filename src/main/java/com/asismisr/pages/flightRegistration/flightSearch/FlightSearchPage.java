package com.asismisr.pages.flightRegistration.flightSearch;

import com.asismisr.pages.BasePage;
import org.openqa.selenium.By;

public final class FlightSearchPage extends BasePage {

    private static final By PASSENGER_SELECT = By.xpath("//*[@id='passengers']");
    private static final By SEARCH_FLIGHTS_BUTTON = By.xpath("//*[@id='search-flights']");


    public FlightSearchPage() {
    }

    public boolean isAt() {
        return isElementVisible(PASSENGER_SELECT);
    }

    public void selectPassengers(String noOfPassengers){
        selectByDropDownValue(PASSENGER_SELECT, noOfPassengers);
    }

    public void searchFlights(){
        clickElement(SEARCH_FLIGHTS_BUTTON);
    }
}
