package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.checkBeforeYouPurchaseProductNameItem;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.checkBeforeYouPurchaseSupplierItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CheckBeforeYouPurchaseSupplierTableAC extends AppCompatActivity {

    CardView supplierSortingView;
    EditText supplierSearchBar;
    Button sortButton, supplierButton, noOfProductButton, issueDateButton, ascendButton, descendButton;
    CheckBeforeYouPurchaseSupplierTableAdapter supplierAdapter;

    List<String> supplierList = new ArrayList<String>();
    List<Date> issueDateDateList = new ArrayList<Date>();
    List<String> issueDateStringList = new ArrayList<String>();
    String issueDateValue = "";

    Boolean sortViewStatus = false;
    String sortByStatus = "Supplier";
    String sortAsStatus = "Ascend";

    ArrayList<checkBeforeYouPurchaseSupplierItem> searchListOriginal = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_before_you_purchase_supplier_table_a_c);

        supplierSortingView = findViewById(R.id.sSortingCardview);
        supplierSearchBar = findViewById(R.id.checkBeforeYouSupplierSearchBar);
        sortButton = findViewById(R.id.supplierSortButton);
        supplierButton = findViewById(R.id.sortingSupplierButton);
        noOfProductButton = findViewById(R.id.sortingNoOfProductButton);
        issueDateButton = findViewById(R.id.iusseDateButton);
        ascendButton = findViewById(R.id.asendButton);
        descendButton = findViewById(R.id.descendButton);

        supplierSearchBar.addTextChangedListener(new TextWatcher() {
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
        setUpDefaultSortingView();

        //set up recyclerview and adapter
        RecyclerView sRecyclerView = findViewById(R.id.supplierRecyclerView);
        sRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager sLayoutManager =  new LinearLayoutManager(this);

        supplierAdapter = new CheckBeforeYouPurchaseSupplierTableAdapter(checkBeforeYouPurchaseSupplierItem.supplierItemsTableList);

        sRecyclerView.setLayoutManager(sLayoutManager);
        sRecyclerView.setAdapter(supplierAdapter);
        supplierAdapter.notifyDataSetChanged();



    }

    public void setUpDefaultSortingView() {
        supplierSortingView.setVisibility(View.INVISIBLE);

        supplierButton.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        supplierButton.setTextColor(Color.WHITE);
        noOfProductButton.setBackgroundColor(Color.parseColor("#F1F0F0"));
        noOfProductButton.setTextColor(Color.BLACK);
        issueDateButton.setBackgroundColor(Color.parseColor("#F1F0F0"));
        issueDateButton.setTextColor(Color.BLACK);

        ascendButton.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        ascendButton.setTextColor(Color.WHITE);
        descendButton.setBackgroundColor(Color.parseColor("#F1F0F0"));
        descendButton.setTextColor(Color.BLACK);

        ascendButton.setText("A to Z");
        descendButton.setText("Z to A");

        sortByStatus = "Supplier";
        sortAsStatus = "Ascend";
    }


    public void getData() {
        checkBeforeYouPurchaseSupplierItem.supplierItemsTableList.clear();

        Date minDate = new Date();
        Date maxDate = new Date();

        Log.i("Product name", checkBeforeYouPurchaseProductNameItem.productNamePass);

        for (int i = 0; i < checkBeforeYouPurchaseSupplierItem.tableList.size(); i++) {
            if (checkBeforeYouPurchaseProductNameItem.productNamePass.equals(checkBeforeYouPurchaseSupplierItem.tableList.get(i).getProdname())) {
//                Log.i("supplier:", checkBeforeYouPurchaseSupplierItem.tableList.get(i).getCompany());
                supplierList.add(checkBeforeYouPurchaseSupplierItem.tableList.get(i).getCompany());
            }
        }

        Set uniqSupplierList = new HashSet<>(supplierList);
        supplierList = new ArrayList<String>(uniqSupplierList);


        for (int x = 0; x < supplierList.size(); x++) {
            issueDateStringList.clear();

            for (int y = 0; y < checkBeforeYouPurchaseSupplierItem.tableList.size(); y++) {
                if (checkBeforeYouPurchaseProductNameItem.productNamePass.equals(checkBeforeYouPurchaseSupplierItem.tableList.get(y).getProdname()) && supplierList.get(x).equals(checkBeforeYouPurchaseSupplierItem.tableList.get(y).getCompany())) {
                    issueDateStringList.add(checkBeforeYouPurchaseSupplierItem.tableList.get(y).getIssueDate());
                }
            }

            SimpleDateFormat inPutdateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outPutdateFormat = new SimpleDateFormat("dd/MM/yyyy");


            if (issueDateStringList.size() > 1) {
                try {
                    for (int z = 0; z < issueDateStringList.size(); z++) {
                        Date dateValue = inPutdateFormat.parse(issueDateStringList.get(z).trim());
                        issueDateDateList.add(dateValue);
                    }
                    minDate = Collections.min(issueDateDateList);
                    maxDate = Collections.max(issueDateDateList);

                    if (minDate.equals(maxDate)) {
                        issueDateValue = outPutdateFormat.format(maxDate);
                    } else {
                        issueDateValue = outPutdateFormat.format(minDate) + " - " + outPutdateFormat.format(maxDate);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            } else if (issueDateStringList.size() == 1) {
                try {

                    Date dateValue = inPutdateFormat.parse(issueDateStringList.get(0).trim());


                    minDate = dateValue;
                    maxDate = dateValue;

                    issueDateValue = outPutdateFormat.format(maxDate);
//                    Log.i("date is:", issueDateValue);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


            checkBeforeYouPurchaseSupplierItem.supplierItemsTableList.add( new checkBeforeYouPurchaseSupplierItem(supplierList.get(x), issueDateStringList.size(), issueDateValue, minDate, maxDate));
        }

        searchListOriginal = checkBeforeYouPurchaseSupplierItem.supplierItemsTableList;
        supplierAtoZSort(checkBeforeYouPurchaseSupplierItem.supplierItemsTableList);

    }

    // search function for searching the product name list
    private void startSeaching(String text) {
        ArrayList<checkBeforeYouPurchaseSupplierItem> searchList = new ArrayList<>();

        checkBeforeYouPurchaseSupplierItem.supplierItemsTableList = searchListOriginal;

        for (checkBeforeYouPurchaseSupplierItem item : checkBeforeYouPurchaseSupplierItem.supplierItemsTableList) {
            if (item.getSupplier().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(item);
            }
        }
        supplierAdapter.filterList(searchList);
    }

    //set A to Z order for result
    public void supplierAtoZSort(ArrayList<checkBeforeYouPurchaseSupplierItem> arrayList) {
//        Log.i("a to z", "ok");
        Collections.sort(arrayList, new Comparator<checkBeforeYouPurchaseSupplierItem>() {
            @Override
            public int compare(checkBeforeYouPurchaseSupplierItem lhs, checkBeforeYouPurchaseSupplierItem rhs) {
                return lhs.getSupplier().compareTo(rhs.getSupplier());
            }
        });
    }

    //set Z to A order for result
    public void supplierZtoASort(ArrayList<checkBeforeYouPurchaseSupplierItem> arrayList) {
//        Log.i("a to z", "ok");
        Collections.sort(arrayList, new Comparator<checkBeforeYouPurchaseSupplierItem>() {
            @Override
            public int compare(checkBeforeYouPurchaseSupplierItem e1, checkBeforeYouPurchaseSupplierItem e2) {
                return e2.getSupplier().compareTo(e1.getSupplier());
            }
        });
    }

    //set Largest to Smallest order for result
    public void supplierLtoSSort(ArrayList<checkBeforeYouPurchaseSupplierItem> arrayList) {
//        Log.i("a to z", "ok");
        Collections.sort(arrayList, new Comparator<checkBeforeYouPurchaseSupplierItem>() {
            @Override
            public int compare(checkBeforeYouPurchaseSupplierItem e3, checkBeforeYouPurchaseSupplierItem e4) {
                return e4.getNoOfProduct().compareTo(e3.getNoOfProduct());
            }
        });
    }

    //set Smallest to Largest order for result
    public void supplierStoLSort(ArrayList<checkBeforeYouPurchaseSupplierItem> arrayList) {
//        Log.i("a to z", "ok");
        Collections.sort(arrayList, new Comparator<checkBeforeYouPurchaseSupplierItem>() {
            @Override
            public int compare(checkBeforeYouPurchaseSupplierItem e5, checkBeforeYouPurchaseSupplierItem e6) {
                return e5.getNoOfProduct().compareTo(e6.getNoOfProduct());
            }
        });
    }

    //set Oldest to Latest order for result
    public void supplierOtoLSort(ArrayList<checkBeforeYouPurchaseSupplierItem> arrayList) {
//        Log.i("a to z", "ok");
        Collections.sort(arrayList, new Comparator<checkBeforeYouPurchaseSupplierItem>() {
            @Override
            public int compare(checkBeforeYouPurchaseSupplierItem e7, checkBeforeYouPurchaseSupplierItem e8) {
                return e7.getMinDate().compareTo(e8.getMinDate());
            }
        });
    }

    //set Oldest to Smallest order for result
    public void supplierLtoOSort(ArrayList<checkBeforeYouPurchaseSupplierItem> arrayList) {
//        Log.i("a to z", "ok");
        Collections.sort(arrayList, new Comparator<checkBeforeYouPurchaseSupplierItem>() {
            @Override
            public int compare(checkBeforeYouPurchaseSupplierItem e9, checkBeforeYouPurchaseSupplierItem e10) {
                return e10.getMaxDate().compareTo(e9.getMaxDate());
            }
        });
    }



    public void supplierSortButtonTapped(View view) {
        if (sortViewStatus.equals(false)) {
            supplierSortingView.setVisibility(View.VISIBLE);
            sortViewStatus = true;
        } else if (sortViewStatus.equals(true)) {
            supplierSortingView.setVisibility(View.INVISIBLE);
            sortViewStatus = false;
        }
    }

    public void supplierButtonTapped(View view) {
        supplierSearchBar.setText("");

        supplierButton.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        supplierButton.setTextColor(Color.WHITE);
        noOfProductButton.setBackgroundColor(Color.parseColor("#F1F0F0"));
        noOfProductButton.setTextColor(Color.BLACK);
        issueDateButton.setBackgroundColor(Color.parseColor("#F1F0F0"));
        issueDateButton.setTextColor(Color.BLACK);

        ascendButton.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        ascendButton.setTextColor(Color.WHITE);
        descendButton.setBackgroundColor(Color.parseColor("#F1F0F0"));
        descendButton.setTextColor(Color.BLACK);

        ascendButton.setText("A to Z");
        descendButton.setText("Z to A");

        sortByStatus = "Supplier";
        sortAsStatus = "Ascend";

        checkBeforeYouPurchaseSupplierItem.supplierItemsTableList = searchListOriginal;
        supplierAtoZSort(checkBeforeYouPurchaseSupplierItem.supplierItemsTableList);
        supplierAdapter.filterList(checkBeforeYouPurchaseSupplierItem.supplierItemsTableList);
    }

    public void noOfProductButtonTapped(View view) {
        supplierSearchBar.setText("");

        noOfProductButton.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        noOfProductButton.setTextColor(Color.WHITE);
        supplierButton.setBackgroundColor(Color.parseColor("#F1F0F0"));
        supplierButton.setTextColor(Color.BLACK);
        issueDateButton.setBackgroundColor(Color.parseColor("#F1F0F0"));
        issueDateButton.setTextColor(Color.BLACK);

        ascendButton.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        ascendButton.setTextColor(Color.WHITE);
        descendButton.setBackgroundColor(Color.parseColor("#F1F0F0"));
        descendButton.setTextColor(Color.BLACK);

        ascendButton.setText("Largest to Smallest");
        descendButton.setText("Smallest to Largest");

        sortByStatus = "No Of Product";
        sortAsStatus = "Ascend";

        checkBeforeYouPurchaseSupplierItem.supplierItemsTableList = searchListOriginal;
        supplierLtoSSort(checkBeforeYouPurchaseSupplierItem.supplierItemsTableList);
        supplierAdapter.filterList(checkBeforeYouPurchaseSupplierItem.supplierItemsTableList);

    }

    public void issueDateButtonTapped(View view) {
        supplierSearchBar.setText("");

        issueDateButton.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        issueDateButton.setTextColor(Color.WHITE);
        supplierButton.setBackgroundColor(Color.parseColor("#F1F0F0"));
        supplierButton.setTextColor(Color.BLACK);
        noOfProductButton.setBackgroundColor(Color.parseColor("#F1F0F0"));
        noOfProductButton.setTextColor(Color.BLACK);

        ascendButton.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        ascendButton.setTextColor(Color.WHITE);
        descendButton.setBackgroundColor(Color.parseColor("#F1F0F0"));
        descendButton.setTextColor(Color.BLACK);

        ascendButton.setText("Latest to Oldest");
        descendButton.setText("Oldest to Latest");

        sortByStatus = "Issue Date";
        sortAsStatus = "Ascend";

        checkBeforeYouPurchaseSupplierItem.supplierItemsTableList = searchListOriginal;
        supplierLtoOSort(checkBeforeYouPurchaseSupplierItem.supplierItemsTableList);
        supplierAdapter.filterList(checkBeforeYouPurchaseSupplierItem.supplierItemsTableList);
    }

    public void setAscendButton(View view) {

        if (sortByStatus.equals("Supplier")) {

            checkBeforeYouPurchaseSupplierItem.supplierItemsTableList = searchListOriginal;
            supplierAtoZSort(checkBeforeYouPurchaseSupplierItem.supplierItemsTableList);
            supplierAdapter.filterList(checkBeforeYouPurchaseSupplierItem.supplierItemsTableList);

        } else if (sortByStatus.equals("No Of Product")) {

            checkBeforeYouPurchaseSupplierItem.supplierItemsTableList = searchListOriginal;
            supplierLtoSSort(checkBeforeYouPurchaseSupplierItem.supplierItemsTableList);
            supplierAdapter.filterList(checkBeforeYouPurchaseSupplierItem.supplierItemsTableList);

        } else if (sortByStatus.equals("Issue Date")) {
            checkBeforeYouPurchaseSupplierItem.supplierItemsTableList = searchListOriginal;
            supplierLtoOSort(checkBeforeYouPurchaseSupplierItem.supplierItemsTableList);
            supplierAdapter.filterList(checkBeforeYouPurchaseSupplierItem.supplierItemsTableList);
        }

        ascendButton.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        ascendButton.setTextColor(Color.WHITE);
        descendButton.setBackgroundColor(Color.parseColor("#F1F0F0"));
        descendButton.setTextColor(Color.BLACK);

        sortAsStatus = "Ascend";
        supplierSearchBar.setText("");
        supplierSortingView.setVisibility(View.INVISIBLE);
    }

    public void setDescendButton(View view) {
        if (sortByStatus.equals("Supplier")) {

            checkBeforeYouPurchaseSupplierItem.supplierItemsTableList = searchListOriginal;
            supplierZtoASort(checkBeforeYouPurchaseSupplierItem.supplierItemsTableList);
            supplierAdapter.filterList(checkBeforeYouPurchaseSupplierItem.supplierItemsTableList);

        } else if (sortByStatus.equals("No Of Product")) {

            checkBeforeYouPurchaseSupplierItem.supplierItemsTableList = searchListOriginal;
            supplierStoLSort(checkBeforeYouPurchaseSupplierItem.supplierItemsTableList);
            supplierAdapter.filterList(checkBeforeYouPurchaseSupplierItem.supplierItemsTableList);

        } else if (sortByStatus.equals("Issue Date")) {
            checkBeforeYouPurchaseSupplierItem.supplierItemsTableList = searchListOriginal;
            supplierOtoLSort(checkBeforeYouPurchaseSupplierItem.supplierItemsTableList);
            supplierAdapter.filterList(checkBeforeYouPurchaseSupplierItem.supplierItemsTableList);
        }

        descendButton.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        descendButton.setTextColor(Color.WHITE);
        ascendButton.setBackgroundColor(Color.parseColor("#F1F0F0"));
        ascendButton.setTextColor(Color.BLACK);

        sortAsStatus = "Descend";
        supplierSearchBar.setText("");
        supplierSortingView.setVisibility(View.INVISIBLE);
    }
}