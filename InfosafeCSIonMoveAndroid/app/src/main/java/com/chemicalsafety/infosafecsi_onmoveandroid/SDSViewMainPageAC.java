package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    TextView toolbarTitle;

    //scroll to view more content
    TextView scrollText;
    ImageView scrollImage;
    LinearLayout scrollContent1, scrollContent2, scrollContent3;
    volatile Boolean status1 = false;
    Boolean status2 = false, status3 = false;
    Boolean sta1 = true, sta2 = true, sta3 = true;
    int once = 0;

    int sdsBtnwidth;

//    private long mLastClickTime = 0;
    private boolean sdsget = false;
//    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdsview_main_page_ac);

        //connect ghs titles
        ghstitle = findViewById(R.id.ghstitle);
        haztitle = findViewById(R.id.haztitle);
        prectitle = findViewById(R.id.prectitle);
        tititle = findViewById(R.id.tititle);
        pstitle = findViewById(R.id.pstitle);

        //connect ghs values
        ghsvalue = findViewById(R.id.ghsvalue);
        hazvalue = findViewById(R.id.hazvalue);
        precvalue = findViewById(R.id.precvalue);
        tivalue = findViewById(R.id.tivalue);
        psvalue = findViewById(R.id.psvalue);

        //connect ghs images
        ghscImg1 = findViewById(R.id.ghscImg1);
        ghscImg2 = findViewById(R.id.ghscImg2);
        ghscImg3 = findViewById(R.id.ghscImg3);
        ghscImg4 = findViewById(R.id.ghscImg4);
        ghscImg5 = findViewById(R.id.ghscImg5);

        //connect preview ti images
        tiImg = findViewById(R.id.tiImg);
        tisubImg1 = findViewById(R.id.tisubImg1);
        tisubImg2 = findViewById(R.id.tisubImg2);

        //connect top tool buttons
        sdsBtn = findViewById(R.id.sdsBtn);
        previewBtn = findViewById(R.id.prevBtn);
        ghsBtn = findViewById(R.id.ghsBtn);
        dgBtn = findViewById(R.id.dgBtn);
        faBtn = findViewById(R.id.faidBtn);

        //connect scrollviews & sds progress bar
        sdsprogressBar = findViewById(R.id.sdsprogressBar);

        preghsSC = findViewById(R.id.PRE_GHSScrollView);
        tiSC = findViewById(R.id.TIScrollView);
        faSC = findViewById(R.id.FAIDScrollView);

        //connect ti buttons
        tisroadBtn = findViewById(R.id.roadBtn);
        tisseaBtn = findViewById(R.id.seaBtn);
        tisairBtn = findViewById(R.id.airBtn);

        //connect ti titles
        tisuntitle = findViewById(R.id.tisunno);
        tisdgtitle = findViewById(R.id.tisdg);
        tisrisktitle = findViewById(R.id.tisrisk);
        tispsntitle = findViewById(R.id.tispsntitle);
        tissymtitle = findViewById(R.id.tissymtitle);
        tisemstitle = findViewById(R.id.tisemstitle);
        tismptitle = findViewById(R.id.tismptitle);
        tishctitle = findViewById(R.id.tishaztitle);
        tisepgtitle = findViewById(R.id.tisepgtitle);
        tisinotitle = findViewById(R.id.tisinotitle);
        tispmtitle = findViewById(R.id.tispmtitle);

        //connect ti images
        tisdgImg = findViewById(R.id.tisdgImg);
        tissubImg1 = findViewById(R.id.tissubImg1);
        tissubImg2 = findViewById(R.id.tissubImg2);

        //connect ti values
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

        //connect fa titles
        inhtitle = findViewById(R.id.inhtitle);
        ingtitle = findViewById(R.id.ingtitle);
        skintitle = findViewById(R.id.skintitle);
        eyetitle = findViewById(R.id.eyetitle);
        faftitle = findViewById(R.id.faftitle);
        atdtitle = findViewById(R.id.atdtitle);

        //connect fa values
        fainhv = findViewById(R.id.inhv);
        faingv = findViewById(R.id.ingv);
        faskinv = findViewById(R.id.skinv);
        faeyev = findViewById(R.id.eyev);
        fafafv = findViewById(R.id.fafv);
        faatdv = findViewById(R.id.atdv);

        //connect toolbar textview
        toolbarTitle = findViewById(R.id.sdsmaintoolbartitle);

        //connect scroll to view more content
        scrollText = findViewById(R.id.viewMoreText);
        scrollImage = findViewById(R.id.viewMoreImg);
        scrollContent1 = findViewById(R.id.viewMoreContent1);
        scrollContent2 = findViewById(R.id.viewMoreContent2);
        scrollContent3 = findViewById(R.id.viewMoreContent3);



        setValues();
        setBtnsValues();

//        progressBarSetup();
//        setSDSProgress();
//        previewBtn.performClick();
        setProgressValue(0);

//        sdsget = callViewSDS();

//        ThreadA threadA = new ThreadA();
//        threadA.go();
//
//        PrimeThread p = new PrimeThread(143);
//        p.start();


