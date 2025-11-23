package com.asismisr.serviceLayer.vendorPortal;

import com.asismisr.listeners.ListenersClass;
import com.asismisr.pages.vendorPortal.userLogin.LoginPage;

public class VendorPortalServiceLayer {

    private LoginPage loginPage = new LoginPage();

    public void enterCredentialToLoginService(String userName, String password){
        loginPage.login(userName, password);
        ListenersClass.attachScreenshotWithMessage("Creds entered");
    }
}
