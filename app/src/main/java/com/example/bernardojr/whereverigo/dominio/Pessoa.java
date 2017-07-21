package com.example.bernardojr.whereverigo.dominio;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.Date;
@Root(name = "pessoa")
public class Pessoa{

    // link do tutorial : https://futurestud.io/tutorials/retrofit-how-to-integrate-xml-converter

    @Element(name = "id")
    private int id;
    @Element(name = "usuario")
    private Usuario usuario;
    @Element(name = "nome")
    private String nome;
    @Element(name = "dataNascimento")
    private Date dataNascimento;
    @Element(name = "sexo")
    private String sexo;

    public Pessoa(String nome, Date dataNascimento,String sexo){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }

    public Pessoa(String nome, Date dataNascimento,String sexo,Usuario usuario){
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.usuario = usuario;
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo(String genero) {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
