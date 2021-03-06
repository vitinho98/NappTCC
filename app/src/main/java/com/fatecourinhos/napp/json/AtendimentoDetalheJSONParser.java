package com.fatecourinhos.napp.json;

import com.fatecourinhos.napp.model.AtendimentoDetalhe;
import com.fatecourinhos.napp.model.Prognostico;

import org.json.JSONArray;
import org.json.JSONObject;

public class AtendimentoDetalheJSONParser {

    public static AtendimentoDetalhe parseDados(String content) {

        try {

            JSONArray jsonArray = new JSONArray(content);

            AtendimentoDetalhe atendimentoDetalhe = null;
            Prognostico prognostico = null;

            for (int i=0; i<jsonArray.length(); i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                atendimentoDetalhe = new AtendimentoDetalhe();
                prognostico = new Prognostico();

                try {
                    atendimentoDetalhe.setObsAtendimento(jsonObject.getString("obsAtendimento"));
                    atendimentoDetalhe.setDiagnosticos(jsonObject.getString("diagnosticos"));
                    atendimentoDetalhe.setProfsExternos(jsonObject.getString("profExternos"));
                } catch (Exception e) {
                    atendimentoDetalhe.setObsAtendimento(null);
                    atendimentoDetalhe.setDiagnosticos(null);
                    atendimentoDetalhe.setProfsExternos(null);
                }

                try {
                    prognostico.setOpcao1(jsonObject.getInt("opcao1"));
                    prognostico.setOpcao2(jsonObject.getInt("opcao2"));
                    prognostico.setOpcao3(jsonObject.getInt("opcao3"));
                    prognostico.setOpcao4(jsonObject.getInt("opcao4"));
                    prognostico.setOpcao5(jsonObject.getInt("opcao5"));
                    prognostico.setObs(jsonObject.getString("observacao"));
                } catch (Exception e) {
                    prognostico.setOpcao1(1);
                    prognostico.setOpcao2(1);
                    prognostico.setOpcao3(1);
                    prognostico.setOpcao4(1);
                    prognostico.setOpcao5(1);
                    prognostico.setObs(null);
                }

                atendimentoDetalhe.setFkPrognostico(prognostico);
            }

            return atendimentoDetalhe;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
