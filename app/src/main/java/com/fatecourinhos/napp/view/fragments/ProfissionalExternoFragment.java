package com.fatecourinhos.napp.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.ProfissionalExterno;
import com.fatecourinhos.napp.util.RetrofitClass;
import com.fatecourinhos.napp.view.adapter.ProfissionalExternoAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroProfissionalExterno;
import com.fatecourinhos.napp.view.listener.OnProfissionalExternoInteractionListener;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfissionalExternoFragment extends Fragment{

    private List<ProfissionalExterno> profissionaisExterno;
    private ProfissionalExternoAdapter profissionalExternoAdapter;
    private OnProfissionalExternoInteractionListener listener;
    private ViewHolder viewHolder = new ViewHolder();
    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        getActivity().setTitle("Profissionais Externos");
        view = inflater.inflate(R.layout.fragment_profissional_externo,container,false);
        context = view.getContext();

        viewHolder.recyclerViewProfissionaisExternos = view.findViewById(R.id.recycler_view_profissional_externo);
        viewHolder.recyclerViewProfissionaisExternos.setLayoutManager(new LinearLayoutManager(context));

        listener = new OnProfissionalExternoInteractionListener() {
            @Override
            public void onListClick(ProfissionalExterno profissionalExterno) {

                Intent intent = new Intent(getActivity(), CadastroProfissionalExterno.class);

                intent.putExtra("idProfissionalExterno", profissionalExterno.getIdProfissionalExterno());
                intent.putExtra("emailProfissionalExterno", profissionalExterno.getEmailProfissionalExterno());
                intent.putExtra("nomeProfissionalExterno", profissionalExterno.getNomeProfissionalExterno());
                intent.putExtra("cidadeProfissionalExterno", profissionalExterno.getCidadeProfissionalExterno());
                intent.putExtra("numero", profissionalExterno.getNumero());
                intent.putExtra("bairro", profissionalExterno.getBairro());
                intent.putExtra("endereco", profissionalExterno.getEndereco());
                intent.putExtra("celularProfissionalExterno", profissionalExterno.getCelularProfissionalExterno());
                intent.putExtra("telefoneProfissionalExterno", profissionalExterno.getTelefoneProfissionalExterno());
                intent.putExtra("nomeCampoAtuacao", profissionalExterno.getFkCampoAtuacao().getNomeCampoAtuacao());
                intent.putExtra("nomeResponsavel", profissionalExterno.getFkResponsavel().getNomeResponsavel());

                getActivity().startActivity(intent);
            }

            @Override
            public void onDeleteClick(ProfissionalExterno profissionalExterno) {

            }
        };

        carregarProfissionaisExternos();

        return view;
    }

    private void carregarProfissionaisExternos(){

        RetrofitClass retrofit = RetrofitClass.retrofit.create(RetrofitClass.class);
        Call<List<ProfissionalExterno>> call = retrofit.getProfissionaisExternos();
        call.enqueue(new Callback<List<ProfissionalExterno>>() {
            @Override
            public void onResponse(Call<List<ProfissionalExterno>> call, Response<List<ProfissionalExterno>> response) {
                profissionaisExterno = response.body();
                profissionalExternoAdapter = new ProfissionalExternoAdapter(profissionaisExterno, listener);
                viewHolder.recyclerViewProfissionaisExternos.setAdapter(profissionalExternoAdapter);
            }

            @Override
            public void onFailure(Call<List<ProfissionalExterno>> call, Throwable t) {

            }
        });

    }

    private static class ViewHolder {
        RecyclerView recyclerViewProfissionaisExternos;
    }

}