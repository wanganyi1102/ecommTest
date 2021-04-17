package com.ecomm.application.control;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ecomm.application.boundary.LoginUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Deregister {

    final static FirebaseAuth mAuth = null;

    public static void deregister(Context context){
//        final String[] result = new String[1];
        mAuth.getInstance().getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
//                    result[0] = "User account deleted.";
                    Toast.makeText(context, "User account deleted", Toast.LENGTH_SHORT).show();
                } else {
//                    result[0] = "Account deletion not successful";
                    Toast.makeText(context, "Account deletion not successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        return result[0];
    }
}
