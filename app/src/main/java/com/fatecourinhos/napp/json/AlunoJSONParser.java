package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.Aluno;
import com.fatecourinhos.napp.model.Usuario;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AlunoJSONParser {

    public static List<Aluno> parseDados(String content) {

        try {

            JSONArray jsonArray = new JSONArray(content);
            List<Aluno> alunos = new ArrayList<>();

            Usuario usuario;
            Aluno aluno;

            for (int i=0; i<jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                usuario = new Usuario();
                aluno = new Aluno();

                aluno.setIdAluno(jsonObject.getInt("idAluno"));
                aluno.setNomeAluno(jsonObject.getString("nomeAluno"));
                /*aluno.setAnoEntrada(jsonObject.getString("anoEntrada"));
                aluno.setCelularAluno(jsonObject.getString("celularAluno"));
                aluno.setSexo(jsonObject.getString("sexo"));
                aluno.setCidadeAluno(jsonObject.getString("cidadeAluno"));
                aluno.setCpf(jsonObject.getString("cpf"));
                aluno.setDataNascimento(jsonObject.getString("dataNascimento"));
                aluno.setEmailAluno(jsonObject.getString("emailAluno"));
                aluno.setCurso(jsonObject.getString("curso"));
                aluno.setSemestre(jsonObject.getInt("semestre"));
                aluno.setRa(jsonObject.getString("ra"));
                aluno.setEstadoCivil(jsonObject.getString("estadoCivil"));

                usuario.setIdUsuario(jsonObject.getInt("idUsuario"));
                usuario.setLogin(jsonObject.getString("login"));
                usuario.setSenha(jsonObject.getString("senha"));
                usuario.setTipoUsuario(jsonObject.getString("tipoUsuario"));
                usuario.setStatus(jsonObject.getInt("status"));
                aluno.setFkUsuario(usuario);*/

                alunos.add(aluno);

            }

            return alunos;

        } catch (Exception e) {
            return null;
        }
    }

}
