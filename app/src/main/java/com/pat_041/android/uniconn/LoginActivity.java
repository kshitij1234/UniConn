package com.pat_041.android.uniconn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.pat_041.android.uniconn.definitions.User;

import java.io.Serializable;
import java.util.List;

import static android.R.id.message;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button loginbtn;

    private DatabaseHandler databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHandler(this);

        List<User> contacts = databaseHelper.getAllUsers();

        // Add Dummy Data to the database


        // Get all the fields of view
        username = (EditText) findViewById(R.id.enterusername);
        password = (EditText) findViewById(R.id.enterpassword);
        loginbtn = (Button) findViewById(R.id.loginbutton);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if(user == null || user.equals("")){
                    Toast.makeText(v.getContext(), "Please Enter Username", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(pass == null || pass.equals("")){
                    Toast.makeText(v.getContext(), "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check for account validity and pass intent to next activity

                User employee = new User();
                try{
                    employee = databaseHelper.getUser(user);
                }catch (Exception e){
                    Toast.makeText(v.getContext(), "Invalid Username", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(employee==null){
                    Toast.makeText(v.getContext(), "Invalid Username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!employee.getPassword().equals(pass)){
                    Toast.makeText(v.getContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(employee.getPassword().equals(pass)) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("User", (Serializable) employee);
                    databaseHelper.close();
                    startActivity(intent);
                }

            }
        });

    }
}