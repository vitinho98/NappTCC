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
import com.fatecourinhos.napp.model.RelatorioFeedback;

import java.util.List;

public class RelatorioFeedbackListAdapter extends ArrayAdapter<RelatorioFeedback> {

    private Context mContext;
    int mResource;

    public RelatorioFeedbackListAdapter(@NonNull Context context, int resource, @NonNull List<RelatorioFeedback> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String data = getItem(position).getData();
        String nomeAluno = getItem(position).getNomeAluno();
        String opcao1 = getItem(position).getOpcao1();
        String opcao2 = getItem(position).getOpcao2();

        LayoutInflater inflater = LayoutInflater.from(mContext);

        convertView = inflater.inflate(mResource, parent, false);

        TextView txtData = convertView.findViewById(R.id.txtDataFeedback);
        TextView txtNomeAluno = convertView.findViewById(R.id.txtRelatorioNomeFeedback);
        TextView txtOpcao1 = convertView.findViewById(R.id.txtRelatorioOpcao1Feedback);
        TextView txtOpcao2 = convertView.findViewById(R.id.txtRelatorioOpcao2Feedback);

        txtData.setText(data);
        txtNomeAluno.setText(nomeAluno);
        txtOpcao1.setText(opcao1);
        txtOpcao2.setText(opcao2);

        return convertView;
    }
}
