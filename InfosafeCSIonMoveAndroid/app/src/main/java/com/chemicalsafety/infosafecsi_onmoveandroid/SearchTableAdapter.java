package com.chemicalsafety.infosafecsi_onmoveandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.SearchTableItem;

import java.util.ArrayList;

public class SearchTableAdapter extends RecyclerView.Adapter<SearchTableAdapter.SearchTableViewHolder> {
    private ArrayList<SearchTableItem> searchTableList;

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
            unnoA = itemView.findViewById(R.id.unno);
            pcodeA = itemView.findViewById(R.id.pcode);
            dateA = itemView.findViewById(R.id.issuedate);
        }

    }

    public SearchTableAdapter(ArrayList<SearchTableItem> itemList) {
        searchTableList = itemList;
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
        SearchTableItem currentItem = searchTableList.get(position);

        holder.pnameA.setText(currentItem.getPname());
        holder.countryA.setText(currentItem.getCountry());
        holder.supplierA.setText(currentItem.getSupplier());
        holder.unnoA.setText(currentItem.getUnno());
        holder.pcodeA.setText(currentItem.getPcode());
        holder.dateA.setText(currentItem.getDate());
    }

    @Override
    public int getItemCount() {

        return searchTableList.size();
    }
}
