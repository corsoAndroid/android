package com.example.genji.am008_dialogfragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {


        FragmentManager manager = getFragmentManager();
        /* close existing dialog fragments .. da sistemare
        Fragment frag = manager.findFragmentByTag("fragment_edit_name");
        if (frag != null) {
            manager.beginTransaction().remove(frag).commit();
        }
        */
        switch (view.getId()) {
            case R.id.showCustomFragment:
                MyDialogFragment editNameDialog = new MyDialogFragment();
                editNameDialog.show(manager, "fragment_edit");
                break;
            case R.id.showAlertDialogFragment:
                MyAlertDialogFragment alertDialogFragment = new MyAlertDialogFragment();
                alertDialogFragment.show(manager, "fragment_alrt");
                break;
            case R.id.datePicker:
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(manager, "date_picker");
                break;
            case R.id.timePicker:
                TimePickerFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.show(manager, "time_picker");
                break;
        }
    }
}
