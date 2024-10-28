package com.example.projeto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projeto.ApiService.ApiService;
import com.example.projeto.model.Usuario;
import com.example.projeto.model.UsuarioForPF;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.protobuf.Api;

import java.net.URLEncoder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PerfilDoUsuario extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private Retrofit retrofit;
    private TextView idUsuario , nomeUsuario, rgUsuario, cpfUsuario, emailUsuario, telefoneUsuario, celularUsuario, cepUsuario, enderecoUsuario ,compUsuario ,numeroUsuario , bairroUsuario , estadoUsuario ;
    private EditText editTextNome, edittextrgg, editcpf, edittextemail, editexttelefone, editextcelular, editextcep, editextendereco , editextcomp , editextnumero , editextbairro , editextestado;
    private Button btn_editar , btn_deslogar , btn_salvar;
    private boolean isEditing = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil_do_usuario);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            getUserData(email);
        } else {

            Intent intent = new Intent(PerfilDoUsuario.this, FormLogin.class);
            startActivity(intent);
            finish();
        }

        IniciarEditText();
        IniciarTextView();
        btn_editar = findViewById(R.id.btn_editar);
        btn_salvar = findViewById(R.id.btn_salvar);
        btn_deslogar = findViewById(R.id.btn_deslogar);
        btn_editar.setOnClickListener(v -> {
            if (!isEditing) {

                EditarDados();
            } else {

                SairModoEdicao();
            }
        });
        btn_salvar.setOnClickListener(v -> saveData());
        btn_deslogar.setOnClickListener(v -> deslogarUsuario());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void getUserData(String uid){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.111:1777")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<UsuarioForPF> call = apiService.getUsuarioByEmail(user.getEmail());
        call.enqueue(new Callback<UsuarioForPF>() {
            @Override
            public void onResponse(Call<UsuarioForPF> call, Response<UsuarioForPF> response) {
                if (response.isSuccessful() && response.body() != null) {
                    UsuarioForPF usuario = response.body();
                    preencherDados(usuario);
                } else {
                    Log.d("UID", "UID do usuário: " + uid);
                    Log.e("API Error", "Código de resposta: " + response.code());
                    Log.e("API Error", "Mensagem de erro: " + response.message());
                    Toast.makeText(PerfilDoUsuario.this, "Erro ao carregar dados!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UsuarioForPF> call, Throwable t) {
                Toast.makeText(PerfilDoUsuario.this, "Falha na comunicação!", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void IniciarTextView(){
        idUsuario = findViewById(R.id.idusuario);
        nomeUsuario = findViewById(R.id.nome);
        rgUsuario = findViewById(R.id.rg);
        cpfUsuario = findViewById(R.id.cpf);
        emailUsuario = findViewById(R.id.email);
        telefoneUsuario = findViewById(R.id.telefone);
        celularUsuario = findViewById(R.id.celular);
        cepUsuario = findViewById(R.id.cep);
        enderecoUsuario = findViewById(R.id.endereco);
        numeroUsuario = findViewById(R.id.numero);
        compUsuario = findViewById(R.id.complemento);
        bairroUsuario = findViewById(R.id.bairro);
        estadoUsuario = findViewById(R.id.estado);
    }
    private void preencherDados(UsuarioForPF usuario) {

        if (idUsuario != null) idUsuario.setText(usuario.getId().toString());
        if (nomeUsuario != null) nomeUsuario.setText(usuario.getNome());
        if (rgUsuario != null) rgUsuario.setText(usuario.getRg());
        if (cpfUsuario != null) cpfUsuario.setText(usuario.getCpf());
        if (emailUsuario != null) emailUsuario.setText(usuario.getEmail());
        if (telefoneUsuario != null) telefoneUsuario.setText(usuario.getTelefone());
        if (celularUsuario != null) celularUsuario.setText(usuario.getCelular());
        if (cepUsuario != null) cepUsuario.setText(usuario.getCep());
        if (enderecoUsuario != null) enderecoUsuario.setText(usuario.getEndereco());
        if (numeroUsuario != null) numeroUsuario.setText(String.valueOf(usuario.getNumero()));
        if (compUsuario != null) compUsuario.setText(usuario.getComplemento());
        if (bairroUsuario != null) bairroUsuario.setText(usuario.getBairro());
        if (estadoUsuario != null) estadoUsuario.setText(usuario.getEstado());
    }
    @SuppressLint("WrongViewCast")
    private void IniciarEditText(){
        editTextNome = findViewById(R.id.editextnome);
        edittextrgg = findViewById(R.id.edittextrg);
        editcpf = findViewById(R.id.editcpf);
        edittextemail = findViewById(R.id.edittextemail);
        editexttelefone = findViewById(R.id.editexttelefone);
        editextcelular = findViewById(R.id.editextcelular);
        editextcep = findViewById(R.id.editextcep);
        editextendereco = findViewById(R.id.editextendereco);
        editextcomp = findViewById(R.id.editextcomplemento);
        editextnumero = findViewById(R.id.editextnumero);
        editextbairro = findViewById(R.id.editextBairro);
        editextestado = findViewById(R.id.editextestado);
    }
    private void EditarDados(){
        isEditing = true;
        editTextNome.setVisibility(View.VISIBLE);
        edittextrgg.setVisibility(View.VISIBLE);
        editcpf.setVisibility(View.VISIBLE);
        edittextemail.setVisibility(View.VISIBLE);
        editexttelefone.setVisibility(View.VISIBLE);
        editextcelular.setVisibility(View.VISIBLE);
        editextcep.setVisibility(View.VISIBLE);
        editextendereco.setVisibility(View.VISIBLE);
        editextcomp.setVisibility(View.VISIBLE);
        editextnumero.setVisibility(View.VISIBLE);
        editextbairro.setVisibility(View.VISIBLE);
        editextestado.setVisibility(View.VISIBLE);

        nomeUsuario.setVisibility(View.GONE);
        rgUsuario.setVisibility(View.GONE);
        cpfUsuario.setVisibility(View.GONE);
        emailUsuario.setVisibility(View.GONE);
        telefoneUsuario.setVisibility(View.GONE);
        celularUsuario.setVisibility(View.GONE);
        cepUsuario.setVisibility(View.GONE);
        enderecoUsuario.setVisibility(View.GONE);
        compUsuario.setVisibility(View.GONE);
        numeroUsuario.setVisibility(View.GONE);
        bairroUsuario.setVisibility(View.GONE);
        estadoUsuario.setVisibility(View.GONE);

        editTextNome.setText(nomeUsuario != null ? nomeUsuario.getText() : "");
        edittextrgg.setText(rgUsuario != null ? rgUsuario.getText() : "");
        editcpf.setText(cpfUsuario != null ? cpfUsuario.getText() : "");
        edittextemail.setText(emailUsuario != null ? emailUsuario.getText() : "");
        editexttelefone.setText(telefoneUsuario != null ? telefoneUsuario.getText() : "");
        editextcelular.setText(celularUsuario != null ? celularUsuario.getText() : "");
        editextcep.setText(cepUsuario != null ? cepUsuario.getText() : "");
        editextendereco.setText(enderecoUsuario != null ? enderecoUsuario.getText() : "");
        editextcomp.setText(compUsuario != null ? compUsuario.getText() : "");
        editextnumero.setText(numeroUsuario != null ? numeroUsuario.getText() : "");
        editextbairro.setText(bairroUsuario != null ? bairroUsuario.getText() : "");
        editextestado.setText(estadoUsuario != null ? estadoUsuario.getText() : "");

        btn_editar.setText("Cancelar");

    }
    private void SairModoEdicao() {
        isEditing = false;

        nomeUsuario.setVisibility(View.VISIBLE);
        rgUsuario.setVisibility(View.VISIBLE);
        cpfUsuario.setVisibility(View.VISIBLE);
        emailUsuario.setVisibility(View.VISIBLE);
        telefoneUsuario.setVisibility(View.VISIBLE);
        celularUsuario.setVisibility(View.VISIBLE);
        cepUsuario.setVisibility(View.VISIBLE);
        enderecoUsuario.setVisibility(View.VISIBLE);
        compUsuario.setVisibility(View.VISIBLE);
        numeroUsuario.setVisibility(View.VISIBLE);
        bairroUsuario.setVisibility(View.VISIBLE);
        estadoUsuario.setVisibility(View.VISIBLE);

        editTextNome.setVisibility(View.GONE);
        edittextrgg.setVisibility(View.GONE);
        editcpf.setVisibility(View.GONE);
        edittextemail.setVisibility(View.GONE);
        editexttelefone.setVisibility(View.GONE);
        editextcelular.setVisibility(View.GONE);
        editextcep.setVisibility(View.GONE);
        editextendereco.setVisibility(View.GONE);
        editextcomp.setVisibility(View.GONE);
        editextnumero.setVisibility(View.GONE);
        editextbairro.setVisibility(View.GONE);
        editextestado.setVisibility(View.GONE);


        btn_editar.setText("Editar");
    }
    private void saveData() {


        nomeUsuario.setText(editTextNome.getText());
        rgUsuario.setText(edittextrgg.getText());
        cpfUsuario.setText(editcpf.getText());
        emailUsuario.setText(edittextemail.getText());
        telefoneUsuario.setText(editexttelefone.getText());
        celularUsuario.setText(editextcelular.getText());
        cepUsuario.setText(editextcep.getText());
        enderecoUsuario.setText(editextendereco.getText());
        compUsuario.setText(editextcomp.getText());
        numeroUsuario.setText(editextnumero.getText());
        bairroUsuario.setText(bairroUsuario.getText());
        estadoUsuario.setText(estadoUsuario.getText());


        UsuarioForPF usuarioAtualizado = new UsuarioForPF();
        usuarioAtualizado.setNome(editTextNome.getText().toString());
        usuarioAtualizado.setRg(edittextrgg.getText().toString());
        usuarioAtualizado.setCpf(editcpf.getText().toString());
        usuarioAtualizado.setEmail(edittextemail.getText().toString());
        usuarioAtualizado.setTelefone(editexttelefone.getText().toString());
        usuarioAtualizado.setCelular(editextcelular.getText().toString());
        usuarioAtualizado.setCep(editextcep.getText().toString());
        usuarioAtualizado.setEndereco(editextendereco.getText().toString());
        usuarioAtualizado.setComplemento(editextcomp.getText().toString());
        usuarioAtualizado.setNumero(Integer.parseInt(editextnumero.getText().toString()));
        usuarioAtualizado.setBairro(editextbairro.getText().toString());
        usuarioAtualizado.setEstado(editextestado.getText().toString());

        ApiService apiService= retrofit.create(ApiService.class);
        String email = user.getEmail();
        Call <Boolean> call = apiService.updateClientes(email, usuarioAtualizado);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(PerfilDoUsuario.this, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();
                    SairModoEdicao(); // Sai do modo de edição ao salvar
                } else {
                    Toast.makeText(PerfilDoUsuario.this, "Falha ao atualizar dados!", Toast.LENGTH_SHORT).show();
                    Log.e("API Error", "Código de resposta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(PerfilDoUsuario.this, "Erro de comunicação!", Toast.LENGTH_SHORT).show();
                Log.e("API Failure", t.getMessage());
            }
        });


    }
    private void deslogarUsuario() {
        FirebaseAuth.getInstance().signOut();

        Intent intent = new Intent(PerfilDoUsuario.this, FormLogin.class);
        startActivity(intent);
        finish();
    }


}