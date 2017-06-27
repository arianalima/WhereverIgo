package com.example.bernardojr.whereverigo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.bernardojr.whereverigo.dominio.Usuario;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroUsuarioActivity extends Activity {

    private EditText textNome, textEmail, textSenha, textRepetirSenha;
    private EditText data;
    private Button cadastrarUsuario;
    private RadioButton buttonMasculino, buttonFeminino;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        textNome = (EditText) findViewById(R.id.userNome);
        textEmail = (EditText) findViewById(R.id.userEmail);
        textSenha = (EditText) findViewById(R.id.userSenha);
        textRepetirSenha = (EditText) findViewById(R.id.userRepetirSenha);
        data = (EditText) findViewById(R.id.textData);
        cadastrarUsuario = (Button) findViewById(R.id.btn_cadastrarUsuario);
        buttonMasculino = (RadioButton) findViewById(R.id.radioButton1);
        buttonFeminino = (RadioButton) findViewById(R.id.radioButton2);

        //chamar metodos
        chamarBotaoCadastrar();
    }

    public void chamarBotaoCadastrar(){

        cadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = textNome.getText().toString().trim();
                String email = textEmail.getText().toString().trim();
                String senha = textSenha.getText().toString().trim();
                String repetirSenha = textRepetirSenha.getText().toString().trim();
                View view = null;

                Usuario usuario = new Usuario(nome,email,senha,repetirSenha);

                if (!validarCamposPreenchidos(usuario)) {
                    Toast.makeText(getApplication(), "Favor preencher todos os campos", Toast.LENGTH_LONG).show();
                }
                if (!validarEmail(email)) {
                    Toast.makeText(getApplication(), "Verifique o e-mail", Toast.LENGTH_LONG).show();
                }
                if (validarRadioButtonGenero(view)){

                }

                //                buttonMasculino.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(CadastroUsuarioActivity.this, "Elemento masculino marcado", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                buttonFeminino.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(CadastroUsuarioActivity.this,"Elemento masculino marcado",Toast.LENGTH_SHORT).show();
//                    }
//                });
            }

        });

    }

    public boolean validarCamposPreenchidos(Usuario usuario) {
        boolean validacao = true;

        if (usuario.getNome() == null || usuario.getNome().equalsIgnoreCase("")) {
            validacao = false;
            textNome.setError(getString(R.string.campo_obrigatorio));
        }
        if (usuario.getEmail() == null || usuario.getNome().equalsIgnoreCase("")) {
            validacao = false;
            textEmail.setError(getString(R.string.campo_obrigatorio));

        }
        if (usuario.getSenha() == null || usuario.getSenha().equalsIgnoreCase("")) {
            validacao = false;
            textSenha.setError(getString(R.string.campo_obrigatorio));

        }
        if (usuario.getSenha() == null || usuario.getSenha().equalsIgnoreCase("")) {
            validacao = false;
            textRepetirSenha.setError(getString(R.string.campo_obrigatorio));

        }
        return validacao;
    }

    public boolean validarEmail(String email){
        boolean emailValido = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                emailValido = true;
            }
        }
        return emailValido;
    }

    public boolean validarRadioButtonGenero(View view){
        boolean checked =((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.radioButton1:
                if(checked)
                    Toast.makeText(getApplicationContext(),"Elemento masculino marcado", Toast.LENGTH_SHORT).show();
                break;
            case R.id.radioButton2:
                if(checked)
                    Toast.makeText(getApplicationContext(),"Elemento feminino marcado", Toast.LENGTH_SHORT).show();
                break;
        }
        return checked;
    }
    public void setCadastrarUsuario(Usuario usuario){

    }
}



