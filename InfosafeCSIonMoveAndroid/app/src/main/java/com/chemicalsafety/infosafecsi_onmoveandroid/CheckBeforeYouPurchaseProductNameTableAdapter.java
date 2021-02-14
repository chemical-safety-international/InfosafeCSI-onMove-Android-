package com.chemicalsafety.infosafecsi_onmoveandroid;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.checkBeforeYouPurchaseProductNameItem;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.checkBeforeYouPurchaseSupplierItem;

import java.util.ArrayList;

public class CheckBeforeYouPurchaseProductNameTableAdapter extends RecyclerView.Adapter<CheckBeforeYouPurchaseProductNameTableAdapter.CheckBeforeYouPurchaseProductNameTableHolder> {

    static class CheckBeforeYouPurchaseProductNameTableHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView noOfSupplier;
        CardView tableItemView;

        CheckBeforeYouPurchaseProductNameTableHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.checkBeforeYouPurchaseProductName);
            noOfSupplier = itemView.findViewById(R.id.checkBeforeYouPurchaseNoOfSupplier);
            tableItemView = itemView.findViewById(R.id.checkBeforeYouPurchaseProductNameCardView);
        }
    }


    CheckBeforeYouPurchaseProductNameTableAdapter(ArrayList<checkBeforeYouPurchaseProductNameItem> itemList) {checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList = itemList; }

    @NonNull
    @Override
    public CheckBeforeYouPurchaseProductNameTableHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkbeforeyoupurchase_productnameitem, parent, false);

        return new CheckBeforeYouPurchaseProductNameTableAdapter.CheckBeforeYouPurchaseProductNameTableHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckBeforeYouPurchaseProductNameTableHolder holder, int position) {

        final checkBeforeYouPurchaseProductNameItem currentItem = checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList.get(position);

//        Log.i("product", checkBeforeYouPurchaseProductNameItem.tableList.get(position).getProdname());
        holder.productName.setText(currentItem.getProductName());
        holder.noOfSupplier.setText("No. Of Supplier(s): " + currentItem.getNoOfSupplier().toString());

        holder.tableItemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    checkBeforeYouPurchaseProductNameItem.productNamePass = currentItem.getProductName();


                    if (!checkBeforeYouPurchaseProductNameItem.productNamePass.isEmpty()) {

                        v.getContext().startActivity(new Intent(v.getContext(), CheckBeforeYouPurchaseSupplierTableAC.class));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList.size();
    }

    public void filterList(ArrayList<checkBeforeYouPurchaseProductNameItem> searchList) {
        checkBeforeYouPurchaseProductNameItem.checkBeforeYouPurchaseTableList = searchList;
        notifyDataSetChanged();
    }
}
