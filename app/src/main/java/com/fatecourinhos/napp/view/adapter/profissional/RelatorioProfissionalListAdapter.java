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
import com.fatecourinhos.napp.model.RelatorioProfissional;

import java.util.List;

public class RelatorioProfissionalListAdapter extends ArrayAdapter<RelatorioProfissional> {

    private Context mContext;
    int mResource;

    public RelatorioProfissionalListAdapter(@NonNull Context context, int resource, @NonNull List<RelatorioProfissional> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String nomeProfissional = getItem(position).getNomeProfissional();
        int quantidade = getItem(position).getQuantidade();

        LayoutInflater inflater = LayoutInflater.from(mContext);

        convertView = inflater.inflate(mResource, parent, false);

        TextView txtNomeProfissional = convertView.findViewById(R.id.txtRelatorioNomeProfissional);
        TextView txtQuantidadeProfissional = convertView.findViewById(R.id.txtRelatorioQuantidadeProfissional);

        txtNomeProfissional.setText(nomeProfissional);
        txtQuantidadeProfissional.setText(String.valueOf(quantidade));

        return convertView;
    }
}
