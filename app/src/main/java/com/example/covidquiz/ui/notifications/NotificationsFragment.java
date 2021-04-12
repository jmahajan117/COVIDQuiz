package com.example.covidquiz.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.covidquiz.CreateRoom;
import com.example.covidquiz.CurrentRoom;
import com.example.covidquiz.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class NotificationsFragment extends Fragment {


    private ListView roomList;
    private Button createRoomButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        roomList = (ListView) root.findViewById(R.id.roomList);
        createRoomButton = (Button) root.findViewById(R.id.buttonCreateRoom);

        ArrayList<String> rooms = new ArrayList<>();
        String teamName = getActivity().getIntent().getStringExtra("Team Name");
        //TODO:List all of the rooms that a team is in
        try {
            //COPY BELOW
            Connection server = DriverManager.getConnection(
                    "jdbc:mysql://34.122.65.95:3306/411Data",
                    "root",
                    "DataBased2");
            Statement statement = server.createStatement();
            //WATCH SPACES
            ResultSet r = statement.executeQuery("SELECT RoomName " +
                    "FROM Rooms " + "WHERE TeamA = " +
                    "\""+ teamName + "\"" + " OR TeamB = " + "\""+ teamName + "\"");

            while(r.next()) {
                rooms.add(r.getString("RoomName"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayAdapter adapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, rooms);
        roomList.setAdapter(adapter);
        roomList.setOnItemClickListener((parent, view, position, id) -> {
            String roomName = rooms.get(position);
            Intent i = new Intent(getActivity(), CurrentRoom.class);
            i.putExtra("Room Name", roomName);
            i.putExtra("Team Name", teamName);
            startActivity(i);

        });



        createRoomButton.setOnClickListener(v -> {
            Intent i = new Intent(getActivity(), CreateRoom.class);
            i.putExtra("Team Name", teamName);
            startActivity(i);
        });

        return root;
    }
}