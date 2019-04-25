package com.fatecourinhos.napp.controller;

import android.os.AsyncTask;

import com.fatecourinhos.napp.json.UsuarioJSONParser;
import com.fatecourinhos.napp.model.ResponsavelModel;
import com.fatecourinhos.napp.model.UsuarioModel;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

import java.util.List;

public class UsuarioController {

    public static boolean sucesso;
    public static List<UsuarioModel> usuarioModelList;

    public static boolean autenticarUsuario(UsuarioModel usuario) {

        sucesso = false;

        String uri = "http://vitorsilva.xyz/napp/usuario/autenticarUsuario.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("login", usuario.getLogin());
        requestHttp.setParametro("senha", usuario.getSenha());

        autenticarUsuario task = new autenticarUsuario();
        task.execute(requestHttp);

        return sucesso;
    }

    private static class autenticarUsuario extends AsyncTask<RequestHttp, String, List<UsuarioModel>> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected List<UsuarioModel> doInBackground(RequestHttp... params) {
            final String conteudo = (String) HttpManager.getDados(params[0]);

            if(conteudo.equals("Vazio"))
                return null;
            else {
                usuarioModelList = UsuarioJSONParser.parseDados(conteudo);
                return usuarioModelList;
            }
        }

        @Override
        protected void onPostExecute(List<UsuarioModel> usuarioModelList) {

            usuarioModelList = usuarioModelList;

        }
    }
}
