package com.example.covidquiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Entry2DAdapter extends ArrayAdapter<TwoEntry> {

    private Context context;
    private int resource;

    public Entry2DAdapter(@NonNull Context context, int resource, ArrayList<TwoEntry> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        String entryOne = getItem(position).entryOne;
        String entryTwo = getItem(position).entryTwo;

        TwoEntry temp = new TwoEntry(entryOne, entryTwo);
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(resource, parent, false);

        TextView entry1 = (TextView) view.findViewById(R.id.textViewEntry1);
        TextView entry2 = (TextView) view.findViewById(R.id.textViewEntry2);

        entry1.setText(entryOne);
        entry2.setText(entryTwo);

        return view;
    }
}
