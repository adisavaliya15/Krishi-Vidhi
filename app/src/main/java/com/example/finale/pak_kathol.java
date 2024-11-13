package com.example.finale;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class pak_kathol extends MyBaseActivity {
    List<String> ChildList;
    Map<String, List<String>> ParentListItem;
    ExpandableListView expandableListView;

    List<String> ParentList = new ArrayList<>();

    {
        ParentList.add("તુવેર");
        ParentList.add("અડદ");
        ParentList.add("ચણા");
        ParentList.add("મગ");
    }

    String[] tuver = {
            "તુવેર પાકની અગત્યતા", "જમીન અને આબોહવા", "વાવણી સમય", "બીજ અને જાતની પસંદગી", "પિયત વ્યવસ્થા", "પાક સંરક્ષણ", "કાપણી તેમજ દાણાં તૈયાર કરવા"
    };

    String[] adad = {
            "જમીનની તૈયારી", "બિયારણનો દર અને સુધારેલી જાત", "વાવણી અંતર", "પાક સંરક્ષણ", "કાપણી"
    };
    String[] chana = {
            "ચણા પાકની અગત્યતા", "જમીનની તૈયારી", "બીજ દર અને માવજત", "પિયત", "પાક સંરક્ષણ", "કાપણી અને સંગ્રહ"
    };
    String[] mag = {
            "જમીનની પ્રાથમિક તૈયારી", "સુધારેલી જાતો", "વાવણી અંતર, બીજનો દર અને વાવણીની રીત", "પાક સંરક્ષણ", "કાપણી અને સંગ્રહ"
    };

    String[] defaultname = {
            "Android Rion"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_pak_kathol, null, false);
        drawer.addView(v, 0);
        setTitle("કઠોળ");

        ParentListItem = new LinkedHashMap<>();
        for (String HoldItem : ParentList) {
            if (HoldItem.equals("તુવેર")) {
                loadChild(tuver);
            } else if (HoldItem.equals("અડદ")) {
                loadChild(adad);
            }else if (HoldItem.equals("ચણા")) {
                loadChild(chana);
            }else if (HoldItem.equals("મગ")) {
                loadChild(mag);
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

                                                           Intent intent = new Intent(pak_kathol.this, pak_kathol_webview.class);
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