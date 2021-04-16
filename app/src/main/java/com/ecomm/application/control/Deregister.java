package com.ecomm.application.control;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.ecomm.application.boundary.LoginUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

class Deregister {

    FirebaseAuth mAuth;

    public String deregister(){
        final String[] result = new String[1];
        mAuth.getInstance().getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    result[0] = "User account deleted.";
                } else {
                    result[0] = "Account deletion not successful";
                }
            }
        });
        return result[0];
    }
}
