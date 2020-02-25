package com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf;

import android.util.Log;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.loginVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.searchVar;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.chemicalsafety.infosafecsi_onmoveandroid.Entities.loginVar.clientid;

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

            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
