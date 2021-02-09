package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.checkBeforeYouPurchaseProductNameItem;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.searchItemList;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckBeforeYouPurchaseProductNameTableAC extends AppCompatActivity {

    EditText productNameSearchBar;
    List<String> list = new ArrayList<String>();
    List<String>  supplierList = new ArrayList<String>();
    ArrayList<checkBeforeYouPurchaseProductNameItem> searchListOriginal = new ArrayList<>();
    CheckBeforeYouPurchaseProductNameTableAdapter sAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_before_you_purchase_product_name_table_a_c);

        productNameSearchBar = findViewById(R.id.checkBeforeYouPurchaseProductNameSearchBar);
        productNameSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                startSeaching(s.toString());
            }
        });

        getData();
        setupUI(findViewById(R.id.checkBeforeYouPurchaseProductNameSearchBar));

        RecyclerView sRecyclerView = findViewById(R.id.checkBeforeYouPurchaseRecyclerView);
        sRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager sLayoutManager =  new LinearLayoutManager(this);

        sAdapter = new CheckBeforeYouPurchaseProductNameTableAdapter(checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList);

        sRecyclerView.setLayoutManager(sLayoutManager);
        sRecyclerView.setAdapter(sAdapter);
        sAdapter.notifyDataSetChanged();


    }

    public void getData() {
        checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList.clear();
        list.clear();
    //get product name list
        for (int i = 0; i < checkBeforeYouPurchaseProductNameItem.tableList.size(); i++) {

            list.add(checkBeforeYouPurchaseProductNameItem.tableList.get(i).getProdname());
        }

    //get unique product name list
        Set uniqList = new HashSet<>(list);
        list = new ArrayList<String>(uniqList);

        //get supplier list
        for (int i = 0; i < list.size(); i++) {
            supplierList.clear();
            String productName = list.get(i);
            for (int y = 0; y < checkBeforeYouPurchaseProductNameItem.tableList.size(); y++) {


                if (productName.equals(checkBeforeYouPurchaseProductNameItem.tableList.get(y).getProdname())) {
                    supplierList.add(checkBeforeYouPurchaseProductNameItem.tableList.get(y).getCompany());

                }
            }
    //get unique supplier list
            Set uniqSupplierList = new HashSet<>(supplierList);
            int countSupplier = uniqSupplierList.size();
    //add into one array list
            checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList.add( new checkBeforeYouPurchaseProductNameItem(productName, countSupplier));
        }


        searchListOriginal = checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList;
        defaultSort(checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList);

    }

    //set default order for result
    public void defaultSort(ArrayList<checkBeforeYouPurchaseProductNameItem> arrayList) {

        Collections.sort(arrayList, new Comparator<checkBeforeYouPurchaseProductNameItem>() {
            @Override
            public int compare(checkBeforeYouPurchaseProductNameItem lhs, checkBeforeYouPurchaseProductNameItem rhs) {
                return lhs.getProductName().compareTo(rhs.getProductName());
            }
        });
    }


    // search function for searching the product name list
    private void startSeaching(String text) {
        ArrayList<checkBeforeYouPurchaseProductNameItem> searchList = new ArrayList<>();

        checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList = searchListOriginal;

        for (checkBeforeYouPurchaseProductNameItem item : checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList) {
            if (item.getProductName().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(item);
            }
        }
        sAdapter.filterList(searchList);
    }

    //setup listener for the view except EditText
    public void setupUI(View view) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(CheckBeforeYouPurchaseProductNameTableAC.this);
                    return false;
                }
            });
        }
    }

    //hide keyboard when called
    public void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getRootView().getWindowToken(), 0);
    }
}