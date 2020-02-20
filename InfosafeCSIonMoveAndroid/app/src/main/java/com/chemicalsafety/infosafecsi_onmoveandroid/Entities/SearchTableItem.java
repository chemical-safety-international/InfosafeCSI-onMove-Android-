package com.chemicalsafety.infosafecsi_onmoveandroid.Entities;

public class SearchTableItem {
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
}