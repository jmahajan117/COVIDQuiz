package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

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
        //TODO: Get the topic, display lowest acc

        QAList.setAdapter(adapter);
    }
}