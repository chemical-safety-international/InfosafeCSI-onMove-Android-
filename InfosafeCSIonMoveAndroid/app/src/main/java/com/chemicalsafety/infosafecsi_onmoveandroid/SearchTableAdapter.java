package com.chemicalsafety.infosafecsi_onmoveandroid;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.loginVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.searchItemList;
import com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf.csiWCF_VM;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class SearchTableAdapter extends RecyclerView.Adapter<SearchTableAdapter.SearchTableViewHolder> {




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

        public Button sdsviewBtn;


        public SearchTableViewHolder(@NonNull View itemView) {

            super(itemView);
            pnameA = itemView.findViewById(R.id.pname);
            countryA = itemView.findViewById(R.id.country);
            supplierA = itemView.findViewById(R.id.supplier);
            unnoA = itemView.findViewById(R.id.unv);
            pcodeA = itemView.findViewById(R.id.pcv);
            dateA = itemView.findViewById(R.id.dav);

            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            img3 = itemView.findViewById(R.id.img3);
            img4 = itemView.findViewById(R.id.img4);
            img5 = itemView.findViewById(R.id.img5);

            sdsviewBtn = itemView.findViewById(R.id.sdsviewBtn);
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
    public void onBindViewHolder(@NonNull SearchTableViewHolder holder, final int position) {

        searchItemList currentItem = searchItemList.tableList.get(position);

        //set values to textviews
        holder.pnameA.setText(currentItem.getProdname());
        holder.countryA.setText(currentItem.getCom_Country());
        holder.supplierA.setText(currentItem.getCompany());
        holder.unnoA.setText(currentItem.getUnno());
        holder.pcodeA.setText(currentItem.getProdcode());
        holder.dateA.setText(currentItem.getIssueDate());

        //set values to imgs
        String img1v = currentItem.getImgV1();
        String img2v = currentItem.getImgV2();
        String img3v = currentItem.getImgV3();
        String img4v = currentItem.getImgV4();
        String img5v = currentItem.getImgV5();


        try {

            //match images and set into imageviews
            if (!img1v.isEmpty() && img1v != null) {
                Class res1 = R.drawable.class;
                Field field1 = res1.getField(img1v);
                int id1 = field1.getInt(null);
                holder.img1.setImageResource(id1);
            }


            if (!img2v.isEmpty() && img2v != null) {
                Class res2 = R.drawable.class;
                Field field2 = res2.getField(img2v);
                int id2 = field2.getInt(null);
                holder.img2.setImageResource(id2);
            }

            if (!img3v.isEmpty() && img3v != null) {
                Class res3 = R.drawable.class;
                Field field3 = res3.getField(img3v);
                int id3 = field3.getInt(null);
                holder.img3.setImageResource(id3);
            }

            if (!img4v.isEmpty() && img4v != null) {
                Class res4 = R.drawable.class;
                Field field4 = res4.getField(img4v);
                int id4 = field4.getInt(null);
                holder.img4.setImageResource(id4);
            }
            if (!img5v.isEmpty() && img5v != null) {
                Class res5 = R.drawable.class;
                Field field5 = res5.getField(img5v);
                int id5 = field5.getInt(null);
                holder.img5.setImageResource(id5);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        //set button action
        holder.sdsviewBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    String sdsno = searchItemList.sdsnoArray[position];
                    System.out.println("passV sdsNO:" + sdsno);
                    String clientid, uid, rtype;
                    int apptp;

                    clientid = loginVar.clientid;
                    uid = loginVar.infosafeid;
                    apptp = loginVar.apptype;
                    rtype = "1";

                    csiWCF_VM callpreview = new csiWCF_VM();

                    // get the GHS value and go to the activity
                    if (callpreview.PreviewGHS(clientid, uid, sdsno, apptp, rtype) == true) {
                        callpreview.PreviewTI(clientid, uid, sdsno, apptp, rtype);

                        v.getContext().startActivity(new Intent(v.getContext(), SDSViewMainPageAC.class));

                    } else {
                        System.out.println("Error, call GHS failed");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }


    @Override
    public int getItemCount() {

//        return searchTableList.size();
        return searchItemList.tableList.size();
    }

    public interface ItemClickListener{
        public void startBtnActivity(int index);
    }

    public class MainActivity extends AppCompatActivity implements ItemClickListener {
        public void startBtnActivity(int index){
            Log.i("reach","here2"+index);
            Intent intent = new Intent(MainActivity.this, SDSViewMainPageAC.class);
            startActivity(intent);
        }
    }
}
