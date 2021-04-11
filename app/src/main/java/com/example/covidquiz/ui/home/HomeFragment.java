package com.example.covidquiz.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.covidquiz.R;

public class HomeFragment extends Fragment {

    private Button deleteTeam;
    private Button joinTeam;
    private Button createTeam;
    private TextView textWins;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        deleteTeam = (Button) root.findViewById(R.id.buttonDeleteTeam);
        joinTeam = (Button) root.findViewById(R.id.buttonJoin);
        createTeam = (Button) root.findViewById(R.id.buttonCreateTeam);
        textWins = (TextView) root.findViewById(R.id.textViewWins);

        String teamName = getActivity().getIntent().getStringExtra("Team Name");

        //TODO: Get the wins of the team and update the textWins elem accordingly

        deleteTeam.setOnClickListener(v -> {
            //TODO: DELETE THE TEAM
            String message = "Good Luck";
        });

        joinTeam.setOnClickListener(v -> {
            //TODO: JOIN THE TEAM
            String message = "Good Luck";
        });

        createTeam.setOnClickListener(v -> {
            //TODO: CREATE THE TEAM
            String message = "Good Luck";
        });

        return root;
    }
}