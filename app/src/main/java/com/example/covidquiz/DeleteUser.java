package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DeleteUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);
        setTitle("Delete User");
    }

    public void onDelete(View view) {
        //TODO: Get Android elements, delete from SQL!
        String userName = ((EditText) findViewById(R.id.editTextUser)).getText().toString();

        try {
            //COPY BELOW
            Connection server = DriverManager.getConnection(
                    "jdbc:mysql://34.122.65.95:3306/411Data",
                    "root",
                    "DataBased2");
            Statement statement = server.createStatement();
            //WATCH SPACES
            int r = statement.executeUpdate("DELETE " +
                    "FROM Users " + "WHERE Username = " +
                    "\""+ userName + "\"");

            // Notify whether deletion was successful
            if(r == 1) {
                Toast toast = Toast.makeText(getApplicationContext(), "User Successfully Deleted", Toast.LENGTH_LONG);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Operation Unsuccessful", Toast.LENGTH_LONG);
                toast.show();
            }

            // Clear the text field
            ((EditText) findViewById(R.id.editTextUser)).setText("");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}