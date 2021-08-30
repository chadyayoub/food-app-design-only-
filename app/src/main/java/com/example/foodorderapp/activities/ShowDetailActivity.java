package com.example.foodorderapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodorderapp.R;
import com.example.foodorderapp.domains.FoodDomain;
import com.example.foodorderapp.helpers.ManagementCart;

public class ShowDetailActivity extends AppCompatActivity {

    private TextView addToCartButton;
    private TextView titleText, fee, descriptionText, numberOrder;
    private ImageView plusButton, minusButton, foodImage;
    private FoodDomain foodDomain;
    private int amountToAdd;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCart=new ManagementCart(this);
        initView();//initialize all the findViewById stuff here
        getBundle();
    }

    void getBundle(){

        int drawableResourceId=this.getResources().getIdentifier(foodDomain.getPic(),"drawable",this.getPackageName());

        Glide.with(this).load(drawableResourceId).into(foodImage);

        titleText.setText(foodDomain.getTitle());
        fee.setText("$"+foodDomain.getFee());
        descriptionText.setText(foodDomain.getDescription());
        numberOrder.setText(String.valueOf(amountToAdd));
    }

    void initView(){

        //FoodDomain
        foodDomain = (FoodDomain) getIntent().getSerializableExtra("object");

        //textViews
        addToCartButton=findViewById(R.id.textview_add_to_cart);
        titleText=findViewById(R.id.textview_title_detail);
        fee=findViewById(R.id.textview_price_detail);
        descriptionText=findViewById(R.id.textview_description);
        numberOrder=findViewById(R.id.textview_amount_detail);

        //imageViews
        minusButton=findViewById(R.id.imageview_minus_button);
        plusButton=findViewById(R.id.imageview_plus_button);
        foodImage=findViewById(R.id.imageview_food_detail);

        amountToAdd=1;

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amountToAdd+=1;
                double totalPrice;
                totalPrice= amountToAdd * foodDomain.getFee();
                fee.setText("$"+totalPrice);
                numberOrder.setText(String.valueOf(amountToAdd));
            }
        });
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(amountToAdd>0) {
                    amountToAdd -= 1;
                    double totalPrice;
                    totalPrice = amountToAdd * foodDomain.getFee();
                    fee.setText("$" + totalPrice);
                    numberOrder.setText(String.valueOf(amountToAdd));
                }
            }
        });

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodDomain.setNumberInCard(amountToAdd);
                managementCart.insertFood(foodDomain);
            }
        });

    }
}