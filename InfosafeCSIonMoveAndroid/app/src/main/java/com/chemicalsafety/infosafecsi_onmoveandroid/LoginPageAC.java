package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf.csiWCFMethods;
import com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf.csiWCF_VM;


public class LoginPageAC extends AppCompatActivity {

    private EditText email;
    private EditText password;

    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.loginID);
        password = findViewById(R.id.loginPW);
        loginButton = findViewById(R.id.loginButton);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
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

        //hid the soft keyboard
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String emailText = email.getText().toString();
        String passwordlText = password.getText().toString();

        Log.d("email", emailText);
        Log.d("password", passwordlText);

        csiWCF_VM wcf = new csiWCF_VM();
        if (wcf.Login(emailText,passwordlText ) == true) {
            toSeachAC();
        } else {
            Log.d("error", "login failed");
        }
    }

    public void toSeachAC() {

        Intent intent = new Intent(this, SearchPageAC.class);
        startActivity(intent);
    }

    public class ThreadA {
        public void go() {
            Log.d("reach", "1");
            PrimeThread p = new PrimeThread(143);
            p.start();
            synchronized (p) {
                try {
                    p.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("reach", "3");
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
            Log.d("reach", "2");
            csiWCFMethods wcf = new csiWCFMethods();
            wcf.LoginByEMail("a","a" );
        }
    }

}
