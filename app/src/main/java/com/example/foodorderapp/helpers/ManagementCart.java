package com.example.foodorderapp.helpers;

import android.content.Context;
import android.widget.Toast;

import com.example.foodorderapp.domains.FoodDomain;
import com.example.foodorderapp.interfaces.ChangeNumberItemListener;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        tinyDB=new TinyDB(context);
    }

    public void insertFood(FoodDomain item) {
        ArrayList<FoodDomain> listFood = getListCard();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listFood.size(); i++) {
            if(listFood.get(i).getTitle().equals(item.getTitle())){
                existAlready=true;
                n=i;
                break;
            }
        }
        if(existAlready){
            listFood.get(n).setNumberInCard(item.getNumberInCard());
        }else{
            listFood.add(item);
        }
        tinyDB.putListObject("CartList",listFood);
        Toast.makeText(context, "Added to your cart", Toast.LENGTH_SHORT).show();
    }
    public ArrayList<FoodDomain> getListCard(){
            return tinyDB.getListObject("CartList");
    }

    public void plusNumberFood(ArrayList<FoodDomain> foodList, int position, ChangeNumberItemListener changeNumberItemListener){
        foodList.get(position).setNumberInCard(foodList.get(position).getNumberInCard()+1);
        tinyDB.putListObject("CartList",foodList);
        changeNumberItemListener.changed();
    }

    public void minusNumberFood(ArrayList<FoodDomain> foodList, int position, ChangeNumberItemListener changeNumberItemListener){
        if(foodList.get(position).getNumberInCard()==1)
        {
            foodList.remove(position);
        }
        else {
            foodList.get(position).setNumberInCard(foodList.get(position).getNumberInCard() - 1);
        }

        tinyDB.putListObject("CartList", foodList);
        changeNumberItemListener.changed();

    }
    public Double getTotalFree(){
        ArrayList<FoodDomain> list=getListCard();
        double fee = 0;
        for (int i = 0; i < list.size(); i++) {
            fee+=list.get(i).getFee()*list.get(i).getNumberInCard();
        }
        return fee;
    }

}
