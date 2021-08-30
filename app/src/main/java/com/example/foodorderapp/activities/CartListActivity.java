package com.example.foodorderapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.foodorderapp.R;
import com.example.foodorderapp.adapters.CartAdapter;
import com.example.foodorderapp.domains.FoodDomain;
import com.example.foodorderapp.helpers.ManagementCart;
import com.example.foodorderapp.interfaces.ChangeNumberItemListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class CartListActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private ManagementCart managementCart;
    private TextView foodFeeText, taxText, deliverFeeText, totalFeeText, emptyText;
    private double tax;
    private ScrollView scrollview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list_acitivty);

        managementCart = new ManagementCart(this);

        initView();
        initList();
        calculateCart();
        bottomNavBar();
    }
    void bottomNavBar() {
        LinearLayout homeButton = findViewById(R.id.home_button);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartListActivity.this, MainActivity.class));
            }
        });
    }

    void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CartAdapter(managementCart.getListCard(), this, new ChangeNumberItemListener() {
            @Override
            public void changed() {
                calculateCart();
            }
        });
        recyclerView.setAdapter(adapter);

        if(managementCart.getListCard().isEmpty()){
            emptyText.setVisibility(View.VISIBLE);
            scrollview.setVisibility(View.GONE);
        }else{
            emptyText.setVisibility(View.GONE);
            scrollview.setVisibility(View.VISIBLE);
        }

    }


    void calculateCart(){
        double percentTax = 0.02;
        double delivery = 10;

        tax=Math.round((managementCart.getTotalFree()*percentTax)*100/100);
        double total = Math.round((managementCart.getTotalFree()+tax+delivery));
        double itemTotal = Math.round((managementCart.getTotalFree() *100)/100);
        foodFeeText.setText("$"+itemTotal);
        taxText.setText("$"+tax);
        deliverFeeText.setText("$"+delivery);
        totalFeeText.setText("$"+total);
    }

    void initView(){

        //recyclerview
        recyclerView = findViewById(R.id.recyclerview_items_cart);

        //textviews
        foodFeeText = findViewById(R.id.textview_food_price_cart);
        taxText = findViewById(R.id.textview_tax_cart);
        deliverFeeText = findViewById(R.id.textview_delivery_cart);
        totalFeeText = findViewById(R.id.textview_total__cart);
        emptyText = findViewById(R.id.textview_empty_cart);
        emptyText.setVisibility(View.GONE);

        //scrollview
        scrollview = findViewById(R.id.scrollview_cart);


    }
}