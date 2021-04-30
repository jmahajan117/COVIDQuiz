package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class CurrentRoom extends AppCompatActivity {

    private String roomName;
    private String teamName;
    private KonfettiView konfettiView;
    boolean isTeamA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_room);
        roomName = this.getIntent().getStringExtra("Room Name");
        teamName = this.getIntent().getStringExtra("Team Name");
        konfettiView = findViewById(R.id.viewKonfetti);
        isTeamA = false;


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
            ResultSet r = statement.executeQuery("SELECT TeamA, TeamB, WinsA, WinsB " +
                    "FROM Rooms " + "WHERE RoomName = " +
                    "\""+ roomName + "\"");

            while(r.next()) {
                ((TextView) findViewById(R.id.textViewTeamAWins)).setText(r.getString("TeamA") + " Wins: \n" + r.getString("WinsA"));
                ((TextView) findViewById(R.id.textViewTeamBWins)).setText(r.getString("TeamB") + " Wins: \n" + r.getString("WinsB"));
                if (r.getString("TeamA") == teamName)
                    isTeamA = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAnswerQuestion(View view) {
        Intent i = new Intent(this, AnswerQuestion.class);
        i.putExtra("Room Name", roomName);
        i.putExtra("Team Name", teamName);
        i.putExtra("is Team A", isTeamA);
        startActivityForResult(i, 150);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 150) {
            konfettiView.build()
                    .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                    .setDirection(0.0, 359.0)
                    .setSpeed(1f, 5f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(2000L)
                    .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                    .addSizes(new Size(12, 5f))
                    .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                    .streamFor(300, 5000L);
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                recreate();
            }, 5000);
        }
        else {
            recreate();
        }
    }


    /*
    public void onRestart() {
        super.onRestart();
        boolean correct = this.getIntent().getBooleanExtra("Correct", false);
        if (correct) {
            konfettiView.build()
                    .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                    .setDirection(0.0, 359.0)
                    .setSpeed(1f, 5f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(2000L)
                    .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                    .addSizes(new Size(12, 5f))
                    .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                    .streamFor(300, 5000L);
        }
    }
    */

}