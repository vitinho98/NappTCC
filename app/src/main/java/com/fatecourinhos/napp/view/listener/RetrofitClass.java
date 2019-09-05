package com.fatecourinhos.napp.view.listener;

import com.fatecourinhos.napp.model.CampoAtuacao;
import com.fatecourinhos.napp.model.Diagnostico;
import com.fatecourinhos.napp.model.LocalAtendimento;
import com.fatecourinhos.napp.model.Responsavel;

import java.util.List;

import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface RetrofitClass {

    @GET("campoAtuacao/selecionarCamposAtuacao.php")
    Call<List<CampoAtuacao>> getCamposAtuacao();

    @GET("diagnostico/selecionarDiagnosticos.php")
    Call<List<Diagnostico>> getDiagnosticos();

    @GET("localAtendimento/selecionarLocaisAtendimento.php")
    Call<List<LocalAtendimento>> getLocaisAtendimento();

    @GET("responsavel/selecionarResponsaveis.php")
    Call<List<Responsavel>> getResponsaveis();

    retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder().baseUrl("http://vitorsilva.xyz/napp/").addConverterFactory(GsonConverterFactory.create()).build();


}
