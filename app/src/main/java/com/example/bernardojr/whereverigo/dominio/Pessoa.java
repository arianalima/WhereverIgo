package com.example.bernardojr.whereverigo.dominio;

public class Pessoa {
    private int id;
    private Usuario usuario;
    private String nome;
    private String email;
    private String dataNascimento;
    private Enum<Genero> generoEnum;

    public Pessoa(String nome, String email, String dataNascimento,Enum<Genero> generoEnum){
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.generoEnum = generoEnum;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Enum<Genero> getGeneroEnum() {
        return generoEnum;
    }

    public void setGeneroEnum(Enum<Genero> generoEnum) {
        this.generoEnum = generoEnum;
    }
}
