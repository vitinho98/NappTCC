package com.fatecourinhos.napp.view.fragments.aluno;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.EncaminhamentoJSONParser;
import com.fatecourinhos.napp.model.Encaminhamento;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.adapter.aluno.EncaminhamentoAlunoAdapter;
import com.fatecourinhos.napp.view.listener.OnEncaminhamentoInteractionListener;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class EncaminhamentoAlunoFragment extends Fragment {

    //variaveis globais
    private boolean sucesso;
    private String conteudo;
    private List<Encaminhamento> encaminhamentos;
    private SharedPreferences preferences;
    int id;

    //componentes da tela
    private ProgressBar progressBar;
    private OnEncaminhamentoInteractionListener listener;
    private ViewHolder viewHolder;
    private EncaminhamentoAlunoAdapter encaminhamentoAlunoAdapter;
    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getActivity().setTitle("Encaminhamentos");
        view = inflater.inflate(R.layout.fragment_encaminhamento,container,false);
        context = view.getContext();
        viewHolder = new ViewHolder();
        preferences = context.getSharedPreferences("user_settings", MODE_PRIVATE);

        progressBar = view.findViewById(R.id.progressBarEncaminhamento);

        viewHolder.recyclerViewEncaminhamentos = view.findViewById(R.id.recycler_view_encaminhamentos);
        viewHolder.recyclerViewEncaminhamentos.setLayoutManager(new LinearLayoutManager(context));

        //seta os eventos da lista
        listener = new OnEncaminhamentoInteractionListener() {
            @Override
            public void onListClick(Encaminhamento encaminhamento) {

                Intent intent = new Intent(getActivity(), VisualizarEncaminhamentoAluno.class);

                intent.putExtra("idProfissionalExterno", encaminhamento.getFkProfissionalExterno().getIdProfissionalExterno());
                intent.putExtra("emailProfissionalExterno", encaminhamento.getFkProfissionalExterno().getEmailProfissionalExterno());
                intent.putExtra("nomeProfissionalExterno", encaminhamento.getFkProfissionalExterno().getNomeProfissionalExterno());
                intent.putExtra("cidadeProfissionalExterno", encaminhamento.getFkProfissionalExterno().getCidadeProfissionalExterno());
                intent.putExtra("numero", encaminhamento.getFkProfissionalExterno().getNumero());
                intent.putExtra("bairro", encaminhamento.getFkProfissionalExterno().getBairro());
                intent.putExtra("endereco", encaminhamento.getFkProfissionalExterno().getEndereco());
                intent.putExtra("celularProfissionalExterno", encaminhamento.getFkProfissionalExterno().getCelularProfissionalExterno());
                intent.putExtra("telefoneProfissionalExterno", encaminhamento.getFkProfissionalExterno().getTelefoneProfissionalExterno());

                if (encaminhamento.getFkProfissionalExterno().getFkCampoAtuacao() != null) {
                    intent.putExtra("nomeC", encaminhamento.getFkProfissionalExterno().getFkCampoAtuacao().getNomeCampoAtuacao());
                }

                if (encaminhamento.getFkProfissionalExterno().getFkResponsavel() != null) {
                    intent.putExtra("nomeResponsavel", encaminhamento.getFkProfissionalExterno().getFkResponsavel().getNomeResponsavel());
                    intent.putExtra("emailResponsavel", encaminhamento.getFkProfissionalExterno().getFkResponsavel().getEmailResponsavel());
                    intent.putExtra("celularResponsavel", encaminhamento.getFkProfissionalExterno().getFkResponsavel().getCelularResponsavel());
                    intent.putExtra("telefoneResponsavel", encaminhamento.getFkProfissionalExterno().getFkResponsavel().getTelefoneResponsavel());

                }

                intent.putExtra("nomeProfissional", encaminhamento.getFkProfissional().getNomeProfissional());

                startActivity(intent);

            }

            @Override
            public void onDeleteClick(Encaminhamento encaminhamento) {

            }
        };

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        selecionarEncaminhamentos();
    }

    private void selecionarEncaminhamentos() {

        if (preferences.contains("idAluno")) {
            id = preferences.getInt("idAluno", 0);
        }

        String uri = "http://vitorsilva.xyz/napp/encaminhamento/selecionarEncaminhamentos.php";
        SelecionarEncaminhamentos task = new SelecionarEncaminhamentos();
        RequestHttp requestHttp = new RequestHttp();

        requestHttp.setMetodo("GET");
        requestHttp.setParametro("idAluno", String.valueOf(id));
        requestHttp.setUrl(uri);

        task.execute(requestHttp);

    }

    private class SelecionarEncaminhamentos extends AsyncTask<RequestHttp, String, List<Encaminhamento>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(ProgressBar.VISIBLE);
        }

        @Override
        protected List<Encaminhamento> doInBackground(RequestHttp... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            encaminhamentos = EncaminhamentoJSONParser.parseDados(conteudo);
            return encaminhamentos;
        }

        @Override
        protected void onPostExecute(List<Encaminhamento> encaminhamentos) {
            super.onPostExecute(encaminhamentos);

            encaminhamentoAlunoAdapter = new EncaminhamentoAlunoAdapter(encaminhamentos, listener);
            viewHolder.recyclerViewEncaminhamentos.setAdapter(encaminhamentoAlunoAdapter);
            progressBar.setVisibility(ProgressBar.INVISIBLE);
        }

    }

    private static class ViewHolder {
        RecyclerView recyclerViewEncaminhamentos;
    }

}
