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
import com.example.projeto.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormCadastro extends AppCompatActivity {

    private EditText nome ,email ,senha , cep , rua;
    private Button btn_cadastro;
    private Retrofit retrofit;

    private Usuario usuario;

    String[] mensagens = {"Preencha os Campos!","Cadastro realizado"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_cadastro);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:1111")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

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
                String editrua = rua.getText().toString();
                String editcep = cep.getText().toString();
                if (editnome.isEmpty() || editemail.isEmpty() || editsenha.isEmpty() || editrua.isEmpty() ||
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
        Log.d("Cadastro","Inciando o Metodo");
        String editnome = nome.getText().toString();
        String editemail =  email.getText().toString();
        String editsenha = senha.getText().toString();
        String editrua = rua.getText().toString();
        String editcep = cep.getText().toString();


        FirebaseAuth.getInstance().createUserWithEmailAndPassword(editemail,editsenha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("Cadastro", "o Firebase esta ok!");
                    Usuario usuarionovo = new Usuario();
                    usuarionovo.setNome(editnome);
                    usuarionovo.setEmail(editemail);
                    usuarionovo.setRua(editrua);
                    usuarionovo.setCep(editcep);
                    ApiService service = retrofit.create(ApiService.class);
                    Call<Usuario> call = service.saveCliente(usuarionovo);
                    call.enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                            if (response.isSuccessful()){
                                Usuario usuarioresposta = response.body();
                                Log.d("Cadastro", "Cadastro realizado com sucesso: " + usuarioresposta);
                                startActivity(new Intent(getApplicationContext(),FormLogin.class));
                            }
                            else {
                                Log.e("Cadastro","Falhou o Cadastro, código:" +  + response.code());

                            }
                        }

                        @Override
                        public void onFailure(Call<Usuario> call, Throwable throwable) {
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
        rua=findViewById(R.id.rua);
        cep=findViewById(R.id.cep);
        btn_cadastro=findViewById(R.id.btn_cadastro);
    }
}