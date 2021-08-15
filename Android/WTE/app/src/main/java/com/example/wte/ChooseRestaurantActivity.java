package com.example.wte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class ChooseRestaurantActivity extends AppCompatActivity {
    //Variable Declarations
    TextView tvGoTo, tvAddress;
    Random random;
    int range, randomNumber, row;
    String toDisplay, address;
    ConnectionHelper connectionHelper;
    Connection connection;
    String sqlStatement;
    Statement statement;
    ResultSet results;
    Restaurant rToDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_restaurant);

        //Variable Initialization
        tvGoTo = (TextView) findViewById(R.id.tv_go_to);
        tvAddress = (TextView) findViewById(R.id.tv_address);

        //Get the database info
        try {
            connectionHelper = new ConnectionHelper();
            connection = connectionHelper.createConnection();
            sqlStatement = "SELECT * FROM Where_To_Eat.dbo.RestaurantLists";
            statement = connection.createStatement();
            results = statement.executeQuery(sqlStatement);

            //Generate a random number
            random = new Random();
            range = 0;
            while (results.next())
                range++;
            randomNumber = random.nextInt(range);

            //Get the info for the restaurant to display
            row = 0;
            results = statement.executeQuery(sqlStatement);
            while (row < randomNumber && results.next())  {
                row++;
                if (row == randomNumber)    {
                    toDisplay = "Go to " + results.getString(2);
                    rToDisplay = new Restaurant(results.getInt(1), results.getString(2), results.getString(3),
                            results.getString(4), results.getString(5), results.getString(6),
                            results.getString(7), results.getString(8), results.getString(9),
                            results.getString(10), results.getString(11), results.getString(12),
                            results.getString(13), results.getString(14));
                    address = rToDisplay.getAddressOne() + '\n' + (rToDisplay.getAddressTwo().isEmpty() ? rToDisplay.getAddressTwo() + '\n': "") +
                            rToDisplay.getCity() + ", " + rToDisplay.getState() + ' ' + rToDisplay.getZip();
                }//End of if statement
            }//End of while loop
        }catch  (Exception exception)   {
            Log.e("Error: " , exception.getMessage());
        }//End of try / catch block


        //Print the choice
        tvGoTo.setText(toDisplay);
        tvAddress.setText(address);
    }//End of method onCreate
}//End of class ChooseRestaurantActivity
