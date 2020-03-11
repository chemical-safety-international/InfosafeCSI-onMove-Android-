package com.chemicalsafety.infosafecsi_onmoveandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;


public class DialogFragment {

    public void callAlert(Context context, String title) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);

        builder1.setMessage(title);

        builder1.setCancelable(true);

        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert1 = builder1.create();
        alert1.show();
    }
}
