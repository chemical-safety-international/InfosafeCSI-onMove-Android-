package com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf;

import android.util.Log;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.SearchTableItem;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.loginVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.previewVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.searchItemList;
import com.chemicalsafety.infosafecsi_onmoveandroid.SearchTableAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;


public class csiWCF_VM {

    public Boolean Login(String email, String pw) {

        try{
            csiWCFMethods wcf = new csiWCFMethods();
            String responseText = wcf.LoginByEMail(email,pw);
//            Log.i("Output", responseText);
            JSONObject respJSON = new JSONObject(responseText);
//            System.out.println(respJSON);
            if (!responseText.isEmpty()) {
                try {
                    loginVar.clientid = respJSON.getString("clientid");
                    loginVar.apptype = respJSON.getInt("apptype");
                    loginVar.clientcode = respJSON.getString("clientcode");
                    loginVar.passed = respJSON.getString("passed");
                    loginVar.infosafeid = respJSON.getString("infosafeid");

                    if(loginVar.clientid != "null" && !loginVar.clientid.isEmpty() && loginVar.apptype == 1) {
                        return true;
                    } else {
                        return false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }

            } else {
                Log.d("error", "login failed");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public Boolean Search(String pnameInput, String pcodeInput, String supplierInput, String clientid, String uid, int apptp) {

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
            passV.put("uid", uid);
            passV.put("apptp", apptp);
            passV.put("p", "1");
            passV.put("psize", "50");

            //product name search bar check
            if (pnameInput != null && !pnameInput.isEmpty()) {

                String pnameStr = pnameInput.trim();
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
            if (supplierInput != null && !supplierInput.isEmpty()) {

                String supStr = supplierInput.trim();
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
            if (pcodeInput != null && !pcodeInput.isEmpty()) {

                String pcodeStr = pcodeInput.trim();
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

            csiWCFMethods wcf = new csiWCFMethods();
            String responseText = wcf.SearchReturnList(passV);

//            Log.i("output", responseText);

            JSONObject respJSON = new JSONObject(responseText);
            JSONArray dataArray;

            //get the values from data
            dataArray = respJSON.getJSONArray("data");

            String[] sdsnoArray1 = new String[dataArray.length()];

            searchItemList.tableList.clear();
            searchItemList.tableList.removeAll(searchItemList.tableList);

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


                //get sdsno and stored
                sdsnoArray1[i] = key2;
                System.out.println(sdsnoArray1[i]);

                //fix and match the pitgrams

                if (pitgs.contains(",")) {
                    String[] imgs = pitgs.split(",");

                    int num = imgs.length;
                    String[] imgsCode = new String[num];

                    for (int n = 0; n < num; n++) {

                        if (imgs[n].toLowerCase().trim().equals("flame")) {
                            String imgCode = "ghs02";
                            imgsCode[n] = imgCode;
                        } else if (imgs[n].toLowerCase().trim().equals("skull and crossbones")) {
                            String imgCode = "ghs06";
                            imgsCode[n] = imgCode;
                        } else if (imgs[n].toLowerCase().trim().equals("flame over circle")) {
                            String imgCode = "ghs03";
                            imgsCode[n] = imgCode;
                        } else if (imgs[n].toLowerCase().trim().equals("exclamation mark")) {
                            String imgCode = "ghs07";
//                            int imgCode = R.drawable.ghs07;
                            imgsCode[n] = imgCode;
                        } else if (imgs[n].toLowerCase().trim().equals("environment")) {
                            String imgCode = "ghs09";
                            imgsCode[n] = imgCode;
                        } else if (imgs[n].toLowerCase().trim().equals("health hazard")) {
                            String imgCode = "ghs08";
                            imgsCode[n] = imgCode;
                        } else if (imgs[n].toLowerCase().trim().equals("corrosion")) {
                            String imgCode = "ghs05";
                            imgsCode[n] = imgCode;
                        } else if (imgs[n].toLowerCase().trim().equals("gas cylinder")) {
                            String imgCode = "ghs04";
                            imgsCode[n] = imgCode;
                        } else if (imgs[n].toLowerCase().trim().equals("exploding bomb")) {
                            String imgCode = "ghs01";
                            imgsCode[n] = imgCode;
                        } else {
                            imgsCode[n] = "";
                        }
//                        Log.i("imgsCode", imgsCode[n]);

                    }

                    if (num == 2) {
                        searchItemList.tableList.add(new searchItemList(com2, date2, pname2, unno2, code2, pitgs, coun1, key2, imgsCode[0], imgsCode[1], "", "", ""));

                    } else if (num == 3) {
                        searchItemList.tableList.add(new searchItemList(com2, date2, pname2, unno2, code2, pitgs, coun1, key2, imgsCode[0], imgsCode[1], imgsCode[2], "", ""));

                    } else if (num == 4) {
                        searchItemList.tableList.add(new searchItemList(com2, date2, pname2, unno2, code2, pitgs, coun1, key2, imgsCode[0], imgsCode[1], imgsCode[2], imgsCode[3], ""));

                    } else if (num == 5) {
                        searchItemList.tableList.add(new searchItemList(com2, date2, pname2, unno2, code2, pitgs, coun1, key2, imgsCode[0], imgsCode[1], imgsCode[2], imgsCode[3], imgsCode[4]));

                    }

                } else {

                    searchItemList.tableList.add(new searchItemList(com2, date2, pname2, unno2, code2, pitgs, coun1, key2, pitgs.trim().toLowerCase(), "", "", "", ""));

                }

            }

            if (searchItemList.tableList.size() == 0 || searchItemList.tableList.isEmpty()) {

                return false;
            } else {
                searchItemList.sdsnoArray = Arrays.copyOf(sdsnoArray1,sdsnoArray1.length);
                return true;
            }

        }catch (Exception e) {

            e.printStackTrace();
            return false;
        }

    }

    public Boolean Preview(String clientid, String uid, String sdsno, int apptp, String rtype) {

        System.out.println(searchItemList.tableList);

        try {
            JSONObject passV = new JSONObject();
            passV.put("client", clientid);
            passV.put("apptp", apptp);
            passV.put("sds", sdsno);
            passV.put("rtype", rtype);
            passV.put("regetFormat", "1");
            passV.put("f", "");
            passV.put("subf","");
            passV.put("uid", uid);

            csiWCFMethods wcf = new csiWCFMethods();
            String responseText = wcf.ViewSDS_Classification(passV);
            Log.i("Output classification:", responseText);

            JSONObject respJSON = new JSONObject(responseText);

            String formatcode = respJSON.getString("formatcode");
            previewVar.formatcode = formatcode;



            String classification = respJSON.getString("classification");
            previewVar.classification = classification;

            String dg = respJSON.getString("dg");
            previewVar.dg = dg;



            String hstate = respJSON.getString("hstate");
            previewVar.hstate = hstate;

            String ps = respJSON.getString("ps");
            previewVar.ps = ps;

//            String pstate = respJSON.getString("psatate");
//            previewVar.pstate = pstate;

            String pic = respJSON.getString("pic");
            previewVar.pic = pic;

            String rphase = respJSON.getString("rphrase");
            previewVar.rphrase = rphase;

            String sds = respJSON.getString("sds");
            previewVar.sds = sds;

            String sphrase = respJSON.getString("sphrase");
            previewVar.sphrase = sphrase;

            String ps_general = respJSON.getString("ps_general");
            previewVar.ps_general = ps_general;

            String ps_response = respJSON.getString("ps_response");
            previewVar.ps_response = ps_response;

            String ps_prevention = respJSON.getString("ps_prevention");
            previewVar.ps_prevention = ps_prevention;


            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
