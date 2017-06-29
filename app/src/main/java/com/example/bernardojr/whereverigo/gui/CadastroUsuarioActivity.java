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
import android.widget.Toast;

import com.example.bernardojr.whereverigo.R;
import com.example.bernardojr.whereverigo.dominio.Genero;

import java.util.Calendar;

public class CadastroUsuarioActivity extends Activity {

    private EditText textNome, textEmail, textSenha, textRepetirSenha;
    private EditText textDataNascimento;
    private Button cadastrarUsuario;
    private RadioButton buttonMasculino, buttonFeminino;
    private Genero genero;

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
        cadastrarUsuario = (Button) findViewById(R.id.btn_cadastrarUsuario);
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

        cadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setCadastrarUsuario();

                buttonMasculino.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(CadastroUsuarioActivity.this, "Elemento masculino marcado", Toast.LENGTH_SHORT).show();
                    }
                });
                buttonFeminino.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(CadastroUsuarioActivity.this,"Elemento feminino marcado",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void setCadastrarUsuario(){
        if (validacaoCadastro()){

        }
    }

    private boolean validacaoCadastro(){
        String nome = textNome.getText().toString().trim();
        String email = textEmail.getText().toString().trim();
        String senha = textSenha.getText().toString().trim();
        String repetirSenha = textRepetirSenha.getText().toString().trim();
        String dataNascimento = textDataNascimento.getText().toString().trim();

        return (!validaCamposVazios(nome,email,senha,repetirSenha,dataNascimento)&&validarEmail(email));
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

    private boolean validarEmail(CharSequence email) {
        if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())){
            textEmail.requestFocus();
            textEmail.setError(getResources().getString(R.string.email_invalido));
            return false;
        }
        return true;
    }

//    public boolean validarRadioButtonGenero(View view){
//        boolean checked =((RadioButton) view).isChecked();
//        switch (view.getId()){
//            case R.id.radioButton1:
//                if(checked)
//                    Toast.makeText(getApplicationContext(),"Elemento masculino marcado", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.radioButton2:
//                if(checked)
//                    Toast.makeText(getApplicationContext(),"Elemento feminino marcado", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return checked;
//    }

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

}



