package ru.ifmo.md.colloquium2;

import android.app.ListActivity;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ListActivity {
    EditText input;
    ArrayAdapter<Candidate> adapter;
    ArrayList<Candidate> candidates;
    boolean vote = false;
    int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        candidates = new ArrayList<Candidate>();
        adapter = new MyArrayAdapter(getApplicationContext(), candidates);
        input = (EditText) findViewById(R.id.editTextName);
        setListAdapter(adapter);

        input.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if (!vote) {
                        if (input.getText().toString().isEmpty()) {
                            showToast(R.string.empty_name, Toast.LENGTH_LONG);
                        } else {
                            updateList(input.getText().toString());
                        }
                    } else {
                        showToast(R.string.vote_in_progress, Toast.LENGTH_LONG);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void updateList(String name) {
        candidates.add(new Candidate(name));
        adapter.notifyDataSetChanged();
        input.setText("");

        final ListView myListView = getListView();
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (vote) {
                    Candidate candidate = candidates.get(position);
                    candidate.increaseVotes();
                    sum++;
                    candidates.set(position, candidate);
                    for (int i = 0; i < candidates.size(); i++) {
                        Candidate tmp = candidates.get(i);
                        tmp.setPercentage(100 * Integer.parseInt(tmp.getVotes()) / sum);
                        candidates.set(i, tmp);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void showToast(int message, int time) {
        Toast toast = Toast.makeText(getApplicationContext(), message, time);
        toast.show();
    }

    public void onStartClick(View view) {
        vote = true;
    }

    public void onAddClick(View view) {
        if (!vote) {
            if (input.getText().toString().isEmpty()) {
                showToast(R.string.empty_name, Toast.LENGTH_LONG);
            } else {
                updateList(input.getText().toString());
            }
        } else {
            showToast(R.string.vote_in_progress, Toast.LENGTH_LONG);
        }
    }

    public void onResetClick(View view) {
        candidates.clear();
        adapter.notifyDataSetChanged();
        vote = false;
        sum = 0;
    }
}
