package com.example.wheretoeat;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends Activity {
    //Class Variables. RARELY initialize here
    Intent intent;
    String usernamePassed;
    Button btnViewList, btnChooseRestaurant, btnAddRestaurant, btnRandomNumber, btnEditRestaurant;
    SQLiteDatabase wteDatabase;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Variable Initializations
        SQLiteDatabase wteDatabase = openOrCreateDatabase("wte_database.db", MODE_PRIVATE, null);
        intent = getIntent();
        usernamePassed = intent.getStringExtra("username");
        btnViewList = (Button) findViewById(R.id.btn_view_list);
        btnChooseRestaurant = (Button) findViewById(R.id.btn_choose_restaurant);
        btnAddRestaurant = (Button) findViewById(R.id.btn_add_restauraunt);
        btnEditRestaurant = (Button) findViewById(R.id.btn_edit_restaurant);
        btnRandomNumber = (Button) findViewById(R.id.btn_random_number);

        btnViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainMenuActivity.this, RestaurantListActivity.class);
                intent.putExtra("username", usernamePassed);
                startActivity(intent);
            }//End of method onClick
        });//End of btnViewList.setOnClickListener

        btnChooseRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainMenuActivity.this, ChooseRestaurantActivity.class);
                intent.putExtra("username", usernamePassed);
                startActivity(intent);
            }//End of method onClick
        });//End of btnChooseRestaurant.setOnClickListener

        btnAddRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainMenuActivity.this, AddRestaurantActivity.class);
                startActivity(intent);
            }//End of method onClick
        });//End of btnAddRestaurant.setOnClickListener

        btnEditRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }//End of method onClick
        });//End of btnEditRestaurant.setOnClickListener

        btnRandomNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainMenuActivity.this, RangeActivity.class);
                intent.putExtra("username", usernamePassed);
                startActivity(intent);
            }//End of method onClick
        });//End of btnRandomNumber.setOnClickListener

    }//End of method onCreate

}//End of MainMenuActivity
