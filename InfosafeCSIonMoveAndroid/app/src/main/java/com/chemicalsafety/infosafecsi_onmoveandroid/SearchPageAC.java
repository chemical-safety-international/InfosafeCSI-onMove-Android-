package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.checkBeforeYouPurchaseProductNameItem;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.loginVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.searchVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf.csiWCF_VM;

public class SearchPageAC extends AppCompatActivity {

    Button searchBtn, logOffBtn, scanButton, scanOCRButton;
    EditText pnameET, supplierET, pcodeET, barcodeET;
    Switch checkBeforeYouPurchaseSwitch;
    ImageView searchCompanyLogo;

    String supplierText;
    Drawable originalDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchBtn = findViewById(R.id.searchBtn);
        scanButton = findViewById(R.id.scanButton);
        scanOCRButton = findViewById(R.id.scanOCRButton);

        pnameET = findViewById(R.id.pnameSearchBar);
        supplierET = findViewById(R.id.supplierSearchBar);
        pcodeET = findViewById(R.id.pcodeSearchBar);
        barcodeET = findViewById(R.id.barcodeSearchBar);

        checkBeforeYouPurchaseSwitch = findViewById(R.id.search_switch);

        searchCompanyLogo = findViewById(R.id.search_companyLogo);

//        callCriteriaList();
        setupUI(findViewById(R.id.SearchCL));

        preloadLogo();
    }


//    public void callCriteriaList() {
//
//        csiWCFMethods wcf = new csiWCFMethods();
//        wcf.SearchCriteriaList();
//
//    }

    public void setupUI(View view) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(SearchPageAC.this);
                    return false;
                }
            });
        }
    }

    public void preloadLogo() {
        if (!loginVar.clientlogo.isEmpty()) {
            byte[] decodedString = Base64.decode(loginVar.clientlogo.getBytes(), Base64.DEFAULT);
            Bitmap decodeByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            searchCompanyLogo.setImageBitmap(decodeByte);
        }
    }


    public void searchBtnTapped(View view) {

        //hid the soft keyboard when leaving this page
//        try {
//            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //get the search bars input
        searchVar.pnameInput = pnameET.getText().toString().trim();
        searchVar.pcodeInput = pcodeET.getText().toString().trim();
        searchVar.supplierInput = supplierET.getText().toString().trim();
        searchVar.barcodeInput = barcodeET.getText().toString().trim();

        final DialogFragment df = new DialogFragment();

        if (searchVar.pnameInput.isEmpty() && searchVar.pcodeInput.isEmpty() && searchVar.supplierInput.isEmpty() && searchVar.barcodeInput.isEmpty()) {
            df.callAlert(SearchPageAC.this, "Search input empty!\nPlease check your input and try again.");
        } else {

            if (!searchVar.pnameInput.isEmpty() && searchVar.pnameInput.length() < 3) {
                df.callAlert(SearchPageAC.this, "Search failed!\nPlease enter more than 2 characters for product name!" );
            } else if(searchVar.supplierInput.length() == 1) {
                df.callAlert(SearchPageAC.this, "Search failed!\nPlease enter more than 1 characters for supplier!");
            } else {
                //call the Search WCF
                final csiWCF_VM wcf =new csiWCF_VM();
                df.callloadingScreen(SearchPageAC.this, "Searching...");
                Thread t= new Thread(new Runnable() {

                    public void run() {
                        if (wcf.Search(searchVar.pnameInput, searchVar.pcodeInput, searchVar.supplierInput, searchVar.barcodeInput, loginVar.clientid, loginVar.infosafeid, loginVar.apptype)) {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    df.cancelLoadingScreen();
                                    Intent intent;
                                    if (searchVar.checkBeforeYouPurchaseSwitchStatus.equals(true)) {

                                        intent = new Intent(SearchPageAC.this, CheckBeforeYouPurchaseProductNameTableAC.class);
                                    } else {
                                        intent = new Intent(SearchPageAC.this, SearchTablePageAC.class);
                                    }
                                    startActivity(intent);

                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    df.cancelLoadingScreen();
                                    Log.i("error", "Search failed!.");
                                    df.callAlert(SearchPageAC.this, "Search Failed!\nPlease check the input and connection.");
                                }
                            });
                        }
                    }});
                t.start();
            }


        }

    }

    //set action for switch button
    public void setSwitchAction(View view) {
        if (checkBeforeYouPurchaseSwitch.isChecked()) {
            searchVar.checkBeforeYouPurchaseSwitchStatus = true;
            supplierText = supplierET.getText().toString();
            originalDrawable = supplierET.getBackground();
            supplierET.setText("");

            supplierET.setEnabled(false);
            supplierET.setBackgroundColor(Color.DKGRAY);
        } else if (!checkBeforeYouPurchaseSwitch.isChecked()) {
            searchVar.checkBeforeYouPurchaseSwitchStatus = false;
            supplierET.setText(supplierText);
            supplierET.setEnabled(true);
            supplierET.setBackground(originalDrawable);

        }
    }

    public void tipButtonTapped(View view) {
        final DialogFragment df = new DialogFragment();
        df.callAlert(SearchPageAC.this, "Turn on to enable Check Before You Purchase search function.");
    }


    public void scanButtonTapped(View view) {
        Intent intent = new Intent(this, ScanBarcodePageAC.class);
        startActivity(intent);

    }

    public void setScanOCRButton(View view) {
        Intent intent = new Intent(this, OCRPageAC.class);
        startActivity(intent);
    }

    public void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getRootView().getWindowToken(), 0);
    }

//    @Override
//    public void onBackPressed() {
//        // Do Here what ever you want do on back press;
//    }
}
