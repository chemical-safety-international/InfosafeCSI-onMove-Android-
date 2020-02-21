package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.criteriaList;
import com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf.csiWCFMethods;

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
        criteriaList.pnameInput = pnameET.getText().toString();
        criteriaList.pcodeInput = pcodeET.getText().toString();
        criteriaList.supplierInput = supplierET.getText().toString();

        csiWCFMethods wcf = new csiWCFMethods();
        wcf.SearchReturnList();


//        Intent intent = new Intent(this, SearchTablePageAC.class);
//        startActivity(intent);
    }
}
