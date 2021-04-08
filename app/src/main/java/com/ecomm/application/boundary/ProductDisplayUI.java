package com.ecomm.application.boundary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecomm.application.R;
import com.ecomm.application.entity.Product;

import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

public class ProductDisplayUI extends AppCompatActivity {

    private Product mango;

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display_u_i);

        if(getIntent().hasExtra("com.ecomm.application.PRODUCT_INFO")){
            mango = (Product) getIntent().getSerializableExtra("com.ecomm.application.PRODUCT_INFO");  //Extras("com.ecomm.application.PRODUCT_INFO", mango);
        }

        //set description to product description
        TextView descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        String description = mango.getDescription();
        descriptionTextView.setText("Product description: " + "\n" + description);

        //set price to product price
        TextView priceTextView = (TextView) findViewById(R.id.priceTextView);
        priceTextView.setText(String.valueOf(mango.getPrice()));

        //set image to product image

        String imageURL = mango.getImageURL();
        ImageView productImage = (ImageView) findViewById(R.id.productImage);
        productImage.setImageDrawable(LoadImageFromWebOperations(imageURL));

        //click gotoecommersewebsite button
        Button externalSiteBtn = (Button) findViewById(R.id.externalSiteBtn);
        externalSiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlToVisit = mango.getUrl().toString();
                Intent gotoSite = new Intent(Intent.ACTION_VIEW, Uri.parse(urlToVisit));
                startActivity(gotoSite);
            }
        });

        //click add to cart
        Button addToCartBtn = (Button) findViewById(R.id.addToCartBtn);
        // add code for adding to cart

        //click on back button
        Button backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backHome = new Intent(getApplicationContext(), HomePageUI.class);
                startActivity(backHome);
            }
        });

    }
}