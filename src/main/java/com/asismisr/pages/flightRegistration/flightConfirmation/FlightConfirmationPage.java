package com.asismisr.pages.flightRegistration.flightConfirmation;

import com.asismisr.pages.BasePage;
import org.openqa.selenium.By;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

public final class FlightConfirmationPage extends BasePage {

    private static Logger log =  LoggerFactory.getLogger(FlightConfirmationPage.class);

    private static final By FLIGHT_CONFIRMATION_ELEMENT = By.xpath("//*[@id='flights-confirmation-section']//div[contains(@class, 'card-body')]//div[contains(@class, 'row')][1]//div[contains(@class, 'col')][2]");
    private static final By TOTAL_PRICE_ELEMENT = By.xpath("//*[@id='flights-confirmation-section']//div[contains(@class, 'card-body')]//div[contains(@class, 'row')][3]//div[contains(@class, 'col')][2]");


    public FlightConfirmationPage() {
    }

    public boolean isAt() {
        return isElementVisible(FLIGHT_CONFIRMATION_ELEMENT);
    }

    public String getPrice(){
        String confirmation = getText(FLIGHT_CONFIRMATION_ELEMENT);
        String price = getText(TOTAL_PRICE_ELEMENT);
        log.info("Flight confirmation# : {}", confirmation);
        log.info("Total price : {}", price);
        return price;
    }
}
