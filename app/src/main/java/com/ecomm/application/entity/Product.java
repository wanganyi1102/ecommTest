package com.ecomm.application.entity;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Comparator;
import java.lang.Math;

public class Product implements Comparable, Serializable {
    private String name;
    private double price;
    private String url;
    private String description;
    private String ecommerceSite;
    private double rating;
    private int sales;
    private double shippingFee;
    private String imageURL;
    private String shipFrom;
    private int quantity;

    public Product(String name, float price, String url, String ecommerceSite){
        this.name = name;
        this.price = price;
        this.url = url;
        this.ecommerceSite = ecommerceSite;
        this.quantity = 1;
    }

    public Product(String name, double price, String url, String description, String ecommerceSite, double rating, double shippingFee, String imageURL, int quantity){
        this.name = name;
        this.price = price;
        this.url = url;
        this.description = description;
        this.ecommerceSite = ecommerceSite;
        this.rating = rating;
        this.shippingFee = shippingFee;
        this.imageURL = imageURL;
        this.quantity = quantity;
    }

    public Product(String name, double price, String url, String description, String ecommerceSite, double rating, double shippingFee, int sales, String imageURL, int quantity){
        this.name = name;
        this.price = price;
        this.url = url;
        this.description = description;
        this.ecommerceSite = ecommerceSite;
        this.rating = rating;
        this.shippingFee = shippingFee;
        this.imageURL = imageURL;
        this.sales = sales;
        this.quantity = quantity;
    }

    //getters
    public String getName(){
        return this.name;
    }

//    public double getPrice(){
//        return price = this.price;
//    }
    public double getPrice(){
        return price = (double) Math.round(this.price * 100) / 100;
    }

    public String getUrl(){ //convert String to URL
//        URI uri = null;
//        try {
//            uri = new URI(this.url);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        URL thisURL = null;
//        try {
//            thisURL = uri.toURL();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        return thisURL;
        return this.url;
    }

    public String getDescription(){
        return this.description;
    }

    public String getEcommerceSite(){
        return this.ecommerceSite;
    }

    public double getRating(){
        return this.rating;
    }

    public int getSales(){ return sales;}

    public double getShippingFee() {
        return this.shippingFee;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getShipFrom(){ return shipFrom; }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public int getQuantity(){return this.quantity;}


    @Override
    public int compareTo(Object o) {
        if(this.getPrice() > ((Product)o).getPrice()){ //cast object o as product
            return 1;
        }
        return -1;
    }

    public int compareToRating(Object o) {
        if(this.getRating() > ((Product)o).getRating()){ //cast object o as product
            return 1;
        }
        return -1;
    }

    public static Comparator<Product> Price = new Comparator<Product>() {

        public int compare(Product o1, Product o2) {
            return o1.compareTo(o2);
        }
    };

    public static Comparator<Product> Rating = new Comparator<Product>() {

        public int compare(Product o1, Product o2) {
            return o1.compareTo(o2);
        }
    };

}
