package com.example.bernardojr.whereverigo.negocio;

import com.example.bernardojr.whereverigo.dominio.Local;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Bernardojr on 26/07/2017.
 */

public interface LocalService {

    @POST("locais")
    Call<Local> getLocais();
}
