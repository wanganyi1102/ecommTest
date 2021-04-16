package com.ecomm.application.control;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ecomm.application.boundary.HomePageUI;
import com.ecomm.application.boundary.RegisterUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText mEmail, mPhone, mEmailPhone, mPassword, mConfirmPass;
    Button RegisterBtn;
    TextView mLoginBtn;
    static FirebaseAuth mAuth;

    public void Register(Context context, String email, String password) {
        mAuth = FirebaseAuth.getInstance();

        //register user firebase
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(context, "User created", Toast.LENGTH_SHORT).show();
//                            Toast.makeText(RegisterUI.this, "User created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(context, HomePageUI.class));
                } else {
                    Toast.makeText(context, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            Toast.makeText(RegisterUI.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
