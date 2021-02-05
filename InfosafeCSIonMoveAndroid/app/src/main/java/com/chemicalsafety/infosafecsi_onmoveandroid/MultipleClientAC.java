package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.UserInfoStoredFunction;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.loginVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.loginVarMulti;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.searchItemList;
import com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf.csiWCF_VM;

public class MultipleClientAC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_client_a_c);

        RecyclerView mRecyclerView = findViewById(R.id.multipleClientRecyclerView);
//        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager =  new LinearLayoutManager(this);

        RecyclerView.Adapter mAdapter = new MultipleClientTableAdapter(loginVarMulti.clientList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

}