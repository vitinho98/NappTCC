package com.fatecourinhos.napp.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.LocalAtendimentoController;
import com.fatecourinhos.napp.model.CampoAtuacao;
import com.fatecourinhos.napp.model.LocalAtendimento;
import com.fatecourinhos.napp.view.adapter.CampoAtuacaoAdapter;
import com.fatecourinhos.napp.view.adapter.LocalAtendimentoAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroCampoAtuacao;
import com.fatecourinhos.napp.view.cadastros.CadastroLocalAtendimento;
import com.fatecourinhos.napp.view.listener.OnCampoAtuacaoInteractionListener;
import com.fatecourinhos.napp.view.listener.OnLocalAtendimentoInteractionListener;
import com.fatecourinhos.napp.view.listener.RetrofitClass;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocalAtendimentoFragment extends Fragment{

    private List<LocalAtendimento> locaisAtendimento;
    private OnLocalAtendimentoInteractionListener listener;
    private ViewHolder viewHolder = new ViewHolder();
    private LocalAtendimentoAdapter localAtendimentoAdapter;
    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        getActivity().setTitle("Locais de Atendimento");

        view = inflater.inflate(R.layout.fragment_local_atendimento,container,false);
        context = view.getContext();

        viewHolder.recyclerViewLocaisAtendimento = view.findViewById(R.id.recycler_view_local_atendimento);
        viewHolder.recyclerViewLocaisAtendimento.setLayoutManager(new LinearLayoutManager(context));

        //seta os eventos da lista
        listener = new OnLocalAtendimentoInteractionListener() {
            @Override
            public void onListClick(LocalAtendimento localAtendimento) {

                Bundle data = new Bundle();
                data.putInt("idLocalAtendimento", localAtendimento.getIdLocalAtendimento());
                data.putString("nomeLocal", localAtendimento.getNomeLocal());
                data.putString("nomeBloco", localAtendimento.getNomeBloco());

                CadastroLocalAtendimento cadastroLocalAtendimento = new CadastroLocalAtendimento();
                cadastroLocalAtendimento.setArguments(data);
                cadastroLocalAtendimento.show(getFragmentManager(), "LOCAL");

            }

            @Override
            public void onDeleteClick(LocalAtendimento localAtendimento) {

            }
        };

        carregarLocaisAtendimento();

        return view;

    }

    private void carregarLocaisAtendimento(){

        RetrofitClass retrofit = RetrofitClass.retrofit.create(RetrofitClass.class);
        Call<List<LocalAtendimento>> call = retrofit.getLocaisAtendimento();
        call.enqueue(new Callback<List<LocalAtendimento>>() {
            @Override
            public void onResponse(Call<List<LocalAtendimento>> call, Response<List<LocalAtendimento>> response) {
                locaisAtendimento = response.body();
                localAtendimentoAdapter = new LocalAtendimentoAdapter(locaisAtendimento, listener);
                viewHolder.recyclerViewLocaisAtendimento.setAdapter(localAtendimentoAdapter);
            }

            @Override
            public void onFailure(Call<List<LocalAtendimento>> call, Throwable t) {

            }
        });

    }

    private static class ViewHolder {
        RecyclerView recyclerViewLocaisAtendimento;
    }

}