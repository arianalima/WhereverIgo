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

import com.example.bernardojr.whereverigo.dominio.Usuario;
import com.example.bernardojr.whereverigo.infra.Criptografia;
import com.example.bernardojr.whereverigo.R;
import com.example.bernardojr.whereverigo.infra.WhereverIgoException;
import com.example.bernardojr.whereverigo.negocio.UsuarioNegocio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        contexto = this;
        criptografia = Criptografia.getInstancia();
        logo = (ImageView) findViewById(R.id.appIcon);

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

                //usuario = usuarioNegocio.logar(login, senhaCriptografada);
                //GuiUtil.exibirSaudacao(this);
                startQuestionarioActivity();

            }catch (Exception e){
                //WhereverIgoException
                Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_LONG).show();
                //GuiUtil.exibirMsg(LoginActivity.this, e.getMessage());

            }
        }
    }
    //metodo chama o cadastro
    public void startSignUpActivity() {
        Intent i = new Intent(LoginActivity.this,CadastroUsuarioActivity.class);
        startActivity(i);
    }

    public void startQuestionarioActivity() {
        startActivity(new Intent(this, QuestionarioActivity.class));
    }

    public static Context getContexto(){ return contexto; }

}
