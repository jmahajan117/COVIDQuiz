package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LowestAccuracy extends AppCompatActivity {

    private ListView QAList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lowest_accuracy);

        QAList = (ListView) findViewById(R.id.questionAccList);
    }

    public void onEntry(View view) {
        ArrayList<TwoEntry> entries = new ArrayList<>();
        Entry2DAdapter adapter = new Entry2DAdapter(this, R.layout.entry2display, entries);
        String topicDBName = ((EditText) findViewById(R.id.editTextTopicEnter)).getText().toString();

        //TODO: Get the topic, display lowest acc
        try {
            Connection server = DriverManager.getConnection(
                    "jdbc:mysql://34.122.65.95:3306/411Data",
                    "root",
                    "DataBased2");
            Statement statement = server.createStatement();
            //WATCH SPACES
            ResultSet r = statement.executeQuery("SELECT Question, Accuracy FROM Questions WHERE Topic = \"" +
                    topicDBName + "\" ORDER BY Accuracy ASC LIMIT 15");

            while (r.next()) {
                TwoEntry item = new TwoEntry(r.getString("Question"), r.getString("Accuracy"));
                entries.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        QAList.setAdapter(adapter);
    }
}