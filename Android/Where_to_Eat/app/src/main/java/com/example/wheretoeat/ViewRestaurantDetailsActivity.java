package com.example.wheretoeat;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ViewRestaurantDetailsActivity extends Activity implements OnMapReadyCallback {
    //Variable Declarations
    Restaurant restaurantToDisplay;
    Intent intent;
    String mealsS = "", fullAddress = "", geoAddress = "";
    ImageView restaurantImage;
    TextView restaurantName, daysOpen, hoursOpen, mealsServed;
    MapView mvAddress;
    Button btnAddress, btnPhone, btnURL;
    public static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";    //For the mapview


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_restaurant_details);

        //Variable Initializations
        intent = getIntent();
        restaurantToDisplay = intent.getParcelableExtra("restaurantToDisplay");
        restaurantImage = (ImageView) findViewById(R.id.iv_restaurant_image);
        restaurantName = (TextView) findViewById(R.id.tv_restaurant_name);
        daysOpen = (TextView) findViewById(R.id.tv_days_open);
        hoursOpen = (TextView) findViewById(R.id.tv_hours_open);
        mealsServed = (TextView) findViewById(R.id.tv_meals_served);
        mvAddress = (MapView) findViewById(R.id.mv_address);
        btnAddress = (Button) findViewById(R.id.btn_address);
        btnPhone = (Button) findViewById(R.id.btn_phone);
        btnURL = (Button) findViewById(R.id.btn_url);


        fullAddress = restaurantToDisplay.getAddressOne() + '\n' + (!restaurantToDisplay.getAddressTwo().isEmpty() ? restaurantToDisplay.getAddressTwo() + '\n': "") +
                restaurantToDisplay.getCity() + ", " + restaurantToDisplay.getState() + ' ' + restaurantToDisplay.getZip();

        mealsS = "Meals Served:\n";
        mealsS += (restaurantToDisplay.getBreakfast().equals("T") ? "Breakfast\n" : "") + (restaurantToDisplay.getLunch().equals("T") ? "Lunch\n" : "") +
                (restaurantToDisplay.getDinner().equals("T") ? "Dinner\n" : "");

        restaurantName.setText(restaurantToDisplay.getName());
        daysOpen.setText(restaurantToDisplay.getDaysOne());
        hoursOpen.setText(restaurantToDisplay.getHoursOne());
        btnAddress.setText(fullAddress);
        btnPhone.setText(restaurantToDisplay.getPhone());
        btnURL.setText(restaurantToDisplay.getWebsite());
        mealsServed.setText(mealsS);

        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse(btnPhone.getText().toString()));
                startActivity(intent);
            }//End of method onClick
        });//End of btnPhone.setOnClickListener

        btnURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(btnURL.getText().toString()));
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

        /*
        ------------------------------------------------------------------------------------------------------------------------------------------------------
        ------------------------------------------------------------------------------------------------------------------------------------------------------
        Map View Methods
        ------------------------------------------------------------------------------------------------------------------------------------------------------
        ------------------------------------------------------------------------------------------------------------------------------------------------------

         */
        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        // objects or sub-Bundles.
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mvAddress = (MapView) findViewById(R.id.mv_address);
        mvAddress.onCreate(mapViewBundle);

        mvAddress.getMapAsync(this);




    }//End of method onCreate

    /*
        ------------------------------------------------------------------------------------------------------------------------------------------------------
        ------------------------------------------------------------------------------------------------------------------------------------------------------
        Map View Methods
        ------------------------------------------------------------------------------------------------------------------------------------------------------
        ------------------------------------------------------------------------------------------------------------------------------------------------------

         */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mvAddress.onSaveInstanceState(mapViewBundle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mvAddress.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mvAddress.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mvAddress.onStop();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    @Override
    protected void onPause() {
        mvAddress.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mvAddress.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mvAddress.onLowMemory();
    }
}//End of class ViewRestaurantDetailsActivity

