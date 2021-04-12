package com.ecomm.application;

import android.content.Intent;
import android.os.Bundle;

import com.ecomm.application.boundary.HomePageUI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageButton;

public class PaymentComplete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_complete_ui);

        ImageButton backBtn = (ImageButton) findViewById(R.id.imageButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backHomeIntent = new Intent(getApplicationContext(), HomePageUI.class);
                startActivity(backHomeIntent);
            }
        });
    }
}