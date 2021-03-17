package com.example.wheretoeat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

public class ViewRestaurantListActivity extends Activity {
    //Variable Declarations
    SQLiteDatabase wteDatabase;
    Cursor cursor;
    Intent intent;
    ArrayList<String> restaurantNames;
    ArrayList<Restaurant> allRestaurants;
    ArrayAdapter adapter;
    ListView lvViewRestaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_restaurant_list);
        allRestaurants = new ArrayList<>();

        //Variable Initializations
        wteDatabase = openOrCreateDatabase("wte_database.db", MODE_PRIVATE, null);
        intent = getIntent();
        restaurantNames = new ArrayList<String>();
        allRestaurants = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, restaurantNames);
        lvViewRestaurants = (ListView) findViewById(R.id.lv_view_restaurants);

        //Populate the list of restaurant names
        cursor = wteDatabase.rawQuery("SELECT * FROM Restaurants ORDER BY Name;", null);
        if (cursor.moveToFirst()) {
            do {
                restaurantNames.add(cursor.getString(0));
                allRestaurants.add(new Restaurant(cursor.getString(0), cursor.getString(1), cursor.getString(3),
                        cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8),
                        cursor.getString(9), cursor.getString(10), cursor.getString(11),
                        cursor.getString(12), cursor.getString(13), cursor.getString(14)));
            } while (cursor.moveToNext());
        }//End of if statement to populate a list of restaurants

        //Add values to the list
        lvViewRestaurants.setAdapter(adapter);

        lvViewRestaurants.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(ViewRestaurantListActivity.this, ViewRestaurantDetailsActivity.class);
                intent.putExtra("restaurantToDisplay", allRestaurants.get(position));
                startActivity(intent);
            }//End of method onItemClick
        });//End of setOnItemClickListener
    }//End of method onCreate


}//End of class ViewRestaurantListActivity