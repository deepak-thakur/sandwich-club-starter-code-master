package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json)  {
        try {
            JSONObject jsonObject = new JSONObject(json);
            Log.d("parseSandwichJson","json start");

            // object of sandwich names
            JSONObject sandwichname = new JSONObject("sandwich");

            Log.d("parseSandwichJson","name loaded");

            //array list to hold also known as

            List<String> also_knowas_list = new ArrayList<>();
            JSONArray also_knowsas_array = sandwichname.getJSONArray("also known as");
            for (int i = 0; i<also_knowsas_array.length(); i++)
            {
                also_knowas_list.add(also_knowsas_array.optString(i));

                
            }
            Log.d("parseSandwichJson","also known added");

            // array list to hold the ingredients
            List<String> Ingredients_List = new ArrayList<>();
            JSONArray ingredientslist_Array = jsonObject.getJSONArray("Ingredients");
            for (int i=0; i<ingredientslist_Array.length();i++)
            {

                Ingredients_List.add(ingredientslist_Array.optString(i));
            }
            Log.d("parseSandwichJson","ingredients added");

                Sandwich sandwich = new Sandwich(

                        sandwichname.optString("sandwich_name"),
                        also_knowas_list,
                        jsonObject.optString("place_of_origin"),
                        jsonObject.optString("description"),
                        jsonObject.optString("image"),
                        Ingredients_List



                );

                return  sandwich;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
