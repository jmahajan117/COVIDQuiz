package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
        try {
            //COPY BELOW
            Connection server = DriverManager.getConnection(
                    "jdbc:mysql://34.122.65.95:3306/411Data",
                    "root",
                    "DataBased2");
            Statement statement = server.createStatement();
            //WATCH SPACES
            ResultSet r = statement.executeQuery("SELECT temp.teamname AS finalName, temp.teamwins AS teamWins\n" +
                    "FROM (\n" +
                    "  SELECT  temp2.teamname, SUM(temp2.teamwins) as teamwins\n" +
                    "  FROM (\n" +
                    "    SELECT tA.TeamName as teamname, SUM(r.WinsA) as teamwins\n" +
                    "    FROM Rooms r JOIN Teams tA ON r.TeamA = tA.TeamName\n" +
                    "    GROUP BY tA.TeamName\n" +
                    "    UNION\n" +
                    "    SELECT tB.TeamName as teamname, SUM(r.WinsB) as teamwins\n" +
                    "    FROM Rooms r JOIN Teams tB ON r.TeamB = tB.TeamName\n" +
                    "    GROUP BY tB.TeamName\n" +
                    "  ) as temp2\n" +
                    "  GROUP BY temp2.teamname\n" +
                    ") as temp\n" +
                    "ORDER BY teamWins DESC\n" +
                    "LIMIT 15;");

            while(r.next()) {
                    TwoEntry entry = new TwoEntry(r.getString("finalName"), r.getString("teamWins"));
                    entries.add(entry);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        teamList.setAdapter(adapter);


    }
}