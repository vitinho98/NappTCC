package com.fatecourinhos.napp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.fatecourinhos.napp.model.RelatorioAluno;
import com.fatecourinhos.napp.model.RelatorioFeedback;
import com.fatecourinhos.napp.model.RelatorioProfissional;
import com.fatecourinhos.napp.view.adapter.profissional.RelatorioAlunoListAdapter;
import com.fatecourinhos.napp.view.adapter.profissional.RelatorioFeedbackListAdapter;
import com.fatecourinhos.napp.view.adapter.profissional.RelatorioProfissionalListAdapter;

import java.util.ArrayList;

public class Relatorio extends AppCompatActivity {

    TextView txtRelatorioTitulo, txtNome, txtQuantidade, txtOpcao1;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        txtRelatorioTitulo = findViewById(R.id.txtRelatorioTitulo);
        txtNome = findViewById(R.id.txtNome);
        txtQuantidade = findViewById(R.id.txtQuantidade);
        txtOpcao1 = findViewById(R.id.txtOpcao1);

        lista = findViewById(R.id.listaRelatorio);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){

            switch (bundle.getInt("tipo")){
                case 1:

                    txtRelatorioTitulo.setText("Relatório por Profissional");
                    txtNome.setText("Nome Profissional");

                    RelatorioProfissional relatorioProfissional = new RelatorioProfissional("Eunice", 10);
                    RelatorioProfissional relatorioProfissional1 = new RelatorioProfissional("Rose", 11);
                    RelatorioProfissional relatorioProfissional2 = new RelatorioProfissional("teste", 12);

                    ArrayList<RelatorioProfissional> listaProfissional = new ArrayList<>();

                    listaProfissional.add(relatorioProfissional);
                    listaProfissional.add(relatorioProfissional1);
                    listaProfissional.add(relatorioProfissional2);

                    RelatorioProfissionalListAdapter adapter = new RelatorioProfissionalListAdapter(this, R.layout.adapter_relatorio_profissional, listaProfissional);
                    lista.setAdapter(adapter);

                    break;
                case 2:

                    txtNome.setText("Nome Aluno");

                    RelatorioAluno relatorioAluno = new RelatorioAluno("wilson", 1);
                    RelatorioAluno relatorioAluno1 = new RelatorioAluno("vitor", 2);

                    ArrayList<RelatorioAluno> listaAluno = new ArrayList<>();

                    listaAluno.add(relatorioAluno);
                    listaAluno.add(relatorioAluno1);

                    RelatorioAlunoListAdapter adapter2 = new RelatorioAlunoListAdapter(this, R.layout.adapter_relatorio_aluno, listaAluno);

                    lista.setAdapter(adapter2);

                    txtRelatorioTitulo.setText("Relatório por Aluno");

                    break;
                case 3:

                    txtNome.setText("Nome Aluno");
                    txtOpcao1.setText("Pergunta1");
                    txtQuantidade.setText("Pergunta2");

                    RelatorioFeedback relatorioFeedback = new RelatorioFeedback("wilson", "sim", "não");
                    RelatorioFeedback relatorioFeedback1 = new RelatorioFeedback("wilson", "sim", "não");

                    ArrayList<RelatorioFeedback> listaFeedback = new ArrayList<>();

                    listaFeedback.add(relatorioFeedback);
                    listaFeedback.add(relatorioFeedback1);

                    RelatorioFeedbackListAdapter adapter3 = new RelatorioFeedbackListAdapter(this, R.layout.adapter_relatorio_feedback, listaFeedback);

                    lista.setAdapter(adapter3);

                    txtRelatorioTitulo.setText("Relatório por Feedback");

                    break;
            }

        }
    }
}
