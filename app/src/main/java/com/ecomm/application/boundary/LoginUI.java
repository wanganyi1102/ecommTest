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
import com.ecomm.application.control.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginUI extends AppCompatActivity {

    EditText mEmailPhone, mPassword;
    Button LoginBtn;
    TextView mRegBtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_ui);

        mEmailPhone = findViewById(R.id.EmailPhoneNo);
        mPassword = findViewById(R.id.Password); //Idk if this is same
        LoginBtn = findViewById(R.id.LoginBtn);
        mRegBtn = findViewById(R.id.CreateAccTxt);
        mAuth = FirebaseAuth.getInstance();

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //have to change to email/phoneNumber (need some logic)
                String email = mEmailPhone.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmailPhone.setError("Email is Required.");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required.");
                    return;
                }

                Login login = new Login();
                login.Login(getApplicationContext(), email, password);

//                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(LoginUI.this, "Login successful", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(), HomePageUI.class));
//                        } else {
//                            Toast.makeText(LoginUI.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
            }
        });

        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginToRegIntent = new Intent(getApplicationContext(), RegisterUI.class);
                startActivity(LoginToRegIntent);
            }
        });

        TextView forgotPassword = findViewById(R.id.forgotPassword);
        Button sendPassResetEmail = findViewById(R.id.sendPassResetBtn);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPassword.setVisibility(View.GONE);
                LoginBtn.setVisibility(View.GONE);
                sendPassResetEmail.setVisibility(View.VISIBLE);

            }
        });


        //Forgot Password, get Firebase to send email to reset password
        sendPassResetEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailPhone.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    mEmailPhone.setError("Email is Required.");
                    return;
                }
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginUI.this, "Email sent", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginUI.this, "Not a valid account.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}