package com.example.bernardojr.whereverigo.dominio;

public class Avaliacao {
    private int id;
    private int idPessoa;
    private int idLocal;
    private int nota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Avaliacao(){}

    public Avaliacao(int id, int idPessoa, int idLocal, int nota){
        this.id = id;
        this.idLocal = idLocal;
        this.idPessoa = idPessoa;
        this.nota = nota;
    }
}
