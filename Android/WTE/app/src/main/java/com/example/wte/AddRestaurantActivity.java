package com.example.wte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddRestaurantActivity extends AppCompatActivity {
    //Variable Declarations
    Intent intent;
    String sqlStatement;
    EditText etRestaurantName, etDaysOpen, etHoursOpen, etAddressOne, etAddressTwo, etCity, etState, etZip, etURL, etPhone;
    Spinner spinnerServesBreakfast, spinnerServesLunch, spinnerServesDinner;
    String rName, rDays, rHours, rAdd1, rAdd2, rCity, rState, rZip, rURL, rPhone;
    String sBreak = "", sLunch = "", sDinner = "";
    Button btnSubmit;
    ConnectionHelper connectionHelper;
    Connection connection;
    Statement statement;
    ResultSet results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        //Variable Initializations
        etRestaurantName = (EditText) findViewById(R.id.et_restaurant_name);
        etDaysOpen = (EditText) findViewById(R.id.et_days_open);
        etHoursOpen = (EditText) findViewById(R.id.et_hours_open);
        etAddressOne = (EditText) findViewById(R.id.et_address_one);
        etAddressTwo = (EditText) findViewById(R.id.et_address_two);
        etCity = (EditText) findViewById(R.id.et_city);
        etState = (EditText) findViewById(R.id.et_state);
        etZip = (EditText) findViewById(R.id.et_zip);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etURL = (EditText) findViewById(R.id.et_web_address);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        spinnerServesBreakfast = (Spinner) findViewById(R.id.spinner_serves_breakfast);
        spinnerServesLunch = (Spinner) findViewById(R.id.spinner_serves_lunch);
        spinnerServesDinner = (Spinner) findViewById(R.id.spinner_serves_dinner);

        spinnerServesBreakfast.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos == 0)
                    sBreak = "T";
                else if (pos == 1)
                    sBreak = "F";
            }//End of method onItemSelected
            public void onNothingSelected(AdapterView<?> parent) {sBreak = "";}//End of method onNothingSelected
        });//End of spinnerServesBreakfast.setOnClickListener

        spinnerServesLunch.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos == 0)
                    sLunch = "T";
                else if (pos == 1)
                    sLunch = "F";
            }//End of method onItemSelected
            public void onNothingSelected(AdapterView<?> parent) {sLunch = "";}//End of method onNothingSelected
        });//End of spinnerServesLunch.setOnClickListener

        spinnerServesDinner.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (pos == 0)
                    sDinner = "T";
                else if (pos == 1)
                    sDinner = "F";
            }//End of method onItemSelected
            public void onNothingSelected(AdapterView<?> parent) {sDinner = "";}//End of method onNothingSelected
        });//End of spinnerServesDinner.setOnClickListener

        //Set the onclick listener
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                //Get the values of the fields submitted
                rName = etRestaurantName.getText().toString().trim();
                rDays = etDaysOpen.getText().toString().trim();
                rHours = etHoursOpen.getText().toString().trim();
                rAdd1 = etAddressOne.getText().toString().trim();
                rAdd2 = etAddressTwo.getText().toString().trim();
                rCity = etCity.getText().toString().trim();
                rState = etState.getText().toString().trim();
                rZip = etZip.getText().toString().trim();
                rURL = etURL.getText().toString().trim();
                rPhone = etPhone.getText().toString().trim();

                //Check if the restaurant already exist
                sqlStatement = "SELECT * FROM Restaurants WHERE Name = " + rName + " AND AddressOne = " + rAdd1 + " AND ZipCode = " + rZip + ";";

                try {
                    connectionHelper = new ConnectionHelper();
                    connection = connectionHelper.createConnection();
                    if (connection != null) {
                        statement = connection.createStatement();
                        results = statement.executeQuery(sqlStatement);

                        results.next();
                        if (results.getRow() == 1) {
                            Toast.makeText(getApplicationContext(), "Restaurant already exists", Toast.LENGTH_SHORT).show();
                        }//End of if statement reached when the restaurant is already in the database
                        else if (rName.isEmpty() || rDays.isEmpty() || rHours.isEmpty() || rAdd1.isEmpty() ||  rCity.isEmpty() || rState. isEmpty() ||
                                rZip.isEmpty() || rPhone.isEmpty() || rURL.isEmpty() || sBreak.isEmpty() || sLunch.isEmpty() || sDinner.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Address Line 2 is the only field that can be empty", Toast.LENGTH_SHORT).show();
                        }//End of else statement for any empty field
                        else    {
                            sqlStatement = "INSERT INTO where_to_eat.dbo.restaurants (Restaurant_Name, Days_Open, Hours_Open, Address_Line_1, " +
                                            "Address_Line_2, City, State, Zipcode, Phone_Number, Website, Serves_Breakfast, Serves_Lunch, " +
                                            "Serves_Dinner) VALUES (" + rName + ", " + rDays + ", " + rHours + ", " + rAdd1 + ", " + rAdd2 +
                                            ", " + rCity + ", " + rState + ", " + rZip + ", " + rURL + ", " + rPhone + ", " + sBreak +
                                            ", " + sLunch + ", " + sDinner + ");";
                        }//End of else statement to add the restaurant to the list
                    }//End of the if block
                }catch  (Exception exception)  {
                    Log.e("Error: ", exception.getMessage());
                    String errorMessage = "Error: " + exception.getMessage();
                }//End of the try / catch block
            }//End of method onClick
        });//End of btnSubmit.setOnClickListener


    }//End of method onCreate
}//End of class AddRestaurantActivity
