package com.gahlot.makemytripinterview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gahlot.makemytripinterview.R;
import com.gahlot.makemytripinterview.model.Variations;
import java.util.List;

public class CustomAdapter  extends ArrayAdapter<Variations> {

    public CustomAdapter(Context context, List<Variations> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Variations user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }
        // Lookup view for data population
        TextView itemName = (TextView) convertView.findViewById(R.id.itemName);
        TextView itemStock = (TextView) convertView.findViewById(R.id.itemStock);
        TextView itemPrice = (TextView) convertView.findViewById(R.id.itemPrice);
        // Populate the data into the template view using the data object
        itemName.setText(user.getName());
        itemStock.setText("InStock: " + user.getInStock());
        itemPrice.setText("Price: " + user.getPrice());
        // Return the completed view to render on screen
        return convertView;
    }
}
