package com.chemicalsafety.infosafecsi_onmoveandroid;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;


 class DialogFragment {
    private static ProgressDialog dialog;

    void callAlert(Context context, String title) {
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

    void callloadingScreen(Context context) {
//        ProgressDialog.Builder builder = new ProgressDialog.Builder(context);
//
//        builder.setMessage("Loading...");
//        builder.setCancelable(false);
//        builder.setInverseBackgroundForced(false);
//        ProgressDialog pd = builder.create();
//        pd.show();

        dialog = new ProgressDialog(context);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);

        dialog.show();

    }

    void cancelLoadingScreen() {
        dialog.dismiss();
    }
}
