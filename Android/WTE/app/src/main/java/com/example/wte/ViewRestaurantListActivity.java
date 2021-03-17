package com.example.wte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.zip.Inflater;

public class ViewRestaurantListActivity extends FragmentActivity implements ListFragment.ItemSelected {
    //Variable Declarations
    ConnectionHelper connectionHelper;
    Connection connection;
    Statement statement;
    ResultSet results;
    String connectionResult = "", sqlStatement = "", uriString = "";
    Intent intent;
    ArrayList<String> restaurantNames;
    ArrayList<Restaurant> allRestaurants;
    Bundle bundle;
    Fragment listFragment;
    int orientation;
    FragmentManager manager;
    LinearLayout llList, llDetails;
    LinearLayout.LayoutParams listParams, detailsParams;
    //Details Fragment layout Objects
    ImageView restaurantImage;
    TextView restaurantName, daysOpen, hoursOpen, mealsServed;
    String fullAddress = "", mealsServedString = "";
    Button btnPhone, btnAddress, btnURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_restaurant_list);


        try {
            //Variable Initializations
            connectionHelper = new ConnectionHelper();
            connection = connectionHelper.createConnection();
            restaurantNames = new ArrayList<>();
            allRestaurants = new ArrayList<>();
            bundle = new Bundle();
            listFragment = new Fragment();
            /*
            //Details Fragment layout Objects
            restaurantImage = (ImageView) findViewById(R.id.iv_restaurant_image);
            restaurantName = (TextView) findViewById(R.id.tv_restaurant_name);
            daysOpen = (TextView) findViewById(R.id.tv_days_open);
            hoursOpen = (TextView) findViewById(R.id.tv_hours_open);
            btnAddress = (Button) findViewById(R.id.btn_address);
            mealsServed = (TextView) findViewById(R.id.tv_meals_served);
            btnPhone = (Button) findViewById(R.id.btn_phone);
            btnURL = (Button) findViewById(R.id.btn_url);
            intent = new Intent(this, ListFragment.class);
             */

            if (connection != null) {
                sqlStatement = "SELECT * FROM Where_To_Eat.dbo.RestaurantLists";
                statement = connection.createStatement();
                results = statement.executeQuery(sqlStatement);

                while (results.next())  {
                    restaurantNames.add(results.getString(2));
                    allRestaurants.add(new Restaurant(results.getInt(1), results.getString(2), results.getString(3),
                            results.getString(4), results.getString(5), results.getString(6),
                            results.getString(7), results.getString(8), results.getString(9),
                            results.getString(10), results.getString(11), results.getString(12),
                            results.getString(13), results.getString(14)));
                }//End of the while loop

                //Send the ArrayList to the list fragment
                //intent.putStringArrayListExtra("restaurantNames", restaurantNames);

            } else  {
                connectionResult = "Check Connection";
            }//End of the if / else block
        }catch  (Exception exception)  {
            Log.e("Error: ", exception.getMessage());
            String errorMessage = "Error: " + exception.getMessage();
        }//End of the try / catch block
    }//End of method onCreate

    @Override
    protected void onStart() {
        super.onStart();

        llDetails = (LinearLayout) findViewById(R.id.ll_details);
        llDetails.setVisibility(View.GONE);
        /*
        detailsParams = new LinearLayout.LayoutParams(0, 100);
        detailsParams.weight = 0;
        llDetails.setLayoutParams(detailsParams);

         */
    }//End of method

    public void getTextFromSQL(View v)   {
    }//End of method getTextFromSQL

    @Override
    public void onItemSelected(int index) {
        llList = (LinearLayout) findViewById(R.id.ll_list);
        llDetails = (LinearLayout) findViewById(R.id.ll_details);
        llList.setVisibility(View.GONE);
        llDetails.setVisibility(View.VISIBLE);
        /*
        listParams = new LinearLayout.LayoutParams(0, 100);
        detailsParams = new LinearLayout.LayoutParams(100, 100);
        listParams.weight = 0;
        detailsParams.weight = 1;
        llList.setLayoutParams(listParams);
        llDetails.setLayoutParams(detailsParams);
         */

        fullAddress = "Address:\n" + allRestaurants.get(index).getAddressOne() + '\n' +
                (!allRestaurants.get(index).getAddressTwo().isEmpty() ? allRestaurants.get(index).getAddressTwo() + '\n': "") +
                allRestaurants.get(index).getCity() + ", " + allRestaurants.get(index).getState() + ' ' + allRestaurants.get(index).getZip();

        mealsServedString = "Meals Served:\n" + (allRestaurants.get(index).getBreakfast().equals("T") ? "Breakfast\n" : "") +
                (allRestaurants.get(index).getLunch().equals("T") ? "Lunch\n" : "") + (allRestaurants.get(index).getDinner().equals("T") ? "Dinner\n" : "");

        //Details Fragment layout Objects
        restaurantImage = (ImageView) findViewById(R.id.iv_restaurant_image);
        restaurantName = (TextView) findViewById(R.id.tv_restaurant_name);
        daysOpen = (TextView) findViewById(R.id.tv_days_open);
        hoursOpen = (TextView) findViewById(R.id.tv_hours_open);
        btnAddress = (Button) findViewById(R.id.btn_address);
        mealsServed = (TextView) findViewById(R.id.tv_meals_served);
        btnPhone = (Button) findViewById(R.id.btn_phone);
        btnURL = (Button) findViewById(R.id.btn_url);
        String item = restaurantNames.get(index), sqlStatement = "SELECT * FROM Restaurants WHERE Name = " + item + " ORDER BY Name;";

        restaurantName.setText(allRestaurants.get(index).getName());
        daysOpen.setText(allRestaurants.get(index).getDays());
        hoursOpen.setText(allRestaurants.get(index).getHours());
        btnAddress.setText(fullAddress);
        btnPhone.setText(allRestaurants.get(index).getPhone());
        btnURL.setText(allRestaurants.get(index).getWebsite());
        mealsServed.setText(mealsServedString);

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
                uriString = "geo:0,0?q=" + fullAddress;
                // Create a Uri from an intent string. Use the result to create an Intent.
                Uri gmmIntentUri = Uri.parse(uriString);

                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                // Attempt to start an activity that can handle the Intent
                startActivity(mapIntent);
            }//End of method onClick
        });//End of btnAddress.setOnClickListener

    }//End of method onItemSelected


}//End of class ViewRestaurantListActivity

