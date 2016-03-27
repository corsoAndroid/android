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
    private String urlJsonArry = "192.168.1.2";
    // the main activity
    private Context context;
    // Progress dialog
    private ProgressDialog pDialog;

    // protocol

    private static boolean queryOK;
    private static boolean connectionOK;

    // the data

    List<Product> products;

    // constructor

    public Connector(Context context){
        this.context = context;
        this.queryOK = false;
        this.connectionOK = false;
        pDialog = new ProgressDialog(context);
    }

    /**
     * Method to make json array request where response starts with [
     * a message show the request
     *
     * es: message = "queryAll"
     *     gestString = queryAll (see PHP)
     *
     * */
    private void makeJsonArrayRequest(String dialogMessage, String getString) {

        showpDialog(dialogMessage);

        JsonArrayRequest req = new JsonArrayRequest(urlJsonArry + getString,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        try {
                            // Parsing json array response
                            // loop through each json object
                            JSONObject first = (JSONObject)response.get(0);
                            if(first.has("sql")){
                                if(first.getString("sql") == "ok"){
                                    queryOK = true;
                                } else {
                                    queryOK = false;
                                }
                            }
                            if(first.has("connection")){
                                if(first.getString("connection") == "ok"){
                                    connectionOK = true;
                                } else {
                                    connectionOK = false;
                                }
                                return;
                            }
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject msg = (JSONObject) response.get(i);

                                if (msg.has("sql") | msg.has("connection")) continue;

                                // TUTTO IL PROTOCOLLO

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(context,
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                        hidepDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(context,
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                hidepDialog();
            }
        });

        // Add a request
        MySingleton.getInstance(context).addToRequestQueue(req);
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
