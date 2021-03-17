package com.example.wheretoeat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddRestaurantActivity extends Activity {
    //Variable Declarations
    SQLiteDatabase wteDatabase;
    Cursor cursor;
    Intent intent;
    String usernamePassed, sqlStatement;
    EditText etRestauantName, etDaysOne, etDaysTwo, etHoursOne, etHoursTwo, etAddressOne, etAddressTwo, etCity, etState, etZip, etURL, etPhone;
    Button btnSubmit;
    DatabaseHelper dbh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        //Variable Initializations
        SQLiteDatabase wteDatabase = openOrCreateDatabase("wte_database.db", MODE_PRIVATE, null);
        dbh = new DatabaseHelper(AddRestaurantActivity.this);
        intent = getIntent();
        usernamePassed = intent.getStringExtra("username");
        etRestauantName = (EditText) findViewById(R.id.et_restaurant_name);
        etDaysOne = (EditText) findViewById(R.id.et_days_one);
        etDaysTwo = (EditText) findViewById(R.id.et_days_two);
        etHoursOne = (EditText) findViewById(R.id.et_hours_one);
        etHoursTwo = (EditText) findViewById(R.id.et_hours_two);
        etAddressOne = (EditText) findViewById(R.id.et_address_one);
        etAddressTwo = (EditText) findViewById(R.id.et_address_two);
        etCity = (EditText) findViewById(R.id.et_city);
        etState = (EditText) findViewById(R.id.et_state);
        etZip = (EditText) findViewById(R.id.et_zip);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etURL = (EditText) findViewById(R.id.et_web_address);
        btnSubmit = (Button) findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v)    {
                sqlStatement = "SELECT * FROM Restaurants WHERE Name = " + etRestauantName.getText().toString() + " AND AddressOne = " + etAddressOne.getText().toString() + " AND ZipCode = " + etZip.getText().toString() + ";";
                cursor = wteDatabase.rawQuery(sqlStatement, null);
                if (cursor.getCount() > 0)  {
                    Toast.makeText(getApplicationContext(), "Restaurant already exists", Toast.LENGTH_SHORT).show();
                }//End of if statement reached when the restaurant is already in the database
                else if(!etRestauantName.getText().toString().trim().isEmpty() && !etDaysOne.getText().toString().trim().isEmpty() && !etHoursOne.getText().toString().trim().isEmpty() &&
                        !etAddressOne.getText().toString().trim().isEmpty() && !etCity.getText().toString().trim().isEmpty() && !etState.getText().toString().trim().isEmpty() &&
                        !etZip.getText().toString().trim().isEmpty() && !etPhone.getText().toString().trim().isEmpty() && !etURL.getText().toString().trim().isEmpty())                    {
                    /*
                    sqlStatement = "INSERT INTO Restaurants VALUES('" + etRestauantName.getText().toString().trim() + "', '" + etDaysOne.getText().toString().trim() + "', '" + etDaysTwo.getText().toString().trim() +
                            "', '" + etHoursOne.getText().toString().trim() + "', '" + etHoursTwo.getText().toString().trim() + "', '" + etAddressOne.getText().toString().trim() +
                            "', '" + etAddressTwo.getText().toString().trim() + "', '" + etCity.getText().toString().trim() + "', '" + etState.getText().toString().trim() +
                            "', '" + etZip.getText().toString().trim() + "', '" + etPhone.getText().toString().trim() + "', '" + etURL.getText().toString().trim() + "', 0, 1, 1);";
                    wteDatabase.execSQL(sqlStatement);

                    dbh.addOneToRestaurants(etRestauantName.getText().toString().trim(), etDaysOne.getText().toString().trim(), etDaysTwo.getText().toString().trim(), etHoursOne.getText().toString().trim(),
                            etHoursTwo.getText().toString().trim(), etAddressOne.getText().toString().trim(), etAddressTwo.getText().toString().trim(), etCity.getText().toString().trim(),
                            etState.getText().toString().trim(), etZip.getText().toString().trim(), etPhone.getText().toString().trim(), etURL.getText().toString().trim());
                     */

                }//End of else clause to add a new restaurant to the list
                else    {
                    Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }//End of else when not all fields have values
            }//End of method onClick
        });//End of method btnSubmit.setOnClickListener

    }//End of method onCreate
}//End of class AddRestaurantActivity
