package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.loginVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.searchVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf.csiWCF_VM;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

public class ScanBarcodePageAC extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int REQUST_CAMERA = 1;
    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_scan_barcode_page_ac);

        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkPermission()) {
                Toast.makeText(ScanBarcodePageAC.this, "Permission is granted!", Toast.LENGTH_LONG).show();
            } else {
                requestPermission();
            }
        }
    }

    private boolean checkPermission() {
        return (ContextCompat.checkSelfPermission(ScanBarcodePageAC.this, CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUST_CAMERA);
    }

    public void onRequestPermissionResult(int requestCode, String permission[], int grantResults[]) {
        switch(requestCode) {
            case REQUST_CAMERA :
                if (grantResults.length > 0) {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted) {
                        Toast.makeText(ScanBarcodePageAC.this, "Permission Granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(ScanBarcodePageAC.this, "Permission Denied", Toast.LENGTH_LONG).show();
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            displayAlertMessage("You need to allow access for both permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            requestPermissions(new String[]{CAMERA} , REQUST_CAMERA);
                                        }
                                    });
                            return;
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkPermission()) {
                if(scannerView == null) {
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            } else {
                requestPermission();
            }
        }
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        scannerView.stopCamera();

    }

    public void displayAlertMessage(String message, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(ScanBarcodePageAC.this)
                .setMessage(message)
                .setPositiveButton("OK", listener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();

    }

    @Override
    public void handleResult(Result result) {
        final String barcodeValue = result.getText();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want to use\n" + "\"" + barcodeValue + "\"" + " to search?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Log.i("Scanning", "Starting scan");
                searchVar.barcodeInput = barcodeValue;
                searchVar.pcodeInput = "";
                searchVar.pnameInput = "";
                searchVar.supplierInput = "";

                final DialogFragment df = new DialogFragment();
                //call the Search WCF
                final csiWCF_VM wcf =new csiWCF_VM();
                df.callloadingScreen(ScanBarcodePageAC.this);
                Thread t= new Thread(new Runnable() {

                    public void run() {
                        if (wcf.Search(searchVar.pnameInput, searchVar.pcodeInput, searchVar.supplierInput, searchVar.barcodeInput, loginVar.clientid, loginVar.infosafeid, loginVar.apptype)) {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    Log.i("Scanning", "scan successfully");
                                    df.cancelLoadingScreen();
                                    Intent intent = new Intent(ScanBarcodePageAC.this, SearchTablePageAC.class);
                                    startActivity(intent);
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    df.cancelLoadingScreen();
                                    Log.i("Scanning", "scan failed");
                                    Log.i("error", "Search failed!.");
                                    df.callAlert(ScanBarcodePageAC.this, "Search Failed!\nPlease check the connection and try again.");
                                }
                            });
                        }
                    }});
                t.start();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                scannerView.resumeCameraPreview(ScanBarcodePageAC.this);
            }
        });
        AlertDialog alert = builder.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();
    }
}
