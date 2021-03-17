/*
package com.example.wheretoeat;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class AllLists implements Parcelable {
    //Constructors
    AllLists()  {
        userList = new ArrayList<>(1);
        restaurantArrayList = new ArrayList<>(1);
    }//End of default constructor

    //Getters
    //Setters
    //Variables
    public ArrayList<User> userList;
    public ArrayList<Restaurant> restaurantArrayList;

    protected AllLists(Parcel in) {
        userList = new ArrayList<>(2);
        restaurantArrayList = new ArrayList<>(2);
        userList.add(new User("admin", "admin", "amulkey21@yahoo.com", "714-552-2152"));
        try {
            //readRestaurants();
        }catch (Exception e)    {
            e.printStackTrace();
        }//End of catch block to read the API for restaurants

    }

    public static final Creator<AllLists> CREATOR = new Creator<AllLists>() {
        @Override
        public AllLists createFromParcel(Parcel in) {
            return new AllLists(in);
        }

        @Override
        public AllLists[] newArray(int size) {
            return new AllLists[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public void readRestaurants() throws FileNotFoundException {
        //Method Variables
        String name, daysOne, daysTwo, hoursOne, hoursTwo, addressOne, addressTwo, city, state, zip, phone, website, temp;
        boolean breakfast, lunch, dinner, locations;



        try {
            File f1 = new File("G:\\OneDrive Overflow\\Documents\\Education\\Self-Paced\\Apps\\Android\\Where_to_Eat\\app\\src\\main\\res\\raw\\list_of_restaurants.txt");
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                String []pieces = line.split(";");
                name = pieces[0];
                daysOne = pieces[1];
                daysTwo = pieces[2];
                hoursOne = pieces[3];
                hoursTwo = pieces[4];
                addressOne = pieces[5];
                addressTwo = pieces[6];
                city = pieces[7];
                state = pieces[8];
                zip = pieces[9];
                phone = pieces[10];
                website = pieces[11];
                temp = pieces[12];
                breakfast = !temp.equals("0");
                temp = pieces[13];
                lunch = !temp.equals("0");
                temp = pieces[14];
                dinner = !temp.equals("0");
                temp = pieces[15];
                locations = !temp.trim().equals("0");
                restaurantArrayList.add(new Restaurant( name, daysOne, hoursOne, addressOne, addressTwo,
                        city, state, zip, phone, website, breakfast, lunch, dinner));
            }//End of the while loop to read the file
        }catch (FileNotFoundException e)    {
            e.printStackTrace();
        }//End of catch block to read the API for restaurants
        catch (Exception e1)    {
            e1.printStackTrace();
        }//End of general catch

    }//End of method readRestaurants


}//End of class AllLists

 */