package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class TeamWins extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_wins);

        ArrayList<TwoEntry> entries = new ArrayList<>();
        Entry2DAdapter adapter = new Entry2DAdapter(this, R.layout.entry2display, entries);
        ListView teamList = (ListView) findViewById(R.id.teamList);
        //TODO: Adv SQL Q here TEAM WITH WINS


        teamList.setAdapter(adapter);


    }
}