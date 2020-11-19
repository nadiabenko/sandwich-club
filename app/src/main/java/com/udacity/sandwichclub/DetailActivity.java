package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.get().load(sandwich.getImage()).into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        TextView alsoKnownAsTextView = findViewById(R.id.also_known_tv);
        alsoKnownAsTextView.setText("");

        if(sandwich.getAlsoKnownAs().size()!=0) {
            for (String string : sandwich.getAlsoKnownAs()) {
                alsoKnownAsTextView.append(string + "\n");
            }
            alsoKnownAsTextView.setText(alsoKnownAsTextView.getText().toString().substring(0, alsoKnownAsTextView.getText().length() - 1));
        } else {
            alsoKnownAsTextView.setVisibility(View.GONE);
        }

        TextView placeOfOriginTextView = findViewById(R.id.origin_tv);

        if(!sandwich.getPlaceOfOrigin().equals("")) {
            placeOfOriginTextView.setText(sandwich.getPlaceOfOrigin());
        } else {
            placeOfOriginTextView.setVisibility(View.GONE);
        }

        TextView descriptionTextView = findViewById(R.id.description_tv);
        descriptionTextView.setText(sandwich.getDescription());
        TextView ingredientsTextView = findViewById(R.id.ingredients_tv);
        ingredientsTextView.setText("");

        for(String string: sandwich.getIngredients()){
            ingredientsTextView.append(string + "\n");
        }
    }
}
