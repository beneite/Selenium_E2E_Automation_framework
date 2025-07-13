package com.asismisr.pages.flightRegistration.flightSelection;

import com.asismisr.configs.Config;
import com.asismisr.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.ThreadLocalRandom;

public final class FlightSelectionPage extends BasePage {

    private static final By DEPARTURE_FLIGHTS_OPTIONS = By.name("departure-flight");
    private static final By ARRIVAL_FLIGHTS_OPTIONS = By.name("arrival-flight");
    private static final By CONFIRM_FLIGHTS_BUTTON = By.xpath("//button[text()='Confirm Flights']");


    public FlightSelectionPage() {
    }

    public boolean isAt() {
        return isElementVisible(CONFIRM_FLIGHTS_BUTTON);
    }

    public void selectFlights(){
        int random = ThreadLocalRandom.current().nextInt(0, returnWebElementsFromBy(DEPARTURE_FLIGHTS_OPTIONS).size());
        clickWebElement(returnWebElementFromBy(DEPARTURE_FLIGHTS_OPTIONS,random));
        clickWebElement(returnWebElementFromBy(ARRIVAL_FLIGHTS_OPTIONS,random));
    }

    public void confirmFlights(){
        scrollToElement(CONFIRM_FLIGHTS_BUTTON);
        clickElement(CONFIRM_FLIGHTS_BUTTON);
    }
}
