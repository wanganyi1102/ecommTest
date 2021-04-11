package com.ecomm.application.entity;

import java.util.ArrayList;
import java.util.Collections;

public class Filter {
    private ArrayList<String> filterBy;
    private int minPrice;
    private int maxPrice;

    public void setFilterBy(ArrayList<String> filterBy) {
        this.filterBy = filterBy;
    }
    public void setMinPrice(int minPrice){
        this.minPrice = minPrice;
    }
    public void setMaxPrice(int maxPrice){
        this.minPrice = maxPrice;
    }

    // sort by price asc/desc
    public ArrayList<Product> sortByPrice(ArrayList<Product> sortList, boolean ascending){
        //ArrayList<Product> sortedList = new ArrayList<Product>();
        Collections.sort(sortList, Product.Price);
        if(ascending == false){
            Collections.reverse(sortList);
        }
        return sortList;
    }

    //filter by price range
    public ArrayList<Product> filterPriceRange(ArrayList<Product> prodList){
        for(Product p : prodList){
            if(p.getPrice() < this.minPrice || p.getPrice() > this.maxPrice){
                prodList.remove(p);
            }
        }
        return prodList;
    }

    //filter by shopping site
    public ArrayList<Product> filterSite(ArrayList<Product> prodList, String site){
        for(Product p : prodList){
            if(p.getEcommerceSite().compareTo(site) != 0){
                prodList.remove(p);
            }
        }
        return prodList;
    }

    //filter free shipping fee
    public ArrayList<Product> filterShippingLoc(ArrayList<Product> prodList){
        for(Product p : prodList){
            if(p.getShippingFee() > 0){
                prodList.remove(p);
            }
        }
        return prodList;
    }

}
