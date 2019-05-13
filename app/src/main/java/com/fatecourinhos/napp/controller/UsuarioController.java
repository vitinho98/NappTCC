package com.fatecourinhos.napp.controller;

import android.app.DownloadManager;
import android.os.AsyncTask;
import android.util.Log;

import com.fatecourinhos.napp.model.UsuarioModel;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;
import com.fatecourinhos.napp.view.LoginActivity;

public class UsuarioController {

    public static String conteudo;
    public static boolean ativo;

    public static boolean isAtivo(UsuarioModel usuario){

        String uri = "http://vitorsilva.xyz/napp/usuario/verificarStatus.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("idUsuario", String.valueOf(usuario.getIdUsuario()));

        isAtivo task = new isAtivo();
        task.execute(requestHttp);

        return ativo;
    }

    public static String autenticarUsuario(UsuarioModel usuario) {

        String uri = "http://vitorsilva.xyz/napp/usuario/autenticarUsuario.php";

        RequestHttp requestHttp = new RequestHttp();
        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        requestHttp.setParametro("login", usuario.getLogin());
        requestHttp.setParametro("senha", usuario.getSenha());
        Log.e("login", usuario.getLogin());Log.e("senha", usuario.getSenha());

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
            LoginActivity loginActivity = new LoginActivity();
            loginActivity.login(conteudo);
            Log.e("haa", ":9");
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

            Log.e("aqui", conteudo);

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
