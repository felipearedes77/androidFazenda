package com.example.projeto.arrays;

import com.example.projeto.R;
import com.example.projeto.model.Food;

import java.util.ArrayList;

public class ConstraintVerduras {
    public static ArrayList<Food> getVerduraData() {
        ArrayList<Food> verduraList = new ArrayList<>();
        Food verdura1 = new Food(R.drawable.alface, "Alface", "Banana Fresca e Natural Produzida em Fazenda", "Clique para verificar o preço");
        Food verdura2 = new Food(R.drawable.batata, "Batata", "Batata Natural Produzida em Fazenda!", "Clique para verificar o preço");
        Food verdura3 = new Food(R.drawable.cenouraa, "Cenoura", "Cenoura Natural Produzida em Fazenda!", "Clique para verificar o preço");
        Food verdura4 = new Food(R.drawable.rucula, "Rucula", "Cenoura Fresca Produzida em Fazenda!", "Clique para verificar o preço");
        verduraList.add(verdura1);
        verduraList.add(verdura2);
        verduraList.add(verdura3);
        verduraList.add(verdura4);
        return verduraList;


    }
}
