package com.fatecourinhos.napp.view.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.CampoAtuacaoJSONParser;
import com.fatecourinhos.napp.model.CampoAtuacao;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.view.adapter.CampoAtuacaoAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroCampoAtuacao;
import com.fatecourinhos.napp.view.listener.OnCampoAtuacaoInteractionListener;

import java.util.List;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CampoAtuacaoFragment extends Fragment{

    private String conteudo;
    private List<CampoAtuacao> camposAtuacao;
    private OnCampoAtuacaoInteractionListener listener;
    private ViewHolder viewHolder = new ViewHolder();
    private CampoAtuacaoAdapter campoAtuacaoAdapter;
    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        getActivity().setTitle("Campos de Atuação");
        view = inflater.inflate(R.layout.fragment_area_atuacao,container,false);
        context = view.getContext();

        viewHolder.recyclerViewCampoAtuacao = view.findViewById(R.id.recycler_view_area_atuacao);
        viewHolder.recyclerViewCampoAtuacao.setLayoutManager(new LinearLayoutManager(context));

        //seta os eventos da lista
        listener = new OnCampoAtuacaoInteractionListener() {
            @Override
            public void onListClick(CampoAtuacao campoAtuacao) {

                Bundle data = new Bundle();
                data.putInt("idCampoAtuacao", campoAtuacao.getIdCampoAtuacao());
                data.putString("nomeCampoAtuacao", campoAtuacao.getNomeCampoAtuacao());
                data.putString("operação", "alteração");

                CadastroCampoAtuacao cadastroCampoAtuacao = new CadastroCampoAtuacao();
                cadastroCampoAtuacao.setArguments(data);
                cadastroCampoAtuacao.show(getFragmentManager(), "CAMPOATUACAO");
            }

            @Override
            public void onDeleteClick(CampoAtuacao campoAtuacao) {

            }
        };

        selecionarCamposAtuacao();
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        selecionarCamposAtuacao();
    }

    public void selecionarCamposAtuacao() {
        String uri = "http://vitorsilva.xyz/napp/campoAtuacao/selecionarCamposAtuacao.php";
        SelecionarCamposAtuacao mytask = new SelecionarCamposAtuacao();
        mytask.execute(uri);
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
        protected void onPostExecute(final List<CampoAtuacao> camposAtuacao) {
            super.onPostExecute(camposAtuacao);

            campoAtuacaoAdapter = new CampoAtuacaoAdapter(camposAtuacao, listener);
            viewHolder.recyclerViewCampoAtuacao.setAdapter(campoAtuacaoAdapter);
        }
    }

    private static class ViewHolder {
        RecyclerView recyclerViewCampoAtuacao;
    }

}