//        scrollableCheck1(preghsSC);
//        scrollableCheck2(tiSC);
//        scrollableCheck3(faSC);

//        Log.i("scrollablecheck1;    ", status1.toString());
//        Log.i("scrollablecheck2;    ", status2.toString());
//        Log.i("scrollablecheck3;    ", status3.toString());


//        scrollContent2.setVisibility(View.GONE);
//        scrollContent3.setVisibility(View.GONE);


    }

//    public void progressBarSetup() {
//        sdsprogressBar.getIndeterminateDrawable().setColorFilter(0b11111111111111110000000000000000, PorterDuff.Mode.MULTIPLY);
//    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        sdsBtnwidth = sdsBtn.getWidth();
    }

    public void setValues() {
        //hide other layout
        tiSC.setVisibility(View.INVISIBLE);
        faSC.setVisibility(View.INVISIBLE);

        //set toolbar title value
        toolbarTitle.setText("PREVIEW");

        //default the imgs
        ghscImg1.setImageDrawable(null);
        ghscImg2.setImageDrawable(null);
        ghscImg3.setImageDrawable(null);
        ghscImg4.setImageDrawable(null);
        ghscImg5.setImageDrawable(null);

        tiImg.setImageDrawable(null);
        tisubImg1.setImageDrawable(null);
        tisubImg2.setImageDrawable(null);

        //init the constrainst set
        ConstraintSet set = new ConstraintSet();
        ConstraintLayout layout;

        //set GHS textviews' value
        if(previewGHSVar.formatcode.equals("OF") || previewGHSVar.formatcode.equals("0A")) {
            ghstitle.setText("GHS CLASSIFICATION");
        } else {
            ghstitle.setText("CLASSIFICATION");
        }

        //build GHS classfication
        if(!previewGHSVar.classification.isEmpty()) {
            ghsvalue.setText(previewGHSVar.classification);
            ghsvalue.setVisibility(View.VISIBLE);
            ghstitle.setVisibility(View.VISIBLE);
        } else {
            ghstitle.setVisibility(View.GONE);
            ghsvalue.setVisibility(View.GONE);
            ghsvalue.setText("");
        }


        //build hazard statement
        if (!previewGHSVar.hstate.isEmpty()) {

            hazvalue.setVisibility(View.VISIBLE);
            haztitle.setVisibility(View.VISIBLE);
//        Log.i("haz value", previewGHSVar.hstate);
            hazvalue.setText(previewGHSVar.hstate);
        } else {

            haztitle.setVisibility(View.GONE);
            hazvalue.setVisibility(View.GONE);
            hazvalue.setText("");
        }


        psvalue.setText(previewGHSVar.ps);


        // build precautionary statement
        String space = "<br/>";
        String dspace = "<br/><br/>";
        String comb = "";



        String ghsgentitle = getString(R.string.ps_general) + space;
        String ghspretitle = getString(R.string.ps_prevention) + space;
        String ghsrestitle = getString(R.string.ps_response) + space;
        String ghsstotitle = getString(R.string.ps_storage) + space;
        String ghsdistitle = getString(R.string.ps_disposal) + space;

        String genString = fromHtml(ghsgentitle) + previewGHSVar.ps_general;

        String resString = fromHtml(ghsrestitle) + previewGHSVar.ps_response;
//        String resString = "Response:/n psdfjsdfkjsnfkjnsdf sdjfoisdnfosndofnso sjfiodsjfods  sijodfjosidf/n/n";
//        SpannableStringBuilder sb = new SpannableStringBuilder(resString);
//        final StyleSpan bss = new StyleSpan(Typeface.BOLD);
//        sb.setSpan(bss, 0, 20, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        String preString = fromHtml(ghspretitle) + previewGHSVar.ps_prevention;
        String stoString = fromHtml(ghsstotitle) + previewGHSVar.ps_storage;
        String disString = fromHtml(ghsdistitle) + previewGHSVar.ps_disposal;

        if (!previewGHSVar.ps_general.isEmpty()) {
            comb = genString + fromHtml(dspace);
        }

        if(!previewGHSVar.ps_response.isEmpty()) {
            comb = comb + resString + fromHtml(dspace);
        }

        if(!previewGHSVar.ps_prevention.isEmpty()) {
            comb = comb + preString + fromHtml(dspace);
        }

        if(!previewGHSVar.ps_storage.isEmpty()) {
            comb = comb + stoString + fromHtml(dspace);
        }

        if(!previewGHSVar.ps_disposal.isEmpty()) {
            comb = comb + disString;
        }



        if (!comb.isEmpty()) {
            prectitle.setVisibility(View.VISIBLE);
            precvalue.setVisibility(View.VISIBLE);
            precvalue.setText(comb);
        } else {
            prectitle.setVisibility(View.GONE);
            precvalue.setVisibility((View.GONE));
            precvalue.setText("");
        }

        //precvalue.setText(fromHtml(ghsgentitle) + previewGHSVar.ps_general + fromHtml(dspace + ghsrestitle) + previewGHSVar.ps_response + fromHtml(dspace + ghspretitle) + previewGHSVar.ps_prevention + fromHtml(dspace + ghsstotitle) + previewGHSVar.ps_storage + fromHtml(dspace + ghsdistitle) + previewGHSVar.ps_disposal);

        //precvalue.setText(fromHtml(ghsgentitle + previewGHSVar.ps_general + dspace + ghsrestitle + previewGHSVar.ps_response + dspace + ghspretitle + previewGHSVar.ps_prevention + dspace + ghsstotitle + previewGHSVar.ps_storage + dspace + ghsdistitle + previewGHSVar.ps_disposal));


        //get ghs images' value
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
                ghscImg1.setVisibility(View.VISIBLE);
            } else {
                ghscImg1.setVisibility(View.GONE);
            }

            if (!ghsimg2v.isEmpty()) {
                Class res2 = R.drawable.class;
                Field field2 = res2.getField(ghsimg2v);
                int id2 = field2.getInt(null);
                ghscImg2.setImageResource(id2);
                ghscImg2.setVisibility(View.VISIBLE);
            } else {
                ghscImg2.setVisibility(View.GONE);
            }

            if (!ghsimg3v.isEmpty()) {
                Class res3 = R.drawable.class;
                Field field3 = res3.getField(ghsimg3v);
                int id3 = field3.getInt(null);
                ghscImg3.setImageResource(id3);
                ghscImg3.setVisibility(View.VISIBLE);
            } else {
                ghscImg3.setVisibility(View.GONE);
            }

            if (!ghsimg4v.isEmpty()) {
                Class res4 = R.drawable.class;
                Field field4 = res4.getField(ghsimg4v);
                int id4 = field4.getInt(null);
                ghscImg4.setImageResource(id4);
                ghscImg4.setVisibility(View.VISIBLE);

                //rebuild the constraint of image4 and ghs value when image4 is exist
                layout = (ConstraintLayout) findViewById(R.id.PRE_GHSCL);
                set.clone(layout);
                set.connect(R.id.ghscImg4, ConstraintSet.TOP, R.id.ghstitle, ConstraintSet.BOTTOM, 170);
//                set.clear(R.id.ghsvalue, ConstraintSet.TOP);
                set.connect(R.id.ghsvalue, ConstraintSet.TOP, R.id.ghscImg4, ConstraintSet.BOTTOM, 15);
                set.applyTo(layout);

            } else {
                ghscImg4.setVisibility(View.GONE);

                //rebuild the constraint of image4 and ghs value when image4 not exist and for image1 exist/ not exist
                layout = (ConstraintLayout) findViewById(R.id.PRE_GHSCL);
                set.clone(layout);
                set.clear(R.id.ghscImg4, ConstraintSet.TOP);
                if (!ghsimg1v.isEmpty() && ghsimg4v.isEmpty()) {
                    set.connect(R.id.ghsvalue, ConstraintSet.TOP, R.id.ghscImg1, ConstraintSet.BOTTOM, 15);
                } else if (ghsimg1v.isEmpty() && ghsimg4v.isEmpty()) {
                    set.connect(R.id.ghsvalue, ConstraintSet.TOP, R.id.ghstitle, ConstraintSet.BOTTOM, 15);
                }
////                else {
//////                    set.connect(R.id.ghscImg4, ConstraintSet.BOTTOM, R.id.ghsvalue, ConstraintSet.TOP, 15);
////                    set.clear(R.id.ghsvalue, ConstraintSet.TOP);
////                }
                set.applyTo(layout);
            }

            if (!ghsimg5v.isEmpty()) {
                Class res5 = R.drawable.class;
                Field field5 = res5.getField(ghsimg5v);
                int id5 = field5.getInt(null);
                ghscImg5.setImageResource(id5);
                ghscImg5.setVisibility(View.VISIBLE);
            } else {
                ghscImg5.setVisibility(View.GONE);
            }

            //set TI textviews' value
            String unnotitle = getString(R.string.ti_unno)  + space ;
            String dgtitle = getString(R.string.ti_dgclass)  + space ;
            String haztitle = getString(R.string.ti_haz)  + space ;
            String pgtitle = getString(R.string.ti_packgrp)  + space ;
            String psntitle = getString(R.string.ti_psn)  + space ;

            String comTIString = "";

            String unnoString = unnotitle + previewTIVar.road_unno;
            String dgString = dgtitle + previewTIVar.road_dgclass;
            String hazString = haztitle + previewTIVar.road_hazchem;
            String pgString = pgtitle + previewTIVar.road_packgrp;
            String psnString = psntitle + previewTIVar.road_psn;

            if (!previewTIVar.road_unno.isEmpty()) {
                comTIString = unnoString + dspace;
            }

            if (!previewTIVar.road_dgclass.isEmpty()) {
                comTIString = comTIString + dgString + dspace;
            }

            if (!previewTIVar.road_hazchem.isEmpty()) {
                comTIString = comTIString + hazString + dspace;
            }

            if (!previewTIVar.road_packgrp.isEmpty()) {
                comTIString = comTIString + pgString + dspace;
            }

            if (!previewTIVar.road_psn.isEmpty()) {
                comTIString = comTIString + psnString;
            }

            if (!comTIString.isEmpty()) {
                tivalue.setText(fromHtml(comTIString));
                tivalue.setVisibility(View.VISIBLE);
                tititle.setVisibility(View.VISIBLE);
            } else {
                tivalue.setText("");
                tivalue.setVisibility(View.GONE);
                tivalue.setVisibility(View.GONE);
            }

            //tivalue.setText(fromHtml(unnoString + dgString + hazString + pgString + psnString));
            //tivalue.setText(fromHtml(unnotitle + "<br/>" + previewTIVar.road_unno + "<br/><br/>" + dgtitle + "<br/>" + previewTIVar.road_dgclass + "<br/><br/>" + haztitle + "<br/>" + previewTIVar.road_hazchem + "<br/><br/>" + pgtitle + "<br/>" + previewTIVar.road_packgrp + "<br/><br/>" + psntitle + "<br/>" + previewTIVar.road_psn));
