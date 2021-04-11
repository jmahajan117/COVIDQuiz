package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
        try {
            //COPY BELOW
            Connection server = DriverManager.getConnection(
                    "jdbc:mysql://34.122.65.95:3306/411Data",
                    "root",
                    "DataBased2");
            Statement statement = server.createStatement();
            //WATCH SPACES
            ResultSet r = statement.executeQuery("SELECT * " +
                    "FROM Topics");

            while(r.next()) {
                topics.add(r.getString("TopicName"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, topics);
        spin.setAdapter(adapter);


    }

    public void onClickAdd(View view) {
        //TODO: Assuming they write a topic, add the question into SQL
        String questionName = ((EditText) findViewById(R.id.editTextQuestion)).getText().toString();
        String topicName = ((Spinner) findViewById(R.id.topicSpinner)).getSelectedItem().toString();

        try {
            //COPY BELOW
            Connection server = DriverManager.getConnection(
                    "jdbc:mysql://34.122.65.95:3306/411Data",
                    "root",
                    "DataBased2");
            Statement statement = server.createStatement();
            //WATCH SPACES
            int r = statement.executeUpdate("INSERT INTO Questions(Question, Topic, Accuracy, Attempts, Flag) " +
                    "VALUES(\"" + questionName + "\", \"" + topicName + "\", 0, 0, 0)");

            // Notify whether addition was successful
            if(r == 1) {
                Toast toast = Toast.makeText(getApplicationContext(), "Question Successfully Added", Toast.LENGTH_LONG);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Operation Unsuccessful", Toast.LENGTH_LONG);
                toast.show();
            }

            // Clear the text field
            ((EditText) findViewById(R.id.editTextQuestion)).setText("");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}