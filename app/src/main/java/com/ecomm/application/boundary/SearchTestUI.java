package com.ecomm.application.boundary;

import android.os.Bundle;

import com.ecomm.application.entity.Product;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ecomm.application.R;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class SearchTestUI extends AppCompatActivity {

    private static final String TAG = "SearchTestUI";

    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_test_u_i);
        Log.d(TAG, "onCreate: started.");
        initImageBitmaps();
//        URI uri = null;
//        try {
//            uri = new URI("https://www.lazada.sg/products/cambodia-keo-romeat-mango-i395210387-s952532064.html?spm=a2o42.searchlist.list.3.e8f72f9eWNyvMi&search=1");
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        URL mangoURL = null;
//        try {
//            mangoURL = uri.toURL();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//        Product mango = new Product("mango", 2.50, mangoURL,
//                "Keo Romeat mango from Cambodia which is also known as the Goldstar mango locally, have a golden yellow color peel when ripen. It as a balanced sweet flavor with a bright orange flesh that consist of minimum fiber allowing it to have a smooth texture.",
//                "Lazada", 4.4, 3.9, "https://sg-test-11.slatic.net/p/b8375ca4a12d1937e2954a31955881ba.jpg_100x100q90.jpg_.webp"); //_400x400q90.jpg_.webp
//
//        Product apple = new Product("apple", 2.30, mangoURL,
//                "Keo Romeat mango from Cambodia which is also known as the Goldstar mango locally, have a golden yellow color peel when ripen. It as a balanced sweet flavor with a bright orange flesh that consist of minimum fiber allowing it to have a smooth texture.",
//                "Lazada", 4.4, 3.9, "https://sg-test-11.slatic.net/p/b8375ca4a12d1937e2954a31955881ba.jpg_100x100q90.jpg_.webp"); //_400x400q90.jpg_.webp
//
//        productArrayList.add(mango);
//        productArrayList.add(apple);
//
//        itemsRecycler = (ListView) findViewById(R.id.itemsRecycler);
//        int i = 0;
//        for(Product p : productArrayList){
//            titles[i] = p.getName();
//            images[i] = p.getImageURL();
//            prices[i] = p.getPrice() + "";
//            i++;
//        }
//
//        ItemAdapter itemAdapter = new ItemAdapter(this, titles, prices, images);
//        itemsRecycler.setAdapter(itemAdapter);
    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");
        mImages.add("https://www.lazada.sg/products/cambodia-keo-romeat-mango-i395210387-s952532064.html?spm=a2o42.searchlist.list.3.e8f72f9eWNyvMi&search=1");
        mTitles.add("mango");

        mImages.add("https://www.lazada.sg/products/cambodia-keo-romeat-mango-i395210387-s952532064.html?spm=a2o42.searchlist.list.3.e8f72f9eWNyvMi&search=1");
        mTitles.add("apple");

        initRecyclerView();
    }
    
    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: initRecycler.");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        ItemAdapter adapter = new ItemAdapter(mTitles, mImages, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}