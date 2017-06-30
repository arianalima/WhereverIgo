package com.example.bernardojr.whereverigo.dominio;


public enum Genero {
    MASCULINO(1), FEMININO(2);

    public int codigo;

    private Genero(int codigo){
        this.codigo = codigo;
    }
    public int getCodigo(){
        return codigo;
    }
}