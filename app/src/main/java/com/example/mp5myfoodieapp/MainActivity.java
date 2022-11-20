package com.example.mp5myfoodieapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

import static com.example.mp5myfoodieapp.R.array.item_calories;
import static com.example.mp5myfoodieapp.R.array.item_descriptions;
import static com.example.mp5myfoodieapp.R.array.item_images;
import static com.example.mp5myfoodieapp.R.array.item_ingredients;
import static com.example.mp5myfoodieapp.R.array.item_links;
import static com.example.mp5myfoodieapp.R.array.item_titles;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ArrayList<MealItem> mData;
    private MealItemAdapter foodAdapter;
    private int gridColumnCount;
    private BroadcastReceiver MyReceiver;
    MealItem currentFood;
    String[] itemTitle;
    String[] itemDescription;
    String[] itemIngredient;
    String[] itemCalories;
    String[] itemLink;
    TypedArray itemImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.home");
        MyReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent){
                android.util.Log.d("MyReciever", "Broadcast Received");
                Random ran = new Random();
                int index = ran.nextInt(3);
                String act = intent.getAction();
                MealItem curMeal = mData.get(index);
                Intent start = new Intent(context, DetailsActivity.class);
                start.putExtra("Image_ID", curMeal.getImage());
                start.putExtra("Title", curMeal.getTitle());
                start.putExtra("Calories", curMeal.getCalories());
                start.putExtra("Ingredients", curMeal.getIngredient());
                start.putExtra("Description", curMeal.getDescription());
                start.putExtra("Link", curMeal.getLink());
                context.startActivity(start);
                android.widget.Toast.makeText(context, "Happy Cooking " + curMeal.getTitle(), android.widget.Toast.LENGTH_LONG).show();
//                android.util.Log.d(TAG, "index = " + index);

            }
        };
        registerReceiver(MyReceiver, intentFilter);

//        intentFilter.addAction();
        registerReceiver(MyReceiver, intentFilter);


        recyclerView = findViewById(R.id.recycler_view);

        gridColumnCount = 1;

        recyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));

        mData = new ArrayList<>();
        foodAdapter = new MealItemAdapter(this, mData);

        recyclerView.setAdapter(foodAdapter);

        loadFoodsData();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                foodAdapter.addContent();
            }
        });

    }





    private void loadFoodsData() {

        mData.clear();
        itemImage = getResources().obtainTypedArray( item_images);
        itemTitle = getResources().getStringArray( item_titles);
        itemDescription = getResources().getStringArray( item_descriptions);
        itemIngredient = getResources().getStringArray( item_ingredients);
        itemCalories = getResources().getStringArray( item_calories);
        itemLink = getResources().getStringArray( item_links);

        for(int i=0; i<itemTitle.length; i++){

            currentFood = new MealItem(itemTitle[i],itemDescription[i], itemIngredient[i],itemCalories[i],itemLink[i], itemImage.getResourceId(i,0));
            mData.add(currentFood);
        }

        foodAdapter.notifyDataSetChanged();
        itemImage.recycle();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_file, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}