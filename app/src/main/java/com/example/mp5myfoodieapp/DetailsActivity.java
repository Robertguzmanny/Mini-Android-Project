package com.example.mp5myfoodieapp;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    TextView mealTitle;
    TextView mealDescription;
    TextView mealIngredient;
    TextView mealCalories;
    TextView mealLink;
    ImageView images;
//    Bundle bundle;
//    ImageView image;
//    int image_view;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();

        mealTitle = findViewById(R.id.mealTitle);
        mealDescription = findViewById(R.id.mealDescription);
        mealIngredient = findViewById(R.id.mealIngredients);
        mealCalories = findViewById(R.id.mealCalories);
        mealLink = findViewById(R.id.mealLink);
        images = findViewById(R.id.mealImage);

        mealTitle.setText(intent.getStringExtra("Title"));
        mealDescription.setText(intent.getStringExtra("Description"));
        mealIngredient.setText(intent.getStringExtra("Ingredients"));
        mealCalories.setText(intent.getStringExtra("Calories"));
        mealLink.setText(intent.getStringExtra("Link"));

        images.setImageResource(intent.getExtras().getInt("Image_ID"));

//        bundle = this.getIntent().getExtras();
//        image_view = bundle.getInt("image");
//        image = findViewById(R.id.mealImage);
//        image.setImageResource(image_view);



    }
}