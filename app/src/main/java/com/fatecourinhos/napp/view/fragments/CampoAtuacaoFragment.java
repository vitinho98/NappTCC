package com.fatecourinhos.napp.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.CampoAtuacao;
import com.fatecourinhos.napp.view.adapter.CampoAtuacaoAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroCampoAtuacao;
import com.fatecourinhos.napp.view.listener.OnCampoAtuacaoInteractionListener;
import com.fatecourinhos.napp.view.listener.RetrofitClass;

import java.util.List;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CampoAtuacaoFragment extends Fragment{

    private List<CampoAtuacao> camposAtuacao;
    private OnCampoAtuacaoInteractionListener listener;
    private ViewHolder viewHolder = new ViewHolder();
    private CampoAtuacaoAdapter campoAtuacaoAdapter;
    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        getActivity().setTitle("Campos de Atuação");
        view = inflater.inflate(R.layout.fragment_area_atuacao,container,false);
        context = view.getContext();

        viewHolder.recyclerViewCampoAtuacao = view.findViewById(R.id.recycler_view_area_atuacao);
        viewHolder.recyclerViewCampoAtuacao.setLayoutManager(new LinearLayoutManager(context));

        //seta os eventos da lista
        listener = new OnCampoAtuacaoInteractionListener() {
            @Override
            public void onListClick(CampoAtuacao campoAtuacao) {

                Bundle data = new Bundle();
                data.putInt("idCampoAtuacao", campoAtuacao.getIdCampoAtuacao());
                data.putString("nomeCampoAtuacao", campoAtuacao.getNomeCampoAtuacao());
                data.putString("operação", "alteração");

                CadastroCampoAtuacao cadastroCampoAtuacao = new CadastroCampoAtuacao();
                cadastroCampoAtuacao.setArguments(data);
                cadastroCampoAtuacao.show(getFragmentManager(), "CAMPOATUACAO");
            }

            @Override
            public void onDeleteClick(CampoAtuacao campoAtuacao) {

            }
        };

        carregarCamposAtuacao();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        carregarCamposAtuacao();
    }

    //busca os dados no banco e adapta para a lista
    private void carregarCamposAtuacao() {

        RetrofitClass retrofit = RetrofitClass.retrofit.create(RetrofitClass.class);
        Call<List<CampoAtuacao>> call = retrofit.getCamposAtuacao();
        call.enqueue(new Callback<List<CampoAtuacao>>() {
            @Override
            public void onResponse(Call<List<CampoAtuacao>> call, Response<List<CampoAtuacao>> response) {
                camposAtuacao = response.body();
                campoAtuacaoAdapter = new CampoAtuacaoAdapter(camposAtuacao, listener);
                viewHolder.recyclerViewCampoAtuacao.setAdapter(campoAtuacaoAdapter);
            }

            @Override
            public void onFailure(Call<List<CampoAtuacao>> call, Throwable t) {

            }
        });

    }

    private static class ViewHolder {
        RecyclerView recyclerViewCampoAtuacao;
    }

}