/*
package com.example.wte;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewRestaurantListActivity extends AppCompatActivity {
    //Variable Declarations
    Connection connection;
    String connectionResult = "";
    int orientation;
    FragmentManager manager;
    //Details Fragment layout bjects
    ImageView restaurantImage;
    TextView restaurantName, daysOpen, hoursOpen, mealsServed;
    String fullAddress = "", mealsServedString = "";
    Button btnPhone, btnAddress, btnURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_restaurant_list);

        Button button = (Button) findViewById(R.id.button);
        TextView tvName = (TextView) findViewById(R.id.tvName);


        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.createConnection();

            if (connection != null) {
                String sqlStatement = "SELECT * FROM Where_To_Eat.dbo.RestaurantLists";
                Statement statement = connection.createStatement();
                ResultSet results = statement.executeQuery(sqlStatement);

                results.next();
                String rName = results.getString(2);
                results.next();
                String rName2 = results.getString(2);
                tvName.setText(results.getString(2));


            } else  {
                connectionResult = "Check Connection";
                tvName.setText(connectionResult);
            }
        }catch  (Exception exception)  {
            Log.e("Error: ", exception.getMessage());
            String errorMessage = "Error: " + exception.getMessage();
            tvName.setText(errorMessage);
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }//End of method onCreate

    public void getTextFromSQL(View v)   {}

    @Override
    public void onItemSelected(int index) {
        String item = restaurantNames.get(index), sqlStatement = "SELECT * FROM Restaurants WHERE Name = " + item + " ORDER BY Name;";

        orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT)  {
            manager = this.getSupportFragmentManager();
            manager.beginTransaction()
                    .hide(Objects.requireNonNull(manager.findFragmentById(R.id.list_fragm)))
                    .show(Objects.requireNonNull(manager.findFragmentById(R.id.details_frag)))
                    .addToBackStack(null)
                    .commit();
        }//End of if statement to hide the details fragment

        fullAddress = "Address:\n" + allRestaurants.get(index).getAddressOne() + '\n' +
                (!allRestaurants.get(index).getAddressTwo().isEmpty() ? allRestaurants.get(index).getAddressTwo() + '\n': "") +
                allRestaurants.get(index).getCity() + ", " + allRestaurants.get(index).getState() + ' ' + allRestaurants.get(index).getZip();

        mealsServedString = "Meals Served:\n" + (allRestaurants.get(index).getBreakfast().equals("T") ? "Breakfast\n" : "") +
                (allRestaurants.get(index).getLunch().equals("T") ? "Lunch\n" : "") + (allRestaurants.get(index).getDinner().equals("T") ? "Dinner\n" : "");


        restaurantName.setText(allRestaurants.get(index).getName());
        daysOpen.setText(allRestaurants.get(index).getDaysOne());
        hoursOpen.setText(allRestaurants.get(index).getHoursOne());
        btnAddress.setText(fullAddress);
        btnPhone.setText(allRestaurants.get(index).getPhone());
        btnURL.setText(allRestaurants.get(index).getWebsite());
        mealsServed.setText(mealsServedString);

    }//End of method onItemSelected
}//End of class ViewRestaurantListActivity
 */