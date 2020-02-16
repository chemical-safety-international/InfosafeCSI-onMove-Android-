package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;


import android.icu.text.StringPrepParseException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.net.HttpRetryException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.zip.Deflater;


public class LoginActivity extends AppCompatActivity {

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
    }

    public void loginBtnTapped(View v) {
    check();
    Login2();




    }

    private void check() {
        String emailText = email.getText().toString();
        String passwordlText = password.getText().toString();

        Log.d("email", emailText);
        Log.d("password", passwordlText);
    }

//    private void Login() {
//        HttpClient httpClient = new DefaultHttpClient();
//        String url = "http://www.csinfosafe.com/CSIMD_WCF/CSI_MD_Service.svc/";
//
//
//        try {
//            URI uri = new URI(url);
//
//            HttpGet method = new HttpGet(uri);
//
//            StringEntity json = new StringEntity("email":"shawn.samuel@chemicalsafety.com.au");
//
//            HttpResponse response = httpClient.execute(method);
//
//            if (response != null) {
//                Log.i("login", "received" + getResponse(response.getEntity()));
//            } else {
//                Log.i("login", "Got no return");
//            }
//
//
//        } catch (IOException e) {
//            Log.e("error", e.getMessage());
//        } catch (URISyntaxException e) {
//            Log.e("error", e.getMessage());
//        }
//    }

    private void Login1() {
        Log.i("login", "reached login1");
        try {
            HttpPost request = new HttpPost("http://www.csinfosafe.com/CSIMD_WCF/CSI_MD_Service.svc/");
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");

            JSONStringer json = new JSONStringer()
                    .object()
                        .key("email").value("shawn.samuel@chemicalsafety.com.au")
                        .endObject()
                    .object()
                        .key("password").value("#PEPSimax")
                        .endObject();

            StringEntity entity = new StringEntity(json.toString(), "UTF-8");
            entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            entity.setContentType("application/json");

            request.setEntity(entity);

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse response = httpClient.execute(request);

            //recieve
//            HttpEntity responseEntity = response.getEntity();
//            String value = EntityUtils.toString(responseEntity);

//            char[] buffer = new char[(int)responseEntity.getContentLength()];
//            InputStream stream = responseEntity.getContent();
//            InputSreamReader reader = new InputStreamReader(stream);
//            reader.read(buffer);


//            Log.i("Return", value);

        } catch (JSONException e) {
            Log.e("error", "unexpected Json exception", e);
        } catch (UnsupportedEncodingException e) {
            Log.e("error", "Entity created failed", e);
        } catch (IOException e) {
            Log.e("error", "Execute failed", e);
        }
    }

    private void Login2() {

        HttpPost request = new HttpPost("http://www.csinfosafe.com/CSIMD_WCF/CSI_MD_Service.svc/");
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");

        try {
            JSONStringer user = new JSONStringer()
                    .object()
                        .key("email").value("shawnsamuel@chemicalsafety.com.au")
                    .endObject();

            StringEntity entity = new StringEntity(user.toString());

            Toast.makeText(this, user.toString() + "\n", Toast.LENGTH_LONG).show();

            request.setEntity(entity);

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse response = httpClient.execute(request);

            Toast.makeText(this, response.getStatusLine() + "\n", Toast.LENGTH_SHORT).show();



        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }

    }


}
