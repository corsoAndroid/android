package com.example.genji.am022_gridview;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by genji on 2/15/16.
 */
public class MyOnClickListener implements View.OnClickListener {

    private int position;

    public MyOnClickListener(int position) {
        this.position = position;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), position + "",
                Toast.LENGTH_SHORT).show();
        Button b = (Button)v;
        b.setTextColor(ContextCompat.getColor(v.getContext(), R.color.colorMyGrey));
    }
}
