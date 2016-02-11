package com.example.genji.am016_sqlite;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class OperateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                 * Snackbar.make(view, "Add a product", Snackbar.LENGTH_LONG)
                 * .setAction("Action", null).show();
                 */
                showInputDialog();
            }
        });
    }

    protected void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(OperateActivity.this);
        View promptView = layoutInflater.inflate(R.layout.dialog_add, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(OperateActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editName = (EditText) promptView.findViewById(R.id.product_name);
        final EditText editDescription = (EditText) promptView.findViewById(R.id.product_description);

        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                /*
                                  Toast toast = Toast.makeText(getApplicationContext(), editName.getText(), Toast.LENGTH_SHORT);
                                  toast.show();
                                */
                                String name = editName.getText().toString();
                                String description = editDescription.getText().toString();
                                Product product = new Product(name, description);
                                ProductsDataSource pds = new ProductsDataSource(OperateActivity.this);
                                // DB transaction
                                pds.open();
                                pds.createProduct(product);
                                pds.close();
                                // get FragmentManager in order to obtain FragmentList
                                FragmentManager fm = OperateActivity.this.getFragmentManager();
                                ProductsListFragment plf = (ProductsListFragment)fm.findFragmentById(R.id.fragment);
                                // update list fragment
                                ProductsArrayAdapter adapter = (ProductsArrayAdapter)plf.getListAdapter();
                                adapter.add(product);
                                adapter.notifyDataSetChanged();
                            }
                        }

                    )
                            .

                                    setNegativeButton("Cancel",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            }

                    );

                    // create an alert dialog
                    AlertDialog alert = alertDialogBuilder.create();
        alert.show();
                }

    }
