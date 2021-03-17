package com.example.wheretoeat;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.app.Activity;
//import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RestaurantListActivity extends FragmentActivity implements RestaurantListFragment.ItemSelected {
    //Variable Declarations
    String meals = "", username, item;
    String mealsS = "", fullAddress = "", geoAddress = "", uriString = "";
    Intent intent, intent2, intent3;
    ArrayList<String> restaurantNames;
    ArrayList<Restaurant> allRestaurants;
    FragmentManager manager;
    int orientation;
    SQLiteDatabase wteDatabase;
    Cursor cursor;
    //Details Fragment Objects
    ImageView restaurantImage;
    TextView restaurantName, daysOpen, hoursOpen, mealsServed;
    MapView mvAddress;
    Button btnPhone, btnAddress, btnURL;
    Bundle bundle;
    Fragment listFragment, listFrag, detailsFrag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        //Variable Initializations
        SQLiteDatabase wteDatabase = openOrCreateDatabase("wte_database.db", MODE_PRIVATE, null);
        intent = getIntent();
        username = intent.getStringExtra("username");
        restaurantNames = new ArrayList<String>();
        allRestaurants = new ArrayList<>();
        intent = new Intent(this, RestaurantListFragment.class);
        //Details Fragment Objects
        restaurantImage = (ImageView) findViewById(R.id.iv_restaurant_image);
        restaurantName = (TextView) findViewById(R.id.tv_restaurant_name);
        daysOpen = (TextView) findViewById(R.id.tv_days_open);
        hoursOpen = (TextView) findViewById(R.id.tv_hours_open);
        btnAddress = (Button) findViewById(R.id.btn_address);
        mealsServed = (TextView) findViewById(R.id.tv_meals_served);
        btnPhone = (Button) findViewById(R.id.btn_phone);
        btnURL = (Button) findViewById(R.id.btn_url);
        bundle = new Bundle();
        listFragment = new Fragment();

        orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT)  {
            manager = this.getSupportFragmentManager();
            manager.beginTransaction()
                    .show(Objects.requireNonNull(manager.findFragmentById(R.id.list_fragment)))
                    .hide(Objects.requireNonNull(manager.findFragmentById(R.id.details_fragment)))
                    .addToBackStack(null)
                    .commit();
        }//End of if statement to hide the details fragment




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

        //Send the ArrayList to the fragment
        //bundle.putStringArrayList("names", restaurantNames);
        //listFragment.setArguments(bundle);


        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uriString = "tel:" + btnPhone.getText().toString();
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse(uriString));
                startActivity(intent);
            }//End of method onClick
        });//End of btnPhone.setOnClickListener

        btnURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uriString = "http://" + btnURL.getText().toString();
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uriString));
                startActivity(intent);
            }//End of method onClick
        });//End of btnURL.setOnClickListener

        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a string for the uri parsing
                geoAddress = "geo:0,0?q=" + fullAddress;
                // Create a Uri from an intent string. Use the result to create an Intent.
                Uri gmmIntentUri = Uri.parse(geoAddress);

                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                // Attempt to start an activity that can handle the Intent
                startActivity(mapIntent);
            }//End of method onClick
        });//End of btnAddress.setOnClickListener


    }//End of method onCreate

    @Override
    protected void onStart() {
        super.onStart();
        listFrag = getSupportFragmentManager().findFragmentById(R.id.list_fragment_all);
        detailsFrag = getSupportFragmentManager().findFragmentById(R.id.details_fragment_all);



        /*


        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        fts.hide(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.details_fragment_all)));
        fts.show(Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.list_fragment_all)));
        fts.addToBackStack(null);
        fts.commit();

        //SQLiteDatabase wteDatabase = openOrCreateDatabase("wte_database", MODE_PRIVATE, null);
        orientation = getResources().getConfiguration().orientation;
        manager = new FragmentActivity().getSupportFragmentManager();
        if (orientation == Configuration.ORIENTATION_PORTRAIT)  {
            manager.beginTransaction()
                    .show(Objects.requireNonNull(manager.findFragmentById(R.id.list_fragment)))
                    .hide(Objects.requireNonNull(manager.findFragmentById(R.id.details_fragment)))
                    .commit();
        }//End of if statement to hide the details fragment
         */
    }//End of method onStart

    @Override
    public void onItemSelected(int index) {
        String item = restaurantNames.get(index), sqlStatement = "SELECT * FROM Restaurants WHERE Name = " + item + " ORDER BY Name;";

        orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT)  {
            manager = this.getSupportFragmentManager();
            manager.beginTransaction()
                    .hide(Objects.requireNonNull(manager.findFragmentById(R.id.list_fragment)))
                    .show(Objects.requireNonNull(manager.findFragmentById(R.id.details_fragment)))
                    .addToBackStack(null)
                    .commit();
        }//End of if statement to hide the details fragment

        /*
        DatabaseHelper dbh = new DatabaseHelper(this);
        wteDatabase = dbh.getReadableDatabase();
        cursor = wteDatabase.rawQuery(sqlStatement, null);
        cursor = wteDatabase.rawQuery("SELECT * FROM Restaurants WHERE Name = ? AND ", new String[] {"item",});

        //Get the data for the restaurant
        restaurauntName = cursor.getString(0);
        daysOpen = cursor.getString(1);
        hoursOpen = cursor.getString(3);
        addressL1 = cursor.getString(5);
        addressL2 = cursor.getString(6);
        cityS = cursor.getString(7);
        stateS = cursor.getString(8);
        zipcode = cursor.getString(9);
        phoneNumber = cursor.getString(10);
        url = cursor.getString(11);
        breakfast = cursor.getString(12);
        lunch = cursor.getString(13);
        dinner = cursor.getString(14);
         */

        /*
        orientation = getResources().getConfiguration().orientation;
        manager = new FragmentActivity().getSupportFragmentManager();
        if (orientation == Configuration.ORIENTATION_PORTRAIT)  {
            manager.beginTransaction()
                    .hide(Objects.requireNonNull(manager.findFragmentById(R.id.list_fragment)))
                    .show(Objects.requireNonNull(manager.findFragmentById(R.id.details_fragment)))
                    .addToBackStack(null)
                    .commit();
        }//End of if statement to hide the details fragment
         */

        fullAddress = allRestaurants.get(index).getAddressOne() + '\n' + (!allRestaurants.get(index).getAddressTwo().isEmpty() ? allRestaurants.get(index).getAddressTwo() + '\n': "") +
                allRestaurants.get(index).getCity() + ", " + allRestaurants.get(index).getState() + ' ' + allRestaurants.get(index).getZip();

        mealsS = "Meals Served:\n";
        mealsS += (allRestaurants.get(index).getBreakfast().equals("T") ? "Breakfast\n" : "") + (allRestaurants.get(index).getLunch().equals("T") ? "Lunch\n" : "") +
                (allRestaurants.get(index).getDinner().equals("T") ? "Dinner\n" : "");

        /*

        mealsS = "";
        if (allRestaurants.get(index).getBreakfast().equals("T"))
            mealsS += "Breakfast\n";

        if (allRestaurants.get(index).getLunch().equals("T"))
            mealsS += "Lunch\n";

        if (allRestaurants.get(index).getDinner().equals("T"))
            mealsS += "Dinner";
         */
        restaurantName.setText(allRestaurants.get(index).getName());
        daysOpen.setText(allRestaurants.get(index).getDaysOne());
        hoursOpen.setText(allRestaurants.get(index).getHoursOne());
        btnAddress.setText(fullAddress);
        btnPhone.setText(allRestaurants.get(index).getPhone());
        btnURL.setText(allRestaurants.get(index).getWebsite());
        mealsServed.setText(mealsS);
        /*
        meals += cursor.getInt(12) == 'T'? "Breakfast\n" : "";
        meals += cursor.getInt(13) == 'T'? "Lunch\n" : "";
        meals += cursor.getInt(14) == 'T'? "Dinner\n" : "";
        mealsServed.setText(meals);
         */
    }//End of method onItemSelected

    ArrayList<String> getMyData()   {
        return restaurantNames;
    }//End of method getMyData


}//End of class RestaurantListActivity
