package com.example.finale;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Expandable_List_Adapter extends BaseExpandableListAdapter {

    private Activity context;
    private Map<String, List<String>> ParentListItems;
    private List<String> Items;

    Expandable_List_Adapter(Activity context, List<String> Items, Map<String, List<String>> ParentListItems)
    {
        this.context = context;
        this.ParentListItems = ParentListItems;
        this.Items =Items;
    }
    @Override
    public int getGroupCount() {
        return Items.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return Items.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String string = (String) getGroup(groupPosition);
        if (convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert layoutInflater != null;
            convertView = layoutInflater.inflate(R.layout.parent_item,null);
        }

        TextView item = convertView.findViewById(R.id.textParent);
       /* ImageView imgparent = convertView.findViewById(R.id.imgParent);
        imgparent.setImageResource();*/
        item.setText(string);
        return convertView;
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        return Objects.requireNonNull(ParentListItems.get(Items.get(groupPosition))).size();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return Objects.requireNonNull(ParentListItems.get(Items.get(groupPosition))).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childName = (String) getChild(groupPosition,childPosition);
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null){
            convertView = inflater.inflate(R.layout.child_item,null);
        }

        TextView item = convertView.findViewById(R.id.textChild);

        item.setText(childName);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

}
