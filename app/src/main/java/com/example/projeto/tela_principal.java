package com.example.projeto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Context;
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
                        ChamarPagamentos();

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        Log.d("rapid", "onItemLongClick pos = " + position);
                    }
                })
        );




    }
    private void ChamarPagamentos(){
        Intent intent = new Intent(tela_principal.this, pagamento.class);
        startActivity(intent);
    }



}
