package com.fatecourinhos.napp.view.fragments.profissional;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.DiagnosticoJSONParser;
import com.fatecourinhos.napp.model.Diagnostico;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.adapter.profissional.DiagnosticoAdapter;
import com.fatecourinhos.napp.view.cadastros.profissional.CadastroDiagnostico;
import com.fatecourinhos.napp.view.listener.OnDiagnosticoInteractionListener;

import java.util.List;

public class DiagnosticoFragment extends Fragment {

    //variaveis globais
    private boolean sucesso;
    private String conteudo;
    private List<Diagnostico> diagnosticos;

    //componentes da tela
    private SearchView searchView;
    private ProgressBar progressBar;
    private ViewHolder viewHolder;
    private OnDiagnosticoInteractionListener listener;
    private DiagnosticoAdapter diagnosticoAdapter;
    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {

        getActivity().setTitle("Diagnósticos");
        view = inflater.inflate(R.layout.fragment_diagnostico,container,false);
        context = view.getContext();
        viewHolder = new ViewHolder();

        progressBar = view.findViewById(R.id.progressBarDiagnostico);
        searchView = view.findViewById(R.id.search_view_diagnostico);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                diagnosticoAdapter.getFilter().filter(s);
                return false;
            }
        });

        viewHolder.recyclerViewDiagnosticos = view.findViewById(R.id.recycler_view_diagnostico);
        viewHolder.recyclerViewDiagnosticos.setLayoutManager(new LinearLayoutManager(context));

        //adiciona eventos aos itens da lista
        listener = new OnDiagnosticoInteractionListener() {
            @Override
            public void onListClick(Diagnostico diagnostico) {

                Intent intent = new Intent(getActivity(), CadastroDiagnostico.class);
                intent.putExtra("idDiagnostico", diagnostico.getIdDiagnostico());
                intent.putExtra("nomeDiagnostico", diagnostico.getNomeDiagnostico());

                getActivity().startActivity(intent);

            }

            @Override
            public void onDeleteClick(final Diagnostico diagnostico) {

                new AlertDialog.Builder(context)
                        .setTitle("Remover diagnóstico?")
                        .setMessage("Deseja remover o diagnóstico?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                excluirDiagnostico(diagnostico.getIdDiagnostico());
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
        selecionarDiagnosticos();
    }

    private void selecionarDiagnosticos() {

        String uri = "http://vitorsilva.xyz/napp/diagnostico/selecionarDiagnosticos.php";
        SelecionarDiagnosticos task = new SelecionarDiagnosticos();

        task.execute(uri);

    }

    private class SelecionarDiagnosticos extends AsyncTask<String, String, List<Diagnostico>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Diagnostico> doInBackground(String... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            diagnosticos = DiagnosticoJSONParser.parseDados(conteudo);
            return diagnosticos;
        }

        @Override
        protected void onPostExecute(List<Diagnostico> diagnosticos) {
            super.onPostExecute(diagnosticos);

            diagnosticoAdapter = new DiagnosticoAdapter(diagnosticos, listener);
            viewHolder.recyclerViewDiagnosticos.setAdapter(diagnosticoAdapter);
            progressBar.setVisibility(View.INVISIBLE);
        }

    }

    private void excluirDiagnostico(int id) {

        String uri = "http://vitorsilva.xyz/napp/diagnostico/excluirDiagnostico.php";
        ExcluirDiagnostico task = new ExcluirDiagnostico();
        RequestHttp requestHttp = new RequestHttp();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        requestHttp.setParametro("idDiagnostico", String.valueOf(id));

        task.execute(requestHttp);

    }

    private class ExcluirDiagnostico extends AsyncTask<RequestHttp, String, String> {

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
                selecionarDiagnosticos();
            } else
                Toast.makeText(view.getContext(),"Erro ao excluir", Toast.LENGTH_SHORT).show();
        }

    }

    private static class ViewHolder {
        RecyclerView recyclerViewDiagnosticos;
    }

}