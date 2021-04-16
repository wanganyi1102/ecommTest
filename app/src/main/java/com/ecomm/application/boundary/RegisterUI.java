package com.ecomm.application.boundary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ecomm.application.R;
import com.ecomm.application.control.Register;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterUI extends AppCompatActivity {

    EditText mEmail, mPhone, mEmailPhone, mPassword, mConfirmPass;
    Button RegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_ui);

        mEmail = findViewById(R.id.EmailInput);
        mPhone = findViewById(R.id.PhoneInput);
        //mEmailPhone = findViewById(R.id.phoneEmailEditText);
        mPassword = findViewById(R.id.Password);
        mConfirmPass = findViewById(R.id.confirmPassword);
        RegisterBtn = findViewById(R.id.RegisterBtn);
        mLoginBtn = findViewById(R.id.RegtoLogin);

        mAuth = FirebaseAuth.getInstance();


//        if (mAuth.getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext(), HomePageUI.class));
//            finish();
//        }

        RegisterBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim(); //currently only works on Email
                String password = mPassword.getText().toString().trim();
                String confirmPass = mConfirmPass.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required.");
                    return;
                }


//                if (password.equals(confirmPass)){
//                    mConfirmPass.setError("Password does not match");
//                    return;
//                }


                //register user firebase
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterUI.this, "User created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),HomePageUI.class));
                        }else{
                            Toast.makeText(RegisterUI.this, "Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent RegToLoginIntent = new Intent(getApplicationContext(), LoginUI.class);
                startActivity(RegToLoginIntent);
            }
        });

    }
}