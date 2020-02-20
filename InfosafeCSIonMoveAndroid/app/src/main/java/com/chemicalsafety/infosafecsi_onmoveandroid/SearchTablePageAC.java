package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.SearchTableItem;

import java.util.ArrayList;

public class SearchTablePageAC extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchtable);

        ArrayList<SearchTableItem> tableList = new ArrayList<>();

        tableList.add(new SearchTableItem("Acetone", "New Zealand", "dsajo djsdosa  dasndoa asd", "SDNA", "sadas", "02/02/2020"));
        tableList.add(new SearchTableItem("Acetone", "Australia", "dsajo djsdosa  dasndodsfsdfsdfsdfsdfsdfsdfdsfsd sdf sdf sd", "213123123", "sadas, ghfg , dfgdfg", "02/02/2020"));
        tableList.add(new SearchTableItem("Acetone sdfs sdf dsf sd fsd fsd fsd fsd fs dsd ", "Netherlands", "dsajo djsdosa  dasndoa asd", "SDNA", "sadas", "02/02/2020"));
    }
}
