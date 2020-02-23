package com.chemicalsafety.infosafecsi_onmoveandroid.Entities;

import java.util.ArrayList;

public class searchItemList {

    public static ArrayList<searchItemList> tableList = new ArrayList<>();

//    private String sdsno;
    private String synno;
    private String company;
    private String issueDate;
    private String prodname;
//    private String prodtype;
//    private String ispartial;
//    private String ps;
    private String unno;
//    private String subrisk1;
    private String prodcode;
//    private String dgclass;
    private String GHS_Pictogram;
    private String Com_Country;
//    private String haz;


//    public searchItemList(String sdsnov, String synnov, String companyv, String issueDatev, String prodnamev, String prodtypev, String ispartialv, String psv, String unnov, String subrisk1v, String prodcodev, String dgclassv, String GHS_Pictogramv, String Com_Countryv, String hazv) {
//
    public searchItemList(String synnov, String companyv, String issueDatev, String prodnamev, String unnov, String prodcodev, String GHS_Pictogramv, String Com_Countryv) {

//        sdsno = sdsnov;
        synno = synnov;
        company = companyv;
        issueDate = issueDatev;
        prodname = prodnamev;
//        prodtype = prodtypev;
//        ispartial = ispartialv;
//        ps = psv;
        unno = unnov;
//        subrisk1 = subrisk1v;
        prodcode = prodcodev;
//        dgclass = dgclassv;
        GHS_Pictogram = GHS_Pictogramv;
        Com_Country = Com_Countryv;
//        haz = hazv;

    }

//    public String getSdsno() {
//        return sdsno;
//    }

    public String getSynno() {
        return synno;
    }

    public String getCompany() {
        return company;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public String getProdname() {
        return prodname;
    }

//    public String getProdtype() {
//        return prodtype;
//    }
//
//    public String getIspartial() { return  ispartial; }
//
//    public String getPs() { return ps; }

    public String getUnno() { return unno;}

//    public String getSubrisk1() { return subrisk1;}

    public String getProdcode() { return prodcode;}

//    public String getDgclass() { return dgclass;}

    public String getGHS_Pictogram() { return GHS_Pictogram;}

    public String getCom_Country() { return Com_Country;}

//    public String getHaz() { return haz;}
}
