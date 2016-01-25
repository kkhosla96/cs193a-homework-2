package com.example.kush.assignmenttwo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> itemsList = new ArrayList<String>();
    private ArrayAdapter<String> toDoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            itemsList = savedInstanceState.getStringArrayList("copyKey");
        }
        else {
            itemsList = new ArrayList<String>();
        }
        setContentView(R.layout.activity_main);
        ListView toDoView = (ListView) findViewById(R.id.todolist);
        toDoListAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemsList);
        toDoView.setAdapter(toDoListAdapter);
        toDoView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> toDoView, View row, int index, long rowID) {
                itemsList.remove(index);
                toDoListAdapter.notifyDataSetChanged();
                return true;
            }
        });
        };

    public void addItem(View view) {
        EditText textToAdd = (EditText) findViewById(R.id.addText);
        String actualText = textToAdd.getText().toString();
        if (actualText.length() != 0) {
            itemsList.add(actualText);
            toDoListAdapter.notifyDataSetChanged();
            textToAdd.setText("");
        }
    }

    public void onSaveInstanceState(Bundle savedState) {

        super.onSaveInstanceState(savedState);
        ArrayList<String> copy = new ArrayList<>();
        int count = toDoListAdapter.getCount();
        for (int i = 0; i < toDoListAdapter.getCount(); i++){
            String item = toDoListAdapter.getItem(i);
            copy.add(i, item);
        }
        savedState.putStringArrayList("copyKey", copy);
    }
}
