package com.example.smaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView aboutTextView;
    private Button registerButton,loginButton,aboutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initalizeViews();
        setOnClickListeners();


    }

    private void initalizeViews() {
        registerButton = findViewById(R.id.btn_register);
        loginButton = findViewById(R.id.btn_login);
        aboutButton = findViewById(R.id.btn_about);
        aboutTextView = findViewById(R.id.tv_about);
    }
    private void setOnClickListeners() {
        aboutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(aboutTextView.getVisibility() == View.GONE)
                    aboutTextView.setVisibility(View.VISIBLE);
                else
                    aboutTextView.setVisibility(View.GONE);
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}