package com.fatecourinhos.napp.view.cadastros;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.CampoAtuacaoController;
import com.fatecourinhos.napp.controller.ProfissionalController;
import com.fatecourinhos.napp.controller.ProfissionalExternoController;
import com.fatecourinhos.napp.controller.ResponsavelController;
import com.fatecourinhos.napp.model.CampoAtuacaoModel;
import com.fatecourinhos.napp.model.ProfissionalExternoModel;
import com.fatecourinhos.napp.model.ResponsavelModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import java.util.ArrayList;
import java.util.List;

public class CadastroProfissionalExterno extends AppCompatActivity {

    final ProfissionalExternoModel profissionalExterno = new ProfissionalExternoModel();
    final CampoAtuacaoModel campoAtuacaoModel = new CampoAtuacaoModel();
    final ResponsavelModel responsavelModel = new ResponsavelModel();

    private AppCompatEditText editTextNomeProfissionalExterno, editTextTelefoneProfissionalExterno, editTextCelularProfissionalExterno,
    editTextBairro, editTextNumero, editTextCidadeProfissionalExterno, editTextEndereco, editTextEmailProfissionalExterno;

    private Spinner spinnerTipo, spinnerResponsavel;

    private Button btn_cadastrar_responsavel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_activity_profissional_externo);

        editTextBairro = (AppCompatEditText) findViewById(R.id.edit_text_bairro_profissional_externo);
        editTextCelularProfissionalExterno = (AppCompatEditText) findViewById(R.id.edit_text_celular_profissional_externo);
        editTextCidadeProfissionalExterno = (AppCompatEditText) findViewById(R.id.edit_text_cidade_profissional_externo);
        editTextEmailProfissionalExterno = (AppCompatEditText) findViewById(R.id.edit_text_email_profissional_externo);
        editTextEndereco = (AppCompatEditText) findViewById(R.id.edit_text_endereco_profissional_externo);
        editTextTelefoneProfissionalExterno = (AppCompatEditText) findViewById(R.id.edit_text_telefone_profissional_externo);
        editTextNomeProfissionalExterno = (AppCompatEditText) findViewById(R.id.edit_text_nome_profissional_externo);
        editTextNumero = (AppCompatEditText) findViewById(R.id.edit_text_numero_profissional_externo);

        final List<CampoAtuacaoModel> camposAtuacao = CampoAtuacaoController.selecionarCamposAtuacao();
        final List<ResponsavelModel> responsaveis = ResponsavelController.selecionarResponsaveis();

        List<String> nomesCampos = new ArrayList<String>();
        List<String> nomesResponsaveis = new ArrayList<String>();

        for ( CampoAtuacaoModel campoAtuacaoModel: camposAtuacao)
            nomesCampos.add(campoAtuacaoModel.getNomeCampoAtuacao());

        for ( ResponsavelModel responsavel: responsaveis)
            nomesResponsaveis.add(responsavel.getNomeResponsavel());

        spinnerTipo = (Spinner) findViewById(R.id.spinnerTipo);
        ArrayAdapter<String> adapterTipo =  new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nomesCampos);
        spinnerTipo.setAdapter(adapterTipo);

        spinnerResponsavel = (Spinner) findViewById(R.id.spinnerResponsavel);
        ArrayAdapter<String> adapterResponsavel =  new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nomesResponsaveis);
        spinnerResponsavel.setAdapter(adapterResponsavel);

        btn_cadastrar_responsavel = (Button) findViewById(R.id.btn_salvar_responsavel);

        if(getIntent().getExtras() != null) {

            profissionalExterno.setEndereco(getIntent().getExtras().getString("endereco"));
            profissionalExterno.setBairro(getIntent().getExtras().getString("bairro"));
            profissionalExterno.setCelularProfissionalExterno(getIntent().getExtras().getString("celularProfissionalExterno"));
            profissionalExterno.setNumero(getIntent().getExtras().getString("numero"));
            profissionalExterno.setTelefoneProfissionalExterno(getIntent().getExtras().getString("telefoneProfissionalExterno"));
            profissionalExterno.setEmailProfissionalExterno(getIntent().getExtras().getString("emailProfissionalExterno"));
            profissionalExterno.setCidadeProfissionalExterno(getIntent().getExtras().getString("cidadeProfissionalExterno"));
            profissionalExterno.setIdProfissionalExterno(getIntent().getExtras().getInt("idProfissionalExterno"));
            profissionalExterno.setNomeProfissionalExterno(getIntent().getExtras().getString("nomeProfissionalExternos"));

            campoAtuacaoModel.setIdCampoAtuacao(getIntent().getExtras().getInt("idCampoAtuacao"));
            campoAtuacaoModel.setNomeCampoAtuacao(getIntent().getExtras().getString("nomeCampoAtuacao"));

            responsavelModel.setIdResponsavel(getIntent().getExtras().getInt("idResponsavel"));
            responsavelModel.setCelularResponsavel(getIntent().getExtras().getString("celularResponsavel"));
            responsavelModel.setTelefoneResponsavel(getIntent().getExtras().getString("telefoneResponsavel"));
            responsavelModel.setEmailResponsavel(getIntent().getExtras().getString("emailResponsavel"));
            responsavelModel.setNomeResponsavel(getIntent().getExtras().getString("nomeResponsavel"));

            profissionalExterno.setCampoAtuacao(campoAtuacaoModel);
            profissionalExterno.setFkResponsavel(responsavelModel);

            editTextNumero.setText(profissionalExterno.getNumero());
            editTextNomeProfissionalExterno.setText(profissionalExterno.getNomeProfissionalExterno());
            editTextTelefoneProfissionalExterno.setText(profissionalExterno.getTelefoneProfissionalExterno());
            editTextEndereco.setText(profissionalExterno.getEndereco());
            editTextBairro.setText(profissionalExterno.getBairro());
            editTextCidadeProfissionalExterno.setText(profissionalExterno.getCidadeProfissionalExterno());
            editTextCelularProfissionalExterno.setText(profissionalExterno.getCelularProfissionalExterno());
            editTextEmailProfissionalExterno.setText(profissionalExterno.getEmailProfissionalExterno());

            for (int i = 0; i < responsaveis.size(); i++) {

                for (ResponsavelModel responsavel : responsaveis) {

                    if (responsavel.getNomeResponsavel().equals(responsavelModel.getNomeResponsavel())) {
                        spinnerResponsavel.setSelection(i);
                    }

                }

            }

            for (int i = 0; i < camposAtuacao.size(); i++) {

                for (CampoAtuacaoModel campoAtuacao : camposAtuacao) {

                    if (campoAtuacao.getNomeCampoAtuacao().equals(campoAtuacaoModel.getNomeCampoAtuacao())) {
                        spinnerTipo.setSelection(i);
                    }

                }

            }

            btn_cadastrar_responsavel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enviarDados(false, camposAtuacao, responsaveis);
                }
            });

        }else{

            btn_cadastrar_responsavel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enviarDados(true, camposAtuacao, responsaveis);
                }
            });

        }

    }

    private void enviarDados(boolean inserir, List<CampoAtuacaoModel> camposAtuacao, List<ResponsavelModel> responsaveis){

        profissionalExterno.setEndereco(editTextEndereco.getText().toString());
        profissionalExterno.setBairro(editTextBairro.getText().toString());
        profissionalExterno.setCelularProfissionalExterno(editTextCelularProfissionalExterno.getText().toString());
        profissionalExterno.setNumero(editTextNumero.getText().toString());
        profissionalExterno.setTelefoneProfissionalExterno(editTextTelefoneProfissionalExterno.getText().toString());
        profissionalExterno.setEmailProfissionalExterno(editTextEmailProfissionalExterno.getText().toString());
        profissionalExterno.setCidadeProfissionalExterno(editTextCidadeProfissionalExterno.getText().toString());
        profissionalExterno.setNomeProfissionalExterno(editTextNomeProfissionalExterno.getText().toString());

        profissionalExterno.setFkResponsavel(null);
        profissionalExterno.setCampoAtuacao(null);

        for (CampoAtuacaoModel campoAtuacao : camposAtuacao) {

            if (spinnerTipo.getSelectedItem().equals(campoAtuacao.getNomeCampoAtuacao())) {
                profissionalExterno.setCampoAtuacao(campoAtuacao);
            }

        }

        for( ResponsavelModel responsavel : responsaveis){

            if(spinnerResponsavel.getSelectedItem().equals(responsavel.getNomeResponsavel())){
                profissionalExterno.setFkResponsavel(responsavel);
            }

        }

        if(conferirDados(profissionalExterno)){

            if(inserir == true){

                if(ProfissionalExternoController.inserirProfissionalExterno(profissionalExterno)) {
                    Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_LONG);
                    limparDados();
                }else
                    Toast.makeText(this, "Erro ao inserir", Toast.LENGTH_LONG);

            }else{

                if(ProfissionalExternoController.alterarProfissionalExterno(profissionalExterno)){
                    Toast.makeText(this, "Alterado com sucesso", Toast.LENGTH_LONG);
                    limparDados();
                }else
                    Toast.makeText(this, "Erro ao alterar", Toast.LENGTH_LONG);
            }

        }else
            Toast.makeText(this, "Insira todos os campos corretamente!", Toast.LENGTH_LONG);

    }

    private void limparDados(){

    }

    private boolean conferirDados(ProfissionalExternoModel profissionalExternoModel){
        boolean retorno = true;


        return retorno;
    }

}
