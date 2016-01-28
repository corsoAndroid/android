package com.example.genji.am010_fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a new Fragment to be placed in the activity layout
        FragmentOne fr = new FragmentOne();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fr);
        fragmentTransaction.commit();


    }

    public void selectFrag(View view) {
        Fragment fr;

        if(view == findViewById(R.id.button2)) {
            fr = new FragmentTwo();

        } else {
            fr = new FragmentOne();
        }


        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fr);
        fragmentTransaction.commit();

    }
}
