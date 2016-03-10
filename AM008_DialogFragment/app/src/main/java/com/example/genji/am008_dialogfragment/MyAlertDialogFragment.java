package com.example.genji.am008_dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class MyAlertDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                /* set dialog icon: https://design.google.com/icons/#ic_alarm_on
                *  Right click on res folder, selecting New > Image Asset.
                *  browse image file. Select the largest image you have
                */
                .setIcon(R.mipmap.ic_alarm_on_black)
                // set Dialog Title
                .setTitle("Alert dialog fragment example")
                // Set Dialog Message
                .setMessage("Pay attention!!!")
                // positive button
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Pressed OK", Toast.LENGTH_SHORT).show();
                    }
                })
                // negative button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();
                    }
                }).create();
    }
}
