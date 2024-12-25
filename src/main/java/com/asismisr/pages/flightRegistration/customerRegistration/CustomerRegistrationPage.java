package com.asismisr.pages.flightRegistration.customerRegistration;

import com.asismisr.enums.WaitStrategy;
import com.asismisr.pages.BasePage;
import org.openqa.selenium.By;

public final class CustomerRegistrationPage extends BasePage {

    private final By FIRST_NAME_INPUT = By.xpath("//*[@id='firstName']");
    private final By LAST_NAME_INPUT = By.xpath("//*[@id='lastName']");
    private final By EMAIL_INPUT = By.xpath("//*[@id='email']");
    private final By PASSWORD_INPUT = By.xpath("//*[@id='password']");
    private final By STREET_INPUT = By.xpath("//*[@name='street']");
    private final By CITY_INPUT = By.xpath("//*[@name='city']");
    private final By ZIP_INPUT = By.xpath("//*[@name='zip']");
    private final By REGISTER_BUTTON = By.xpath("//*[@id='register-btn']");

    public CustomerRegistrationPage(){}

    public boolean isAt() {
        return isElementVisible(FIRST_NAME_INPUT);
    }

    public void goTo(String url){
        goToUrl(url);
    }

    public void enterUserDetails(String firstName, String lastName){
        sendElementWithWait(FIRST_NAME_INPUT, firstName, WaitStrategy.ISPRESENT);
        sendElementWithWait(LAST_NAME_INPUT, lastName, WaitStrategy.ISPRESENT);
    }

    public void enterUserCredentials(String email, String password){
        sendElement(EMAIL_INPUT, email);
        sendElement(PASSWORD_INPUT, password);
    }

    public void enterAddress(String street, String city, String zip){
        sendElement(STREET_INPUT, street);
        sendElement(CITY_INPUT, city);
        sendElement(ZIP_INPUT, zip);
    }

    public void register(){
        clickElementWithWait(REGISTER_BUTTON, WaitStrategy.CLICKABLE);
    }
}
