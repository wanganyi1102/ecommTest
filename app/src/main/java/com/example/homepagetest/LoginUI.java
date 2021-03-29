package com.example.homepagetest;

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

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginUI.this, "Login successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomePageUI.class));
                        } else {
                            Toast.makeText(LoginUI.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterUI.class));
            }
        });

    }
}
//public class LoginUI extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        Button loginBtn = (Button) findViewById(R.id.loginBtn); //declare login btn
//        Login login = new Login();
//        loginBtn.setOnClickListener(new View.OnClickListener() { //upon clicking on login btn
//            @Override
//            public void onClick(View v) {
//                EditText PhoneEmailEditText = (EditText) findViewById(R.id.PhoneEmailEditText);
//                EditText enterPasswordEditText = (EditText) findViewById(R.id.enterPasswordEditText);
//                String phoneEmail = PhoneEmailEditText.getText().toString(); //get texts entered
//                String password = enterPasswordEditText.getText().toString();
//
//                //if(login.checkExist(phoneEmail) == true){ //login successful
//                    Intent loginSuccessIntent = new Intent(getApplicationContext(), HomePageUI.class);
//                    loginSuccessIntent.putExtra("com.example.ACCOUNT", "xiaoming123");
//                    startActivity(loginSuccessIntent);
//                /*}
//                else{
//                    System.out.println("not successful");
//                }*/
//            }
//        });
//
//        TextView registerBtn = (TextView) findViewById(R.id.registerBtn);
//        registerBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent registerIntent = new Intent(getApplicationContext(), RegisterUI.class);
//                startActivity(registerIntent);
//            }
//        });
//
//    }
//}