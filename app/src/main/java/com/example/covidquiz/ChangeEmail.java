package com.example.covidquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ChangeEmail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);

    }

    public void onButtonClick(View view) {

        String userName = ((EditText) findViewById(R.id.username_textfield)).getText().toString();
        String password = ((EditText) findViewById(R.id.password_textfield)).getText().toString();

        //TODO: Update Email

        finishActivity(0);
    }

}