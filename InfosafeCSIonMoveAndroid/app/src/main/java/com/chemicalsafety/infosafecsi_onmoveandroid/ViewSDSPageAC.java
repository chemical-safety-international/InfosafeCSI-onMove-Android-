package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Base64;


import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.sdspdfVar;
import com.github.barteksc.pdfviewer.PDFView;



public class ViewSDSPageAC extends AppCompatActivity {


    PDFView pdfviewer;
    byte[] decodedString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sdspage_ac);


        pdfviewer = findViewById(R.id.pdfviewer);

        setValue();
    }

    public void setValue() {

        try {
            if(sdspdfVar.sdspdf != null) {


                decodedString = Base64.decode(sdspdfVar.sdspdf.toString(), Base64.DEFAULT);

                pdfviewer.fromBytes(decodedString).load();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
