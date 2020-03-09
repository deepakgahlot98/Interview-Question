package com.gahlot.makemytripinterview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gahlot.makemytripinterview.adapter.CustomAdapter;
import com.gahlot.makemytripinterview.adapter.SauceCustomAdapter;
import com.gahlot.makemytripinterview.adapter.SizeCustomAdapter;
import com.gahlot.makemytripinterview.model.Exclude_list;
import com.gahlot.makemytripinterview.model.Root;
import com.gahlot.makemytripinterview.model.Variations;
import com.gahlot.makemytripinterview.utils.Utils;
import com.gahlot.makemytripinterview.viewModel.RootViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    TextView crust,size;
    Button cartButton;
    ListView crustList, sizeList, sauceList;
    private RootViewModel rootViewModel;
    private static Root root1 = new Root();
    private static List<Variations> variations = new ArrayList<>();
    private List<List<Exclude_list>> exclude_lists = new ArrayList<>();
    private List<Variations> Size = new ArrayList<>();
    private List<Variations> Sauces = new ArrayList<>();
    private Utils utils = new Utils();
    private Menu mToolbarMenu;
    private Map<OrderItem,String> map = new HashMap<>();
    private static int itemsInCart = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        rootViewModel = ViewModelProviders.of(this).get(RootViewModel.class);
        rootViewModel.getRoot().observe(this, new Observer<Root>() {
            @Override
            public void onChanged(Root root) {
                //update our recycler view
                root1 = root;
                variations = root.getVariants().getVariant_groups().get(0).getVariations();
                exclude_lists = root.getVariants().getExclude_list();
                CustomAdapter adapter1 = new CustomAdapter(getApplicationContext(), variations);
                crustList.setAdapter(adapter1);
            }
        });

        crustList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                for (int i = 0; i < crustList.getChildCount(); i++) {
                    if(position == i ){
                        map.put(OrderItem.CRUST,crustList.getChildAt(i).toString());
                        crustList.getChildAt(i).setBackgroundColor(Color.parseColor("#ffce26"));
                        Size = utils.getSizes(utils.getMappedGroupIDVariationID(root1),exclude_lists,crustList.getItemAtPosition(position).toString(),root1.getVariants().getVariant_groups());
                        SizeCustomAdapter adapter2 = new SizeCustomAdapter(getApplicationContext(), Size);
                        sizeList.setAdapter(adapter2);
                    }else{
                        crustList.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                        map.remove(OrderItem.CRUST);
                    }
                }

            }
        });

        sizeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                for (int i = 0; i < sizeList.getChildCount(); i++) {
                    if (position == i) {
                        map.put(OrderItem.SIZE,sizeList.getChildAt(i).toString());
                        sizeList.getChildAt(i).setBackgroundColor(Color.parseColor("#ffce26"));
                        Sauces = utils.getSauces(utils.getMappedGroupIDVariationID(root1),exclude_lists,sizeList.getItemAtPosition(position).toString(),root1.getVariants().getVariant_groups());
                        SauceCustomAdapter adapter3 = new SauceCustomAdapter(getApplicationContext(), Sauces);
                        sauceList.setAdapter(adapter3);
                    } else{
                        sizeList.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                        map.remove(OrderItem.SIZE);
                    }
                }
            }
        });

        sauceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                for (int i=0; i < sauceList.getChildCount(); i++) {
                    if (position == i) {
                        map.put(OrderItem.SAUCE,sauceList.getChildAt(i).toString());
                        sauceList.getChildAt(i).setBackgroundColor(Color.parseColor("#ffce26"));
                    } else {
                        sauceList.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                        map.remove(OrderItem.SAUCE);
                    }
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
        switch (paramMenuItem.getItemId()) {
            case R.id.cart:
                return true;
            default:
                return super.onOptionsItemSelected(paramMenuItem);
        }
    }

    public boolean onPrepareOptionsMenu(Menu paramMenu) {
        mToolbarMenu = paramMenu;
        createCartBadge(0);
        return super.onPrepareOptionsMenu(paramMenu);
    }

    private void createCartBadge(int paramInt) {
        if (Build.VERSION.SDK_INT <= 15) {
            return;
        }
        MenuItem cartItem = this.mToolbarMenu.findItem(R.id.cart);
        LayerDrawable localLayerDrawable = (LayerDrawable) cartItem.getIcon();
        Drawable cartBadgeDrawable = localLayerDrawable
                .findDrawableByLayerId(R.id.ic_badge);
        BadgeDrawable badgeDrawable;
        if ((cartBadgeDrawable != null)
                && ((cartBadgeDrawable instanceof BadgeDrawable))
                && (paramInt < 10)) {
            badgeDrawable = (BadgeDrawable) cartBadgeDrawable;
        } else {
            badgeDrawable = new BadgeDrawable(this);
        }
        badgeDrawable.setCount(paramInt);
        localLayerDrawable.mutate();
        localLayerDrawable.setDrawableByLayerId(R.id.ic_badge, badgeDrawable);
        cartItem.setIcon(localLayerDrawable);
    }

    void init() {
        crust = findViewById(R.id.crust_text);
        size = findViewById(R.id.size_text);
        cartButton = findViewById(R.id.cart_button);
        crustList = findViewById(R.id.crust_list);
        sizeList = findViewById(R.id.size_list);
        sauceList = findViewById(R.id.sauce_list);
    }

    public void addToCart(View view) {
        if (map.size() >= 3) {
            itemsInCart = itemsInCart + 1;
            createCartBadge(itemsInCart);
        } else {
            Toast.makeText(this,"Please select all the options for orderinng Pizza",Toast.LENGTH_SHORT).show();
        }
    }
}
