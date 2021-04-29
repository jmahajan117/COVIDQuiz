package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    String topicDBName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lowest_accuracy);

        QAList = (ListView) findViewById(R.id.questionAccTopic);

        Button b = (Button) findViewById(R.id.buttonSubmit);
        b.setOnClickListener(v -> {
            topicDBName = ((EditText) findViewById(R.id.editTextTopicEnter)).getText().toString();
            new Task().execute();
        });
    }

    class Task extends AsyncTask<Void, Void, Void> {
        ArrayList<TwoEntry> entries = new ArrayList<>();
        @Override
        protected Void doInBackground(Void... voids) {


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
            Entry2DAdapter adapter = new Entry2DAdapter(getApplicationContext(), R.layout.entry2display, entries);
            if (adapter == null) {
                Log.wtf("help", "adapter's null");
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    QAList.setAdapter(adapter);
                }
            });


            return null;
        }
    }

}