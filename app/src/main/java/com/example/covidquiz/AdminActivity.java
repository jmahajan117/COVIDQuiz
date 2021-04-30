package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void onActivate(View view) {

        try {
            Connection server = DriverManager.getConnection(
                    "jdbc:mysql://34.122.65.95:3306/411Data",
                    "root",
                    "DataBased2");
            CallableStatement statement = server.prepareCall("{call Switchboard(1, ?, ?)}");

            statement.registerOutParameter(2, Types.INTEGER);
            statement.setString(1, "team1");
            statement.execute();
            Toast.makeText(this, "Anti Cheat Enabled", Toast.LENGTH_LONG).show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}