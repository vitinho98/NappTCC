package com.fatecourinhos.napp.view;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.view.cadastros.CadastroAreaAtuacao;
import com.fatecourinhos.napp.view.cadastros.CadastroDiagnostico;
import com.fatecourinhos.napp.view.cadastros.CadastroHorario;
import com.fatecourinhos.napp.view.cadastros.CadastroLocalAtendimento;
import com.fatecourinhos.napp.view.cadastros.CadastroProfissional;
import com.fatecourinhos.napp.view.cadastros.CadastroProfissionalExterno;
import com.fatecourinhos.napp.view.fragments.AreaAtuacaoFragment;
import com.fatecourinhos.napp.view.fragments.DiagnosticoFragment;
import com.fatecourinhos.napp.view.fragments.HorarioAtendimentoFragment;
import com.fatecourinhos.napp.view.fragments.LocalAtendimentoFragment;
import com.fatecourinhos.napp.view.fragments.ProfissionalExternoFragment;
import com.fatecourinhos.napp.view.fragments.ProfissionalFragment;
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

import java.util.List;

public class MenuProfissionalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_profissional);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_profissional);
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        //TO DO --> Abrir a tela adequeada
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment ativo = new Fragment();
                FragmentManager fragmentManager = MenuProfissionalActivity.this.getSupportFragmentManager();
                List<Fragment> fragments = fragmentManager.getFragments();

                if(fragments!=null){
                    for(Fragment fragment : fragments){
                        if(fragment != null && fragment.isVisible()){
                            ativo = fragment;
                        }
                    }
                }

                switch(ativo.getTag()){
                    case("PROFISSIONAL"):
                        Intent intent = new Intent(MenuProfissionalActivity.this, CadastroProfissional.class);
                        startActivity(intent);
                        break;
                    case("HORARIO"):
                        CadastroHorario cadastroHorario = new CadastroHorario();
                        cadastroHorario.show(getSupportFragmentManager(), "HORARIO");
                        break;
                    case("LOCAL"):
                        CadastroLocalAtendimento cadastroLocalAtendimento = new CadastroLocalAtendimento();
                        cadastroLocalAtendimento.show(getSupportFragmentManager(), "LOCAL");
                        break;
                    case("EXTERNO"):
                        Intent intent1 = new Intent(MenuProfissionalActivity.this, CadastroProfissionalExterno.class);
                        startActivity(intent1);
                        break;
                    case("DIAGNOSTICO"):
                        CadastroDiagnostico cadastroDiagnostico = new CadastroDiagnostico();
                        cadastroDiagnostico.show(getSupportFragmentManager(), "DIAGNOSTICO");
                        break;
                    case("CAMPOATUACAO"):
                        CadastroAreaAtuacao cadastroAreaAtuacao = new CadastroAreaAtuacao();
                        cadastroAreaAtuacao.show(getSupportFragmentManager(), "CAMPOATUACAO");
                        break;
                    case("RESPONSAVEL"):
                        Intent intent2 = new Intent(MenuProfissionalActivity.this, CadastroProfissional.class);
                        startActivity(intent2);
                        break;
                }
            }
        });
    }

    //quando o botao da drawer na toolbar é apertado
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //cria o menu de 3 pontinhos
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profissional, menu);
        return true;
    }

    //quando clica no menu 3 pontinhos na toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //TO DO --> Voltar para a tela de login
        if (id == R.id.sair) {

            return true;
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

    //TO DO --> Adicionar todas as telas par
    //método chamado quando algum item do drawer é clicado
    private void displaySeletecScreen(int itemid) {

        Fragment fragment = null;

        switch (itemid) {
            case R.id.nav_profissional:
                fragment = new ProfissionalFragment();
                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout_nav, fragment,"PROFISSIONAL");
                    ft.commit();
                }
                break;

            case R.id.nav_horario_atendimento:
                fragment = new HorarioAtendimentoFragment();
                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout_nav, fragment,"HORARIO");
                    ft.commit();
                }
                break;

            case R.id.nav_local_atendimento:
                fragment = new LocalAtendimentoFragment();
                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout_nav, fragment, "LOCAL");
                    ft.commit();
                }
                break;

            case R.id.nav_profissional_externo:
                fragment = new ProfissionalExternoFragment();
                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout_nav, fragment, "EXTERNO");
                    ft.commit();
                }
                break;

            case R.id.nav_diagnostico:
                fragment = new DiagnosticoFragment();
                if(fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout_nav, fragment, "DIAGNOSTICO");
                    ft.commit();
                }
                break;

            case R.id.nav_campo_atuacao:
                fragment = new AreaAtuacaoFragment();
                if(fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout_nav, fragment, "CAMPOATUACAO");
                    ft.commit();
                }
                break;

            case R.id.nav_responsavel:
                fragment = new HorarioAtendimentoFragment();
                if(fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frame_layout_nav, fragment, "RESPONSAVEL");
                    ft.commit();
                }
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    private boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            return true;
        }else{
            return false;
        }
    }
}