package com.example.bernardojr.whereverigo.dominio;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "usuario")
public class Usuario{

    @Element(name = "id")
    private int id;
    @Element(name = "email")
    private String email;
    @Element(name = "senha")
    private String senha;

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario(int id, String email, String senha) {
        this.id = id;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(){

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}