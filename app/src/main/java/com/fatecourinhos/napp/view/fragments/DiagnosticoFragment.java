package com.fatecourinhos.napp.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.Diagnostico;
import com.fatecourinhos.napp.view.adapter.DiagnosticoAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroDiagnostico;
import com.fatecourinhos.napp.view.listener.OnDiagnosticoInteractionListener;
import com.fatecourinhos.napp.util.RetrofitClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiagnosticoFragment extends Fragment{

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

        carregarDiagnosticos();

        return view;
    }

    private void carregarDiagnosticos() {

        RetrofitClass retrofit = RetrofitClass.retrofit.create(RetrofitClass.class);
        Call<List<Diagnostico>> call = retrofit.getDiagnosticos();

        call.enqueue(new Callback<List<Diagnostico>>() {
            @Override
            public void onResponse(Call<List<Diagnostico>> call, Response<List<Diagnostico>> response) {
                diagnosticos = response.body();
                diagnosticoAdapter = new DiagnosticoAdapter(diagnosticos, listener);
                System.out.println(diagnosticos.get(1).getNomeDiagnostico());
                viewHolder.recyclerViewDiagnosticos.setAdapter(diagnosticoAdapter);
            }

            @Override
            public void onFailure(Call<List<Diagnostico>> call, Throwable t) {

            }
        });

    }

    private static class ViewHolder {
        RecyclerView recyclerViewDiagnosticos;
    }

}