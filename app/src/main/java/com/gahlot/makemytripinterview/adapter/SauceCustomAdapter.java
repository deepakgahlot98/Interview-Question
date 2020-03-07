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

public class SauceCustomAdapter extends ArrayAdapter {

    public SauceCustomAdapter(@NonNull Context context, List<Variations> sauces) {
        super(context, 0,sauces);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Variations sauce = (Variations) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }
        // Lookup view for data population
        TextView itemName = (TextView) convertView.findViewById(R.id.itemName);
        TextView itemStock = (TextView) convertView.findViewById(R.id.itemStock);
        TextView itemPrice = (TextView) convertView.findViewById(R.id.itemPrice);
        // Populate the data into the template view using the data object
        itemName.setText(sauce.getName());
        itemStock.setText("Stock: " + sauce.getInStock());
        itemPrice.setText("Price:Rs " + sauce.getPrice());
        // Return the completed view to render on screen
        return convertView;
    }
}
