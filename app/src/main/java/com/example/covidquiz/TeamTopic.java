package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TeamTopic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_topic);

        ArrayList<TwoEntry> entries = new ArrayList<>();
        Entry2DAdapter adapter = new Entry2DAdapter(this, R.layout.entry2display, entries);
        ListView teamList = (ListView) findViewById(R.id.topicTeam);
        //TODO: Adv SQL Q here TEAM WITH Topic
        try {
            Connection server = DriverManager.getConnection(
                    "jdbc:mysql://34.122.65.95:3306/411Data",
                    "root",
                    "DataBased2");
            Statement statement = server.createStatement();

            ResultSet r = statement.executeQuery("SELECT DISTINCT temp2.teamname as selteam, MAX(temp2.topic) as topic, MAX(temp2.topicrounds)" +
                    "FROM ( " +
                    "SELECT temp.teamname as teamname, temp.topic as topic, SUM(temp.topicrounds) as topicrounds " +
                    "FROM ( " +
                    "SELECT tA.TeamName as teamname, r.RoomTopic as topic, r.WinsA + r.WinsB as topicrounds " +
                    "FROM Rooms r JOIN Teams tA ON r.TeamA = tA.TeamName " +
                    "UNION " +
                    "SELECT tB.TeamName as teamname, r.RoomTopic as topic, r.WinsA + r.WinsB as topicrounds " +
                    "FROM Rooms r JOIN Teams tB ON r.TeamB = tB.TeamName " +
                    ") as temp " +
                    "GROUP BY temp.teamname, temp.topic " +
                    ") as temp2 " +
                    "GROUP BY temp2.teamname " +
                    "LIMIT 30;");
            while (r.next()) {
                TwoEntry questionTopic = new TwoEntry(r.getString("selteam"), r.getString("topic"));
                entries.add(questionTopic);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        teamList.setAdapter(adapter);
    }
}