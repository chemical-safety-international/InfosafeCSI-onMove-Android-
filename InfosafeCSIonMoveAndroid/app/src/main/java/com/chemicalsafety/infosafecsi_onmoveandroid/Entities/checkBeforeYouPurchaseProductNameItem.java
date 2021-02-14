package com.chemicalsafety.infosafecsi_onmoveandroid.Entities;

import java.util.ArrayList;

public class checkBeforeYouPurchaseProductNameItem {

        public static ArrayList<searchItemList> tableList = new ArrayList<>();

        public static ArrayList<checkBeforeYouPurchaseProductNameItem> checkBeforeYouPurchaseTableList = new ArrayList<>();

        private String productName;
        private Integer noOfSupplier;

        public static String productNamePass;

        public checkBeforeYouPurchaseProductNameItem(String productNamev, Integer noOfSupplierv) {
                productName = productNamev;
                noOfSupplier = noOfSupplierv;

        }

        public String getProductName() {
                return productName;
        }

        public Integer getNoOfSupplier() {
                return noOfSupplier;
        }

}
