package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.AlunoModel;
import com.fatecourinhos.napp.model.UsuarioModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AlunoJSONParser {

    public static List<AlunoModel> parseDados(String content){
        try{

            JSONArray jsonArray = new JSONArray(content);
            List<AlunoModel> alunoList = new ArrayList<>();

            UsuarioModel usuarioModel = new UsuarioModel();
            AlunoModel aluno = new AlunoModel();

            for(int i=0; i<jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                aluno.setIdAluno(jsonObject.getInt("idAluno"));
                aluno.setNomeAluno(jsonObject.getString("nomeAluno"));

                usuarioModel.setIdUsuario(jsonObject.getInt("idUsuario"));
                usuarioModel.setLogin(jsonObject.getString("login"));
                usuarioModel.setSenha(jsonObject.getString("senha"));
                usuarioModel.setTipoUsuario(jsonObject.getString("tipo"));
                usuarioModel.setStatus(jsonObject.getInt("status"));
                aluno.setFkUsuario(usuarioModel);

                alunoList.add(aluno);

            }

            return alunoList;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
