package com.fatecourinhos.napp.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.ProfissionalExternoModel;
import com.fatecourinhos.napp.model.ResponsavelModel;
import com.fatecourinhos.napp.view.ProfissionalExternoActivity;
import com.fatecourinhos.napp.view.ResponsavelActivity;
import com.fatecourinhos.napp.view.adapter.ResponsavelAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ResponsavelFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){

        RecyclerView responsavelRecycler = (RecyclerView)inflater.inflate(R.layout.responsavel_fragment,container,false);

        final List<ResponsavelModel> responsaveis = new ArrayList<ResponsavelModel>();


        ResponsavelAdapter adapter = new ResponsavelAdapter(responsaveis);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        responsavelRecycler.setLayoutManager(layoutManager);
        responsavelRecycler.setAdapter(adapter);

        adapter.setListener(new ResponsavelAdapter.Listener() {
            @Override
            public void onClick(ResponsavelModel responsavelModel) {
                Intent intent = new Intent(getActivity(), ResponsavelActivity.class);
                intent.putExtra("nomeResponsavel", responsavelModel.getNomeResponsavel());
                getActivity().startActivity(intent);
            }

        });


        return responsavelRecycler;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance){

        super.onViewCreated(view, savedInstance);

        getActivity().setTitle("Profissionais Externos");

    }

}