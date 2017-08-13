package com.example.bernardojr.whereverigo.negocio;

import com.example.bernardojr.whereverigo.dominio.Pessoa;

public class SessaoUsuario {
    private static SessaoUsuario instanciaSessaoUsuario = new SessaoUsuario();
    private SessaoUsuario(){}
    public static SessaoUsuario getInstancia() { return instanciaSessaoUsuario; }

    private Pessoa pessoaLogada = null;

    public Pessoa getPessoaLogada() {
        return pessoaLogada;
    }

    public void setPessoaLogada(Pessoa pessoa) {
        this.pessoaLogada = pessoa;
    }

    public void invalidarSessao(){
        this.setPessoaLogada(null);
    }
}
