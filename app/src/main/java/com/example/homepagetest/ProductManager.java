package com.example.homepagetest;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class ProductManager {
    private ArrayList<Product> productList;
    private webCrawl wc = new webCrawl();
    private Filter filter = new Filter();

    @Test
    public void combineWebsites() throws InterruptedException, MalformedURLException, URISyntaxException {
        this.productList = wc.testQoo10Search();
        //following lines are added for testing
        this.productList = filter.sortByPrice(this.productList, true);
        for(int i = 0; i<this.productList.size(); i++){
            System.out.println(this.productList.get(i).getPrice());
        }
    }

    public void sortByPrice(){
        this.productList = filter.sortByPrice(productList, true);
    }

    public ArrayList<Product> getProductList(){
        for(int i = 0; i<productList.size(); i++){
            System.out.println(productList.get(i).getPrice());
        }
        return this.productList;
    }

}
