package com.ecomm.application.boundary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ecomm.application.R;
import com.ecomm.application.control.CartListAdapter;
import com.ecomm.application.entity.Product;

import java.util.ArrayList;

public class ShoppingCartUI extends AppCompatActivity {

    RecyclerView recycler_itemlist;
    CartListAdapter cartListAdapter;
    String jsonCartList;
//    CheckBox itemCheckBox = (CheckBox)findViewById(R.id.chk_selectitem);
    public static final ArrayList<Product> productsInCart = new ArrayList<Product>();
    public static final ArrayList<Product> selectedProducts = new ArrayList<Product>();
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> imageURLs = new ArrayList<>();
//    ArrayList<String> ratings = new ArrayList<>();
    ArrayList<String> prices = new ArrayList<>();
    ArrayList<Integer> quantity = new ArrayList<>();
    public static TextView orderTotalTextView;
    public static double total = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart_u_i);

        if(getIntent().hasExtra("price")){
            Product product = (Product) getIntent().getSerializableExtra("price");
            int check = 0;
            for(Product p :productsInCart) {
                if (product.getName().compareTo(p.getName()) == 0) {
                    p.setQuantity(p.getQuantity() + 1);
                    check = 1;
                }
            }
            if(check == 0){ //if none of product names are the same
                productsInCart.add(product);
            }
        }

        total = 0.0;
        initImageBitmaps();

        orderTotalTextView =  (TextView) findViewById(R.id.orderTotalTextView);

        //Set button to payment
        Button paymentBtn = (Button) findViewById(R.id.btn_check_out);
        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paymentIntent = new Intent(getApplicationContext(), PaymentUI.class);
                startActivity(paymentIntent);
            }
        });

        //Set back button to activity
        ImageButton backBtn = (ImageButton) findViewById(R.id.backToHome);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backHomeIntent = new Intent(getApplicationContext(), HomePageUI.class);
                startActivity(backHomeIntent);
            }
        });


//        tv_total =(TextView) findViewById(R.id.orderTotalTextView);
//
//        recycler_itemlist =(RecyclerView) findViewById(R.id.cartRecyclerView);
//        recycler_itemlist.setHasFixedSize(true);
//        recycler_itemlist.setRecycledViewPool(new RecyclerView.RecycledViewPool());
//        recycler_itemlist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
//        recycler_itemlist.getRecycledViewPool().setMaxRecycledViews(0, 0);

//        cartListAdapter = new CartListAdapter(ShoppingCartUI.this,ItemListAdapter.selecteditems);
//        recycler_itemlist.setAdapter(cartListAdapter);

//        getIntentData();
//
//        calculateTotal();
    }

//    private void getIntentData(){
//        if(getIntent()!=null && getIntent().getExtras()!=null){
//            // Get the Required Parameters for sending Order to server.
//        }
//    }
//
//    public static void calculateTotal(){
//        int i=0;
//        total=0;
//        while(i<ItemListAdapter.selecteditems.size()){
//            total=total + ( Integer.valueOf(ItemListAdapter.selecteditems.get(i).getRate()) * Integer.valueOf(ItemListAdapter.selecteditems.get(i).getQuantity()) );
//            i++;
//        }
//        tv_total.setText(""+total);
//    }
//
//    public void insertOrder(View view){
//
//        if(total>0){
//
//            Gson gson = new Gson();
//            jsonCartList = gson.toJson(ItemListAdapter.selecteditems);
//
//            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    switch (which){
//                        case DialogInterface.BUTTON_POSITIVE:
//                            //Yes button clicked
//                            placeOrderRequest();
//                            break;
//
//                        case DialogInterface.BUTTON_NEGATIVE:
//                            //No button clicked
//                            break;
//                    }
//                }
//            };
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
//            builder.setMessage("Do you want to place Order ?").setPositiveButton("Yes", dialogClickListener)
//                    .setNegativeButton("No", dialogClickListener).show();
//
//        }else{
//            Toast.makeText(CartActivity.this,"No items in Cart !",Toast.LENGTH_LONG).show();
//        }
//
//
//    }

    //put product info into lists
    private void initImageBitmaps(){
        for(Product p : productsInCart){
            titles.add(p.getName());
            imageURLs.add(p.getImageURL());
//            ratings.add(p.getRating()+"");
            prices.add(p.getPrice()+"");
            quantity.add(p.getQuantity());
        }
        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView cartRecyclerView = findViewById(R.id.cartRecyclerView);
        CartListAdapter adapter = new CartListAdapter(titles, imageURLs, prices, quantity, this);
        cartRecyclerView.setAdapter(adapter);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    };

}