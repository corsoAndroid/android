package com.example.genji.am015_viewholder;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by genji on 4/2/16.
 */
public class ProductsArrayAdapter extends ArrayAdapter<Product> {
    private final Activity context;
    // the products arrayList
    private ArrayList products;

    // this object will be tag
    static class ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView description;
    }

    public ProductsArrayAdapter(Activity context, ArrayList products) {
        super(context, R.layout.row_layout, products);
        // this also work
        // super(context, 0, products);
        this.context = context;
        this.products = products;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder viewHolder;

        Product p = (Product)products.get(position);

        // reuse view: ViewHolder pattern
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.row_layout, null);
            // configure view holder
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) rowView.findViewById(R.id.name);
            viewHolder.description = (TextView) rowView.findViewById(R.id.description);
            viewHolder.image = (ImageView) rowView
                    .findViewById(R.id.icon);
            // take memory of the view
            rowView.setTag(viewHolder);
        } else {
            // reuse the object
            viewHolder = (ViewHolder) rowView.getTag();
        }
        viewHolder.image.setImageResource(R.mipmap.ic_launcher);
        viewHolder.name.setText(p.getName());
        viewHolder.description.setText(p.getDescription());
        return rowView;
    }
}

