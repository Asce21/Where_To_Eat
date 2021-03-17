package com.example.wheretoeat;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "wte_database.db", null, 1);
    }//End of the default constructor

    @Override
    public void onCreate(SQLiteDatabase wteDatabase) {
        wteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Restaurants(Name VARCHAR, DaysOne VARCHAR, DaysTwo VARCHAR, HoursOne VARCHAR, HoursTwo VARCHAR, AddressOne VARCHAR, AddressTwo VARCHAR, City VARCHAR, State VARCHAR, ZipCode VARCHAR, Phone VARCHAR, Website VARCHAR, Breakfast TEXT, Lunch TEXT, Dinner TEXT, CONSTRAINT restaurants_pk PRIMARY KEY (name, AddressOne, ZipCode));");
    }//End of method onCreate to initially create the database

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }//End of method onUpgrade used when the database is updated



    public boolean addOneToRestaurants(String newName, String newDaysOne, String newHoursOne, String newAddressOne, String newAddressTwo, String newCity, String newState, String newZipCode, String newPhone, String newURL, String newBreakfast, String newLunch, String newDinner)    {
        SQLiteDatabase wteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Name", newName);
        cv.put("DaysOne", newDaysOne);
        cv.put("DaysTwo", "");
        cv.put("HoursOne", newHoursOne);
        cv.put("HoursTwo", "");
        cv.put("AddressOne", newAddressOne);
        cv.put("AddressTwo", newAddressTwo);
        cv.put("City", newCity);
        cv.put("State", newState);
        cv.put("ZipCode", newZipCode);
        cv.put("Phone", newPhone);
        cv.put("Website", newURL);
        cv.put("Breakfast", newBreakfast);
        cv.put("Lunch", newLunch);
        cv.put("Dinner", newDinner);

        wteDatabase.insert("Restaurants", null, cv);
        wteDatabase.close();
        return true;
    }//End of the method addOneToRestaurants

    public boolean editRestaurants(String newName, String newDaysOne, String newDaysTwo, String newHoursOne, String newHoursTwo, String newAddressOne, String newAddressTwo, String newCity, String newState, String newZipCode, String newPhone, String newURL, int newBreakfast, int newLunchDinner)    {
        SQLiteDatabase wteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Name", newName);
        cv.put("DaysOne", newDaysOne);
        cv.put("DaysTwo", newDaysTwo);
        cv.put("HoursOne", newHoursOne);
        cv.put("HoursTwo", newHoursTwo);
        cv.put("AddressOne", newAddressOne);
        cv.put("AddressTwo", newAddressTwo);
        cv.put("City", newCity);
        cv.put("State", newState);
        cv.put("ZipCode", newZipCode);
        cv.put("Phone", newPhone);
        cv.put("Website", newURL);
        cv.put("Breakfast", newBreakfast);
        cv.put("Lunch", newLunchDinner);
        cv.put("Dinner", newLunchDinner);

        wteDatabase.insert("Restaurants", null, cv);
        wteDatabase.close();
        return true;
    }//End of the method editRestaurants


}//End of class DatabaseHelper