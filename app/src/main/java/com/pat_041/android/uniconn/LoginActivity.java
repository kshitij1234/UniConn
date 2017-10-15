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


import com.pat_041.android.uniconn.definitions.Project;
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

        databaseHelper.addUser(new User("jtatia", "Jai Tatia", "Admin1234","b-112,hope road","margao","Goa","cstudent","IIT Delhi","jai@gmail.com","7686432335"));
        databaseHelper.addUser(new User("abhijit", "Abhijit Roy", "Admin1234","b-112,hope road","kanpur","UP","student","Mano School","abh@gmail.com","7686222265"));
        databaseHelper.addUser(new User("shashwat", "Shashwat Tiwari", "Admin1234","b-112,hope road","guhawati","AP","professor","IIT Patna","shashwat@gmail.com","6486432265"));
        databaseHelper.addUser(new User("kshitij", "Kshitij Jauhri", "Admin1234","b-112,hope road","lucknow","UP","cstudent","IIT Patna","kshitij@gmail.com","7686872265"));

        databaseHelper.addProject(new Project(1,"Robotics System","robotics","Radio Frequency based remote controlled robot with wireless video camera mounted on it.","10 April, 2017","3 months","Bhopal"));
        databaseHelper.addProject(new Project(2,"Course Management System","java","Course management system is a Java based platform which provides an interface for professors and students to interact on course related topics and also provides a staging area where all assignments can be uploaded by faculties subject-wise. Students can then download the assignments and submit their solutions thus allowing faculties to check the solutions and score them.","12 July, 2017","4 months","Bombay"));
        databaseHelper.addProject(new Project(3,"ChatBot","machine learning","An automated machine learning based chatbot application implemented using re-inforcement learning","15 September, 2017","1 year","Kolkata"));
        databaseHelper.addProject(new Project(4,"Biometric Sensing System","biomedical","A hardware based device which can be used to get the biometrics of a person.","21 June, 2017","7 months","Bhopal"));

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