package com.fatecourinhos.napp.view;

import android.os.Bundle;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.view.fragments.AgendamentoAlunoFragment;
import com.fatecourinhos.napp.view.fragments.Feedback;
import com.fatecourinhos.napp.view.fragments.MensagemAlunoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;

public class MenuAlunoActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment = null;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()) {
                case R.id.nav_agendamento_aluno:

                    fragment = new AgendamentoAlunoFragment();
                    ft.replace(R.id.frame_layout_aluno, fragment);
                    ft.commit();

                    return true;
                case R.id.nav_mensagem:

                    fragment = new MensagemAlunoFragment();
                    ft.replace(R.id.frame_layout_aluno, fragment);
                    ft.commit();

                    return true;
                case R.id.nav_feedback:

                    fragment = new Feedback();
                    ft.replace(R.id.frame_layout_aluno, fragment);
                    ft.commit();

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_aluno);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
