package com.gahlot.makemytripinterview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class OrderDetailActivity extends AppCompatActivity {

    private static final String TAG = "OrderDetailActivity";
    private static Map<OrderItem,String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            map = (Map<OrderItem, String>) extra.getSerializable("OrderItem");

            for (Map.Entry<OrderItem, String> s : map.entrySet()) {
                Log.i(TAG, "addToCart: " + s.getValue());
            }
        }

    }




}
