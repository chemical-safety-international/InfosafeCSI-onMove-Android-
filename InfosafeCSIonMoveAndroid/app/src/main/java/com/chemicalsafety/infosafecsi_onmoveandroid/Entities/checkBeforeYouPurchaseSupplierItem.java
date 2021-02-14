package com.chemicalsafety.infosafecsi_onmoveandroid.Entities;

import java.util.ArrayList;
import java.util.Date;

public class checkBeforeYouPurchaseSupplierItem {

    public static ArrayList<searchItemList> tableList = new ArrayList<>();

    public static ArrayList<checkBeforeYouPurchaseSupplierItem> supplierItemsTableList = new ArrayList<>();

    private String supplier;
    private Integer noOfProduct;
    private String issueDate;

    private Date minDate;
    private Date maxDate;

    public static String productNamePass;

    public checkBeforeYouPurchaseSupplierItem(String supplierv, Integer noOfProductv, String issueDatev, Date minDatev, Date maxDatev) {
        supplier = supplierv;
        noOfProduct = noOfProductv;
        issueDate = issueDatev;
        minDate = minDatev;
        maxDate = maxDatev;
    }

    public String getSupplier() {
        return supplier;
    }

    public Integer getNoOfProduct() {
        return noOfProduct;
    }

    public String getIssueDate() { return issueDate; }

    public Date getMinDate() { return  minDate; }

    public Date getMaxDate() { return  maxDate; }

}
