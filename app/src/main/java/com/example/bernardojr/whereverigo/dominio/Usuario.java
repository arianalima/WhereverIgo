package com.example.bernardojr.whereverigo.dominio;

import java.util.Date;

public class Usuario {
    private  int id;
    private  String nome;
    private  String email;
    private  String senha;
    private  String repetirSenha;
    private  Date data;

    public Usuario(String nome, String email, String senha, String repetirSenha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.repetirSenha = repetirSenha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
