package com.fatecourinhos.napp.view;

import android.os.Bundle;

import com.fatecourinhos.napp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

public class ResponsavelActivity extends AppCompatActivity {

    private AppCompatEditText editTextNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_responsavel);
    }
}
