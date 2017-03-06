package com.example.genji.am013_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    private String[] products = {
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

        mListView = (ListView) findViewById(R.id.my_list_view);

        // ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, products);
        // lstView.setChoiceMode(ListView.CHOICE_MODE_NONE);
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // lstView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_checked, products);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Toast.makeText(MainActivity.this,"You selected : " + products[position], Toast.LENGTH_SHORT).show();
                CheckedTextView item = (CheckedTextView) view;
                Toast.makeText(MainActivity.this, products[position] + " checked : " +
                        item.isChecked(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
