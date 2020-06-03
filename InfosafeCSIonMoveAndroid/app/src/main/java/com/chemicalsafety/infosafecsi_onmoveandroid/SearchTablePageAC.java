package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.searchItemList;

public class SearchTablePageAC extends AppCompatActivity {

//    public TextView cardText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchtable);

//        ArrayList<SearchTableItem> tableList = new ArrayList<>();

//        tableList.add(new SearchTableItem("Acetone", "New Zealand", "dsajo djsdosa  dasndoa asd", "SDNA", "sadas", "02/02/2020"));
//        tableList.add(new SearchTableItem("Acetone", "Australia", "dsajo djsdosa  dasndodsfsdfsdfsdfsdfsdfsdfdsfsd sdf sdf sd", "213123123", "sadas, ghfg , dfgdfg", "02/02/2020"));
//        tableList.add(new SearchTableItem("Acetone sdfs sdf dsf sd fsd fsd fsd fsd fs dsd ", "Netherlands", "dsajo djsdosa  dasndoa asd", "SDNA", "sadas", "02/02/2020"));


        RecyclerView sRecyclerView = findViewById(R.id.recyclerView);
        sRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager sLayoutManager =  new LinearLayoutManager(this);

        RecyclerView.Adapter sAdapter = new SearchTableAdapter(searchItemList.tableList);

        sRecyclerView.setLayoutManager(sLayoutManager);
        sRecyclerView.setAdapter(sAdapter);
        sAdapter.notifyDataSetChanged();
    }

    //override back button to directly back to search page(for scan function)
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(SearchTablePageAC.this, SearchPageAC.class));
        finish();

    }


}
