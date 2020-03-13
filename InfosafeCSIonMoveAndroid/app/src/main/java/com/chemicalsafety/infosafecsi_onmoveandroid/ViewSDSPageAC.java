package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.sdspdfVar;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;


public class ViewSDSPageAC extends AppCompatActivity {


    PDFView pdfviewer;
    byte[] decodedString;
    Button shareBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sdspage_ac);


        pdfviewer = findViewById(R.id.pdfviewer);
        shareBtn = findViewById(R.id.shareBtn);

        setValue();
        checkPermission();
    }

    public void setValue() {

        try {
            if(sdspdfVar.sdspdf != null) {


                decodedString = Base64.decode(sdspdfVar.sdspdf, Base64.DEFAULT);

                pdfviewer.fromBytes(decodedString).load();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void shareBtnTapped(View v) {

        System.out.println("called share");
        try {
            //convert base64 string to pdf file
            final File filePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "SDS.pdf");
            byte[] pdfAsBytes = Base64.decode(sdspdfVar.sdspdf, Base64.DEFAULT);
            FileOutputStream os;
            os = new FileOutputStream(filePath, false);
            os.write(pdfAsBytes);
            os.flush();
            os.close();


            //get the pdf file
            Uri uri = FileProvider.getUriForFile(getBaseContext(), getApplicationContext().getPackageName() + ".provider", filePath);

            //build and call share button
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("application/pdf");
            sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);

            // create chooser
            Intent chooser = Intent.createChooser(sharingIntent, "Share file");

            //check the permission
            List<ResolveInfo> resInfoList = this.getPackageManager().queryIntentActivities(chooser, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo resolveInfo: resInfoList) {
                String packageName = resolveInfo.activityInfo.packageName;
                this.grantUriPermission(packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }

            //call the share
            startActivity(chooser);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //check the storage permission
    public void checkPermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v("TRUE", "Permission is granted");
            } else {
                Log.v("FALSE", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        } else {
            Log.v("TRUE", "PERMISSION IS GRANTED");
        }

    }


}
