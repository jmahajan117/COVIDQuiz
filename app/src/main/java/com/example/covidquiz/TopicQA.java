package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Spinner;

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

        listView.setAdapter(adapter);

    }
}