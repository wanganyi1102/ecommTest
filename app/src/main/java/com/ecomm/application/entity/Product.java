package com.ecomm.application.entity;

import java.io.Serializable;
import java.net.URL;
import java.util.Comparator;
import com.ecomm.application.entity.Product;

public class Product implements Comparable, Serializable {
    private String name;
    private double price;
    private URL url;
    private String description;
    private String ecommerceSite;
    private double rating;
    private double shippingFee;
    private String imageURL;

    public Product(String name, float price, URL url, String ecommerceSite){
        this.name = name;
        this.price = price;
        this.url = url;
        this.ecommerceSite = ecommerceSite;
    }

    public Product(String name, double price, URL url, String description, String ecommerceSite, double rating, double shippingFee, String imageURL){
        this.name = name;
        this.price = price;
        this.url = url;
        this.description = description;
        this.ecommerceSite = ecommerceSite;
        this.rating = rating;
        this.shippingFee = shippingFee;
        this.imageURL = imageURL;
    }

    //getters
    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }

    public URL getUrl(){
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

    public double getShippingFee() {
        return this.shippingFee;
    }

    public String getImageURL() {
        return imageURL;
    }

    @Override
    public int compareTo(Object o) {
        if(this.getPrice() > ((Product)o).getPrice()){ //cast object o as product
            return 1;
        }
        return -1;
    }

    public static Comparator<Product> Price = new Comparator<Product>() {

        public int compare(Product o1, Product o2) {
            return o1.compareTo(o2);
        }
    };

}
