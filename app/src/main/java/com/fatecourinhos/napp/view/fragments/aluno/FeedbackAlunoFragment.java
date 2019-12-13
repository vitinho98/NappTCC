package com.fatecourinhos.napp.view.fragments.aluno;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fatecourinhos.napp.R;
import com.fatecourinhos.napp.util.HttpManager;
import com.fatecourinhos.napp.util.RequestHttp;

public class FeedbackAlunoFragment extends Fragment {

    private View view;
    private Context context;
    private String conteudo;
    private RadioGroup rg1,rg2;
    private RadioButton rb1, rb2;
    private Button btn;
    private SharedPreferences preferences;
    private boolean sucesso;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getActivity().setTitle("Feedback");
        view = inflater.inflate(R.layout.fragment_feedback_nucleo,container,false);
        context = view.getContext();

        preferences = context.getSharedPreferences("user_settings", Context.MODE_PRIVATE);
        btn = view.findViewById(R.id.btn_feedback);
        rg1 = view.findViewById(R.id.radio_group_opcao1);
        rg2 = view.findViewById(R.id.radiogroup_opcao2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inserirFeedback();
                System.out.println("?");
            }
        });

        return view;
    }

    private void inserirFeedback(){

        String uri = "http://vitorsilva.xyz/napp/feedback/cadFeedback.php";
        RequestHttp requestHttp = new RequestHttp();
        InserirFeedback task = new InserirFeedback();

        requestHttp.setMetodo("GET");
        requestHttp.setUrl(uri);

        rb1 = view.findViewById(rg1.getCheckedRadioButtonId());
        requestHttp.setParametro("opcao1", (String) rb1.getText());

        rb2 = view.findViewById(rg2.getCheckedRadioButtonId());
        requestHttp.setParametro("opcao2", (String) rb2.getText());

        int id = 0;
        if (preferences.contains("idAluno")) {
            id = preferences.getInt("idAluno", 0);
        }

        requestHttp.setParametro("idAluno", String.valueOf(id));

        task.execute(requestHttp);

    }

    private class InserirFeedback extends AsyncTask<RequestHttp, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(RequestHttp... params) {

            try {

                conteudo = HttpManager.getDados(params[0]);
                System.out.println(conteudo);
                if (conteudo.contains("Sucesso"))
                    sucesso = true;
                else
                    sucesso = false;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return conteudo;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (sucesso)
                Toast.makeText(context, "Feedback enviado, obrigado!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(context, "Erro ao enviar o feedback!", Toast.LENGTH_SHORT).show();

        }
    }

}
