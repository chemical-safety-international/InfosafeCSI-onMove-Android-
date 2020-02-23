package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.SearchTableItem;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.searchItemList;

import java.util.ArrayList;

public class SearchTablePageAC extends AppCompatActivity {
    private RecyclerView sRecyclerView;
    private RecyclerView.Adapter sAdapter;
    private RecyclerView.LayoutManager sLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchtable);

//        ArrayList<SearchTableItem> tableList = new ArrayList<>();

//        tableList.add(new SearchTableItem("Acetone", "New Zealand", "dsajo djsdosa  dasndoa asd", "SDNA", "sadas", "02/02/2020"));
//        tableList.add(new SearchTableItem("Acetone", "Australia", "dsajo djsdosa  dasndodsfsdfsdfsdfsdfsdfsdfdsfsd sdf sdf sd", "213123123", "sadas, ghfg , dfgdfg", "02/02/2020"));
//        tableList.add(new SearchTableItem("Acetone sdfs sdf dsf sd fsd fsd fsd fsd fs dsd ", "Netherlands", "dsajo djsdosa  dasndoa asd", "SDNA", "sadas", "02/02/2020"));

        sRecyclerView = findViewById(R.id.recyclerView);
        sRecyclerView.setHasFixedSize(true);
        sLayoutManager =  new LinearLayoutManager(this);
        sAdapter = new SearchTableAdapter(searchItemList.tableList);

        sRecyclerView.setLayoutManager(sLayoutManager);
        sRecyclerView.setAdapter(sAdapter);
    }
}
