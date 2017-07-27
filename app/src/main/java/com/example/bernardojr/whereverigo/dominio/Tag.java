package com.example.bernardojr.whereverigo.dominio;

public enum Tag {
    PRAIA("Praia",1), ROMANTICO("Romântico",2), FRIO("Frio",3), FAMILIA("Família",4), ESPORTE_RADICAL("Esporte Radical",5),
    GASTRONOMIA("Gastronomia",6), TRANQUILO("Tranquilo",7), HISTORICO("Histórico",8), RELIGIOSO("Religioso",9);

    private String tag;
    private int id;

    Tag(String tag, int id) {
        this.id = id;
        this.tag = tag;
    }

    public String getTag(){ return this.tag; }
    public int getId(){return this.id;}
}
