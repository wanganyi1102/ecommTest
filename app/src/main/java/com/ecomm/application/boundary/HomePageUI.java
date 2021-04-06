package com.ecomm.application.boundary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ecomm.application.R;
import com.ecomm.application.webCrawl;

import org.w3c.dom.Text;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class HomePageUI extends AppCompatActivity {

    EditText mSearchTerm;
    Button LoginBtn;
    webCrawl crawler = new webCrawl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchTerm = findViewById(R.id.searchBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get search term that was keyed in
        String searchTerm = mSearchTerm.getText().toString().trim();

        if (TextUtils.isEmpty(searchTerm)) {
            mSearchTerm.setError("Enter search term.");
            return;
        }

        try {
            crawler.testLazadaSearch(searchTerm);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }



        //get intent passed from loginUI
        if (getIntent().hasExtra("com.example.ACCOUNT")){
            String username = getIntent().getExtras().getString("com.example.ACCOUNT");
        }

        // click on cart button
        ImageButton cartBtn = (ImageButton) findViewById(R.id.cartBtn);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cartIntent = new Intent(getApplicationContext(), ShoppingCartUI.class);
                startActivity(cartIntent);
            }
        });

        //(test filter) click on filter button
        TextView filterSearchBtn = (TextView) findViewById(R.id.filterSearchBtn);
        filterSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent filterIntent = new Intent(getApplicationContext(), FilterUI.class);
                startActivity(filterIntent);
            }
        });

    }

}