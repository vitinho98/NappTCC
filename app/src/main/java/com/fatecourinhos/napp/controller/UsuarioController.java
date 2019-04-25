package com.fatecourinhos.napp.controller;

import android.os.AsyncTask;

import com.fatecourinhos.napp.json.UsuarioJSONParser;
import com.fatecourinhos.napp.model.ResponsavelModel;
import com.fatecourinhos.napp.model.UsuarioModel;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import java.util.List;

public class UsuarioController {

    public static String tipoLista;

    public String autenticarUsuario(UsuarioModel usuario) {

        String uri = "http://vitorsilva.xyz/napp/usuario/autenticarUsuario.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("login", usuario.getLogin());
        requestHttp.setParametro("senha", usuario.getSenha());

        autenticarUsuario task = new autenticarUsuario();
        task.execute(requestHttp);

        return tipoLista;
    }

    private static class autenticarUsuario extends AsyncTask<RequestHttp, String, String> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            final String conteudo = (String) HttpManager.getDados(params[0]);

            if(conteudo.equals("Vazio"))
                return null;
            else {
                return conteudo;
            }
        }

        @Override
        protected void onPostExecute(String conteudo) {

        }
    }
}
