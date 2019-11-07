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
import com.fatecourinhos.napp.json.AlunoJSONParser;
import com.fatecourinhos.napp.json.CampoAtuacaoJSONParser;
import com.fatecourinhos.napp.model.Aluno;
import com.fatecourinhos.napp.model.CampoAtuacao;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.adapter.profissional.AlunoAdapter;
import com.fatecourinhos.napp.view.adapter.profissional.CampoAtuacaoAdapter;
import com.fatecourinhos.napp.view.cadastros.profissional.CadastroCampoAtuacao;
import com.fatecourinhos.napp.view.cadastros.profissional.CadastroMensagem2;
import com.fatecourinhos.napp.view.consultaraluno.VerDadosAluno;
import com.fatecourinhos.napp.view.listener.OnAlunoInteractionListener;
import com.fatecourinhos.napp.view.listener.OnCampoAtuacaoInteractionListener;

import java.util.List;

public class AlunoFragment extends Fragment {

    //variaveis globais
    private boolean sucesso;
    private String conteudo;
    private List<Aluno> alunos;

    //componentes da tela
    private ProgressBar progressBar;
    private SearchView searchView;

    private OnAlunoInteractionListener listener;
    private ViewHolder viewHolder;
    private AlunoAdapter alunoAdapter;
    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {

        getActivity().setTitle("Alunos");
        view = inflater.inflate(R.layout.fragment_aluno,container,false);
        context = view.getContext();
        viewHolder = new ViewHolder();

        progressBar = view.findViewById(R.id.progressBar_alunos);
        searchView = view.findViewById(R.id.search_view_alunos);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                alunoAdapter.getFilter().filter(s);
                return false;
            }
        });

        viewHolder.recyclerViewAlunos = view.findViewById(R.id.recycler_view_alunos);
        viewHolder.recyclerViewAlunos.setLayoutManager(new LinearLayoutManager(context));

        //seta os eventos da lista
        listener = new OnAlunoInteractionListener() {
            @Override
            public void onListClick(Aluno aluno) {

                VerDadosAluno dialog = new VerDadosAluno();
                Bundle bundle = new Bundle();
                bundle.putInt("idAluno", aluno.getIdAluno());
                dialog.setArguments(bundle);
                dialog.show(getFragmentManager(), "VER DADOS ALUNO");

            }
        };

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        selecionarAlunos();
    }

    private void selecionarAlunos() {

        String uri = "http://vitorsilva.xyz/napp/aluno/selecionarAlunos.php";
        SelecionarAlunos task = new SelecionarAlunos();

        task.execute(uri);

    }

    private class SelecionarAlunos extends AsyncTask<String, String, List<Aluno>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(ProgressBar.VISIBLE);
        }

        @Override
        protected List<Aluno> doInBackground(String... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            alunos = AlunoJSONParser.parseDados(conteudo);
            return alunos;
        }

        @Override
        protected void onPostExecute(final List<Aluno> alunos) {
            super.onPostExecute(alunos);

            alunoAdapter = new AlunoAdapter(alunos, listener);
            viewHolder.recyclerViewAlunos.setAdapter(alunoAdapter);
            progressBar.setVisibility(ProgressBar.INVISIBLE);
        }

    }

    private static class ViewHolder {
        RecyclerView recyclerViewAlunos;
    }

}
