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
import com.fatecourinhos.napp.json.CampoAtuacaoJSONParser;
import com.fatecourinhos.napp.model.CampoAtuacao;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.adapter.CampoAtuacaoAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroCampoAtuacao;
import com.fatecourinhos.napp.view.listener.OnCampoAtuacaoInteractionListener;

import java.util.List;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CampoAtuacaoFragment extends Fragment {

    //variaveis globais
    private boolean sucesso;
    private String conteudo;
    private List<CampoAtuacao> camposAtuacao;

    //componentes da tela
    private OnCampoAtuacaoInteractionListener listener;
    private ViewHolder viewHolder;
    private CampoAtuacaoAdapter campoAtuacaoAdapter;
    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {

        getActivity().setTitle("Campos de Atuação");
        view = inflater.inflate(R.layout.fragment_area_atuacao,container,false);
        context = view.getContext();
        viewHolder = new ViewHolder();

        viewHolder.recyclerViewCampoAtuacao = view.findViewById(R.id.recycler_view_area_atuacao);
        viewHolder.recyclerViewCampoAtuacao.setLayoutManager(new LinearLayoutManager(context));

        //seta os eventos da lista
        listener = new OnCampoAtuacaoInteractionListener() {
            @Override
            public void onListClick(CampoAtuacao campoAtuacao) {

                Intent intent = new Intent(getActivity(), CadastroCampoAtuacao.class);
                intent.putExtra("idCampoAtuacao", campoAtuacao.getIdCampoAtuacao());
                intent.putExtra("nomeCampoAtuacao", campoAtuacao.getNomeCampoAtuacao());

                getActivity().startActivity(intent);
            }

            @Override
            public void onDeleteClick(final CampoAtuacao campoAtuacao) {

                new AlertDialog.Builder(context)
                        .setTitle("Remover campo de atuação")
                        .setMessage("Deseja remover o campo de atuação?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                excluirCampoAtuacao(campoAtuacao.getIdCampoAtuacao());
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
        selecionarCamposAtuacao();
    }

    private void excluirCampoAtuacao(int id) {

        String uri = "http://vitorsilva.xyz/napp/campoAtuacao/excluirCampoAtuacao.php";
        RequestHttp requestHttp = new RequestHttp();
        ExcluirCampoAtuacao task = new ExcluirCampoAtuacao();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idCampoAtuacao", String.valueOf(id));

        task.execute(requestHttp);

    }

    private void selecionarCamposAtuacao() {

        String uri = "http://vitorsilva.xyz/napp/campoAtuacao/selecionarCamposAtuacao.php";
        SelecionarCamposAtuacao task = new SelecionarCamposAtuacao();

        task.execute(uri);

    }

    private class SelecionarCamposAtuacao extends AsyncTask<String, String, List<CampoAtuacao>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<CampoAtuacao> doInBackground(String... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            camposAtuacao = CampoAtuacaoJSONParser.parseDados(conteudo);
            return camposAtuacao;
        }

        @Override
        protected void onPostExecute(List<CampoAtuacao> camposAtuacao) {
            super.onPostExecute(camposAtuacao);

            campoAtuacaoAdapter = new CampoAtuacaoAdapter(camposAtuacao, listener);
            viewHolder.recyclerViewCampoAtuacao.setAdapter(campoAtuacaoAdapter);
        }

    }

    private class ExcluirCampoAtuacao extends AsyncTask<RequestHttp, String, String> {

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
                conteudo = null;
            }

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (sucesso) {
                Toast.makeText(view.getContext(), "Excluído com sucesso", Toast.LENGTH_SHORT).show();
                selecionarCamposAtuacao();
            } else
                Toast.makeText(view.getContext(),"Erro ao excluir", Toast.LENGTH_SHORT).show();
        }

    }

    private static class ViewHolder {
        RecyclerView recyclerViewCampoAtuacao;
    }

}