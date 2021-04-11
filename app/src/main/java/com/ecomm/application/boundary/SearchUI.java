package com.ecomm.application.boundary;

import android.content.Intent;
import android.os.Bundle;

import com.ecomm.application.R;
import com.ecomm.application.boundary.HomePageUI;
import com.ecomm.application.entity.Product;
import com.ecomm.application.webCrawl;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class SearchUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_u_i);
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

         */

        //click on back button
        Button home = (Button) findViewById(R.id.toHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toHome = new Intent(getApplicationContext(), HomePageUI.class);
                startActivity(toHome);
            }
        });

        //initialise all textViews
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        TextView textView4 = (TextView) findViewById(R.id.textView4);
        TextView textView5 = (TextView) findViewById(R.id.textView5);
        TextView textView6 = (TextView) findViewById(R.id.textView6);
        TextView textView9 = (TextView) findViewById(R.id.textView9);
        TextView textView10 = (TextView) findViewById(R.id.textView10);
        TextView textView11 = (TextView) findViewById(R.id.textView11);

//        SearchView SearchBar = (SearchView) findViewById(R.id.searchBar);
//        //textView2.setText("(2 Pack Bundle) MILO® Instant 3in1 ACTIV-GO 18 Sachets x 27g (Expires May 2022)");
//        String newString = SearchBar.getQuery().toString();
//        textView2.setText(newString);
        //textView3.setText("$13.00");
        textView4.setText(" MILO Australian Recipe Powder Tin 1.25KG");
        textView5.setText("$17.90");
        textView6.setText("MILO Instant 3in1 ACTIVGO Sachet 18x27G (Expires May 2022)");
        textView9.setText("$6.50");
        textView10.setText("(Bundle of 2) MILO Australian Recipe Powder Refill 900G (Expires Feb 2022)");
        textView11.setText("$25.00");




        //ImageView imageView6 = (ImageView)findViewById(R.id.imageView6);
        //Picasso.with(SearchUI.this).load("https://sg-test-11.slatic.net/p/514d199bc6acc8eceb1ce3ae992599ce.jpg_400x400q90.jpg_.webp").into(imageView6);

        ImageView imageView7 = (ImageView)findViewById(R.id.imageView7);
        Picasso.with(SearchUI.this).load("https://sg-test-11.slatic.net/p/067c568c3897e5b972963a421716e1e5.jpg_400x400q90.jpg_.webp").into(imageView7);

        ImageView imageView8 = (ImageView)findViewById(R.id.imageView8);
        Picasso.with(SearchUI.this).load("https://sg-test-11.slatic.net/p/c640737f7fb5b9fe309e006928e220cc.jpg_400x400q90.jpg_.webp").into(imageView8);

        ImageView imageView9 = (ImageView)findViewById(R.id.imageView9);
        Picasso.with(SearchUI.this).load("https://sg-test-11.slatic.net/p/3b4286a37cd08cf8de06215800ca8fc4.jpg_400x400q90.jpg_.webp").into(imageView9);




        /*
        ArrayList<Product> productList = new ArrayList<Product>();


        try {
            productList = crawler.testLazadaSearch((String) searchView.getQuery());
            textView2.setText(productList.get(1).getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        ArrayList<Product> productList = new ArrayList<Product>();

        try {
            productList = this.lazadaresults((String) searchView.getQuery());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        textView2.setText(productList.get(1).getName());


        //@Test
        //public void lazadaresults() throws InterruptedException, MalformedURLException, URISyntaxException {
        webCrawl crawler = new webCrawl();
        public ArrayList<Product> lazadaresults(String q) throws InterruptedException, MalformedURLException, URISyntaxException {
            //HomePageUI search = new HomePageUI();
            //SearchView SearchBar = (SearchView) findViewById(R.id.searchBar);
            //String q = (String) SearchBar.getQuery();
            //String q = "orange";

            ArrayList<Product> productList = new ArrayList<Product>();
            productList = crawler.testLazadaSearch(q);

            System.out.println(productList.get(1).getPrice());
            System.out.println(productList.get(1).getName());
            System.out.println(productList.get(1).getUrl());
            System.out.println(productList.get(1).getEcommerceSite());

            return productList;
        }

         */





    }

}