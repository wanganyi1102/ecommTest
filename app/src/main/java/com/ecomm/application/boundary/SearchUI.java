package com.ecomm.application.boundary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ecomm.application.R;

public class SearchUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results_ui);

        /*
        //click on back button
        Button backBtn = (Button) findViewById(R.id.backToHome);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gobackHome = new Intent(getApplicationContext(), HomePageUI.class);
                startActivity(gobackHome);
            }
        });

         */

    }
}
