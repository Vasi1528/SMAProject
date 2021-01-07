package com.example.smaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;

import static com.example.smaproject.FirebaseHelper.userChildDatabase;

public class LoggedActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser user = firebaseAuth.getCurrentUser();
    HashMap<String,Object> map = new HashMap<>();
    private TextView tvName,tvPhone,tvCity;
    private EditText etName,etPhone,etCity;
    private Button logoutButton,changeButton,okButton,changeButton2,okButton2,changeButton3,okButton3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);
        initalizeViews();
        setOnClickListeners();
    }

    private void initalizeViews() {
        logoutButton = findViewById(R.id.btn_logout);
        changeButton = findViewById(R.id.btn_change);
        okButton = findViewById(R.id.btn_changed);
        etName = findViewById(R.id.et_name);
        tvName = findViewById(R.id.tv_name);
        changeButton2 = findViewById(R.id.btn_change2);
        okButton2 = findViewById(R.id.btn_changed2);
        etPhone = findViewById(R.id.et_phone);
        tvPhone = findViewById(R.id.tv_phone);
        changeButton3 = findViewById(R.id.btn_change3);
        okButton3 = findViewById(R.id.btn_changed3);
        etCity = findViewById(R.id.et_city);
        tvCity = findViewById(R.id.tv_city);
    }
    private void setOnClickListeners() {
        changeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tvName.setVisibility(View.GONE);
                etName.setVisibility(View.VISIBLE);
                changeButton.setVisibility(View.GONE);
                okButton.setVisibility(View.VISIBLE);
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                HashMap<String,Object> map = new HashMap<>();
                map.put("name",etName.getText().toString());
                //SecondUserModel userModel = new SecondUserModel(etName.getText().toString());
                userChildDatabase.child(user.getUid()).updateChildren(map);
                tvName.setText(etName.getText().toString());
                tvName.setVisibility(View.VISIBLE);
                etName.setVisibility(View.GONE);
                changeButton.setVisibility(View.VISIBLE);
                okButton.setVisibility(View.GONE);
            }
        });
        changeButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tvPhone.setVisibility(View.GONE);
                etPhone.setVisibility(View.VISIBLE);
                changeButton2.setVisibility(View.GONE);
                okButton2.setVisibility(View.VISIBLE);
            }
        });
        okButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                HashMap<String,Object> map = new HashMap<>();
                map.put("phone",etPhone.getText().toString());
                userChildDatabase.child(user.getUid()).updateChildren(map);
                tvPhone.setText(etPhone.getText().toString());
                tvPhone.setVisibility(View.VISIBLE);
                etPhone.setVisibility(View.GONE);
                changeButton2.setVisibility(View.VISIBLE);
                okButton2.setVisibility(View.GONE);
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent = new Intent(LoggedActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        changeButton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tvCity.setVisibility(View.GONE);
                etCity.setVisibility(View.VISIBLE);
                changeButton3.setVisibility(View.GONE);
                okButton3.setVisibility(View.VISIBLE);
            }
        });
        okButton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                HashMap<String,Object> map = new HashMap<>();
                map.put("city",etCity.getText().toString());
                //SecondUserModel userModel = new SecondUserModel(etName.getText().toString());
                userChildDatabase.child(user.getUid()).updateChildren(map);
                tvCity.setText(etCity.getText().toString());
                tvCity.setVisibility(View.VISIBLE);
                etCity.setVisibility(View.GONE);
                changeButton3.setVisibility(View.VISIBLE);
                okButton3.setVisibility(View.GONE);
            }
        });
    }
}