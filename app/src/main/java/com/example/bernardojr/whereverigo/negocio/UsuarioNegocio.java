package com.example.bernardojr.whereverigo.negocio;

import android.util.Log;

import com.example.bernardojr.whereverigo.R;
import com.example.bernardojr.whereverigo.dominio.Pessoa;
import com.example.bernardojr.whereverigo.dominio.Usuario;
import com.example.bernardojr.whereverigo.gui.LoginActivity;
import com.example.bernardojr.whereverigo.infra.Criptografia;
import com.example.bernardojr.whereverigo.infra.WhereverIgoException;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;

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

   /* Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(UsuarioService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    UsuarioService service = retrofit.create(UsuarioService.class);

    public void consultarEmail(String email){

        Call<Usuario> requestCatalog = service.consultarEmail(email.toString());
        requestCatalog.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (!response.isSuccessful()){
                    Log.i("script", "Erro"+ response.code());

                } else {
                    //Rquisição retornou com sucesso
                    List<Usuario> usuarioList = (List<Usuario>) response.body();

                    for (Usuario c : usuarioList){
                        c.getEmail();
                        Log.i("script","email: "+c.getEmail());
                    }
                    //UsusarioCatalog catalog = response.body();
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                t.printStackTrace();
                Log.i("script", "Erro"+t.getMessage());
            }
        });

    }

    public void inserirUsuario(Pessoa pessoa){
        String genero = null;

        Call<Pessoa> call = service.insertUsuario(
                pessoa.getUsuario().getEmail(),
                pessoa.getUsuario().getSenha(),
                pessoa.getNome(),
                pessoa.getDataNascimento(),
                pessoa.getSexo(genero));

        call.enqueue(new Callback<Pessoa>() {
            @Override
            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {

            }
            @Override
            public void onFailure(Call<Pessoa> call, Throwable t) {

            }
        });

    }

    //** Faz a requisição no banco por email e solicita ao usuarioDAO a inserção do usuário. */
//    public void inserirUsuario(Pessoa pessoa) throws WhereverIgoException {
//        Usuario emailEncontrado = null;
//
//        criptografia.receberSenhaOriginal(pessoa.getUsuario().getSenha());
//        senhaCriptografada = criptografia.getSenhaCriptografada();
//        pessoa.getUsuario().setSenha(senhaCriptografada);
//
//        try{
//
//            //emailEncontrado = usuarioDAO.buscarUsuarioPorEmail(pessoa.getUsuario().getEmail());
//
//        }catch (Exception e){
//            throw new WhereverIgoException("Erro ao verificar e-mail digitado");
//        }
//        if (emailEncontrado != null){
//            throw new WhereverIgoException("Email já cadastrado");
//        }else {
//            pessoa.getUsuario().setSenha(senhaCriptografada);
//            //usuarioDAO.inserirUsuario(usuario);
//        }
//    }*/

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

    public void consultarUsuario(String user, String senha){

       /* Call<Usuario> requestCatalog = service.consultarUsuario(user.toString(),senha.toString());
        requestCatalog.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (!response.isSuccessful()){
                    Log.i("script", "Erro"+ response.code());

                } else {
                    //Rquisição retornou com sucesso
                    List<Usuario> usuarioList = (List<Usuario>) response.body();

                    SessaoUsuario sessaoUsuario = SessaoUsuario.getInstancia();
                    sessaoUsuario.setUsuarioLogado();
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                t.printStackTrace();
                Log.i("script", "Erro"+t.getMessage());
            }
        });
        */

    }

}