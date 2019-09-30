package com.fatecourinhos.napp.view.cadastros;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.json.CampoAtuacaoJSONParser;
import com.fatecourinhos.napp.json.ResponsavelJSONParser;
import com.fatecourinhos.napp.model.CampoAtuacao;
import com.fatecourinhos.napp.model.ProfissionalExterno;
import com.fatecourinhos.napp.model.Responsavel;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import java.util.ArrayList;
import java.util.List;

public class CadastroProfissionalExterno extends AppCompatActivity {

    //variaveis globais
    private ProfissionalExterno profissionalExterno = new ProfissionalExterno();
    private CampoAtuacao campoAtuacaoCA = new CampoAtuacao();
    private Responsavel responsavelR = new Responsavel();

    private ArrayAdapter<String> adapterResponsavel;
    private ArrayAdapter<String> adapterCampoAtuacao;

    private List<CampoAtuacao> camposAtuacao = new ArrayList<>();
    private List<Responsavel> responsaveis = new ArrayList<>();

    private List<String> nomesCampos = new ArrayList<>();
    private List<String> nomesResponsaveis = new ArrayList<>();

    private boolean sucesso;
    private String conteudo;

    //componentes da tela
    private ProgressBar progressBar;
    private AppCompatEditText editTextNomeProfissionalExterno, editTextTelefoneProfissionalExterno, editTextCelularProfissionalExterno,
    editTextBairro, editTextNumero, editTextCidadeProfissionalExterno, editTextEndereco, editTextEmailProfissionalExterno;
    private Spinner spinnerCampoAtuacao, spinnerResponsavel;
    private Button btnCadastrarResponsavel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_activity_profissional_externo);

        getComponentes();
        carregarSpinners();

        if (getIntent().getExtras() != null) {

            editTextNumero.setText(getIntent().getExtras().getString("numero"));
            editTextNomeProfissionalExterno.setText(getIntent().getExtras().getString("nomeProfissionalExterno"));
            editTextTelefoneProfissionalExterno.setText(getIntent().getExtras().getString("telefoneProfissionalExterno"));
            editTextEndereco.setText(getIntent().getExtras().getString("endereco"));
            editTextBairro.setText(getIntent().getExtras().getString("bairro"));
            editTextCidadeProfissionalExterno.setText(getIntent().getExtras().getString("cidadeProfissionalExterno"));
            editTextCelularProfissionalExterno.setText(getIntent().getExtras().getString("celularProfissionalExterno"));
            editTextEmailProfissionalExterno.setText(getIntent().getExtras().getString("emailProfissionalExterno"));

            profissionalExterno.setIdProfissionalExterno(getIntent().getExtras().getInt("idProfissionalExterno"));

            btnCadastrarResponsavel.setText(R.string.btn_salvar);
            btnCadastrarResponsavel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enviarDados(false);
                }
            });

        } else {

            btnCadastrarResponsavel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enviarDados(true);
                }
            });
        }

    }

    //pega os componentes da tela
    private void getComponentes() {

        editTextBairro = findViewById(R.id.edit_text_bairro_profissional_externo);
        editTextCelularProfissionalExterno = findViewById(R.id.edit_text_celular_profissional_externo);
        editTextCidadeProfissionalExterno = findViewById(R.id.edit_text_cidade_profissional_externo);
        editTextEmailProfissionalExterno = findViewById(R.id.edit_text_email_profissional_externo);
        editTextEndereco = findViewById(R.id.edit_text_endereco_profissional_externo);
        editTextTelefoneProfissionalExterno = findViewById(R.id.edit_text_telefone_profissional_externo);
        editTextNomeProfissionalExterno = findViewById(R.id.edit_text_nome_profissional_externo);
        editTextNumero = findViewById(R.id.edit_text_numero_profissional_externo);

        spinnerResponsavel = findViewById(R.id.spinnerResponsavel);
        spinnerCampoAtuacao = findViewById(R.id.spinnerCampoAtuacao);
        btnCadastrarResponsavel = findViewById(R.id.btn_salvar_profissional_externo);
        progressBar = findViewById(R.id.progressBarCadResponsavel);

        SimpleMaskFormatter maskCelular = new SimpleMaskFormatter("(NN) NNNNN-NNNN");
        SimpleMaskFormatter maskTelefone = new SimpleMaskFormatter("(NN) NNNN-NNNN");

        MaskTextWatcher mtwCelular = new MaskTextWatcher(editTextCelularProfissionalExterno, maskCelular);
        MaskTextWatcher mtwTelefone = new MaskTextWatcher(editTextTelefoneProfissionalExterno, maskTelefone);

        editTextCelularProfissionalExterno.addTextChangedListener(mtwCelular);
        editTextTelefoneProfissionalExterno.addTextChangedListener(mtwTelefone);

    }

    //carrega os sppiners com os dados do banco
    private void carregarSpinners() {

        progressBar.setVisibility(ProgressBar.VISIBLE);

        nomesCampos.add("Nenhum");
        nomesResponsaveis.add("Nenhum");

        selecionarCamposAtuacao();

    }

    private void selecionarCamposAtuacao() {

        String uri = "http://vitorsilva.xyz/napp/campoAtuacao/selecionarCamposAtuacao.php";
        SelecionarCamposAtuacao task = new SelecionarCamposAtuacao();

        task.execute(uri);

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
        protected void onPostExecute(List<CampoAtuacao> camposAtuacao) {
            super.onPostExecute(camposAtuacao);

            for (CampoAtuacao campoAtuacao : camposAtuacao)
                nomesCampos.add(campoAtuacao.getNomeCampoAtuacao());

            adapterCampoAtuacao = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, nomesCampos);
            spinnerCampoAtuacao.setAdapter(adapterCampoAtuacao);

            selecionarResponsaveis();
        }

    }

    private void selecionarResponsaveis() {

        String uri = "http://vitorsilva.xyz/napp/responsavel/selecionarResponsaveis.php";
        SelecionarResponsaveis task = new SelecionarResponsaveis();

        task.execute(uri);

    }

    private class SelecionarResponsaveis extends AsyncTask<String, String, List<Responsavel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Responsavel> doInBackground(String... params) {

            try {
                conteudo = HttpManager.getDados(params[0]);
            } catch (Exception e) {
                conteudo = null;
            }

            responsaveis = ResponsavelJSONParser.parseDados(conteudo);
            return responsaveis;
        }

        @Override
        protected void onPostExecute(List<Responsavel> responsaveis) {
            super.onPostExecute(responsaveis);

            for (Responsavel responsavel : responsaveis)
                nomesResponsaveis.add(responsavel.getNomeResponsavel());

            adapterResponsavel =  new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, nomesResponsaveis);
            spinnerResponsavel.setAdapter(adapterResponsavel);

            //------------------------------------------------------------------------------------//

            if (getIntent().getExtras() != null) {

                if (getIntent().getExtras().get("idC") != null) {

                    campoAtuacaoCA.setIdCampoAtuacao(getIntent().getExtras().getInt("idC"));
                    campoAtuacaoCA.setNomeCampoAtuacao(getIntent().getExtras().getString("nomeC"));
                    profissionalExterno.setFkCampoAtuacao(campoAtuacaoCA);

                    System.out.println(campoAtuacaoCA.getIdCampoAtuacao() + " " + campoAtuacaoCA.getNomeCampoAtuacao());
                    for (CampoAtuacao campoAtuacao : camposAtuacao)
                        if (campoAtuacao.getNomeCampoAtuacao().equals(campoAtuacaoCA.getNomeCampoAtuacao()))
                            spinnerCampoAtuacao.setSelection(adapterCampoAtuacao.getPosition(campoAtuacaoCA.getNomeCampoAtuacao()));

                } else
                    spinnerCampoAtuacao.setSelection(0);

                if (getIntent().getExtras().get("idResponsavel") != null) {

                    responsavelR.setIdResponsavel(getIntent().getExtras().getInt("idResponsavel"));
                    responsavelR.setNomeResponsavel(getIntent().getExtras().getString("nomeResponsavel"));
                    profissionalExterno.setFkResponsavel(responsavelR);

                    for (Responsavel responsavel : responsaveis)
                        if (responsavel.getNomeResponsavel().equals(responsavelR.getNomeResponsavel()))
                            spinnerResponsavel.setSelection(adapterResponsavel.getPosition(responsavelR.getNomeResponsavel()));

                } else
                    spinnerResponsavel.setSelection(0);

            } else {

                spinnerCampoAtuacao.setSelection(0);
                spinnerResponsavel.setSelection(0);

            }

            progressBar.setVisibility(ProgressBar.INVISIBLE);

        }

    }

    private boolean conferirDados(ProfissionalExterno profissionalExterno){

        if (profissionalExterno.getNomeProfissionalExterno().isEmpty())
            return false;
        else
            return true;

    }

    private void enviarDados(boolean inserir) {

        profissionalExterno.setEndereco(editTextEndereco.getText().toString());
        profissionalExterno.setBairro(editTextBairro.getText().toString());
        profissionalExterno.setCelularProfissionalExterno(editTextCelularProfissionalExterno.getText().toString());
        profissionalExterno.setNumero(editTextNumero.getText().toString());
        profissionalExterno.setTelefoneProfissionalExterno(editTextTelefoneProfissionalExterno.getText().toString());
        profissionalExterno.setEmailProfissionalExterno(editTextEmailProfissionalExterno.getText().toString());
        profissionalExterno.setCidadeProfissionalExterno(editTextCidadeProfissionalExterno.getText().toString());
        profissionalExterno.setNomeProfissionalExterno(editTextNomeProfissionalExterno.getText().toString());

        profissionalExterno.setFkResponsavel(null);
        profissionalExterno.setFkCampoAtuacao(null);

        for (CampoAtuacao campoAtuacao : camposAtuacao)
            if (spinnerCampoAtuacao.getSelectedItem().equals(campoAtuacao.getNomeCampoAtuacao()))
                profissionalExterno.setFkCampoAtuacao(campoAtuacao);

        for (Responsavel responsavel : responsaveis)
            if (spinnerResponsavel.getSelectedItem().equals(responsavel.getNomeResponsavel()))
                profissionalExterno.setFkResponsavel(responsavel);

        if (conferirDados(profissionalExterno)) {

            if (inserir)
                inserirProfissionalExterno(profissionalExterno);
            else
                alterarProfissionalExterno(profissionalExterno);

        } else
            Toast.makeText(this, "Insira todos os campos corretamente!", Toast.LENGTH_LONG);

    }

    public void inserirProfissionalExterno(ProfissionalExterno profissionalExterno) {

        String uri = "http://vitorsilva.xyz/napp/profissionalExterno/inserirProfExterno.php";
        CadastrarProfissionalExterno task = new CadastrarProfissionalExterno();
        RequestHttp requestHttp = new RequestHttp();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("nomeProfissionalExterno", profissionalExterno.getNomeProfissionalExterno());
        requestHttp.setParametro("bairroProfissionalExterno", profissionalExterno.getBairro());
        requestHttp.setParametro("celularProfissionalExterno", profissionalExterno.getCelularProfissionalExterno());
        requestHttp.setParametro("telefoneProfissionalExterno", profissionalExterno.getTelefoneProfissionalExterno());
        requestHttp.setParametro("cidadeProfissionalExterno", profissionalExterno.getCidadeProfissionalExterno());
        requestHttp.setParametro("enderecoProfissionalExterno", profissionalExterno.getEndereco());
        requestHttp.setParametro("emailProfissionalExterno", profissionalExterno.getEmailProfissionalExterno());
        requestHttp.setParametro("numeroProfissionalExterno", profissionalExterno.getNumero());

        if (profissionalExterno.getFkCampoAtuacao() != null)
            requestHttp.setParametro("fkCampoAtuacao", String.valueOf(profissionalExterno.getFkCampoAtuacao().getIdCampoAtuacao()));
        else
            requestHttp.setParametro("fkCampoAtuacao", null);

        if (profissionalExterno.getFkResponsavel() != null)
            requestHttp.setParametro("fkResponsavel", String.valueOf(profissionalExterno.getFkResponsavel().getIdResponsavel()));
        else
            requestHttp.setParametro("fkCampoAtuacao", null);

        task.execute(requestHttp);

    }

    public void alterarProfissionalExterno(ProfissionalExterno profissionalExterno) {

        String uri = "http://vitorsilva.xyz/napp/profissionalExterno/alterarProfExterno.php";
        AlterarProfissionalExterno task = new AlterarProfissionalExterno();
        RequestHttp requestHttp = new RequestHttp();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("idProfissionalExterno", String.valueOf(profissionalExterno.getIdProfissionalExterno()));
        requestHttp.setParametro("nomeProfissionalExterno", profissionalExterno.getNomeProfissionalExterno());
        requestHttp.setParametro("bairroProfissionalExterno", profissionalExterno.getBairro());
        requestHttp.setParametro("celularProfissionalExterno", profissionalExterno.getCelularProfissionalExterno());
        requestHttp.setParametro("telefoneProfissionalExterno", profissionalExterno.getTelefoneProfissionalExterno());
        requestHttp.setParametro("cidadeProfissionalExterno", profissionalExterno.getCidadeProfissionalExterno());
        requestHttp.setParametro("idResponsavel", String.valueOf(profissionalExterno.getFkResponsavel().getIdResponsavel()));
        requestHttp.setParametro("enderecoProfissionalExterno", profissionalExterno.getEndereco());
        requestHttp.setParametro("emailProfissionalExterno", profissionalExterno.getEmailProfissionalExterno());
        requestHttp.setParametro("numeroProfissionalExterno", profissionalExterno.getNumero());
        requestHttp.setParametro("idCampoAtuacao", String.valueOf(profissionalExterno.getFkCampoAtuacao().getIdCampoAtuacao()));

        task.execute(requestHttp);

    }

    private class CadastrarProfissionalExterno extends AsyncTask<RequestHttp, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {

            try {

                conteudo = HttpManager.getDados(params[0]);

                if (conteudo.contains("Sucesso"))
                    sucesso = true;
                else
                    sucesso = false;

            } catch (Exception e) {
                conteudo = null;
            }

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (sucesso) {
                Toast.makeText(getApplicationContext(), "Inserido com sucesso", Toast.LENGTH_SHORT).show();
                finish();
            } else
                Toast.makeText(getApplicationContext(), "Erro ao inserir", Toast.LENGTH_SHORT).show();
        }

    }

    private class AlterarProfissionalExterno extends AsyncTask<RequestHttp, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {

            try {

                conteudo = HttpManager.getDados(params[0]);

                if (conteudo.equals("Sucesso"))
                    sucesso = true;
                else
                    sucesso = false;

            } catch (Exception e) {
                conteudo = null;
            }

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (sucesso) {
                Toast.makeText(getApplicationContext(), "Alterado com sucesso", Toast.LENGTH_SHORT).show();
                finish();
            } else
                Toast.makeText(getApplicationContext(), "Erro ao alterar", Toast.LENGTH_SHORT).show();
        }

    }

}
