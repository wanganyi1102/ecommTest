package com.ecomm.application.control;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ecomm.application.boundary.HomePageUI;
import com.ecomm.application.boundary.LoginUI;
import com.ecomm.application.entity.Transaction;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login  extends AppCompatActivity{
    static FirebaseAuth mAuth;

    public void Login(Context context, String email, String password){
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Transaction.setUsername(email);
                    Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(context, HomePageUI.class));
                } else {
                    Toast.makeText(context, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
