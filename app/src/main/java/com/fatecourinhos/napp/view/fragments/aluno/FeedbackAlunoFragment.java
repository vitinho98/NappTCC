package com.fatecourinhos.napp.view.fragments.aluno;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fatecourinhos.napp.R;

public class FeedbackAlunoFragment extends Fragment {

    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getActivity().setTitle("Feedback");
        view = inflater.inflate(R.layout.fragment_feedback_nucleo,container,false);
        context = view.getContext();

        return view;
    }

}
