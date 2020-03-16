package com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf;


import android.util.Log;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.loginVar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


public class csiWCFMethods {

    //private String url = "http://www.csinfosafe.com/CSIMD_WCF/CSI_MD_Service.svc/";
    String url = "http://192.168.1.22/CSIMD_WCF/CSI_MD_Service.svc/";



    public String LoginByEMail(String email, String pw) {

        //crate json object
        JSONObject user = new JSONObject();

        try {
            // put value in json object
           // user.put("email", "shawn.samuel@chemicalsafety.com.au");
            user.put("email", email);
            //user.put("password", "#PEPSimax");
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

//            System.out.println(user);

            String urlFinal = url + "LoginByEMail";

            //create post method
            HttpPost postMethod = new HttpPost(urlFinal.trim());

            //set header for post method
            postMethod.setHeader("Accept", "application/json");
            postMethod.setHeader("Content-type", "application/json");

            //add json object in post method
            StringEntity entity = new StringEntity(user.toString(), HTTP.UTF_8);
            postMethod.setEntity(entity);

            //create session to request
            HttpClient hc = new DefaultHttpClient();

            //get response from wcf service
            HttpResponse response = hc.execute(postMethod);

            HttpEntity entity1 = response.getEntity();
            final String responseText = EntityUtils.toString(entity1);

            JSONObject respJSON = new JSONObject(responseText);


            //responseText;
//            Log.i("Output", responseText);

            return responseText;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String SearchCriteriaList() {
        JSONObject id = new JSONObject();

        try {
            id.put("ClientCode", loginVar.clientid);
            id.put("AppType", loginVar.apptype);
            id.put("UserID", loginVar.infosafeid);

            System.out.println(id);

            String urlFinal = url + "GetSearchCriteriaList";

            HttpPost postMethod = new HttpPost(urlFinal.trim());

            postMethod.setHeader("Accept", "application/json");
            postMethod.setHeader("Content-type", "application/json");

            StringEntity entity = new StringEntity(id.toString(), HTTP.UTF_8);
            postMethod.setEntity(entity);

            HttpClient hc = new DefaultHttpClient();

            HttpResponse response = hc.execute(postMethod);

            HttpEntity entity1 = response.getEntity();
            final String responseText = EntityUtils.toString(entity1);

//            Log.i("Output", responseText);

            return responseText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String SearchReturnList(JSONObject passV) {

        try {

            //create JSON type and send request to WCF
            String urlFinal = url + "GetSDSSearchResultsPage";

            HttpPost postMethod = new HttpPost(urlFinal.trim());

            postMethod.setHeader("Accept", "application/json");
            postMethod.setHeader("Content-type", "application/json");

            StringEntity entity = new StringEntity(passV.toString(), HTTP.UTF_8);
            postMethod.setEntity(entity);

            HttpClient hc = new DefaultHttpClient();

            HttpResponse response = hc.execute(postMethod);

            HttpEntity entity1 = response.getEntity();
            final String responseText = EntityUtils.toString(entity1);

//            Log.i("Output", responseText);
            return responseText;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String ViewSDS_Classification(JSONObject passV) {

        try {
            String urlFinal = url + "ViewSDS_Classification";

            HttpPost postMethod = new HttpPost(urlFinal.trim());

            postMethod.setHeader("Accept", "application/json");
            postMethod.setHeader("Content-type", "application/json");

            StringEntity entity = new StringEntity(passV.toString(), HTTP.UTF_8);
            postMethod.setEntity(entity);

            HttpClient hc = new DefaultHttpClient();

            HttpResponse response = hc.execute(postMethod);

            HttpEntity entity1 = response.getEntity();
            final String responseText = EntityUtils.toString(entity1);

//            Log.i("Output classification:", responseText);

            return responseText;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String ViewSDS_Transport(JSONObject passV) {
        try {
            String urlFinal = url + "ViewSDS_Transport";

            HttpPost postMethod = new HttpPost(urlFinal.trim());

            postMethod.setHeader("Accept", "application/json");
            postMethod.setHeader("Content-type", "application/json");

            StringEntity entity = new StringEntity(passV.toString(), HTTP.UTF_8);
            postMethod.setEntity(entity);

            HttpClient hc = new DefaultHttpClient();

            HttpResponse response = hc.execute(postMethod);

            HttpEntity entity1 = response.getEntity();
            final String responseText = EntityUtils.toString(entity1);

//            Log.i("Output TI Information:", responseText);

            return responseText;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String ViewSDS(JSONObject passV) {
        try {
            String urlFinal = url + "ViewSDS";

            HttpPost postMethod = new HttpPost(urlFinal.trim());

            postMethod.setHeader("Accept", "application/json");
            postMethod.setHeader("Content-type", "application/json");

            StringEntity entity = new StringEntity(passV.toString(), HTTP.UTF_8);
            postMethod.setEntity(entity);

            HttpClient hc = new DefaultHttpClient();

            HttpResponse response = hc.execute(postMethod);

            HttpEntity entity1 = response.getEntity();
            final String responseText = EntityUtils.toString(entity1);

//            Log.i("Output ViewSDS:", responseText);

            return responseText;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String ViewSDS_FirstAid(JSONObject passV) {
        try {
            String urlFinal = url + "ViewSDS_FirstAid";

            HttpPost postMethod = new HttpPost(urlFinal.trim());

            postMethod.setHeader("Accept", "application/json");
            postMethod.setHeader("Content-type", "application/json");

            StringEntity entity = new StringEntity(passV.toString(), HTTP.UTF_8);
            postMethod.setEntity(entity);

            HttpClient hc = new DefaultHttpClient();

            HttpResponse response = hc.execute(postMethod);

            HttpEntity entity1 = response.getEntity();
            final String responseText = EntityUtils.toString(entity1);

//            Log.i("Output ViewSDS_FirstAid:", responseText);

            return responseText;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
