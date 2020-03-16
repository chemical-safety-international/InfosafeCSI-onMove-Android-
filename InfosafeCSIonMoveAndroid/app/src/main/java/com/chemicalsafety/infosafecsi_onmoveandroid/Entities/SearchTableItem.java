package com.chemicalsafety.infosafecsi_onmoveandroid.Entities;

import java.util.ArrayList;

public class SearchTableItem {

    public static int pcount;
    public static int lcount;
    public static int ocount;
    public static int totalcount;

    private String pname;
    private String country;
    private String supplier;
    private String unno;
    private String pcode;
    private String date;


    public SearchTableItem(String pnamev, String countryv, String supplierv, String unnov, String pcodev, String datev) {
        pname = pnamev;
        country = countryv;
        supplier = supplierv;
        unno = unnov;
        pcode = pcodev;
        date = datev;
    }

    public String getPname() {
        return pname;
    }

    public String getCountry() {
        return country;
    }

    public String getSupplier() {
        return supplier;
    }

    public String getUnno() {
        return unno;
    }

    public String getPcode() {
        return pcode;
    }

    public String getDate() {
        return date;
    }

//    private String sdsno;
//
//    public static ArrayList<SearchTableItem> sdsnoList = new ArrayList<>();
//
//    public SearchTableItem(String sdsnov) {
//        this.sdsno = sdsnov;
//    }
//
//    public String getSdsno() {
//        return sdsno;
//    }
}
