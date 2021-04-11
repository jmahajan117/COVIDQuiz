package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSignIn(View view) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection server = DriverManager.getConnection(
                    "jdbc:mysql://34.122.65.95:3306/newdata",
                    "root",
                    "DataBased2");
            Statement statement = server.createStatement();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}