//            Log.i("DG String", previewTIVar.dgImg);

            //setup the ti images
            if (previewTIVar.dgImg != null && !previewTIVar.dgImg.isEmpty()) {
                Class res1 = R.drawable.class;
                Field field1 = res1.getField(previewTIVar.dgImg);
                int id1 = field1.getInt(null);
                tiImg.setImageResource(id1);

                tiImg.setVisibility(View.VISIBLE);

                //rebuild the constraint of ti image and ti value
                layout = (ConstraintLayout) findViewById(R.id.PRE_GHSCL);
                set.clone(layout);
                set.connect(R.id.tivalue, ConstraintSet.TOP, R.id.tiImg, ConstraintSet.BOTTOM, 20);

            } else {
                tiImg.setVisibility(View.GONE);

                //rebuild the constraint of ti image and ti value
                layout = (ConstraintLayout) findViewById(R.id.PRE_GHSCL);
                set.clone(layout);
                set.connect(R.id.tivalue, ConstraintSet.TOP, R.id.tititle, ConstraintSet.BOTTOM, 20);
                set.applyTo(layout);
            }

            if (!previewTIVar.subImg1.isEmpty() && !previewTIVar.subImg1.equals("dg")) {
                Class res2 = R.drawable.class;
                Field field2 = res2.getField(previewTIVar.subImg1);
                int id2 = field2.getInt(null);
                tisubImg1.setImageResource(id2);

                tisubImg1.setVisibility(View.VISIBLE);


                set.applyTo(layout);
            } else {
                tisubImg1.setVisibility(View.GONE);


            }

            if (!previewTIVar.subImg2.isEmpty() && !previewTIVar.subImg2.equals("dg")) {
                Class res3 = R.drawable.class;
                Field field3 = res3.getField(previewTIVar.subImg2);
                int id3 = field3.getInt(null);
                tisubImg2.setImageResource(id3);

                tisubImg2.setVisibility(View.VISIBLE);
            } else {
                tisubImg2.setVisibility(View.GONE);
            }

