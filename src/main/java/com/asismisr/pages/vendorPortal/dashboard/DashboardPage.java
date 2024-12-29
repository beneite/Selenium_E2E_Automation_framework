package com.asismisr.pages.vendorPortal.dashboard;

import com.asismisr.pages.BasePage;
import com.asismisr.utils.extentreport.ExtentReportLogger;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DashboardPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    private static final By MONTHLY_EARNING_ELEMENT = By.id("monthly-earning");
    private static final By ANNUAL_EARNING_ELEMENT = By.id("annual-earning");
    private static final By PROFIT_MARGIN_ELEMENT = By.id("profit-margin");
    private static final By AVAILABLE_INVENTORY_ELEMENT = By.id("available-inventory");
    private static final By SEARCH_INPUT = By.cssSelector("#dataTable_filter input");
    private static final By SEARCH_RESULTS_COUNT_ELEMENT = By.id("dataTable_info");
    private static final By USER_PROFILE_PICTURE_ELEMENT = By.cssSelector("img.img-profile");
    private static final By LOGOUT_LINK = By.linkText("Logout");
    private static final By MODAL_LOGOUT_BUTTON = By.cssSelector("#logoutModal a");


    public DashboardPage() {
    }

    public boolean isAt() {
        return isElementVisible(MONTHLY_EARNING_ELEMENT);
    }

    public String getMonthlyEarning(){
        return getText(MONTHLY_EARNING_ELEMENT);
    }

    public String getAnnualEarning(){
        return getText(ANNUAL_EARNING_ELEMENT);
    }

    public String getProfitMargin(){
        return getText(PROFIT_MARGIN_ELEMENT);
    }

    public String getAvailableInventory(){
        return getText(AVAILABLE_INVENTORY_ELEMENT);
    }

    public void searchOrderHistoryBy(String keyword){
        sendElement(SEARCH_INPUT, keyword);
        ExtentReportLogger.info("Passing "+keyword+" to search");
    }

    /*
        Showing 1 to 10 of 32 entries (filtered from 99 total entries)
        arr[0] = "Showing"
        arr[1] = "1"
        arr[2] = "to"
        arr[3] = "10"
        arr[4] = "of"
        arr[5] = "32"
        ...
        ...
     */
    public int getSearchResultsCount(){
        String resultsText = getText(SEARCH_RESULTS_COUNT_ELEMENT);
        String[] arr = resultsText.split(" ");
        // if we do not have 5th item, it would throw exception.
        // that's fine. we would want our test to fail anyway in that case!
        int count = Integer.parseInt(arr[5]);
        log.info("Results count: {}", count);
        return count;
    }

    public void logout(){
        clickElement(USER_PROFILE_PICTURE_ELEMENT);
        isElementVisible(LOGOUT_LINK);
        clickElement(LOGOUT_LINK);
        isElementVisible(MODAL_LOGOUT_BUTTON);
        clickElement(MODAL_LOGOUT_BUTTON);
        ExtentReportLogger.info("User clicked on Logout");
    }

}
