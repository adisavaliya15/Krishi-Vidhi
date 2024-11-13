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

public class pak_anaj extends MyBaseActivity {
    List<String> ChildList;
    Map<String, List<String>> ParentListItem;
    ExpandableListView expandableListView;

    List<String> ParentList = new ArrayList<>();
    {
        ParentList.add("ઘઉ");
        ParentList.add("મકાઈ");
        ParentList.add("બાજરી");
        ParentList.add("જુવાર");
        ParentList.add("ડાંગર");
    }

    String[] ghav = {
            "ઘઉની અગત્યતા", "અનુકુળ જમીન અને આબોહવાા", "વિવિધ જાતની પસંદગી", "વાવણી સમય અને પધ્ધતિ", "ખાતર વ્યવસ્થાપન", "પિયત વ્યવસ્થાપન", "નીદણ વ્યવસ્થાપન", "વિવિધ જાતની પસંદગી", "કાપણી અને સંગ્રહ"
    };

    String[] makai = {
            "જમીન અને જમીનની તૈયારી", "બીજની પસંદગી", "પારવણી", "પાક સંરક્ષણ", "કાપણી અને ઉત્પાદન"
    };
    String[] bajari = {
            "જમીન અને જમીનની તૈયારી", "બીજનો દર અને વાવેતર અંતર", "જાતો અને તેની પસંદગી", "ખાતર", "જીવાત  અને રોગ નિયંત્રણ", "કાપણી"
    };
    String[] juver = {
            "જમીન અને જમીનની તૈયારી", "પિયતની વ્યવસ્થા", "નિંદામણ અને ખાતર વ્યવસ્થાપન", "વાવેતર પધ્ધતિ", "જાતોની માહિતી", "અનાજની સાચવણી"
    };
    String[] rice = {
            "જમીન અને જમીનની તૈયારી", "જાતની પસંદગી", "બીજ દર અને માવજત", "વાવણી અને ફેરરોપણી", "ખાતર વ્યવસ્થાપન", "પિયત વ્યવસ્થાપન", "રોગ અને જીવાત નિયંત્રણ", "ડાંગરની ચાર સુત્રી (સીરા) અને “શ્રી”(એસઆરઆઇ) ખેતી પધ્ધતિ", "પાકની કાપણી"
    };

    String[] defaultname = {
            "Android Rion"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_pak_anaj, null, false);
        drawer.addView(v, 0);
        setTitle("અનાજ");
        ParentListItem = new LinkedHashMap<>();
        for (String HoldItem : ParentList) {
            if (HoldItem.equals("ઘઉ")) {
                loadChild(ghav);
            } else if (HoldItem.equals("મકાઈ")) {
                loadChild(makai);
            }else if (HoldItem.equals("બાજરી")) {
                loadChild(bajari);
            }else if (HoldItem.equals("જુવાર")) {
                loadChild(juver);
            }else if (HoldItem.equals("ડાંગર")) {
                loadChild(rice);
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

                                                           Intent intent = new Intent(pak_anaj.this, pak_anaj_webview.class);
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