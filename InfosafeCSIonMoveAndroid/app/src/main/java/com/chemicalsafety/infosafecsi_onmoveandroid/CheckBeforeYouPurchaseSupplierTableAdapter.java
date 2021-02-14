package com.chemicalsafety.infosafecsi_onmoveandroid;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.SearchTableItem;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.checkBeforeYouPurchaseProductNameItem;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.checkBeforeYouPurchaseSupplierItem;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.searchItemList;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CheckBeforeYouPurchaseSupplierTableAdapter extends RecyclerView.Adapter<CheckBeforeYouPurchaseSupplierTableAdapter.CheckBeforeYouPurchaseSupplierTableHolder> {

    static class CheckBeforeYouPurchaseSupplierTableHolder extends RecyclerView.ViewHolder {
        TextView supplierTextView;
        TextView noOfProductTextView;
        TextView issueDateTextView;

        CardView supplierCardView;

        CheckBeforeYouPurchaseSupplierTableHolder(@NonNull View itemView) {
            super(itemView);
            supplierTextView = itemView.findViewById(R.id.supplierTextView);
            noOfProductTextView = itemView.findViewById(R.id.noOfProductTextView);
            issueDateTextView = itemView.findViewById(R.id.issueDateTextView);

            supplierCardView = itemView.findViewById(R.id.supplierCardView);
        }
    }


    CheckBeforeYouPurchaseSupplierTableAdapter(ArrayList<checkBeforeYouPurchaseSupplierItem> itemList) {checkBeforeYouPurchaseSupplierItem.supplierItemsTableList = itemList; }

    @NonNull
    @Override
    public CheckBeforeYouPurchaseSupplierTableHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkbeforeyoupurchase_supplieritem, parent, false);

        return new CheckBeforeYouPurchaseSupplierTableAdapter.CheckBeforeYouPurchaseSupplierTableHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull CheckBeforeYouPurchaseSupplierTableHolder holder, int position) {

        final checkBeforeYouPurchaseSupplierItem currentItem = checkBeforeYouPurchaseSupplierItem.supplierItemsTableList.get(position);

        holder.supplierTextView.setText(currentItem.getSupplier());
        holder.noOfProductTextView.setText("No. Of Supplier(s): " + currentItem.getNoOfProduct().toString());
        holder.issueDateTextView.setText("Issue Date: " + currentItem.getIssueDate());

        holder.supplierCardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                searchItemList.tableListForSearch.clear();

                try {
                    String productName = checkBeforeYouPurchaseProductNameItem.productNamePass;
                    String supplier = currentItem.getSupplier();


                    for (int i = 0; i < searchItemList.tableList.size(); i++) {

                        if (productName.equals(searchItemList.tableList.get(i).getProdname()) && supplier.equals(searchItemList.tableList.get(i).getCompany())) {
                            //store each item
                            String pname2 = searchItemList.tableList.get(i).getProdname();

                            String com2 = searchItemList.tableList.get(i).getCompany();

                            String date2 = searchItemList.tableList.get(i).getIssueDate();

//                                    JSONObject key1 = item.getJSONObject("key");
                            String key2 = searchItemList.tableList.get(i).getSynno();

                            String unno2 = searchItemList.tableList.get(i).getUnno();

                            String code2 = searchItemList.tableList.get(i).getProdcode();

                            String coun1 = searchItemList.tableList.get(i).getCom_Country();

                            String pitgs = searchItemList.tableList.get(i).getGHS_Pictogram();


                            //fix and match the pitgrams
                            if (pitgs.contains(",")) {
                                String[] imgs = pitgs.split(",");

                                int num = imgs.length;
                                String[] imgsCode = new String[num];

                                for (int n = 0; n < num; n++) {

                                    if (imgs[n].toLowerCase().trim().equals("flame")) {
                                        String imgCode = "ghs02";
                                        imgsCode[n] = imgCode;
                                    } else if (imgs[n].toLowerCase().trim().equals("skull and crossbones")) {
                                        String imgCode = "ghs06";
                                        imgsCode[n] = imgCode;
                                    } else if (imgs[n].toLowerCase().trim().equals("flame over circle")) {
                                        String imgCode = "ghs03";
                                        imgsCode[n] = imgCode;
                                    } else if (imgs[n].toLowerCase().trim().equals("exclamation mark")) {
                                        String imgCode = "ghs07";
//                            int imgCode = R.drawable.ghs07;
                                        imgsCode[n] = imgCode;
                                    } else if (imgs[n].toLowerCase().trim().equals("environment")) {
                                        String imgCode = "ghs09";
                                        imgsCode[n] = imgCode;
                                    } else if (imgs[n].toLowerCase().trim().equals("health hazard")) {
                                        String imgCode = "ghs08";
                                        imgsCode[n] = imgCode;
                                    } else if (imgs[n].toLowerCase().trim().equals("corrosion")) {
                                        String imgCode = "ghs05";
                                        imgsCode[n] = imgCode;
                                    } else if (imgs[n].toLowerCase().trim().equals("gas cylinder")) {
                                        String imgCode = "ghs04";
                                        imgsCode[n] = imgCode;
                                    } else if (imgs[n].toLowerCase().trim().equals("exploding bomb")) {
                                        String imgCode = "ghs01";
                                        imgsCode[n] = imgCode;
                                    } else {
                                        imgsCode[n] = "";
                                    }
//                        Log.i("imgsCode", imgsCode[n]);

                                }

                                if (num == 2) {
                                    searchItemList.tableListForSearch.add(new searchItemList(com2, date2, pname2, unno2, code2, pitgs, coun1, key2, imgsCode[0], imgsCode[1], "", "", ""));

                                } else if (num == 3) {
                                    searchItemList.tableListForSearch.add(new searchItemList(com2, date2, pname2, unno2, code2, pitgs, coun1, key2, imgsCode[0], imgsCode[1], imgsCode[2], "", ""));

                                } else if (num == 4) {
                                    searchItemList.tableListForSearch.add(new searchItemList(com2, date2, pname2, unno2, code2, pitgs, coun1, key2, imgsCode[0], imgsCode[1], imgsCode[2], imgsCode[3], ""));

                                } else if (num == 5) {
                                    searchItemList.tableListForSearch.add(new searchItemList(com2, date2, pname2, unno2, code2, pitgs, coun1, key2, imgsCode[0], imgsCode[1], imgsCode[2], imgsCode[3], imgsCode[4]));

                                }

                            } else {

                                String ghsimginput = pitgs.trim().toLowerCase();
                                String imgCode;
                                if (ghsimginput.toLowerCase().trim().equals("flame")) {
                                    imgCode = "ghs02";

                                } else if (ghsimginput.toLowerCase().trim().equals("skull and crossbones")) {
                                    imgCode = "ghs06";

                                } else if (ghsimginput.toLowerCase().trim().equals("flame over circle")) {
                                    imgCode = "ghs03";

                                } else if (ghsimginput.toLowerCase().trim().equals("exclamation mark")) {
                                    imgCode = "ghs07";
//                            int imgCode = R.drawable.ghs07;

                                } else if (ghsimginput.toLowerCase().trim().equals("environment")) {
                                    imgCode = "ghs09";

                                } else if (ghsimginput.toLowerCase().trim().equals("health hazard")) {
                                    imgCode = "ghs08";

                                } else if (ghsimginput.toLowerCase().trim().equals("corrosion")) {
                                    imgCode = "ghs05";

                                } else if (ghsimginput.toLowerCase().trim().equals("gas cylinder")) {
                                    imgCode = "ghs04";

                                } else if (ghsimginput.toLowerCase().trim().equals("exploding bomb")) {
                                    imgCode = "ghs01";

                                } else {
                                    imgCode = "";
                                }
                                searchItemList.tableListForSearch.add(new searchItemList(com2, date2, pname2, unno2, code2, pitgs, coun1, key2, imgCode, "", "", "", ""));

                            }

                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                v.getContext().startActivity(new Intent(v.getContext(), SearchTablePageAC.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return checkBeforeYouPurchaseSupplierItem.supplierItemsTableList.size();
    }

    public void filterList(ArrayList<checkBeforeYouPurchaseSupplierItem> searchList) {
        checkBeforeYouPurchaseSupplierItem.supplierItemsTableList = searchList;
        notifyDataSetChanged();
    }
}
