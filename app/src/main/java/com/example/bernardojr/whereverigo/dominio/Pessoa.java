package com.example.bernardojr.whereverigo.dominio;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Pessoa{

    // link do tutorial : https://futurestud.io/tutorials/retrofit-how-to-integrate-xml-converter

    private int id;
    private Usuario usuario;
    private String nome;
    private Date dataNascimento;
    private String sexo;
    private String strDataNascimento;
/*
    @Element(name = "tags")
    private List<Tag> tags;

    @Element(name = "avaliacoes")
    private List<Avaliacao> avaliacoes;

    @Element(name = "recomendacoes")
    private List<Local> locaisRecomendados;
*/


    public Pessoa() {
    }

    public Pessoa(int id, Usuario usuario, String nome, String sexo, String strDataNascimento) {
        this.id = id;
        this.usuario = usuario;
        this.nome = nome;
        this.sexo = sexo;
        this.strDataNascimento = strDataNascimento;
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getStrDataNascimento() {
        return strDataNascimento;
    }

    public void setStrDataNascimento(String strDataNascimento) {
        this.strDataNascimento = strDataNascimento;
    }
}
