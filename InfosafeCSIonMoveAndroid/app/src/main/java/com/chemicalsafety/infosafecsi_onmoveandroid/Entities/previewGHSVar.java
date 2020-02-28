package com.chemicalsafety.infosafecsi_onmoveandroid.Entities;

import java.util.ArrayList;

public class previewGHSVar {

    public static String formatcode;
    public static String classification;
    public static String dg;
    public static String hstate;
    public static String ps;
    public static String pstate;
    public static String pic;
    public static String rphrase;
    public static String sds;
    public static String sphrase;
    public static String ps_general;
    public static String ps_response;
    public static String ps_prevention;
    public static String ps_storage;
    public static String ps_disposal;

    private String ghscimg1;
    private String ghscimg2;
    private String ghscimg3;
    private String ghscimg4;
    private String ghscimg5;

    public static ArrayList<previewGHSVar> ghscImgList = new ArrayList<>();

    public previewGHSVar(String ghscimgV1, String ghscimgV2, String ghscimgV3, String ghscimgV4, String ghscimgV5){
        ghscimg1 = ghscimgV1;
        ghscimg2 = ghscimgV2;
        ghscimg3 = ghscimgV3;
        ghscimg4 = ghscimgV4;
        ghscimg5 = ghscimgV5;
    }

    public String getGhscimg1() {
        return ghscimg1;
    }

    public String getGhscimg2() {
        return ghscimg2;
    }

    public String getGhscimg3() {
        return ghscimg3;
    }

    public String getGhscimg4() {
        return ghscimg4;
    }

    public String getGhscimg5() {
        return ghscimg5;
    }
}
