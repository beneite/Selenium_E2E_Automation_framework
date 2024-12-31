package com.asismisr.test.vendorPortal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Employee {
    public String username;
    public String password;
    public String monthlyEarning;
    public String annualEarning;
    public String profitMargin;
    public String availableInventory;
    public String searchKeyword;
    public Integer searchResultsCount;
}
