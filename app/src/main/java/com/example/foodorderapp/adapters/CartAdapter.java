 package com.example.foodorderapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodorderapp.R;
import com.example.foodorderapp.activities.ShowDetailActivity;
import com.example.foodorderapp.domains.FoodDomain;
import com.example.foodorderapp.helpers.ManagementCart;
import com.example.foodorderapp.interfaces.ChangeNumberItemListener;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    ArrayList<FoodDomain> foodDomains;
    ManagementCart managementCart;
    ChangeNumberItemListener changeNumberItemListener;
    public CartAdapter(ArrayList<FoodDomain> foodDomains, Context context, ChangeNumberItemListener changeNumberItemListener){
        this.foodDomains = foodDomains;
        managementCart = new ManagementCart(context);
        this.changeNumberItemListener = changeNumberItemListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(foodDomains.get(position).getTitle());
        holder.pricePerItem.setText(String.valueOf(foodDomains.get(position).getFee()));
        holder.totalPricePerItem.setText(String.valueOf(Math.round((foodDomains.get(position).getFee()*foodDomains.get(position).getNumberInCard())*100)/100));
        holder.number.setText(String.valueOf(foodDomains.get(position).getNumberInCard()));


        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.image);


        holder.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.plusNumberFood(foodDomains, position, new ChangeNumberItemListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemListener.changed();
                    }
                });
            }
        });

        holder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.minusNumberFood(foodDomains, position, new ChangeNumberItemListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemListener.changed();
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title, pricePerItem, totalPricePerItem, number;
        ImageView image, plusButton, minusButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.textview_food_title_cart);
            pricePerItem=itemView.findViewById(R.id.textview_price_one_cart);
            image=itemView.findViewById(R.id.imageview_food_image_cart_viewholder);
            plusButton=itemView.findViewById(R.id.imageview_plus_button_cart);
            minusButton=itemView.findViewById(R.id.imageview_minus_button_cart);
            totalPricePerItem=itemView.findViewById(R.id.textview_price_all_cart);
            number=itemView.findViewById(R.id.textView_count_cart);
        }
    }

}
