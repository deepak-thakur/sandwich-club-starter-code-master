package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* TextView sandwich_origin;
        TextView sandwich_description;
        TextView sandwich_ingredients;
        TextView sandwich_knows_as;

        sandwich_origin = (TextView) findViewById(R.id.origin_tv);
        sandwich_description = (TextView) findViewById(R.id.description_tv);
        sandwich_ingredients = (TextView) findViewById(R.id.ingredients_tv);
        sandwich_knows_as = (TextView) findViewById(R.id.also_known_tv); */



       // all sandwich names adapter for liste_view
        String[] sandwiches = getResources().getStringArray(R.array.sandwich_names);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, sandwiches);


        // sandwich details adapter
        String [] sand_details = getResources().getStringArray(R.array.sandwich_details);
         ArrayAdapter<String> adapter_for_sand = new ArrayAdapter<String>(this,R.layout.activity_detail,sand_details);


        // Simplification: Using a ListView instead of a RecyclerView
        ListView listView = findViewById(R.id.sandwiches_listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                launchDetailActivity(position);
            }
        });
    }

    private void launchDetailActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POSITION, position);
        startActivity(intent);
    }
}