//            sdsBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//
//
//            });



//            Thread t= new Thread(new Runnable() {
//
//                public void run() {
//                    System.out.println("reach 0");
//
//                    System.out.println("status1:" + status1.booleanValue());

//                    if (status1) {
////                        System.out.println("reach 1");
//                        if (sta1) {
////                            System.out.println("reach 2");
//                            scrollContent1.setVisibility(View.VISIBLE);
//                        }
//                    } else {
//                        scrollContent1.setVisibility(View.INVISIBLE);

//                    }

//                }
//            }); t.start();

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
                faSC.setVisibility(View.INVISIBLE);

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
                            sdsBtn.setTextColor(Color.WHITE);
                            sdsBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
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
            DialogFragment df = new DialogFragment();
            df.callAlert(SDSViewMainPageAC.this, "Call SDS Failed!\nPlease check your connection and try again.");

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


//        System.out.println("preview tapped");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tiSC.setVisibility(View.INVISIBLE);
                preghsSC.setVisibility(View.VISIBLE);
                faSC.setVisibility(View.INVISIBLE);


                //set toolbar title value
                toolbarTitle.setText("PREVIEW");

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


                scrollContent2.setVisibility(View.INVISIBLE);
                scrollContent3.setVisibility(View.INVISIBLE);

//                scrolltobuttomCheck1(preghsSC);

//                scrolltobuttomCheck2(tiSC);
//                scrolltobuttomCheck3(faSC);

                //preghsSC.getViewTreeObserver().removeOnGlobalLayoutListener(this);

//                scrollableCheck1(preghsSC);
//                scrolltobuttomCheck1(preghsSC);
//                System.out.println("status1:" + status1.booleanValue());

//                preghsSC.invalidate();
//                preghsSC.setVisibility(View.GONE);
//                preghsSC.setVisibility(View.VISIBLE);
//                System.out.println(status1);

//                if(status1) {
//                    if (sta1) {
//                        scrollContent1.setVisibility(View.VISIBLE);
//                    }
//
//                } else {
//                    scrollContent1.setVisibility(View.INVISIBLE);
//                }


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


                //set toolbar title value
                toolbarTitle.setText("GHS");

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

                scrollContent2.setVisibility(View.INVISIBLE);
                scrollContent3.setVisibility(View.INVISIBLE);

