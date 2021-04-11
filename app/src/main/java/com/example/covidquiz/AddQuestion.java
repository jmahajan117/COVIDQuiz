package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddQuestion extends AppCompatActivity {

    private Spinner spin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        spin = (Spinner) findViewById(R.id.topicSpinner);

        ArrayList<String> topics = new ArrayList<>();
        //TODO: Fill array list with topics


        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, topics);
        spin.setAdapter(adapter);


    }

    public void onClickAdd(View view) {
        //TODO: Assuming they write a topic, add the question into SQL
    }


}