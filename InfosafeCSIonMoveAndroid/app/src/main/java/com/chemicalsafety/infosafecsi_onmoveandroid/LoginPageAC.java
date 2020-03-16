package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.UserInfoStoredFunction;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.loginVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf.csiWCFMethods;
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

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

//        Log.i("email id:", UserInfoStoredFunction.getEmail(this));

        if (UserInfoStoredFunction.getStatus(this)) {
//            Log.i("logo", UserInfoStoredFunction.getLogo(this));
            setRememberValues();
        } else {
            loginLogo.setImageResource(R.drawable.csi_logo);
        }
    }


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
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        } catch (Exception e) {
            e.printStackTrace();
        }


        final String emailText = email.getText().toString();
        final String passwordlText = password.getText().toString();

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
            df.callloadingScreen(LoginPageAC.this);

            Thread t= new Thread(new Runnable() {

                public void run() {
                    if (wcf.Login(emailText,passwordlText )) {

//                        runOnUiThread(new Runnable() {
//                            @Override
                        runOnUiThread(new Runnable() {
                            public void run() {
                                df.cancelLoadingScreen();
                                if (!loginVar.clientlogo.isEmpty()) {
                                    UserInfoStoredFunction.setLogo(LoginPageAC.this, loginVar.clientlogo);
                                }

                                toSeachAC();
                            }
                        });

                    } else {
                        Log.d("error", "login failed");

                        runOnUiThread(new Runnable() {
                            public void run() {
                                remBtn.setBackgroundResource(R.drawable.login_untickcheckbox);
                                UserInfoStoredFunction.setFalseStatus(LoginPageAC.this);

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

    public void toSeachAC() {

        Intent intent = new Intent(this, SearchPageAC.class);
        startActivity(intent);
    }

    public void remBtnTapped(View v) {

        if(!UserInfoStoredFunction.getStatus(this)) {

            remBtn.setBackgroundResource(R.drawable.login_tickedcheckbox);
            UserInfoStoredFunction.setTrueStatus(this);

            String emailText = email.getText().toString();
            String passwordText = password.getText().toString();

            UserInfoStoredFunction.setEmail(this, emailText);
            UserInfoStoredFunction.setPassword(this, passwordText);



        } else {
            remBtn.setBackgroundResource(R.drawable.login_untickcheckbox);
            UserInfoStoredFunction.setFalseStatus(this);

            UserInfoStoredFunction.setEmail(this, "");
            UserInfoStoredFunction.setPassword(this, "");
        }


//        Log.i("email id:", UserInfoStoredFunction.getEmail(this));
//        Log.i("password:", UserInfoStoredFunction.getPassword(this));

    }

    public void setRememberValues() {
        remBtn.setBackgroundResource(R.drawable.login_tickedcheckbox);
        email.setText(UserInfoStoredFunction.getEmail(this));
        password.setText(UserInfoStoredFunction.getPassword(this));

        if(!UserInfoStoredFunction.getLogo(this).isEmpty()) {
            byte[] decodedString = Base64.decode(UserInfoStoredFunction.getLogo(this).getBytes(), Base64.DEFAULT);
            Bitmap decodeByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            loginLogo.setImageBitmap(decodeByte);
        }


    }

    public class ThreadA {
        public void go() {
//            Log.d("reach", "1");
            PrimeThread p = new PrimeThread(143);
            p.start();
            synchronized (p) {
                try {
                    p.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                Log.d("reach", "3");
                toSeachAC();
            }
        }

    }

    class PrimeThread extends Thread {
        long minPrime;
        PrimeThread(long minPrime) {
            this.minPrime = minPrime;
        }

        public void run() {
//            Log.d("reach", "2");
            csiWCFMethods wcf = new csiWCFMethods();
            wcf.LoginByEMail("a","a" );
        }
    }


}
