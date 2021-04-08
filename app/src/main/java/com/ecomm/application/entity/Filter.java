package com.ecomm.application.entity;

import java.util.ArrayList;
import java.util.Collections;

public class Filter {
    private ArrayList<String> filterBy;

    public void setFilterBy(ArrayList<String> filterBy) {
        this.filterBy = filterBy;
    }

    public ArrayList<Product> sortByPrice(ArrayList<Product> sortList, boolean ascending){
        //ArrayList<Product> sortedList = new ArrayList<Product>();
        Collections.sort(sortList, Product.Price);
        if(ascending == false){
            Collections.reverse(sortList);
        }
        return sortList;
    }
}
