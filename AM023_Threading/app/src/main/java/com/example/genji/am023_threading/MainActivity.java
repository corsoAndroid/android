package com.example.genji.am023_threading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

// READ: http://developer.android.com/guide/components/processes-and-threads.html

public class MainActivity extends AppCompatActivity {

    private ProgressBar progress;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        text = (TextView) findViewById(R.id.textView);
    }

    public void startProgress(View view) {
        // do something long
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 10; i++) {
                    final int value = i;
                    // fix a delay
                    doFakeWork();
                    // add a runnable to message queue
                    progress.post(new Runnable() {
                        @Override
                        public void run() {
                            text.setText("Updating");
                            progress.setProgress(value);
                        }
                    });
                }
                // the last message
                progress.post(new Runnable() {
                    @Override
                    public void run() {
                        text.setText("Finish");
                        progress.setProgress(0);
                    }
                });
            }
        };
        Thread t = new Thread(runnable);
        t.start();
    }

    // Simulating something timeconsuming
    private void doFakeWork() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