//                scrollableCheck1(preghsSC);
//                scrolltobuttomCheck1(preghsSC);

//                preghsSC.invalidate();
//                preghsSC.setVisibility(View.GONE);
//                preghsSC.setVisibility(View.VISIBLE);
//                System.out.println(status1);

//                if(status1) {
//                    if (sta1) {
//                        scrollContent1.setVisibility(View.VISIBLE);
//                    }
//
//                } else {
//                    scrollContent1.setVisibility(View.INVISIBLE);
//                }


            }
        });
    }

    public void dgBtnTapped(View v) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                scrollableCheck2(tiSC);


                tiSC.setVisibility(View.VISIBLE);
                preghsSC.setVisibility(View.INVISIBLE);
                faSC.setVisibility(View.INVISIBLE);
                setTIPageBtns();


                //set toolbar title value
                toolbarTitle.setText("DG CLASS");

                previewBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                dgBtn.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                faBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                ghsBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));

                scrollContent1.setVisibility(View.INVISIBLE);
                scrollContent3.setVisibility(View.INVISIBLE);



//                scrolltobuttomCheck2(tiSC);

//                tiSC.invalidate();
//                tiSC.setVisibility(View.GONE);
//                tiSC.setVisibility(View.VISIBLE);

//                if(status2) {
//                    if(sta2) {
//                        scrollContent2.setVisibility(View.VISIBLE);
//                    }
//
//                } else {
//                    scrollContent2.setVisibility(View.INVISIBLE);
//                }

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

                tisroadBtn.setBackgroundResource(R.drawable.tipage_tappedbuttons_states);
                tisseaBtn.setBackgroundResource(R.drawable.tipage_untapbuttons_states);
                tisairBtn.setBackgroundResource(R.drawable.tipage_untapbuttons_states);

                tiSC.setBackgroundResource(R.drawable.tis_roadbg);
            }
        });

        tisunv.setText(previewTIVar.road_unno);
        tisdgv.setText(previewTIVar.road_dgclass);
        tisriskv.setText(previewTIVar.road_subrisks);
        tispgv.setText(previewTIVar.road_packgrp);
        tispsnv.setText(previewTIVar.road_psn);

        if (!previewTIVar.road_hazchem.isEmpty()) {
            tishcv.setText(previewTIVar.road_hazchem);

            tishctitle.setVisibility(View.VISIBLE);
            tishcv.setVisibility(View.VISIBLE);
        } else {
            tishctitle.setVisibility(View.GONE);
            tishcv.setVisibility(View.GONE);
        }

        if (!previewTIVar.road_epg.isEmpty()) {
            tisepgv.setText(previewTIVar.road_epg);

            tisepgtitle.setVisibility(View.VISIBLE);
            tisepgv.setVisibility(View.VISIBLE);
        } else {
            tisepgtitle.setVisibility(View.GONE);
            tisepgv.setVisibility(View.GONE);
        }

        if (!previewTIVar.road_ierg.isEmpty()) {
            tisinov.setText(previewTIVar.road_ierg);

            tisinotitle.setVisibility(View.VISIBLE);
            tisinov.setVisibility(View.VISIBLE);
        } else {
            tisinotitle.setVisibility(View.GONE);
            tisinov.setVisibility(View.GONE);
        }

        if (!previewTIVar.road_packmethod.isEmpty()) {
            tispmv.setText(previewTIVar.road_packmethod);

            tispmtitle.setVisibility(View.VISIBLE);
            tispmv.setVisibility(View.VISIBLE);
        } else {
            tispmtitle.setVisibility(View.GONE);
            tispmv.setVisibility(View.GONE);
        }


        tissymv.setText("");
        tissymtitle.setVisibility(View.GONE);
        tissymv.setVisibility(View.GONE);

        tisemsv.setText("");
        tisemstitle.setVisibility(View.GONE);
        tisemsv.setVisibility(View.GONE);

        tismpv.setText("");
        tismptitle.setVisibility(View.GONE);
        tismpv.setVisibility(View.GONE);

        getTIimgs(previewTIVar.road_dgclass, previewTIVar.road_subrisks);

        setTIimgs();

    }

    public void setTIPageSeaBtn() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {


                tisroadBtn.setBackgroundResource(R.drawable.tipage_untapbuttons_states);
                tisseaBtn.setBackgroundResource(R.drawable.tipage_tappedbuttons_states);
                tisairBtn.setBackgroundResource(R.drawable.tipage_untapbuttons_states);

                tiSC.setBackgroundResource(R.drawable.tis_seabg);
            }
        });

        tisunv.setText(previewTIVar.imdg_unno);
        tisdgv.setText(previewTIVar.imdg_dgclass);
        tisriskv.setText(previewTIVar.imdg_subrisks);
        tispgv.setText(previewTIVar.imdg_packgrp);
        tispsnv.setText(previewTIVar.imdg_psn);


        tishcv.setText("");
        tishctitle.setVisibility(View.GONE);
        tishcv.setVisibility(View.GONE);

        tisepgv.setText("");
        tisepgtitle.setVisibility(View.GONE);
        tisepgv.setVisibility(View.GONE);

        tisinov.setText("");
        tisinotitle.setVisibility(View.GONE);
        tisinov.setVisibility(View.GONE);

        tispmv.setText("");
        tispmtitle.setVisibility(View.GONE);
        tispmv.setVisibility(View.GONE);

        tissymv.setText("");
        tissymtitle.setVisibility(View.GONE);
        tissymv.setVisibility(View.GONE);

        if(!previewTIVar.imdg_ems.isEmpty()) {
            tisemsv.setText(previewTIVar.imdg_ems);
            tisemstitle.setVisibility(View.VISIBLE);
            tisemsv.setVisibility(View.VISIBLE);
        } else {
            tisemstitle.setVisibility(View.GONE);
            tisemsv.setVisibility(View.GONE);
        }

        if(!previewTIVar.imdg_mp.isEmpty()) {
            tismpv.setText(previewTIVar.imdg_mp);
            tismptitle.setVisibility(View.VISIBLE);
            tismpv.setVisibility(View.VISIBLE);
        } else {
            tismptitle.setVisibility(View.GONE);
            tismpv.setVisibility(View.GONE);
        }


        getTIimgs(previewTIVar.imdg_dgclass, previewTIVar.imdg_subrisks);

        setTIimgs();

    }

    public void setTIPageAirBtn() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                tisroadBtn.setBackgroundResource(R.drawable.tipage_untapbuttons_states);
                tisseaBtn.setBackgroundResource(R.drawable.tipage_untapbuttons_states);
                tisairBtn.setBackgroundResource(R.drawable.tipage_tappedbuttons_states);

                tiSC.setBackgroundResource(R.drawable.tis_airbg);
            }
        });

        tisunv.setText(previewTIVar.iata_unno);
        tisdgv.setText(previewTIVar.iata_dgclass);
        tisriskv.setText(previewTIVar.iata_subrisks);
        tispgv.setText(previewTIVar.iata_packgrp);
        tispsnv.setText(previewTIVar.iata_psn);

        tishcv.setText("");
        tishctitle.setVisibility(View.GONE);
        tishcv.setVisibility(View.GONE);

        tisepgv.setText("");
        tisepgtitle.setVisibility(View.GONE);
        tisepgv.setVisibility(View.GONE);

        tisinov.setText("");
        tisinotitle.setVisibility(View.GONE);
        tisinov.setVisibility(View.GONE);

        tispmv.setText("");
        tispmtitle.setVisibility(View.GONE);
        tispmv.setVisibility(View.GONE);

        if(!previewTIVar.iata_symbol.isEmpty()) {
            tissymv.setText(previewTIVar.iata_symbol);
            tissymtitle.setVisibility(View.VISIBLE);
            tissymv.setVisibility(View.VISIBLE);
        } else {
            tissymtitle.setVisibility(View.GONE);
            tissymv.setVisibility(View.GONE);
        }

        tisemsv.setText("");
        tisemstitle.setVisibility(View.GONE);
        tisemsv.setVisibility(View.GONE);

        tismpv.setText("");
        tismptitle.setVisibility(View.GONE);
        tismpv.setVisibility(View.GONE);

        getTIimgs(previewTIVar.iata_dgclass, previewTIVar.iata_subrisks);

        setTIimgs();
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

    public void getTIimgs(String dgv, String subv) {

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

    public void setTIimgs() {

        //init the constrainst set
        ConstraintSet set = new ConstraintSet();
        ConstraintLayout layout;

        try {
            if (previewTIVar.tisdgImg != null && !previewTIVar.tisdgImg.isEmpty()) {
                Class res1 = R.drawable.class;
                Field field1 = res1.getField(previewTIVar.tisdgImg);
                int id1 = field1.getInt(null);
                tisdgImg.setImageResource(id1);

                tisdgImg.setVisibility(View.VISIBLE);

                //rebuild the constraint of ti image and ti value
                layout = findViewById(R.id.TICL);
                set.clone(layout);
                set.connect(R.id.tisunno, ConstraintSet.TOP, R.id.tisdgImg, ConstraintSet.BOTTOM, 40);
                set.applyTo(layout);
            } else {
                tisdgImg.setVisibility(View.GONE);

                //rebuild the constraint of ti image and ti value
                layout = findViewById(R.id.TICL);
                set.clone(layout);
                set.connect(R.id.tisunno, ConstraintSet.TOP, R.id.tiinnerBtns, ConstraintSet.BOTTOM, 20);
                set.applyTo(layout);
            }

            if (!previewTIVar.tissubImg1.isEmpty() && previewTIVar.tissubImg1.equals("dg")) {
                Class res2 = R.drawable.class;
                Field field2 = res2.getField(previewTIVar.tissubImg1);
                int id2 = field2.getInt(null);
                tissubImg1.setImageResource(id2);

                tissubImg1.setVisibility(View.VISIBLE);
            } else {
                tissubImg1.setVisibility(View.GONE);
            }

            if (!previewTIVar.tissubImg2.isEmpty() && previewTIVar.tissubImg2.equals("dg")) {
                Class res3 = R.drawable.class;
                Field field3 = res3.getField(previewTIVar.tissubImg2);
                int id3 = field3.getInt(null);
                tissubImg2.setImageResource(id3);

                tissubImg2.setVisibility(View.VISIBLE);
            } else {
                tissubImg2.setVisibility(View.GONE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void faBtnTapped(View v) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                scrollableCheck3(faSC);

                tiSC.setVisibility(View.INVISIBLE);
                preghsSC.setVisibility(View.INVISIBLE);
                faSC.setVisibility(View.VISIBLE);


                //set toolbar title value
                toolbarTitle.setText("FIRST AID");

                previewBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                dgBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));
                faBtn.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                ghsBtn.setBackgroundColor(getResources().getColor(R.color.colorLightBlack));

                setFAIDValues();

                scrollContent1.setVisibility(View.INVISIBLE);
                scrollContent2.setVisibility(View.INVISIBLE);

//                scrolltobuttomCheck3(faSC);

//                faSC.invalidate();
//                faSC.setVisibility(View.GONE);
//                faSC.setVisibility(View.VISIBLE);
//                faSC.removeAllViews();

//                Log.i("value", "Ti scroll bar Y:" +tiSC.getScrollY() + "\nTi scroll bar child At:" +tiSC.getChildAt(0).getBottom() + "\nTi scroll bar Height:" +tiSC.getHeight());


//                if(status3) {
//                    if(sta3){
//                        scrollContent3.setVisibility(View.VISIBLE);
//                    }
//
//                } else {
//                    scrollContent3.setVisibility(View.INVISIBLE);
//                }


            }
        });
    }

    //setup first AID valuse and layout
    public void setFAIDValues() {

        if(!previewFAIDVar.inhalation.isEmpty()) {
            fainhv.setText(previewFAIDVar.inhalation);
            inhtitle.setVisibility(View.VISIBLE);
            fainhv.setVisibility(View.VISIBLE);
        } else {
            inhtitle.setVisibility(View.GONE);
            fainhv.setVisibility(View.GONE);
        }

        if(!previewFAIDVar.ingestion.isEmpty()) {
            faingv.setText(previewFAIDVar.ingestion);
            ingtitle.setVisibility(View.VISIBLE);
            faingv.setVisibility(View.VISIBLE);
        } else {
            ingtitle.setVisibility(View.GONE);
            faingv.setVisibility(View.GONE);
        }

        if(!previewFAIDVar.skin.isEmpty()) {
            faskinv.setText(previewFAIDVar.skin);
            skintitle.setVisibility(View.VISIBLE);
            faskinv.setVisibility(View.VISIBLE);
        } else {
            skintitle.setVisibility(View.GONE);
            faskinv.setVisibility(View.GONE);
        }

        if(!previewFAIDVar.eye.isEmpty()) {
            faeyev.setText(previewFAIDVar.eye);
            eyetitle.setVisibility(View.VISIBLE);
            faeyev.setVisibility(View.VISIBLE);
        } else {
            eyetitle.setVisibility(View.GONE);
            faeyev.setVisibility(View.GONE);
        }

        if(!previewFAIDVar.fafacilities.isEmpty()) {
            fafafv.setText(previewFAIDVar.fafacilities);
            faftitle.setVisibility(View.VISIBLE);
            fafafv.setVisibility(View.VISIBLE);
        } else {
            faftitle.setVisibility(View.GONE);
            fafafv.setVisibility(View.GONE);
        }

        if(!previewFAIDVar.advdoctor.isEmpty()) {
            faatdv.setText(previewFAIDVar.advdoctor);
            atdtitle.setVisibility(View.VISIBLE);
            faatdv.setVisibility(View.VISIBLE);
        } else {
            atdtitle.setVisibility(View.GONE);
            faatdv.setVisibility(View.GONE);
        }

    }

