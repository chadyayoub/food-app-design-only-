package com.example.foodorderapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.foodorderapp.R;
import com.example.foodorderapp.adapters.CategoryAdapter;
import com.example.foodorderapp.adapters.PopularAdapter;
import com.example.foodorderapp.domains.CategoryDomain;
import com.example.foodorderapp.domains.FoodDomain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter mAdapter, mFoodAdapter;
    private RecyclerView recyclerViewCategoryList, recyclerViewFoodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();
        recyclerViewFood();
        bottomNavBar();
    }

    void bottomNavBar(){
        FloatingActionButton fab = findViewById(R.id.floatingactionbutton_cart);
        LinearLayout homeButton = findViewById(R.id.home_button);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CartListActivity.class));
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CartListActivity.class));
            }
        });

    }

    private void recyclerViewFood() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerViewFoodList = findViewById(R.id.recycler_view_2);
        recyclerViewFoodList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodList = new ArrayList<>();
        foodList.add(new FoodDomain("Pepperoni Pizza","pizza1","pizza with pepperoni toppings",9.76));
        foodList.add(new FoodDomain("Cheese Burger","burger","Specialty burger with cheddar and mozarella cheese",8.1));
        foodList.add(new FoodDomain("Vegetable Pizza","pizza2","pizza with vegetable toppings",10.1));

        mFoodAdapter = new PopularAdapter(foodList);
        recyclerViewFoodList.setAdapter(mFoodAdapter);
    }

    void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerViewCategoryList = findViewById(R.id.recycler_view);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryDomains = new ArrayList<>();
        categoryDomains.add(new CategoryDomain("Pizza","cat_1"));
        categoryDomains.add(new CategoryDomain("Burger","cat_2"));
        categoryDomains.add(new CategoryDomain("Hotdog","cat_3"));
        categoryDomains.add(new CategoryDomain("Drink","cat_4"));
        categoryDomains.add(new CategoryDomain("Donut","cat_5"));

        mAdapter = new CategoryAdapter(categoryDomains);
        recyclerViewCategoryList.setAdapter(mAdapter);
    }
}