package com.fatecourinhos.napp.view.cadastros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import com.fatecourinhos.napp.R;

public class AnamneseActivity extends AppCompatActivity {

    RadioGroup rg1,rg2,rg3,rg4,rg5,rg6,rg7,rg8,rg9;
    Button btnAnamnese;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anamnese);

        rg1 = findViewById(R.id.rg1);
        rg2 = findViewById(R.id.rg2);
        rg3 = findViewById(R.id.rg3);
        rg4 = findViewById(R.id.rg4);
        rg5 = findViewById(R.id.rg5);
        rg6 = findViewById(R.id.rg6);
        rg7 = findViewById(R.id.rg7);
        rg8 = findViewById(R.id.rg8);
        rg9 = findViewById(R.id.rg9);
        btnAnamnese = findViewById(R.id.btn_anamnese);
    }
}
