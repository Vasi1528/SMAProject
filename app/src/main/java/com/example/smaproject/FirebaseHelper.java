package com.example.smaproject;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {
    public static final DatabaseReference userChildDatabase = FirebaseDatabase.getInstance().getReference().child("User");
    public static final DatabaseReference userDatabase = FirebaseDatabase.getInstance().getReference("User");
}
