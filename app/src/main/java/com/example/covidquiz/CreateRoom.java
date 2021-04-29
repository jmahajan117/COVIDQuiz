package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CreateRoom extends AppCompatActivity {

    private String currentTeam;
    private Spinner topics;
    private Spinner teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);
        currentTeam = this.getIntent().getStringExtra("Team Name");

        topics = (Spinner) findViewById(R.id.spinnerTopic);
        teams = (Spinner) findViewById(R.id.spinnerTeams);

        ArrayList<String> allTopics = new ArrayList<>();
        ArrayList<String> allPossibleTeams = new ArrayList<>();
        //TODO: Populate the spinner, for topics and teams (EXCEPT THE CURR TEAM)
        try {
            Connection server = DriverManager.getConnection(
                    "jdbc:mysql://34.122.65.95:3306/411Data",
                    "root",
                    "DataBased2");
            Statement statement = server.createStatement();

            ResultSet r = statement.executeQuery("SELECT * " + "FROM Topics");
            while (r.next()) {
                allTopics.add(r.getString("TopicName"));
            }

            ResultSet r2 = statement.executeQuery("SELECT TeamName " + "FROM Teams " +
                    "WHERE TeamName <> \"" + currentTeam + "\"");
            while (r2.next()) {
                allPossibleTeams.add(r2.getString("TeamName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        ArrayAdapter topicAdapter = new ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line, allTopics);
        ArrayAdapter teamAdapter = new ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line, allPossibleTeams);
        topics.setAdapter(topicAdapter);
        teams.setAdapter(teamAdapter);

    }

    public void onCreateRoom(View view) {
        // TODO: Add room to SQL
        String roomNameDB = ((EditText) findViewById(R.id.editTextRoomName)).getText().toString();
        String otherTeam = ((Spinner) findViewById(R.id.spinnerTeams)).getSelectedItem().toString();
        String roomTopic = ((Spinner) findViewById(R.id.spinnerTopic)).getSelectedItem().toString();

        if (currentTeam == null) {
            Toast toast = Toast.makeText(getApplicationContext(), "You are not in a team!", Toast.LENGTH_LONG);
            toast.show();
        }

        if (currentTeam != null) {
            try {
                Connection server = DriverManager.getConnection(
                        "jdbc:mysql://34.122.65.95:3306/411Data",
                        "root",
                        "DataBased2");
                Statement statement = server.createStatement();

                // Query
                int r = statement.executeUpdate("INSERT INTO Rooms(RoomName, TeamA, TeamB, WinsA, WinsB, RoomTopic) " +
                        "VALUES(" + "\"" + roomNameDB + "\", \"" + currentTeam + "\", \"" + otherTeam + "\", 0, 0, \"" + roomTopic + "\")");

                if (r == 1) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Room Successfully Added", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Operation Unsuccessful", Toast.LENGTH_LONG);
                    toast.show();
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            setResult(100);
            finish();
        }
    }
}