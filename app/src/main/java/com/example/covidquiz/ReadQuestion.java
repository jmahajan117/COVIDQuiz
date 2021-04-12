package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ReadQuestion extends AppCompatActivity {

    private String question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_question);
        question = getIntent().getStringExtra("Question");
        TextView textView = ((TextView) findViewById(R.id.questionText));
        textView.setText(question);
    }

    public void onDelete(View view) {
        //TODO: Delete the current question
    }


}