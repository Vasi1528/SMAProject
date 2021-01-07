package com.example.smaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.smaproject.FirebaseHelper.userChildDatabase;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText emailText,passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        initalizeViews();
    }
    private void initalizeViews() {
        emailText = findViewById(R.id.emailtxt);
        passwordText = findViewById(R.id.pswrdtxt);


    }

    public void onRegister(View view) {
        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        if (emailText.getText().toString().isEmpty() || passwordText.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Please fill in email and password", Toast.LENGTH_SHORT).show();
            return;
        }
        final String email = emailText.getText().toString();
        final String password = passwordText.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user == null){
                        return;
                    }
                    UserModel userModel = new UserModel(emailText.getText().toString());
                    userChildDatabase.child(user.getUid()).setValue(userModel);
                    startActivity(new Intent(RegisterActivity.this, LoggedActivity.class));

                    Toast.makeText(RegisterActivity.this, "Register succeded!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Register failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}