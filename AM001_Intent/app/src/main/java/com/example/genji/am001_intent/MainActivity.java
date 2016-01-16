package com.example.genji.am001_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* I STEP
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Button was clicked!",
                        Toast.LENGTH_SHORT).show();
            }
        });
        */

        /*
        IN GNERAL

        Intent i = new Intent();
        i.setAction("com.example.genji.Activity2");
        startActivity(i);

        FROM THIS APP

        Intent i = new Intent(MainActivity.this, ActivityTwo.class);
        startActivity(i);
        */


        //---Button view---
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ActivityTwo.class);
                startActivity(i);
            }
        });


    }
}
