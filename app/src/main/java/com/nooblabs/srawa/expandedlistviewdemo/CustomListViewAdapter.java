package com.nooblabs.srawa.expandedlistviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    public ArrayList<String> groupHeaders;
    public HashMap<String,ArrayList<Item>> groupItems;

    public CustomListViewAdapter(Context context, ArrayList<String> groupHeaders, HashMap<String,ArrayList<Item>> groupItems) {
        this.context = context;
        this.groupHeaders = groupHeaders;
        this.groupItems = groupItems;
    }

    @Override
    public int getGroupCount() {
        return groupHeaders.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groupItems.get(groupHeaders.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupHeaders.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groupItems.get(groupHeaders.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String groupTitle = (String) getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group_header, null);
        }
        TextView groupTitleTextView = convertView.findViewById(R.id.group_title);
        CheckBox checkBox = convertView.findViewById(R.id.group_header_checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkAll(groupPosition,isChecked);
            }
        });
        groupTitleTextView.setText(groupTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Item childItem = (Item) getChild(groupPosition,childPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group_item, null);
        }
        TextView itemTextView = convertView.findViewById(R.id.item_title);
        CheckBox checkBox = convertView.findViewById(R.id.group_item_checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setItemChecked(childItem,isChecked);
            }
        });
        checkBox.setChecked(childItem.selected);
        itemTextView.setText(childItem.data);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    void checkAll(int groupPosition, boolean isChecked){
        for(Item item: groupItems.get(groupHeaders.get(groupPosition))){
            setItemChecked(item,isChecked);
        }
    }

    void setItemChecked(Item item, boolean isChecked){
        item.selected = isChecked;
        notifyDataSetChanged();
    }
}
