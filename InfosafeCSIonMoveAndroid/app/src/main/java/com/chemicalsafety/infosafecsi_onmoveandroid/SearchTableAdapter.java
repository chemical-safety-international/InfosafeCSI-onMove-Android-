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
import androidx.recyclerview.widget.RecyclerView;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.SearchTableItem;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.loginVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.searchItemList;
import com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf.csiWCF_VM;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class SearchTableAdapter extends RecyclerView.Adapter<SearchTableAdapter.SearchTableViewHolder> {


    static class SearchTableViewHolder extends RecyclerView.ViewHolder {

        ImageView img1;
        ImageView img2;
        ImageView img3;
        ImageView img4;
        ImageView img5;

        TextView pnameA;
        TextView countryA;
        TextView supplierA;
        TextView unnoA;
        TextView pcodeA;
        TextView dateA;

        Button sdsviewBtn;

        TextView cardViewT;


        SearchTableViewHolder(@NonNull View itemView) {

            super(itemView);
            pnameA = itemView.findViewById(R.id.productName);
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

            cardViewT = itemView.findViewById(R.id.cardText);

        }

    }


    SearchTableAdapter(ArrayList<searchItemList> itemList) {
        searchItemList.tableList = itemList;
    }

    @NonNull
    @Override
    public SearchTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchtable_item, parent, false);
//        SearchTableViewHolder svh = new SearchTableViewHolder(v);
        return new SearchTableViewHolder(v);
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

        holder.img1.setImageDrawable(null);
        holder.img2.setImageDrawable(null);
        holder.img3.setImageDrawable(null);
        holder.img4.setImageDrawable(null);
        holder.img5.setImageDrawable(null);

        //init the constrainst set
//        ConstraintSet set = new ConstraintSet();
//        ConstraintLayout layout;

//        Log.i("single ghs img:", img1v);
        try {

            //match images and set into imageviews
            if (!img1v.isEmpty() ) {
                Class res1 = R.drawable.class;
                Field field1 = res1.getField(img1v);
                int id1 = field1.getInt(null);
                holder.img1.setImageResource(id1);
                holder.img1.setVisibility(View.VISIBLE);
            } else {
                holder.img1.setVisibility(View.GONE);
            }


            if (!img2v.isEmpty()) {
                Class res2 = R.drawable.class;
                Field field2 = res2.getField(img2v);
                int id2 = field2.getInt(null);
                holder.img2.setImageResource(id2);
                holder.img2.setVisibility(View.VISIBLE);
            } else {
                holder.img2.setVisibility(View.GONE);
            }

            if (!img3v.isEmpty() ) {
                Class res3 = R.drawable.class;
                Field field3 = res3.getField(img3v);
                int id3 = field3.getInt(null);
                holder.img3.setImageResource(id3);
                holder.img3.setVisibility(View.VISIBLE);
            } else {
                holder.img3.setVisibility(View.GONE);
            }

            if (!img4v.isEmpty()) {
                Class res4 = R.drawable.class;
                Field field4 = res4.getField(img4v);
                int id4 = field4.getInt(null);
                holder.img4.setImageResource(id4);
                holder.img4.setVisibility(View.VISIBLE);
            } else {
                holder.img4.setVisibility(View.GONE);
            }

            if (!img5v.isEmpty()) {
                Class res5 = R.drawable.class;
                Field field5 = res5.getField(img5v);
                int id5 = field5.getInt(null);
                holder.img5.setImageResource(id5);
                holder.img5.setVisibility(View.VISIBLE);
            } else {
                holder.img5.setVisibility(View.GONE);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        //set button action
        holder.sdsviewBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v) {
                try {

                    final String sdsno = searchItemList.sdsnoArray[position];
//                    System.out.println("passV sdsNO:" + sdsno);
                    final String clientid, uid, rtype;
                    final int apptp;

                    searchItemList.sdsno = sdsno;
                    clientid = loginVar.clientid;
                    uid = loginVar.infosafeid;
                    apptp = loginVar.apptype;
                    rtype = "1";

                    final csiWCF_VM callpreview = new csiWCF_VM();
                    final DialogFragment df = new DialogFragment();
                    df.callloadingScreen(v.getContext(), "Loading...");
                    // get the GHS value and go to the activity
                    Thread t= new Thread(new Runnable() {

                        public void run() {
                            if (callpreview.PreviewGHS(clientid, uid, sdsno, apptp, rtype)) {
                                if (callpreview.PreviewTI(clientid, uid, sdsno, apptp, rtype)){
                                    if (callpreview.PreviewFAID(clientid, uid, sdsno, apptp, rtype)) {
//                                        runOnUiThread(new Runnable() {
//                                            public void run() {
                                                df.cancelLoadingScreen();
                                                v.getContext().startActivity(new Intent(v.getContext(), SDSViewMainPageAC.class));

//                                            }
//                                        });
                                    } else {
                                        df.cancelLoadingScreen();
                                        Log.i("Error", "call first aid failed");
                                    }
                                } else {
                                    df.cancelLoadingScreen();
                                    Log.i("Error", "call TI information failed");
                                }

                            } else {
                                df.cancelLoadingScreen();
                                Log.i("Error", "call GHS failed");
                            }
                        }});
                    t.start();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

//        System.out.println(position);
//        System.out.println(searchItemList.tableList.size());

//        SearchTablePageAC stpac = new SearchTablePageAC();
//
        holder.cardViewT.setVisibility(View.GONE);
        if(position == (searchItemList.tableList.size() - 1)) {
//            stpac..getContext().changeText();
//            SearchTablePageAC.class.getResource(R.id.cardViewText);
            holder.cardViewT.setVisibility(View.VISIBLE);
            if(SearchTableItem.totalcount > 249) {
                holder.cardViewT.setText("Only 250 results have been displayed.\n Please refine your search criteria for more accurate results.");
            } else {

                String value = holder.itemView.getContext().getString(R.string.table_notifiy);
                holder.cardViewT.setText(value);


            }

        }

//                if(position == (searchItemList.tableList.size() - 1)) {
//                    myCallback.updateMyText("All Data");
//                }
    }


    @Override
    public int getItemCount() {

//        return searchTableList.size();
        return searchItemList.tableList.size();
    }

//    public interface ItemClickListener{
//        void startBtnActivity(int index);
//    }
//
//    @SuppressLint("Registered")
//    public class MainActivity extends AppCompatActivity implements ItemClickListener {
//        public void startBtnActivity(int index){
////            Log.i("reach","here2"+index);
//            Intent intent = new Intent(MainActivity.this, SDSViewMainPageAC.class);
//            startActivity(intent);
//        }
//    }


}
