package com.example.covidquiz;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.net.Inet4Address;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link navigate_stats#newInstance} factory method to
 * create an instance of this fragment.
 */
public class navigate_stats extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public navigate_stats() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment navigate_stats.
     */
    // TODO: Rename and change types and number of parameters
    public static navigate_stats newInstance(String param1, String param2) {
        navigate_stats fragment = new navigate_stats();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_navigate_stats, container, false);
        Button QA = (Button) root.findViewById(R.id.buttonQAcc);
        Button topicQA = (Button) root.findViewById(R.id.buttonTopicQA);
        Button teamWithWins = (Button) root.findViewById(R.id.buttonTeamsWins);
        Button teamTopic = (Button) root.findViewById(R.id.buttomTeamTopic);
        //Button lowest = (Button) root.findViewById(R.id.buttonLowAcc);

        QA.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), QuestionAccuracy.class);
            startActivity(i);
        });

        topicQA.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), TopicQA.class);
            startActivity(i);
        });

        teamWithWins.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), TeamWins.class);
            startActivity(i);

        });

        teamTopic.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), TeamTopic.class);
            startActivity(i);

        });

        /*
        lowest.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), LowestAccuracy.class);
            startActivity(i);
        });
         */

        return root;
    }
}