package com.fatecourinhos.napp.view.fragments.aluno;

import android.content.Context;
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
import com.fatecourinhos.napp.json.MensagemJSONParser;
import com.fatecourinhos.napp.model.Mensagem;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.adapter.aluno.MensagemAlunoAdapter;
import com.fatecourinhos.napp.view.listener.OnMensagemInteractionListener;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class MensagemAlunoFragment extends Fragment {

    //variaveis globais
    private boolean sucesso;
    private String conteudo;
    private List<Mensagem> mensagens;
    private SharedPreferences preferences;
    int id;

    //componentes da tela
    private ProgressBar progressBar;
    private OnMensagemInteractionListener listener;
    private ViewHolder viewHolder;
    private MensagemAlunoAdapter mensagemAlunoAdapter;
    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getActivity().setTitle("Mensagens");
        view = inflater.inflate(R.layout.fragment_mensagens,container,false);
        context = view.getContext();
        viewHolder = new ViewHolder();
        preferences = context.getSharedPreferences("user_settings", MODE_PRIVATE);

        progressBar = view.findViewById(R.id.progressBarMensagem);

        viewHolder.recyclerViewMensagem = view.findViewById(R.id.recycler_view_mensagens);
        viewHolder.recyclerViewMensagem.setLayoutManager(new LinearLayoutManager(context));

        //seta os eventos da lista
        listener = new OnMensagemInteractionListener() {
            @Override
            public void onListClick(Mensagem mensagem) {

            }

            @Override
            public void onDeleteClick(Mensagem mensagem) {

            }
        };

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        selecionarMensagens();
    }

    private void selecionarMensagens() {

        if (preferences.contains("idAluno")) {
            id = preferences.getInt("idAluno", 0);
        }

        String uri = "http://vitorsilva.xyz/napp/mensagem/selecionarMensagens.php";
        SelecionarMensagens task = new SelecionarMensagens();
        RequestHttp requestHttp = new RequestHttp();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);
        System.out.println(id);
        requestHttp.setParametro("idAluno", String.valueOf(id));

        task.execute(uri);

    }

    private class SelecionarMensagens extends AsyncTask<String, String, List<Mensagem>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(ProgressBar.VISIBLE);
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected List<Mensagem> doInBackground(String... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
                System.out.println(conteudo);
            } catch (Exception e) {
                conteudo = null;
            }

            mensagens = MensagemJSONParser.parseDados(conteudo);
            return mensagens;
        }

        @Override
        protected void onPostExecute(List<Mensagem> mensagens) {
            super.onPostExecute(mensagens);

            mensagemAlunoAdapter = new MensagemAlunoAdapter(mensagens, listener);
            viewHolder.recyclerViewMensagem.setAdapter(mensagemAlunoAdapter);
            progressBar.setVisibility(ProgressBar.INVISIBLE);
        }

    }

    private static class ViewHolder {
        RecyclerView recyclerViewMensagem;
    }

}
