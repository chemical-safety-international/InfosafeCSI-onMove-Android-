package com.chemicalsafety.infosafecsi_onmoveandroid;


import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.loginVar;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.text.DecimalFormat;


public class StartupPageAC extends AppCompatActivity {

    //    private Button welcomeBtn;
    Button welcomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeButton = findViewById(R.id.welcomeBtn);

//        Log.i("Local version:", getCurrentVersion());
//        Log.i("BudleID:", BuildConfig.APPLICATION_ID );
        try {
            final String googlePlayStoreVersion = new GetLatestVersion().execute().get();
//            Log.i("Google play version:", googlePlayStoreVersion);

            float gpVersion = Float.parseFloat(googlePlayStoreVersion);
            float cVersion = Float.parseFloat(getCurrentVersion());

            gpVersion = Float.parseFloat(new DecimalFormat("##.#").format(gpVersion));
            cVersion = Float.parseFloat(new DecimalFormat("##.#").format(cVersion));

//            Log.i("changed local version:", "" + cVersion);
//            Log.i("changed store version:", "" + gpVersion);

            if (gpVersion != cVersion && gpVersion > cVersion) {
                alertUpdateInfo();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        getDeviceId(this);
//        getDeviceInfo();
    }


    public void openLoginActivity(View view) {
        Intent intent = new Intent(this, LoginPageAC.class);
        startActivity(intent);
    }

    //get local app version
    private String getCurrentVersion() {
        PackageManager pm = this.getPackageManager();
        PackageInfo pInfo = null;

        try {
            pInfo = pm.getPackageInfo(this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        String currentVersion = pInfo.versionName;

        return currentVersion;
    }

    public void getDeviceInfo() {
        StringBuffer infoBuffer = new StringBuffer();

        infoBuffer.append("-------------------------------------\n");
        infoBuffer.append("Model :" + Build.MODEL + "\n");//The end-user-visible name for the end product.
        infoBuffer.append("Device: " + Build.DEVICE + "\n");//The name of the industrial design.
        infoBuffer.append("Manufacturer: " + Build.MANUFACTURER + "\n");//The manufacturer of the product/hardware.
        infoBuffer.append("Board: " + Build.BOARD + "\n");//The name of the underlying board, like "goldfish".
        infoBuffer.append("Brand: " + Build.BRAND + "\n");//The consumer-visible brand with which the product/hardware will be associated, if any.
        infoBuffer.append("Serial: " + Build.SERIAL + "\n");
        infoBuffer.append("-------------------------------------\n");
    /* Android doc:
        This 'Serial' field was deprecated in API level O.
        Use getSerial() instead.
        A hardware serial number, if available.
        Alphanumeric only, case-insensitive. For apps targeting SDK higher than N_MR1 this field is set to UNKNOWN.
    */

//I just used AlertDialog to show device information
        AlertDialog.Builder dialog = new AlertDialog.Builder(StartupPageAC.this);
        dialog.setCancelable(true);
        dialog.setTitle("Device information:");
        dialog.setMessage(infoBuffer);//StringBuffer which we appended the device informations.
        dialog.show();
    }

    public static String getDeviceId(Context context) {
        Log.i("Android ID:", "reached getDevice ID");

        String androidId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);;
        Log.i("Android ID:", androidId);
        loginVar.deviceserialno = androidId;
//        Toast.makeText(context, "android_id= " + androidId, Toast.LENGTH_LONG).show();
        return androidId;
    }



    //get Google play store app version
    private class GetLatestVersion extends AsyncTask<String, String, String> {
        String storeVersion;;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                storeVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "&hl=en")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .select("div.hAyfc:nth-child(4) > span:nth-child(2) > div:nth-child(1) > span:nth-child(1)")
                        .first()
                        .ownText();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return storeVersion;

        }

    }

    private void alertUpdateInfo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Find new version. Click OK to update now.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                welcomeButton.setVisibility(View.INVISIBLE);
            }
        });
        AlertDialog alert = builder.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }

}
