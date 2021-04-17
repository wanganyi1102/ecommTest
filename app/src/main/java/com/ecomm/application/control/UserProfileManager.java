package com.ecomm.application.control;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ecomm.application.boundary.UserProfileUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserProfileManager {


    public static void changePassword(Context context, TextView oldPass, TextView newPass){
        FirebaseUser user;
        user = FirebaseAuth.getInstance().getCurrentUser();
        final String email = user.getEmail();
        AuthCredential credential = EmailAuthProvider.getCredential(email,oldPass.getText().toString().trim());
//        final String[] result = new String[1];

        user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    user.updatePassword(newPass.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(context, "Something went wrong. Please try again later", Toast.LENGTH_LONG).show();
//                                result[0] = "Something went wrong. Please try again later";
                            }else {
                                Toast.makeText(context, "Password Successfully Modified", Toast.LENGTH_LONG).show();
//                                result[0] = "Password Successfully Modified";
                            }
                        }
                    });
                }else {
                    Toast.makeText(context, "Authentication Failed", Toast.LENGTH_LONG).show();
//                    result[0] = "Authentication Failed";
                }
            }
        });
//        return result[0];
    }
}
