package com.example.genji.am101_db;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by genji on 3/26/16.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    // my data
    private List<Product> productList;
    private Context mContext;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ProductAdapter(List<Product> pData, Context context) {
        productList = pData;
        mContext = context;
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void add(Product product, int position) {
        position = position == -1 ? getItemCount()  : position;
        productList.add(position, product);
        notifyItemInserted(position);
    }

    public void remove(int position){
        if (position < getItemCount()  ) {
            productList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void update(int position, String description){
        if (position < getItemCount()  ) {
            productList.remove(position);
            notifyItemRemoved(position);
        }
    }


    @Override
    public void onBindViewHolder(ProductViewHolder pvh, int i) {
        Product p = productList.get(i);
        pvh.vName.setText(p.getName());
        pvh.vDescription.setText(p.getDescription());
        pvh.vUpdated.setChecked(p.getUpdated() == 1 ? true : false);
        pvh.setClickListener(new ItemClickListener() {

            @Override
            public void onClick(View view, int position) {
                String name = (ProductAdapter.this.productList.get(position)).getName();
                Toast.makeText(mContext, "#" + position + " - " + name, Toast.LENGTH_SHORT).show();
                ((MainActivity) mContext).openUpdateDialog(name, position);
            }
        });
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_product, viewGroup, false);

        return new ProductViewHolder(itemView);
    }



    // you provide access to all the views for a data item in a view holder
    public static class ProductViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        protected TextView vName;
        protected TextView vDescription;
        protected CheckBox vUpdated;
        private ItemClickListener clickListener;

        public ProductViewHolder(View pv) {
            super(pv);
            vName = (TextView) pv.findViewById(R.id.name);
            vDescription = (TextView) pv.findViewById(R.id.description);
            vUpdated = (CheckBox) pv.findViewById(R.id.updated);
            pv.setOnClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getLayoutPosition());
        }
    }

    public static interface ItemClickListener {
        void onClick(View view, int position);
    }

}


