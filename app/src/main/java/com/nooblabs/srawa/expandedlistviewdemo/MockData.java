package com.nooblabs.srawa.expandedlistviewdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public final class MockData {

    public static ArrayList<String> groupHeaders = new ArrayList<>(
            Arrays.asList("Ubisoft","EA","From Software")
    );

    public static HashMap<String,ArrayList<Item>> groupItems;

    public static void loadMockData(){
        groupItems = new HashMap<>();
        for(String group: groupHeaders){
            final ArrayList<Item> items = new ArrayList<>();
            for(int i=1;i<5;i++) {
                items.add(new Item(group, group + " " + i));
            }
            groupItems.put(group, items);
        }
    }



}
