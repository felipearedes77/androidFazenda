package com.example.projeto.arrays;

import com.example.projeto.model.ItensParaHome;

import java.util.ArrayList;

public class ConstrantsProdutos {
    public static ArrayList<ItensParaHome> getProdutostData() {
        ArrayList<ItensParaHome> produtosList = new ArrayList<>();
        ItensParaHome comp1 = new ItensParaHome("Acessar Frutas");
        ItensParaHome comp2 = new ItensParaHome("Acessar Verduras");
        ItensParaHome comp3 = new ItensParaHome("Acessar Alimentos para Doação");
        produtosList.add(comp1);
        produtosList.add(comp2);
        produtosList.add(comp3);
        return produtosList;


    }
}
