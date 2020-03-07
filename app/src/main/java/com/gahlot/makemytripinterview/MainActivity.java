package com.gahlot.makemytripinterview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    TextView crust,size;
    Button cartButton;
    ListView crustList, sizeList, sauceList;
    private ScrollView scrollView;
    private LinearLayout linearLayout;
    private RootViewModel rootViewModel;
    private static Root root1 = new Root();
    private static List<Variations> variations = new ArrayList<>();
    private List<List<Exclude_list>> exclude_lists = new ArrayList<>();
    private List<Variations> Size = new ArrayList<>();
    private List<Variations> Sauces = new ArrayList<>();
    private Utils utils = new Utils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                findViewById(R.id.container).getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });

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
                        crustList.getChildAt(i).setBackgroundColor(Color.parseColor("#ffce26"));
                        Size = utils.getSizes(utils.getMappedGroupIDVariationID(root1),exclude_lists,crustList.getItemAtPosition(position).toString(),root1.getVariants().getVariant_groups());
                        SizeCustomAdapter adapter2 = new SizeCustomAdapter(getApplicationContext(), Size);
                        sizeList.setAdapter(adapter2);
                    }else{
                        crustList.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }

            }
        });

        sizeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                for (int i = 0; i < sizeList.getChildCount(); i++) {
                    if (position == i) {
                        sizeList.getChildAt(i).setBackgroundColor(Color.parseColor("#ffce26"));
                        Sauces = utils.getSauces(utils.getMappedGroupIDVariationID(root1),exclude_lists,sizeList.getItemAtPosition(position).toString(),root1.getVariants().getVariant_groups());
                        SauceCustomAdapter adapter3 = new SauceCustomAdapter(getApplicationContext(), Sauces);
                        sauceList.setAdapter(adapter3);
                    } else{
                        sizeList.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
        });

        sauceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                for (int i=0; i < sauceList.getChildCount(); i++) {
                    if (position == i) {
                        sauceList.getChildAt(i).setBackgroundColor(Color.parseColor("#ffce26"));
                    } else {
                        sauceList.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
        });

    }

    void init() {
        crust = findViewById(R.id.crust_text);
        size = findViewById(R.id.size_text);
        cartButton = findViewById(R.id.cart_button);
        crustList = findViewById(R.id.crust_list);
        sizeList = findViewById(R.id.size_list);
        sauceList = findViewById(R.id.sauce_list);
        scrollView = findViewById(R.id.main_scrollview);
        linearLayout = findViewById(R.id.container);
    }

//    void orderCart(View view) {
//        if (pizzaType .equals("Cheese burst") && pizzaSize.equals("Small")) {
//            cartButton.setEnabled(false);
//            Toast.makeText(getApplicationContext(),"This combination is not allowed.",Toast.LENGTH_SHORT).show();
//        } else if (pizzaType .equals("Thick") && pizzaSize.equals(":Large")) {
//            cartButton.setEnabled(false);
//            Toast.makeText(this,"This combination is not allowed.",Toast.LENGTH_SHORT).show();
//        } else {
//            cartButton.setEnabled(true);
//        }
//    }
}
