package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.checkBeforeYouPurchaseProductNameItem;


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
    Boolean sortViewStatus = false;
    String sortByStatus = "Product Name";
    String sortAsStatus = "Ascend";
    Button productNameButton, noOfSupplierButton, ltosButton, stolButton;
    CardView sortingCardview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_before_you_purchase_product_name_table_a_c);

        productNameButton = findViewById(R.id.sorting_productnameButton);
        noOfSupplierButton = findViewById(R.id.sortingNoOfSupplierButton);
        ltosButton = findViewById(R.id.sortingAsAtoZ);
        stolButton = findViewById(R.id.sortingAsZtoA);
        sortingCardview = findViewById(R.id.pnSortingCardview);


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
        setDefaultSortingView();

        RecyclerView sRecyclerView = findViewById(R.id.checkBeforeYouPurchaseProductNameRecyclerView);
        sRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager sLayoutManager =  new LinearLayoutManager(this);

        sAdapter = new CheckBeforeYouPurchaseProductNameTableAdapter(checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList);

        sRecyclerView.setLayoutManager(sLayoutManager);
        sRecyclerView.setAdapter(sAdapter);
        sAdapter.notifyDataSetChanged();


    }

    public void setDefaultSortingView() {
        sortingCardview.setVisibility(View.INVISIBLE);

        productNameButton.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        productNameButton.setTextColor(Color.WHITE);
        noOfSupplierButton.setBackgroundColor(Color.parseColor("#F1F0F0"));
        noOfSupplierButton.setTextColor(Color.BLACK);

        ltosButton.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        ltosButton.setTextColor(Color.WHITE);
        stolButton.setBackgroundColor(Color.parseColor("#F1F0F0"));
        stolButton.setTextColor(Color.BLACK);

        ltosButton.setText("A to Z");
        stolButton.setText("Z to A");


        sortByStatus = "Product Name";
        sortAsStatus = "Ascend";

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
        atozSort(checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList);

    }

    //set A to Z order for result
    public void atozSort(ArrayList<checkBeforeYouPurchaseProductNameItem> arrayList) {
//        Log.i("a to z", "ok");
        Collections.sort(arrayList, new Comparator<checkBeforeYouPurchaseProductNameItem>() {
            @Override
            public int compare(checkBeforeYouPurchaseProductNameItem lhs, checkBeforeYouPurchaseProductNameItem rhs) {
                return lhs.getProductName().compareTo(rhs.getProductName());
            }
        });
    }

    //set Z to A order for result
    public void ztoaSort(ArrayList<checkBeforeYouPurchaseProductNameItem> arrayList) {
//    Log.i("z to a", "ok");
        Collections.sort(arrayList, new Comparator<checkBeforeYouPurchaseProductNameItem>() {
            @Override
            public int compare(checkBeforeYouPurchaseProductNameItem e1, checkBeforeYouPurchaseProductNameItem e2) {
                return e2.getProductName().compareTo(e1.getProductName());
            }
        });
    }

    //set largest to smallest order for result
    public void largesttosmallestSort(ArrayList<checkBeforeYouPurchaseProductNameItem> arrayList) {
//        Log.i("l to s", "ok");
        Collections.sort(arrayList, new Comparator<checkBeforeYouPurchaseProductNameItem>() {
            @Override
            public int compare(checkBeforeYouPurchaseProductNameItem e3, checkBeforeYouPurchaseProductNameItem e4) {
                return e4.getNoOfSupplier().compareTo(e3.getNoOfSupplier());
            }
        });
    }

    public void smallesttolargestSort(ArrayList<checkBeforeYouPurchaseProductNameItem> arrayList) {
//        Log.i("s to l", "ok");
        Collections.sort(arrayList, new Comparator<checkBeforeYouPurchaseProductNameItem>() {
            @Override
            public int compare(checkBeforeYouPurchaseProductNameItem e5, checkBeforeYouPurchaseProductNameItem e6) {
                return e5.getNoOfSupplier().compareTo(e6.getNoOfSupplier());
            }
        });
    }


    public void sortButtonTapped(View view) {
        if (sortViewStatus.equals(false)) {
            sortingCardview.setVisibility(View.VISIBLE);
            sortViewStatus = true;
        } else if (sortViewStatus.equals(true)) {
            sortingCardview.setVisibility(View.INVISIBLE);
            sortViewStatus = false;
        }
    }

    public void sortByProductNameButtonTapped(View view) {

        sortByStatus = "Product Name";
        sortAsStatus = "Ascend";
        productNameSearchBar.setText("");

        productNameButton.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        productNameButton.setTextColor(Color.WHITE);
        noOfSupplierButton.setBackgroundColor(Color.parseColor("#F1F0F0"));
        noOfSupplierButton.setTextColor(Color.BLACK);

        ltosButton.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        ltosButton.setTextColor(Color.WHITE);
        stolButton.setBackgroundColor(Color.parseColor("#F1F0F0"));
        stolButton.setTextColor(Color.BLACK);

        ltosButton.setText("A to Z");
        stolButton.setText("Z to A");

        checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList = searchListOriginal;
        atozSort(checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList);
        sAdapter.filterList(checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList);

    }

    public void sortByNoOfSupplierButtonTapped(View view) {

        sortByStatus = "No Of Supplier";
        sortAsStatus = "Ascend";
        productNameSearchBar.setText("");

        noOfSupplierButton.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        noOfSupplierButton.setTextColor(Color.WHITE);
        productNameButton.setBackgroundColor(Color.parseColor("#F1F0F0"));
        productNameButton.setTextColor(Color.BLACK);

        ltosButton.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        ltosButton.setTextColor(Color.WHITE);
        stolButton.setBackgroundColor(Color.parseColor("#F1F0F0"));
        stolButton.setTextColor(Color.BLACK);

        checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList = searchListOriginal;
        largesttosmallestSort(checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList);
        sAdapter.filterList(checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList);

        ltosButton.setText("Largest to Smallest");
        stolButton.setText("Smallest to Largest");

    }

    //ascend buton action
    public void ascendButtonTapped(View view) {
        Log.i("sortas", sortByStatus);
        if (sortByStatus.equals("Product Name")) {

            checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList = searchListOriginal;
            atozSort(checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList);
            sAdapter.filterList(checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList);

        } else if (sortByStatus.equals("No Of Supplier")) {

            checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList = searchListOriginal;
            largesttosmallestSort(checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList);
            sAdapter.filterList(checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList);

        }

        ltosButton.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        ltosButton.setTextColor(Color.WHITE);
        stolButton.setBackgroundColor(Color.parseColor("#F1F0F0"));
        stolButton.setTextColor(Color.BLACK);

        sortAsStatus = "Ascend";
        productNameSearchBar.setText("");
        sortingCardview.setVisibility(View.INVISIBLE);
    }

    //descend button action
    public void descendButtonTapped(View view) {

        if (sortByStatus.equals("Product Name")) {

            checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList = searchListOriginal;
            ztoaSort(checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList);
            sAdapter.filterList(checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList);

        } else if (sortByStatus.equals("No Of Supplier")) {

            checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList = searchListOriginal;
            smallesttolargestSort(checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList);
            sAdapter.filterList(checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList);

        }

        stolButton.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        stolButton.setTextColor(Color.WHITE);
        ltosButton.setBackgroundColor(Color.parseColor("#F1F0F0"));
        ltosButton.setTextColor(Color.BLACK);

        sortAsStatus = "Descend";
        productNameSearchBar.setText("");

        sortingCardview.setVisibility(View.INVISIBLE);
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