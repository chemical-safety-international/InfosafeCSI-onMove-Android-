package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;

import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.loginVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.Entities.searchVar;
import com.chemicalsafety.infosafecsi_onmoveandroid.csiwcf.csiWCF_VM;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;


import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class OCRPageAC extends AppCompatActivity {

    private Button reScanButton, ocrSearchButton;
    private ImageView scanImage;
    private EditText scanText;

    private static final int REQUST_CAMERA = 1;
    private static final int REQUST_STORAGE = 1;

    private static final int IMAGE_PICK_CAMERA_CODE = 1000;


    Uri image_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr_page_ac);

        reScanButton = findViewById(R.id.rescanButton);
        ocrSearchButton = findViewById(R.id.ocrSearchButton);

        scanImage = findViewById(R.id.scanImageView);

        scanText = findViewById(R.id.scanText);

        setupUI(findViewById(R.id.OCRCL));

        //check the permission of camera and storage
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkCameraPermission()) {
//                Toast.makeText(OCRPageAC.this, "Permission is granted!", Toast.LENGTH_LONG).show();
            } else {
                requestCameraPermission();
            }

            if (checkStoragePermission()) {

            } else {
                requestStoragePermission();
            }
        }

        dispatchTakePictureIntent();
    }

    //hide keyboard
    public void setupUI(View view) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @SuppressLint("ClickableViewAccessibility")
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(OCRPageAC.this);
                    return false;
                }
            });
        }
    }

    //create camera activity
    private void dispatchTakePictureIntent() {

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "NewPic");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image To text");

        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(takePictureIntent, IMAGE_PICK_CAMERA_CODE);

    }

    private boolean checkCameraPermission() {
        return (ContextCompat.checkSelfPermission(OCRPageAC.this, CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUST_CAMERA);
    }

    private boolean checkStoragePermission() {
        return (ContextCompat.checkSelfPermission(OCRPageAC.this, WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUST_STORAGE);
    }

    //handle result from camera action
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        Log.i("1", "reached onactivityResult");

        //check if user pressed the ok button
        if (resultCode == RESULT_OK) {
//            Log.i("2", "reached resultcode == result-ok");
            if (requestCode == IMAGE_PICK_CAMERA_CODE) {
//                Log.i("3", "reached requestcode == Image-Pick-camera-code");
                //start crop function
                CropImage.activity(image_uri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(this);
            }

        } else if (resultCode == RESULT_CANCELED) {
            super.onBackPressed();
        }

        //handle the cropped image
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            Log.i("4", "reached requestcode == CropImage.crop-image-activity-request-code");

            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();

                scanImage.setImageURI(resultUri);

                BitmapDrawable bitmapDrawable = (BitmapDrawable)scanImage.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();

                TextRecognizer recognizer = new TextRecognizer.Builder(getApplicationContext()).build();

                if (!recognizer.isOperational()) {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                } else {
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();

                    SparseArray<TextBlock> items = recognizer.detect(frame);
                    StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < items.size(); i++) {
                        TextBlock myItem = items.valueAt(i);
                        sb.append(myItem.getValue());
                        sb.append("\n");
                    }

                    scanText.setText(sb.toString());
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();
                Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
                Log.i("result Error: ", ""+error);

            }
        }


    }

    public void setReScanButton(View v) {
        dispatchTakePictureIntent();
    }

    public void setOcrSearchButton(View v) {

        searchVar.pnameInput = scanText.getText().toString().trim();

        final DialogFragment df = new DialogFragment();

        if (searchVar.pnameInput.isEmpty()) {
            df.callAlert(OCRPageAC.this, "Search input empty!\nPlease check your input and try again.");
        } else {
            if (!searchVar.pnameInput.isEmpty() && searchVar.pnameInput.length() < 3) {
                df.callAlert(OCRPageAC.this, "Search failed!\nPlease enter more than 2 characters for product name!" );
            } else {
                //call the Search WCF
                final csiWCF_VM wcf =new csiWCF_VM();
                df.callloadingScreen(OCRPageAC.this, "Searching...");
                Thread t= new Thread(new Runnable() {

                    public void run() {
                        if (wcf.Search(searchVar.pnameInput, "", "", "", loginVar.clientid, loginVar.infosafeid, loginVar.apptype)) {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    df.cancelLoadingScreen();
                                    Intent intent = new Intent(OCRPageAC.this, SearchTablePageAC.class);
                                    startActivity(intent);
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    df.cancelLoadingScreen();
                                    Log.i("error", "Search failed!.");
                                    df.callAlert(OCRPageAC.this, "Search Failed!\nPlease check the input and connection.");
                                }
                            });
                        }
                    }});
                t.start();
            }
        }
    }

//    private void runTextRecognition(Bitmap  captureBitmap) {
//        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(captureBitmap);
//        FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
//
//        detector.processImage(image)
//                .addOnSuccessListener(
//                        new OnSuccessListener<FirebaseVisionText>() {
//                            @Override
//                            public void onSuccess(FirebaseVisionText texts) {
//                                processTextRecognitionResult(texts);
//                            }
//
//                        }
//                );
//    }
//
//    private void processTextRecognitionResult(FirebaseVisionText texts) {
//        List<FirebaseVisionText.TextBlock> blocks = texts.getTextBlocks();
//        if(blocks.size() == 0) {
//            Log.i("no result", "no text recognised");
//            return;
//        }
//
//        scanText.setText(blocks.toString());
////        for (int i = 0; i < blocks.size(); i++) {
////            List<FirebaseVisionText.Line> lines = blocks.get(i).getLines();
////        }
//    }

    public void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getRootView().getWindowToken(), 0);
    }
}
