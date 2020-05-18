package com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


class csiWCFMethods {

    //private String url = "http://www.csinfosafe.com/CSIMD_WCF/CSI_MD_Service.svc/";
//    private String url = "http://192.168.1.22/CSIMD_WCF/CSI_MD_Service.svc/";
    private String url = "https://192.168.1.22:4438/CSIMD_WCF/CSI_MD_Service.svc/";



    String LoginByEMail(String email, String pw) {

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

//            final String responseText = EntityUtils.toString(entity1);

//            JSONObject respJSON = new JSONObject(responseText);


            //responseText;
//            Log.i("Login Output", responseText);

            return EntityUtils.toString(entity1);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //LoginBtEMail for https WCF service
    String LoginByEMail_https(String email, String pw) {

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


            URL url1;
            DataOutputStream output;
            DataInputStream input;

            trustAllHosts();
//             Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);


//            String url2 = URLEncoder.encode(url + "LoginByEMail", "UTF-8");

            url1 = new URL(url + "LoginByEMail");
//            url1 = new URL(url2);
            HttpsURLConnection connection = (HttpsURLConnection) url1.openConnection();
//            connection.setSSLSocketFactory(trustCert().getSocketFactory());

            connection.setHostnameVerifier(DO_NOT_VERIFY);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
//            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.setUseCaches(false);



            output = new DataOutputStream(connection.getOutputStream());
            output.writeBytes(user.toString());

//            Log.i("Output", output.toString());


            output.flush();
            output.close();

            input = new DataInputStream(connection.getInputStream());
//            Log.i("input", input.toString());

            StringBuffer inputLine = new StringBuffer();
            String tmp;
            while ((tmp = input.readLine()) != null) {
                inputLine.append(tmp);
//                System.out.println(tmp);
            }
            //use inputLine.toString(); here it would have whole source
            input.close();

//            return EntityUtils.toString(entity1);
            return inputLine.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

//    String SearchCriteriaList() {
//        JSONObject id = new JSONObject();
//
//        try {
//            id.put("ClientCode", loginVar.clientid);
//            id.put("AppType", loginVar.apptype);
//            id.put("UserID", loginVar.infosafeid);
//
//            System.out.println(id);
//
//            String urlFinal = url + "GetSearchCriteriaList";
//
//            HttpPost postMethod = new HttpPost(urlFinal.trim());
//
//            postMethod.setHeader("Accept", "application/json");
//            postMethod.setHeader("Content-type", "application/json");
//
//            StringEntity entity = new StringEntity(id.toString(), HTTP.UTF_8);
//            postMethod.setEntity(entity);
//
//            HttpClient hc = new DefaultHttpClient();
//
//            HttpResponse response = hc.execute(postMethod);
//
//            HttpEntity entity1 = response.getEntity();
//            final String responseText = EntityUtils.toString(entity1);
//
////            Log.i("Search Output", responseText);
//
//            return responseText;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    String SearchReturnList(JSONObject passV) {

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
//            final String responseText = EntityUtils.toString(entity1);

//            Log.i("Output", responseText);
            return EntityUtils.toString(entity1);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //SearchReturnList for https WCF service
    String SearchReturnList_https(JSONObject passV) {

        try {

            URL url1;
            DataOutputStream output;
            DataInputStream input;

            trustAllHosts();
//             Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);


//            String url2 = URLEncoder.encode(url + "LoginByEMail", "UTF-8");

            url1 = new URL(url + "GetSDSSearchResultsPage");
//            url1 = new URL(url2);
            HttpsURLConnection connection = (HttpsURLConnection) url1.openConnection();
//            connection.setSSLSocketFactory(trustCert().getSocketFactory());

            connection.setHostnameVerifier(DO_NOT_VERIFY);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
//            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.setUseCaches(false);



            output = new DataOutputStream(connection.getOutputStream());
            output.writeBytes(passV.toString());

            output.flush();
            output.close();

            input = new DataInputStream(connection.getInputStream());

            StringBuffer inputLine = new StringBuffer();
            String tmp;
            while ((tmp = input.readLine()) != null) {
                inputLine.append(tmp);
//                System.out.println(tmp);
            }
            //use inputLine.toString(); here it would have whole source
            input.close();

//            return EntityUtils.toString(entity1);
            return inputLine.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    String ViewSDS_Classification(JSONObject passV) {

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
//            final String responseText = EntityUtils.toString(entity1);

//            Log.i("Output classification:", responseText);

            return EntityUtils.toString(entity1);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //ViewSDS_Classification for https WCF service
    String ViewSDS_Classification_https(JSONObject passV) {

        try {

            URL url1;
            DataOutputStream output;
            DataInputStream input;

            trustAllHosts();
//             Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);


//            String url2 = URLEncoder.encode(url + "LoginByEMail", "UTF-8");

            url1 = new URL(url + "ViewSDS_Classification");
//            url1 = new URL(url2);
            HttpsURLConnection connection = (HttpsURLConnection) url1.openConnection();
//            connection.setSSLSocketFactory(trustCert().getSocketFactory());

            connection.setHostnameVerifier(DO_NOT_VERIFY);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
//            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.setUseCaches(false);



            output = new DataOutputStream(connection.getOutputStream());
            output.writeBytes(passV.toString());

            output.flush();
            output.close();

            input = new DataInputStream(connection.getInputStream());

            StringBuffer inputLine = new StringBuffer();
            String tmp;
            while ((tmp = input.readLine()) != null) {
                inputLine.append(tmp);
//                System.out.println(tmp);
            }
            //use inputLine.toString(); here it would have whole source
            input.close();

//            return EntityUtils.toString(entity1);
            return inputLine.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    String ViewSDS_Transport(JSONObject passV) {
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
//            final String responseText = EntityUtils.toString(entity1);

//            Log.i("Output TI Information:", responseText);

            return EntityUtils.toString(entity1);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //ViewSDS_Transport for https WCF service
    String ViewSDS_Transport_https(JSONObject passV) {
        try {

            URL url1;
            DataOutputStream output;
            DataInputStream input;

            trustAllHosts();
//             Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);


//            String url2 = URLEncoder.encode(url + "LoginByEMail", "UTF-8");

            url1 = new URL(url + "ViewSDS_Transport");
//            url1 = new URL(url2);
            HttpsURLConnection connection = (HttpsURLConnection) url1.openConnection();
//            connection.setSSLSocketFactory(trustCert().getSocketFactory());

            connection.setHostnameVerifier(DO_NOT_VERIFY);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
//            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.setUseCaches(false);



            output = new DataOutputStream(connection.getOutputStream());
            output.writeBytes(passV.toString());

            output.flush();
            output.close();

            input = new DataInputStream(connection.getInputStream());

            StringBuffer inputLine = new StringBuffer();
            String tmp;
            while ((tmp = input.readLine()) != null) {
                inputLine.append(tmp);
//                System.out.println(tmp);
            }
            //use inputLine.toString(); here it would have whole source
            input.close();

//            return EntityUtils.toString(entity1);
            return inputLine.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    String ViewSDS(JSONObject passV) {
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
//            final String responseText = EntityUtils.toString(entity1);

//            Log.i("Output ViewSDS:", responseText);

            return EntityUtils.toString(entity1);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //ViewSDS for https WCF service
    String ViewSDS_https(JSONObject passV) {
        try {

            URL url1;
            DataOutputStream output;
            DataInputStream input;

            trustAllHosts();
//             Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);


//            String url2 = URLEncoder.encode(url + "LoginByEMail", "UTF-8");

            url1 = new URL(url + "ViewSDS");
//            url1 = new URL(url2);
            HttpsURLConnection connection = (HttpsURLConnection) url1.openConnection();
//            connection.setSSLSocketFactory(trustCert().getSocketFactory());

            connection.setHostnameVerifier(DO_NOT_VERIFY);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
//            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.setUseCaches(false);



            output = new DataOutputStream(connection.getOutputStream());
            output.writeBytes(passV.toString());

            output.flush();
            output.close();

            input = new DataInputStream(connection.getInputStream());

            StringBuffer inputLine = new StringBuffer();
            String tmp;
            while ((tmp = input.readLine()) != null) {
                inputLine.append(tmp);
//                System.out.println(tmp);
            }
            //use inputLine.toString(); here it would have whole source
            input.close();

//            return EntityUtils.toString(entity1);
            return inputLine.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    String ViewSDS_FirstAid(JSONObject passV) {
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
//            final String responseText = EntityUtils.toString(entity1);

//            Log.i("Output ViewSDS_FirstAid:", responseText);

            return EntityUtils.toString(entity1);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //ViewSDS_FirstAid for https WCF service
    String ViewSDS_FirstAid_https(JSONObject passV) {
        try {

            URL url1;
            DataOutputStream output;
            DataInputStream input;

            trustAllHosts();
//             Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);


//            String url2 = URLEncoder.encode(url + "LoginByEMail", "UTF-8");

            url1 = new URL(url + "ViewSDS_FirstAid");
//            url1 = new URL(url2);
            HttpsURLConnection connection = (HttpsURLConnection) url1.openConnection();
//            connection.setSSLSocketFactory(trustCert().getSocketFactory());

            connection.setHostnameVerifier(DO_NOT_VERIFY);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
//            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.setUseCaches(false);


            output = new DataOutputStream(connection.getOutputStream());
            output.writeBytes(passV.toString());

            output.flush();
            output.close();

            input = new DataInputStream(connection.getInputStream());

            StringBuffer inputLine = new StringBuffer();
            String tmp;
            while ((tmp = input.readLine()) != null) {
                inputLine.append(tmp);
//                System.out.println(tmp);
            }
            //use inputLine.toString(); here it would have whole source
            input.close();

//            return EntityUtils.toString(entity1);
            return inputLine.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * For self-signed Https only
     * Trust every server - dont check for any certificate
     */

    // always verify the host - dont check for certificate
    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    private static void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {

            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {

            }

            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[] {};
            }

        } };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
