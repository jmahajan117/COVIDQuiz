package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

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
        try {
            //COPY BELOW
            Connection server = DriverManager.getConnection(
                    "jdbc:mysql://34.122.65.95:3306/411Data",
                    "root",
                    "DataBased2");
            Statement statement = server.createStatement();
            //WATCH SPACES
            int r = statement.executeUpdate("DELETE " +
                    "FROM Questions " + "WHERE Question = " +
                    "\""+ question + "\"");

            // Notify whether deletion was successful
            if (r == 1) {
                Toast toast = Toast.makeText(getApplicationContext(), "Question Successfully Deleted", Toast.LENGTH_LONG);
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