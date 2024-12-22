package com.asismisr.pages.flightRegistration.registrationConfirmation;

import com.asismisr.pages.BasePage;
import org.openqa.selenium.By;

public final class RegistrationConfirmationPage extends BasePage {

    private final By GO_TO_FLIGHTS_SEARCH_BUTTON = By.xpath("//*[@id='go-to-flights-search']");
    private final By FIRST_NAME_ELEMENT = By.xpath("//*[@id='registration-confirmation-section']//p//b");


    public RegistrationConfirmationPage(){
    }

    public boolean isAt() {
        return isElementVisible(GO_TO_FLIGHTS_SEARCH_BUTTON);
    }

    public String getFirstName(){
        return getText(FIRST_NAME_ELEMENT);
    }

    public void goToFlightsSearch(){
        clickElement(GO_TO_FLIGHTS_SEARCH_BUTTON);
    }
}
