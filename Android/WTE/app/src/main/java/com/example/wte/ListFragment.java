package com.example.wte;

import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ListFragment extends androidx.fragment.app.ListFragment {
    //Variable Declarations
    ItemSelected activity;
    Bundle extras;
    ArrayList<String> restaurantNames;
    ListView lvRestaurantList;
    Adapter adapter;
    Connection connection;
    ConnectionHelper connectionHelper;
    Statement statement;
    ResultSet results;
    String connectionResult = "", sqlStatement = "";

    public interface ItemSelected   {
        void onItemSelected(int index);
    }//End of interface itemSelected

    public ListFragment() {
        // Required empty public constructor
    }//End of the default constructor

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        activity = (ItemSelected) context;
    }//End of method onAttach

    public static ListFragment newInstance(String param1, String param2) {
        ListFragment listFragment = new ListFragment();
        Bundle args = new Bundle();
        listFragment.setArguments(args);
        return listFragment;
    }//End of the method newInstance

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }//End of the method onCreate


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Inflate the fragment
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        //Query the database and get the list of names
        try {
            //Variable Initializations
            connectionHelper = new ConnectionHelper();
            connection = connectionHelper.createConnection();
            restaurantNames = new ArrayList<>();

            if (connection != null) {
                sqlStatement = "SELECT * FROM Where_To_Eat.dbo.RestaurantLists";
                statement = connection.createStatement();
                results = statement.executeQuery(sqlStatement);

                while (results.next())  {
                    restaurantNames.add(results.getString(2));
                }//End of the while loop
            } else  {
                connectionResult = "Check Connection";
            }//End of the if / else block
        }catch  (Exception exception)  {
            Log.e("Error: ", exception.getMessage());
        }//End of the try / catch block

        //Populate the listView layout object
        //lvRestaurantList = (ListView) rootView.findViewById(android.R.id.list);
        //lvRestaurantList.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, restaurantNames));

        setListAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, restaurantNames));

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }//End of the method onCreateView where everything happens

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        activity.onItemSelected(position);
    }//End of method onListItemClick


}//End of fragment ListFragment