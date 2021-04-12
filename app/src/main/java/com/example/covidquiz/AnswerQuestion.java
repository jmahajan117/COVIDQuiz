package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AnswerQuestion extends AppCompatActivity {

    private String roomName;
    private String teamName;
    private String correctAnswer;
    private String questionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_question);
        roomName = this.getIntent().getStringExtra("Room Name");
        teamName = this.getIntent().getStringExtra("Team Name");
        correctAnswer = "";
        questionName = "";
        //TODO: Get a question, display it and it's choices
        try {
            //COPY BELOW
            Connection server = DriverManager.getConnection(
                    "jdbc:mysql://34.122.65.95:3306/411Data",
                    "root",
                    "DataBased2");
            Statement statement = server.createStatement();
            //WATCH SPACES
            ResultSet r = statement.executeQuery("SELECT q.Question " +
                    "FROM Rooms r JOIN Questions q ON q.Topic = r.RoomTopic " +
                    "WHERE r.RoomName = \"" + roomName + "\" AND q.Topic = r.RoomTopic " +
                    "ORDER BY RAND() " +
                    "LIMIT 1");

            r.next();
            questionName = r.getString("Question");
            ((TextView) findViewById(R.id.textViewQuestion)).setText(r.getString("Question"));
            ResultSet r2 = statement.executeQuery("SELECT * " +
                        "FROM Answers " + "WHERE QuestionName = \"" + r.getString("Question") + "\"");

            ArrayList<TextView> buttons = new ArrayList<>();
            buttons.add((TextView) findViewById(R.id.buttonChoice1));
            buttons.add((TextView) findViewById(R.id.buttonChoice2));
            buttons.add((TextView) findViewById(R.id.buttonChoice3));
            buttons.add((TextView) findViewById(R.id.buttonChoice4));
            int i = 0;

            while(r2.next()) {
                if(r2.getString("IsRight").equals("1")) {
                    correctAnswer = r2.getString("Answer");
                }
                buttons.get(i).setText(r2.getString("Answer"));
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        boolean userCorrect = false;
        if(textSelected.equals(correctAnswer)) {
            userCorrect = true;
        }

        // Notify whether answer is correct or not
        if(userCorrect) {
            Toast toast = Toast.makeText(getApplicationContext(), "Answer Is Correct", Toast.LENGTH_LONG);
            toast.show();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Answer Is Incorrect", Toast.LENGTH_LONG);
            toast.show();
        }

        // Update Room
        try {
            //COPY BELOW
            Connection server = DriverManager.getConnection(
                    "jdbc:mysql://34.122.65.95:3306/411Data",
                    "root",
                    "DataBased2");
            Statement statement = server.createStatement();
            //WATCH SPACES
            int r = statement.executeUpdate("UPDATE Rooms " +
                    "SET WinsA = WinsA + 1 " + "WHERE TeamA = \"" + teamName + "\" AND RoomName = \"" + roomName + "\"");

            int r2 = statement.executeUpdate("UPDATE Rooms " +
                    "SET WinsB = WinsB + 1 " + "WHERE TeamB = \"" + teamName + "\" AND RoomName = \"" + roomName + "\"");

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Update Question
        try {
            //COPY BELOW
            Connection server = DriverManager.getConnection(
                    "jdbc:mysql://34.122.65.95:3306/411Data",
                    "root",
                    "DataBased2");
            Statement statement = server.createStatement();
            //WATCH SPACES
            int r = statement.executeUpdate("UPDATE Questions " +
                    "SET Accuracy = ((Accuracy * Attempts) + 1) / (Attempts + 1), Attempts = Attempts + 1 " +
                    "WHERE Question = \"" + questionName + "\"");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}