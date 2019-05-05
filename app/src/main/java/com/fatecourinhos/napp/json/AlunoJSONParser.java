package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.AlunoModel;
import com.fatecourinhos.napp.model.UsuarioModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
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
                aluno.setAnoEntrada(jsonObject.getString("anoEntrada"));
                aluno.setCelularAluno(jsonObject.getString("celularAluno"));
                aluno.setSexo(jsonObject.getString("sexo"));
                aluno.setCidadeAluno(jsonObject.getString("cidadeAluno"));
                aluno.setCpf(jsonObject.getString("cpf"));
                //aluno.setDataCadAluno(jsonObject.getString("dataCadAluno"));
                //aluno.setDataNascimento(jsonObject.getString("dataNascimento"));
                aluno.setEmailAluno(jsonObject.getString("emailAluno"));
                aluno.setCurso(jsonObject.getString("curso"));
                aluno.setEmpregado(jsonObject.getBoolean("empregado"));
                aluno.setSemestre(jsonObject.getInt("semestre"));
                aluno.setRa(jsonObject.getString("ra"));
                aluno.setEstadoCivil(jsonObject.getString("estadoCivil"));

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
