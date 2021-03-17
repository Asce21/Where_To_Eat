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
import java.util.Vector;

public class RegisterActivity extends Activity {
    //Class Variables. RARELY initialize here
    final Vector<User> users = new Vector<User>();
    Boolean ok = true;
    Button register;
    EditText etUserName;
    EditText etPassword;
    EditText etRPassword;
    EditText etEmail;
    EditText etPhone;
    String username;
    String password;
    String rPassword;
    String emailAddress;
    String phoneNumber;
    SQLiteDatabase wteDatabase;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Variable Initializations
        wteDatabase = openOrCreateDatabase("wte_database", MODE_PRIVATE, null);
        Intent intent = getIntent();
        register = (Button) findViewById(R.id.RA_register);
        etUserName = (EditText) findViewById(R.id.RA_username);
        etPassword = (EditText) findViewById(R.id.RA_password);
        etRPassword = (EditText) findViewById(R.id.RA_rPassword);
        etEmail = (EditText) findViewById(R.id.RA_email);
        etPhone = (EditText) findViewById(R.id.RA_phone);


        register.setOnClickListener(new View.OnClickListener()    {
            @Override
            public void onClick (View v)    {
                //Variable Initializations
                username = etUserName.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                rPassword = etRPassword.getText().toString().trim();
                emailAddress = etEmail.getText().toString().trim();
                phoneNumber = etPhone.getText().toString().trim();

                //Check the values before sending to MainActivity
                cursor = wteDatabase.rawQuery("SELECT * FROM Users WHERE Username = " + username + ";", null);
                if (cursor.getCount() > 0)  {
                    Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_SHORT).show();
                }//End of if statement when username is taken
                else if (username != null && password != null && rPassword != null && emailAddress != null && phoneNumber != null &&
                        !username.isEmpty() && !password.isEmpty() && !rPassword.isEmpty() && !emailAddress.isEmpty() && !phoneNumber.isEmpty())    {
                    if (password.equals(rPassword)) {
                        wteDatabase.execSQL("INSERT INTO Users VALUES('" + username + "', '" + password + "', '" + emailAddress + "', '" + phoneNumber + "');");
                        openLoginActivity(username);
                    }//End of if statement that creates a new user in the database
                    else    {
                        Toast.makeText(getApplicationContext(), "Password mismatch", Toast.LENGTH_SHORT).show();
                    }//End of else statement if passwords do not match
                }//End of else if to verify that all fields contain values
                else    {
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                }//End of else statement if not all fields contain values
            }//End of method onClick
        });//End of method setOnClickListener

    }//End of method onCreate

    public void openLoginActivity(String name)    {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("Username", name);
        startActivity(intent);
    }//End of method openLoginActivity
}//End of RegisterActivity
