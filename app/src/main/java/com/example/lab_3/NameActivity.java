package com.example.lab_3;

import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NameActivity extends AppCompatActivity {

    Button button2;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);


        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity2.TEXT);

        TextView textView = findViewById(R.id.textView);
        textView.setText("Welcome " + text + "!");

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(0, intent);
                finish();
            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(1, intent);
                finish();
            }
        });
    }
}