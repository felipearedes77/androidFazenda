package com.example.projeto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projeto.ApiService.ApiService;
import com.example.projeto.model.Produtos;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class pagamento extends AppCompatActivity {
    private TextView txtProdutoNome ,txtPreco , txtQtdEstoque;
    private ImageView imgProduto;
    private Retrofit retrofit;
    private Button btnComprar;
    final int[] quantidade = {1};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pagamento);
        Intent intent = getIntent();

        String produtoNome = intent.getStringExtra("produtoNome");
        int produtoImagem = intent.getIntExtra("produtoImagem", R.drawable.banana1);
        int produtoID = intent.getIntExtra("produtoID", -1);
        txtProdutoNome = findViewById(R.id.produtopag);
        imgProduto = findViewById(R.id.imgpag);
        txtProdutoNome.setText(produtoNome);
        imgProduto.setImageResource(produtoImagem);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.111:1777")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Produtos> call =apiService.findById(produtoID);
        call.enqueue(new Callback<Produtos>() {
                         @Override
                         public void onResponse(Call<Produtos> call, Response<Produtos> response) {
                             if (response.isSuccessful() && response.body() != null) {
                                 Produtos produto = response.body();
                                 txtPreco = findViewById(R.id.preco);
                                 txtQtdEstoque = findViewById(R.id.qtdestoque);
                                 txtPreco.setText(String.valueOf(produto.getPreco()));
                                 txtQtdEstoque.setText(String.valueOf(produto.getQtd_estoque()));
                             } else {
                                 Log.e("pagamento", "Resposta vazia: " + response.message());
                             }
                         }

                         @Override
                         public void onFailure(Call<Produtos> call, Throwable t) {

                         }


                     });
                ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
                });
        callComponent();
        Quantidade();
        btnComprar.setOnClickListener(v -> {
            Intent pixIntent = new Intent(pagamento.this, PixGG.class);
            pixIntent.putExtra("quantidadeSelecionada", quantidade[0]);
            pixIntent.putExtra("produtoID", produtoID);
            startActivity(pixIntent);
        });
    }
 private void Quantidade(){
     TextView txtQuantidade = findViewById(R.id.txt_quantidade);
     AppCompatButton btnIncrement = findViewById(R.id.btn_increment);
     AppCompatButton btnDecrement = findViewById(R.id.btn_decrement);





     btnIncrement.setOnClickListener(v -> {
         quantidade[0]++;
         txtQuantidade.setText(String.valueOf(quantidade[0]));
     });


     btnDecrement.setOnClickListener(v -> {
         if (quantidade[0] > 1) {
             quantidade[0]--;
             txtQuantidade.setText(String.valueOf(quantidade[0]));
         }
     });
 }
 private void callComponent(){
        btnComprar=findViewById(R.id.btn_comprar);
 }
}