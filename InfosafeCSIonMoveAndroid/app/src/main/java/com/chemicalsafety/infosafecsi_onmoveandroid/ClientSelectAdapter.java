package com.chemicalsafety.infosafecsi_onmoveandroid;


import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.clientSelectItem;

import java.util.ArrayList;

public class ClientSelectAdapter extends RecyclerView.Adapter<ClientSelectAdapter.ClientSelectViewHolder> {

    @NonNull
    @Override
    public ClientSelectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ClientSelectViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ClientSelectViewHolder extends RecyclerView.ViewHolder {

        TextView clientName;

        public ClientSelectViewHolder(@NonNull View itemView) {
            super(itemView);

            clientName = itemView.findViewById(R.id.clientName);
        }
    }

//    ClientSelectAdapter(ArrayList<clientSelectItem> ite)
}
