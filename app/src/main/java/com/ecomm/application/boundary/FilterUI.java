package com.ecomm.application.boundary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import com.ecomm.application.R;
import com.ecomm.application.control.Filter;
import com.ecomm.application.entity.Product;
import com.google.android.material.chip.Chip;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import java.util.ArrayList;
import java.util.Arrays;

public class FilterUI extends AppCompatActivity {
    private Filter filter = new Filter();
    private ArrayList<String> filterBy = new ArrayList<String>();
    private ArrayList<Product> prodList = new ArrayList<Product>();

    public void setProdList(ArrayList<Product> prodList){
        this.prodList = prodList;
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_u_i);

        //pass in arraylist to be displayed
        if(getIntent().hasExtra("productlist")){
            prodList = (ArrayList<Product>) getIntent().getSerializableExtra("productlist");
        }

        //click on back button
        Button backBtn = (Button) findViewById(R.id.BackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                Intent backHome = new Intent(getApplicationContext(), HomePageUI.class);
//                startActivity(backHome);
            }
        });

        //initialize all chips on filter page
        Chip priceAscending = (Chip) findViewById(R.id.priceAscending);
        Chip priceDescending = (Chip) findViewById(R.id.priceDescending);
        Chip ratingsAscending = (Chip) findViewById(R.id.ratingsAscending);
        Chip ratingsDescending = (Chip) findViewById(R.id.ratingsDescending);
        Chip relevanceDescending = (Chip) findViewById(R.id.relevanceDescending);
        Chip relevanceAscending = (Chip) findViewById(R.id.relevanceAscending);
        Chip salesAscending = (Chip) findViewById(R.id.salesAscending);
        Chip salesDescending = (Chip) findViewById(R.id.salesDescending);
        Chip shippingDomestic = (Chip) findViewById(R.id.shippingDomestic2);
        Chip shippingOverseas = (Chip) findViewById(R.id.shippingOverseas);
        Chip shippingFree = (Chip) findViewById(R.id.shippingFree);
        Chip websiteLazada = (Chip) findViewById(R.id.Lazada);
        Chip websiteQoo10 = (Chip) findViewById(R.id.qoo10);
        Chip websiteOthers = (Chip) findViewById(R.id.others);

        ArrayList<Chip> allChips = new ArrayList<Chip>(Arrays.asList(priceAscending, priceDescending, ratingsAscending, ratingsDescending,
                relevanceAscending, relevanceDescending, salesAscending, salesDescending, shippingDomestic, shippingOverseas, shippingFree,
                websiteLazada, websiteQoo10, websiteOthers));

        for(Chip chip : allChips){
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chip.setChipBackgroundColorResource(R.color.purple_200); //changes color when selected
                    int startInd = chip.toString().indexOf("/") +1;
                    int endInd = chip.toString().indexOf("}");
                    System.out.println(chip.toString().substring(startInd, endInd));
                    filterBy.add(chip.toString().substring(startInd, endInd));
                }
            });
        }

        //price seek bar
        RangeSeekBar rangeSeekBar = (RangeSeekBar) findViewById(R.id.rangeSeekBar);
        rangeSeekBar.setSelectedMaxValue(100);
        rangeSeekBar.setSelectedMinValue(0);
        rangeSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Object maxValue, Object minValue) {
                Number min_value = bar.getSelectedMinValue();
                Number max_value = bar.getSelectedMaxValue();
                int min = (int) min_value;
                int max = (int) max_value;
                filter.setMinPrice(min);
                filter.setMaxPrice(max);
                //Toast.makeText(getApplicationContext(), "Min="+min+"\n"+"Max="+max, Toast.LENGTH_LONG).show();
            }
        });

        //click reset button
        Button resetFilterBtn = (Button) findViewById(R.id.resetFilterBtn);
        resetFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Chip chip : allChips){
                    chip.setChipBackgroundColorResource(R.color.grey); //changes all colours back to original
                    filterBy.clear();
                    rangeSeekBar.setSelectedMinValue(0);
                    rangeSeekBar.setSelectedMaxValue(100);
                }
            }
        });

        //click apply button
        Button applyFilterBtn = (Button) findViewById(R.id.applyFilterBtn);
        applyFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(String s : filterBy){
                    System.out.println(s);
                }

                //prodList = filter.filterPriceRange(prodList);
                filter.setFilterBy(filterBy); //pass selected filters to filter control
                prodList = filter.performFilter(filterBy, prodList);
                for(Product p: prodList){
                    System.out.println(p.getPrice()+"");
                }
                Intent displayFilteredResults = new Intent(getApplicationContext(), SearchTestUI.class);
                displayFilteredResults.putExtra("com.ecomm.application.PRODUCT_LIST", prodList);
                startActivity(displayFilteredResults);
            }
        });

    }
}