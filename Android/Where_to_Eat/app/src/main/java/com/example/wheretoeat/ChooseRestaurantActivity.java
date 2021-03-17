package com.example.wheretoeat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class ChooseRestaurantActivity extends Activity {
    //Variable Declarations
    TextView goTo;
    Intent intent;
    Random random;
    int range;
    int randomNumber;
    String toDisplay;
    SQLiteDatabase wteDatabase;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_restaurant);

        //Variable Initializations
        SQLiteDatabase wteDatabase = openOrCreateDatabase("wte_database.db", MODE_PRIVATE, null);
        cursor = wteDatabase.rawQuery("SELECT * FROM Restaurants;", null);
        goTo = (TextView) findViewById(R.id.tv_go_to);
        intent = getIntent();

        //Generate a random number
        random = new Random();
        range = cursor.getCount();
        randomNumber = random.nextInt(range);


        //Print the choice
        cursor.moveToPosition(randomNumber);
        toDisplay = "Go to " + cursor.getString(0);
        goTo.setText(toDisplay);
    }//End of method onCreate
}//End of class ChooseRestaurantActivity