//    @TargetApi(16)
    //check the scroll bar
    public void scrollableCheck1(final ScrollView scrollView) {
        ViewTreeObserver observer = scrollView.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int viewHeight = scrollView.getMeasuredHeight();
                int contentHeight = scrollView.getChildAt(0).getHeight();
                if (viewHeight - contentHeight < 0) {
//                    scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                    Log.i("scroll bar1", "scrollable!!");
                    status1 = true;
                    if(once == 0) {
                        once = once + 1;
                        previewBtn.performClick();
                    }

                } else {
//                    scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                    Log.i("scroll bar1", "not scrollable!!");
                    status1 = false;
                }
            }
        });

    }

//    @TargetApi(16)
    public void scrollableCheck2(final ScrollView scrollView) {
        ViewTreeObserver observer = scrollView.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int viewHeight = scrollView.getMeasuredHeight();
                int contentHeight = scrollView.getChildAt(0).getHeight();
                if (viewHeight - contentHeight < 0) {
//                    Log.i("scroll bar2", "scrollable!!");
//                    scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    status2 = true;

                } else {
//                    scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                    Log.i("scroll bar2", "not scrollable!!");
                    status2 = false;
                }
            }
        });
    }

//    @TargetApi(16)
    public void scrollableCheck3(final ScrollView scrollView) {
        ViewTreeObserver observer = scrollView.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int viewHeight = scrollView.getMeasuredHeight();
                int contentHeight = scrollView.getChildAt(0).getHeight();
//                Log.i("value", "Ti scroll bar Y:" +scrollView.getScrollY() + "\nTi scroll bar child At:" +scrollView.getChildAt(0).getBottom() + "\nTi scroll bar Height:" +scrollView.getHeight());
                if (viewHeight - contentHeight < 0) {
//                    Log.i("scroll bar3", "scrollable!!");
                    status3 = true;
//                    scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                } else {
//                    Log.i("scroll bar3", "not scrollable!!");

                    status3 = false;
//                    scrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }

//    @TargetApi(16)
    public void scrolltobuttomCheck1(final ScrollView scrollView) {
        scrollView.getViewTreeObserver()
                .addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        if(scrollView.getChildAt(0).getBottom() <= (scrollView.getHeight() + scrollView.getScrollY()) + 1) {
//                            Log.i("scroll bar1", "reach to buttom");
//                            scrollView.getViewTreeObserver().removeOnScrollChangedListener(this);
                            scrollContent1.setVisibility(View.INVISIBLE);
                            scrollContent3.setVisibility(View.INVISIBLE);
                            scrollContent2.setVisibility(View.INVISIBLE);
                            sta1 = false;
                        } else {
//                            Log.i("scroll bar1", "not reach to buttom");
//                            scrollView.getViewTreeObserver().removeOnScrollChangedListener(this);
                            scrollContent1.setVisibility(View.VISIBLE);
                            scrollContent3.setVisibility(View.INVISIBLE);
                            scrollContent2.setVisibility(View.INVISIBLE);
                            sta1 = true;
                        }

                    }
                });
    }

