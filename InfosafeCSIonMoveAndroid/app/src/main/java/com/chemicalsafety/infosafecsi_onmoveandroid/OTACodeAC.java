package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.UserInfoStoredFunction;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.loginVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf.csiWCF_VM;

public class OTACodeAC extends AppCompatActivity {

    private EditText otacodeET;
    Button otaContinueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_a_code_a_c);

        otacodeET = findViewById(R.id.otaEditText);
        otaContinueButton = findViewById(R.id.otaContinueButton);

        setupUI(findViewById(R.id.OTACodeCL));
    }

    public void otaContinueButtonTapped(View view) {
        callLoginWCF();
    }

    public void callLoginWCF() {
        final String otaText = otacodeET.getText().toString().trim();

        final DialogFragment df = new DialogFragment();

        if (otaText.isEmpty()) {
            df.callAlert(OTACodeAC.this, "OTA Code is empty!\nPlease enter OTA Code and try again.");
        } else {

            final csiWCF_VM wcf = new csiWCF_VM();
//            DialogFragment df = new DialogFragment();
            df.callloadingScreen(OTACodeAC.this, "Loading...");

            Thread t= new Thread(new Runnable() {

                public void run() {
                    if (wcf.Login(loginVar.email,"", otaText,"")) {

                        runOnUiThread(new Runnable() {
                            public void run() {
                                df.cancelLoadingScreen();


                                if (loginVar.passed.equals(true)) {

                                    if (loginVar.isgeneric.equals(true) && (loginVar.needchooseclient.equals(true))) {
                                        toLoginMultipleClientAC();
                                    } else if (loginVar.isgeneric.equals(true) && (loginVar.needchooseclient.equals(false))) {
                                        toSeachAC();
                                    } else if (loginVar.isgeneric.equals(false) && (loginVar.needchooseclient.equals(true))) {
                                        UserInfoStoredFunction.setOTACode(this, otaText);
                                        toLoginMultipleClientAC();
                                    } else if (loginVar.isgeneric.equals(false) && (loginVar.needchooseclient.equals(false)) && loginVar.needpsw.equals(true)) {
                                        toLoginpasswordAC();
                                    }

                                } else if (loginVar.passed.equals(false)) {

                                    if (loginVar.isgeneric.equals(true) && (loginVar.needchooseclient.equals(true))) {
                                        toLoginMultipleClientAC();
                                    } else if (loginVar.isgeneric.equals(false) && (loginVar.needchooseclient.equals(true))) {
                                        UserInfoStoredFunction.setOTACode(this, otaText);
                                        toLoginMultipleClientAC();
                                    } else if (loginVar.isgeneric.equals(false) && (loginVar.needchooseclient.equals(false)) && loginVar.needpsw.equals(true)) {
                                        toLoginpasswordAC();

                                    } else if (loginVar.isgeneric.equals(true) && loginVar.retIndexText.contains("OTA Code Sent")) {
                                        toLoginOTAAC();
                                    } else {
                                        df.callAlert(OTACodeAC.this, "Login Failed!\n" + loginVar.retIndexText);
                                    }
                                }

                            }
                        });

                    } else {
                        Log.d("error", "login failed");

                        runOnUiThread(new Runnable() {
                            public void run() {
                                df.cancelLoadingScreen();
                                df.callAlert(OTACodeAC.this, "Login Failed!\n" + loginVar.retIndexText);
                            }
                        });


                    }
                }
            });
            t.start();
        }
    }

    //jump to search page
    public void toSeachAC() {

        Intent intent = new Intent(this, SearchPageAC.class);
        startActivity(intent);
    }

    //jump to password page
    public void toLoginpasswordAC() {

        Intent intent = new Intent(this, LoginPageAC.class);
        startActivity(intent);
    }

    //jump to ota code page
    public void toLoginOTAAC() {

        Intent intent = new Intent(this, OTACodeAC.class);
        startActivity(intent);
    }

    //jump to ota code page
    public void toLoginMultipleClientAC() {

        Intent intent = new Intent(this, MultipleClientAC.class);
        startActivity(intent);
    }


    //setup listener for the view except EditText
    public void setupUI(View view) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(OTACodeAC.this);
                    return false;
                }
            });
        }
    }

    //hide keyboard when called
    public void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getRootView().getWindowToken(), 0);
    }
}