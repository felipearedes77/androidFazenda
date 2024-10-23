package com.example.projeto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto.adapter.FoodAdapter;
import com.example.projeto.arrays.Constrants;
import com.example.projeto.databinding.ActivityTelaPrincipalBinding;
import com.example.projeto.model.Food;
import com.example.projeto.model.RecyclerItemClickListener;

import java.util.ArrayList;

public class tela_principal extends AppCompatActivity {

     private ActivityTelaPrincipalBinding binding;
     private FoodAdapter foodAdapter;
     private ArrayList<Food> foodList;
    String[] ids = {
            "ecf534b0-13d8-4560-bf49-6dd6ad4773c6",
            "02eb1680-22a3-4006-88d1-46060f4977ab"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        foodList = Constrants.getFoodData();
        RecyclerView recyclerViewFood = binding.RecyclerViewFood;
        recyclerViewFood.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFood.setHasFixedSize(true);
        foodAdapter = new FoodAdapter(foodList,this);
        recyclerViewFood.setAdapter(foodAdapter);

        recyclerViewFood.addOnItemTouchListener(
                new RecyclerItemClickListener(tela_principal.this, recyclerViewFood ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Log.d("rapid", "onItemClick position: " + position);
                        String idParaProduto = ids[position]; // Obtém o ID correspondente
                        ChamarPagamentos(position, idParaProduto);

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        Log.d("rapid", "onItemLongClick pos = " + position);
                    }
                })
        );




    }
    private void ChamarPagamentos(int position, String idParaProduto){
        Food food = foodList.get(position);
        if (food != null) {
            Intent intent = new Intent(tela_principal.this, pagamento.class);
            intent.putExtra("produtoNome", food.getNameFood());
            intent.putExtra("produtoImagem", food.getImgFood());
            intent.putExtra("produtoID", idParaProduto);
            startActivity(intent);
        } else {
            Log.e("ChamarPagamentos", "O objeto food é nulo.");
        }
    }



}
