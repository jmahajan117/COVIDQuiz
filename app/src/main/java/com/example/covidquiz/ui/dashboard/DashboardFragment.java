package com.example.covidquiz.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.covidquiz.AddQuestion;
import com.example.covidquiz.CreateRoom;
import com.example.covidquiz.R;
import com.example.covidquiz.ReadQuestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private ListView questionList;
    private Button addQuestionButton;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        getActivity().setTitle("Teams");
        questionList = (ListView) root.findViewById(R.id.questionsList);
        addQuestionButton = (Button) root.findViewById(R.id.buttonAddQuestion);

        addQuestionButton.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), AddQuestion.class);
            startActivity(i);
        });


        //Get Questions from SQL
        ArrayList<String> questions = new ArrayList<>();
        try {
            //COPY BELOW
            Connection server = DriverManager.getConnection(
                    "jdbc:mysql://34.122.65.95:3306/411Data",
                    "root",
                    "DataBased2");
            Statement statement = server.createStatement();
            //WATCH SPACES
            ResultSet r = statement.executeQuery("SELECT * FROM Questions LIMIT 15");

            //Make sure you put this loop
            while (r.next()) {
                questions.add(r.getString("Question"));
            }

            ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_expandable_list_item_1, questions);
            questionList.setAdapter(adapter);

            questionList.setOnItemClickListener((parent, view, position, id) -> {
                Intent i = new Intent(getActivity(), ReadQuestion.class);
                i.putExtra("Question", questions.get(position));
                startActivity(i);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }


        return root;
    }
}