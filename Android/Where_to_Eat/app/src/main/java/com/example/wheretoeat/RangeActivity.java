package com.example.wheretoeat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RangeActivity extends Activity {
    //Variable Declarations
    Intent intent;
    Button generateRandomNumber;
    TextView lowerBound;
    TextView upperBound;
    int range, lower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_range);

        //Variable Initializations
        generateRandomNumber = (Button) findViewById(R.id.btn_random);
        intent = new Intent(this, RandomNumberActivity.class);
        upperBound = (TextView) findViewById(R.id.tv_upper_bound);
        lowerBound = (TextView) findViewById(R.id.tv_lower_bound);

        generateRandomNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(upperBound.getText().toString()) >= Integer.parseInt(lowerBound.getText().toString()) )    {
                    range = Integer.parseInt(upperBound.getText().toString()) - Integer.parseInt(lowerBound.getText().toString()) + 1;
                    lower = Integer.parseInt(lowerBound.getText().toString());
                    intent.putExtra("range", range);
                    intent.putExtra("lower", lower);
                    startActivity(intent);
                }//End of if statement when upper bound is >= lower bound

            }//End of method onClick
        });//End of generateRandomNumber.setOnClickListener
    }//End of method onCreate
}//End of class RangeActivity
