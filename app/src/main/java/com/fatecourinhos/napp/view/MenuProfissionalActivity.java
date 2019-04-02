package com.fatecourinhos.napp.view;

import android.content.Intent;
import android.os.Bundle;

import com.fatecourinhos.napp.R;
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
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuProfissionalActivity.this, ProfissionalActivity.class);
                startActivity(intent);
                /*
                switch (navigationView.getCheckedItem().getItemId()) {

                    case R.id.nav_profissional:
                        intent = new Intent(MenuProfissionalActivity.this, ProfissionalActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.nav_horario_atendimento:
                        intent = new Intent(MenuProfissionalActivity.this, CadastroHorario.class);
                        startActivity(intent);
                        break;

                    case R.id.nav_local_atendimento:
                        intent = new Intent(MenuProfissionalActivity.this, CadastroLocal.class);
                        startActivity(intent);
                        break;

                    case R.id.nav_profissional_externo:
                        intent = new Intent(MenuProfissionalActivity.this, ProfissionalExternoActivity.class);
                        startActivity(intent);
                        break;

                }
                */

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

    //método chamado quando algum item do drawer é clicado
    private void displaySeletecScreen(int itemid) {

        Fragment fragment = null;

        switch (itemid) {
            case R.id.nav_profissional:
                fragment = new ProfissionalFragment();
                break;

            case R.id.nav_horario_atendimento:
                fragment = new HorarioAtendimentoFragment();
                break;

            case R.id.nav_local_atendimento:
                fragment = new LocalAtendimentoFragment();
                break;

            case R.id.nav_profissional_externo:
                fragment = new ProfissionalExternoFragment();
                break;

        }


        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_layout_nav, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

}
