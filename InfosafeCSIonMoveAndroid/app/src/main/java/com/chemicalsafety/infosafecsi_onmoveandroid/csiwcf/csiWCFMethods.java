package com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf;

import android.util.Log;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.globalVar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


import java.io.InputStream;

public class csiWCFMethods {

    //String url = "http://www.csinfosafe.com/CSIMD_WCF/CSI_MD_Service.svc/";
    //String url = "http://192.168.1.22/CSIMD_WCF/CSI_MD_Service.svc/";
    String url = "http://192.168.1.22/CSIMD_WCF/CSI_MD_Service.svc/";

    public void Login(String email, String pw) {


        Log.i("login", "reached login1");
        JSONObject user = new JSONObject();

        HttpClient httpClient = new DefaultHttpClient();

        Log.i("login", "reached login2");
        HttpPost request = new HttpPost(url + "loginbyEmail");

        Log.i("login", "reached   " + url + "loginbyEmail");
//        request.setHeader("Accept", "application/json");
        Log.i("login", "reached login4");
        request.setHeader("Content-type", "application/json");
        Log.i("login", "reached login5");

        try {

            Log.i("login", "reached login6");
            user.put("email", email);
            user.put("password", pw);
            user.put("deviceid", "");
            user.put("devicemac", "");
            user.put("phoneno", "");
            user.put("devicename", "");
            user.put("devicemodel", "");
            user.put("deviceserialno", "");
            user.put("deviceSEID", "");
            user.put("deviceIMEI", "");
            user.put("deviceMEID", "");
            user.put("sourceip", "");

            System.out.println(user);



            Log.i("login", "reached login7");
            StringEntity entity = new StringEntity(user.toString());

            Log.i("login", "reached login8");
            request.setEntity(entity);

            Log.i("login", "reached login9");
            //DefaultHttpClient httpClient = new DefaultHttpClient();

            Log.i("login", "reached login10");
            HttpResponse response = httpClient.execute(request);

            Log.i("login", "reached login11");
            //HttpEntity responseEntityentity = response.getEntity();
            String respStr = EntityUtils.toString(response.getEntity());

            Log.i("login", "reached login12");
            System.out.println(respStr);

            JSONObject respJSON = new JSONObject(respStr);

            globalVar.clientid = respJSON.getString("clientid");
            globalVar.apptype = respJSON.getInt("apptype");
            globalVar.infosafeid = respJSON.getString("infosafeid");

            System.out.println(globalVar.apptype);
            System.out.println(globalVar.clientid);
            System.out.println(globalVar.infosafeid);
//            if (response != null) {
//                InputStream in = responseEntityentity.getContent();
//
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void testing() {
        HttpParams httpParams = new BasicHttpParams();
        ThreadSafeClientConnManager connMgr = new ThreadSafeClientConnManager(httpParams, new SchemeRegistry());
        HttpClient client = new DefaultHttpClient(connMgr, httpParams);

        try {
            HttpGet get = new HttpGet(url + "loginbyEmail");
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
