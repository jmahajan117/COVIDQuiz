package com.example.covidquiz.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.covidquiz.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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

        String user = getActivity().getIntent().getStringExtra("User");
        String teamName = getActivity().getIntent().getStringExtra("Team Name");

        //TODO: Get the wins of the team and update the textWins elem accordingly

        deleteTeam.setOnClickListener(v -> {
            //TODO: DELETE THE TEAM
            String teamNameDB = ((EditText) root.findViewById(R.id.editTextTextPersonName)).getText().toString();

            try {
                //COPY BELOW
                Connection server = DriverManager.getConnection(
                        "jdbc:mysql://34.122.65.95:3306/411Data",
                        "root",
                        "DataBased2");
                Statement statement = server.createStatement();
                //WATCH SPACES
                int r = statement.executeUpdate("DELETE " +
                        "FROM Teams " + "WHERE TeamName = " +
                        "\""+ teamNameDB + "\"");

            } catch (Exception e) {
                e.printStackTrace();
            }
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