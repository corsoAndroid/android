package com.example.genji.am032_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by genji on 3/31/16.
 */
public class ContactsAdapter extends
        RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private static List<Contact> mContacts;
    private static Context context;

    // Pass in the contact array into the constructor
    public ContactsAdapter(List<Contact> contacts, Context context) {
        mContacts = contacts;
        this.context = context;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_contact, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Contact contact = mContacts.get(position);

        // Set item views based on the data model
        TextView textView = viewHolder.nameTextView;
        textView.setText(contact.getName());

        Button button = viewHolder.messageButton;

        if (contact.isOnline()) {
            button.setText("Message");
            button.setEnabled(true);
        }
        else {
            button.setText("Offline");
            button.setEnabled(false);
        }

    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    // view holder class
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nameTextView;
        public Button messageButton;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.contact_name);
            messageButton = (Button) itemView.findViewById(R.id.message_button);
            messageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getLayoutPosition();
                    Contact contact = mContacts.get(position);
                    Toast.makeText(context, contact.getEmail(), Toast.LENGTH_SHORT).show();

                }
            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition(); // gets item position
            Contact contact = mContacts.get(position);
            Toast.makeText(context, contact.getName() + ": "+ position, Toast.LENGTH_SHORT).show();
        }

    }
}
