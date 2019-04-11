package com.fatecourinhos.napp.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class RequestHttp {
    private String url;
    private String metodo = "GET";
    private Map<String, String> parametros = new HashMap<>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public Map<String, String> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, String> parametros) {
        this.parametros = parametros;
    }

    public void setParametro(String key, String value){
        parametros.put(key, value);
    }

    public String getEncodedParametros(){
        StringBuilder stringBuilder = new StringBuilder();

        for (String key: parametros.keySet()){
            String value = null;

            try {
                value = URLEncoder.encode(parametros.get(key), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if (stringBuilder.length() > 0){
                stringBuilder.append("&");
            }

            stringBuilder.append(key+"="+value);
        }

        return stringBuilder.toString();
    }
}
