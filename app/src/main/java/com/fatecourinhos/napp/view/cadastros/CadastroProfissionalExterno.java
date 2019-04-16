package com.fatecourinhos.napp.view.cadastros;

import android.os.Bundle;

import com.fatecourinhos.napp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

public class CadastroProfissionalExterno extends AppCompatActivity {

    private AppCompatEditText editTextNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_activity_profissional_externo);
    }
}
