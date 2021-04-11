package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void onSignIn(View view) {
        String userName = ((EditText) findViewById(R.id.userNameText)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordText)).getText().toString();

        try {
            //DONT COPY THIS CLASS SHIT
            Class.forName("com.mysql.jdbc.Driver");
            //COPY BELOW
            Connection server = DriverManager.getConnection(
                    "jdbc:mysql://34.122.65.95:3306/411Data",
                    "root",
                    "DataBased2");
            Statement statement = server.createStatement();
            //WATCH SPACES
            ResultSet r = statement.executeQuery("SELECT u.Username, u.Password, u.TName " +
                    "FROM Users u " + "WHERE u.Username = " +
                    "\""+ userName + "\""+  " AND u.Password = " +  "\""+ password + "\"");

            //Make sure you put this loop
            while (r.next()) {
                if (r.getString("Username").equals(userName) && r.getString("Password").equals(password)) {
                    Intent nextScreen = new Intent(this, Lobby.class);
                    nextScreen.putExtra("Team Name", r.getString("TName"));
                    nextScreen.putExtra("User", userName);
                    startActivity(nextScreen);
                    //DO WHATEVER HERE
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCreateUser(View view) {
        Intent i = new Intent(this, CreateUser.class);
        startActivity(i);
    }

    public void onDeleteUser(View view) {
        Intent i = new Intent(this, DeleteUser.class);
        startActivity(i);
    }
}