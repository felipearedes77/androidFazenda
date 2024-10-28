package com.example.projeto.arrays;

import com.example.projeto.R;
import com.example.projeto.model.Food;

import java.util.ArrayList;

public class ConstraintDoação {
    public static ArrayList<Food> getVerduraData() {
        ArrayList<Food> verduraList = new ArrayList<>();
        Food verdura1 = new Food(R.drawable.alface, "cenoura", "Banana Fresca e Natural Produzida em Fazenda", "Clique para verificar o preço");
        verduraList.add(verdura1);
        return verduraList;


    }
}
