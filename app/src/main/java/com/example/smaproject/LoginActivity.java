package com.example.smaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button loginButton,cancelButton;
    private EditText emailText,passwordText;
    public void updateUI(FirebaseUser account){

        if(account != null){
            Toast.makeText(this,"U Signed In successfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,LoggedActivity.class));

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initalizeViews();
        setOnClickListeners();
        mAuth = FirebaseAuth.getInstance();
    }


    private void setOnClickListeners() {


        cancelButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Hw6Activity.this,H73Activity.class);
                //startActivity(intent);
            }
        });
    }
    public void onLogin(View view) {
        mAuth = FirebaseAuth.getInstance();
        if (emailText.getText().toString().isEmpty() || passwordText.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Please fill in email and password", Toast.LENGTH_SHORT).show();
            return;
        }
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(LoginActivity.this,LoggedActivity.class);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "Login succeded", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    private void initalizeViews() {
        loginButton = findViewById(R.id.btn_login);
        emailText = findViewById(R.id.et_email);
        passwordText = findViewById(R.id.et_password);
        cancelButton = findViewById(R.id.btn_cancel);
    }
}