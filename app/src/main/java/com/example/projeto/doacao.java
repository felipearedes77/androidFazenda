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
import com.example.projeto.arrays.ConstraintDoacao;
import com.example.projeto.databinding.ActivityDoacaoBinding;
import com.example.projeto.model.Food;
import com.example.projeto.model.RecyclerItemClickListener;

import java.util.ArrayList;

public class doacao extends AppCompatActivity {
    private ActivityDoacaoBinding binding;
    private FoodAdapter foodAdapter;
    private ArrayList<Food> doacaoList;
    int[] ids = {
            5

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDoacaoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        doacaoList = ConstraintDoacao.getDoaData();
        RecyclerView recyclerViewDoacao = binding.RecyclerViewDoacao;
        recyclerViewDoacao.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewDoacao.setHasFixedSize(true);
        foodAdapter = new FoodAdapter(doacaoList,this);
        recyclerViewDoacao.setAdapter(foodAdapter);
        recyclerViewDoacao.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerViewDoacao ,new RecyclerItemClickListener.OnItemClickListener() {
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
        Food food = doacaoList.get(position);
        if (food != null) {
            Intent intent = new Intent(this, pagamento.class);
            intent.putExtra("produtoNome", food.getNameFood());
            intent.putExtra("produtoImagem", food.getImgFood());
            intent.putExtra("produtoID", idParaProduto);
            startActivity(intent);
        } else {
            Log.e("ChamarPagamentos", "O objeto food é nulo.");
        }
    }
}