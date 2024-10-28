package com.example.projeto.arrays;

import com.example.projeto.R;
import com.example.projeto.model.Food;

import java.util.ArrayList;

public class ConstraintDoacao {
    public static ArrayList<Food> getDoaData() {
        ArrayList<Food> doacaoList = new ArrayList<>();
        Food verdura1 = new Food(R.drawable.cenoura, "Cenoura", "Cenoura Natural Produzida em Fazenda", "Clique para ver a Disponibilidade");
        doacaoList.add(verdura1);
        return doacaoList;

    }
}
