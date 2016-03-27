package com.example.genji.am008_dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by genji on 3/27/16.
 *
 * This versione works better: everywhere ....
 */
public class MyDialogFragment2 extends DialogFragment implements TextView.OnEditorActionListener{

    private EditText mEditText;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Context ctx = getActivity();
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_my_dialog_2, null, false);
        mEditText = (EditText) view.findViewById(R.id.username);
        // set this instance as callback for editor action
        mEditText.setOnEditorActionListener(this);
        // DO NOT UNCOMMENT ABOVE
        // mEditText.requestFocus();
        // getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        return new AlertDialog.Builder(ctx)
                //.setTitle("Please enter username")
                .setView(view)
                .create();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        // Return input text to activity
        TextView text = (TextView)getActivity().findViewById(R.id.textView);
        text.setText(mEditText.getText());
        // dismiss the Dialog Fragment
        this.dismiss();
        return true;
    }
}
