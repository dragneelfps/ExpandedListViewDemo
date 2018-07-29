package com.nooblabs.srawa.expandedlistviewdemo;

public class Item {

    String group;
    String data;
    boolean selected = false;

    public Item(String group, String data, boolean selected) {
        this.group = group;
        this.data = data;
        this.selected = selected;
    }

    public Item(String data, boolean selected) {
        this(null,data,selected);
    }

    public Item(String data) {
        this(null,data,false);
    }

    public Item(String group, String data) {
        this(group,data,false);
    }
}
