package com.fatecourinhos.napp.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.ResponsavelController;
import com.fatecourinhos.napp.model.CampoAtuacao;
import com.fatecourinhos.napp.model.Responsavel;
import com.fatecourinhos.napp.view.adapter.CampoAtuacaoAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroResponsavel;
import com.fatecourinhos.napp.view.adapter.ResponsavelAdapter;
import com.fatecourinhos.napp.view.listener.OnCampoAtuacaoInteractionListener;
import com.fatecourinhos.napp.view.listener.OnResponsavelInteractionListener;
import com.fatecourinhos.napp.view.listener.RetrofitClass;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResponsavelFragment extends Fragment{

    private List<Responsavel> responsaveis;
    private ResponsavelAdapter responsavelAdapter;
    private OnResponsavelInteractionListener listener;
    private ViewHolder viewHolder = new ViewHolder();
    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        getActivity().setTitle("Responsaveis");
        view = inflater.inflate(R.layout.fragment_responsavel,container,false);
        context = view.getContext();

        viewHolder.recyclerViewResponsaveis = view.findViewById(R.id.recycler_view_responsavel);
        viewHolder.recyclerViewResponsaveis.setLayoutManager(new LinearLayoutManager(context));

        listener = new OnResponsavelInteractionListener() {
            @Override
            public void onListClick(Responsavel responsavel) {

                Intent intent = new Intent(getActivity(), CadastroResponsavel.class);

                intent.putExtra("idResponsavel", responsavel.getIdResponsavel());
                intent.putExtra("nomeResponsavel", responsavel.getNomeResponsavel());
                intent.putExtra("emailResponsavel", responsavel.getEmailResponsavel());
                intent.putExtra("celularResponsavel", responsavel.getCelularResponsavel());
                intent.putExtra("telefoneResponsavel", responsavel.getTelefoneResponsavel());

                getActivity().startActivity(intent);

            }

            @Override
            public void onDeleteClick(Responsavel responsavel) {

            }
        };

        carregarResponsaveis();

        return view;

    }

    private void carregarResponsaveis(){

        RetrofitClass retrofit = RetrofitClass.retrofit.create(RetrofitClass.class);
        Call<List<Responsavel>> call = retrofit.getResponsaveis();
        call.enqueue(new Callback<List<Responsavel>>() {
            @Override
            public void onResponse(Call<List<Responsavel>> call, Response<List<Responsavel>> response) {
                responsaveis = response.body();
                responsavelAdapter = new ResponsavelAdapter(responsaveis, listener);
                viewHolder.recyclerViewResponsaveis.setAdapter(responsavelAdapter);
            }

            @Override
            public void onFailure(Call<List<Responsavel>> call, Throwable t) {

            }
        });
    }

    private static class ViewHolder {
        RecyclerView recyclerViewResponsaveis;
    }
}