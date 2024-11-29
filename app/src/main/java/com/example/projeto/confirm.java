package com.example.projeto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projeto.ApiService.ApiService;
import com.example.projeto.model.Produtos;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class confirm extends AppCompatActivity {
    private int quantidadeSelecionada;
    private int produtoID;
    private TextView txt_quantidade;
    private Retrofit retrofit;
    private Button btnConfirm;
    @SuppressLint("MissingInflatedId")
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        EdgeToEdge.enable(this);
        btnConfirm = findViewById(R.id.btn_confirm);
        Intent intent = getIntent();
        quantidadeSelecionada = intent.getIntExtra("quantidadeSelecionada", 1);
        produtoID = intent.getIntExtra("produtoID", -1);
        txt_quantidade = findViewById(R.id.txt_quantidade);
        txt_quantidade.setText(String.valueOf(quantidadeSelecionada));
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:1777")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnConfirm.setOnClickListener(v -> confirmPag());
    }
    private void confirmPag(){
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Produtos> call = apiService.findById(produtoID);
        call.enqueue(new Callback<Produtos>() {
            @Override
            public void onResponse(Call<Produtos> call, Response<Produtos> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Produtos produtos = response.body();

                    int estoqueAtual = produtos.getQtd_estoque();

                    int novoEstoque = estoqueAtual - quantidadeSelecionada;

                    if (novoEstoque < 0) {

                        Toast.makeText(confirm.this, "Estoque insuficiente!", Toast.LENGTH_SHORT).show();
                    } else {

                        Produtos updateData = new Produtos(produtos.getDescricao(), produtos.getPreco(), novoEstoque, produtos.getValidade() ,produtos.getId());


                        Call<Produtos> updateCall = apiService.updateProduto(String.valueOf(produtoID), updateData);
                        updateCall.enqueue(new Callback<Produtos>() {
                            @Override
                            public void onResponse(Call<Produtos> call, Response<Produtos> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(confirm.this, "Pagamento confirmado e estoque atualizado!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(confirm.this, obrigado.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(confirm.this, "Erro ao atualizar estoque!", Toast.LENGTH_SHORT).show();
                                    Log.e("Pix", "Erro ao atualizar estoque!" + response.message());
                                }
                            }

                            @Override
                            public void onFailure(Call<Produtos> call, Throwable t) {
                                Toast.makeText(confirm.this, "Falha na comunicação com o servidor!", Toast.LENGTH_SHORT).show();
                                Log.e("Pix", "Falha na comunicação com o servidor!" + response.message());
                            }
                        });
                    }
                } else {
                    Toast.makeText(confirm.this, "Produto não encontrado!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Produtos> call, Throwable t) {
                Toast.makeText(confirm.this, "Falha na comunicação com o servidor!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}