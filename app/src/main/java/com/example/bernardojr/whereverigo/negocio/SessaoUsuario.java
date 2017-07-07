package com.example.bernardojr.whereverigo.negocio;

import com.example.bernardojr.whereverigo.dominio.Pessoa;
import com.example.bernardojr.whereverigo.dominio.Usuario;

class SessaoUsuario {
    /* singleton */
    private static SessaoUsuario instanciaSessaoUsuario = new SessaoUsuario();
    private SessaoUsuario(){}
    public static SessaoUsuario getInstancia() { return instanciaSessaoUsuario; }

    private Usuario usuarioLogado = null;
    private Pessoa pessoaLogada = null;

    public Pessoa getPessoaLogada() {
        return pessoaLogada;
    }

    public void setPessoaLogada(Pessoa pessoa) {
        this.pessoaLogada = pessoa;
    }

    public void setUsuarioLogado(Usuario usuario) {
        this.usuarioLogado = usuario;
    }

    public void invalidarSessao(){
        this.setUsuarioLogado(null);
        this.setPessoaLogada(null);
    }
}
