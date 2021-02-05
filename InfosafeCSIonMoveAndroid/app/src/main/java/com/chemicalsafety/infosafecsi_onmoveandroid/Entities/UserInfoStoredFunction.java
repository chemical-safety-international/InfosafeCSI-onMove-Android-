package com.chemicalsafety.infosafecsi_onmoveandroid.Entities;

import android.content.Context;
import android.content.SharedPreferences;

public class UserInfoStoredFunction {

    private SharedPreferences sharedPreferences;
    private static String PREF_NAME = "pref";

    public void UserInfoStoredFunction() {

    }

    public static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static String getEmail(Context context) {
        return getPrefs(context).getString("email_key", "default_email");
    }


    public static String getPassword(Context context) {
        return getPrefs(context).getString("password_key", "default_password");
    }

    public static String getMultiPassword(Context context) {
        return getPrefs(context).getString("password_key" + loginVar.clientid, "default_password");
    }

    public static String getLogo(Context context) {
        return getPrefs(context).getString("logo_key", "default_logo");
    }

    public static Boolean getStatus(Context context, String clientid) {
        return getPrefs(context).getBoolean(clientid, false);
    }

    public static String getOTACode(Context context) {
        return getPrefs(context).getString("otacode_key" + loginVar.email, "default_otacode");
    }

    public static void setEmail(Context context, String inputEmail) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("email_key", inputEmail);
        editor.commit();
    }

    public static void setPassword(Context context, String inputPassword) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("password_key", inputPassword);
        editor.commit();
    }

    public static void setMultiPassword(Context context, String inputPassword) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("password_key" + loginVar.clientid, inputPassword);
        editor.commit();
    }

    public static void setLogo(Context context, String inputLogo) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("logo_key", inputLogo);
        editor.commit();
    }

    public static void setTrueStatus(Context context, String clientid) {

        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putBoolean(clientid, true);
        editor.commit();
    }

    public static void setFalseStatus(Context context, String clientid) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putBoolean(clientid, false);
        editor.commit();
    }

    public static void setOTACode(Runnable context, String inputOTACode) {
        SharedPreferences.Editor editor = getPrefs((Context) context).edit();
        editor.putString("otacode_key" + loginVar.email, inputOTACode);
        editor.commit();
    }
}
