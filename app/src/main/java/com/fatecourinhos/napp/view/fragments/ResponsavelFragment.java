package com.fatecourinhos.napp.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.ResponsavelJSONParser;
import com.fatecourinhos.napp.model.Responsavel;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.view.cadastros.CadastroResponsavel;
import com.fatecourinhos.napp.view.adapter.ResponsavelAdapter;
import com.fatecourinhos.napp.view.listener.OnResponsavelInteractionListener;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ResponsavelFragment extends Fragment{

    private String conteudo;
    private List<Responsavel> responsaveis;
    private ResponsavelAdapter responsavelAdapter;
    private OnResponsavelInteractionListener listener;
    private ViewHolder viewHolder = new ViewHolder();
    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {

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

        selecionarResponsaveis();
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        selecionarResponsaveis();
    }

    public void selecionarResponsaveis() {
        String uri = "http://vitorsilva.xyz/napp/responsavel/selecionarResponsaveis.php";
        SelecionarResponsaveis mytask = new SelecionarResponsaveis();
        mytask.execute(uri);
    }

    private class SelecionarResponsaveis extends AsyncTask<String, String, List<Responsavel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Responsavel> doInBackground(String... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            responsaveis = ResponsavelJSONParser.parseDados(conteudo);
            return responsaveis;
        }

        @Override
        protected void onPostExecute(final List<Responsavel> responsaveis) {
            super.onPostExecute(responsaveis);

            responsavelAdapter = new ResponsavelAdapter(responsaveis, listener);
            viewHolder.recyclerViewResponsaveis.setAdapter(responsavelAdapter);
        }
    }

    private static class ViewHolder {
        RecyclerView recyclerViewResponsaveis;
    }

}