package com.example.genji.am002_intent;

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

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ActivityTwo.class);
                // use putExtra() to add new key/value pairs to intent i ---
                i.putExtra("str1", "Message Example");
                i.putExtra("n1", 25);

                // use a Bundle object to add new key/values
                Bundle extras = new Bundle();
                extras.putString("str2", "This is another string");
                extras.putInt("n2", 666);
                // attach the Bundle object to the Intent object
                i.putExtras(extras);

                // start the activity to get a result back
                startActivityForResult(i, 1);
            }
        });
    }

    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        // check if the request code is 1 (we came from 1 ... )
        if (requestCode == 1) {
            //---if the result is OK---
        }
        if (resultCode == RESULT_OK) {
            // data passed in using getStringExtra() via Intent data
            Toast.makeText(this, data.getStringExtra("rok"),
                    Toast.LENGTH_SHORT).show();
        }
    }


}
