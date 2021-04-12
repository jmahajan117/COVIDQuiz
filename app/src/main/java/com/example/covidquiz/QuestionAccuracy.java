package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class QuestionAccuracy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_accuracy);

        //TwoEntry a = new TwoEntry("Hello", "Fuck");
        //TwoEntry b = new TwoEntry("Yeet", "T");
        ArrayList<TwoEntry> entries = new ArrayList<TwoEntry>();
        //entries.add(a);
        //entries.add(b);

        Entry2DAdapter adapter = new Entry2DAdapter(this, R.layout.entry2display, entries);
        ListView list = (ListView) findViewById(R.id.questionAccList);
        //TODO: Use the ADV Sql fill the arraylist


        list.setAdapter(adapter);
    }
}