package com.gahlot.makemytripinterview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.gahlot.makemytripinterview.R;
import com.gahlot.makemytripinterview.model.Variations;

import java.util.List;

public class CartCustomAdapter extends ArrayAdapter<String> {

    public CartCustomAdapter(@NonNull Context context, List<String> orderDetails) {
        super(context, 0, orderDetails);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        String order = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cart_item, parent, false);
        }
        // Lookup view for data population
        TextView itemName = (TextView) convertView.findViewById(R.id.crust_name);
        TextView itemSize = (TextView) convertView.findViewById(R.id.size_name);
        TextView itemPrice = (TextView) convertView.findViewById(R.id.sauce_name);
        // Populate the data into the template view using the data object
        itemName.setText(user.getName());
        itemStock.setText("InStock: " + user.getInStock());
        itemPrice.setText("Price: " + user.getPrice());
        // Return the completed view to render on screen
        return convertView;
    }
}
