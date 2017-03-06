package com.example.genji.am013_listactivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

// http://developer.android.com/reference/android/app/ListActivity.html

public class MainActivity extends ListActivity {

    String[] products = {
            "gioppini",
            "jambonetti",
            "patatine sfizione",
            "tarallini",
            "gallette",
            "frollini plus",
            "cioccolini",
            "secchini",
            "grissinini",
            "patasplash",
            "majopatas",
            "crocchette al sesamo",
            "crocchette alla pancetta",
            "biscotti al miglio e avena"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* SIMPLE ---List View---

        setListAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, products));
        */


        ListView lstView = getListView();
        // lstView.setChoiceMode(ListView.CHOICE_MODE_NONE);
        lstView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // lstView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        // you can select item
        // lstView.setTextFilterEnabled(true);
        /*setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_checked, products)); */
        setListAdapter(new ArrayAdapter<String>(this,
                android.R., products));


    }

    public void onListItemClick(ListView parent, View v,
                                int position, long id) {
        /* SIMPLE
        Toast.makeText(this, "You have selected " + products[position],
                Toast.LENGTH_SHORT).show();
        */
        CheckedTextView item = (CheckedTextView) v;
        Toast.makeText(this, products[position] + " checked : " +
                !item.isChecked(), Toast.LENGTH_SHORT).show();
    }
}
