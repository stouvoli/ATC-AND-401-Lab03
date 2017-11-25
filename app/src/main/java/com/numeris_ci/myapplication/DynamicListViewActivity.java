package com.numeris_ci.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class DynamicListViewActivity extends AppCompatActivity {
    private EditText item;
    private Button add;
    private ListView dynamicListView;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_list_view);

        //Get references to UI widgets
        item = (EditText) findViewById(R.id.itemEditText);
        add = (Button) findViewById(R.id.addIemButton);
        dynamicListView = (ListView) findViewById(R.id.itemListView);

        //Initialize the list
        list = new ArrayList<>();
        //Initialize the ArrayAdapter
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        //Setting the adapter to the listview
        dynamicListView.setAdapter(adapter);

        //add item to the listview after click on button add
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toDoItem = item.getText().toString();
                if (toDoItem.length() > 0) {
                    //Add editext value to the list
                    list.add(0,toDoItem);
                    //Refresh the listview
                    adapter.notifyDataSetChanged();
                    //Clear the editext
                    item.setText("");
                    //Scroll to the bottom of the list
                    //dynamicListView.scrollTo(0,dynamicListView.getBottom());
                }
            }
        });

        //delete item on log click on the list
        dynamicListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Remove the item from the list
                list.remove(position);

                //Refresh the listview
                adapter.notifyDataSetChanged();

                return true;
            }
        });

    }
}
