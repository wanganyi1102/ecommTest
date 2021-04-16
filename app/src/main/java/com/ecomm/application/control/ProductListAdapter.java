package com.ecomm.application.control;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ecomm.application.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductListAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private ArrayList<String> pImage = new ArrayList<>();
    private ArrayList<String> pTitles= new ArrayList<>();
    private ArrayList<String> pPrice= new ArrayList<>();
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView pImage;
        TextView pTitle;
        TextView pPrice;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pImage = itemView.findViewById(R.id.pImage);
            pTitle = itemView.findViewById(R.id.pTitle);
            pPrice= itemView.findViewById(R.id.pPrice);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

    //constructor
    public ProductListAdapter(ArrayList<String> imageNames, ArrayList<String> titles, ArrayList<String> prices, Context context){
        pImage= imageNames;
        pTitles = titles;
        pPrice = prices;
        this.context= context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item_1, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.with(context).load(pImage.get(position)).into(holder.pImage);
        holder.pTitle.setText(pTitles.get(position));
        holder.pPrice.setText(pPrice.get(position));
        holder.pImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SearchTestUI.setClickedposition(position);
            }
        });
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return pTitles.size();
    }

}
