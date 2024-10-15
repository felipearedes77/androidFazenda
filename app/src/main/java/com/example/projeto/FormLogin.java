package com.example.projeto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projeto.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FormLogin extends AppCompatActivity {
    private TextView tela_cadastro;
    private EditText email , senha;
    private Button btnentrar;
    private ProgressBar ProgressBar;
    private Usuario usuario;
    private FirebaseAuth entrar;
    String[] mensagens = {"Preencha os Campos!","Login realizado!", "Erro no Login"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        IniciaComponentes();
        FazerLogin();
        ProgressBar.setVisibility(View.GONE);
        btnentrar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View v) {
                String textemail =  email.getText().toString();
                String textsenha = senha.getText().toString();


                if ( textemail.isEmpty() || textsenha.isEmpty() ){

                    Snackbar snackbar = Snackbar.make(v,mensagens[0],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                    btnentrar = findViewById(R.id.btn_entrar);
                    btnentrar.setEnabled(false);

                }else

                    usuario = new Usuario();
                usuario.setEmail(textemail);
                usuario.setSenha(textsenha);
                EntrarnaConta( usuario );
            }
        });


        tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormLogin.this, FormCadastro.class);
                startActivity(intent);
            }
        });

    }
    private void EntrarnaConta(Usuario usuario){
        ProgressBar.setVisibility(View.VISIBLE);
        entrar = FirebaseAuth.getInstance();
        entrar.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            ProgressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(FormLogin.this, tela_principal.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(FormLogin.this,"Erro no login", Toast.LENGTH_SHORT).show();
                            ProgressBar.setVisibility(View.GONE);


                        }

                    }
                });

    }


    private void IniciaComponentes() {
        tela_cadastro = findViewById(R.id.tela_cadastro);

    }

    private void FazerLogin(){
        email = findViewById(R.id.tlemail);
        senha = findViewById(R.id.senhafirst);
        ProgressBar = findViewById(R.id.ProgressBar);
        btnentrar = findViewById(R.id.btn_entrar);

        email.requestFocus();

    }
}