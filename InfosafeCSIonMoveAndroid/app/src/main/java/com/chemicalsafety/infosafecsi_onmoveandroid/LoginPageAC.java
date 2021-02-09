package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.content.Intent;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.UserInfoStoredFunction;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.loginVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf.csiWCF_VM;


public class LoginPageAC extends AppCompatActivity {

    private EditText email;
    private EditText password;

    Button loginButton, remBtn;

    ImageView loginLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.loginID);
        password = findViewById(R.id.loginPW);
        loginButton = findViewById(R.id.loginButton);
        remBtn = findViewById(R.id.remBtn);

        loginLogo = findViewById(R.id.loginLogo);
        //check the thread policy
//        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
//        }

//        Log.i("email id:", UserInfoStoredFunction.getEmail(this));


        if (UserInfoStoredFunction.getStatus(this, loginVar.clientid)) {

            setRememberValues();
        }
//        else {
//            loginLogo.setImageResource(R.drawable.csi_logo);
//        }

//        email.setOnFocusChangeListener(focusListener);
//        password.setOnFocusChangeListener(focusListener);
        email.clearFocus();


//        email.requestFocus();
//        InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
//        inputMethodManager.restartInput(email);

        //build listener for keyboard
        setupUI(findViewById(R.id.LoginCL));

        preloadEmail();
        preloadLogo();
    }

    public void preloadEmail() {
        if (!loginVar.email.isEmpty()) {
            email.setText(loginVar.email);
        }
    }

    public void preloadLogo() {
        if (!loginVar.clientlogo.isEmpty()) {
            byte[] decodedString = Base64.decode(loginVar.clientlogo.getBytes(), Base64.DEFAULT);
            Bitmap decodeByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            loginLogo.setImageBitmap(decodeByte);
        }
    }

    //setup listener for the view except EditText
    public void setupUI(View view) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(LoginPageAC.this);
                    return false;
                }
            });
        }
    }

    private View.OnFocusChangeListener focusListener = new View.OnFocusChangeListener() {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus){
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
        }
    };

    public void loginBtnTapped(View view) {


//        ThreadA threadA = new ThreadA();
//        threadA.go();

//        csiWCFMethods wcf = new csiWCFMethods();
//        wcf.LoginByEMail(emailText, passwordlText);
//        wcf.testing();

//        PrimeThread p = new PrimeThread(143);
//        p.start();
//
//
//        Intent intent = new Intent(this, SearchPageAC.class);
//        startActivity(intent);
        callLoginWCF();
    }

    public void callLoginWCF() {

        //hid the soft keyboard when leaving this page
//        try {
//            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


        final String emailText = email.getText().toString().trim();
        final String passwordlText = password.getText().toString().trim();
//        final String emailText = "itsupport@chemicalsafety.com.au";
//        final String passwordlText = "itsupport";
//        Log.d("email", emailText);
//        Log.d("password", passwordlText);
        final DialogFragment df = new DialogFragment();

        if(emailText.isEmpty() && passwordlText.isEmpty()) {
            df.callAlert(LoginPageAC.this, "Login input empty!\nPlease check your email address or password and try again.");
        } else if (emailText.isEmpty()) {
            df.callAlert(LoginPageAC.this, "Email address empty!\nPlease enter email address and try again.");
        } else if (passwordlText.isEmpty()) {
            df.callAlert(LoginPageAC.this, "Password empty!\nPlease enter password and try again.");
        } else {

            final csiWCF_VM wcf = new csiWCF_VM();
//            DialogFragment df = new DialogFragment();
            df.callloadingScreen(LoginPageAC.this, "Loading...");


            Thread t= new Thread(new Runnable() {

                public void run() {
                    if (wcf.MulitpleClientLogin(emailText,passwordlText,"" , loginVar.appointclient)) {


                        runOnUiThread(new Runnable() {
                            public void run() {
                                df.cancelLoadingScreen();


                                if (loginVar.passed.equals(true)) {
                                    df.cancelLoadingScreen();
                                    toSeachAC();


                                } else if (loginVar.passed.equals(false)) {

                                    if (loginVar.isgeneric.equals(true) && (loginVar.needchooseclient.equals(true))) {
                                        df.cancelLoadingScreen();
                                        toLoginMultipleClientAC();
                                    } else if (loginVar.isgeneric.equals(false) && (loginVar.needchooseclient.equals(true))) {
                                        df.cancelLoadingScreen();
                                        toLoginMultipleClientAC();
                                    } else if (loginVar.needpsw.equals(true)) {
                                        df.cancelLoadingScreen();
                                        toLoginpasswordAC();

                                    } else if (loginVar.isgeneric.equals(true) && loginVar.retIndexText.contains("OTA Code Sent")) {
                                        df.cancelLoadingScreen();
                                        toLoginOTAAC();
                                    } else {
                                    df.callAlert(LoginPageAC.this, "Login Failed!\n" + loginVar.retIndexText);
                                    }
                                }
                            }
                        });

                    } else {
                        Log.d("error", "login failed");

                        runOnUiThread(new Runnable() {
                            public void run() {
                                remBtn.setBackgroundResource(R.drawable.login_untickcheckbox);
                                UserInfoStoredFunction.setFalseStatus(LoginPageAC.this, loginVar.clientid);

                                password.setText("");

                                df.cancelLoadingScreen();
                                df.callAlert(LoginPageAC.this, "Login Failed!\nPlease check your email address or password and try again.");
                            }
                        });


                    }
                }
            });
            t.start();
        }

    }


    public void remBtnTapped(View v) {

        if(!UserInfoStoredFunction.getStatus(this, loginVar.clientid)) {

            remBtn.setBackgroundResource(R.drawable.login_tickedcheckbox);
            UserInfoStoredFunction.setTrueStatus(this, loginVar.clientid);

//            String emailText = email.getText().toString().trim();
            String passwordText = password.getText().toString().trim();

//            UserInfoStoredFunction.setEmail(this, emailText);
            UserInfoStoredFunction.setMultiPassword(this, passwordText);



        } else {
            remBtn.setBackgroundResource(R.drawable.login_untickcheckbox);
            UserInfoStoredFunction.setFalseStatus(this, loginVar.clientid);

//            UserInfoStoredFunction.setEmail(this, "");
            UserInfoStoredFunction.setMultiPassword(this, "");
        }


//        Log.i("email id:", UserInfoStoredFunction.getEmail(this));
//        Log.i("password:", UserInfoStoredFunction.getPassword(this));

    }

    public void setRememberValues() {
        remBtn.setBackgroundResource(R.drawable.login_tickedcheckbox);
//        email.setText(UserInfoStoredFunction.getEmail(this));
        password.setText(UserInfoStoredFunction.getMultiPassword(this));

//        if(!UserInfoStoredFunction.getLogo(this).isEmpty()) {
//            byte[] decodedString = Base64.decode(UserInfoStoredFunction.getLogo(this).getBytes(), Base64.DEFAULT);
//            Bitmap decodeByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//            loginLogo.setImageBitmap(decodeByte);
//        }


    }

    //hide keyboard when called
    public void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getRootView().getWindowToken(), 0);
    }

    //enable keyboard when called
    public void enableSoftKeyboard(Activity activity) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }


//    @Override
//    public void onBackPressed() {
//        // Do Here what ever you want do on back press;
//    }

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
}
