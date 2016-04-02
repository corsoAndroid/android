package com.example.genji.am015_viewholder;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by genji on 4/2/16.
 */
public class ProductsListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    ArrayList products;

    /*
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        products = new ArrayList<>();
        products.add(new Product("gioppini", "panetti sfiziosi"));
        products.add(new Product("jambonetti", "salatini al prosciutto"));
        products.add(new Product("patatine sfizione", "patatine salate ed aromatizzate"));
        products.add(new Product("tarallini", "anelli di pane"));
        products.add(new Product("gallette", "gallette plus"));
        products.add(new Product("frollini plus", "biscotti all'uovo"));
        products.add(new Product("cioccolini", "frollini con gocce di cioccolata"));
        products.add(new Product("secchini", "biscotti secchi"));
        products.add(new Product("grissinini", "grissini piccoli e sottili"));
        products.add(new Product("patasplash", "le patatine da bordo piscina"));
        products.add(new Product("majopatas", "le patatine aromatizzate .."));
        products.add(new Product("crocchette al sesamo", "panetti al sesamo"));
        products.add(new Product("crocchette alla pancetta", "panetti alla pancetta"));
        products.add(new Product("biscotti al miglio e avena", "i biscotti cinguettanti"));

        ProductsArrayAdapter adapter = new ProductsArrayAdapter(getActivity(), products);
        //use this below for a correct initialization
        setListAdapter(adapter);

        // connect to
        getListView().setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item: " + position + "\nname: " + ((Product)products.get(position)).getName() + "\n" +
                "description: " + ((Product)products.get(position)).getDescription(), Toast.LENGTH_SHORT).show();
    }
}