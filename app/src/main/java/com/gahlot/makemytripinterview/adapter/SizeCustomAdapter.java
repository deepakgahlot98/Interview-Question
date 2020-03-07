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

public class SizeCustomAdapter extends ArrayAdapter {

    public SizeCustomAdapter(Context context, List<Variations> sizes) {
        super(context, 0, sizes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Variations size = (Variations) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }
        // Lookup view for data population
        TextView itemName = (TextView) convertView.findViewById(R.id.itemName);
        TextView itemStock = (TextView) convertView.findViewById(R.id.itemStock);
        TextView itemPrice = (TextView) convertView.findViewById(R.id.itemPrice);
        // Populate the data into the template view using the data object
        itemName.setText(size.getName().replaceAll("[^a-zA-Z0-9]", ""));
        itemStock.setText("Stock: " + size.getInStock());
        itemPrice.setText("Price:Rs " + size.getPrice());
        // Return the completed view to render on screen
        return convertView;
    }
}
