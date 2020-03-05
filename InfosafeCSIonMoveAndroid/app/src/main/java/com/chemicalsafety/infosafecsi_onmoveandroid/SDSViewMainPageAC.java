package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.loginVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.previewFAIDVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.previewGHSVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.previewTIVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.searchItemList;
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
    ScrollView preghsSC, tiSC, faSC;

    //TI layout images, buttons and textviews
    Button tisroadBtn, tisseaBtn, tisairBtn;

    ImageView tisdgImg, tissubImg1, tissubImg2;

    TextView tisuntitle, tisdgtitle, tisrisktitle, tispgtitle, tispsntitle, tissymtitle, tisemstitle, tismptitle, tishctitle, tisepgtitle, tisinotitle, tispmtitle;

    TextView tisunv, tisdgv, tisriskv, tispgv, tispsnv, tissymv, tisemsv, tismpv, tishcv, tisepgv, tisinov, tispmv;

    //First AID layout textviews
    TextView inhtitle, ingtitle, skintitle, eyetitle, faftitle, atdtitle;

    TextView fainhv, faingv, faskinv, faeyev, fafafv, faatdv;

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
        tiSC = findViewById(R.id.TIScrollView);
        faSC = findViewById(R.id.FAIDScrollView);

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

        fainhv = findViewById(R.id.inhv);
        faingv = findViewById(R.id.ingv);
        faskinv = findViewById(R.id.skinv);
        faeyev = findViewById(R.id.eyev);
        fafafv = findViewById(R.id.fafv);
        faatdv = findViewById(R.id.atdv);

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
        //hide other layout
        tiSC.setVisibility(View.INVISIBLE);
        faSC.setVisibility(View.INVISIBLE);

        //default the imgs
        ghscImg1.setImageDrawable(null);
        ghscImg2.setImageDrawable(null);
        ghscImg3.setImageDrawable(null);
        ghscImg4.setImageDrawable(null);
        ghscImg5.setImageDrawable(null);

        tiImg.setImageDrawable(null);
        tisubImg1.setImageDrawable(null);
        tisubImg2.setImageDrawable(null);

        //set GHS textviews' value
        ghsvalue.setText(previewGHSVar.classification);
        hazvalue.setText(previewGHSVar.hstate);
        psvalue.setText(previewGHSVar.ps);
