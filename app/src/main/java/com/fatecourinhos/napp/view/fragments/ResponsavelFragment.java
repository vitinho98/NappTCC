package com.fatecourinhos.napp.view.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.ResponsavelJSONParser;
import com.fatecourinhos.napp.model.Responsavel;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.cadastros.CadastroResponsavel;
import com.fatecourinhos.napp.view.adapter.ResponsavelAdapter;
import com.fatecourinhos.napp.view.listener.OnResponsavelInteractionListener;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ResponsavelFragment extends Fragment {

    //variaveis globais
    private boolean sucesso;
    private String conteudo;
    private List<Responsavel> responsaveis;

    //componentes da tela
    private ResponsavelAdapter responsavelAdapter;
    private OnResponsavelInteractionListener listener;
    private ViewHolder viewHolder;
    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {

        getActivity().setTitle("Responsáveis");
        view = inflater.inflate(R.layout.fragment_responsavel,container,false);
        context = view.getContext();
        viewHolder = new ViewHolder();

        viewHolder.recyclerViewResponsaveis = view.findViewById(R.id.recycler_view_responsavel);
        viewHolder.recyclerViewResponsaveis.setLayoutManager(new LinearLayoutManager(context));

        //adiciona eventos ao itens da lista
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
            public void onDeleteClick(final Responsavel responsavel) {

                new AlertDialog.Builder(context)
                        .setTitle("Remover responsável")
                        .setMessage("Deseja remover o responsável?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                excluirResponsavel(responsavel.getIdResponsavel());
                            }
                        })
                        .setNeutralButton("Não", null)
                        .show();
            }
        };

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        selecionarResponsaveis();
    }

    private void selecionarResponsaveis() {

        String uri = "http://vitorsilva.xyz/napp/responsavel/selecionarResponsaveis.php";
        SelecionarResponsaveis task = new SelecionarResponsaveis();

        task.execute(uri);

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

    private void excluirResponsavel(int id) {

        String uri = "http://vitorsilva.xyz/napp/responsavel/excluirResponsavel.php";
        ExcluirResponsavel task = new ExcluirResponsavel();
        RequestHttp requestHttp = new RequestHttp();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idResponsavel", String.valueOf(id));

        task.execute(requestHttp);

    }

    private class ExcluirResponsavel extends AsyncTask<RequestHttp, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {

            try {

                conteudo = HttpManager.getDados(params[0]);

                if (conteudo.contains("Sucesso"))
                    sucesso = true;
                else
                    sucesso = false;

            } catch (Exception e) {
                sucesso = false;
            }

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (sucesso) {
                Toast.makeText(view.getContext(), "Excluído com sucesso", Toast.LENGTH_SHORT).show();
                selecionarResponsaveis();
            } else
                Toast.makeText(view.getContext(),"Erro ao excluir", Toast.LENGTH_SHORT).show();
        }

    }

    private static class ViewHolder {
        RecyclerView recyclerViewResponsaveis;
    }

}