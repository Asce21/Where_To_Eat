package com.example.wheretoeat;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Objects;


public class RestaurantListFragment extends androidx.fragment.app.ListFragment {
    //Class Variables
    ItemSelected activity;
    ArrayList<String> restaurantNames;

    public interface ItemSelected   {
        void onItemSelected(int index);
    }//End of interface itemSelected

    public RestaurantListFragment() {
        // Required empty public constructor
    }//End of default constructor method

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        activity = (ItemSelected) context;
    }//End of method onAttach

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Populate the list to be displayed
        RestaurantListActivity parentActivity = (RestaurantListActivity) getActivity();
        assert parentActivity != null;
        restaurantNames = parentActivity.getMyData();

        //Add values to the list
        setListAdapter(new ArrayAdapter<String>(Objects.requireNonNull(getActivity()), android.R.layout.simple_list_item_1, restaurantNames));
    }//End of method onActivityCreated

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        activity.onItemSelected(position);
    }//End of method onListItemClick
}//End of fragment RestaurantListFragment
