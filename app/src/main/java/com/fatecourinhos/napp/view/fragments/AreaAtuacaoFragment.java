package com.fatecourinhos.napp.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.CampoAtuacaoController;
import com.fatecourinhos.napp.model.CampoAtuacaoModel;
import com.fatecourinhos.napp.view.MenuProfissionalActivity;
import com.fatecourinhos.napp.view.adapter.CampoAtuacaoAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroAreaAtuacao;
import java.util.List;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AreaAtuacaoFragment extends Fragment{

    List<CampoAtuacaoModel> camposAtuacao;
    CampoAtuacaoAdapter adapter;
    RecyclerView campoAtuacaoRecycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        campoAtuacaoRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_area_atuacao,container,false);
        campoAtuacaoRecycler.setLayoutManager(layoutManager);

        return campoAtuacaoRecycler;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance){

        super.onViewCreated(view, savedInstance);

        getActivity().setTitle("Campos de Atuação");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CampoAtuacaoController campoAtuacaoController = new CampoAtuacaoController();

        camposAtuacao = campoAtuacaoController.selecionarCamposAtuacao();

        adapter = new CampoAtuacaoAdapter(camposAtuacao);

        campoAtuacaoRecycler.setAdapter(adapter);

        adapter.setListener(new CampoAtuacaoAdapter.Listener() {
            @Override
            public void onClick(CampoAtuacaoModel campoAtuacao) {

                Bundle data = new Bundle();
                data.putInt("idCampoAtuacao", campoAtuacao.getIdCampoAtuacao());
                data.putString("nomeCampoAtuacao", campoAtuacao.getNomeCampoAtuacao());
                data.putString("operação", "alteração");

                CadastroAreaAtuacao cadastroAreaAtuacao = new CadastroAreaAtuacao();
                cadastroAreaAtuacao.setArguments(data);

                cadastroAreaAtuacao.show(getFragmentManager(), "CAMPOATUACAO");

            }
        });
    }
}