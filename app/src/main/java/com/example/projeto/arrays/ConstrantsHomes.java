package com.example.projeto.arrays;

import com.example.projeto.R;
import com.example.projeto.model.ItensParaHome;

import java.util.ArrayList;

public class ConstrantsHomes {
    public static ArrayList<ItensParaHome> getHomeData() {
        ArrayList<ItensParaHome> homeList = new ArrayList<>();
        ItensParaHome comp1 = new ItensParaHome("Acessar Produtos");
        ItensParaHome comp2 = new ItensParaHome("Acessar Doações");
        ItensParaHome comp3 = new ItensParaHome("Acessar Perfil");
        homeList.add(comp1);
        homeList.add(comp2);
        homeList.add(comp3);
        return homeList;


    }
}
