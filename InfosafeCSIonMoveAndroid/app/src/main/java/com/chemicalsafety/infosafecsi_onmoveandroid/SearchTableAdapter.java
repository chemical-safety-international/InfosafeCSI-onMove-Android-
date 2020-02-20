package com.chemicalsafety.infosafecsi_onmoveandroid;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchTableAdapter extends RecyclerView.Adapter<SearchTableAdapter.SearchTableViewHolder> {

    public static class SearchTableViewHolder extends RecyclerView.ViewHolder {

        public ImageView img1;
        public ImageView img2;
        public ImageView img3;
        public ImageView img4;
        public ImageView img5;

        public TextView pname;
        public TextView country;
        public TextView supplier;
        public TextView unno;
        public TextView pcode;
        public TextView date;

        public SearchTableViewHolder(@NonNull View itemView) {
            super(itemView);
        }

    }

    @NonNull
    @Override
    public SearchTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchTableViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
