package com.example.projeto;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projeto.ApiService.ApiService;
import com.example.projeto.ApiService.NullOnEmptyConverterFactory;
import com.example.projeto.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormCadastro extends AppCompatActivity {

    private EditText nome ,email ,senha , cep ,endereco;
    private Button btn_cadastro;
    private Retrofit retrofit;

    private Usuario usuario;

    String[] mensagens = {"Preencha os Campos!","Cadastro realizado"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_cadastro);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        IniciarComponentes();
        btn_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editnome = nome.getText().toString();
                String editemail =  email.getText().toString();
                String editsenha = senha.getText().toString();
                String editenderco = endereco.getText().toString();
                String editcep = cep.getText().toString();
                if (editnome.isEmpty() || editemail.isEmpty() || editsenha.isEmpty() || editenderco.isEmpty() ||
                editcep.isEmpty()){

                    Snackbar snackbar = Snackbar.make(v,mensagens[0],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                }else {
                    CadastrarUsuario(v);

                }
            }
        });

    }
    private void CadastrarUsuario(View v){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.111:1777")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .client(client)
                .build();
        Log.d("Cadastro","Inciando o Metodo");
        String editnome = nome.getText().toString();
        String editemail =  email.getText().toString();
        String editsenha = senha.getText().toString();
        String editendereco = endereco.getText().toString();
        String editcep = cep.getText().toString();


        FirebaseAuth.getInstance().createUserWithEmailAndPassword(editemail,editsenha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("Cadastro", "o Firebase esta ok!");
                    Usuario usuarionovo = new Usuario();
                    usuarionovo.setNome(editnome);
                    usuarionovo.setEmail(editemail);
                    usuarionovo.setEndereco(editendereco);
                    usuarionovo.setCep(editcep);

                    ApiService service = retrofit.create(ApiService.class);
                    Call<Void> call = service.saveCliente(usuarionovo);
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()){
                                Log.d("Cadastro", "Cadastro realizado com sucesso!");
                                iniciarLogin();
                            }
                            else {
                                try {
                                    Log.e("RespostaAPI","Falhou o Cadastro, código:" +  + response.code());
                                    Log.e("RespostaAPI","Mensagem de erro:" + response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }



                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable throwable) {
                            Log.e("Cadastro","Falha na Conexao" + throwable.getMessage());
                        }
                    });






                }

                else {
                    Log.d("Cadastro", "Erro no Firebase!");
                    String erro;
                    try {

                    throw task.getException();


                    }
                    catch (FirebaseAuthWeakPasswordException e){
                        erro = "Digite uma senha com 6 caracteres no minimo!";
                    }
                    catch (FirebaseAuthUserCollisionException e){
                        erro = "Esta conta ja foi cadastrada!";
                    }
                    catch (FirebaseAuthInvalidCredentialsException e){
                        erro = "E-mail inválido";
                    }
                    catch (Exception e){
                        erro = "Erro ao cadastrar o usuário";

                    }


                    Snackbar snackbar = Snackbar.make(v,erro,Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();


                    }


            }
        });

    }

    private void IniciarComponentes(){
        nome=findViewById(R.id.nome);
        email=findViewById(R.id.email);
        senha=findViewById(R.id.senha);
        endereco=findViewById(R.id.endereco);
        cep=findViewById(R.id.cep);
        btn_cadastro=findViewById(R.id.btn_cadastro);
    }

    private void iniciarLogin(){
        startActivity(new Intent(FormCadastro.this,FormLogin.class));
    }
}