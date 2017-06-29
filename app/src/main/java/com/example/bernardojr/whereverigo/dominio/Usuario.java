package com.example.bernardojr.whereverigo.dominio;

import java.util.Date;

public class Usuario {

    private String senha;
    private String repetirSenha;

    public Usuario(){
        this.senha = null;
        this.repetirSenha = null;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRepetirSenha() {
        return repetirSenha;
    }

    public void setRepetirSenha(String repetirSenha) {
        this.repetirSenha = repetirSenha;
    }

}
