package com.example.bernardojr.whereverigo.negocio;

import com.example.bernardojr.whereverigo.dominio.Pessoa;
import com.example.bernardojr.whereverigo.dominio.Usuario;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UsuarioService {

        public static final String BASE_URL =  "http://localhost:8080/UserManagement/rest/UserService/";

        @FormUrlEncoded
        @GET("users")
        Call<Usuario> consultarEmail(
            @Field("username") String email
        );

        @FormUrlEncoded
        @POST("users")
        Call<Pessoa> insertUsuario(
            @Field("email") String email,
            @Field("senha") String senha,
            @Field("nome") String nome,
            @Field("dataNascimento") Date dataNascimento,
            @Field("sexo") String sexo
         );
}