//        psvalue.setText("00000");

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
//            Log.i("DG String", previewTIVar.dgImg);

            if (previewTIVar.dgImg != null && !previewTIVar.dgImg.isEmpty()) {
                Class res1 = R.drawable.class;
                Field field1 = res1.getField(previewTIVar.dgImg);
                int id1 = field1.getInt(null);
                tiImg.setImageResource(id1);
            }

            if (!previewTIVar.subImg1.isEmpty() && !previewTIVar.subImg1.equals("dg")) {
                Class res2 = R.drawable.class;
                Field field2 = res2.getField(previewTIVar.subImg1);
                int id2 = field2.getInt(null);
                tisubImg1.setImageResource(id2);
            }

            if (!previewTIVar.subImg2.isEmpty() && !previewTIVar.subImg2.equals("dg")) {
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
                tiSC.setVisibility(View.INVISIBLE);
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
//        Log.i("View SDS", "CallViewSDS");

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
//        Log.i("View SDS", "Tapped sdsbtn" + sdsget);

        if (sdsget) {
            Intent intent = new Intent(this, ViewSDSPageAC.class);
            startActivity(intent);
        }

    }

    public void previewBtnTapped(View v) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tiSC.setVisibility(View.INVISIBLE);
                preghsSC.setVisibility(View.VISIBLE);
                faSC.setVisibility(View.INVISIBLE);

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
                tiSC.setVisibility(View.INVISIBLE);
                preghsSC.setVisibility(View.VISIBLE);
                faSC.setVisibility(View.INVISIBLE);

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
                tiSC.setVisibility(View.VISIBLE);
                preghsSC.setVisibility(View.INVISIBLE);
                faSC.setVisibility(View.INVISIBLE);
                setTIPageBtns();

                previewBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                dgBtn.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                faBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                ghsBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
            }
        });
    }

    public void setTIPageBtns() {
        if (previewTIVar.imdg_unno.isEmpty()) {
            tisseaBtn.setVisibility(View.INVISIBLE);
        } else {
            tisseaBtn.setVisibility(View.VISIBLE);
        }
        if(previewTIVar.iata_unno.isEmpty()) {
            tisairBtn.setVisibility(View.INVISIBLE);
        } else {
            tisairBtn.setVisibility(View.VISIBLE);
        }
        setTIPageRoadBtn();
    }

    public void setTIPageRoadBtn() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                tisroadBtn.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                tisseaBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                tisairBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));

                tiSC.setBackgroundResource(R.drawable.tis_roadbg);
            }
        });

        tisunv.setText(previewTIVar.road_unno);
        tisdgv.setText(previewTIVar.road_dgclass);
        tisriskv.setText(previewTIVar.road_subrisks);
        tispgv.setText(previewTIVar.road_packgrp);
        tispsnv.setText(previewTIVar.road_psn);

        tishcv.setText(previewTIVar.road_hazchem);
        tisepgv.setText(previewTIVar.road_epg);
        tisinov.setText(previewTIVar.road_ierg);
        tispmv.setText(previewTIVar.road_packmethod);

        tissymv.setText("");
        tisemsv.setText("");
        tismpv.setText("");

        setTIimgs(previewTIVar.road_dgclass, previewTIVar.road_subrisks);

        try {
            if (previewTIVar.tisdgImg != null && !previewTIVar.tisdgImg.isEmpty()) {
                Class res1 = R.drawable.class;
                Field field1 = res1.getField(previewTIVar.tisdgImg);
                int id1 = field1.getInt(null);
                tisdgImg.setImageResource(id1);
            }

            if (!previewTIVar.tissubImg1.isEmpty() && previewTIVar.tissubImg1.equals("dg")) {
                Class res2 = R.drawable.class;
                Field field2 = res2.getField(previewTIVar.tissubImg1);
                int id2 = field2.getInt(null);
                tissubImg1.setImageResource(id2);
            }

            if (!previewTIVar.tissubImg2.isEmpty() && previewTIVar.tissubImg2.equals("dg")) {
                Class res3 = R.drawable.class;
                Field field3 = res3.getField(previewTIVar.tissubImg2);
                int id3 = field3.getInt(null);
                tissubImg2.setImageResource(id3);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setTIPageSeaBtn() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                tisroadBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                tisseaBtn.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                tisairBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));

                tiSC.setBackgroundResource(R.drawable.tis_seabg);
            }
        });

        tisunv.setText(previewTIVar.imdg_unno);
        tisdgv.setText(previewTIVar.imdg_dgclass);
        tisriskv.setText(previewTIVar.imdg_subrisks);
        tispgv.setText(previewTIVar.imdg_packgrp);
        tispsnv.setText(previewTIVar.imdg_psn);

        tishcv.setText("");
        tisepgv.setText("");
        tisinov.setText("");
        tispmv.setText("");

        tissymv.setText("");
        tisemsv.setText(previewTIVar.imdg_ems);
        tismpv.setText(previewTIVar.imdg_mp);

        setTIimgs(previewTIVar.imdg_dgclass, previewTIVar.imdg_subrisks);

        try {
            if (previewTIVar.tisdgImg != null && !previewTIVar.tisdgImg.isEmpty()) {
                Class res1 = R.drawable.class;
                Field field1 = res1.getField(previewTIVar.tisdgImg);
                int id1 = field1.getInt(null);
                tisdgImg.setImageResource(id1);
            }

            if (!previewTIVar.tissubImg1.isEmpty() && previewTIVar.tissubImg1.equals("dg")) {
                Class res2 = R.drawable.class;
                Field field2 = res2.getField(previewTIVar.tissubImg1);
                int id2 = field2.getInt(null);
                tissubImg1.setImageResource(id2);
            }

            if (!previewTIVar.tissubImg2.isEmpty() && previewTIVar.tissubImg2.equals("dg")) {
                Class res3 = R.drawable.class;
                Field field3 = res3.getField(previewTIVar.tissubImg2);
                int id3 = field3.getInt(null);
                tissubImg2.setImageResource(id3);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTIPageAirBtn() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                tisroadBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                tisseaBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                tisairBtn.setBackgroundColor(getResources().getColor(R.color.colorOrange));

                tiSC.setBackgroundResource(R.drawable.tis_airbg);
            }
        });

        tisunv.setText(previewTIVar.iata_unno);
        tisdgv.setText(previewTIVar.iata_dgclass);
        tisriskv.setText(previewTIVar.iata_subrisks);
        tispgv.setText(previewTIVar.iata_packgrp);
        tispsnv.setText(previewTIVar.iata_psn);

        tishcv.setText("");
        tisepgv.setText("");
        tisinov.setText("");
        tispmv.setText("");

        tissymv.setText(previewTIVar.iata_symbol);
        tisemsv.setText("");
        tismpv.setText("");

        setTIimgs(previewTIVar.iata_dgclass, previewTIVar.iata_subrisks);

        try {
            if (previewTIVar.tisdgImg != null && !previewTIVar.tisdgImg.isEmpty()) {
                Class res1 = R.drawable.class;
                Field field1 = res1.getField(previewTIVar.tisdgImg);
                int id1 = field1.getInt(null);
                tisdgImg.setImageResource(id1);
            }

            if (!previewTIVar.tissubImg1.isEmpty() && previewTIVar.tissubImg1.equals("dg")) {
                Class res2 = R.drawable.class;
                Field field2 = res2.getField(previewTIVar.tissubImg1);
                int id2 = field2.getInt(null);
                tissubImg1.setImageResource(id2);
            }

            if (!previewTIVar.tissubImg2.isEmpty() && previewTIVar.tissubImg2.equals("dg")) {
                Class res3 = R.drawable.class;
                Field field3 = res3.getField(previewTIVar.tissubImg2);
                int id3 = field3.getInt(null);
                tissubImg2.setImageResource(id3);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void setTIimgs(String dgv, String subv) {

        tisdgImg.setImageDrawable(null);
        tissubImg1.setImageDrawable(null);
        tissubImg2.setImageDrawable(null);



        String dgimgs = dgv;

//        System.out.println("Before");
//        Log.i("values for road", dgimgs);

        if (dgimgs.contains("None")) {
            previewTIVar.tisdgImg = "";
        } else {
            if (dgimgs.contains(".")) {
                dgimgs = dgimgs.replace(".", "");
            }
            previewTIVar.tisdgImg = "dg" + dgimgs;
        }
//        System.out.println("after");
//        Log.i("values 2:", previewTIVar.tisdgImg);

//            Log.i("SUBIMGS", previewTIVar.road_subrisks);
//            System.out.println(previewTIVar.road_subrisks);
        if (subv.contains("None")) {
            previewTIVar.tissubImg1 = "";
            previewTIVar.tissubImg2 = "";
        } else {
            String[] subImgsArray = subv.split(" ");

            if (subImgsArray.length == 2) {
                previewTIVar.tissubImg1 = subImgsArray[0];
                previewTIVar.tissubImg1 = previewTIVar.tissubImg1.replace(".", "");
                previewTIVar.tissubImg1 = "dg" + previewTIVar.tissubImg1;

                previewTIVar.tissubImg2 = subImgsArray[1];
                previewTIVar.tissubImg2 = previewTIVar.tissubImg2.replace(".", "");
                previewTIVar.tissubImg2 = "dg" + previewTIVar.tissubImg2;

            } else if (subImgsArray.length == 1 && !subImgsArray[0].equals("")) {
                previewTIVar.tissubImg1 = subImgsArray[0];
                previewTIVar.tissubImg1 = previewTIVar.tissubImg1.replace(".", "");
                previewTIVar.tissubImg1 = "dg" + previewTIVar.tissubImg1;

                previewTIVar.tissubImg2 = "";
            } else {
                previewTIVar.tissubImg1 = "";
                previewTIVar.tissubImg2 = "";
            }
        }


    }

    public void faBtnTapped(View v) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tiSC.setVisibility(View.INVISIBLE);
                preghsSC.setVisibility(View.INVISIBLE);
                faSC.setVisibility(View.VISIBLE);

                previewBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                dgBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                faBtn.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                ghsBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));

                setFAIDValues();
            }
        });
    }

    public void setFAIDValues() {

        fainhv.setText(previewFAIDVar.inhalation);
        faingv.setText(previewFAIDVar.ingestion);
        faskinv.setText(previewFAIDVar.skin);
        faeyev.setText(previewFAIDVar.eye);
        fafafv.setText(previewFAIDVar.fafacilities);
        faatdv.setText(previewFAIDVar.advdoctor);
    }
}
