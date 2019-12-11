package com.fatecourinhos.napp.view.menu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.Relatorio;
import com.fatecourinhos.napp.RelatoriosFragment;
import com.fatecourinhos.napp.view.cadastros.profissional.CadastroCampoAtuacao;
import com.fatecourinhos.napp.view.cadastros.profissional.CadastroDiagnostico;
import com.fatecourinhos.napp.view.cadastros.profissional.CadastroHorario;
import com.fatecourinhos.napp.view.cadastros.profissional.CadastroLocalAtendimento;
import com.fatecourinhos.napp.view.cadastros.profissional.CadastroMensagem;
import com.fatecourinhos.napp.view.cadastros.profissional.CadastroProfissional;
import com.fatecourinhos.napp.view.cadastros.profissional.CadastroProfissionalExterno;
import com.fatecourinhos.napp.view.cadastros.profissional.CadastroResponsavel;
import com.fatecourinhos.napp.view.fragments.profissional.AgendamentoProfissionalFragment;
import com.fatecourinhos.napp.view.fragments.profissional.AlunoFragment;
import com.fatecourinhos.napp.view.fragments.profissional.CampoAtuacaoFragment;

import com.fatecourinhos.napp.view.fragments.profissional.DiagnosticoFragment;
import com.fatecourinhos.napp.view.fragments.profissional.HorarioProfissionalFragment;
import com.fatecourinhos.napp.view.fragments.profissional.LocalAtendimentoFragment;
import com.fatecourinhos.napp.view.fragments.profissional.ProfissionalExternoFragment;
import com.fatecourinhos.napp.view.fragments.profissional.ProfissionalFragment;
import com.fatecourinhos.napp.view.fragments.profissional.ResponsavelFragment;
import com.fatecourinhos.napp.view.login.Login;
import com.fatecourinhos.napp.view.senha.AlterarSenha;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MenuProfissional extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FloatingActionButton fab;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_profissional);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view_profissional);
        navigationView.setNavigationItemSelectedListener(this);

        TextView textViewNome;
        textViewNome = navigationView.getHeaderView(0).findViewById(R.id.txt_header);
        preferences = getSharedPreferences("user_settings", MODE_PRIVATE);
        if (preferences.contains("nome")) {
            textViewNome.setText("Bem vindo(a) " + preferences.getString("nome", null));
        }

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment ativo = new Fragment();
                FragmentManager fragmentManager = MenuProfissional.this.getSupportFragmentManager();
                List<Fragment> fragments = fragmentManager.getFragments();

                if (fragments != null)
                    for (Fragment fragment : fragments)
                        if (fragment != null && fragment.isVisible())
                            ativo = fragment;

                switch (ativo.getTag()) {

                    case("PROFISSIONAL"):
                        startActivity(new Intent(MenuProfissional.this, CadastroProfissional.class));
                        break;

                    case("HORARIO"):
                        startActivity(new Intent(MenuProfissional.this, CadastroHorario.class));
                        break;

                    case("LOCAL"):
                        startActivity(new Intent(MenuProfissional.this, CadastroLocalAtendimento.class));
                        break;

                    case("EXTERNO"):
                        startActivity(new Intent(MenuProfissional.this, CadastroProfissionalExterno.class));
                        break;

                    case("DIAGNOSTICO"):
                        startActivity(new Intent(MenuProfissional.this, CadastroDiagnostico.class));
                        break;

                    case("CAMPOATUACAO"):
                        startActivity(new Intent(MenuProfissional.this, CadastroCampoAtuacao.class));
                        break;

                    case("RESPONSAVEL"):
                        startActivity(new Intent(MenuProfissional.this, CadastroResponsavel.class));
                        break;

                }
            }
        });
    }

    //quando o botao da drawer na toolbar é apertado
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    //cria o menu de 3 pontinhos
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sistema, menu);
        return true;
    }

    @SuppressLint("RestrictedApi")
    private void desabilitarFloat() {
        fab.setEnabled(false);
        fab.setVisibility(View.INVISIBLE);
    }

    @SuppressLint("RestrictedApi")
    private void habilitarFloat() {
        fab.setEnabled(true);
        fab.setVisibility(View.VISIBLE);
    }

    //quando clica no menu 3 pontinhos na toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.sair) {

            SharedPreferences preferences = getSharedPreferences("user_settings", MODE_PRIVATE);;
            SharedPreferences.Editor editor = preferences.edit();

            editor.clear();
            editor.commit();

            startActivity(new Intent(MenuProfissional.this, Login.class));
            finish();

        } else  if (id == R.id.trocar_senha) {

            AlterarSenha dialog = new AlterarSenha();
            dialog.show(getSupportFragmentManager(), "ALTERAR SENHA");

        }

        return super.onOptionsItemSelected(item);
    }

    //quando clica em algum menu do drawer chama o metodo display
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displaySeletecScreen(item.getItemId());
        return true;
    }

    //método chamado quando algum item do drawer é clicado
    private void displaySeletecScreen(int itemid) {

        Fragment fragment = null;

        switch (itemid) {

            case R.id.nav_agendamento:
                fragment = new AgendamentoProfissionalFragment();
                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout_nav, fragment,"AGENDAMENTO");
                    ft.commit();
                    desabilitarFloat();
                }
                break;

            case R.id.nav_alunos:
                fragment = new AlunoFragment();
                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout_nav, fragment,"ALUNOS");
                    ft.commit();
                }
                desabilitarFloat();
                break;

            case R.id.nav_relatorios:
                fragment = new RelatoriosFragment();
                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout_nav, fragment,"RELATORIOS");
                    ft.commit();
                }
                desabilitarFloat();

                break;

            case R.id.nav_indicacoes:
                startActivity(new Intent(MenuProfissional.this, CadastroMensagem.class));
                break;

            case R.id.nav_profissional:
                fragment = new ProfissionalFragment();
                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout_nav, fragment,"PROFISSIONAL");
                    ft.commit();
                    habilitarFloat();
                }
                break;

            case R.id.nav_horario_atendimento:
                fragment = new HorarioProfissionalFragment();
                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout_nav, fragment,"HORARIO");
                    ft.commit();
                    habilitarFloat();
                }
                break;

            case R.id.nav_local_atendimento:
                fragment = new LocalAtendimentoFragment();
                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout_nav, fragment, "LOCAL");
                    ft.commit();
                    habilitarFloat();
                }
                break;

            case R.id.nav_profissional_externo:
                fragment = new ProfissionalExternoFragment();
                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout_nav, fragment, "EXTERNO");
                    ft.commit();
                    habilitarFloat();
                }
                break;

            case R.id.nav_diagnostico:
                fragment = new DiagnosticoFragment();
                if(fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout_nav, fragment, "DIAGNOSTICO");
                    ft.commit();
                    habilitarFloat();
                }
                break;

            case R.id.nav_campo_atuacao:
                fragment = new CampoAtuacaoFragment();
                if(fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout_nav, fragment, "CAMPOATUACAO");
                    ft.commit();
                    habilitarFloat();
                }
                break;

            case R.id.nav_responsavel:
                fragment = new ResponsavelFragment();
                if(fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout_nav, fragment, "RESPONSAVEL");
                    ft.commit();
                    habilitarFloat();
                }
                break;

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private boolean isOnline(){

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;

    }

}