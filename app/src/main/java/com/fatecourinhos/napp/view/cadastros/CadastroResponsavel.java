package com.fatecourinhos.napp.view.cadastros;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.controller.ResponsavelController;
import com.fatecourinhos.napp.model.ResponsavelModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

public class CadastroResponsavel extends AppCompatActivity {

    private AppCompatEditText editTextNomeResponsavel, editTextEmailResponsavel, editTextCelularResponsavel, editTextTelefoneResponsavel;
    private Button btn_cadastrar_responsavel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_activity_responsavel);

        editTextCelularResponsavel = (AppCompatEditText)findViewById(R.id.edit_text_celular_responsavel);
        editTextEmailResponsavel = (AppCompatEditText) findViewById(R.id.edit_text_email_responsavel);
        editTextNomeResponsavel = (AppCompatEditText) findViewById(R.id.edit_text_nome_responsavel);
        editTextTelefoneResponsavel = (AppCompatEditText) findViewById(R.id.edit_text_telefone_responsavel);

        btn_cadastrar_responsavel = (Button) findViewById(R.id.btn_salvar_responsavel);

        if(getIntent().getExtras() != null){

            final ResponsavelModel responsavel = new ResponsavelModel();

            responsavel.setIdResponsavel(getIntent().getExtras().getInt("idResponsavel"));
            responsavel.setCelularResponsavel(getIntent().getExtras().getString("celularResponsavel"));
            responsavel.setEmailResponsavel(getIntent().getExtras().getString("emailResponsavel"));
            responsavel.setNomeResponsavel(getIntent().getExtras().getString("nomeResponsavel"));
            responsavel.setTelefoneResponsavel(getIntent().getExtras().getString("telefoneResponsavel"));

            editTextTelefoneResponsavel.setText(responsavel.getTelefoneResponsavel());
            editTextNomeResponsavel.setText(responsavel.getNomeResponsavel());
            editTextCelularResponsavel.setText(responsavel.getCelularResponsavel());
            editTextEmailResponsavel.setText(responsavel.getEmailResponsavel());
            btn_cadastrar_responsavel.setText(R.string.btn_salvar);

            btn_cadastrar_responsavel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(conferirDados()){

                        responsavel.setTelefoneResponsavel(editTextTelefoneResponsavel.getText().toString());
                        responsavel.setNomeResponsavel(editTextNomeResponsavel.getText().toString());
                        responsavel.setEmailResponsavel(editTextEmailResponsavel.getText().toString());
                        responsavel.setCelularResponsavel(editTextCelularResponsavel.getText().toString());

                        if(ResponsavelController.alterarResponsavel(responsavel)){

                            Toast.makeText(CadastroResponsavel.this,"Alterado com sucesso", Toast.LENGTH_SHORT).show();

                        }else{

                            Toast.makeText(CadastroResponsavel.this,"Erro ao alterar", Toast.LENGTH_SHORT).show();

                        }

                    }else{

                        Toast.makeText(CadastroResponsavel.this,"Insira todos os campos obrigatórios!", Toast.LENGTH_SHORT).show();

                    }

                }
            });

        }else{

            btn_cadastrar_responsavel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ResponsavelModel responsavelModel = new ResponsavelModel();

                    if(conferirDados()){

                        responsavelModel.setTelefoneResponsavel(editTextTelefoneResponsavel.getText().toString());
                        responsavelModel.setNomeResponsavel(editTextNomeResponsavel.getText().toString());
                        responsavelModel.setEmailResponsavel(editTextEmailResponsavel.getText().toString());
                        responsavelModel.setCelularResponsavel(editTextCelularResponsavel.getText().toString());

                        if(ResponsavelController.inserirResponsavel(responsavelModel)){

                            Toast.makeText(CadastroResponsavel.this,"Cadastrado com sucesso", Toast.LENGTH_SHORT).show();

                        }else{

                            Toast.makeText(CadastroResponsavel.this,"Erro ao cadastrar", Toast.LENGTH_SHORT).show();

                        }

                    }else{

                        Toast.makeText(CadastroResponsavel.this,"Insira todos os campos obrigatórios!", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

    public boolean conferirDados(){

        if(editTextNomeResponsavel.getText().toString().isEmpty()){
            return false;
        }else{
            return true;
        }

    }

}
