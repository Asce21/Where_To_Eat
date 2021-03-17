package com.example.wte;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //Class Variables. RARELY initialize here
    Intent intent;
    Button btnViewList, btnChooseRestaurant, btnAddRestaurant, btnRandomNumber, btnEditRestaurant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Variable Initializations
        btnViewList = (Button) findViewById(R.id.btn_view_list);
        btnChooseRestaurant = (Button) findViewById(R.id.btn_choose_restaurant);
        btnAddRestaurant = (Button) findViewById(R.id.btn_add_restauraunt);
        btnEditRestaurant = (Button) findViewById(R.id.btn_edit_restaurant);
        btnRandomNumber = (Button) findViewById(R.id.btn_random_number);

        btnViewList.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, ViewRestaurantListActivity.class);
                startActivity(intent);
            }//End of method onClick
        });//End of btnViewList.setOnClickListener

        btnChooseRestaurant.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, ChooseRestaurantActivity.class);
                startActivity(intent);
            }//End of method onClick
        });//End of btnChooseRestaurant.setOnClickListener

        btnAddRestaurant.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, AddRestaurantActivity.class);
                startActivity(intent);
            }//End of method onClick
        });//End of btnAddRestaurant.setOnClickListener

        btnEditRestaurant.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                //Insert Code Here
            }//End of method onClick
        });//End of btnEditRestaurant.setOnClickListener

        btnRandomNumber.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, RangeActivity.class);
                startActivity(intent);
            }//End of method onClick
        });//End of btnRandomNumber.setOnClickListener

    }//End of method onCreate
}//End of class androidx.constraintlayout.widget.ConstraintLayout