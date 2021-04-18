package com.ecomm.application.entity;

import com.ecomm.application.boundary.ShoppingCartUI;
import com.google.firebase.auth.FirebaseAuth;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class Transaction {

    private String transactionID;
    private static double amount;
    private static String username; //which is the email
    private static ArrayList<Product> products;
    private static Transaction onlyInstance;

    public static Transaction getInstance(){
        if (onlyInstance==null){
            onlyInstance = new Transaction();
        } return onlyInstance;
    }

    private Transaction(){
        setUsername(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        setTransactionID();
    }

    public static void addProduct(Product product){
        products.add(product);
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public static void setUsername(String email) {
        username = email;
    }

    public static void getAmount() {
        amount = ShoppingCartUI.total;
    }

    public static void setProducts(ArrayList<Product> products) {
        Transaction.products = products;
    }

    public void setTransactionID() {
        if (onlyInstance.transactionID==null){
            byte[] array = new byte[7]; // length is bounded by 7
            new Random().nextBytes(array);
            String generatedString = new String(array, Charset.forName("UTF-8"));
            transactionID = generatedString;
        }
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public static String getUsername() {
        double k = amount + products.size();
        return username;
    }
}
