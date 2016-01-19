package com.example.genji.am004_receiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MyBroadcastReceiver myReceiver;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myReceiver = new MyBroadcastReceiver();
        intentFilter = new IntentFilter("MY_ACTION_01");
    }

    public void onClick(View view) {
        Intent i = new Intent("MY_ACTION_01");
        i.putExtra("key", "*** My Action 01 ***");
        sendBroadcast(i);
    }

    public void onClickAgain(View view) {
        Intent i = new Intent("MY_ACTION_02");
        i.putExtra("key", "*** My Action 02 ***");
        sendBroadcast(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        //---register the receiver---
        registerReceiver(myReceiver, intentFilter);
    }
    @Override
    public void onPause() {
        super.onPause();
        //---unregister the receiver---
        unregisterReceiver(myReceiver);
    }
}
