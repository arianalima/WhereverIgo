package com.example.bernardojr.whereverigo.dominio;

import java.io.Serializable;

public class Pessoa implements Serializable {
    private int id;
    private Usuario usuario;
    private String nome;
    private String dataNascimento;
    private String sexo;

    public Pessoa(String nome, String dataNascimento,String sexo){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }

    public Pessoa(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getGeneroEnum(String genero) {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
