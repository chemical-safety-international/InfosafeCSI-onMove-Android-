package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.loginVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.previewGHSVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.previewTIVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.searchItemList;
import com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf.csiWCFMethods;
import com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf.csiWCF_VM;

import java.lang.reflect.Field;

import static android.text.Html.fromHtml;

public class SDSViewMainPageAC extends AppCompatActivity {

    //GHS & preview textviews, images and buttons
    TextView ghstitle, haztitle, prectitle, tititle, pstitle;

    TextView ghsvalue, hazvalue, precvalue, tivalue, psvalue;

    ImageView ghscImg1, ghscImg2, ghscImg3, ghscImg4, ghscImg5;

    ImageView tiImg, tisubImg1, tisubImg2;

    Button sdsBtn, previewBtn, ghsBtn, dgBtn, faBtn;

    ProgressBar sdsprogressBar;

    //scrollviews
    ScrollView preghsSC, dgSC;

    //TI layout images, buttons and textviews
    Button tisroadBtn, tisseaBtn, tisairBtn;

    ImageView tisdgImg, tissubImg1, tissubImg2;

    TextView tisuntitle, tisdgtitle, tisrisktitle, tispgtitle, tispsntitle, tissymtitle, tisemstitle, tismptitle, tishctitle, tisepgtitle, tisinotitle, tispmtitle;

    TextView tisunv, tisdgv, tisriskv, tispgv, tispsnv, tissymv, tisemsv, tismpv, tishcv, tisepgv, tisinov, tispmv;

    int sdsBtnwidth;

//    private long mLastClickTime = 0;
    private boolean sdsget = false;
//    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdsview_main_page_ac);

        ghstitle = findViewById(R.id.ghstitle);
        haztitle = findViewById(R.id.haztitle);
        prectitle = findViewById(R.id.prectitle);
        tititle = findViewById(R.id.tititle);
        pstitle = findViewById(R.id.pstitle);


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
        tisubImg1 = findViewById(R.id.tisubImg1);
        tisubImg2 = findViewById(R.id.tisubImg2);

        sdsBtn = findViewById(R.id.sdsBtn);
        previewBtn = findViewById(R.id.prevBtn);
        ghsBtn = findViewById(R.id.ghsBtn);
        dgBtn = findViewById(R.id.dgBtn);
        faBtn = findViewById(R.id.faidBtn);

        sdsprogressBar = findViewById(R.id.sdsprogressBar);

        preghsSC = findViewById(R.id.PRE_GHSScrollView);
        dgSC = findViewById(R.id.TIScrollView);

        tisroadBtn = findViewById(R.id.roadBtn);
        tisseaBtn = findViewById(R.id.seaBtn);
        tisairBtn = findViewById(R.id.airBtn);

        tisdgImg = findViewById(R.id.tisdgImg);
        tissubImg1 = findViewById(R.id.tissubImg1);
        tissubImg2 = findViewById(R.id.tissubImg2);

        tisunv = findViewById(R.id.tisunv);
        tisdgv = findViewById(R.id.tisdgv);
        tisriskv = findViewById(R.id.tisriskv);
        tispgv = findViewById(R.id.tispgv);
        tispsnv = findViewById(R.id.tispsnv);
        tissymv = findViewById(R.id.tissymv);
        tisemsv = findViewById(R.id.tisemsv);
        tismpv = findViewById(R.id.tismpv);
        tishcv = findViewById(R.id.tishazv);
        tisepgv = findViewById(R.id.tisepgv);
        tisinov = findViewById(R.id.tisinov);
        tispmv = findViewById(R.id.tispmv);

        setValues();
        setBtnsValues();

//        setSDSProgress();

        setProgressValue(0);

//        sdsget = callViewSDS();

//        ThreadA threadA = new ThreadA();
//        threadA.go();
//
//        PrimeThread p = new PrimeThread(143);
//        p.start();

    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        sdsBtnwidth = sdsBtn.getWidth();
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
            if (!ghsimg1v.isEmpty()) {
                Class res1 = R.drawable.class;
                Field field1 = res1.getField(ghsimg1v);
                int id1 = field1.getInt(null);
                ghscImg1.setImageResource(id1);
            }


