package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CurrentRoom extends AppCompatActivity {

    private String roomName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_room);
        roomName = this.getIntent().getStringExtra("Room Name");
        TextView name = (TextView) findViewById(R.id.textViewRoomName);
        name.setText(roomName);

        //TODO: Display the wins of A, wins of B
    }

    public void onAnswerQuestion(View view) {

    }

    public void onDeleteRoom(View view) {
        //TODO: Delete the current room
    }

}