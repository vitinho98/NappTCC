package com.fatecourinhos.napp.controller;

import android.app.DownloadManager;
import android.os.AsyncTask;

import com.fatecourinhos.napp.model.UsuarioModel;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

public class UsuarioController {

    public static String conteudo;
    public static boolean ativo;

    public boolean isAtivo(UsuarioModel usuario){

        String uri = "http://vitorsilva.xyz/napp/usuario/verificarStatus.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("idUsuario", String.valueOf(usuario.getIdUsuario()));

        isAtivo task = new isAtivo();
        task.execute(requestHttp);

        return ativo;
    }

    public String autenticarUsuario(UsuarioModel usuario) {

        String uri = "http://vitorsilva.xyz/napp/usuario/autenticarUsuario.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("login", usuario.getLogin());
        requestHttp.setParametro("senha", usuario.getSenha());

        autenticarUsuario task = new autenticarUsuario();
        task.execute(requestHttp);

        return conteudo;
    }

    private static class isAtivo extends AsyncTask<RequestHttp, String, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            conteudo = (String) HttpManager.getDados(params[0]);

            if(conteudo.equals("Sucesso")){
                ativo = true;
            }else{
                ativo = false;
            }

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }

    private static class autenticarUsuario extends AsyncTask<RequestHttp, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {
            conteudo = (String) HttpManager.getDados(params[0]);

            if(conteudo.equals("Vazio"))
                return null;
            else {
                return conteudo;
            }
        }

        @Override
        protected void onPostExecute(String conteudo) {
            super.onPostExecute(conteudo);
        }
    }
}
