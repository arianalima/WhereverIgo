package com.example.bernardojr.whereverigo.dominio;

public class Local {
    private int id;
    private String cidade;
    private String estadoPais;
    private String descricao;
    private String strImagem;
    private int imagem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstadoPais() {
        return estadoPais;
    }

    public void setEstadoPais(String estadoPais) {
        this.estadoPais = estadoPais;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStrImagem() {
        return strImagem;
    }

    public void setStrImagem(String str) {
        this.strImagem = str;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public Local() {
    }

    public Local(int id, String cidade, String estadoPais, String strImagem, String descricao) {
        this.id = id;
        this.cidade = cidade;
        this.estadoPais = estadoPais;
        this.descricao = descricao;
        this.strImagem = strImagem;
    }



}
