package com.ecomm.application.entity;

import java.net.URL;
import java.util.Comparator;
import com.ecomm.application.entity.Product;

public class Product implements Comparable{
    private String name;
    private float price;
    private URL url;
    private String description;
    private String ecommerceSite;

    public Product(String name, float price, URL url, String ecommerceSite){
        this.name = name;
        this.price = price;
        this.url = url;
        this.ecommerceSite = ecommerceSite;
    }

    public float getPrice(){
        return this.price;
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
