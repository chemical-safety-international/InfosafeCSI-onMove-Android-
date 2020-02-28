package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.previewGHSVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.previewTIVar;

import java.lang.reflect.Field;

import static android.text.Html.fromHtml;

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

        tiImg = findViewById(R.id.tiImg);

        setValues();

    }

    public void setValues() {

        //set GHS textviews' value
        ghsvalue.setText(previewGHSVar.classification);
        hazvalue.setText(previewGHSVar.hstate);
        psvalue.setText(previewGHSVar.ps);

        String ghsgentitle = getString(R.string.ps_general);
        String ghspretitle = getString(R.string.ps_prevention);
        String ghsrestitle = getString(R.string.ps_response);
        String ghsstotitle = getString(R.string.ps_storage);
        String ghsdistitle = getString(R.string.ps_disposal);

        precvalue.setText(fromHtml(ghsgentitle + "<br/>" + previewGHSVar.ps_general + "<br/><br/>" + ghsrestitle + "<br/>" + previewGHSVar.ps_response + "<br/><br/>" + ghspretitle + "<br/>" + previewGHSVar.ps_prevention + "<br/><br/>" + ghsstotitle + "<br/>" + previewGHSVar.ps_storage + "<br/><br/>" + ghsdistitle + "<br/>" + previewGHSVar.ps_disposal));


        //set ghs images' value

        previewGHSVar ghsItems = previewGHSVar.ghscImgList.get(0);
        String ghsimg1v = ghsItems.getGhscimg1();
        String ghsimg2v = ghsItems.getGhscimg2();
        String ghsimg3v = ghsItems.getGhscimg3();
        String ghsimg4v = ghsItems.getGhscimg4();
        String ghsimg5v = ghsItems.getGhscimg5();

        try {

            //match images and set into imageviews
            if (!ghsimg1v.isEmpty() && ghsimg1v != null) {
                Class res1 = R.drawable.class;
                Field field1 = res1.getField(ghsimg1v);
                int id1 = field1.getInt(null);
                ghscImg1.setImageResource(id1);
            }


            if (!ghsimg2v.isEmpty() && ghsimg2v != null) {
                Class res2 = R.drawable.class;
                Field field2 = res2.getField(ghsimg2v);
                int id2 = field2.getInt(null);
                ghscImg2.setImageResource(id2);
            }

            if (!ghsimg3v.isEmpty() && ghsimg3v != null) {
                Class res3 = R.drawable.class;
                Field field3 = res3.getField(ghsimg3v);
                int id3 = field3.getInt(null);
                ghscImg3.setImageResource(id3);
            }

            if (!ghsimg4v.isEmpty() && ghsimg4v != null) {
                Class res4 = R.drawable.class;
                Field field4 = res4.getField(ghsimg4v);
                int id4 = field4.getInt(null);
                ghscImg4.setImageResource(id4);
            }
            if (!ghsimg5v.isEmpty() && ghsimg5v != null) {
                Class res5 = R.drawable.class;
                Field field5 = res5.getField(ghsimg5v);
                int id5 = field5.getInt(null);
                ghscImg5.setImageResource(id5);
            }

            //set TI textviews' value
            String unnotitle = getString(R.string.ti_unno);
            String dgtitle = getString(R.string.ti_dgclass);
            String haztitle = getString(R.string.ti_haz);
            String pgtitle = getString(R.string.ti_packgrp);
            String psntitle = getString(R.string.ti_psn);

            tivalue.setText(fromHtml(unnotitle + "<br/>" + previewTIVar.road_unno + "<br/><br/>" + dgtitle + "<br/>" + previewTIVar.road_dgclass + "<br/><br/>" + haztitle + "<br/>" + previewTIVar.road_hazchem + "<br/><br/>" + pgtitle + "<br/>" + previewTIVar.road_packgrp + "<br/><br/>" + psntitle + "<br/>" + previewTIVar.road_psn));

            if (!previewTIVar.dgImg.isEmpty() && previewTIVar.dgImg != null) {
                Class res1 = R.drawable.class;
                Field field1 = res1.getField(previewTIVar.dgImg);
                int id5 = field1.getInt(null);
                tiImg.setImageResource(id5);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