            if (!ghsimg2v.isEmpty()) {
                Class res2 = R.drawable.class;
                Field field2 = res2.getField(ghsimg2v);
                int id2 = field2.getInt(null);
                ghscImg2.setImageResource(id2);
            }

            if (!ghsimg3v.isEmpty()) {
                Class res3 = R.drawable.class;
                Field field3 = res3.getField(ghsimg3v);
                int id3 = field3.getInt(null);
                ghscImg3.setImageResource(id3);
            }

            if (!ghsimg4v.isEmpty()) {
                Class res4 = R.drawable.class;
                Field field4 = res4.getField(ghsimg4v);
                int id4 = field4.getInt(null);
                ghscImg4.setImageResource(id4);
            }
            if (!ghsimg5v.isEmpty()) {
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

            if (!previewTIVar.dgImg.isEmpty() && previewTIVar.dgImg.equals("dg")) {
                Class res1 = R.drawable.class;
                Field field1 = res1.getField(previewTIVar.dgImg);
                int id1 = field1.getInt(null);
                tiImg.setImageResource(id1);
            }

            if (!previewTIVar.subImg1.isEmpty() && previewTIVar.subImg1.equals("dg")) {
                Class res2 = R.drawable.class;
                Field field2 = res2.getField(previewTIVar.subImg1);
                int id2 = field2.getInt(null);
                tisubImg1.setImageResource(id2);
            }

            if (!previewTIVar.subImg2.isEmpty() && previewTIVar.subImg2.equals("dg")) {
                Class res3 = R.drawable.class;
                Field field3 = res3.getField(previewTIVar.subImg2);
                int id3 = field3.getInt(null);
                tisubImg2.setImageResource(id3);
            }

//            sdsBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//
//
//            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setBtnsValues() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dgSC.setVisibility(View.INVISIBLE);
                preghsSC.setVisibility(View.VISIBLE);

                sdsBtn.setEnabled(false);
                sdsprogressBar.setScaleY(12f);
//                sdsprogressBar.setProgressBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorLightBlack)));

                previewBtn.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                sdsBtn.setBackgroundColor(Color.TRANSPARENT);

//                sdsprogressBar.getLayoutParams().width = sdsBtnwidth;

                ghstitle.setVisibility(View.VISIBLE);
                ghscImg1.setVisibility(View.VISIBLE);
                ghscImg2.setVisibility(View.VISIBLE);
                ghscImg3.setVisibility(View.VISIBLE);
                ghscImg4.setVisibility(View.VISIBLE);
                ghscImg5.setVisibility(View.VISIBLE);
                ghsvalue.setVisibility(View.VISIBLE);

                haztitle.setVisibility(View.GONE);
                hazvalue.setVisibility(View.GONE);

                prectitle.setVisibility(View.GONE);
                precvalue.setVisibility(View.GONE);

                tititle.setVisibility(View.VISIBLE);
                tiImg.setVisibility(View.VISIBLE);
                tisubImg1.setVisibility(View.VISIBLE);
                tisubImg1.setVisibility(View.VISIBLE);
                tivalue.setVisibility(View.VISIBLE);

                pstitle.setVisibility(View.VISIBLE);
                psvalue.setVisibility(View.VISIBLE);

            }
        });

    }

    public void setProgressValue(final int progress) {
        sdsprogressBar.setProgress(progress);

        //thread is used to change the pb value
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (sdsprogressBar.getProgress() < 75) {

                    setProgressValue(progress + 5);
                }


                if (sdsprogressBar.getProgress() == 5) {
                    sdsget = callViewSDS();
                }
                if(sdsprogressBar.getProgress() == 75 && !sdsget) {
                    setProgressValue(progress);
                } else if (sdsget){
                    setProgressValue(100);
//                    sdsBtn.setEnabled(true);
//                    sdsprogressBar.setVisibility(View.INVISIBLE);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            sdsBtn.setEnabled(true);
                        }
                    });

                }
            }
        });
        thread.start();
    }

    public Boolean callViewSDS() {
        Log.i("View SDS", "CallViewSDS");

        String rtype = "1";
        int apptp = loginVar.apptype;

        csiWCF_VM wcf = new csiWCF_VM();

        if (wcf.ViewSDS(loginVar.clientid, loginVar.infosafeid, searchItemList.sdsno, apptp, rtype)) {
            sdsprogressBar.setProgress(100);


            return true;
        } else {
            return false;
        }
    }

    public void sdsBtnTapped(View v) {
        Log.i("View SDS", "Tapped sdsbtn" + sdsget);

        if (sdsget) {
            Intent intent = new Intent(this, ViewSDSPageAC.class);
            startActivity(intent);
        }

    }

    public void previewBtnTapped(View v) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dgSC.setVisibility(View.INVISIBLE);
                preghsSC.setVisibility(View.VISIBLE);

                ghsBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                dgBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                faBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                previewBtn.setBackgroundColor(getResources().getColor(R.color.colorOrange));

                ghstitle.setVisibility(View.VISIBLE);
                ghscImg1.setVisibility(View.VISIBLE);
                ghscImg2.setVisibility(View.VISIBLE);
                ghscImg3.setVisibility(View.VISIBLE);
                ghscImg4.setVisibility(View.VISIBLE);
                ghscImg5.setVisibility(View.VISIBLE);
                ghsvalue.setVisibility(View.VISIBLE);

                haztitle.setVisibility(View.GONE);
                hazvalue.setVisibility(View.GONE);

                prectitle.setVisibility(View.GONE);
                precvalue.setVisibility(View.GONE);

                tititle.setVisibility(View.VISIBLE);
                tiImg.setVisibility(View.VISIBLE);
                tisubImg1.setVisibility(View.VISIBLE);
                tisubImg1.setVisibility(View.VISIBLE);
                tivalue.setVisibility(View.VISIBLE);

                pstitle.setVisibility(View.VISIBLE);
                psvalue.setVisibility(View.VISIBLE);
            }
        });
    }

    public void ghsBtnTapped(View v) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dgSC.setVisibility(View.INVISIBLE);
                preghsSC.setVisibility(View.VISIBLE);

                previewBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                dgBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                faBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                ghsBtn.setBackgroundColor(getResources().getColor(R.color.colorOrange));

                ghstitle.setVisibility(View.VISIBLE);
                ghscImg1.setVisibility(View.VISIBLE);
                ghscImg2.setVisibility(View.VISIBLE);
                ghscImg3.setVisibility(View.VISIBLE);
                ghscImg4.setVisibility(View.VISIBLE);
                ghscImg5.setVisibility(View.VISIBLE);
                ghsvalue.setVisibility(View.VISIBLE);

                haztitle.setVisibility(View.VISIBLE);
                hazvalue.setVisibility(View.VISIBLE);

                prectitle.setVisibility(View.VISIBLE);
                precvalue.setVisibility(View.VISIBLE);

                tititle.setVisibility(View.GONE);
                tiImg.setVisibility(View.GONE);
                tisubImg1.setVisibility(View.GONE);
                tisubImg2.setVisibility(View.GONE);
                tivalue.setVisibility(View.GONE);
                pstitle.setVisibility(View.GONE);
                psvalue.setVisibility(View.GONE);

            }
        });
    }

    public void dgBtnTapped(View v) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dgSC.setVisibility(View.VISIBLE);
                preghsSC.setVisibility(View.INVISIBLE);
                setTIPageBtns();

                previewBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                dgBtn.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                faBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                ghsBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
            }
        });
    }

    public void setTIPageBtns() {
        setTIPageRoadBtn();
    }

    public void setTIPageRoadBtn() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                tisroadBtn.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                tisseaBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                tisairBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));

                dgSC.setBackgroundResource(R.drawable.tis_roadbg);
            }
        });


    }

    public void setTIPageSeaBtn() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                tisroadBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                tisseaBtn.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                tisairBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));

                dgSC.setBackgroundResource(R.drawable.tis_seabg);
            }
        });
    }

    public void setTIPageAirBtn() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                tisroadBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                tisseaBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                tisairBtn.setBackgroundColor(getResources().getColor(R.color.colorOrange));

                dgSC.setBackgroundResource(R.drawable.tis_airbg);
            }
        });
    }

    public void roadBtnTapped(View v) {
        setTIPageRoadBtn();
    }

    public void seaBtnTapped(View v) {
        setTIPageSeaBtn();
    }

    public void airBtnTapped(View v) {
        setTIPageAirBtn();
    }
}
