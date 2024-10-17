package com.example.projeto;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto.adapter.FoodAdapter;
import com.example.projeto.databinding.ActivityMainBinding;
import com.example.projeto.databinding.ActivityTelaPrincipalBinding;
import com.example.projeto.model.Food;

import java.util.ArrayList;

public class tela_principal extends AppCompatActivity {

     private ActivityTelaPrincipalBinding binding;
     private FoodAdapter foodAdapter;
     private ArrayList<Food> foodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTelaPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);

        RecyclerView recyclerViewFood = binding.RecyclerViewFood;
        recyclerViewFood.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFood.setHasFixedSize(true);
        foodAdapter = new FoodAdapter(foodList,this);
        recyclerViewFood.setAdapter(foodAdapter);
        getFood();



    }
    private void getFood(){
        Food food1 = new Food(
                R.drawable.banana1,
                "Banana Prata",
                "Banana fresca e doce",
                "Verique o preço no carrinho"
        );
        foodList.add(food1);
        Food food2 = new Food(
                R.drawable.laranja,
                "Laranja",
                "Laranja natural e doce",
                "Verique o preço no carrinho"
        );
        foodList.add(food2);
        Food food3 = new Food(
                R.drawable.banana1,
                "Laranja",
                "",
                "Verique o preço no carrinho"
        );
        foodList.add(food3);
        Food food4 = new Food(
                R.drawable.banana1,
                "Laranja",
                "",
                "Verique o preço no carrinho"
        );
        foodList.add(food4);

    }
}
