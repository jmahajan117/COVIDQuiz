package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.text.DecimalFormat;

public class Ranking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        String team = this.getIntent().getStringExtra("Team Name");

        try {
            Connection server = DriverManager.getConnection(
                    "jdbc:mysql://34.122.65.95:3306/411Data",
                    "root",
                    "DataBased2");


            CallableStatement statement = server.prepareCall("{call Switchboard(2, ?, ?)}");
            statement.registerOutParameter(2, Types.INTEGER);
            statement.setString(1, team);

            statement.execute();
            int wins = statement.getInt(2);


            int total = 0;
            Statement count = server.createStatement();
            ResultSet r = count.executeQuery("SELECT COUNT(*) FROM Teams");
            while(r.next()) {
                total = r.getInt(1);
            }

            double percent = wins / (total + 0.0);


            ImageView view = (ImageView) findViewById(R.id.badgeImageView);
            TextView textView = (TextView) findViewById(R.id.textViewRank);
            String text = textView.getText().toString();

            if (percent < 0.4) {
                Glide.with(this).load(R.drawable.bronze_medal).into(view);
                text += " \nBronze";
            }
            else if (percent < 0.7) {
                Glide.with(this).load(R.drawable.silver_medal).into(view);
                text += " \nSilver";
            }

            else if (percent < 0.9) {
                Glide.with(this).load(R.drawable.gold_medal).into(view);
                text += " \nGold";
            }
            else {
                Glide.with(this).load(R.drawable.platinum_medal).into(view);
                text += " \nPlatinum";
            }

            textView.setText(text);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}