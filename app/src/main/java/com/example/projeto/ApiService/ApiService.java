package com.example.projeto.ApiService;

import com.example.projeto.model.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;

import retrofit2.http.POST;

public interface ApiService {

    @POST("/clientes")
    Call<Void> saveCliente(@Body Usuario usuario );
}
