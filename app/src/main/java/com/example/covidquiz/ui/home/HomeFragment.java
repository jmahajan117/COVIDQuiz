package com.example.covidquiz.ui.home;

import android.content.Intent;
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
import com.example.covidquiz.Ranking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
        Button rank = (Button) root.findViewById(R.id.rankButton);

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
            String teamNameDB = ((EditText) root.findViewById(R.id.editTextTextPersonName)).getText().toString();

            try {
                Connection server = DriverManager.getConnection(
                        "jdbc:mysql://34.122.65.95:3306/411Data",
                        "root",
                        "DataBased2");
                Statement statement = server.createStatement();

                // Check if the entered team exists- if it does, then display wins and then allow a joining
                ResultSet r = statement.executeQuery("SELECT t.TeamName " + "FROM Teams t " +
                        "WHERE t.TeamName = \"" + teamNameDB + "\"");

                boolean foundTeam = false;
                while (r.next()) {
                    if (r.getString("TeamName").equals(teamNameDB)) {
                        foundTeam = true;
                        break;
                    }
                }

                if (foundTeam) {
                    int r2 = statement.executeUpdate("UPDATE Users " + "SET TName = " +
                            "\"" + teamNameDB + "\" " + "WHERE Username = \"" + user + "\"");
                }

                // Check if this team has ever been in a room, else set the text to a hard 0
                ResultSet r3 = statement.executeQuery("SELECT temp.teamname AS tname, SUM(temp.teamwins) AS totalwins " +
                        "FROM (SELECT r.TeamA as teamname, r.WinsA as teamwins FROM Rooms r " +
                        "WHERE r.TeamA = \"" + teamNameDB + "\" UNION SELECT r.TeamB as teamname, " +
                        "r.WinsB as teamwins FROM Rooms r WHERE r.TeamB = \"" + teamNameDB + "\") as " +
                        "temp GROUP BY temp.teamname");
                int teamWins = 0;
                while (r3.next()) {
                    if (r3.getString("tname").equals(teamNameDB)) {
                        teamWins = r3.getInt("totalwins");
                    }
                }

                // TODO: Place teamWins into the textViewWins field!
                textWins.setText("Team Wins: " + teamWins);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        createTeam.setOnClickListener(v -> {
            //TODO: CREATE THE TEAM
            String teamNameDB = ((EditText) root.findViewById(R.id.editTextTextPersonName)).getText().toString();

            try {
                Connection server = DriverManager.getConnection(
                        "jdbc:mysql://34.122.65.95:3306/411Data",
                        "root",
                        "DataBased2");
                Statement statement = server.createStatement();

                int r = statement.executeUpdate("INSERT INTO Teams(TeamName, Owner) VALUES(" +
                        "\"" + teamNameDB + "\", \"" + user + "\")");
            } catch (Exception e) {
                e.printStackTrace();;
            }
        });


        rank.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), Ranking.class);
            i.putExtra("Team Name", teamName);
            startActivity(i);
        });

        return root;
    }
}