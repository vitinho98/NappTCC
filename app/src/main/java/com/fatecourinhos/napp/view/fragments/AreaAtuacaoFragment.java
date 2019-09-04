package com.fatecourinhos.napp.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.CampoAtuacaoController;
import com.fatecourinhos.napp.json.CampoAtuacaoJSONParser;
import com.fatecourinhos.napp.model.CampoAtuacao;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.view.adapter.CampoAtuacaoAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroCampoAtuacao;
import java.util.List;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AreaAtuacaoFragment extends Fragment{

    private List<CampoAtuacao> camposAtuacao;
    private String conteudo;
    private ViewHolder viewHolder = new ViewHolder();

    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){
        getActivity().setTitle("Campos de Atuação");
        view = inflater.inflate(R.layout.fragment_area_atuacao,container,false);
        context = view.getContext();

        String uri = "http://vitorsilva.xyz/napp/campoAtuacao/selecionarCamposAtuacao.php";
        conteudo = HttpManager.getDados(uri);
        camposAtuacao = CampoAtuacaoJSONParser.parseDados(conteudo);

        CampoAtuacaoAdapter campoAtuacaoAdapter = new CampoAtuacaoAdapter(camposAtuacao);
        this.viewHolder.recyclerViewCampoAtuacao = view.findViewById(R.id.recycler_view_area_atuacao);
        this.viewHolder.recyclerViewCampoAtuacao.setAdapter(campoAtuacaoAdapter);
        this.viewHolder.recyclerViewCampoAtuacao.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

    private static class ViewHolder {
        RecyclerView recyclerViewCampoAtuacao;
    }

}