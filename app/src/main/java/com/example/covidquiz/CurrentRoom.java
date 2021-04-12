package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
        try {
            //COPY BELOW
            Connection server = DriverManager.getConnection(
                    "jdbc:mysql://34.122.65.95:3306/411Data",
                    "root",
                    "DataBased2");
            Statement statement = server.createStatement();
            //WATCH SPACES
            ResultSet r = statement.executeQuery("SELECT WinsA, WinsB " +
                    "FROM Rooms " + "WHERE RoomName = " +
                    "\""+ roomName + "\"");

            while(r.next()) {
                ((TextView) findViewById(R.id.textViewTeamAWins)).setText(r.getString("WinsA"));
                ((TextView) findViewById(R.id.textViewTeamBWins)).setText(r.getString("WinsB"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAnswerQuestion(View view) {

    }

    public void onDeleteRoom(View view) {
        //TODO: Delete the current room
        try {
            //COPY BELOW
            Connection server = DriverManager.getConnection(
                    "jdbc:mysql://34.122.65.95:3306/411Data",
                    "root",
                    "DataBased2");
            Statement statement = server.createStatement();
            //WATCH SPACES
            int r = statement.executeUpdate("DELETE " +
                    "FROM Rooms " + "WHERE RoomName = " +
                    "\""+ roomName + "\"");

            // Notify whether deletion was successful
            if(r == 1) {
                Toast toast = Toast.makeText(getApplicationContext(), "Room Successfully Deleted", Toast.LENGTH_LONG);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Operation Unsuccessful", Toast.LENGTH_LONG);
                toast.show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
    }

}