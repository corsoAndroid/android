package com.example.genji.am101_db;

import android.app.FragmentManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // json array response url
    // private String urlDB = "192.168.1.2";

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ProductAdapter pAdapter;
    List<Product> products;
    List<Integer> productsUpdated; // positions
    List<Long> productsDeleted; //ids



    // manage connection
    private Connector mConnector;

    // fragment manager
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // fragment manager for dialogs
        fm = getFragmentManager();

        // create a connector
        mConnector = new Connector(this);

        // as in android developers
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {

            @Override
            public void onTouchEvent (RecyclerView rv, MotionEvent e){

            }
        });


        // specify an adapter
        products = MyData.createList();
        //products = new ArrayList<>();
        productsUpdated = new ArrayList<>();
        productsDeleted = new ArrayList<>();

        pAdapter = new ProductAdapter(products, this);
        mRecyclerView.setAdapter(pAdapter);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                MainActivity.this.openInsertDialog();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        // drawer.setDrawerListener(toggle); (deprecated)
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.drawer_update) {
            // Udate with remote resource
            if(!mConnector.down){
                downloadAll();
                mConnector.down = true;
            } else {
                deleteAll();
                updateAll();
                mConnector.down = true;
            }

            Log.w("UPDATE", "download all");
        } else if (id == R.id.drawer_connection) {
            // Check connection
            testConnection();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // MY ADDED METHODS *******************

    public void add(Product product){
        // add a product to list
        products.add(product);
        pAdapter.notifyItemInserted(products.size()-1);
    }

    public void update(int position, String description){
        // add product position ti update list
        productsUpdated.add(position);
        Product updated = products.get(position);
        updated.setDescription(description);
        updated.setUpdated(1);
        pAdapter.notifyItemChanged(position);
    }

    public void delete(int position){
        // add id product
        productsDeleted.add(products.get(position).getId());
        products.remove(position);
        // NOTIFY TO UPDATE
        pAdapter.notifyItemRemoved(position);
    }

    public void openInsertDialog(){
        // Create an instance of the dialog fragment and show it
        InsertDialog dialog = new InsertDialog();
        dialog.show(fm, "Insert");
    }

    public void openUpdateDialog(String name, int position){
        // Create an instance of the dialog fragment and show it;

        UpdateDialog dialog = new UpdateDialog();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putInt("position", position);
        dialog.setArguments(args);
        dialog.show(fm, "");
    }

    // Connector methods

    public void testConnection(){
        mConnector.testConnection();
    }

    public void downloadAll(){
        for(int position = 0; position < products.size(); position++){
            products.remove(position);
            // NOTIFY TO UPDATE
            pAdapter.notifyItemRemoved(position);
        }
        for(Product product : mConnector.downloadAll()){
            add(product);
        }
        // ***************** NB: notifyAll doesnt work here *****************
    }

    public void updateAll(){
        for(int position : productsUpdated) mConnector.update(position);
        productsUpdated.clear();
    }

    public void deleteAll(){
        for(long id : productsDeleted) mConnector.delete(id);
        productsDeleted.clear();
    }

}
