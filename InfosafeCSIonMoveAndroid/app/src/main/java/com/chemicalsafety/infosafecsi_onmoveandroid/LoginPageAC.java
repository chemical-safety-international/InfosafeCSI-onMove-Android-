package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.os.AsyncTask;
import android.widget.Toast;
import android.telephony.mbms.DownloadStatusListener;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf.csiWCFMethods;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.HttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;



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
//        wcf.Login(emailText, passwordlText);
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

        String emailText = email.getText().toString();
        String passwordlText = password.getText().toString();

        Log.d("email", emailText);
        Log.d("password", passwordlText);

        csiWCFMethods wcf = new csiWCFMethods();
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
            wcf.Login("a","a" );
        }
    }

}
