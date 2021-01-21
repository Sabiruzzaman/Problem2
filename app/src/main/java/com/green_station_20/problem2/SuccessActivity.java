package com.green_station_20.problem2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);


        TextView textView = findViewById(R.id.editTextId);

        String s = getIntent().getStringExtra("msg");
        textView.setText(s);

    }
}