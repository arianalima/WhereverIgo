package com.example.bernardojr.whereverigo.gui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bernardojr.whereverigo.R;
import com.example.bernardojr.whereverigo.dominio.Pessoa;
import com.example.bernardojr.whereverigo.dominio.Usuario;
import com.example.bernardojr.whereverigo.infra.Criptografia;
import com.example.bernardojr.whereverigo.negocio.SessaoUsuario;
import com.example.bernardojr.whereverigo.negocio.UsuarioNegocio;
import com.example.bernardojr.whereverigo.negocio.UsuarioService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends Activity implements View.OnClickListener{
    private ImageView logo;

    private EditText edtEmail;
    private EditText edtSenha;

    private Button btnLogin;
    private Button btnCadastro;

    private Resources resources;
    private static Context contexto;
    private Usuario usuario;
    private UsuarioNegocio usuarioNegocio;
    private Criptografia criptografia;
    private String senhaCriptografada;

    private SessaoUsuario sessaoUsuario;

    private SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        contexto = this;
        criptografia = Criptografia.getInstancia();
        logo = (ImageView) findViewById(R.id.appIcon);
        sessaoUsuario = SessaoUsuario.getInstancia();

        sdf = new SimpleDateFormat("dd/MM/yyyy");

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        btnCadastro = (Button) findViewById(R.id.btn_cadastro);
        btnCadastro.setOnClickListener(this);

        edtEmail = (EditText) findViewById(R.id.userEmail);
        edtSenha = (EditText) findViewById(R.id.userSenha);

        initViews();

    }

    @Override
    protected void onResume() {
        super.onResume();
        sessaoUsuario.setPessoaLogada(null);
        edtSenha.setText(null);
        edtEmail.setText(null);
        //usuarioNegocio=UsuarioNegocio.getInstancia(this);
    }



    private void initViews() {
        resources = getResources();
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        edtEmail = (EditText) findViewById(R.id.userEmail);
        edtEmail.addTextChangedListener(textWatcher);

        edtSenha = (EditText) findViewById(R.id.userSenha);
        edtSenha.addTextChangedListener(textWatcher);

    }

    private boolean validateFields(){
        String email = edtEmail.getText().toString().trim();
        String senha = edtSenha.getText().toString();
        return (!isEmptyFields(email, senha) && isValidEmail(email));
    }

    private boolean isEmptyFields(String email, String senha) {
        if (TextUtils.isEmpty(email)) {
            edtEmail.requestFocus();
            edtEmail.setError(resources.getString(R.string.campo_vazio));
            return true;
        } else if (TextUtils.isEmpty(senha)) {
            edtSenha.requestFocus();
            edtSenha.setError(resources.getString(R.string.campo_vazio));
            return true;
        }
        return false;
    }

    public boolean isValidEmail(String email) {
        Pattern regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        Matcher regMatcher   = regexPattern.matcher(email);
        if(regMatcher.matches()) {
            return true;
        }
        edtEmail.requestFocus();
        edtEmail.setError(resources.getString(R.string.email_invalido));
        return false;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_login:
                logar(v);
                break;
            case R.id.btn_cadastro:
                startSignUpActivity();
                break;
        }
    }

    private void logar(View view){
        if (validateFields()){
            try {
                String login = edtEmail.getText().toString();
                String senha = edtSenha.getText().toString();

                criptografia.receberSenhaOriginal(senha);
                senhaCriptografada = criptografia.getSenhaCriptografada();
                getUsuario(login,senha);

                //usuario = usuarioNegocio.logar(login, senhaCriptografada);
                //GuiUtil.exibirSaudacao(this);

            }catch (Exception e){
                //WhereverIgoException
                Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_LONG).show();
                //GuiUtil.exibirMsg(LoginActivity.this, e.getMessage());

            }
        }
    }

    public void startSignUpActivity() {
        Intent i = new Intent(LoginActivity.this,CadastroUsuarioActivity.class);
        startActivity(i);
    }

    public void startHomeActivity() {
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
    }

    public static Context getContexto(){ return contexto; }

    public void getUsuario(String email, final String senha){
        //IPs/endereços
        //bernardo: http://192.168.25.55:8080/WhereverIgo/rest/UserService/
        //ari: http://10.246.42.39:8080/UserManagement/rest/UserService/ ou 192.168.25.234
        //leut: http://10.246.13.221:8080/WhereverIGo/rest/UsuarioService/ ou 192.168.31.191

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.25.55:8080/WhereverIGo/rest/UsuarioService/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsuarioService usuarioService = retrofit.create(UsuarioService.class);


        Call<Pessoa> pessoaCall = usuarioService.getUser(email, senha);

        pessoaCall.enqueue(new Callback<Pessoa>() {
            @Override
            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                if(response.isSuccessful()){
                    //ResponseBody rb = response.body();
                    Pessoa pessoa = response.body();
                    if(pessoa != null){
                        try {
                            Date data = sdf.parse(pessoa.getStrDataNascimento());
                            Toast.makeText(getApplicationContext(),"Bem vindo(a)!" ,Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),"Nome: "+pessoa.getNome() ,Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),"ID: "+pessoa.getId() ,Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),"str datanasc: "+pessoa.getStrDataNascimento() ,Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),"sexo: "+pessoa.getSexo() ,Toast.LENGTH_SHORT).show();
                            //sessaoUsuario.setPessoaLogada(pessoa);

                            startHomeActivity();
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(),"Erro ao carregar usuário." ,Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        edtSenha.requestFocus();
                        edtSenha.setError(resources.getString(R.string.login_incorreto));
                        edtEmail.requestFocus();
                        edtEmail.setError(resources.getString(R.string.login_incorreto));

                    }
                }else {
                    Toast.makeText(getApplicationContext(),R.string.login_erro + response.code() ,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Pessoa> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(),R.string.server_erro,Toast.LENGTH_LONG).show();
            }
        });
    }


}
