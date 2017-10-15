package com.pat_041.android.uniconn;

/**
 * Created by jaita on 14-Oct-17.
 */

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.pat_041.android.uniconn.definitions.User;

public class AndroidSQLiteTutorialActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addUser(new User("jtatia", "Jai Tatia", "Admin1234","b-112,hope road","margao","Goa","cstudent","IIT Delhi","jai@gmail.com","7686432335"));
        db.addUser(new User("abhijit", "Abhijit Roy", "Admin1234","b-112,hope road","kanpur","UP","student","Mano School","abh@gmail.com","7686222265"));
        db.addUser(new User("shashwat", "Shashwat Tiwari", "Admin1234","b-112,hope road","guhawati","AP","professor","IIT Patna","shashwat@gmail.com","6486432265"));
        db.addUser(new User("kshitij", "Kshitij Jauhri", "Admin1234","b-112,hope road","lucknow","UP","cstudent","IIT Patna","kshitij@gmail.com","7686872265"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<User> contacts = db.getAllUsers();

        for (User cn : contacts) {
            String log = "Id: "+cn.getId()+" ,Name: " + cn.getUname() + " ,Phone: " + cn.getInstitute();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }
    }
}