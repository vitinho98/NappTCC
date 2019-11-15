package com.fatecourinhos.napp.view.adapter.profissional;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.model.RelatorioAluno;

import java.util.List;

public class RelatorioAlunoListAdapter extends ArrayAdapter<RelatorioAluno> {

    private Context mContext;
    int mResource;

    public RelatorioAlunoListAdapter(@NonNull Context context, int resource, @NonNull List<RelatorioAluno> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String nomeAluno = getItem(position).getNomeAluno();
        int quantidade = getItem(position).getQuantidade();

        LayoutInflater inflater = LayoutInflater.from(mContext);

        convertView = inflater.inflate(mResource, parent, false);

        TextView txtNomeAluno = convertView.findViewById(R.id.txtRelatorioNomeAluno);
        TextView txtQuantidadeAluno = convertView.findViewById(R.id.txtRelatorioQuantidadeAluno);

        txtNomeAluno.setText(nomeAluno);
        txtQuantidadeAluno.setText(String.valueOf(quantidade));

        return convertView;
    }
}
