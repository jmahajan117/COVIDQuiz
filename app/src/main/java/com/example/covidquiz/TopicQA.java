package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Spinner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TopicQA extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_q);

        ArrayList<TwoEntry> entryArrayList = new ArrayList<>();
        Entry2DAdapter adapter = new Entry2DAdapter(this, R.layout.entry2display,
                entryArrayList);
        ListView listView = (ListView) findViewById(R.id.topicAcc);
        //TODO: Adv SQL Stuff here
        try {
            Connection server = DriverManager.getConnection(
                    "jdbc:mysql://34.122.65.95:3306/411Data",
                    "root",
                    "DataBased2");
            Statement statement = server.createStatement();

            ResultSet r = statement.executeQuery("SELECT temp.Topic, temp.Question, temp.Accuracy, a.Answer, a.IsRight " +
                    "FROM(" +
                    "    SELECT q.Topic, q.Question, q.Accuracy " +
                    "    FROM Questions q " +
                    "    GROUP BY q.Topic, q.Question, q.Accuracy " +
                    "    HAVING q.Accuracy = MIN(q.Accuracy) " +
                    ") as temp JOIN Answers a ON temp.Question = a.QuestionName " + "LIMIT 30");
            while (r.next()) {
                TwoEntry questionTopic = new TwoEntry(r.getString("Question"), r.getString("Accuracy"));
                entryArrayList.add(questionTopic);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        listView.setAdapter(adapter);

    }
}