package com.example.genji.am008_alertdialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by genji on 2/21/17.
 */

public class MyDFragment01 extends DialogFragment {

    int style;
    int theme;

    static MyDFragment01 newInstance(int style, int theme) {
        MyDFragment01 f = new MyDFragment01();
        Bundle args = new Bundle();
        args.putInt("style", style);
        args.putInt("theme", theme);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int dFStyle, dFTheme;

        style = getArguments().getInt("style");
        theme = getArguments().getInt("theme");

        dFStyle = DialogFragment.STYLE_NORMAL;
        dFTheme = android.R.style.Theme_Holo;

        switch (style%4) {
            case 0: dFStyle = DialogFragment.STYLE_NORMAL; break;
            case 1: dFStyle = DialogFragment.STYLE_NO_TITLE; break;
            case 2: dFStyle = DialogFragment.STYLE_NO_FRAME; break;
            case 3: dFStyle = DialogFragment.STYLE_NO_INPUT; break;
        }
        switch (theme%4) {
            case 0: dFTheme = android.R.style.Theme_Holo; break;
            case 1: dFTheme = android.R.style.Theme_Holo_Light_Dialog; break;
            case 2: dFTheme = android.R.style.Theme_Holo_Light_Panel; break;
            case 3: dFTheme = android.R.style.Theme_Holo_Light; break;
        }
        setStyle(dFStyle, dFTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.my_dfragment_01, container, false);
        Button button = (Button)v.findViewById(R.id.mcdf01b);
        final TextView tvd = (TextView)v.findViewById(R.id.mcdf01t);
        tvd.setText("");
        final TextView tv = (TextView)getActivity().findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tvd.setText("style #" + style + " theme #" + theme);
                tv.setText("clicked");
            }
        });

        return v;
    }

}
