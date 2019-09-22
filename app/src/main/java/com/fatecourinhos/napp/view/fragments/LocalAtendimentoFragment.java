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
import com.fatecourinhos.napp.json.LocalAtendimentoJSONParser;
import com.fatecourinhos.napp.model.LocalAtendimento;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.MenuProfissionalActivity;
import com.fatecourinhos.napp.view.adapter.LocalAtendimentoAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroLocalAtendimento;
import com.fatecourinhos.napp.view.listener.OnLocalAtendimentoInteractionListener;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LocalAtendimentoFragment extends Fragment{

    private boolean sucesso;
    private String conteudo;
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

                Intent intent = new Intent(getActivity(), CadastroLocalAtendimento.class);
                intent.putExtra("idLocalAtendimento", localAtendimento.getIdLocalAtendimento());
                intent.putExtra("nomeLocal", localAtendimento.getNomeLocal());
                intent.putExtra("nomeBloco", localAtendimento.getNomeBloco());

                getActivity().startActivity(intent);
            }

            @Override
            public void onDeleteClick(final LocalAtendimento localAtendimento) {

                new AlertDialog.Builder(context)
                        .setTitle("Remover local de atendimento?")
                        .setMessage("Deseja remover o local de atendimento?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                excluirLocalAtendimento(localAtendimento.getIdLocalAtendimento());
                            }
                        })
                        .setNeutralButton("Não", null)
                        .show();
            }
        };

        selecionarLocalAtendimento();
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        selecionarLocalAtendimento();
    }

    private void excluirLocalAtendimento(int id) {

        String uri = "http://vitorsilva.xyz/napp/localAtendimento/excluirLocalAtendimento.php";
        ExcluirLocalAtendimento task = new ExcluirLocalAtendimento();
        RequestHttp requestHttp = new RequestHttp();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idLocalAtendimento", String.valueOf(id));

        task.execute(requestHttp);

    }

    private void selecionarLocalAtendimento() {

        String uri = "http://vitorsilva.xyz/napp/localAtendimento/selecionarLocaisAtendimento.php";
        SelecionarLocaisAtendimento task = new SelecionarLocaisAtendimento();

        task.execute(uri);

    }

    private class SelecionarLocaisAtendimento extends AsyncTask<String, String, List<LocalAtendimento>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<LocalAtendimento> doInBackground(String... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            locaisAtendimento = LocalAtendimentoJSONParser.parseDados(conteudo);
            return locaisAtendimento;
        }

        @Override
        protected void onPostExecute(final List<LocalAtendimento> locaisAtendimento) {
            super.onPostExecute(locaisAtendimento);

            localAtendimentoAdapter = new LocalAtendimentoAdapter(locaisAtendimento, listener);
            viewHolder.recyclerViewLocaisAtendimento.setAdapter(localAtendimentoAdapter);
        }

    }

    private class ExcluirLocalAtendimento extends AsyncTask<RequestHttp, String, String> {

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
                selecionarLocalAtendimento();
            } else
                Toast.makeText(view.getContext(),"Erro ao excluir", Toast.LENGTH_SHORT).show();
        }

    }

    private static class ViewHolder {
        RecyclerView recyclerViewLocaisAtendimento;
    }

}