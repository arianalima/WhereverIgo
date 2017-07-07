package com.example.bernardojr.whereverigo.negocio;

import android.util.Log;

import com.example.bernardojr.whereverigo.R;
import com.example.bernardojr.whereverigo.dominio.Pessoa;
import com.example.bernardojr.whereverigo.dominio.Usuario;
import com.example.bernardojr.whereverigo.gui.LoginActivity;
import com.example.bernardojr.whereverigo.infra.Criptografia;
import com.example.bernardojr.whereverigo.infra.WhereverIgoException;

public class UsuarioNegocio {

    private String senhaCriptografada;
    private static UsuarioNegocio instancia = new UsuarioNegocio();
    private Criptografia criptografia;

    private UsuarioNegocio(){
        //this.usuarioDAO = UsuarioDAO.getInstancia();
    }

    public static UsuarioNegocio getInstancia(){
        return instancia;
    }


    //** Faz a requisição no banco por email e solicita ao usuarioDAO a inserção do usuário. */
    public void inserirUsuario(Usuario usuario) throws WhereverIgoException {
        Usuario emailEncontrado = null;

        criptografia.receberSenhaOriginal(usuario.getSenha());
        senhaCriptografada = criptografia.getSenhaCriptografada();
        usuario.setSenha(senhaCriptografada);

        Log.i("Script","senha cri "+senhaCriptografada);
        try{
            //emailEncontrado = usuarioDAO.buscarUsuarioPorEmail(usuario.getEmail());

        }catch (Exception e){
            throw new WhereverIgoException("Erro ao verificar e-mail digitado");
        }
        if (emailEncontrado != null){
            throw new WhereverIgoException("Email já cadastrado");
        }else {
            usuario.setSenha(senhaCriptografada);
            //usuarioDAO.inserirUsuario(usuario);
        }
    }

    public Usuario logar(String login, String senha) throws WhereverIgoException {
        Usuario usuario = null;
        //Usuario usuario = usuarioDao.buscarUsuario(login, senha);
        String loginInvalido = "";

        if (usuario == null) {
            loginInvalido = LoginActivity.getContexto().getString(R.string.login_invalido);
        }
        if (loginInvalido.length() > 0) {
            throw new WhereverIgoException(loginInvalido);
        }

        SessaoUsuario sessaoUsuario = SessaoUsuario.getInstancia();
        sessaoUsuario.setUsuarioLogado(usuario);
        sessaoUsuario.setPessoaLogada(pesquisarPorId(usuario.getId()));

        return usuario;
    }

    public Pessoa pesquisarPorId(int id) {
        Pessoa pessoa = null;
        //pessoa = usuarioDao.buscarPessoaId(id);
        return pessoa;
    }

}
