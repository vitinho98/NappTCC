package com.fatecourinhos.napp.controller;

import com.fatecourinhos.napp.model.LocalAtendimentoModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ILocalAtendimento {

    @POST("LocalAtendimento/inserirLocal")
    Call<Void> insereLocal(@Body LocalAtendimentoModel local);

    @GET("LocalAtendimento/buscarLocais")
    Call<List<LocalAtendimentoModel>> getLocais();

    @PUT("Livros/webresources/br.ufs.tep.livros/editar/{id}")
    Call<Void> alteraLivro(@Path("id") String id, @Body LocalAtendimentoModel local);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://vitorsilva.xyz/napp/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
