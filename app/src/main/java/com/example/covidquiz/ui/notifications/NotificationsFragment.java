package com.example.covidquiz.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.covidquiz.R;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {


    private ListView roomList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        roomList = (ListView) root.findViewById(R.id.roomList);
        ArrayList<String> rooms = new ArrayList<>();
        String teamName = getActivity().getIntent().getStringExtra("Team Name");
        //TODO:List all of the rooms that a team is in


        ArrayAdapter adapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, rooms);
        roomList.setAdapter(adapter);


        return root;
    }
}