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
import com.fatecourinhos.napp.json.ProfissionalExternoJSONParser;
import com.fatecourinhos.napp.model.ProfissionalExterno;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.adapter.ProfissionalExternoAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroProfissionalExterno;
import com.fatecourinhos.napp.view.listener.OnProfissionalExternoInteractionListener;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProfissionalExternoFragment extends Fragment {

    //variaveis globais
    private boolean sucesso;
    private String conteudo;
    private List<ProfissionalExterno> profissionaisExterno;

    //componentes da tela
    private ProfissionalExternoAdapter profissionalExternoAdapter;
    private OnProfissionalExternoInteractionListener listener;
    private ViewHolder viewHolder;
    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {

        getActivity().setTitle("Profissionais Externos");
        view = inflater.inflate(R.layout.fragment_profissional_externo,container,false);
        context = view.getContext();
        viewHolder = new ViewHolder();

        viewHolder.recyclerViewProfissionaisExternos = view.findViewById(R.id.recycler_view_profissional_externo);
        viewHolder.recyclerViewProfissionaisExternos.setLayoutManager(new LinearLayoutManager(context));

        //adiciona eventos aos itens da lista
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

                intent.putExtra("idC", profissionalExterno.getFkCampoAtuacao().getIdCampoAtuacao());
                intent.putExtra("nomeC", profissionalExterno.getFkCampoAtuacao().getNomeCampoAtuacao());

                intent.putExtra("idResponsavel", profissionalExterno.getFkResponsavel().getIdResponsavel());
                intent.putExtra("nomeResponsavel", profissionalExterno.getFkResponsavel().getNomeResponsavel());

                getActivity().startActivity(intent);
            }

            @Override
            public void onDeleteClick(final ProfissionalExterno profissionalExterno) {

                new AlertDialog.Builder(context)
                        .setTitle("Remover profissional externo")
                        .setMessage("Deseja remover o profissional externo?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                excluirProfissionalExterno(profissionalExterno.getIdProfissionalExterno());
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
        selecionarProfissionaisExternos();
    }

    public void selecionarProfissionaisExternos() {

        String uri = "http://vitorsilva.xyz/napp/profissionalExterno/selecionarProfExterno.php";
        SelecionarProfissionaisExternos mytask = new SelecionarProfissionaisExternos();

        mytask.execute(uri);

    }

    private class SelecionarProfissionaisExternos extends AsyncTask<String, String, List<ProfissionalExterno>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<ProfissionalExterno> doInBackground(String... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            profissionaisExterno = ProfissionalExternoJSONParser.parseDados(conteudo);
            return profissionaisExterno;
        }

        @Override
        protected void onPostExecute(final List<ProfissionalExterno> profissionaisExternos) {
            super.onPostExecute(profissionaisExternos);

            profissionalExternoAdapter = new ProfissionalExternoAdapter(profissionaisExterno, listener);
            viewHolder.recyclerViewProfissionaisExternos.setAdapter(profissionalExternoAdapter);
        }

    }

    private void excluirProfissionalExterno(int id) {

        String uri = "http://vitorsilva.xyz/napp/profissionalExterno/excluirProfExterno.php";
        ExcluirProfissionalExterno task = new ExcluirProfissionalExterno();
        RequestHttp requestHttp = new RequestHttp();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idProfissionalExterno", String.valueOf(id));

        task.execute(requestHttp);

    }

    private class ExcluirProfissionalExterno extends AsyncTask<RequestHttp, String, String> {

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
                selecionarProfissionaisExternos();
            } else
                Toast.makeText(view.getContext(),"Erro ao excluir", Toast.LENGTH_SHORT).show();
        }

    }

    private static class ViewHolder {
        RecyclerView recyclerViewProfissionaisExternos;
    }

}