package com.example.projeto.ApiService;

import com.example.projeto.model.Produtos;
import com.example.projeto.model.Usuario;
import com.example.projeto.model.UsuarioForPF;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @POST("/clientes")
    Call<Void> saveCliente(@Body Usuario usuario );
    @GET("/clientes")
    Call<UsuarioForPF> getUsuario(@Path("uid") String uid);
    @GET("/clientes/email/{email}")
    Call<UsuarioForPF> getUsuarioByEmail(@Path("email") String email);
    @GET("/produtos/{id}")
    Call<Produtos> findById(@Path("id") int produtoID);
    @PUT("/produtos/{id}")
    Call<Produtos> updateProduto(@Path("id") String id, @Body Produtos produtos);
    @PUT("clientes/{email}")
    Call<Boolean> updateClientes(@Path("email")String email,@Body UsuarioForPF usuarios);


}
