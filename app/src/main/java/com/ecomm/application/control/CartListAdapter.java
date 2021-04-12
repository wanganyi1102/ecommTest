package com.ecomm.application.control;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecomm.application.R;
import com.ecomm.application.boundary.ShoppingCartUI;
import com.ecomm.application.entity.Product;
import com.example.homepagetest.ShoppingCart;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    private List callListResponses = new ArrayList<>();
    final List templist=new ArrayList<>();
    //private Activity context;
    private Context context;
    int lastPosition=0;

    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> imageURLs = new ArrayList<>();
//    ArrayList<String> ratings = new ArrayList<>();
    ArrayList<String> prices = new ArrayList<>();
    //public static final ArrayList<Product> productsInCart = new ArrayList<Product>();
    ArrayList<Integer> quantity = new ArrayList<>();


    public CartListAdapter(Activity context, List callListResponses)
    {
        super();
        this.context = context;
        this.callListResponses=callListResponses;
    }

    //anyi
    public CartListAdapter(ArrayList<String> titles, ArrayList<String> imageURLs, ArrayList<String> prices, ArrayList<Integer> quantity, Context context){
        this.titles = titles;
        this.imageURLs = imageURLs;
//        this.ratings = ratings;
        this.prices = prices;
        this.quantity = quantity;
        this.context = context;
}


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_ui, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.with(context).load(imageURLs.get(position)).into(holder.productImageView);
        holder.tv_name.setText(titles.get(position));
//        holder.tv_rate.setText(ratings.get(position));
        holder.tv_total.setText(prices.get(position));
        holder.tv_qty.setText(quantity.get(position).toString());

        holder.chk_selectitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("selected");
                //System.out.println(ShoppingCartUI.productsInCart.get(position).getName());
                ShoppingCartUI.selectedProducts.add(ShoppingCartUI.productsInCart.get(position));
                ShoppingCartUI.total += Double.parseDouble(prices.get(position));
                ShoppingCartUI.orderTotalTextView.setText("S$" + ShoppingCartUI.total+"" +"0");
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView productImageView;
        TextView tv_name;
//        TextView tv_rate;
        TextView tv_total;
        TextView tv_qty;
        CheckBox chk_selectitem;

        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.productImageView);
            tv_name = itemView.findViewById(R.id.tv_name);
//            tv_rate = itemView.findViewById(R.id.tv_rate);
            tv_total = itemView.findViewById(R.id.tv_total);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            chk_selectitem = itemView.findViewById(R.id.chk_selectitem);
            tv_qty = itemView.findViewById(R.id.tv_qty);
        }
    }

//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(context).inflate(R.layout.item_layout_ui, parent, false);
//
//        return new ViewHolder(itemView);
//    }
//
//
//    @Override
//    public void onBindViewHolder(final ViewHolder holder, int position) {
//        final Item call = (Item) callListResponses.get(position);
//
//        holder.itemname.setText(call.getItemName());
//        holder.itemprice.setText(call.getRate());
//        holder.tv_quantity.setText(call.getQuantity());
//
////        holder.cart_minus_img.setOnClickListener(new QuantityListener(context, holder.tv_quantity,call,false));
////        holder.cart_plus_img.setOnClickListener(new QuantityListener(context, holder.tv_quantity,call,true));
////        holder.img_deleteitem.setOnClickListener(new DeleteItemListener(context,call,this));
//    }
//
//    //Animating single element
////    private void setAnimation(View viewToAnimate, int position)
////    {
////        if (position > lastPosition) {
////            Animation animation = AnimationUtils.loadAnimation(context, R.anim.push_right_in);
////            viewToAnimate.startAnimation(animation);
////            lastPosition=position;
////        }
////        position++;
////    }
//
//    @Override
//    public int getItemCount() {
//        //Log.d("Size List:",String.valueOf(callListResponses.size()));
//        if(callListResponses!=null){
//            return callListResponses.size();
//        }
//        return 0;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        private TextView itemprice,itemname, tv_quantity;
////        ImageView cart_minus_img, cart_plus_img,img_deleteitem;
//
//
//        public ViewHolder(View itemView) {
//            super(itemView);
////            cart_minus_img=(ImageView) itemView.findViewById(R.id.cart_minus_img);
////            cart_plus_img=(ImageView) itemView.findViewById(R.id.cart_plus_img);
////            img_deleteitem=(ImageView) itemView.findViewById(R.id.img_deleteitem);
////            itemname=(TextView) itemView.findViewById(R.id.itemname);
////            itemprice=(TextView) itemView.findViewById(R.id.itemprice);
////            tv_quantity=(TextView) itemView.findViewById(R.id.tv_quantity);
//
//        }
//    }

}
