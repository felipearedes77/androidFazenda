package com.example.projeto.arrays;

import com.example.projeto.model.ItensParaHome;

import java.util.ArrayList;

public class ConstrantsPerfil {
    public static ArrayList<ItensParaHome> getPerfilData() {
        ArrayList<ItensParaHome> perfiList = new ArrayList<>();
        ItensParaHome comp1 = new ItensParaHome("Acessar Perfil");
        ItensParaHome comp2 = new ItensParaHome("Login");
        ItensParaHome comp3 = new ItensParaHome("Cadastro");
        perfiList.add(comp1);
        perfiList.add(comp2);
        perfiList.add(comp3);
        return perfiList;


    }
}
