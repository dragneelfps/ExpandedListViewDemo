package com.nooblabs.srawa.expandedlistviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<String> groupHeaders;
    public static HashMap<String,ArrayList<Item>> groupItems;

    private ExpandableListView listView;
    private CustomListViewAdapter adapater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MockData.loadMockData();
        groupHeaders = MockData.groupHeaders;
        groupItems = MockData.groupItems;

        listView = findViewById(R.id.list_view);

        adapater = new CustomListViewAdapter(this, groupHeaders, groupItems);
        listView.setAdapter(adapater);
    }
}