//    @TargetApi(16)
    public void scrolltobuttomCheck2(final ScrollView scrollView) {
        scrollView.getViewTreeObserver()
                .addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        if(scrollView.getChildAt(0).getBottom() <= (scrollView.getHeight() + scrollView.getScrollY())  + 1) {
//                            Log.i("scroll bar2", "reach to buttom");
//                            scrollView.getViewTreeObserver().removeOnScrollChangedListener(this);
                            scrollContent2.setVisibility(View.INVISIBLE);
                            scrollContent1.setVisibility(View.INVISIBLE);
                            scrollContent3.setVisibility(View.INVISIBLE);
                            sta2 = false;
                        } else {
//                            Log.i("scroll bar2", "not reach to buttom");
//                            scrollView.getViewTreeObserver().removeOnScrollChangedListener(this);
                            scrollContent2.setVisibility(View.VISIBLE);
                            scrollContent1.setVisibility(View.INVISIBLE);
                            scrollContent3.setVisibility(View.INVISIBLE);
                            sta2 = true;
                        }

                    }
                });
    }

//    @TargetApi(16)
    public void scrolltobuttomCheck3(final ScrollView scrollView) {
        scrollView.getViewTreeObserver()
                .addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        if(scrollView.getChildAt(0).getBottom()  <= (scrollView.getHeight() + scrollView.getScrollY())  + 1) {
//                            Log.i("scroll bar3", "reach to buttom");
//                            scrollView.getViewTreeObserver().removeOnScrollChangedListener(this);
                            scrollContent3.setVisibility(View.INVISIBLE);
                            scrollContent1.setVisibility(View.INVISIBLE);
                            scrollContent2.setVisibility(View.INVISIBLE);
                            sta3 = false;
                        } else {
//                            Log.i("scroll bar3", "not reach to buttom");
//                            scrollView.getViewTreeObserver().removeOnScrollChangedListener(this);
                            scrollContent3.setVisibility(View.VISIBLE);
                            scrollContent1.setVisibility(View.INVISIBLE);
                            scrollContent2.setVisibility(View.INVISIBLE);
                            sta3 = true;
                        }

                    }
                });
    }

    public void scrollbarCheck(ScrollView scrollView, boolean status) {
        int yValue = scrollView.getScrollY();
        if (status) {
//            if (scrollView.g)
        }

    }

//    public void scrollAtButtomCheck3(final ScrollView scrollView) {
//        scrollView.getViewTreeObserver()
//                .addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
//                    @Override
//                    public void onScrollChanged() {
//                        if(scrollView.getChildAt(0).getBottom()  >= (scrollView.getHeight() + scrollView.getScrollY()) + 10) {
//                            Log.i("scroll bar3", "reach to buttom");
//                            scrollContent3.setVisibility(View.INVISIBLE);
//                            scrollContent1.setVisibility(View.INVISIBLE);
//                            scrollContent2.setVisibility(View.INVISIBLE);
//                            sta3 = false;
//                        } else {
//                            Log.i("scroll bar3", "not reach to buttom");
//                            scrollContent3.setVisibility(View.VISIBLE);
//                            scrollContent1.setVisibility(View.INVISIBLE);
//                            scrollContent2.setVisibility(View.INVISIBLE);
//                            sta3 = true;
//                        }
//
//                    }
//                });
//    }

}


