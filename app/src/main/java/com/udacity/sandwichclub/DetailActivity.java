package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    TextView sandwich_origin;
    TextView sandwich_description;
    TextView sandwich_ingredients;
    TextView sandwich_knows_as;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        sandwich_origin = (TextView) findViewById(R.id.origin_tv);
        sandwich_description = (TextView) findViewById(R.id.description_tv);
        sandwich_ingredients = (TextView) findViewById(R.id.ingredients_tv);
        sandwich_knows_as = (TextView) findViewById(R.id.also_known_tv);
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
            //closeOnError();

            return;

        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI( Sandwich sandwich)
    {
        if (sandwich.getPlaceOfOrigin().isEmpty())
           {
               sandwich_origin.setText(R.string.detail_error_message);
           }

           else
               {

                 sandwich_origin.setText(sandwich.getPlaceOfOrigin());
               }

            if (sandwich.getAlsoKnownAs().isEmpty())
            {

                sandwich_knows_as.setText(R.string.detail_error_message);
            }
            else

                {
                    sandwich_knows_as.setText(Model_list(sandwich.getAlsoKnownAs()) );

                 }
                    if (sandwich.getIngredients().isEmpty())
                    {
                        sandwich_ingredients.setText(R.string.detail_error_message);
                    } else
                    {
                        sandwich_ingredients.setText(Model_list(sandwich.getIngredients()));
                    }

                    if (sandwich.getDescription().isEmpty())
                    {
                        sandwich_description.setText(R.string.detail_error_message);
                    }
                    else
                    {
                        sandwich_description.setText(sandwich.getDescription());
                    }



    }

    public StringBuilder Model_list(List<String> list1){
        StringBuilder stringBuilder= new StringBuilder();
        for (int i=0;i<list1.size();i++){
            stringBuilder.append(list1.get(i)).append("\n");
        }
        return stringBuilder;
    }

}
