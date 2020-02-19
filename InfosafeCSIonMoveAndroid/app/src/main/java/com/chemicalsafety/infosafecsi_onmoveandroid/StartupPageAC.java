package com.chemicalsafety.infosafecsi_onmoveandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class StartupPageAC extends AppCompatActivity {

//    private Button welcomeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void openLoginActivity(View view) {
        Intent intent = new Intent(this, LoginPageAC.class);
        startActivity(intent);
    }
}
