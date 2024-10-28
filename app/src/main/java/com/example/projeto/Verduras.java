package com.example.projeto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto.adapter.FoodAdapter;
import com.example.projeto.arrays.ConstraintVerduras;
import com.example.projeto.databinding.ActivityVerdurasBinding;
import com.example.projeto.model.Food;
import com.example.projeto.model.RecyclerItemClickListener;

import java.util.ArrayList;

public class Verduras extends AppCompatActivity {
    private ActivityVerdurasBinding binding;
    private FoodAdapter foodAdapter;
    private ArrayList<Food> verduraList;
    int[] ids = {
            3,
            4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityVerdurasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        verduraList = ConstraintVerduras.getVerduraData();
        RecyclerView recyclerViewVerduras = binding.RecyclerViewVerduras;
        recyclerViewVerduras.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewVerduras.setHasFixedSize(true);
        foodAdapter = new FoodAdapter(verduraList,this);
        recyclerViewVerduras.setAdapter(foodAdapter);
        recyclerViewVerduras.addOnItemTouchListener(
                new RecyclerItemClickListener(Verduras.this, recyclerViewVerduras ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Log.d("rapid", "onItemClick position: " + position);
                        int idParaProduto = ids[position];
                        ChamarPagamentos(position, idParaProduto);

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        Log.d("rapid", "onItemLongClick pos = " + position);
                    }
                })
        );





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void ChamarPagamentos(int position, int idParaProduto){
        Food food = verduraList.get(position);
        if (food != null) {
            Intent intent = new Intent(Verduras.this, pagamento.class);
            intent.putExtra("produtoNome", food.getNameFood());
            intent.putExtra("produtoImagem", food.getImgFood());
            intent.putExtra("produtoID", idParaProduto);
            startActivity(intent);
        } else {
            Log.e("ChamarPagamentos", "O objeto food é nulo.");
        }
    }
}