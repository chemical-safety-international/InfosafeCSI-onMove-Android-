package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;

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

    TextView ghsvalue, hazvalue, precvalue, tivalue, psvalue;

    ImageView ghscImg1, ghscImg2, ghscImg3, ghscImg4, ghscImg5;

    ImageView tiImg, tisubImg1, tisubImg2;

    Button sdsBtn, previewBtn, ghsBtn, dgBtn, faBtn;

    ProgressBar sdsprogressBar;

//    private long mLastClickTime = 0;
    private boolean sdsget = false;
//    int progress = 0;

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
        tisubImg1 = findViewById(R.id.tisubImg1);
        tisubImg2 = findViewById(R.id.tisubImg2);

        sdsBtn = findViewById(R.id.sdsBtn);
        previewBtn = findViewById(R.id.prevBtn);

        sdsprogressBar = findViewById(R.id.sdsprogressBar);

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
                sdsBtn.setEnabled(false);
                sdsprogressBar.setScaleY(10f);
//                sdsprogressBar.setProgressBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorLightBlack)));

                previewBtn.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                sdsBtn.setBackgroundColor(Color.TRANSPARENT);

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


                if (sdsprogressBar.getProgress() == 10) {
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

//    public void setSDSProgress() {
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//              try {
//
//              } catch (Exception e){
//                  e.printStackTrace();
//              }
//            }
//        });
//    }


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

//    class PrimeThread extends Thread {
//        long minPrime;
//        PrimeThread(long minPrime) {
//            this.minPrime = minPrime;
//        }
//
//        public void run() {
////            Log.d("reach", "2");
//            sdsget = callViewSDS();
//        }
//    }

//    public class ThreadA {
//        public void go() {
//            Log.d("reach", "1");
//            PrimeThread p = new PrimeThread(143);
//            p.start();
//            synchronized (p) {
//                try {
//                    p.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
////                Log.d("reach", "3");
//
//            }
//        }
//
//    }
}
