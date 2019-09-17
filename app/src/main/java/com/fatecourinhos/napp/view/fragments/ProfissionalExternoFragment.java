package com.fatecourinhos.napp.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.ProfissionalExternoJSONParser;
import com.fatecourinhos.napp.model.ProfissionalExterno;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.view.adapter.ProfissionalExternoAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroProfissionalExterno;
import com.fatecourinhos.napp.view.listener.OnProfissionalExternoInteractionListener;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProfissionalExternoFragment extends Fragment{

    private String conteudo;
    private List<ProfissionalExterno> profissionaisExterno;
    private ProfissionalExternoAdapter profissionalExternoAdapter;
    private OnProfissionalExternoInteractionListener listener;
    private ViewHolder viewHolder = new ViewHolder();
    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        getActivity().setTitle("Profissionais Externos");
        view = inflater.inflate(R.layout.fragment_profissional_externo,container,false);
        context = view.getContext();

        viewHolder.recyclerViewProfissionaisExternos = view.findViewById(R.id.recycler_view_profissional_externo);
        viewHolder.recyclerViewProfissionaisExternos.setLayoutManager(new LinearLayoutManager(context));

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
                intent.putExtra("nomeCampoAtuacao", profissionalExterno.getFkCampoAtuacao().getNomeCampoAtuacao());
                intent.putExtra("nomeResponsavel", profissionalExterno.getFkResponsavel().getNomeResponsavel());

                getActivity().startActivity(intent);
            }

            @Override
            public void onDeleteClick(ProfissionalExterno profissionalExterno) {

            }
        };

        selecionarProfissionaisExternos();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        selecionarProfissionaisExternos();
    }

    public void selecionarProfissionaisExternos() {
        String uri = "http://vitorsilva.xyz/napp/profissionalExterno/selecionarProfissionaisExternos.php";
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

    private static class ViewHolder {
        RecyclerView recyclerViewProfissionaisExternos;
    }

}