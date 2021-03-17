package com.example.wheretoeat;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RandomNumberActivity extends Activity {
    //Variable Declarations
    TextView tvRandomNumber;
    Intent intent;
    Random random;
    int rangeVal, rn, lower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_number);

        //Variable Initializations
        tvRandomNumber = (TextView) findViewById(R.id.tv_random_number);
        intent = getIntent();
        rangeVal = intent.getIntExtra("range", 0);
        lower = intent.getIntExtra("lower", 0);

        //Generate a random number
        random = new Random();
        rn = random.nextInt(rangeVal) + lower;

        //Print the result
        tvRandomNumber.setText(String.valueOf(rn));

    }//End of method onCreate
}//End of class RandomNumberActivity
