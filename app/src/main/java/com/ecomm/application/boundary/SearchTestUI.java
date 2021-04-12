package com.ecomm.application.boundary;

import android.content.Intent;
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
import android.widget.SearchView;
import android.widget.TextView;

import com.ecomm.application.R;

import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import static com.ecomm.application.boundary.HomePageUI.q;

public class SearchTestUI extends AppCompatActivity {

    private ArrayList<Product> productsToDisplay = new ArrayList<Product>();
    private ArrayList<String> pTitles = new ArrayList<>();
    private ArrayList<String> pImages = new ArrayList<>();
    private ArrayList<String> pPrices = new ArrayList<>();

    public static ArrayList<Product> productList = new ArrayList<Product>();
    public static int clickedposition;
//    androiddrive2 ad = new androiddrive2();


    public void setClickedposition(int clickedposition) {
        SearchTestUI.clickedposition = clickedposition;
        displayProductDet(clickedposition);
    }

    public void displayProductDet(int clickedposition){
        Product productToOpen = productsToDisplay.get(clickedposition);
        Intent displayProduct = new Intent(getApplicationContext(), ProductDisplayUI.class);
        displayProduct.putExtra("productdeets", productToOpen);
        startActivity(displayProduct);
    }

    TextView Filter = (TextView) findViewById(R.id.filterText);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_test_u_i);

        //pass in arraylist to be displayed
        if(getIntent().hasExtra("com.ecomm.application.PRODUCT_LIST")){
            productsToDisplay = (ArrayList<Product>) getIntent().getSerializableExtra("com.ecomm.application.PRODUCT_LIST");
        }

        try {
            initImageBitmaps();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

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

    public void initImageBitmaps() throws InterruptedException, IOException, URISyntaxException {

//        productList = ad.goingqoo(q);
//        System.out.println(productList.get(1));

        for(Product p : productsToDisplay){
            pImages.add(p.getImageURL());
            pTitles.add(p.getName());
            pPrices.add(p.getPrice()+"");
//            ratings.add(p.getRating()+"");
//            prices.add(p.getPrice()+"");
        }
//        pImages.add("https://www.lazada.sg/products/cambodia-keo-romeat-mango-i395210387-s952532064.html?spm=a2o42.searchlist.list.3.e8f72f9eWNyvMi&search=1");
//        pTitles.add("mango");
//        pPrices.add("$2.50");
//
//        pImages.add("https://www.lazada.sg/products/cambodia-keo-romeat-mango-i395210387-s952532064.html?spm=a2o42.searchlist.list.3.e8f72f9eWNyvMi&search=1");
//        pTitles.add("apple");
//        pPrices.add("$2.10");

        initRecyclerView();
    }


    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.searchRecyclerView);
        ItemAdapter adapter = new ItemAdapter(pImages, pTitles, pPrices, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}