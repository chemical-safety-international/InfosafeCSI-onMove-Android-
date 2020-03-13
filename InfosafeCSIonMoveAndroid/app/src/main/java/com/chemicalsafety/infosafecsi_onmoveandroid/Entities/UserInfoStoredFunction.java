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

    public static String getLogo(Context context) {
        return getPrefs(context).getString("logo_key", "default_logo");
    }

    public static Boolean getStatus(Context context) {
        return getPrefs(context).getBoolean("status_key", false);
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

    public static void setLogo(Context context, String inputLogo) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("logo_key", inputLogo);
        editor.commit();
    }

    public static void setTrueStatus(Context context) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putBoolean("status_key", true);
        editor.commit();
    }

    public static void setFalseStatus(Context context) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putBoolean("status_key", false);
        editor.commit();
    }
}
