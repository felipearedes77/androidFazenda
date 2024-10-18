package com.example.projeto.arrays;

import com.example.projeto.R;
import com.example.projeto.model.Food;

import java.util.ArrayList;

public class Constrants {
    public static ArrayList<Food> getFoodData() {
        ArrayList<Food> foodList = new ArrayList<>();
        Food foods1 = new Food(R.drawable.banana1, "Banana", "Banana Fresca e Natural", "Clique para verificar o preço");
        Food foods2 = new Food(R.drawable.laranja, "Banana", "Banana Fresca e Natural", "Clique para verificar o preço");
        foodList.add(foods1);
        foodList.add(foods2);
        return foodList;


    }
}
