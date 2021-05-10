package com.example.lab6_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class MixActivity extends AppCompatActivity {

    int backIndex = 0;
    int firstIndex = 0;
    int secondIndex = 0;
    int thirdIndex = 0;

    Spinner backSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mix);
    }

    public void switchActivities(View view) {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
}