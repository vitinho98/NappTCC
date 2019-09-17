package com.fatecourinhos.napp.view.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.DiagnosticoJSONParser;
import com.fatecourinhos.napp.model.Diagnostico;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.view.adapter.DiagnosticoAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroDiagnostico;
import com.fatecourinhos.napp.view.listener.OnDiagnosticoInteractionListener;

import java.util.List;

public class DiagnosticoFragment extends Fragment{

    private String conteudo;
    private List<Diagnostico> diagnosticos;
    private ViewHolder viewHolder = new ViewHolder();
    private OnDiagnosticoInteractionListener listener;
    private DiagnosticoAdapter diagnosticoAdapter;
    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        getActivity().setTitle("Diagnosticos");
        view = inflater.inflate(R.layout.fragment_diagnostico,container,false);
        context = view.getContext();

        viewHolder.recyclerViewDiagnosticos = view.findViewById(R.id.recycler_view_diagnostico);
        viewHolder.recyclerViewDiagnosticos.setLayoutManager(new LinearLayoutManager(context));

        listener = new OnDiagnosticoInteractionListener() {
            @Override
            public void onListClick(Diagnostico diagnostico) {

                Bundle data = new Bundle();
                data.putInt("idDiagnostico", diagnostico.getIdDiagnostico());
                data.putString("nomeDiagnostico", diagnostico.getNomeDiagnostico());
                data.putString("operação", "alteração");

                CadastroDiagnostico cadastroDiagnostico = new CadastroDiagnostico();
                cadastroDiagnostico.setArguments(data);
                cadastroDiagnostico.show(getFragmentManager(), "DIAGNOSTICO");

            }

            @Override
            public void onDeleteClick(Diagnostico diagnostico) {

            }
        };

        selecionarDiagnosticos();
        return view;
    }

    public void selecionarDiagnosticos(){
        String uri = "http://vitorsilva.xyz/napp/diagnostico/selecionarDiagnosticos.php";
        SelecionarDiagnosticos mytask = new SelecionarDiagnosticos();
        mytask.execute(uri);
    }

    private class SelecionarDiagnosticos extends AsyncTask<String, String, List<Diagnostico>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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
        protected void onPostExecute(final List<Diagnostico> diagnosticos) {
            super.onPostExecute(diagnosticos);

            diagnosticoAdapter = new DiagnosticoAdapter(diagnosticos, listener);
            viewHolder.recyclerViewDiagnosticos.setAdapter(diagnosticoAdapter);
        }
    }

    private static class ViewHolder {
        RecyclerView recyclerViewDiagnosticos;
    }

}