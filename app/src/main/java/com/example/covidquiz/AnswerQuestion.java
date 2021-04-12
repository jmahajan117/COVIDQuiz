package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AnswerQuestion extends AppCompatActivity {

    private String roomName;
    private String teamName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_question);
        roomName = this.getIntent().getStringExtra("Room Name");
        teamName = this.getIntent().getStringExtra("Team Name");
        //TODO: Get a question, display it and it's choices
    }

    public void onAnswer(View view) {
        //TODO: Given what the user clicked, update question Info and wins
        int id = view.getId();
        Button clicked;
        switch (id) {
            case R.id.buttonChoice1:
                clicked = (Button) findViewById(R.id.buttonChoice1);
                break;
            case R.id.buttonChoice2:
                clicked = (Button) findViewById(R.id.buttonChoice2);
                break;
            case R.id.buttonChoice3:
                clicked = (Button) findViewById(R.id.buttonChoice3);
                break;
            default:
                clicked = (Button) findViewById(R.id.buttonChoice4);
        }

        String textSelected = clicked.getText().toString();
    }
}