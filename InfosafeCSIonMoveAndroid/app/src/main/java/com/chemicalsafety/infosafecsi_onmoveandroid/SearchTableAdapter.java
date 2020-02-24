package com.chemicalsafety.infosafecsi_onmoveandroid;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.searchItemList;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class SearchTableAdapter extends RecyclerView.Adapter<SearchTableAdapter.SearchTableViewHolder> {
//    private ArrayList<SearchTableItem> searchTableList;

    public static class SearchTableViewHolder extends RecyclerView.ViewHolder {

        public ImageView img1;
        public ImageView img2;
        public ImageView img3;
        public ImageView img4;
        public ImageView img5;

        public TextView pnameA;
        public TextView countryA;
        public TextView supplierA;
        public TextView unnoA;
        public TextView pcodeA;
        public TextView dateA;

        public SearchTableViewHolder(@NonNull View itemView) {

            super(itemView);
            pnameA = itemView.findViewById(R.id.pname);
            countryA = itemView.findViewById(R.id.country);
            supplierA = itemView.findViewById(R.id.supplier);
            unnoA = itemView.findViewById(R.id.unv);
            pcodeA = itemView.findViewById(R.id.pcv);
            dateA = itemView.findViewById(R.id.dav);

            img1 = itemView.findViewById(R.id.img1);
        }

    }


    public SearchTableAdapter(ArrayList<searchItemList> itemList) {
        searchItemList.tableList = itemList;
    }

    @NonNull
    @Override
    public SearchTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchtable_item, parent, false);
        SearchTableViewHolder svh = new SearchTableViewHolder(v);
        return svh;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchTableViewHolder holder, int position) {

        searchItemList currentItem = searchItemList.tableList.get(position);

        holder.pnameA.setText(currentItem.getProdname());
        holder.countryA.setText(currentItem.getCom_Country());
        holder.supplierA.setText(currentItem.getCompany());
        holder.unnoA.setText(currentItem.getUnno());
        holder.pcodeA.setText(currentItem.getProdcode());
        holder.dateA.setText(currentItem.getIssueDate());

        String img1v = currentItem.getImgV1();
        String img2v = currentItem.getImgV2();
        String img3v = currentItem.getImgV3();
        String img4v = currentItem.getImgV4();
        String img5v = currentItem.getImgV5();

    try {
        Class res = R.drawable.class;
        Field field1 = res.getField(img1v);
        int id1 = field1.getInt(null);
        holder.img1.setImageResource(id1);

        Field field2 = res.getField(img2v);
        int id2 = field2.getInt(null);
        holder.img1.setImageResource(id2);

        Field field3 = res.getField(img3v);
        int id3 = field3.getInt(null);
        holder.img1.setImageResource(id3);

        Field field4 = res.getField(img4v);
        int id4 = field4.getInt(null);
        holder.img1.setImageResource(id4);

        Field field5 = res.getField(img5v);
        int id5 = field5.getInt(null);
        holder.img1.setImageResource(id5);

    } catch (Exception e) {
        e.printStackTrace();
    }



    }

    @Override
    public int getItemCount() {

//        return searchTableList.size();
        return searchItemList.tableList.size();
    }
}
