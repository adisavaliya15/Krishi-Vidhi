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

public class pak_fruit extends MyBaseActivity {
    List<String> ChildList;
    Map<String, List<String>> ParentListItem;
    ExpandableListView expandableListView;

    List<String> ParentList = new ArrayList<>();

    {
        ParentList.add("દિવેલાં");
        ParentList.add("મગફળી");
        ParentList.add("તલ");
        ParentList.add("સોયાબીન");
    }

    String[] divela = {
            "જમીન અને આબોહવા", "બીજની પસંદગી", "વાવણી સમય અને અંતર", "સેન્દ્રિય ખાતર", "પિયત", "પાક સંરક્ષણ", "કાપણી"
    };

    String[] magfali = {
            "જમીન અને જમીનની તૈયારી", "બીજ માવજત", "પૂરક/જીવન રક્ષક પિયત", "જીવાત નિયંત્રણ", "કાપણી, ગ્રેડીંગ અને સંગ્રહ"
    };
    String[] tal = {
            "જમીનની પસંદગી અને તૈયારી", "વાવણીનો સમય અને અંતર", "નિંદામણ અને આંતરખેડ", "જીવાત નિયંત્રણ", "આંતરપાક તરીકે તલ"
    };
    String[] soyabean = {
            "જમીન", "જમીન અને આબોહવા", "વાવેતર સમય", "આંતરખેડ અને નિંદામણ", "કાપણી"
    };

    String[] defaultname = {
            "Android Rion"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_pak_fruit, null, false);
        drawer.addView(v, 0);
        setTitle("ફળો");
        ParentListItem = new LinkedHashMap<>();
        for (String HoldItem : ParentList) {
            if (HoldItem.equals("દિવેલાં")) {
                loadChild(divela);
            } else if (HoldItem.equals("મગફળી")) {
                loadChild(magfali);
            }else if (HoldItem.equals("તલ")) {
                loadChild(tal);
            }else if (HoldItem.equals("સોયાબીન")) {
                loadChild(soyabean);
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

                                                           Intent intent = new Intent(pak_fruit.this, pak_fruit_webview.class);
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