package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.webkit.WebView;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.sdspdfVar;

import java.io.File;
import java.io.FileOutputStream;

public class ViewSDSPageAC extends AppCompatActivity {

    WebView pdfview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sdspage_ac);

        pdfview = findViewById(R.id.pdfview);

        setValue();
    }

    public void setValue() {

        pdfview.getSettings().setJavaScriptEnabled(true);
        pdfview.getSettings().setLoadWithOverviewMode(true);
        pdfview.getSettings().setUseWideViewPort(true);
//        System.out.println(sdspdfVar.sdspdf);

//        pdfview.loadDataWithBaseURL("", sdspdfVar.sdspdf, "application/pdf", "UTF-8", "");


        try {
            if(sdspdfVar.sdspdf != null) {
//                final File sdsPath = new File(DOWNLOADS_FOLDER + "SDS" + ".pdf");

                File sdsfile = new File("./sdsfile.pdf");
                FileOutputStream fos = new FileOutputStream(sdsfile);

                byte[] decode = Base64.decode(sdspdfVar.sdspdf, 0);

                fos.write(decode);

                pdfview.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + sdsfile);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
