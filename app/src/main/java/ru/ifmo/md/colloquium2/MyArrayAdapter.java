package ru.ifmo.md.colloquium2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Миша on 11.11.2014.
 */
public class MyArrayAdapter extends ArrayAdapter<Candidate> {
    public MyArrayAdapter(Context context, ArrayList<Candidate> names) {
        super(context, R.layout.list_item, names);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Candidate candidate = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, null);
        }
        ((TextView) convertView.findViewById(R.id.name))
                .setText(candidate.getName());
        ((TextView) convertView.findViewById(R.id.votes))
                .setText(candidate.getVotes() + " votes  ");
        ((TextView) convertView.findViewById(R.id.percentage))
                .setText(candidate.getPercentage() + "%");

        return convertView;
    }
}
