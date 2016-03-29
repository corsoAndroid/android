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
    static final String TAG = "Volley";

    // json array response url
    private static final String myurl = "http://192.168.1.2";
    // the main activity
    private Context context;
    // Progress dialog
    private ProgressDialog pDialog;

    // the data (received and transmitted)
    List<Product> products;

    // constructor

    public Connector(Context context){
        this.context = context;
        pDialog = new ProgressDialog(context);
        pDialog.setCancelable(false);
        products = new ArrayList<>();
    }

    /**
     * Method to make json array request where response starts with [
     * a message show the request
     *
     * es: message = "select"
     *     gestString = select (see PHP)
     *
     * */
    private void makeJsonArrayRequest(String dialogMessage, String getString) {

        final String urlComplete = myurl + "/" + getString;

        // showpDialog(dialogMessage + " :" + urlComplete);

        JsonArrayRequest req = new JsonArrayRequest(urlComplete,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d(TAG, response.toString());
                        try {
                            // Parsing json array response
                            // loop through each json object
                            JSONObject first = (JSONObject)response.get(0);
                            if(first.has("connection")){
                                if(first.getString("connection").matches("ok")){
                                    Log.d(TAG, "connection ok");
                                    Toast.makeText(context, "connection OK", Toast.LENGTH_SHORT).show();
                                    // hide dialog
                                    hidepDialog();
                                } else {
                                    Toast.makeText(context, "connection NK", Toast.LENGTH_SHORT).show();
                                    // hide dialog
                                    hidepDialog();
                                }
                                return;
                            }
                            if(first.has("sql")){
                                if(first.getString("sql").matches("nok")){
                                    Toast.makeText(context, "query NOK", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }

                            // this is not a select query
                            if(response.get(1)==null) return;

                            for (int i = 1; i < response.length(); i++) {

                                JSONObject msg = (JSONObject) response.get(i);



                                long id = msg.getLong("_id");
                                String name = msg.getString("name");
                                String description = msg.getString("description");
                                products.add(new Product(id, name, description, 0));
                                Log.d(TAG, "download: " + name + ", " + description);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context,
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                        // hide dialog
                        hidepDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(context,
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide dialog
                hidepDialog();
            }
        });

        // Add a request
        MySingleton.getInstance(context).addToRequestQueue(req);
    }

    void downloadAll(){
        makeJsonArrayRequest("select all", "select.php");
        MainActivity ma = (MainActivity)context;
        ma.products = this.products;
    }



    public void testConnection(){
        makeJsonArrayRequest("test connection", "connection.php");
    }

    // dialog methods

    private void showpDialog(String msg) {
        if (!pDialog.isShowing())
            pDialog.show(context, "action", msg);
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
