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

public class LoginActivity extends Activity {
    //Class Variables. RARELY initialize here
    Button register;
    Button login;
    EditText etUser;
    EditText etPass;
    String un, un2, pw, pw2;
    Intent intent;
    final Vector<User> users = new Vector<User>();  //An exception
    SQLiteDatabase wteDatabase;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Variable Initializations
        wteDatabase = openOrCreateDatabase("wte_database", MODE_PRIVATE, null);
        intent = getIntent();
        register = (Button) findViewById(R.id.register);
        login = (Button) findViewById(R.id.login);
        etUser = (EditText) findViewById(R.id.username);
        etPass = (EditText) findViewById(R.id.password);
        final Intent intent = getIntent();
        final String username = intent.getStringExtra("Username");

        //Check that a user has been successfully created
        if (username != null && !username.isEmpty())    {
            cursor = wteDatabase.rawQuery("SELECT * FROM Users WHERE Username = " + username + ";", null);
            if (cursor.getCount() == 1) {
                Toast.makeText(getApplicationContext(), username + " created successfully!", Toast.LENGTH_SHORT).show();
            }//End of if statement to check if username is in the table Users
        }//End of if statement to ensure that username has a value

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v)    {
                openRegisterActivity();
            }//End of method onClick
        });//End of method register.setOnClickListener

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                un = etUser.getText().toString();
                pw = etPass.getText().toString();
                cursor = wteDatabase.rawQuery("SELECT * FROM Users WHERE Username = '" + un + "' AND Password = '" + pw + "';", null);
                cursor.moveToFirst();
                if (!un.isEmpty() && !pw.isEmpty() && cursor.getString(0).equals(un) &&  cursor.getString(1).equals(pw))    {
                    openMainMenuActivity(un);
                }//End of if if statement to check that the username and password combination is valid
            }//End of method onClick
        });//End of login.setOnClickListener

    }//End of method onCreate

    public void openRegisterActivity()  {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }//End of openRegisterActivity

    public void openMainMenuActivity(String username)  {
        Intent intent = new Intent(this, MainMenuActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }//End of openMainMenuActivity
}//End of LoginActivity
