package com.ecomm.application.boundary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ecomm.application.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfileUI extends AppCompatActivity {

//    private Toolbar toolbar;
//    public TextView username = (TextView) findViewById(R.id.strUserName2);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_u_i);

        //Set back button to activity
        ImageButton backBtn = (ImageButton) findViewById(R.id.userBackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backHomeIntent = new Intent(getApplicationContext(), HomePageUI.class);
                startActivity(backHomeIntent);
            }
        });

        String userEmail;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            userEmail = user.getEmail();
        } else {
            // No user is signed in
            userEmail = "";
        }
        TextView username;
        username = findViewById(R.id.strUserName2);
        username.setText(userEmail);


        //click button to change password, show the textboxes
        Button changePassBtn = (Button) findViewById(R.id.changePassBtn);
        Button confirmChange = (Button) findViewById(R.id.confirmChangePass);
        TextView newPass, confirmNewPass, oldPass;
        newPass = findViewById(R.id.newPass);
        confirmNewPass = findViewById(R.id.confirmNewPass);
        oldPass = findViewById(R.id.oldPass);
        changePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPass.setVisibility(View.VISIBLE);
                newPass.setVisibility(View.VISIBLE);
                confirmNewPass.setVisibility(View.VISIBLE);
                changePassBtn.setVisibility(View.GONE);
                confirmChange.setVisibility(View.VISIBLE);
            }
        });

        //click confirm change to change password
        confirmChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user;
                user = FirebaseAuth.getInstance().getCurrentUser();
                final String email = user.getEmail();
                AuthCredential credential = EmailAuthProvider.getCredential(email,oldPass.getText().toString().trim());

                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            user.updatePassword(newPass.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(UserProfileUI.this, "Something went wrong. Please try again later", Toast.LENGTH_LONG).show();
                                    }else {
                                        Toast.makeText(UserProfileUI.this, "Password Successfully Modified", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(UserProfileUI.this, "Authentication Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}