package com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf;


import android.util.Log;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.criteriaList;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.loginVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.searchItemList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import static com.chemicalsafety.infosafecsi_onmoveandroid.Entities.loginVar.clientid;
import static com.chemicalsafety.infosafecsi_onmoveandroid.Entities.searchItemList.tableList;


public class csiWCFMethods {

    //private String url = "http://www.csinfosafe.com/CSIMD_WCF/CSI_MD_Service.svc/";
    String url = "http://192.168.1.22/CSIMD_WCF/CSI_MD_Service.svc/";



    public Boolean Login(String email, String pw) {

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

            System.out.println(user);

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

//            Log.i("response", ""+response.toString());
            HttpEntity entity1 = response.getEntity();
            final String responseText = EntityUtils.toString(entity1);

            JSONObject respJSON = new JSONObject(responseText);

            loginVar.clientid = respJSON.getString("clientid");
            loginVar.apptype = respJSON.getInt("apptype");
            loginVar.clientcode = respJSON.getString("clientcode");
            loginVar.passed = respJSON.getString("passed");
            loginVar.infosafeid = respJSON.getString("infosafeid");


            System.out.println(loginVar.apptype);
            System.out.println(loginVar.clientid);
            System.out.println(loginVar.clientcode);
            System.out.println(loginVar.infosafeid);
            System.out.println(loginVar.passed);

            //responseText;
            Log.i("Output", responseText);

            if (clientid != null && !clientid.isEmpty() && clientid != "null") {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public void SearchCriteriaList() {
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

            Log.i("Output", responseText);

//            return true;
        } catch (Exception e) {
            e.printStackTrace();
//            return false;
        }
    }

    public void SearchReturnList() {

        //create variables
        JSONObject passV = new JSONObject();
        JSONArray emptyArray = new JSONArray();
        JSONArray advArray = new JSONArray();

        String advanced;
        String type = "2";
        String singleValue = null;

        try {
            //create JSON send values
            passV.put("client", clientid);
            passV.put("uid", loginVar.infosafeid);
            passV.put("apptp", loginVar.apptype);
            passV.put("p", "1");
            passV.put("psize", "50");

            //product name search bar check
            if (criteriaList.pnameInput != null && !criteriaList.pnameInput.isEmpty()) {

               String pnameStr = criteriaList.pnameInput.trim();
               singleValue = "0" + pnameStr;
               type = "2";
               System.out.println(singleValue);

               JSONObject pnameJson = new JSONObject();
               JSONArray pnArray = new JSONArray();

                pnArray.put(singleValue);

               pnameJson.put("type", type);
               pnameJson.put("isgroup", "0");
               pnameJson.put("groups", emptyArray);
               pnameJson.put("values", pnArray);

               advArray.put(pnameJson);
            }

            //supplier search bar check
            if (criteriaList.supplierInput != null && !criteriaList.supplierInput.isEmpty()) {

                String supStr = criteriaList.supplierInput.trim();
                singleValue = "0" + supStr;
                type = "4";
                System.out.println(singleValue);

                JSONObject supJSON = new JSONObject();
                JSONArray supArray = new JSONArray();

                supArray.put(singleValue);

                supJSON.put("type", type);
                supJSON.put("isgroup", "0");
                supJSON.put("groups", emptyArray);
                supJSON.put("values", supArray);

                advArray.put(supJSON);
            }

            //product code search bar check
            if (criteriaList.pcodeInput != null && !criteriaList.pcodeInput.isEmpty()) {

                String pcodeStr = criteriaList.pcodeInput.trim();
                singleValue = "0" + pcodeStr;
                type = "8";
                System.out.println(singleValue);

                JSONObject pcJSON = new JSONObject();
                JSONArray pcArray = new JSONArray();

                pcArray.put(singleValue);

                pcJSON.put("type", type);
                pcJSON.put("isgroup", "0");
                pcJSON.put("groups", emptyArray);
                pcJSON.put("values", pcArray);

                advArray.put(pcJSON);
            }

            //check the if it is multi or single search
            if (advArray.length() > 1) {
                advanced = "1";
            } else {
                advanced = "";
                if (singleValue != null && !singleValue.isEmpty()) {
                    singleValue = singleValue.substring(1);
                }
            }

            passV.put("c", type);
            passV.put("v", singleValue);
            passV.put("advanced", advanced);
            passV.put("advancedsitetype", "3");
            passV.put("advanceditems", advArray);

//            System.out.println(passV);

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

            Log.i("Output", responseText);

            JSONObject respJSON = new JSONObject(responseText);

            JSONArray dataArray;

            //get the values from data
            dataArray = respJSON.getJSONArray("data");

            //store each item
            for (int i = 0; i < dataArray.length(); i++) {

                JSONObject item = dataArray.getJSONObject(i);

//                JSONObject sdsno1 = item.getJSONObject("sdsno");
//                String sdsno2 = sdsno1.getString("value");

                JSONObject pname1 = item.getJSONObject("name");
                String pname2 = pname1.getString("value");

                JSONObject com1 = item.getJSONObject("com");
                String com2 = com1.getString("value");

                JSONObject date1 = item.getJSONObject("issue");
                String date2 = date1.getString("value");

                JSONObject key1 = item.getJSONObject("key");
                String key2 = key1.getString("value");

                JSONObject unno1 = item.getJSONObject("unno");
                String unno2 = unno1.getString("value");

                JSONObject code1 = item.getJSONObject("code");
                String code2 = code1.getString("value");

                String coun1 = item.getString("scountry");
                String pitgs = item.getString("sdsghspic");

                //fix and match the pitgrams
                String[] imgs = pitgs.split(",");
                int num = imgs.length;
                String[] imgsCode = null;
//                System.out.println(pitgs);
//                System.out.println(num);
//                System.out.println(imgs[0]);
//                System.out.println(imgs[1]);

                for (int n = 0; n < num; n++) {
                    if (imgs[i].toLowerCase() == "flame") {
                        String imgCode = "ghs02";
                        imgsCode[i] = imgCode;
                    } else if (imgs[i].toLowerCase() == "skull and crossbones") {
                        String imgCode = "ghs06";
                        imgsCode[i] = imgCode;
                    } else if (imgs[i].toLowerCase() == "flame over circle") {
                        String imgCode = "ghs03";
                        imgsCode[i] = imgCode;
                    } else if (imgs[i].toLowerCase() == "exclamation mark") {
                        String imgCode = "ghs07";
                        imgsCode[i] = imgCode;
                    } else if (imgs[i].toLowerCase() == "environment") {
                        String imgCode = "ghs09";
                        imgsCode[i] = imgCode;
                    } else if (imgs[i].toLowerCase() == "health hazard") {
                        String imgCode = "ghs08";
                        imgsCode[i] = imgCode;
                    } else if (imgs[i].toLowerCase() == "corrosion") {
                        String imgCode = "ghs05";
                        imgsCode[i] = imgCode;
                    } else if (imgs[i].toLowerCase() == "gas cylinder") {
                        String imgCode = "ghs04";
                        imgsCode[i] = imgCode;
                    } else if (imgs[i].toLowerCase() == "exploding bomb") {
                        String imgCode = "ghs01";
                        imgsCode[i] = imgCode;
                    } else {
                        imgsCode[i] = "";
                    }

                    System.out.println(imgsCode[i]);
                }



                searchItemList.tableList.add(new searchItemList(com2, date2, pname2, unno2, code2, pitgs, coun1, key2, imgsCode[0], imgsCode[1], imgsCode[2], imgsCode[3], imgsCode[4]));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
