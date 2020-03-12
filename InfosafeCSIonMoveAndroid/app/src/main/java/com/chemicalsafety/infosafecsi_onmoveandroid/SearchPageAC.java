package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

        //hid the soft keyboard
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //get the search bars input
        searchVar.pnameInput = pnameET.getText().toString();
        searchVar.pcodeInput = pcodeET.getText().toString();
        searchVar.supplierInput = supplierET.getText().toString();

        DialogFragment df = new DialogFragment();

        if (searchVar.pnameInput.isEmpty() && searchVar.pcodeInput.isEmpty() && searchVar.supplierInput.isEmpty()) {
            df.callAlert(SearchPageAC.this, "Search input empty!\nPlease check your input and try again.");
        } else {

            if (!searchVar.pnameInput.isEmpty() && searchVar.pnameInput.length() < 3) {
                df.callAlert(SearchPageAC.this, "Search failed!\nPlease enter more than 2 characters for product name!" );
            } else if(searchVar.supplierInput.length() == 1) {
                df.callAlert(SearchPageAC.this, "Search failed!\nPlease enter more than 1 characters for supplier!");
            } else {
                //call the Search WCF
                csiWCF_VM wcf =new csiWCF_VM();
                if (wcf.Search(searchVar.pnameInput, searchVar.pcodeInput, searchVar.supplierInput, loginVar.clientid, loginVar.infosafeid, loginVar.apptype)) {
                    Intent intent = new Intent(this, SearchTablePageAC.class);
                    startActivity(intent);
                } else {
                    Log.i("error", "Search failed!.");
                    df.callAlert(SearchPageAC.this, "Search Failed!\nPlease check the connection and try again.");
                }
            }


        }

    }
}
