package com.example.finale;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class pak_vegetable extends MyBaseActivity {
    List<String> ChildList;
    Map<String, List<String>> ParentListItem;
    ExpandableListView expandableListView;

    List<String> ParentList = new ArrayList<>();
    {
        ParentList.add("મરચા");

    }

    String[] marcha = {
            "જમીન અને જમીનની તૈયારી", "સુધારેલી જાતો", "ખાતર", "જીવાતો અને રોગોનું નિયંત્રણ", "પિયત અને કાપણી"
    };

    String[] defaultname = {
            "Android Rion"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_pak_vegetable, null, false);
        drawer.addView(v, 0);
        setTitle("શાક્ભાજી");

        ParentListItem = new LinkedHashMap<>();
        for (String HoldItem : ParentList) {
            if (HoldItem.equals("મરચા")) {
                loadChild(marcha);
            } else
                loadChild(defaultname);

            ParentListItem.put(HoldItem, ChildList);
        }

        expandableListView = findViewById(R.id.expandListView);
        final ExpandableListAdapter expandableListAdapter = new Expandable_List_Adapter(this, ParentList, ParentListItem);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                                                       @Override
                                                       public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                                                           Intent intent = new Intent(pak_vegetable.this, pak_vegetable_webview.class);
                                                           intent.putExtra("key", groupPosition);
                                                           intent.putExtra("child", childPosition);
                                                           startActivity(intent);
                                                           return false;
                                                       }
                                                   }
        );
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousItem = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousItem )
                    expandableListView.collapseGroup(previousItem );
                previousItem = groupPosition;
            }
        });
    }
    private void loadChild(String[] ParentElementname) {

        ChildList = new ArrayList<>();
        Collections.addAll(ChildList, ParentElementname);
    }
}