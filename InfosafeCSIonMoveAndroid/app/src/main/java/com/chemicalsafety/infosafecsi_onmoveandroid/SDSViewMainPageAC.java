package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.previewVar;

public class SDSViewMainPageAC extends AppCompatActivity {

    TextView ghsvalue, hazvalue, precvalue, tivalue, psvalue;

    ImageView ghscImg1, ghscImg2, ghscImg3, ghscImg4, ghscImg5;

    ImageView tiImg, tisubImg1, tisubImg2;

    Button SDS, Preview, GHS, DG, FAID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdsview_main_page_ac);

        ghsvalue = findViewById(R.id.ghsvalue);
        hazvalue = findViewById(R.id.hazvalue);
        precvalue = findViewById(R.id.precvalue);
        tivalue = findViewById(R.id.tivalue);
        psvalue = findViewById(R.id.psvalue);

        ghscImg1 = findViewById(R.id.ghscImg1);
        ghscImg2 = findViewById(R.id.ghscImg2);
        ghscImg3 = findViewById(R.id.ghscImg3);
        ghscImg4 = findViewById(R.id.ghscImg4);
        ghscImg5 = findViewById(R.id.ghscImg5);

        setValues();

    }

    public void setValues() {

        ghsvalue.setText(previewVar.classification);
        hazvalue.setText(previewVar.hstate);
        precvalue.setText(previewVar.ps_general + previewVar.ps_response + previewVar.ps_prevention);
        psvalue.setText(previewVar.ps);

//        tivalue.setText(previewVar.);

    }
}
