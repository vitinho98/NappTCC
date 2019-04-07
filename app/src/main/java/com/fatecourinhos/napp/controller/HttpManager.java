package com.fatecourinhos.napp.controller;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpManager {


    public static String getDados(RequestHttp requestHttp){
        BufferedReader reader = null;
        String uri = requestHttp.getUrl();

        if (requestHttp.getMetodo().equals("GET")){
            uri += "?"+requestHttp.getEncodedParametros();
        }

        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(requestHttp.getMetodo());

            if(requestHttp.getMetodo().equals("POST")){

                JSONObject jsonObject = new JSONObject(requestHttp.getParametros());
                String parametros = jsonObject.toString();


                con.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                writer.write(parametros);
                writer.flush();

            }


            StringBuilder stringBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

    public static String getDados(String uri){

        BufferedReader reader = null;

        try{
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            StringBuilder stringBuilder = new StringBuilder();

            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
}
