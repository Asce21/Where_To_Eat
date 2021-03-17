package com.example.wheretoeat;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

public class MainActivity extends Activity {
    //Variable Declarations
    //AllLists wteList = new AllLists();
    Intent intent;
    Bundle bundle;
    int count;
    Cursor cursor;
    Connection conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        //Connect to the SQL Server database
        try {
            Log.e("MSSQL", "Attempting to connect");

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:jtds:sqlserver://admulkey.azurewebsites.net/Where_To_Eat",
                    "", "");

            Log.e("MSSQL", "Connected");
        } catch (Exception e)   {
            e.printStackTrace();
            Log.e("MSSQL", e.toString());
        }//End of the try catch to connect
         */

        //Create a database to hold the tables
        SQLiteDatabase wteDatabase = openOrCreateDatabase("wte_database.db", MODE_PRIVATE, null);
        wteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Restaurants(Name VARCHAR, DaysOne VARCHAR, DaysTwo VARCHAR, HoursOne VARCHAR, HoursTwo VARCHAR, AddressOne VARCHAR, AddressTwo VARCHAR, City VARCHAR, State VARCHAR, ZipCode VARCHAR, Phone VARCHAR, Website VARCHAR, Breakfast TEXT, Lunch TEXT, Dinner TEXT, CONSTRAINT restaurants_pk PRIMARY KEY (name, AddressOne, ZipCode));");
        /*
        wteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Users(Username VARCHAR, Password VARCHAR, Email VARCHAR, Phone VARCHAR);");
        cursor = wteDatabase.rawQuery("SELECT * FROM Users;", null);
        if (cursor.getCount() == 0) {
            wteDatabase.execSQL("INSERT INTO Users VALUES('admin', 'admin', 'amulkey21@yahoo.com', '714-552-2152');");
        }//End of if statement to initially populate the table Users
         */

        cursor = wteDatabase.rawQuery("SELECT * FROM Restaurants;", null);
        if (cursor.getCount() == 0) {
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Aloha Hawaiian BBQ', 'Every Day', '', '1030-2100', '', '3814 S. Bristol St.', 'Unit B', 'Santa Ana', 'CA', '92704', '7149571134', 'www.alohabbq1888.com', 'F', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Buca di Beppo', 'Every Day', '', '1130-2200', '', '7979 Center Ave.', '', 'Huntington Beach', 'CA', '92647', '7148914666', 'www.bucadibeppo.com', 'F', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Buffalo Wild Wings', 'Every Day', '', '1100-2000', '', '2811 S. Bristol St.', '', 'Santa Ana', 'CA', '92704', '7145401737', 'www.buffalowildwings.com', 'F', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Burger King', 'Every Day', '', '0700-2200', '', '2850 Bristol St.', '', 'Santa Ana', 'CA', '92704', '7149756126', 'www.bk.com', 'T', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Chcick-fil-A', 'M - Sa', '', '0730-2300', '', '3601 S. Bristol St.', '', 'Santa Ana', 'CA', '92704', '7145400020', 'www.chick-fil-a.com', 'T', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Chipotle', 'Every Day', '', '1030-2200', '', '3705 S. Bristol St.', '', 'Santa Ana', 'CA', '92704', '7147547380', 'www.chipotle.com', 'F', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Corner Bakery', 'Every Day', '', '', '', '1621 W. Sunflower Ave.', '', 'Santa Ana', 'CA', '92704', '7145461555', 'www.cornerbakerycafe.com', 'T', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Don Jacinto''s Pollo Grill', 'M-F', 'Sa-Su', '0930-2200', '0900-2200', '2413 S. Fairview St.', 'Unit H', 'Santa Ana', 'CA', '92704', '7144340821', 'www.donjacintopollogrill.com', 'F', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Duke''s', 'Every Day', '', '0630-2200', '', '2900 W. Warner Ave.', '', 'Santa Ana', 'CA', '92704', '7145575480', 'no web site', 'T', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Five Guys', 'Every Day', '', '1100-2100', '', '3030 Harbor Blvd.', '', 'Costa Mesa', 'CA', '92626', '7145490135', 'www.fiveguys.com', 'F', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('In-N-Out Burger', 'Every Day', '', '1030-0100', '', '3361 S. Bristol St.', '', 'Santa Ana', 'CA', '92704', '8007861000', 'www.in-n-out.com', 'F', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Jersey Mike''s', 'Every Day', '', '1100-1800', '', '3941 S. Bristol St.', 'Unit G', 'Santa Ana', 'CA', '92704', '7144381300', 'www.jerseymikes.com', 'F', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Jimmy John''s', 'Every Day', '', '1100-2200', '', '3930 S. Bristol St.', 'Unit 106', 'Santa Ana', 'CA', '92704', '7145404040', 'www.jimmyjohns.com', 'F', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('L&L Hawaiian BBQ', 'Every Day', '', '1100-2000', '', '19692 Beach Blvd.', '', 'Huntington Beach', 'CA', '92648', '7149681898', 'www.hawaiianbarbeque.com', 'F', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Maggiano''s Little Italy', 'Every Day', '', '1100-2100', '', '3333 Bristol St.', '', 'Santa Ana', 'CA', '92626', '7145469550', 'www.msggianos.com', 'F', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Maui Hawaiian BBQ', 'Every Day', '', '1100-2000', '', '16428 Beach Blvd.', '', 'Westminster', 'CA', '92683', '7145967299', 'www.mauihawaiianbbqca.com', 'F', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('McDonald''s', 'Every Day', '', '0700-2300', '', '6561 Edinger Blvd.', '', 'Huntington Beach', 'CA', '92647', '7148951476', 'www.mcdonalds.com', 'T', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('McDonald''s', 'Every Day', '', '0700-2300', '', '16866 Beach Blvd.', '', 'Huntington Beach', 'CA', '92647', '7148481361', 'www.mcdonalds.com', 'T', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Nate''s Korner', 'Every Day', '', '0600-1500', '', '3960 S. Main St.', 'Unit D', 'Santa Ana', 'CA', '92707', '7145455772', 'no website', 'T', 'T', 'F');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Olive Garden', 'SU-Th', 'F-Sa', '1100-2100', '1100-2000', '16811 Beach Blvd.', '', 'Huntington Beach', 'CA', '92647', '7148478874', 'www.olivegarden.com', 'F', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Panda Express', 'Every Day', '', '1000-2200', '', '18575 Beach Blvd.', '', 'Huntington Beach', 'CA', '92648', '7143759338', 'www.pandaexpress.com', 'F', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Panera Bread', 'Every Day', '', '', '', '16428 Beach Blvd.', '', 'Westminster', 'CA', '92683', '7148435600', 'www.panerabread.com', 'T', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Popeye''s', 'Every Day', '', '1000-2200', '', '14532 Beach Blvd.', '', 'Huntington Beach', 'CA', '92683', '7148988151', 'www.popeyes.com', 'F', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Raising Cane''s Chicken Fingers', 'Every Day', '', '0900-0330', '', '12775 Beach Blvd.', '', 'Westminster', 'CA', '90680', '7143730188', 'www.raisingcanes.com', 'F', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Ritter''s Steam Kettle Cooking', 'Every Day', '', '1100-2200', '', '1421 W. MacArthur Blvd.', 'Unit G', 'Santa Ana', 'CA', '92704', '7148501380', 'www.ritterskc.com', 'F', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Round Table Pizza', 'Every Day', '', '1030-2230', '', '16122 Goldenwest st', '', 'Huntington Beach', 'CA', '92647', '7148475517', 'www.roudtablepizza.com', 'F', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Tandoori Fresh', 'M-Sa', '', '1130-2200', '', '1500 Adams Ave.', 'Unit 100A', 'Costa Mesa', 'CA', '92626', '7144444407', 'www.tandoorifresh.com', 'F', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('The Habit Hamburger Grill', 'Every Day', '', '1100-2000', '', '15122 Goldenwest st.', 'Suite A', 'Westminster', 'CA', '92683', '6577770877', 'www.habitburger.com', 'F', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('The Hat', 'Every Day', '', '10000-2300', '', '23641 Rockfield Blvd.', '', 'Lake Forest', 'CA', '92630', '9495869200', 'www.thehat.com', 'F', 'T', 'T');");
            wteDatabase.execSQL("INSERT INTO Restaurants VALUES('Togo''s Sandwiches', 'Every Day', '', '1100-1900', '', '15062 Goldenwest st.', '', 'Westminster', 'CA', '92683', '7148998000', 'www.togos.com', 0'F', 'T', 'T');");
        }//End of if statement to initially populate the table Restaurants

        //For now Just go to the LoginActivity
        //openLoginActivity();
        openMainMenuActivity();
    }//End of method onCreate


    public void openLoginActivity() {
    intent = new Intent(MainActivity.this, LoginActivity.class);
    startActivity(intent);
}//End of method openLoginActivity

    public void openMainMenuActivity() {
        intent = new Intent(MainActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }//End of method openMainMenuActivity
/*
    public void readRestaurants()   {
        //Method Variables
        String name, daysOne, daysTwo, hoursOne, hoursTwo, addressOne, addressTwo, city, state, zip, phone, website, temp;
        boolean breakfast, lunch, dinner, locations;
        File path = new File("G:\\OneDrive Overflow\\Documents\\Education\\Self-Paced\\Apps\\Android\\Where_to_Eat\\app\\src\\main\\res\\raw\\init.txt");

        try {
            Scanner inputFile = new Scanner(path);
            while (inputFile.hasNext()) {
                inputFile.useDelimiter(";");
                name = inputFile.nextLine();
                daysOne = inputFile.nextLine();
                daysTwo = inputFile.nextLine();
                hoursOne = inputFile.nextLine();
                hoursTwo = inputFile.nextLine();
                addressOne = inputFile.nextLine();
                addressTwo = inputFile.nextLine();
                city = inputFile.nextLine();
                state = inputFile.nextLine();
                zip = inputFile.nextLine();
                phone = inputFile.nextLine();
                website = inputFile.nextLine();
                //inputFile.skip(";");
                temp = inputFile.nextLine();
                breakfast = !temp.equals("0");
                //inputFile.skip(";");
                temp = inputFile.nextLine();
                lunch = !temp.equals("0");
                //inputFile.skip(";");
                temp = inputFile.nextLine();
                dinner = !temp.equals("0");
                inputFile.useDelimiter("\n");
                temp = inputFile.nextLine();
                locations = !temp.trim().equals("0");
                wteList.restaurantArrayList.add(new Restaurant( name, daysOne, daysTwo, hoursOne, hoursTwo, addressOne, addressTwo,
                        city, state, zip, phone, website, breakfast, lunch, dinner, locations));
            }//End of the while loop to read the file
        }catch (Exception e)    {
            e.printStackTrace();
        }//End of catch block to read the API for restaurants

    }//End of method readRestaurants
*/

}//End of MainActivity

