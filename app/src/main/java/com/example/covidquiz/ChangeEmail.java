package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ChangeEmail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);

    }

    public void onButtonClick(View view) {

        String userName = ((EditText) findViewById(R.id.username_textfield)).getText().toString();
        String email = ((EditText) findViewById(R.id.email_textfield)).getText().toString();

        //TODO: Update Email
        try {
            //COPY BELOW
            Connection server = DriverManager.getConnection(
                    "jdbc:mysql://34.122.65.95:3306/411Data",
                    "root",
                    "DataBased2");
            Statement statement = server.createStatement();
            //WATCH SPACES
            int r = statement.executeUpdate("UPDATE Users " +
                    "SET Email = \"" + email + "\" " +
                    "WHERE Username = \"" + userName + "\"");

        } catch (Exception e) {
            e.printStackTrace();
        }

        finishActivity(0);
    }

}