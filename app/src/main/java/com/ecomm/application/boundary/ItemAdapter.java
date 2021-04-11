package com.ecomm.application.boundary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ecomm.application.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private static final String TAG = "ItemAdapter";
    private ArrayList<String> mImage = new ArrayList<>();
    private ArrayList<String> mTitles= new ArrayList<>();
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        TextView title;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.Title);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

    //constructor
    public ItemAdapter(ArrayList<String> imageNames, ArrayList<String> titles, Context context){
        mImage= imageNames;
        mTitles = titles;
        mContext= context;
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
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImage.get(position))
                .into(holder.image);
        holder.title.setText(mImage.get(position));
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return mImage.size();
    }

}
