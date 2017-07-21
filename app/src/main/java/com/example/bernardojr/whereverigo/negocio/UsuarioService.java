package com.example.bernardojr.whereverigo.negocio;


import com.example.bernardojr.whereverigo.dominio.Pessoa;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UsuarioService {

    @FormUrlEncoded
    @POST("users")
    Call<String> createUser(
            @Field("email") String email,
            @Field("senha") String senha,
            @Field("nome") String nome,
            @Field("dataNascimento") String dataNascimento,
            @Field("sexo") String sexo
    );


    @FormUrlEncoded
    @POST("existeusers")
    Call<String> existeUser(
            @Field("email") String email
    );


    @FormUrlEncoded
    @POST("getuser")
    Call<Pessoa> getUser(
            @Field("email") String email,
            @Field("senha") String senha
    );
}
