package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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



        ArrayAdapter topicAdapter = new ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line, allTopics);
        ArrayAdapter teamAdapter = new ArrayAdapter(this,
                android.R.layout.simple_dropdown_item_1line, allPossibleTeams);
        topics.setAdapter(topicAdapter);
        teams.setAdapter(teamAdapter);

    }

    public void onCreateRoom(View view) {
        // TODO: Add room to SQL
    }
}