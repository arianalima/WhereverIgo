package com.example.bernardojr.whereverigo.gui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bernardojr.whereverigo.R;
import com.example.bernardojr.whereverigo.dominio.Usuario;
import com.example.bernardojr.whereverigo.dominio.Pessoa;
import com.example.bernardojr.whereverigo.negocio.UsuarioNegocio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CadastroUsuarioActivity extends Activity {

    private EditText textNome, textEmail, textSenha, textRepetirSenha, textDataNascimento;
    private Button buttonCadastrar;
    private RadioGroup radioGroup;
    private RadioButton buttonMasculino, buttonFeminino;

    private UsuarioNegocio usuarioNegocio = UsuarioNegocio.getInstancia();

    private Calendar calendar = Calendar.getInstance();
    private int year;
    private int month;
    private int day;

    private String genero;
    private String nome;
    private String email;
    private String senha;
    private String repetirSenha;
    private String dataNascimentoString;
    private String sexoEscolhido;
    private Date dataNascimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        setDataNascimento();

        textNome = (EditText) findViewById(R.id.userNome);
        textEmail = (EditText) findViewById(R.id.userEmail);
        textSenha = (EditText) findViewById(R.id.userSenha);
        textRepetirSenha = (EditText) findViewById(R.id.userRepetirSenha);
        textDataNascimento= (EditText) findViewById(R.id.textData);
        textDataNascimento.setFocusable(false);
        buttonCadastrar = (Button) findViewById(R.id.btn_cadastrarUsuario);
        radioGroup = (RadioGroup) findViewById(R.id.radioGenero);
        buttonMasculino = (RadioButton) findViewById(R.id.radioButton1);
        buttonFeminino = (RadioButton) findViewById(R.id.radioButton2);
        textDataNascimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                definirDataNascimento();
            }
        });
        chamarBotaoCadastrar();
    }

    public void chamarBotaoCadastrar(){
        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    setCadastrarUsuario();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean validacaoDeCadastro() throws ParseException {

        nome = textNome.getText().toString().trim();
        email = textEmail.getText().toString().trim();
        senha = textSenha.getText().toString().trim();
        repetirSenha = textRepetirSenha.getText().toString().trim();
        sexoEscolhido = adicionandoGenero();
        dataNascimentoString = textDataNascimento.getText().toString().trim();
        try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dataNascimento = dateFormat.parse(dataNascimentoString);
        }catch (ParseException e){
            Toast.makeText(getApplication(), "Preencha a data de nascimento", Toast.LENGTH_LONG).show();
        }
        return (!validaCamposVazios(nome,email,senha,repetirSenha, dataNascimentoString,sexoEscolhido)&&
                !camposComEspacos(email,senha,repetirSenha, dataNascimentoString, dataNascimento)&&
                tamanhoPreenchido(senha,repetirSenha)&&validarEmail(email));
    }

    private String adicionandoGenero(){
        final String masculino = buttonMasculino.getText().toString().trim();
        final String feminino = buttonFeminino.getText().toString().trim();

            radioGroup.setOnCheckedChangeListener(
                    new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            boolean buttonMasculino = R.id.radioButton1 == checkedId;
                            boolean buttonFeminino = R.id.radioButton2 == checkedId;
                            switch (checkedId){
                                case R.id.radioButton1:
                                    if (buttonMasculino)
                                        genero = masculino;
                                    break;
                                case R.id.radioButton2:
                                    if(buttonFeminino)
                                        genero = feminino;
                                    break;
                            }
                        }
                    });
        return genero;
    }

    private boolean validaCamposVazios(String nome, String email, String senha, String repetirSenha, String dataNascimentoString, String sexoEscolhido){
        boolean validacao = false;
        if (TextUtils.isEmpty(nome)){
            validacao = true;
            textNome.requestFocus();
            textNome.setError(getString(R.string.campo_vazio));
        }else if(TextUtils.isEmpty(email)){
            validacao = true;
            textEmail.requestFocus();
            textEmail.setError(getString(R.string.campo_vazio));
        }else if (TextUtils.isEmpty(senha)){
            validacao = true;
            textSenha.requestFocus();
            textSenha.setError(getString(R.string.campo_vazio));
        }else if (TextUtils.isEmpty(repetirSenha)){
            validacao = true;
            textRepetirSenha.requestFocus();
            textRepetirSenha.setError(getString(R.string.campo_vazio));
        }else if (TextUtils.isEmpty(dataNascimentoString)) {
            validacao = true;
            textDataNascimento.requestFocus();
            textDataNascimento.setError(getString(R.string.campo_vazio));
        }return validacao;
    }

    private boolean camposComEspacos(String email, String senha, String repetirSenha, String dataNascimentoString, Date dataNascimento){
        if (email.indexOf(" ") != -1){
            textEmail.requestFocus();
            textEmail.setError(getString(R.string.email_invalido));
            return  true;
        }else if (senha.indexOf(" ") != -1){
            textSenha.requestFocus();
            textSenha.setError(getString(R.string.campo_invalido));
            return  true;
        }else  if (repetirSenha.indexOf(" ") != -1) {
            textRepetirSenha.requestFocus();
            textRepetirSenha.setError(getString(R.string.campo_invalido));
            return true;
        }else  if (!(senha.equals(repetirSenha))){
            textRepetirSenha.requestFocus();
            textRepetirSenha.setError(getString(R.string.campo_senha_diferentes));
            return true;
        }else if(dataNascimentoString.indexOf(" ") != -1) {
            textDataNascimento.requestFocus();
            textDataNascimento.setError(getString(R.string.campo_data_nascimento));
            return true;
        }else if (dataNascimento == null){
            textDataNascimento.requestFocus();
            textDataNascimento.setError(getString(R.string.campo_data_nascimento));
            return  true;
        }else if (adicionandoGenero() == null || adicionandoGenero().equalsIgnoreCase("")) {
            Toast.makeText(getApplication(), "Favor preencha a opção genero", Toast.LENGTH_LONG).show();
            return true;
        }return false;
    }

    private boolean tamanhoPreenchido(String senha, String repetirSenha){
        if (!(senha.length() > 4)){
            textSenha.requestFocus();
            textSenha.setError(getString(R.string.senha_curta));
            return false;
        }else if (!(repetirSenha.length()> 4)){
            textRepetirSenha.requestFocus();
            textRepetirSenha.setError(getString(R.string.senha_curta));
            return false;
        }return true;
    }

    private boolean validarEmail(CharSequence email) {
        if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())){
            textEmail.requestFocus();
            textEmail.setError(getString(R.string.email_invalido));
            return false;
        }return true;
    }

    private void setDataNascimento() {
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public void definirDataNascimento(){
        final DatePickerDialog datepicker = new DatePickerDialog(CadastroUsuarioActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int mesCerto = monthOfYear +1;
                textDataNascimento.setText(dayOfMonth + "/" + mesCerto + "/" + year);
                dataNascimentoString = dayOfMonth + "-" + monthOfYear + "-" + year;
            }
        }, year, month, day);
        datepicker.show();
    }

    public void setCadastrarUsuario() throws ParseException {
            if (validacaoDeCadastro()){

                try {
                    Usuario usuario = new Usuario();
                    usuario.setSenha(senha);
                    usuario.setEmail(email);

                    Pessoa pessoa = new Pessoa();
                    pessoa.setNome(nome);
                    pessoa.setDataNascimento(dataNascimento);
                    pessoa.setSexo(sexoEscolhido);
                    pessoa.setUsuario(usuario);

                    //usuarioNegocio.inserirUsuario(pessoa);
                    Toast.makeText(getApplication(),"Cadastro realizado!",Toast.LENGTH_SHORT).show();
                    //chamar metodo para retornar a LoginActivity
                    finish();
                }catch (Exception e){
                    Toast.makeText(getApplication(),"Usuário não cadastrado",Toast.LENGTH_LONG).show();
                }
            }
    }
}