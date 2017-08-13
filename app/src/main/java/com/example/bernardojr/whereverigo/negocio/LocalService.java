package com.example.bernardojr.whereverigo.negocio;

import com.example.bernardojr.whereverigo.dominio.Local;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LocalService {

    @POST("locais")
    Call<ArrayList<Local>> getLocais();

    @FormUrlEncoded
    @POST("locaisportag")
    Call<ArrayList<Local>> getLocaisPorTag(
            @Field("tags") String tags
    );

    @FormUrlEncoded
    @POST("locaisultimapesquisa")
    Call<ArrayList<Local>> getUltimaPesquisa(
            @Field("id") int id
    );


    @FormUrlEncoded
    @POST("locaiscomnota")
    Call<String> sendLugarComNota(
            @Field("id") int id,
            @Field("lugares") String lugares,
            @Field("notas") String notas,
            @Field("todoslocais") String todoslocais
    );
}
