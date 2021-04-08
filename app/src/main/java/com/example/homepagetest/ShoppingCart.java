package com.example.homepagetest;

import com.ecomm.application.entity.Product;

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Product> productList;

    public float calculateTotalPrice(){
        float totalPrice = 0;
        for(Product p : productList){
            totalPrice += p.getPrice();
        }
        return totalPrice;
    }
}
