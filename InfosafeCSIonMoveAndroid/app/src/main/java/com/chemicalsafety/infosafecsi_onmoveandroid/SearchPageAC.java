package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.loginVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.searchVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf.csiWCFMethods;
import com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf.csiWCF_VM;

public class SearchPageAC extends AppCompatActivity {

    Button searchBtn, logOffBtn;
    EditText pnameET, supplierET, pcodeET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchBtn = findViewById(R.id.searchBtn);
        logOffBtn = findViewById(R.id.searchLogoff);

        pnameET = findViewById(R.id.pnameSearchBar);
        supplierET = findViewById(R.id.supplierSearchBar);
        pcodeET = findViewById(R.id.pcodeSearchBar);

//        callCriteriaList();

    }


    public void callCriteriaList() {

        csiWCFMethods wcf = new csiWCFMethods();
        wcf.SearchCriteriaList();

    }

    public void logOffBtnTapped(View view) {
        Intent intent = new Intent(this, StartupPageAC.class);
        startActivity(intent);

    }

    public void searchBtnTapped(View view) {
        searchVar.pnameInput = pnameET.getText().toString();
        searchVar.pcodeInput = pcodeET.getText().toString();
        searchVar.supplierInput = supplierET.getText().toString();

//        csiWCFMethods wcf = new csiWCFMethods();
//        wcf.SearchReturnList();

        csiWCF_VM wcf =new csiWCF_VM();
        if (wcf.Search(searchVar.pnameInput, searchVar.pcodeInput, searchVar.supplierInput, loginVar.clientid, loginVar.infosafeid, loginVar.apptype) == true) {
            Intent intent = new Intent(this, SearchTablePageAC.class);
            startActivity(intent);
        } else {
            Log.i("error", "Search failed!.");
        }


    }
}
