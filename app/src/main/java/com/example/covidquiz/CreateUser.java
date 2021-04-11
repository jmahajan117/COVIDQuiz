package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        setTitle("Create New User");
    }

    public void onCreateUser(View view) {
        // TODO: Get the typed info and insert into SQL, assume Team is NULL for now
        String userName = ((EditText) findViewById(R.id.editTextUser)).getText().toString();
        String email = ((EditText) findViewById(R.id.editTextEmail)).getText().toString();
        String password = ((EditText) findViewById(R.id.editTextPass)).getText().toString();

        try {
            Connection server = DriverManager.getConnection(
                    "jdbc:mysql://34.122.65.95:3306/411Data",
                    "root",
                    "DataBased2");
            Statement statement = server.createStatement();

            // Query
            int r = statement.executeUpdate("INSERT INTO Users(Username, Email, Password, TName) " +
                    "VALUES(" + "\"" + userName + "\", \"" + email + "\", \"" + password + "\", NULL" + ")");

            if (r == 1) {
                Toast toast = Toast.makeText(getApplicationContext(), "User Successfully Added", Toast.LENGTH_LONG);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Operation Unsuccessful", Toast.LENGTH_LONG);
                toast.show();
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}