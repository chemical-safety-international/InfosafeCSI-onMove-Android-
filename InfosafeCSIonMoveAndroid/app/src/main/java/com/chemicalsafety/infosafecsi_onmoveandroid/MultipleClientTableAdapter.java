package com.chemicalsafety.infosafecsi_onmoveandroid;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.UserInfoStoredFunction;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.loginVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.loginVarMulti;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.searchItemList;
import com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf.csiWCF_VM;

import java.util.ArrayList;

public class MultipleClientTableAdapter extends RecyclerView.Adapter<MultipleClientTableAdapter.MultipleClientTableViewHolder> {

    static class MultipleClientTableViewHolder extends RecyclerView.ViewHolder {
    TextView clientTextView;

        MultipleClientTableViewHolder(@NonNull View itemView) {
            super(itemView);

            clientTextView = itemView.findViewById(R.id.clientNameTextView);
        }
    }


    MultipleClientTableAdapter(ArrayList<loginVarMulti> itemList) { loginVarMulti.clientList = itemList; }

    @NonNull
    @Override
    public MultipleClientTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.multipleclienttable_item, parent, false);
        return new MultipleClientTableViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MultipleClientTableViewHolder holder, final int position) {

        final loginVarMulti currentItem = loginVarMulti.clientList.get(position);



        holder.clientTextView.setText(currentItem.getClientname());

//        //set button action
        holder.clientTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v) {
                try {


                    final String loginappointid = currentItem.getClientid();
                    loginVar.clientid = loginappointid;
                    loginVar.appointclient = loginappointid;

                    if (!UserInfoStoredFunction.getOTACode(v.getContext()).equals("default_otacode")) {
                        loginVar.otacode = UserInfoStoredFunction.getOTACode(v.getContext());
                    } else {
                        loginVar.otacode = "";
                    }

                    final DialogFragment df = new DialogFragment();
                    final csiWCF_VM wcf = new csiWCF_VM();

                    df.callloadingScreen(v.getContext(), "Loading...");




                    Thread t = new Thread(new Runnable() {

                    public void run() {
                        if (wcf.MulitpleClientLogin(loginVar.email, "", loginVar.otacode, loginappointid)) {


                            if (loginVar.passed.equals(true)) {

                                if (loginVar.isgeneric.equals(true) && (loginVar.needchooseclient.equals(true))) {
                                    df.cancelLoadingScreen();
                                    toLoginMultipleClientAC(v);
                                } else if (loginVar.isgeneric.equals(true) && (loginVar.needchooseclient.equals(false))) {
                                    df.cancelLoadingScreen();
                                    toSeachAC(v);
                                } else if (loginVar.isgeneric.equals(false) && (loginVar.needchooseclient.equals(true))) {
                                    df.cancelLoadingScreen();
                                    toLoginMultipleClientAC(v);
                                } else if (loginVar.isgeneric.equals(false) && (loginVar.needchooseclient.equals(false)) && loginVar.needpsw.equals(true)) {
                                    df.cancelLoadingScreen();
                                    toLoginpasswordAC(v);
                                }

                            } else if (loginVar.passed.equals(false)) {

                                if (loginVar.isgeneric.equals(true) && (loginVar.needchooseclient.equals(true))) {
                                    df.cancelLoadingScreen();
                                    toLoginMultipleClientAC(v);
                                } else if (loginVar.isgeneric.equals(false) && (loginVar.needchooseclient.equals(true))) {
                                    df.cancelLoadingScreen();
                                    toLoginMultipleClientAC(v);
                                } else if (loginVar.needpsw.equals(true)) {
                                    if (wcf.clientLogo(loginappointid)) {
                                        df.cancelLoadingScreen();
                                        toLoginpasswordAC(v);
                                    }

                                } else if (loginVar.isgeneric.equals(true) && loginVar.retIndexText.contains("OTA Code Sent")) {
                                    df.cancelLoadingScreen();
                                    toLoginOTAAC(v);
                                } else {
//                                    df.callAlert(v.getContext(), "Login Failed!\n" + loginVar.retIndexText);
                                }
                            }

                        } else {
                            Log.d("error", "login failed");

//                            df.cancelLoadingScreen();
//                            df.callAlert(v.getContext(), "Login Failed!\n" + loginVar.retIndexText);
                        }

                    }});
                    t.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return loginVarMulti.clientList.size();
    }

    //jump to search page
    public void toSeachAC(View v) {

        v.getContext().startActivity(new Intent(v.getContext(), SearchPageAC.class));
    }

    //jump to password page
    public void toLoginpasswordAC(View v) {

        v.getContext().startActivity(new Intent(v.getContext(), LoginPageAC.class));
    }

    //jump to ota code page
    public void toLoginOTAAC(View v) {

        v.getContext().startActivity(new Intent(v.getContext(), OTACodeAC.class));
    }

    //jump to ota code page
    public void toLoginMultipleClientAC(View v) {

        v.getContext().startActivity(new Intent(v.getContext(), MultipleClientAC.class));
    }

}
