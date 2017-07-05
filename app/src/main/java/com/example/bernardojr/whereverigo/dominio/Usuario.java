package com.example.bernardojr.whereverigo.dominio;

public class Usuario {

    private int id;
    private String senha;

    Pessoa pessoa = new Pessoa();

    public Usuario() {
        this.senha = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String setEmailLogin(String email){
        email = pessoa.getEmail();
        return email;
    }
}