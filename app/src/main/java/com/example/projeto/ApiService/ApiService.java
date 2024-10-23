package com.example.projeto.ApiService;

import com.example.projeto.model.Produtos;
import com.example.projeto.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("/clientes")
    Call<Void> saveCliente(@Body Usuario usuario );
    @GET("/produtos/{id}")
    Call<Produtos> findById(@Path("id") String produtoID);

}
