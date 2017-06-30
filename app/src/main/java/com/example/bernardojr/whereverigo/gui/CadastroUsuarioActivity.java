package com.example.bernardojr.whereverigo.gui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bernardojr.whereverigo.R;

import java.util.Calendar;

public class CadastroUsuarioActivity extends Activity {

    private EditText textNome, textEmail, textSenha, textRepetirSenha;
    private EditText textDataNascimento;
    private Button buttonCadastrar;
    private RadioGroup radioGroup;
    private RadioButton buttonMasculino, buttonFeminino;
    private String sexo;

    private Calendar calendar = Calendar.getInstance();
    private int year;
    private int month;
    private int day;

    private String dataNascimento1;

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

        //chamar metodos
        chamarBotaoCadastrar();

    }

    public void chamarBotaoCadastrar(){
        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setCadastrarUsuario();

            }
        });
    }

    private boolean validacaoCadastro(){
        String nome = textNome.getText().toString().trim();
        String email = textEmail.getText().toString().trim();
        String senha = textSenha.getText().toString().trim();
        String repetirSenha = textRepetirSenha.getText().toString().trim();
        String dataNascimento = textDataNascimento.getText().toString().trim();
        adcionandoGenero();

        Log.i("Script", "verificando nome "+ nome);
        Log.i("Script", "verificando email "+ email);
        Log.i("Script", "verificando data de nascimento "+ dataNascimento);
        Log.i("Script", "verificando Genero selecionado "+ adcionandoGenero());

        return (!validaCamposVazios(nome,email,senha,repetirSenha,dataNascimento)&&validarEmail(email)&&camposSemEspacos(nome,email,senha,repetirSenha,dataNascimento));

    }

    private String adcionandoGenero(){
        final String masculino = buttonMasculino.getText().toString().trim();
        final String feminino = buttonFeminino.getText().toString().trim();

        try {
            radioGroup.setOnCheckedChangeListener(
                    new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            boolean buttonMasculino = R.id.radioButton1 == checkedId;
                            boolean buttonFeminino = R.id.radioButton2 == checkedId;
                            switch (checkedId){
                                case R.id.radioButton1:
                                    if (buttonMasculino)
                                        sexo = masculino;
                                    break;
                                case R.id.radioButton2:
                                    if(buttonFeminino)
                                        sexo = feminino;
                                    break;
                            }
                            Log.i("Script", "OK "+ sexo);

                        }
                    });
        }catch (Exception e){
            Toast.makeText(getApplication(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return sexo;
    }

    private boolean validaCamposVazios(String nome, String email, String senha, String repetirSenha, String dataNascimento){
        if (TextUtils.isEmpty(nome)){
            textNome.requestFocus();
            textNome.setError(getResources().getString(R.string.campo_vazio));
            return true;
        }else if(TextUtils.isEmpty(email)){
            textEmail.requestFocus();
            textEmail.setError(getResources().getString(R.string.campo_vazio));
            return true;
        }else if (TextUtils.isEmpty(senha)){
            textSenha.requestFocus();
            textSenha.setError(getResources().getString(R.string.campo_vazio));
            return true;
        }else if (TextUtils.isEmpty(repetirSenha)){
            textRepetirSenha.requestFocus();
            textRepetirSenha.setError(getResources().getString(R.string.campo_vazio));
            return true;
        }else if (TextUtils.isEmpty(dataNascimento)){
            textDataNascimento.requestFocus();
            textDataNascimento.setError(getResources().getString(R.string.campo_vazio));
            return true;
        }
        return false;
    }

    private boolean camposSemEspacos(String nome, String email, String senha, String repetirSenha, String dataNascimento){
        if(nome.indexOf(" ") != -1){
            textNome.requestFocus();
            textNome.setError(getResources().getString(R.string.campo_invalido));
            return true;
        }else if (email.indexOf(" ") != -1){
            textEmail.requestFocus();
            textEmail.setError(getResources().getString(R.string.email_invalido));
            return  true;
        }else if (senha.indexOf(" ") != -1){
            textSenha.requestFocus();
            textSenha.setError(getResources().getString(R.string.campo_invalido));
            return  true;
        }else  if (repetirSenha.indexOf(" ") != -1){
            textRepetirSenha.requestFocus();
            textRepetirSenha.setError(getResources().getString(R.string.campo_invalido));
            return true;
        }else if(dataNascimento.indexOf(" ") != -1){
            textDataNascimento.requestFocus();
            textDataNascimento.setError(getResources().getString(R.string.campo_data_nascimento));
            return  true;
        }
        return false;
    }

    private boolean validarEmail(CharSequence email) {
        if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())){
            textEmail.requestFocus();
            textEmail.setError(getResources().getString(R.string.email_invalido));
            return false;
        }
        return true;
    }

    private void setDataNascimento() {
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public void definirDataNascimento(){
        DatePickerDialog datepicker = new DatePickerDialog(CadastroUsuarioActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int mesCerto = monthOfYear + 1;
                textDataNascimento.setText(dayOfMonth + "/" + mesCerto + "/" + year);
                dataNascimento1 = dayOfMonth + "-" + monthOfYear + "-" + year;
            }
        }, year, month, day);
        datepicker.show();
    }

    public void setCadastrarUsuario(){
        if (validacaoCadastro()){

        }
    }

}



