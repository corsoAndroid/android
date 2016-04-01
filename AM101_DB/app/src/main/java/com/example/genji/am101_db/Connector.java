package com.example.genji.am101_db;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by genji on 3/26/16.
 *
 * this class connect with JSON from MariaDB via PHP
 */
public class Connector {

    // TAG for Volley
    static final String TAG = "VConnector";

    // json array response url (modify it changing server)
    private static final String myurl = "http://192.168.1.2";
    // the main activity
    private Context context;
    // the data (received and transmitted)
    List<Product> products;
    // protocol
    boolean connectionOK, queryOK;

    // constructor

    public Connector(Context context){
        this.context = context;
        products = new ArrayList<>();
        connectionOK = queryOK = false;
    }

    /**
     * Method to make json array request where response starts with [
     * a message show the request
     *
     * es: message = "select"
     *     gestString = select (see PHP)
     *
     * */
    private void makeJsonArrayRequest(String getString) {

        final String urlComplete = myurl + "/" + getString;

        // showpDialog(dialogMessage + " :" + urlComplete);

        JsonArrayRequest req = new JsonArrayRequest(urlComplete,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        // clear product list
                        products.clear();

                        Log.d(TAG, response.toString());
                        try {
                            // test connection before any action
                            JSONObject first = (JSONObject)response.get(0);
                            // test connection
                            if(first.has("connection")){
                                if(first.getString("connection").matches("ok")){
                                    Log.d(TAG, "connection ok");
                                    connectionOK = true;
                                    Toast.makeText(context, "connection OK", Toast.LENGTH_SHORT).show();
                                } else {
                                    connectionOK = false;
                                    Toast.makeText(context, "connection NOK", Toast.LENGTH_SHORT).show();
                                }
                                return;
                            }
                            if(connectionOK && first.has("sql")){
                                if (first.getString("sql").matches("nok")) {
                                    queryOK = false;
                                    return;
                                } else {
                                    queryOK = false;
                                }
                                // this is not a select query
                                if (response.get(1) == null) return;
                                for (int i = 1; i < response.length(); i++) {

                                    // download a single object
                                    JSONObject msg = (JSONObject) response.get(i);
                                    long id = msg.getLong("_id");
                                    String name = msg.getString("name");
                                    String description = msg.getString("description");
                                    products.add(new Product(id, name, description, 0));
                                    Log.d(TAG, "download: (" + id + ", " + name + ", " + description +")");
                                }
                            } else {
                                Toast.makeText(context, "Try connection!", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context,
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(context,
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        // Add a request
        MySingleton.getInstance(context).addToRequestQueue(req);
    }

    // test connection method
    public void testConnection(){
        makeJsonArrayRequest("connection.php");
    }

    // synchronizing method from external DB
    List<Product> downloadAll(){
        makeJsonArrayRequest("select.php");
        Log.w(TAG, products.toString());
        return products;
    }

    void insert(Product product){
        String name = product.getName();
        String description = product.getDescription();
        String urlRequest = "insert.php?name=" + name
                + "&description=" + description;
        makeJsonArrayRequest(urlRequest);

        if(queryOK) Log.w(TAG, "(" + name +", + " + description +") inserted");
    }

    void update(Product product){
        long id = product.getId();
        String name = product.getName();
        String description = product.getDescription();
        String urlRequest = "update.php?id=" + String.valueOf(id) + "&name=" + name
                + "&description=" + description;
        makeJsonArrayRequest(urlRequest);

        if(queryOK) Log.w(TAG, "(" + name +", " + description +") updated");
    }

    void delete(long id){
        String urlRequest = "delete.php?_id=" + String.valueOf(id);
        makeJsonArrayRequest(urlRequest);

        if(queryOK) Log.w(TAG, "(" + id +") deleted");
    }


}