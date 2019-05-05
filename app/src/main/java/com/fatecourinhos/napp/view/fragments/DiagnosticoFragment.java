package com.fatecourinhos.napp.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.DiagnosticoController;
import com.fatecourinhos.napp.controller.ProfissionalController;
import com.fatecourinhos.napp.model.CampoAtuacaoModel;
import com.fatecourinhos.napp.model.DiagnosticoModel;
import com.fatecourinhos.napp.model.LocalAtendimentoModel;
import com.fatecourinhos.napp.model.ProfissionalModel;
import com.fatecourinhos.napp.view.adapter.DiagnosticoAdapter;
import com.fatecourinhos.napp.view.adapter.LocalAtendimentoAdapter;
import com.fatecourinhos.napp.view.adapter.ProfissionalAdapter;
import com.fatecourinhos.napp.view.cadastros.CadastroDiagnostico;
import com.fatecourinhos.napp.view.cadastros.CadastroProfissional;

import java.util.ArrayList;
import java.util.List;

public class DiagnosticoFragment extends Fragment{

    List<DiagnosticoModel> diagnosticos;
    DiagnosticoAdapter adapter;
    RecyclerView diagnosticoRecycler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        diagnosticoRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_diagnostico,container,false);
        diagnosticoRecycler.setLayoutManager(layoutManager);

        return diagnosticoRecycler;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance){

        super.onViewCreated(view, savedInstance);

        getActivity().setTitle("Diagnosticos");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DiagnosticoController diagnosticoController = new DiagnosticoController();

        diagnosticos = diagnosticoController.selecionarDiagnosticos();

        adapter = new DiagnosticoAdapter(diagnosticos);

        diagnosticoRecycler.setAdapter(adapter);

        adapter.setListener(new DiagnosticoAdapter.Listener() {
            @Override
            public void onClick(DiagnosticoModel diagnostico) {

                Bundle data = new Bundle();
                data.putInt("idDiagnostico", diagnostico.getIdDiagostico());
                data.putString("nomeDiagnostico", diagnostico.getNomeDiagnotico());
                data.putString("operação", "alteração");

                CadastroDiagnostico cadastroDiagnostico = new CadastroDiagnostico();
                cadastroDiagnostico.setArguments(data);
                cadastroDiagnostico.show(getFragmentManager(), "DIAGNOSTICO");

            }
        });
    }

}